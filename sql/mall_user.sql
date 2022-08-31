/*
Navicat MySQL Data Transfer

Source Server         : 20.232.224.16
Source Server Version : 80030
Source Host           : 20.232.224.16:3306
Source Database       : mall_user

Target Server Type    : MYSQL
Target Server Version : 80030
File Encoding         : 65001

Date: 2022-08-11 14:19:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mall_user
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL COMMENT 'MD5',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL,
  `answer` varchar(100) DEFAULT NULL,
  `role` int NOT NULL COMMENT '0-,1-',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of mall_user
-- ----------------------------
INSERT INTO `mall_user` VALUES ('1', 'admin', '21232F297A57A5A743894A0E4A801FC3', 'admin@qq.com', null, null, null, '0', '2000-08-06 15:12:00', '2000-08-06 15:12:00');
INSERT INTO `mall_user` VALUES ('9', 'xuepeng', '8952a25920caad197865ec4b86b97f2f', 'xuepeng@chinasofti.com', null, null, null, '1', '2022-08-11 14:06:48', '2022-08-11 14:06:48');
