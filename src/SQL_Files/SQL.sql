-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema OrderOnlineProcessing
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema OrderOnlineProcessing
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OrderOnlineProcessing` DEFAULT CHARACTER SET utf8 ;
USE `OrderOnlineProcessing` ;

-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Publisher` (
  `publisher_name` VARCHAR(100) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`publisher_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Book` (
  `ISBN` INT UNSIGNED NOT NULL,
  `title` VARCHAR(300) NOT NULL,
  `publication_year` DATE NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL DEFAULT 10,
  `threshold` INT NOT NULL DEFAULT 0,
  `copies` INT NOT NULL DEFAULT 0,
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
COMMENT = 'This Table will represent the books available in the store';


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Author` (
  `author_name` VARCHAR(100) NOT NULL,
  `ISBN` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`author_name`, `ISBN`),
  INDEX `fk_Author_Book1_idx` (`ISBN` ASC),
  CONSTRAINT `fk_Author_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This Table will contain all authors with their books';


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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Order` (
  `order_id` INT UNSIGNED NOT NULL,
  `ISBN` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `ISBN`),
  INDEX `fk_book_id_idx` (`ISBN` ASC),
  CONSTRAINT `fk_book_id_order`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`Category` (
  `Category_Name` VARCHAR(40) NOT NULL,
  `ISBN` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Category_Name`, `ISBN`),
  INDEX `fk_Category_Book1_idx` (`ISBN` ASC),
  CONSTRAINT `fk_Category_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`User` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `passowrd` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `shipping_address` VARCHAR(100) NOT NULL,
  `is_manger` TINYINT(1) NOT NULL DEFAULT 0,
  `email` VARCHAR(100) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`CUNSTOMER_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`CUNSTOMER_ORDER` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `ISBN` INT UNSIGNED NULL,
  `sale_date` DATE NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_user_id_user_idx` (`user_id` ASC),
  INDEX `fk_book_id_book_idx` (`ISBN` ASC),
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `OrderOnlineProcessing`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_id_book`
    FOREIGN KEY (`ISBN`)
    REFERENCES `OrderOnlineProcessing`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderOnlineProcessing`.`ORDER_ITEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `OrderOnlineProcessing`.`ORDER_ITEM` (
  `ISBN` INT UNSIGNED NOT NULL,
  `quantity` INT UNSIGNED NOT NULL DEFAULT 0,
  `order_id` INT NOT NULL,
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
ENGINE = InnoDB;

USE `OrderOnlineProcessing`;

DELIMITER $$

CREATE
	TRIGGER `afterInsert` AFTER insert 
	ON `Book` 
	FOR EACH ROW BEGIN
    declare to_order INT;
    set to_order = new.copies - new.threshold;
	
		IF to_order < 0 THEN
            if( exists ( select * from OrderOnlineProcessing.Order where ISBN = new.ISBN)) then
				update OrderOnlineProcessing.Order set quantity = quantity + to_order;
               
			else
            INSERT INTO OrderOnlineProcessing.Order VALUES (new.ISBN,to_order);
             end if;
		
		END IF;
    
    END$$
DELIMITER ;

DELIMITER $$
USE `OrderOnlineProcessing`$$
CREATE DEFINER = CURRENT_USER TRIGGER `OrderOnlineProcessing`.`NegativeQuantity` BEFORE UPDATE ON `Book` FOR EACH ROW
BEGIN

if new.copies < 0 then
	signal sqlstate '45000';	
end if;

END$$

USE `OrderOnlineProcessing`$$
CREATE DEFINER = CURRENT_USER TRIGGER `OrderOnlineProcessing`.`PassThreshold` AFTER UPDATE ON `Book` FOR EACH ROW
BEGIN

declare to_order INT;

set to_order = new.copies - new.threshold;

if to_order < 0 then 
 if( exists ( select * from OrderOnlineProcessing.Order where ISBN = new.ISBN)) then
				update OrderOnlineProcessing.Order set quantity = quantity + to_order;
                
			else
	insert into OrderOnlineProcessing.Order values (UUID(), new.ISBN, to_order);
            end if;
 end if;

END$$

USE `OrderOnlineProcessing`$$
CREATE DEFINER = CURRENT_USER TRIGGER `OrderOnlineProcessing`.`UpdateQuantity` BEFORE DELETE ON `Order` FOR EACH ROW
BEGIN

update book set copies = copies + old.quantity where ISBN = old.ISBN;

END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
