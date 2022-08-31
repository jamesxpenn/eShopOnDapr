/*
Navicat MySQL Data Transfer

Source Server         : 20.232.224.16
Source Server Version : 80030
Source Host           : 20.232.224.16:3306
Source Database       : mall_pay

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2022-08-11 14:18:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_payment_info
-- ----------------------------
DROP TABLE IF EXISTS `t_payment_info`;
CREATE TABLE `t_payment_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_no` varchar(50) DEFAULT NULL,
  `transaction_id` varchar(50) DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL,
  `trade_type` varchar(20) DEFAULT NULL,
  `trade_state` varchar(50) DEFAULT NULL,
  `payer_total` int DEFAULT NULL COMMENT '()',
  `content` text,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_payment_info
-- ----------------------------
INSERT INTO `t_payment_info` VALUES ('29', '1660192255562', '74d4a042-9ca5-49cc-b984-6d25d3f1b8f3', '微信', 'NATIVE', 'SUCCESS', '8998', null, '2022-08-11 04:30:59', '2022-08-11 04:30:59');
INSERT INTO `t_payment_info` VALUES ('30', '1660198080035', '4972a779-25f2-4a95-8578-7684da0274d1', '微信', 'NATIVE', 'SUCCESS', '13998', null, '2022-08-11 14:08:05', '2022-08-11 14:08:05');
