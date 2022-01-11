CREATE TABLE `user`(
`id`BIGINT(6) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) NULL,
`last_name` VARCHAR(45) NULL,
`phone_no` VARCHAR(45) NULL,
`email` VARCHAR(50) NOT NULL,
`password` VARCHAR(200) NOT NULL,
`created_on` DATETIME NOT NULL ,
`created_by` VARCHAR(50) NOT NULL ,
`updated_on` DATETIME NULL ,
`updated_by` VARCHAR(50) NULL,
`deleted_on` DATETIME NULL,
`is_deleted` TINYINT(6) DEFAULT 0 NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE INDEX `email_UNIQUE` (`email` ASC),
 UNIQUE INDEX `id_UNIQUE` (`id` ASC)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
 INSERT INTO `user`(`id`,`first_name`,`last_name`,`phone_no`,`email`,`password`,`created_on`,`created_by`)  VALUES ('1','Ebin','B','8137854703','ebinb11@gmail.com','$2a$10$ENC8jbRrOjiQChht68l0p.aBXGiDtm3SXzMTQwj.UdVspWWV3EZQy','2021-01-10 12:01:34','ebin');
