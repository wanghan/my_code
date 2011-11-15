/*
Navicat MySQL Data Transfer

Source Server         : mysql_local
Source Server Version : 50153
Source Host           : localhost:3306
Source Database       : academicdata

Target Server Type    : MYSQL
Target Server Version : 50153
File Encoding         : 65001

Date: 2011-11-15 22:03:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `db_author`
-- ----------------------------
CREATE TABLE `db_author` (
`id`  bigint(11) NOT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`link`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`acm_index`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`tm_index`  int(11) NOT NULL ,
`position`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`affiliation`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`address`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`homepage`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`interest`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `db_conference`
-- ----------------------------
CREATE TABLE `db_conference` (
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
AUTO_INCREMENT=2

;

-- ----------------------------
-- Table structure for `db_paper`
-- ----------------------------
CREATE TABLE `db_paper` (
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
FOREIGN KEY (`id`) REFERENCES `db_conference` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`conference_id`) REFERENCES `db_conference` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`conference_id`) REFERENCES `db_conference` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `db_paper_authors`
-- ----------------------------
CREATE TABLE `db_paper_authors` (
`author_id`  bigint(11) NOT NULL ,
`paper_id`  int(11) NOT NULL ,
PRIMARY KEY (`author_id`, `paper_id`),
FOREIGN KEY (`author_id`) REFERENCES `db_author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`paper_id`) REFERENCES `db_paper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Auto increment value for `db_conference`
-- ----------------------------
ALTER TABLE `db_conference` AUTO_INCREMENT=2;

-- ----------------------------
-- Indexes structure for table `db_paper`
-- ----------------------------
CREATE INDEX `conference_id` USING BTREE ON `db_paper`(`conference_id`) ;
CREATE INDEX `FK658118C933D6B4B` USING BTREE ON `db_paper`(`conference_id`) ;
CREATE INDEX `FK7C8CAA8B10A3D36D` USING BTREE ON `db_paper`(`id`) ;

-- ----------------------------
-- Auto increment value for `db_paper`
-- ----------------------------
ALTER TABLE `db_paper` AUTO_INCREMENT=1;

-- ----------------------------
-- Indexes structure for table `db_paper_authors`
-- ----------------------------
CREATE INDEX `FK352A7C9513B6916B` USING BTREE ON `db_paper_authors`(`author_id`) ;
CREATE INDEX `FK352A7C95CD9D3089` USING BTREE ON `db_paper_authors`(`paper_id`) ;
