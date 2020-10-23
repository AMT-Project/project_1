-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema AMT-db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema AMT-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AMT-db` ;
USE `AMT-db` ;

-- -----------------------------------------------------
-- Table `AMT-db`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AMT-db`.`Person` (
  `uuid` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AMT-db`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AMT-db`.`Question` (
  `uuid` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `person_uuid` VARCHAR(255) NOT NULL,
  `created_on` DATETIME NOT NULL,
  PRIMARY KEY (`uuid`),
  INDEX `fk_Question_Person_idx` (`person_uuid` ASC) VISIBLE,
  CONSTRAINT `fk_Question_Person`
    FOREIGN KEY (`person_uuid`)
    REFERENCES `AMT-db`.`Person` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AMT-db`.`Answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AMT-db`.`Answer` (
  `uuid` VARCHAR(255) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `question_uuid` VARCHAR(255) NOT NULL,
  `person_uuid` VARCHAR(255) NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`uuid`),
  INDEX `fk_Answer_Question1_idx` (`question_uuid` ASC) VISIBLE,
  INDEX `fk_Answer_Person1_idx` (`person_uuid` ASC) VISIBLE,
  CONSTRAINT `fk_Answer_Question1`
    FOREIGN KEY (`question_uuid`)
    REFERENCES `AMT-db`.`Question` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answer_Person1`
    FOREIGN KEY (`person_uuid`)
    REFERENCES `AMT-db`.`Person` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AMT-db`.`Vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AMT-db`.`Vote` (
  `uuid` VARCHAR(255) NOT NULL,
  `is_upvote` TINYINT NOT NULL,
  `answer_uuid` VARCHAR(255) NOT NULL,
  `question_uuid` VARCHAR(255) NOT NULL,
  `person_uuid` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  INDEX `fk_Vote_Answer1_idx` (`answer_uuid` ASC) VISIBLE,
  INDEX `fk_Vote_Question1_idx` (`question_uuid` ASC) VISIBLE,
  INDEX `fk_Vote_Person1_idx` (`person_uuid` ASC) VISIBLE,
  CONSTRAINT `fk_Vote_Answer1`
    FOREIGN KEY (`answer_uuid`)
    REFERENCES `AMT-db`.`Answer` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vote_Question1`
    FOREIGN KEY (`question_uuid`)
    REFERENCES `AMT-db`.`Question` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vote_Person1`
    FOREIGN KEY (`person_uuid`)
    REFERENCES `AMT-db`.`Person` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AMT-db`.`Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AMT-db`.`Comments` (
  `uuid` VARCHAR(255) NOT NULL,
  `person_uuid` VARCHAR(255) NOT NULL,
  `question_uuid` VARCHAR(255),
  `answer_uuid` VARCHAR(255),
  `created_at` DATETIME NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  INDEX `fk_Comments_Person1_idx` (`person_uuid` ASC) VISIBLE,
  INDEX `fk_Comments_Question1_idx` (`question_uuid` ASC) VISIBLE,
  INDEX `fk_Comments_Answer1_idx` (`answer_uuid` ASC) VISIBLE,
  CONSTRAINT `fk_Comments_Person1`
    FOREIGN KEY (`person_uuid`)
    REFERENCES `AMT-db`.`Person` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_Question1`
    FOREIGN KEY (`question_uuid`)
    REFERENCES `AMT-db`.`Question` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_Answer1`
    FOREIGN KEY (`answer_uuid`)
    REFERENCES `AMT-db`.`Answer` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Person (uuid, username, email, firstname, lastname, password) VALUES ("c53ed5d3-1efd-409f-a139-8d3bdef43c96","test","test@user.com","test","user","AAA");
INSERT INTO Question (uuid, title, description, person_uuid, created_on) VALUES ("5b078997-1882-4119-aa52-2cdb82232886","Comment fonctionne la vie?","Lorem ipsum dolor si amet","c53ed5d3-1efd-409f-a139-8d3bdef43c96","2020-10-22 00:00:00");
INSERT INTO Answer (uuid, content, question_uuid, person_uuid, created_at) VALUES ("a414304c-4428-496e-a0ec-20fbdcb0da80","comment?","5b078997-1882-4119-aa52-2cdb82232886","c53ed5d3-1efd-409f-a139-8d3bdef43c96","2020-10-22 00:00:00");
