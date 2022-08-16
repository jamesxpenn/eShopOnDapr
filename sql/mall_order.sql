/*
Navicat MySQL Data Transfer

Source Server         : 20.232.224.16
Source Server Version : 80030
Source Host           : 20.232.224.16:3306
Source Database       : mall_order

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2022-08-11 14:18:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mall_order
-- ----------------------------
DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_no` bigint DEFAULT NULL,
  `user_id` int DEFAULT NULL COMMENT 'id',
  `shipping_id` int DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT ',,',
  `payment_type` int DEFAULT NULL COMMENT ',1-',
  `postage` int DEFAULT NULL COMMENT ',',
  `code_url` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT ':0--10-20-40-50-60-',
  `payment_time` datetime DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `close_time` datetime DEFAULT NULL,
  `create_time` text,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12691 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of mall_order
-- ----------------------------
INSERT INTO `mall_order` VALUES ('12685', '1660188204274', '1', '11', '15997.00', '1', '0', null, '60', null, null, null, null, '2022-08-11 11:23:23', null);
INSERT INTO `mall_order` VALUES ('12686', '1660188231189', '1', '11', '6999.00', '1', '0', null, '60', null, null, null, null, '2022-08-11 11:23:50', null);
INSERT INTO `mall_order` VALUES ('12687', '1660192255562', '1', '11', '8998.00', '1', '0', null, '20', null, null, null, null, '2022-08-11 12:30:54', null);
INSERT INTO `mall_order` VALUES ('12688', '1660192371389', '1', '11', '1999.00', '1', '0', null, '60', null, null, null, null, '2022-08-11 12:32:50', null);
INSERT INTO `mall_order` VALUES ('12689', '1660198080035', '9', '12', '13998.00', '1', '0', null, '20', null, null, null, null, '2022-08-11 14:07:59', null);
INSERT INTO `mall_order` VALUES ('12690', '1660198097512', '9', '12', '1999.00', '1', '0', null, '60', null, null, null, null, '2022-08-11 14:08:17', null);

-- ----------------------------
-- Table structure for mall_order_item
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_item`;
CREATE TABLE `mall_order_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int DEFAULT NULL,
  `order_no` bigint DEFAULT NULL,
  `product_id` int DEFAULT NULL COMMENT 'id',
  `product_name` varchar(100) DEFAULT NULL,
  `product_image` varchar(500) DEFAULT NULL,
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT ',',
  `quantity` int DEFAULT NULL,
  `total_price` decimal(20,2) DEFAULT NULL COMMENT ',,',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12293 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of mall_order_item
-- ----------------------------
INSERT INTO `mall_order_item` VALUES ('12285', '1', '1660188204274', '26', 'Apple iPhone 7 Plus (A1661) 128G  4G11', 'http://img.springboot.cn/241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '2', '13998.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12286', '1', '1660188204274', '28', '4+64G/Huawei/ nova P9/P10plus123', 'http://img.springboot.cn/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12287', '1', '1660188231189', '26', 'Apple iPhone 7 Plus (A1661) 128G  4G11', 'http://img.springboot.cn/241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '1', '6999.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12288', '1', '1660192255562', '26', 'Apple iPhone 7 Plus (A1661) 128G  4G11', 'http://img.springboot.cn/241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '1', '6999.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12289', '1', '1660192255562', '28', '4+64G/Huawei/ nova P9/P10plus123', 'http://img.springboot.cn/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12290', '1', '1660192371389', '28', '4+64G/Huawei/ nova P9/P10plus123', 'http://img.springboot.cn/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12291', '9', '1660198080035', '26', 'Apple iPhone 7 Plus (A1661) 128G  4G11', 'http://img.springboot.cn/241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '6999.00', '2', '13998.00', null, null);
INSERT INTO `mall_order_item` VALUES ('12292', '9', '1660198097512', '28', '4+64G/Huawei/ nova P9/P10plus123', 'http://img.springboot.cn/0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '1999.00', '1', '1999.00', null, null);
