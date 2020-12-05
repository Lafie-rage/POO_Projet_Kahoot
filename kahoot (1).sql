-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 05 déc. 2020 à 17:13
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `kahoot`
--

-- --------------------------------------------------------

--
-- Structure de la table `answer`
--

DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `ID_answer` int(11) NOT NULL AUTO_INCREMENT,
  `TEXTE_answer` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID_answer`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `ID_Category` int(11) NOT NULL AUTO_INCREMENT,
  `TEXTE_Category` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ID_Category`),
  UNIQUE KEY `TEXTE_Category` (`TEXTE_Category`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
  `ID_Game` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`ID_Game`),
  KEY `fk_category` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `ID_Player` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`ID_Player`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `proposition`
--

DROP TABLE IF EXISTS `proposition`;
CREATE TABLE IF NOT EXISTS `proposition` (
  `answer_ID_answer` int(11) NOT NULL,
  `QUESTION_ID_QUESTION` int(11) NOT NULL,
  PRIMARY KEY (`answer_ID_answer`,`QUESTION_ID_QUESTION`),
  KEY `fk_PROPOSITION_QUESTION1` (`QUESTION_ID_QUESTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `ID_QUESTION` int(11) NOT NULL AUTO_INCREMENT,
  `TEXTE_QUESTION` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `Category_ID_Category` int(11) NOT NULL,
  `ID_BONNE_answer` int(11) NOT NULL,
  PRIMARY KEY (`ID_QUESTION`,`Category_ID_Category`,`ID_BONNE_answer`),
  KEY `fk_QUESTION_Category1` (`Category_ID_Category`),
  KEY `fk_QUESTION_answer1` (`ID_BONNE_answer`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `question_game`
--

DROP TABLE IF EXISTS `question_game`;
CREATE TABLE IF NOT EXISTS `question_game` (
  `Game_ID_Game` int(11) NOT NULL,
  `QUESTION_ID_QUESTION` int(11) NOT NULL,
  PRIMARY KEY (`Game_ID_Game`,`QUESTION_ID_QUESTION`),
  KEY `fk_QUESTION_Game_QUESTION1` (`QUESTION_ID_QUESTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `score`
--

DROP TABLE IF EXISTS `score`;
CREATE TABLE IF NOT EXISTS `score` (
  `Id_player` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `Score` int(11) NOT NULL,
  PRIMARY KEY (`Id_player`,`id_game`),
  KEY `Fk_game` (`id_game`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`ID_Category`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `proposition`
--
ALTER TABLE `proposition`
  ADD CONSTRAINT `fk_PROPOSITION_QUESTION1` FOREIGN KEY (`QUESTION_ID_QUESTION`) REFERENCES `question` (`ID_QUESTION`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_PROPOSITION_answer1` FOREIGN KEY (`answer_ID_answer`) REFERENCES `answer` (`ID_answer`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_QUESTION_Category1` FOREIGN KEY (`Category_ID_Category`) REFERENCES `category` (`ID_Category`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_QUESTION_answer1` FOREIGN KEY (`ID_BONNE_answer`) REFERENCES `answer` (`ID_answer`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `question_game`
--
ALTER TABLE `question_game`
  ADD CONSTRAINT `fk_QUESTION_Game_Game1` FOREIGN KEY (`Game_ID_Game`) REFERENCES `game` (`ID_Game`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_QUESTION_Game_QUESTION1` FOREIGN KEY (`QUESTION_ID_QUESTION`) REFERENCES `question` (`ID_QUESTION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `Fk_game` FOREIGN KEY (`id_game`) REFERENCES `game` (`ID_Game`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_player` FOREIGN KEY (`Id_player`) REFERENCES `player` (`ID_Player`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
