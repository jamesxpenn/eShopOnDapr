package com.chinasofti.mall.user;

import com.chinasofti.mall.user.consts.MallConst;
import com.chinasofti.mall.user.exception.UserLoginException;
import com.chinasofti.mall.common.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuepeng@chinasofti.com
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {


	private static final ThreadLocal<String> THREAD_LOCAL=new ThreadLocal<>();

	/**
	 * true 表示继续流程，false表示中断
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle...");
		String userid = request.getHeader("userid");
		if(StringUtils.isBlank(userid)){{
			throw new UserLoginException();
		}}

		THREAD_LOCAL.set(userid);
		return true;
//		User user = (User) request.getSession().getAttribute(MallConst.CURRENT_USER);
//		if (user == null) {
//			log.info("user=null");
//			throw new UserLoginException();
//
////			response.getWriter().print("error");
////			return false;
////			return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//		}
//		return true;
	}

	public static String getUserId(){
		return THREAD_LOCAL.get();
	}
}
