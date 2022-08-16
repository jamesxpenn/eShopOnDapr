/*
Navicat MySQL Data Transfer

Source Server         : 20.232.224.16
Source Server Version : 80030
Source Host           : 20.232.224.16:3306
Source Database       : mall_shipping

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2022-08-11 14:18:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mall_shipping
-- ----------------------------
DROP TABLE IF EXISTS `mall_shipping`;
CREATE TABLE `mall_shipping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL COMMENT 'id',
  `receiver_name` varchar(20) DEFAULT NULL,
  `receiver_phone` varchar(20) DEFAULT NULL,
  `receiver_mobile` varchar(20) DEFAULT NULL,
  `receiver_province` varchar(20) DEFAULT NULL,
  `receiver_city` varchar(20) DEFAULT NULL,
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '/',
  `receiver_address` varchar(200) DEFAULT NULL,
  `receiver_zip` varchar(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of mall_shipping
-- ----------------------------
INSERT INTO `mall_shipping` VALUES ('11', '1', 'xuepeng', '0', '13313334567', '湖北省', '湖南省', '湖北省', '1111', '710014', null, null);
INSERT INTO `mall_shipping` VALUES ('12', '9', 'xuepeng', '0', '13411223311', '湖南省', '湖南省', '湖北省', '111', '710014', null, null);
