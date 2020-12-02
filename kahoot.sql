-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema kahoot
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kahoot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kahoot` DEFAULT CHARACTER SET utf8 ;
USE `kahoot` ;

-- -----------------------------------------------------
-- Table `kahoot`.`categorie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`categorie` (
  `idCATEGORIE` INT NOT NULL AUTO_INCREMENT,
  `texteCATEGORIE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCATEGORIE`),
  UNIQUE INDEX `texteCATEGORIE_UNIQUE` (`texteCATEGORIE` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kahoot`.`joueur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`joueur` (
  `idJOUEUR` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idJOUEUR`),
  UNIQUE INDEX `UC_LOGIN` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kahoot`.`partie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`partie` (
  `ID_PARTIE` INT NOT NULL AUTO_INCREMENT,
  `DEBUT` DATETIME NULL DEFAULT NULL,
  `categorie_idCATEGORIE` INT NOT NULL,
  PRIMARY KEY (`ID_PARTIE`, `categorie_idCATEGORIE`),
  INDEX `fk_partie_categorie1_idx` (`categorie_idCATEGORIE` ASC) VISIBLE,
  CONSTRAINT `fk_partie_categorie1`
    FOREIGN KEY (`categorie_idCATEGORIE`)
    REFERENCES `kahoot`.`categorie` (`idCATEGORIE`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kahoot`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`question` (
  `ID_QUESTION` INT NOT NULL AUTO_INCREMENT,
  `ID_BONNE_REPONSE` INT NOT NULL,
  `ID_CATEGORIE` INT NOT NULL,
  `textequestion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_QUESTION`),
  INDEX `fk_QUESTION_REPONSE1_idx` (`ID_BONNE_REPONSE` ASC) VISIBLE,
  INDEX `fk_QUESTION_CATEGORIE1_idx` (`ID_CATEGORIE` ASC) VISIBLE,
  CONSTRAINT `fk_QUESTION_CATEGORIE1`
    FOREIGN KEY (`ID_CATEGORIE`)
    REFERENCES `kahoot`.`categorie` (`idCATEGORIE`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 89
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kahoot`.`questions_partie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`questions_partie` (
  `question_ID_QUESTION` INT NOT NULL,
  `partie_ID_PARTIE` INT NOT NULL,
  PRIMARY KEY (`question_ID_QUESTION`, `partie_ID_PARTIE`),
  INDEX `fk_question_has_partie_partie1_idx` (`partie_ID_PARTIE` ASC) VISIBLE,
  CONSTRAINT `fk_question_has_partie_question1`
    FOREIGN KEY (`question_ID_QUESTION`)
    REFERENCES `kahoot`.`question` (`ID_QUESTION`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_question_has_partie_partie1`
    FOREIGN KEY (`partie_ID_PARTIE`)
    REFERENCES `kahoot`.`partie` (`ID_PARTIE`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kahoot`.`reponse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`reponse` (
  `ID_Reponse` INT NOT NULL AUTO_INCREMENT,
  `texteREPONSE` VARCHAR(45) NULL DEFAULT NULL,
  `question_ID_QUESTION` INT NOT NULL,
  PRIMARY KEY (`ID_Reponse`, `question_ID_QUESTION`),
  INDEX `fk_reponse_question1_idx` (`question_ID_QUESTION` ASC) VISIBLE,
  CONSTRAINT `fk_reponse_question1`
    FOREIGN KEY (`question_ID_QUESTION`)
    REFERENCES `kahoot`.`question` (`ID_QUESTION`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 337
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `kahoot`.`score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kahoot`.`score` (
  `joueur_idJOUEUR` INT NOT NULL,
  `partie_ID_PARTIE` INT NOT NULL,
  `Score` INT NULL DEFAULT 0,
  PRIMARY KEY (`joueur_idJOUEUR`, `partie_ID_PARTIE`),
  INDEX `fk_Score_partie1_idx` (`partie_ID_PARTIE` ASC) VISIBLE,
  CONSTRAINT `fk_Score_joueur1`
    FOREIGN KEY (`joueur_idJOUEUR`)
    REFERENCES `kahoot`.`joueur` (`idJOUEUR`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Score_partie1`
    FOREIGN KEY (`partie_ID_PARTIE`)
    REFERENCES `kahoot`.`partie` (`ID_PARTIE`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
