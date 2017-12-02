/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50508
Source Host           : 127.0.0.1:3306
Source Database       : db_blog

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-11-22 16:51:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `email` varchar(400) NOT NULL COMMENT '邮箱',
  `password` varchar(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `typeId` int(10) NOT NULL COMMENT '博客类型id',
  `title` varchar(60) NOT NULL COMMENT '文章标题',
  `content` text NOT NULL COMMENT '文章内容',
  `contentNoTag` text NOT NULL COMMENT '文章内容（无标签）',
  `summary` varchar(255) NOT NULL COMMENT '文章内容概要',
  `reader` int(10) NOT NULL COMMENT '阅读量',
  `updateTime` datetime NOT NULL COMMENT '更新时间',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_t_diary` (`typeId`),
  CONSTRAINT `专栏` FOREIGN KEY (`typeId`) REFERENCES `t_blogtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_blogger
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger`;
CREATE TABLE `t_blogger` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `imagePath` varchar(100) NOT NULL,
  `profile` text NOT NULL COMMENT '关于',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_blogtype
-- ----------------------------
DROP TABLE IF EXISTS `t_blogtype`;
CREATE TABLE `t_blogtype` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '博客类型id',
  `typeName` varchar(40) NOT NULL COMMENT '专栏名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code` (
  `id` int(10) NOT NULL COMMENT '用户账号id',
  `code` varchar(255) NOT NULL COMMENT '激活码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL COMMENT '用户名、昵称',
  `email` varchar(20) NOT NULL COMMENT '邮箱，用于登录',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `imagePath` varchar(40) DEFAULT NULL COMMENT '头像路径',
  `mood` varchar(50) DEFAULT NULL COMMENT '状态信息',
  `signInIP` varchar(20) NOT NULL,
  `signUpIP` varchar(20) NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '账号状态  0：未激活，1：已激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
