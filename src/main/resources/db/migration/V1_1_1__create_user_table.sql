
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO `role` VALUES (1,'User','The user'),
(2,'ADMIN','The Administrator'),
(3,'SUPER_ADMIN','The super admin'),
(4,'SENIOR_ADMIN','The senior admin');


CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `phone_no` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `created_on` datetime NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_on` datetime DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `is_deleted` tinyint(6) NOT NULL DEFAULT 0,
  `image_data` longblob DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

 
 INSERT INTO `user`(`id`,`first_name`,`last_name`,`phone_no`,`email`,`password`,`created_on`,`created_by`)  VALUES ('1','Ebin','B','8137854703','ebinb11@gmail.com','$2a$10$ENC8jbRrOjiQChht68l0p.aBXGiDtm3SXzMTQwj.UdVspWWV3EZQy','2021-01-10 12:01:34','ebin');
 INSERT INTO `user`(`id`,`first_name`,`last_name`,`phone_no`,`email`,`password`,`created_on`,`created_by`)  VALUES ('2','Admin','B','8137854703','admin@gmail.com','$2a$10$ENC8jbRrOjiQChht68l0p.aBXGiDtm3SXzMTQwj.UdVspWWV3EZQy','2021-01-10 12:01:34','ebin');
 INSERT INTO `user`(`id`,`first_name`,`last_name`,`phone_no`,`email`,`password`,`created_on`,`created_by`)  VALUES ('3','Super Admin','B','8137854703','superadmin@gmail.com','$2a$10$ENC8jbRrOjiQChht68l0p.aBXGiDtm3SXzMTQwj.UdVspWWV3EZQy','2021-01-10 12:01:34','ebin');
 INSERT INTO `user`(`id`,`first_name`,`last_name`,`phone_no`,`email`,`password`,`created_on`,`created_by`)  VALUES ('4','Senior Admin','B','8137854703','senioradmin@gmail.com','$2a$10$ENC8jbRrOjiQChht68l0p.aBXGiDtm3SXzMTQwj.UdVspWWV3EZQy','2021-01-10 12:01:34','ebin');


CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_user_role_1_idx` (`user_id`),
  KEY `fk_user_role_2_idx` (`role_id`),
  CONSTRAINT `fk_user_role_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;


INSERT INTO `user_role` VALUES (1,1,1);
INSERT INTO `user_role` VALUES (2,2,2);
INSERT INTO `user_role` VALUES (3,3,3);
INSERT INTO `user_role` VALUES (4,4,4);


 
