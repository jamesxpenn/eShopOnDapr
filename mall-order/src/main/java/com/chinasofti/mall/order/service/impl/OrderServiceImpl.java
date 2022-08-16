package com.chinasofti.mall.order.service.impl;

import com.chinasofti.mall.common.dto.Cart;
import com.chinasofti.mall.common.dto.Product;
import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.dto.Shipping;
import com.chinasofti.mall.common.enums.OrderStatusEnum;
import com.chinasofti.mall.common.enums.PaymentTypeEnum;
import com.chinasofti.mall.common.enums.ProductStatusEnum;
import com.chinasofti.mall.common.enums.ResponseEnum;
import com.chinasofti.mall.common.vo.OrderItemVo;
import com.chinasofti.mall.common.vo.OrderVo;
import com.chinasofti.mall.order.dao.OrderItemMapper;
import com.chinasofti.mall.order.dao.OrderMapper;
import com.chinasofti.mall.order.feign.CartFeign;
import com.chinasofti.mall.order.feign.ProductFeign;
import com.chinasofti.mall.order.feign.ShippingFeign;
import com.chinasofti.mall.order.pojo.Order;
import com.chinasofti.mall.order.pojo.OrderItem;
import com.chinasofti.mall.order.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xuepeng@chinasofti.com
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private ShippingFeign shippingFeign;

	@Autowired
	private CartFeign cartFeign;

	@Autowired
	private ProductFeign productFeign;
//
//	@Autowired
//	private ICartService cartService;
//
//	@Autowired
//	private ProductMapper productMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Override
	@Transactional
	public Response<OrderVo> create(Integer uid, Integer shippingId) {

		Shipping shipping =shippingFeign.selectByUidAndShippingId(uid,shippingId);
		System.out.println("shipping--"+shipping);
//		Shipping shipping = shippingMapper.selectByUidAndShippingId(uid, shippingId);
		if (shipping == null) {
			return Response.error(ResponseEnum.SHIPPING_NOT_EXIST);
		}

		List<Cart> cartList = cartFeign.listcards(uid);
		if (CollectionUtils.isEmpty(cartList)) {
			return Response.error(ResponseEnum.CART_SELECTED_IS_EMPTY);
		}

		//获取购物车，校验（是否有商品、库存）
//		List<Cart> cartList = cartService.listForCart(uid).stream()
//				.filter(Cart::getProductSelected)
//				.collect(Collectors.toList());
//		if (CollectionUtils.isEmpty(cartList)) {
//			return Response.error(ResponseEnum.CART_SELECTED_IS_EMPTY);
//		}

		//获取cartList里的productIds
		Set<Integer> productIdSet = cartList.stream()
				.map(Cart::getProductId)
				.collect(Collectors.toSet());
		List<Product> productList = productFeign.productList(productIdSet);
		Map<Integer, Product> map  = productList.stream()
				.collect(Collectors.toMap(Product::getId, product -> product));

		//获取cartList里的productIds
//		Set<Integer> productIdSet = cartList.stream()
//				.map(Cart::getProductId)
//				.collect(Collectors.toSet());
//		List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
//		Map<Integer, Product> map  = productList.stream()
//				.collect(Collectors.toMap(Product::getId, product -> product));



		List<OrderItem> orderItemList = new ArrayList<>();
		Long orderNo = generateOrderNo();
		for (Cart cart : cartList) {
			//根据productId查数据库
			Product product = map.get(cart.getProductId());
			//是否有商品
			if (product == null) {
				return Response.error(ResponseEnum.PRODUCT_NOT_EXIST,
						"商品不存在. productId = " + cart.getProductId());
			}
			//商品上下架状态
			if (!ProductStatusEnum.ON_SALE.getCode().equals(product.getStatus())) {
				return Response.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE,
						"商品不是在售状态. " + product.getName());
			}

			//库存是否充足
			if (product.getStock() < cart.getQuantity()) {
				return Response.error(ResponseEnum.PROODUCT_STOCK_ERROR,
						"库存不正确. " + product.getName());
			}

			OrderItem orderItem = buildOrderItem(uid, orderNo, cart.getQuantity(), product);
			orderItemList.add(orderItem);

			//减库存
			product.setStock(product.getStock() - cart.getQuantity());

			int row = productFeign.update(product);
			//int row = productMapper.updateByPrimaryKeySelective(product);
			if (row <= 0) {
				return Response.error(ResponseEnum.ERROR);
			}
		}

		//计算总价，只计算选中的商品
		//生成订单，入库：order和order_item，事务
		Order order = buildOrder(uid, orderNo, shippingId, orderItemList);

		int rowForOrder = orderMapper.insertSelective(order);
		if (rowForOrder <= 0) {
			return Response.error(ResponseEnum.ERROR);
		}

		int rowForOrderItem = orderItemMapper.batchInsert(orderItemList);
		if (rowForOrderItem <= 0) {
			return Response.error(ResponseEnum.ERROR);
		}

		//更新购物车（选中的商品）
		//Redis有事务(打包命令)，不能回滚
		for (Cart cart : cartList) {
			cartFeign.deleteCart(uid, cart.getProductId());
			//cartService.delete(uid, cart.getProductId());
		}

		//构造orderVo
		OrderVo orderVo = buildOrderVo(order, orderItemList, shipping);
		return Response.success(orderVo);

	}

	@Override
	public Response<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Order> orderList = orderMapper.selectByUid(uid);

		Set<Long> orderNoSet = orderList.stream()
				.map(Order::getOrderNo)
				.collect(Collectors.toSet());
		List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet);
		Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream()
				.collect(Collectors.groupingBy(OrderItem::getOrderNo));

		Set<Integer> shippingIdSet = orderList.stream()
				.map(Order::getShippingId)
				.collect(Collectors.toSet());
		List<Shipping> shippingList = shippingFeign.shippingList(shippingIdSet);
		//List<Shipping> shippingList = shippingMapper.selectByIdSet(shippingIdSet);
		Map<Integer, Shipping> shippingMap = shippingList.stream()
				.collect(Collectors.toMap(Shipping::getId, shipping -> shipping));

		List<OrderVo> orderVoList = new ArrayList<>();
		for (Order order : orderList) {
			OrderVo orderVo = buildOrderVo(order,
					orderItemMap.get(order.getOrderNo()),
					shippingMap.get(order.getShippingId()));
			orderVoList.add(orderVo);
		}
		PageInfo pageInfo = new PageInfo<>(orderList);
		pageInfo.setList(orderVoList);

		return Response.success(pageInfo);
		//return null;
	}

	@Override
	public Response<OrderVo> detail(Integer uid, Long orderNo) {
		Order order = orderMapper.selectByOrderNo(orderNo);
		if (order == null || !order.getUserId().equals(uid)) {
			return Response.error(ResponseEnum.ORDER_NOT_EXIST);
		}
		Set<Long> orderNoSet = new HashSet<>();
		orderNoSet.add(order.getOrderNo());
		List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet);


		Shipping shipping = shippingFeign.getShipByShipID(order.getShippingId());
		//Shipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());

		OrderVo orderVo = buildOrderVo(order, orderItemList, shipping);
		return Response.success(orderVo);

		//return null;
	}

	@Override
	public Response cancel(Integer uid, Long orderNo) {
		Order order = orderMapper.selectByOrderNo(orderNo);
		if (order == null || !order.getUserId().equals(uid)) {
			return Response.error(ResponseEnum.ORDER_NOT_EXIST);
		}
		//只有[未付款]订单可以取消，看自己公司业务
		if (!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
			return Response.error(ResponseEnum.ORDER_STATUS_ERROR);
		}

		order.setStatus(OrderStatusEnum.CANCELED.getCode());
		order.setCloseTime(new Date());
		int row = orderMapper.updateByPrimaryKeySelective(order);
		if (row <= 0) {
			return Response.error(ResponseEnum.ERROR);
		}

		return Response.success();
	}

	@Override
	public void paid(Long orderNo) {
		Order order = orderMapper.selectByOrderNo(orderNo);
		if (order == null) {
			throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getDesc() + "订单id:" + orderNo);
		}
		if (!order.getStatus().equals(OrderStatusEnum.NO_PAY.getCode())) {
			throw new RuntimeException(ResponseEnum.ORDER_STATUS_ERROR.getDesc() + "订单id:" + orderNo);
		}

		order.setStatus(OrderStatusEnum.PAID.getCode());
		order.setPaymentTime(new Date());
		int row = orderMapper.updateByPrimaryKeySelective(order);
		if (row <= 0) {
			throw new RuntimeException("将订单更新为已支付状态失败，订单id:" + orderNo);
		}
	}

	private OrderVo buildOrderVo(Order order, List<OrderItem> orderItemList, Shipping shipping) {
		OrderVo orderVo = new OrderVo();
		BeanUtils.copyProperties(order, orderVo);

		List<OrderItemVo> OrderItemVoList = orderItemList.stream().map(e -> {
			OrderItemVo orderItemVo = new OrderItemVo();
			BeanUtils.copyProperties(e, orderItemVo);
			return orderItemVo;
		}).collect(Collectors.toList());
		orderVo.setOrderItemVoList(OrderItemVoList);

		if (shipping != null) {
			orderVo.setShippingId(shipping.getId());
			orderVo.setShippingVo(shipping);
		}

		return orderVo;
	}

	private Order buildOrder(Integer uid,
							 Long orderNo,
							 Integer shippingId,
							 List<OrderItem> orderItemList
							 ) {
		BigDecimal payment = orderItemList.stream()
				.map(OrderItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setUserId(uid);
		order.setShippingId(shippingId);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateStr = sdf.format(new Date());
		order.setCreateTime(dateStr);

		order.setPayment(payment);
		order.setPaymentType(PaymentTypeEnum.PAY_ONLINE.getCode());
		order.setPostage(0);
		order.setStatus(OrderStatusEnum.NO_PAY.getCode());
		return order;
	}

	/**
	 * 企业级：分布式唯一id/主键
	 * @return
	 */
	private Long generateOrderNo() {
		return System.currentTimeMillis() + new Random().nextInt(999);
	}

	private OrderItem buildOrderItem(Integer uid, Long orderNo, Integer quantity, Product product) {
		OrderItem item = new OrderItem();
		item.setUserId(uid);
		item.setOrderNo(orderNo);
		item.setProductId(product.getId());
		item.setProductName(product.getName());
		item.setProductImage(product.getMainImage());
		item.setCurrentUnitPrice(product.getPrice());
		item.setQuantity(quantity);
		item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
		return item;
	}
}
