/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 50153
Source Host           : localhost:3306
Source Database       : academicdata

Target Server Type    : MYSQL
Target Server Version : 50153
File Encoding         : 65001

Date: 2011-10-30 17:51:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `author`
-- ----------------------------
CREATE TABLE `author` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`link`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`acm_index`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tm_index`  int(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=70163

;

-- ----------------------------
-- Table structure for `conference`
-- ----------------------------
CREATE TABLE `conference` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`tm_index`  int(11) NOT NULL ,
`global_index`  int(255) NULL DEFAULT NULL ,
`global_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`date`  datetime NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=366

;

-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
CREATE TABLE `paper` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`pages`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`source`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`link`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`abstract`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`doi`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`doi_link`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tm_index`  int(11) NOT NULL ,
`conference_id`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`conference_id`) REFERENCES `conference` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`conference_id`) REFERENCES `conference` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=22545

;

-- ----------------------------
-- Table structure for `paper_authors`
-- ----------------------------
CREATE TABLE `paper_authors` (
`author_id`  int(11) NOT NULL ,
`paper_id`  int(11) NOT NULL ,
PRIMARY KEY (`paper_id`, `author_id`),
FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Auto increment value for `author`
-- ----------------------------
ALTER TABLE `author` AUTO_INCREMENT=70163;

-- ----------------------------
-- Auto increment value for `conference`
-- ----------------------------
ALTER TABLE `conference` AUTO_INCREMENT=366;

-- ----------------------------
-- Indexes structure for table `paper`
-- ----------------------------
CREATE INDEX `conference_id` USING BTREE ON `paper`(`conference_id`) ;
CREATE INDEX `FK658118C933D6B4B` USING BTREE ON `paper`(`conference_id`) ;

-- ----------------------------
-- Auto increment value for `paper`
-- ----------------------------
ALTER TABLE `paper` AUTO_INCREMENT=22545;

-- ----------------------------
-- Indexes structure for table `paper_authors`
-- ----------------------------
CREATE INDEX `FK352A7C9513B6916B` USING BTREE ON `paper_authors`(`author_id`) ;
CREATE INDEX `FK352A7C95CD9D3089` USING BTREE ON `paper_authors`(`paper_id`) ;
