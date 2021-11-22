SET NAMES utf8;

DROP TABLE IF EXISTS `meal`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `user`;

CReate SCHEMA IF NOT EXISTS `keto-diet`;
Use `keto-diet`;

SET character_set_client = utf8mb4;

SET character_set_client = utf8mb4;
CREATE TABLE `user`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name`       varchar(255)  DEFAULT NULL,
    `password`   varchar(255)  DEFAULT NULL,
    `tdee`       DOUBLE(19, 2) default null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_as_cs;


CREATE TABLE `product`
(
    `product_id`    int(11) NOT NULL AUTO_INCREMENT,
    `carbohydrates` DOUBLE(19, 2) DEFAULT NULL,
    `fat`           DOUBLE(19, 2) DEFAULT NULL,
    `kcal`          DOUBLE(19, 2) DEFAULT NULL,
    `name`          varchar(255)  DEFAULT NULL,
    `protein`       DOUBLE(19, 2) DEFAULT NULL,
    `user_id`       int(11) default NULL ,
    PRIMARY KEY (`product_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_as_cs;
SET character_set_client = utf8mb4;

CREATE TABLE `meal`
(
    `meal_id` int(11) NOT NULL AUTO_INCREMENT,
    `count`   int(11) NOT NULL,
    `date`    date    DEFAULT NULL,
    `product_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`meal_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_as_cs;

