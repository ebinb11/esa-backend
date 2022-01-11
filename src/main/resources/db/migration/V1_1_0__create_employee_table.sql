CREATE TABLE IF NOT EXISTS `employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone_no` VARCHAR(45) NULL,
  `email_id` VARCHAR(45) NULL,
   `password` VARCHAR(100) NULL,
  `dob` DATETIME NULL,
  `is_deleted` TINYINT NULL,
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
