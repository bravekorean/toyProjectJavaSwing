
<!-- MY SQL 8.0.33  --!>

CREATE TABLE `stockdb`.`stock` (
  `proid` INT NOT NULL AUTO_INCREMENT,
  `proname` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`proid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `stockdb`.`user` (
  `usercode` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(45) NOT NULL,
  `usernpass` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `userpart` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usercode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;