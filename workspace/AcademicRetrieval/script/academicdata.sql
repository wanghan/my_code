/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 50153
Source Host           : localhost:3306
Source Database       : academicdata

Target Server Type    : MYSQL
Target Server Version : 50153
File Encoding         : 65001

Date: 2011-11-15 21:27:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `db_author`
-- ----------------------------
DROP TABLE IF EXISTS `db_author`;
CREATE TABLE `db_author` (
  `id` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `acm_index` varchar(255) DEFAULT NULL,
  `tm_index` int(11) NOT NULL,
  `position` text,
  `affiliation` text,
  `address` text,
  `homepage` text,
  `interest` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_author
-- ----------------------------

-- ----------------------------
-- Table structure for `db_conference`
-- ----------------------------
DROP TABLE IF EXISTS `db_conference`;
CREATE TABLE `db_conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `tm_index` int(11) NOT NULL,
  `global_index` int(255) DEFAULT NULL,
  `global_name` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_conference
-- ----------------------------

-- ----------------------------
-- Table structure for `db_paper`
-- ----------------------------
DROP TABLE IF EXISTS `db_paper`;
CREATE TABLE `db_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `pages` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `abstract` text NOT NULL,
  `doi` varchar(255) DEFAULT NULL,
  `doi_link` varchar(255) DEFAULT NULL,
  `tm_index` int(11) NOT NULL,
  `conference_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conference_id` (`conference_id`),
  KEY `FK658118C933D6B4B` (`conference_id`),
  CONSTRAINT `conference_id` FOREIGN KEY (`conference_id`) REFERENCES `db_conference` (`id`),
  CONSTRAINT `FK658118C933D6B4B` FOREIGN KEY (`conference_id`) REFERENCES `db_conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_paper
-- ----------------------------

-- ----------------------------
-- Table structure for `db_paper_authors`
-- ----------------------------
DROP TABLE IF EXISTS `db_paper_authors`;
CREATE TABLE `db_paper_authors` (
  `author_id` bigint(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  PRIMARY KEY (`author_id`,`paper_id`),
  KEY `FK352A7C9513B6916B` (`author_id`),
  KEY `FK352A7C95CD9D3089` (`paper_id`),
  CONSTRAINT `db_paper_authors_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `db_author` (`id`),
  CONSTRAINT `FK352A7C95CD9D3089` FOREIGN KEY (`paper_id`) REFERENCES `db_paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_paper_authors
-- ----------------------------
