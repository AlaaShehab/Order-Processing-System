-- MySQL Workbench Forward Engineeringuseremail

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SAMPLE32
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SAMPLE32
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SAMPLE32` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema OrderOnlineProcessing
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema OrderOnlineProcessing
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OrderOnlineProcessing` DEFAULT CHARACTER SET utf8 ;
USE `SAMPLE32` ;

-- -----------------------------------------------------
-- Table `SAMPLE32`.`PUBLISHER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`PUBLISHER` (
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(15) NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAMPLE32`.`BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`BOOK` (
  `Book_id` VARCHAR(20) NOT NULL,
  `Title` VARCHAR(45) NOT NULL,
  `PUBLISHER_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Book_id`),
  INDEX `fk_BOOK_PUBLISHER_idx` (`PUBLISHER_Name` ASC),
  CONSTRAINT `fk_BOOK_PUBLISHER`
    FOREIGN KEY (`PUBLISHER_Name`)
    REFERENCES `SAMPLE32`.`PUBLISHER` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAMPLE32`.`BOOK_AUTHORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`BOOK_AUTHORS` (
  `Author_Name` VARCHAR(45) NOT NULL,
  `Book_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Author_Name`, `Book_id`),
  INDEX `fk_BOOK_AUTHORS_BOOK1_idx` (`Book_id` ASC),
  CONSTRAINT `fk_BOOK_AUTHORS_BOOK1`
    FOREIGN KEY (`Book_id`)
    REFERENCES `SAMPLE32`.`BOOK` (`Book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAMPLE32`.`LIBRARY_BRANCH`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`LIBRARY_BRANCH` (
  `Branch_id` INT NOT NULL,
  `Branch_Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Branch_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAMPLE32`.`BOOK_COPIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`BOOK_COPIES` (
  `Book_id` VARCHAR(20) NOT NULL,
  `Branch_id` INT NOT NULL,
  `No_Of_Copies` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`Book_id`, `Branch_id`),
  INDEX `fk_BOOK_COPIES_LIBRARY_BRANCH1_idx` (`Branch_id` ASC),
  CONSTRAINT `fk_BOOK_COPIES_BOOK1`
    FOREIGN KEY (`Book_id`)
    REFERENCES `SAMPLE32`.`BOOK` (`Book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOOK_COPIES_LIBRARY_BRANCH1`
    FOREIGN KEY (`Branch_id`)
    REFERENCES `SAMPLE32`.`LIBRARY_BRANCH` (`Branch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAMPLE32`.`BORROWER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`BORROWER` (
  `Card_No` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(15) NULL,
  PRIMARY KEY (`Card_No`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SAMPLE32`.`BOOK_LOANS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SAMPLE32`.`BOOK_LOANS` (
  `Branch_id` INT NOT NULL,
  `Book_id` VARCHAR(20) NOT NULL,
  `Card_No` INT NOT NULL,
  `Date_Out` DATE NOT NULL,
  `Due_Date` DATE NOT NULL,
  PRIMARY KEY (`Branch_id`, `Book_id`, `Card_No`),
  INDEX `fk_BOOK_LOANS_LIBRARY_BRANCH1_idx` (`Branch_id` ASC),
  INDEX `fk_BOOK_LOANS_BORROWER1_idx` (`Card_No` ASC),
  CONSTRAINT `fk_BOOK_LOANS_BOOK1`
    FOREIGN KEY (`Book_id`)
    REFERENCES `SAMPLE32`.`BOOK` (`Book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOOK_LOANS_LIBRARY_BRANCH1`
    FOREIGN KEY (`Branch_id`)
    REFERENCES `SAMPLE32`.`LIBRARY_BRANCH` (`Branch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOOK_LOANS_BORROWER1`
    FOREIGN KEY (`Card_No`)
    REFERENCES `SAMPLE32`.`BORROWER` (`Card_No`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `OrderOnlineProcessing` ;

-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Publisher` (
  `publisher_name` VARCHAR(100) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`publisher_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Book` (
  `ISBN` INT(10) UNSIGNED NOT NULL,
  `title` VARCHAR(300) NOT NULL,
  `publication_year` DATE NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL DEFAULT '10',
  `threshold` INT(11) NOT NULL DEFAULT '0',
  `copies` INT(11) NOT NULL DEFAULT '0',
  `publisher_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ISBN`),
  INDEX `TITLE_INDEX` (`title` ASC),
  INDEX `fk_Book_Publisher1_idx` (`publisher_name` ASC),
  CONSTRAINT `fk_Book_Publisher1`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `OrderOnlineProcessing`.`Publisher` (`publisher_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'This Table will represent the books available in the store';


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Author` (
  `author_name` VARCHAR(100) NOT NULL,
  `ISBN` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`author_name`, `ISBN`),
  INDEX `fk_Author_Book1_idx` (`ISBN` ASC),
  CONSTRAINT `fk_Author_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'This Table will contain all authors with their books';


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`User` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `passowrd` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `shipping_address` VARCHAR(100) NOT NULL,
  `is_manger` TINYINT(1) NOT NULL DEFAULT '0',
  `email` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`CUNSTOMER_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`CUNSTOMER_ORDER` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `ISBN` INT(10) UNSIGNED NULL DEFAULT NULL,
  `sale_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_user_id_user_idx` (`user_id` ASC),
  INDEX `fk_book_id_book_idx` (`ISBN` ASC),
  CONSTRAINT `fk_book_id_book`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `OrderOnlineProcessing`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Category` (
  `Category_Name` VARCHAR(40) NOT NULL,
  `ISBN` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Category_Name`, `ISBN`),
  INDEX `fk_Category_Book1_idx` (`ISBN` ASC),
  CONSTRAINT `fk_Category_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`ORDER_ITEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`ORDER_ITEM` (
  `ISBN` INT(10) UNSIGNED NOT NULL,
  `quantity` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `order_id` INT(11) NOT NULL,
  PRIMARY KEY (`ISBN`, `order_id`),
  INDEX `fk_ORDER_ITEM_Book1_idx` (`ISBN` ASC),
  INDEX `fk_ORDER_ITEM_CUNSTOMER_ORDER1_idx` (`order_id` ASC),
  CONSTRAINT `fk_ORDER_ITEM_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ORDER_ITEM_CUNSTOMER_ORDER1`
    FOREIGN KEY (`order_id`)
    REFERENCES `OrderOnlineProcessing`.`CUNSTOMER_ORDER` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Order` (
  `order_id` INT(10) UNSIGNED NOT NULL,
  `ISBN` INT(10) UNSIGNED NOT NULL,
  `quantity` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `ISBN`),
  INDEX `fk_book_id_idx` (`ISBN` ASC),
  CONSTRAINT `fk_book_id_order`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`PublisherPhone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`PublisherPhone` (
  `publisher_phone` VARCHAR(45) NOT NULL,
  `publisher_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`publisher_phone`, `publisher_name`),
  INDEX `fk_PublisherPhone_Publisher1_idx` (`publisher_name` ASC),
  CONSTRAINT `fk_PublisherPhone_Publisher1`
    FOREIGN KEY (`publisher_name`)
    REFERENCES `OrderOnlineProcessing`.`Publisher` (`publisher_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `OrderOnlineProcessing`;

DELIMITER $$
USE `OrderOnlineProcessing`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `OrderOnlineProcessing`.`NegativeQuantity`
BEFORE UPDATE ON `OrderOnlineProcessing`.`Book`
FOR EACH ROW
BEGIN

if new.copies < 0 then
	signal sqlstate '45000';	
end if;

END$$

USE `OrderOnlineProcessing`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `OrderOnlineProcessing`.`PassThreshold`
AFTER UPDATE ON `OrderOnlineProcessing`.`Book`
FOR EACH ROW
BEGIN

declare to_order INT;

set to_order = new.copies - new.threshold;

if to_order < 0 then 
	insert into OrderOnlineProcessing.Order values (UUID(), new.ISBN, to_order);
 end if;

END$$

USE `OrderOnlineProcessing`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `OrderOnlineProcessing`.`UpdateQuantity`
BEFORE DELETE ON `OrderOnlineProcessing`.`Order`
FOR EACH ROW
BEGIN

update book set copies = copies + old.quantity where ISBN = old.ISBN;

END$$


DELIMITER ;

