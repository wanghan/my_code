CREATE TABLE `author` (
`id` int(11) NOT NULL,
`name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`acm_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`index` int(11) NOT NULL,
PRIMARY KEY (`id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `conference` (
`id` int(11) NOT NULL,
`name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`index` int(11) NOT NULL,
`global_index` int(255) NULL DEFAULT NULL,
`global_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`date` date NOT NULL,
PRIMARY KEY (`id`) 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `paper` (
`id` int(11) NOT NULL,
`title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`pages` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`abstract` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`doi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`doi_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`index` int(11) NOT NULL,
`conference_id` int(11) NOT NULL,
PRIMARY KEY (`id`) ,
INDEX `conference_id` (`conference_id`),
INDEX `FK658118C933D6B4B` (`conference_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `paper_authors` (
`author_id` int(11) NOT NULL,
`paper_id` int(11) NOT NULL,
PRIMARY KEY (`paper_id`, `author_id`) ,
INDEX `FK352A7C9513B6916B` (`author_id`),
INDEX `FK352A7C95CD9D3089` (`paper_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;


ALTER TABLE `paper` ADD CONSTRAINT `conference_id` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`id`);
ALTER TABLE `paper` ADD CONSTRAINT `FK658118C933D6B4B` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`id`);
ALTER TABLE `paper_authors` ADD CONSTRAINT `FK352A7C95CD9D3089` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`);
ALTER TABLE `paper_authors` ADD CONSTRAINT `FK352A7C9513B6916B` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);

