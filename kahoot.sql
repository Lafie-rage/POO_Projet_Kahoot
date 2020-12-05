-- -----------------------------------------------------
-- Schema Kahoot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS Kahoot ;
USE Kahoot ;

-- -----------------------------------------------------
-- Table Kahoot.Player
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.Player (
  ID_Player INT NOT NULL AUTO_INCREMENT,
  NOM_Player VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID_Player))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Kahoot.answer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.answer (
  ID_answer INT NOT NULL AUTO_INCREMENT,
  TEXTE_answer VARCHAR(50) NULL,
  PRIMARY KEY (ID_answer))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Kahoot.Category
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.Category (
  ID_Category INT NOT NULL AUTO_INCREMENT,
  TEXTE_Category VARCHAR(25) NOT NULL,
  PRIMARY KEY (ID_Category))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Kahoot.QUESTION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.QUESTION (
  ID_QUESTION INT NOT NULL AUTO_INCREMENT,
  TEXTE_QUESTION VARCHAR(50) NULL,
  Category_ID_Category INT NOT NULL,
  ID_BONNE_answer INT NOT NULL,
  PRIMARY KEY (ID_QUESTION, Category_ID_Category, ID_BONNE_answer),
  CONSTRAINT fk_QUESTION_Category1
    FOREIGN KEY (Category_ID_Category)
    REFERENCES Kahoot.Category (ID_Category)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_QUESTION_answer1
    FOREIGN KEY (ID_BONNE_answer)
    REFERENCES Kahoot.answer (ID_answer)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Kahoot.PROPOSITION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.PROPOSITION (
  answer_ID_answer INT NOT NULL,
  QUESTION_ID_QUESTION INT NOT NULL,
  PRIMARY KEY (answer_ID_answer, QUESTION_ID_QUESTION),
  CONSTRAINT fk_PROPOSITION_answer1
    FOREIGN KEY (answer_ID_answer)
    REFERENCES Kahoot.answer (ID_answer)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_PROPOSITION_QUESTION1
    FOREIGN KEY (QUESTION_ID_QUESTION)
    REFERENCES Kahoot.QUESTION (ID_QUESTION)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Kahoot.Game
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.Game (
  ID_Game INT NOT NULL AUTO_INCREMENT,
  Date DATE NOT NULL,
  Score INT NOT NULL,
  Player_ID_Player INT NOT NULL,
  PRIMARY KEY (ID_Game, Player_ID_Player),
  CONSTRAINT fk_Game_Player1
    FOREIGN KEY (Player_ID_Player)
    REFERENCES Kahoot.Player (ID_Player)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Kahoot.QUESTION_Game
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Kahoot.QUESTION_Game (
  Game_ID_Game INT NOT NULL,
  QUESTION_ID_QUESTION INT NOT NULL,
  PRIMARY KEY (Game_ID_Game, QUESTION_ID_QUESTION),
  CONSTRAINT fk_QUESTION_Game_Game1
    FOREIGN KEY (Game_ID_Game)
    REFERENCES Kahoot.Game (ID_Game)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_QUESTION_Game_QUESTION1
    FOREIGN KEY (QUESTION_ID_QUESTION)
    REFERENCES Kahoot.QUESTION (ID_QUESTION)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

