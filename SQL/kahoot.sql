-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 08 déc. 2020 à 07:33
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

DROP TABLE IF EXISTS `Answer`;
CREATE TABLE IF NOT EXISTS `Answer` (
  `ID_answer` int(11) NOT NULL AUTO_INCREMENT,
  `TEXTE_answer` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID_answer`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `answer`
--

INSERT INTO `Answer` (`ID_answer`, `TEXTE_answer`) VALUES
(121, 'Rejoindre le centre'),
(122, 'Mimer une carte'),
(123, 'Tous les perdre'),
(124, 'Compter ses points'),
(125, 'Mastermind'),
(126, 'Uno'),
(127, 'Blokus'),
(128, 'Pictionary'),
(129, 'Monopoly'),
(130, '1000 bornes'),
(131, 'Boggle'),
(132, 'La bonne paye'),
(133, 'Double'),
(134, 'Pour 100 points'),
(135, 'Triple'),
(136, 'Pour 50 points'),
(137, 'Le Mikado'),
(138, 'Le Mandarin'),
(139, 'Le Mahjong'),
(140, 'Le Samouraï'),
(141, 'La reine'),
(142, 'Le roi'),
(143, 'Le cavalier'),
(144, 'Le fou'),
(145, '421'),
(146, 'Craps'),
(147, 'Yahtzee'),
(148, 'Dudo'),
(149, 'Des dés'),
(150, 'Des billes'),
(151, 'Des jetons'),
(152, 'Des cartes'),
(153, '43'),
(154, '52'),
(155, '65'),
(156, '36'),
(157, 'La paire'),
(158, 'La quinte flush'),
(159, 'Le brelan'),
(160, 'Le carré');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `Category`;
CREATE TABLE IF NOT EXISTS `Category` (
  `ID_Category` int(11) NOT NULL AUTO_INCREMENT,
  `TEXTE_Category` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ID_Category`),
  UNIQUE KEY `TEXTE_Category` (`TEXTE_Category`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `Category` (`ID_Category`, `TEXTE_Category`) VALUES
(5, 'Jeux de société');

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

DROP TABLE IF EXISTS `Game`;
CREATE TABLE IF NOT EXISTS `Game` (
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

DROP TABLE IF EXISTS `Player`;
CREATE TABLE IF NOT EXISTS `Player` (
  `ID_Player` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) CHARACTER SET utf8 NOT NULL,
  `password` varchar(20) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`ID_Player`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `player`
--

INSERT INTO `Player` (`ID_Player`, `login`, `password`) VALUES
(0, 'admin', 'admin'),
(3, 'user0', 'test'),
(4, 'user1', 'test'),
(5, 'user2', 'test'),
(7, 'user4', 'test'),
(8, 'user5', 'test'),
(9, 'user6', 'test'),
(10, 'user7', 'test'),
(23, 'd', 'ff'),
(28, 'ffee', 'fffr'),
(29, 'fff', 'fff'),
(34, 'ff', 'sdsdf');

-- --------------------------------------------------------

--
-- Structure de la table `proposition`
--

DROP TABLE IF EXISTS `Proposition`;
CREATE TABLE IF NOT EXISTS `Proposition` (
  `answer_ID_answer` int(11) NOT NULL,
  `QUESTION_ID_QUESTION` int(11) NOT NULL,
  PRIMARY KEY (`answer_ID_answer`,`QUESTION_ID_QUESTION`),
  KEY `fk_PROPOSITION_QUESTION1` (`QUESTION_ID_QUESTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `proposition`
--

INSERT INTO `Proposition` (`answer_ID_answer`, `QUESTION_ID_QUESTION`) VALUES
(121, 31),
(122, 31),
(123, 31),
(124, 31),
(125, 32),
(126, 32),
(127, 32),
(128, 32),
(129, 33),
(130, 33),
(131, 33),
(132, 33),
(133, 34),
(134, 34),
(135, 34),
(136, 34),
(137, 35),
(138, 35),
(139, 35),
(140, 35),
(141, 36),
(142, 36),
(143, 36),
(144, 36),
(145, 37),
(146, 37),
(147, 37),
(148, 37),
(149, 38),
(150, 38),
(151, 38),
(152, 38),
(153, 39),
(154, 39),
(155, 39),
(156, 39),
(157, 40),
(158, 40),
(159, 40),
(160, 40);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `Question`;
CREATE TABLE IF NOT EXISTS `Question` (
  `ID_QUESTION` int(11) NOT NULL AUTO_INCREMENT,
  `TEXTE_QUESTION` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `Category_ID_Category` int(11) NOT NULL,
  `ID_BONNE_answer` int(11) NOT NULL,
  PRIMARY KEY (`ID_QUESTION`,`Category_ID_Category`,`ID_BONNE_answer`),
  KEY `fk_QUESTION_Category1` (`Category_ID_Category`),
  KEY `fk_QUESTION_answer1` (`ID_BONNE_answer`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `Question` (`ID_QUESTION`, `TEXTE_QUESTION`, `Category_ID_Category`, `ID_BONNE_answer`) VALUES
(31, 'Au « Trivial Pursuit », que faut-il faire quand on a obtenu tous les camemberts ?', 5, 121),
(32, 'Quel jeu créé en 1985 par Rob Angel consiste à faire deviner un mot en le dessinant ?', 5, 128),
(33, 'Quel jeu de société consiste à ruiner ses adversaires par des opérations immobilières ?', 5, 129),
(34, 'Au « Scrabble », le joueur qui démarre la partie place un mot sur le plateau qui compte...', 5, 133),
(35, 'Quel jeu d\'adresse, praticable de 2 à 6 joueurs, consiste à se saisir de baguettes une par une ?', 5, 137),
(36, 'Quelle est la pièce la plus mobile aux échecs parmi les seize pièces de chaque joueur ?', 5, 141),
(37, 'Dans quel jeu populaire faut-il reconstituer une seule combinaison gagnante avec trois dés ?', 5, 145),
(38, 'Que faut-il aligner pour remporter une partie de « Puissance 4 », jeu de société dérivé du Morpion ?', 5, 151),
(39, 'De combien de cartes est généralement constitué un jeu de bataille ?', 5, 154),
(40, 'Au poker, parmi les combinaisons possibles, quelle est la suite de cartes la plus puissante ?', 5, 158);

-- --------------------------------------------------------

--
-- Structure de la table `question_game`
--

DROP TABLE IF EXISTS `Question_game`;
CREATE TABLE IF NOT EXISTS `Question_game` (
  `Game_ID_Game` int(11) NOT NULL,
  `QUESTION_ID_QUESTION` int(11) NOT NULL,
  PRIMARY KEY (`Game_ID_Game`,`QUESTION_ID_QUESTION`),
  KEY `fk_QUESTION_Game_QUESTION1` (`QUESTION_ID_QUESTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `score`
--

DROP TABLE IF EXISTS `Score`;
CREATE TABLE IF NOT EXISTS `Score` (
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
ALTER TABLE `Game`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`ID_Category`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `proposition`
--
ALTER TABLE `Proposition`
  ADD CONSTRAINT `fk_PROPOSITION_QUESTION1` FOREIGN KEY (`QUESTION_ID_QUESTION`) REFERENCES `question` (`ID_QUESTION`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_PROPOSITION_answer1` FOREIGN KEY (`answer_ID_answer`) REFERENCES `answer` (`ID_answer`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `question`
--
ALTER TABLE `Question`
  ADD CONSTRAINT `fk_QUESTION_Category1` FOREIGN KEY (`Category_ID_Category`) REFERENCES `category` (`ID_Category`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_QUESTION_answer1` FOREIGN KEY (`ID_BONNE_answer`) REFERENCES `answer` (`ID_answer`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `question_game`
--
ALTER TABLE `Question_game`
  ADD CONSTRAINT `fk_QUESTION_Game_Game1` FOREIGN KEY (`Game_ID_Game`) REFERENCES `game` (`ID_Game`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_QUESTION_Game_QUESTION1` FOREIGN KEY (`QUESTION_ID_QUESTION`) REFERENCES `question` (`ID_QUESTION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `score`
--
ALTER TABLE `Score`
  ADD CONSTRAINT `Fk_game` FOREIGN KEY (`id_game`) REFERENCES `game` (`ID_Game`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_player` FOREIGN KEY (`Id_player`) REFERENCES `player` (`ID_Player`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
