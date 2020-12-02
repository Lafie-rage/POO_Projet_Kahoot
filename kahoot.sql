-- -----------------------------------------------------
-- Schema Kahoot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS 'Kahoot' ;
USE 'Kahoot' ;

-- -----------------------------------------------------
-- Table 'Kahoot'.'Joueur'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'Joueur' (
  'ID_JOUEUR' INT NOT NULL AUTO_INCREMENT,
  'NOM_JOUEUR' VARCHAR(20) NOT NULL,
  PRIMARY KEY ('ID_JOUEUR'))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'Kahoot'.'REPONSE'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'REPONSE' (
  'ID_REPONSE' INT NOT NULL AUTO_INCREMENT,
  'TEXTE_REPONSE' VARCHAR(50) NULL,
  PRIMARY KEY ('ID_REPONSE'))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'Kahoot'.'CATEGORIE'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'CATEGORIE' (
  'ID_CATEGORIE' INT NOT NULL AUTO_INCREMENT,
  'TEXTE_CATEGORIE' VARCHAR(25) NOT NULL,
  PRIMARY KEY ('ID_CATEGORIE'))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'Kahoot'.'QUESTION'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'QUESTION' (
  'ID_QUESTION' INT NOT NULL AUTO_INCREMENT,
  'TEXTE_QUESTION' VARCHAR(50) NULL,
  'CATEGORIE_ID_CATEGORIE' INT NOT NULL,
  'ID_BONNE_REPONSE' INT NOT NULL,
  PRIMARY KEY ('ID_QUESTION', 'CATEGORIE_ID_CATEGORIE', 'ID_BONNE_REPONSE'),
  CONSTRAINT 'fk_QUESTION_CATEGORIE1'
    FOREIGN KEY ('CATEGORIE_ID_CATEGORIE')
    REFERENCES 'Kahoot'.'CATEGORIE' ('ID_CATEGORIE')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_QUESTION_Reponse1'
    FOREIGN KEY ('ID_BONNE_REPONSE')
    REFERENCES 'Kahoot'.'REPONSE' ('ID_REPONSE')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'Kahoot'.'PROPOSITION'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'PROPOSITION' (
  'Reponse_ID_REPONSE' INT NOT NULL,
  'QUESTION_ID_QUESTION' INT NOT NULL,
  PRIMARY KEY ('Reponse_ID_REPONSE', 'QUESTION_ID_QUESTION'),
  CONSTRAINT 'fk_PROPOSITION_Reponse1'
    FOREIGN KEY ('Reponse_ID_REPONSE')
    REFERENCES 'Kahoot'.'REPONSE' ('ID_REPONSE')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_PROPOSITION_QUESTION1'
    FOREIGN KEY ('QUESTION_ID_QUESTION')
    REFERENCES 'Kahoot'.'QUESTION' ('ID_QUESTION')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'Kahoot'.'PARTIE'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'PARTIE' (
  'ID_PARTIE' INT NOT NULL AUTO_INCREMENT,
  'Date' DATE NOT NULL,
  'Score' INT NOT NULL,
  'Joueur_ID_JOUEUR' INT NOT NULL,
  PRIMARY KEY ('ID_PARTIE', 'Joueur_ID_JOUEUR'),
  CONSTRAINT 'fk_PARTIE_Joueur1'
    FOREIGN KEY ('Joueur_ID_JOUEUR')
    REFERENCES 'Kahoot'.'Joueur' ('ID_JOUEUR')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table 'Kahoot'.'QUESTION_PARTIE'
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS 'Kahoot'.'QUESTION_PARTIE' (
  'PARTIE_ID_PARTIE' INT NOT NULL,
  'QUESTION_ID_QUESTION' INT NOT NULL,
  PRIMARY KEY ('PARTIE_ID_PARTIE', 'QUESTION_ID_QUESTION'),
  CONSTRAINT 'fk_QUESTION_PARTIE_PARTIE1'
    FOREIGN KEY ('PARTIE_ID_PARTIE')
    REFERENCES 'Kahoot'.'PARTIE' ('ID_PARTIE')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT 'fk_QUESTION_PARTIE_QUESTION1'
    FOREIGN KEY ('QUESTION_ID_QUESTION')
    REFERENCES 'Kahoot'.'QUESTION' ('ID_QUESTION')
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

