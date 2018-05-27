/*
Navicat MySQL Data Transfer

Source Server         : after
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : czxy_blog

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-05-28 00:23:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------

DROP DATABASE IF EXISTS `czxy_blog`;

CREATE DATABASE IF NOT EXISTS `czxy_blog`;

USE `czxy_blog`;

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` tinytext,
  `content` mediumtext,
  `date` date DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `pic_link` varchar(500) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'java', 'jajajjajjaj', null, '1', null, '1');
INSERT INTO `blog` VALUES ('4', 'arrrrrrrrrrrrrrrr', 'zzzzzzzzzzzzz', '2018-05-16', '12', null, '1');
INSERT INTO `blog` VALUES ('5', 'fffffffffffffffffff', 'fffffffffffffff', '2018-05-16', '12', null, '1');
INSERT INTO `blog` VALUES ('6', '1111111111', '111111111', '2018-05-16', '12', null, '1');
INSERT INTO `blog` VALUES ('7', 'wwwwwwwwwwwwwwww', 'wwwwwwwwwwwwwwwww', '2018-05-16', '12', null, '1');
INSERT INTO `blog` VALUES ('8', 'android', 'ddddd', null, null, null, '1');
INSERT INTO `blog` VALUES ('9', 'android', 'ddddd', null, null, null, '1');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `blog_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES ('1', '2', null);
INSERT INTO `blog_tag` VALUES ('1', '8', null);
INSERT INTO `blog_tag` VALUES ('1', '2', null);
INSERT INTO `blog_tag` VALUES ('1', '8', null);
INSERT INTO `blog_tag` VALUES ('1', '7', null);
INSERT INTO `blog_tag` VALUES ('1', '8', null);
INSERT INTO `blog_tag` VALUES ('1', '6', null);
INSERT INTO `blog_tag` VALUES ('1', '7', null);
INSERT INTO `blog_tag` VALUES ('1', '8', null);
INSERT INTO `blog_tag` VALUES ('3', '6', null);
INSERT INTO `blog_tag` VALUES ('2', '6', null);
INSERT INTO `blog_tag` VALUES ('3', '6', null);
INSERT INTO `blog_tag` VALUES ('1', '1', null);
INSERT INTO `blog_tag` VALUES ('1', '6', null);
INSERT INTO `blog_tag` VALUES ('1', '2', null);
INSERT INTO `blog_tag` VALUES ('1', '6', null);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `content` text,
  `date` date DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `url` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('4', '1', 'aa', '2018-05-16', null, null, null);
INSERT INTO `comment` VALUES ('6', '3', 'hhhhhhhh', null, null, null, null);

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `info` varchar(500) DEFAULT NULL,
  `_order` int(11) DEFAULT NULL,
  `lxfs` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('1', 'hello', 'www.baidu.com', 'hi', null, null, null);
INSERT INTO `link` VALUES ('3', 'zzzzzzzzzzzzz', 'aaaaaaaaaaa', 'aaaaaaaa', '1', null, '2018-05-18');
INSERT INTO `link` VALUES ('11', 'ppppppppp', 'ppppppppppp', 'ppppppppppppp', '1', 'pppppppppppp', '2018-05-18');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(20) DEFAULT NULL,
  `_order` int(11) DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('5', 'aaaaaaa', null, '0', null);
INSERT INTO `notice` VALUES ('6', 'kkkkk', null, '0', null);

-- ----------------------------
-- Table structure for read_history
-- ----------------------------
DROP TABLE IF EXISTS `read_history`;
CREATE TABLE `read_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of read_history
-- ----------------------------
INSERT INTO `read_history` VALUES ('1', '1', null);
INSERT INTO `read_history` VALUES ('2', '1', null);
INSERT INTO `read_history` VALUES ('3', '1', null);
INSERT INTO `read_history` VALUES ('4', '2', null);
INSERT INTO `read_history` VALUES ('5', '2', null);
INSERT INTO `read_history` VALUES ('6', '2', null);
INSERT INTO `read_history` VALUES ('7', '2', null);
INSERT INTO `read_history` VALUES ('8', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('9', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('10', '2', '2018-05-16');
INSERT INTO `read_history` VALUES ('11', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('12', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('13', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('14', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('15', '2', '2018-05-16');
INSERT INTO `read_history` VALUES ('16', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('17', '2', '2018-05-16');
INSERT INTO `read_history` VALUES ('18', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('19', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('20', '2', '2018-05-16');
INSERT INTO `read_history` VALUES ('21', '2', '2018-05-16');
INSERT INTO `read_history` VALUES ('22', '2', '2018-05-16');
INSERT INTO `read_history` VALUES ('23', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('24', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('25', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('26', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('27', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('28', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('29', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('30', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('31', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('32', '1', '2018-05-16');
INSERT INTO `read_history` VALUES ('33', '1', '2018-05-17');
INSERT INTO `read_history` VALUES ('34', '10', '2018-05-17');
INSERT INTO `read_history` VALUES ('35', '9', '2018-05-17');
INSERT INTO `read_history` VALUES ('36', '9', '2018-05-17');
INSERT INTO `read_history` VALUES ('37', '1', '2018-05-17');
INSERT INTO `read_history` VALUES ('38', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('39', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('40', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('41', '7', '2018-05-18');
INSERT INTO `read_history` VALUES ('42', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('43', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('44', '10', '2018-05-18');
INSERT INTO `read_history` VALUES ('45', '7', '2018-05-18');
INSERT INTO `read_history` VALUES ('46', '9', '2018-05-18');
INSERT INTO `read_history` VALUES ('47', '3', '2018-05-18');
INSERT INTO `read_history` VALUES ('48', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('49', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('50', '9', '2018-05-18');
INSERT INTO `read_history` VALUES ('51', '9', '2018-05-18');
INSERT INTO `read_history` VALUES ('52', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('53', '3', '2018-05-18');
INSERT INTO `read_history` VALUES ('54', '4', '2018-05-18');
INSERT INTO `read_history` VALUES ('55', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('56', '3', '2018-05-18');
INSERT INTO `read_history` VALUES ('57', '5', '2018-05-18');
INSERT INTO `read_history` VALUES ('58', '6', '2018-05-18');
INSERT INTO `read_history` VALUES ('59', '7', '2018-05-18');
INSERT INTO `read_history` VALUES ('60', '10', '2018-05-18');
INSERT INTO `read_history` VALUES ('61', '3', '2018-05-18');
INSERT INTO `read_history` VALUES ('62', '4', '2018-05-18');
INSERT INTO `read_history` VALUES ('63', '3', '2018-05-18');
INSERT INTO `read_history` VALUES ('64', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('65', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('66', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('67', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('68', '4', '2018-05-18');
INSERT INTO `read_history` VALUES ('69', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('70', '4', '2018-05-18');
INSERT INTO `read_history` VALUES ('71', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('72', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('73', '4', '2018-05-18');
INSERT INTO `read_history` VALUES ('74', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('75', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('76', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('77', '1', '2018-05-18');
INSERT INTO `read_history` VALUES ('78', '4', '2018-05-18');
INSERT INTO `read_history` VALUES ('79', '5', '2018-05-18');
INSERT INTO `read_history` VALUES ('80', '6', '2018-05-18');
INSERT INTO `read_history` VALUES ('81', '7', '2018-05-18');
INSERT INTO `read_history` VALUES ('82', '10', '2018-05-18');
INSERT INTO `read_history` VALUES ('83', '6', '2018-05-18');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` tinytext,
  `info` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'errr', '', null);
INSERT INTO `tag` VALUES ('2', '1', '', null);
INSERT INTO `tag` VALUES ('6', 'errr', '', null);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `title` tinytext,
  `info` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', null, 'java', null);
INSERT INTO `type` VALUES ('2', null, 'android', null);
INSERT INTO `type` VALUES ('3', '1', 'java ??', null);
INSERT INTO `type` VALUES ('4', '0', 'python', null);
INSERT INTO `type` VALUES ('5', '0', 'php', null);
INSERT INTO `type` VALUES ('6', '0', 'c++', null);
INSERT INTO `type` VALUES ('7', null, 'bbb', 'aa');
INSERT INTO `type` VALUES ('8', '2', 'aaaaaaaa', 'rrrr');
INSERT INTO `type` VALUES ('9', null, 'aaaaaaaaaa', 'wwww');
INSERT INTO `type` VALUES ('10', '7', 'wwwwwwwww', '');
INSERT INTO `type` VALUES ('11', null, 'python', 'youknow');
INSERT INTO `type` VALUES ('12', '11', 'AI', 'wwwww');
INSERT INTO `type` VALUES ('13', '1', '9999', '');
INSERT INTO `type` VALUES ('14', '-1', 'java', '');
INSERT INTO `type` VALUES ('15', '-1', 'tttttttt', 'wwww');
INSERT INTO `type` VALUES ('16', '-1', '22222222222', 'aa');
INSERT INTO `type` VALUES ('17', '-1', '22222222222222', 'aa');
INSERT INTO `type` VALUES ('18', '11', 'zzzzzzzzzzzz', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `pic_link` varchar(500) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'waaaaaa', 'www', null, 'w', 'w@1', null);
INSERT INTO `user` VALUES ('2', 'waaaaaa', 'www', null, 'w', 'w@1', null);
