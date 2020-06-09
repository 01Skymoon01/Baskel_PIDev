-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 09 juin 2020 à 00:50
-- Version du serveur :  5.7.28
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `baskel2`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `ref_c` int(11) NOT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ref_c`),
  KEY `libelle` (`libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`ref_c`, `libelle`) VALUES
(584346, '584346'),
(256256, 'koko'),
(148965, 'Vélo'),
(587744, 'Vêtements');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(3, 'Sante');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `TotalPrix` double NOT NULL,
  `nbrProduit` int(11) NOT NULL,
  `etat` int(11) NOT NULL,
  `etat_liv` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `id_client`, `date`, `TotalPrix`, `nbrProduit`, `etat`, `etat_liv`) VALUES
(102, 20, '2020-05-12 22:59:46', 25, 1, 0, 2),
(103, 20, '2020-05-13 04:21:34', 48, 2, 0, 1),
(104, 20, '2020-05-13 10:47:44', 48, 2, 0, 0),
(107, 20, '2020-05-18 05:33:40', 25, 1, 0, 2),
(108, 20, '2020-05-18 06:40:47', 77, 2, 1, 2),
(109, 15, '2020-06-02 00:00:00', 48, 2, 0, NULL),
(110, 15, '2020-06-09 00:00:00', 48, 2, 0, NULL),
(111, 15, '2020-06-09 00:00:00', 48, 2, 1, NULL),
(113, 15, '2020-06-09 00:00:00', 163, 2, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

DROP TABLE IF EXISTS `contrat`;
CREATE TABLE IF NOT EXISTS `contrat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_event` int(11) DEFAULT NULL,
  `id_partenaire` int(11) DEFAULT NULL,
  `Pack` double DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_60349993D52B4B97` (`id_event`),
  KEY `IDX_60349993977523A4` (`id_partenaire`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `contrat`
--

INSERT INTO `contrat` (`id`, `id_event`, `id_partenaire`, `Pack`, `description`) VALUES
(1, 2, 1, NULL, 'kljlk'),
(2, 3, 2, NULL, 'aaa'),
(3, 2, 3, 3200, 'nour'),
(4, 6, 2, 12, 'nour');

-- --------------------------------------------------------

--
-- Structure de la table `details_commande`
--

DROP TABLE IF EXISTS `details_commande`;
CREATE TABLE IF NOT EXISTS `details_commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomProduit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idProduit` int(11) NOT NULL,
  `qteProduit` int(11) NOT NULL,
  `PrixPrduit` double NOT NULL,
  `idCommande` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_4BCD5F63D498C26` (`idCommande`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `details_commande`
--

INSERT INTO `details_commande` (`id`, `nomProduit`, `idProduit`, `qteProduit`, `PrixPrduit`, `idCommande`) VALUES
(115, 'basklea', 265268, 1, 25, 103),
(116, 'veloo', 266266, 1, 23, 103),
(117, 'basklea', 265268, 1, 25, 104),
(118, 'veloo', 266266, 1, 23, 104),
(121, 'basklea', 265268, 1, 25, 107),
(122, 'basklea', 265268, 1, 25, 108),
(123, 'velo', 299299, 1, 52, 108),
(124, 'veloo', 266266, 5, 23, 109),
(125, 'basklea', 265268, 1, 25, 109),
(126, 'veloo', 266266, 3, 23, 110),
(127, 'basklea', 265268, 1, 25, 110),
(128, 'veloo', 266266, 2, 23, 111),
(129, 'basklea', 265268, 1, 25, 111),
(132, 'veloo', 266266, 3, 138, 113),
(133, 'basklea', 265268, 1, 25, 113);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `Nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nb_participants` int(11) DEFAULT NULL,
  `responsable` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `whyattend` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `Date`, `Nom`, `Description`, `nb_participants`, `responsable`, `whyattend`, `image`) VALUES
(2, '2020-02-23', 'test2', 'mmm', 10, 'gonklovox@gmail.com', 'its great', '7ec191e10f790863289c5b2483eca94d.jpeg'),
(3, '2022-04-30', 'test', 'mmmbb', 51, 'gonklovox@gmail.com', 'its great', 'bc0ed5ad46e7eb7a9bbc1f6eb7d2524a.jpeg'),
(5, '2022-04-23', 'nizar', 'nizare', 25, 'nour.khedher@esprit.tn', 'nour', 'on hold'),
(6, '2021-04-17', 'nour', 'nour', 25, 'nour.khedher@esprit.tn', 'nour', 'on hold'),
(7, '2021-05-15', 'nizar', 'nizar', 25, 'nour.khedher@esprit.tn', 'nour', 'on hold');

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
CREATE TABLE IF NOT EXISTS `livraison` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateLivraison` datetime DEFAULT NULL,
  `codeConf` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idLivreur` int(11) DEFAULT NULL,
  `idCommande` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_A60C9F1FFBC3259F` (`idLivreur`),
  KEY `IDX_A60C9F1F3D498C26` (`idCommande`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`id`, `dateLivraison`, `codeConf`, `idLivreur`, `idCommande`) VALUES
(9, '2020-05-17 04:39:47', 'J9Q9I', 9, 102),
(10, '2020-05-17 04:45:26', 'OLT9G', 9, 102),
(11, '2020-05-17 04:50:42', '9BRFH', 9, 102),
(12, '2020-05-18 05:37:52', 'I39NF', 9, 107),
(13, '2020-05-18 06:43:27', 'K520R', 9, 108);

-- --------------------------------------------------------

--
-- Structure de la table `livreur`
--

DROP TABLE IF EXISTS `livreur`;
CREATE TABLE IF NOT EXISTS `livreur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateNaiss` date NOT NULL,
  `solde` int(11) NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_username` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_EB7A4E6DA885EAE2` (`id_username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `livreur`
--

INSERT INTO `livreur` (`id`, `nom`, `prenom`, `dateNaiss`, `solde`, `etat`, `id_username`) VALUES
(9, 'nour', 'nour', '2020-05-06', 2000, 'en attente', 9);

-- --------------------------------------------------------

--
-- Structure de la table `mail`
--

DROP TABLE IF EXISTS `mail`;
CREATE TABLE IF NOT EXISTS `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `objet` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `migration_versions`
--

DROP TABLE IF EXISTS `migration_versions`;
CREATE TABLE IF NOT EXISTS `migration_versions` (
  `version` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `migration_versions`
--

INSERT INTO `migration_versions` (`version`) VALUES
('20200218213050'),
('20200218213846'),
('20200218215314');

-- --------------------------------------------------------

--
-- Structure de la table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route_parameters` longtext COLLATE utf8_unicode_ci COMMENT '(DC2Type:array)',
  `notification_date` datetime NOT NULL,
  `seen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notifications`
--

INSERT INTO `notifications` (`id`, `title`, `description`, `icon`, `route`, `route_parameters`, `notification_date`, `seen`) VALUES
(1, 'nour', 'bonjour', NULL, 'forum_view_single', 'a:1:{s:2:\"id\";O:29:\"forumBundle\\Entity\\Postsforum\":8:{s:33:\"\0forumBundle\\Entity\\Postsforum\0id\";i:2;s:38:\"\0forumBundle\\Entity\\Postsforum\0subject\";s:7:\"bonjour\";s:42:\"\0forumBundle\\Entity\\Postsforum\0description\";s:13:\"mlkamlakmalka\";s:41:\"\0forumBundle\\Entity\\Postsforum\0posteddate\";O:8:\"DateTime\":3:{s:4:\"date\";s:26:\"2020-02-26 09:59:35.000000\";s:13:\"timezone_type\";i:3;s:8:\"timezone\";s:3:\"UTC\";}s:37:\"\0forumBundle\\Entity\\Postsforum\0iduser\";C:21:\"AppBundle\\Entity\\User\":198:{a:8:{i:0;s:60:\"$2y$13$GrFiPpxIfR4xI6dv2NYzL.ogihFG.vttiD83A3U.IxdgaLj7hdihe\";i:1;N;i:2;s:4:\"nour\";i:3;s:4:\"nour\";i:4;b:1;i:5;i:9;i:6;s:22:\"nour.khedher@esprit.tn\";i:7;s:22:\"nour.khedher@esprit.tn\";}}s:41:\"\0forumBundle\\Entity\\Postsforum\0categoryid\";O:42:\"Proxies\\__CG__\\forumBundle\\Entity\\Category\":3:{s:17:\"__isInitialized__\";b:0;s:31:\"\0forumBundle\\Entity\\Category\0id\";i:3;s:33:\"\0forumBundle\\Entity\\Category\0name\";N;}s:39:\"\0forumBundle\\Entity\\Postsforum\0comments\";O:33:\"Doctrine\\ORM\\PersistentCollection\":2:{s:13:\"\0*\0collection\";O:43:\"Doctrine\\Common\\Collections\\ArrayCollection\":1:{s:53:\"\0Doctrine\\Common\\Collections\\ArrayCollection\0elements\";a:0:{}}s:14:\"\0*\0initialized\";b:0;}s:36:\"\0forumBundle\\Entity\\Postsforum\0views\";i:2;}}', '2020-02-26 10:16:40', 0),
(2, 'nour', 'bonjour', NULL, 'forum_view_single', 'a:1:{s:2:\"id\";O:29:\"forumBundle\\Entity\\Postsforum\":8:{s:33:\"\0forumBundle\\Entity\\Postsforum\0id\";i:2;s:38:\"\0forumBundle\\Entity\\Postsforum\0subject\";s:7:\"bonjour\";s:42:\"\0forumBundle\\Entity\\Postsforum\0description\";s:13:\"mlkamlakmalka\";s:41:\"\0forumBundle\\Entity\\Postsforum\0posteddate\";O:8:\"DateTime\":3:{s:4:\"date\";s:26:\"2020-02-26 09:59:35.000000\";s:13:\"timezone_type\";i:3;s:8:\"timezone\";s:3:\"UTC\";}s:37:\"\0forumBundle\\Entity\\Postsforum\0iduser\";C:21:\"AppBundle\\Entity\\User\":198:{a:8:{i:0;s:60:\"$2y$13$GrFiPpxIfR4xI6dv2NYzL.ogihFG.vttiD83A3U.IxdgaLj7hdihe\";i:1;N;i:2;s:4:\"nour\";i:3;s:4:\"nour\";i:4;b:1;i:5;i:9;i:6;s:22:\"nour.khedher@esprit.tn\";i:7;s:22:\"nour.khedher@esprit.tn\";}}s:41:\"\0forumBundle\\Entity\\Postsforum\0categoryid\";O:42:\"Proxies\\__CG__\\forumBundle\\Entity\\Category\":3:{s:17:\"__isInitialized__\";b:0;s:31:\"\0forumBundle\\Entity\\Category\0id\";i:3;s:33:\"\0forumBundle\\Entity\\Category\0name\";N;}s:39:\"\0forumBundle\\Entity\\Postsforum\0comments\";O:33:\"Doctrine\\ORM\\PersistentCollection\":2:{s:13:\"\0*\0collection\";O:43:\"Doctrine\\Common\\Collections\\ArrayCollection\":1:{s:53:\"\0Doctrine\\Common\\Collections\\ArrayCollection\0elements\";a:0:{}}s:14:\"\0*\0initialized\";b:0;}s:36:\"\0forumBundle\\Entity\\Postsforum\0views\";i:4;}}', '2020-02-26 10:17:52', 0),
(3, 'nour', 'bonjour', NULL, 'forum_view_single', 'a:1:{s:2:\"id\";O:29:\"forumBundle\\Entity\\Postsforum\":8:{s:33:\"\0forumBundle\\Entity\\Postsforum\0id\";i:2;s:38:\"\0forumBundle\\Entity\\Postsforum\0subject\";s:7:\"bonjour\";s:42:\"\0forumBundle\\Entity\\Postsforum\0description\";s:13:\"mlkamlakmalka\";s:41:\"\0forumBundle\\Entity\\Postsforum\0posteddate\";O:8:\"DateTime\":3:{s:4:\"date\";s:26:\"2020-02-26 09:59:35.000000\";s:13:\"timezone_type\";i:3;s:8:\"timezone\";s:3:\"UTC\";}s:37:\"\0forumBundle\\Entity\\Postsforum\0iduser\";C:21:\"AppBundle\\Entity\\User\":198:{a:8:{i:0;s:60:\"$2y$13$GrFiPpxIfR4xI6dv2NYzL.ogihFG.vttiD83A3U.IxdgaLj7hdihe\";i:1;N;i:2;s:4:\"nour\";i:3;s:4:\"nour\";i:4;b:1;i:5;i:9;i:6;s:22:\"nour.khedher@esprit.tn\";i:7;s:22:\"nour.khedher@esprit.tn\";}}s:41:\"\0forumBundle\\Entity\\Postsforum\0categoryid\";O:42:\"Proxies\\__CG__\\forumBundle\\Entity\\Category\":3:{s:17:\"__isInitialized__\";b:0;s:31:\"\0forumBundle\\Entity\\Category\0id\";i:3;s:33:\"\0forumBundle\\Entity\\Category\0name\";N;}s:39:\"\0forumBundle\\Entity\\Postsforum\0comments\";O:33:\"Doctrine\\ORM\\PersistentCollection\":2:{s:13:\"\0*\0collection\";O:43:\"Doctrine\\Common\\Collections\\ArrayCollection\":1:{s:53:\"\0Doctrine\\Common\\Collections\\ArrayCollection\0elements\";a:0:{}}s:14:\"\0*\0initialized\";b:0;}s:36:\"\0forumBundle\\Entity\\Postsforum\0views\";i:6;}}', '2020-02-26 10:21:30', 0);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `nom_prod` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `prix_prod` double NOT NULL,
  `qte_prod` int(11) NOT NULL,
  `image_prod` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `refP` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id`, `id_client`, `nom_prod`, `prix_prod`, `qte_prod`, `image_prod`, `refP`) VALUES
(74, 15, 'veloo', 138, 3, 'f_5e98b359f3dec.jpg', 266266),
(68, 5, 'basklea', 25, 4, 'f_5e98b3a81e9d0.jpg', 265268),
(70, 5, 'veloo', 23, 1, 'f_5e98b359f3dec.jpg', 266266),
(72, 5, 'bask', 26, 3, 'f_5e98bcbd1aae9.jpg', 666666),
(73, 15, 'basklea', 25, 1, 'f_5e98b3a81e9d0.jpg', 265268);

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
--

DROP TABLE IF EXISTS `partenaire`;
CREATE TABLE IF NOT EXISTS `partenaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `representant` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `partenaire`
--

INSERT INTO `partenaire` (`id`, `nom`, `description`, `type`, `representant`) VALUES
(1, 'ooredo', 'tres bien', 'gold', 'nour khedher'),
(2, 'mytech', 'tres bien', 'silver', 'zeineb sfaxi'),
(3, 'BCT', 'bien', 'Gold', 'nour khedher');

-- --------------------------------------------------------

--
-- Structure de la table `postscomments`
--

DROP TABLE IF EXISTS `postscomments`;
CREATE TABLE IF NOT EXISTS `postscomments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` text COLLATE utf8_unicode_ci NOT NULL,
  `postedOn` datetime NOT NULL,
  `idPost` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPost` (`idPost`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `postscomments`
--

INSERT INTO `postscomments` (`id`, `commentaire`, `postedOn`, `idPost`, `idUser`) VALUES
(1, 'hi', '2020-02-26 10:16:40', 2, 9),
(2, 'non', '2020-02-26 10:17:52', 2, 9),
(3, 'salut', '2020-02-26 10:21:30', 2, 9);

-- --------------------------------------------------------

--
-- Structure de la table `postsforum`
--

DROP TABLE IF EXISTS `postsforum`;
CREATE TABLE IF NOT EXISTS `postsforum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `postedDate` datetime NOT NULL,
  `views` int(11) NOT NULL DEFAULT '0',
  `idUser` int(11) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  KEY `categoryId` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `postsforum`
--

INSERT INTO `postsforum` (`id`, `subject`, `description`, `postedDate`, `views`, `idUser`, `categoryId`) VALUES
(1, 'lol', 'nice ', '2020-02-26 04:56:01', 5, 9, 3),
(2, 'bonjour', 'mlkamlakmalka', '2020-02-26 09:59:35', 8, 9, 3);

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `ref_p` int(11) NOT NULL,
  `ref_c` int(11) NOT NULL,
  `nom_p` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `genre_p` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `couleur_p` tinytext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:json_array)',
  `quantite_p` int(11) NOT NULL,
  `prix_p` double NOT NULL,
  `marque_p` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` tinytext COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rating_p` int(11) DEFAULT NULL,
  `solde` double DEFAULT NULL,
  PRIMARY KEY (`ref_p`),
  KEY `IDX_BE2DDF8CEC0A8568` (`ref_c`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`ref_p`, `ref_c`, `nom_p`, `genre_p`, `couleur_p`, `quantite_p`, `prix_p`, `marque_p`, `description`, `image`, `rating_p`, `solde`) VALUES
(265268, 148965, 'basklea', 'Homme', '[\"pink\", \"darkred\"]', 25, 25, 'soule', 'test', 'f_5e98b3a81e9d0.jpg', NULL, NULL),
(266266, 148965, 'veloo', 'Homme', '[\"green\"]', 22, 23, 'bakle', 'tes1', 'f_5e98b359f3dec.jpg', NULL, NULL),
(299299, 587744, 'velo', 'Homme', '[\"pink\", \"darkred\"]', 49, 52, 'soule', 'test1', 'f_5e98b3e9837e4.jpg', NULL, NULL),
(666666, 148965, 'bask', 'Femme', '[\"green\", \"pink\"]', 26, 26, 'baskla', 'test1', 'f_5e98bcbd1aae9.jpg', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) NOT NULL,
  `idProd` int(11) NOT NULL,
  `totalRate` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D8892622C6494F09` (`idProd`),
  KEY `IDX_D8892622A455ACCF` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `rating`
--

INSERT INTO `rating` (`id`, `rate`, `idProd`, `totalRate`, `idClient`) VALUES
(3, 2, 266266, 2, 9),
(4, 4, 666666, 4, 9),
(5, 5, 299299, 5, 9);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `userid` int(11) DEFAULT NULL,
  `idR` int(11) NOT NULL AUTO_INCREMENT,
  `dateR` date NOT NULL,
  `objetR` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etatR` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `detailsR` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idR`),
  KEY `IDX_CE606404F132696E` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`userid`, `idR`, `dateR`, `objetR`, `etatR`, `detailsR`) VALUES
(9, 4, '2020-05-18', 'probleme de livaison', 'Non traitee', 'retard'),
(15, 5, '2020-06-02', 'Produit abime', 'non traitee', ':\"\")');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_event` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_42C84955D52B4B97` (`id_event`),
  KEY `IDX_42C849556B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `id_event`, `id_user`, `etat`) VALUES
(6, 6, 10, 'Annulée'),
(7, 2, 9, 'Confirmée'),
(9, 2, 9, 'Annulée'),
(10, 2, 15, 'réservé'),
(11, 3, 15, 'réservé');

-- --------------------------------------------------------

--
-- Structure de la table `r_d_v`
--

DROP TABLE IF EXISTS `r_d_v`;
CREATE TABLE IF NOT EXISTS `r_d_v` (
  `technicienid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `idRDV` int(11) NOT NULL AUTO_INCREMENT,
  `dateDeptRDV` date NOT NULL,
  `dateRDV` date NOT NULL,
  `objetRDV` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etatRDV` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `detailsRDV` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idRDV`),
  KEY `IDX_1DC5AD28D12D9D92` (`technicienid`),
  KEY `IDX_1DC5AD28F132696E` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `r_d_v`
--

INSERT INTO `r_d_v` (`technicienid`, `userid`, `idRDV`, `dateDeptRDV`, `dateRDV`, `objetRDV`, `etatRDV`, `detailsRDV`) VALUES
(NULL, 9, 13, '2020-05-18', '2020-05-18', 'test', 'Non traitee', 'test'),
(NULL, 9, 14, '2020-05-18', '2020-10-18', 'probleme de livraison', 'Non traitee', 'retard'),
(NULL, 15, 15, '2020-06-02', '2020-06-10', 'Reparation', 'non traitee', 'j ai un probleme1'),
(NULL, 15, 16, '2020-06-02', '2020-06-10', 'Reparation', 'non traitee', 'j ai un probleme2');

-- --------------------------------------------------------

--
-- Structure de la table `technicien`
--

DROP TABLE IF EXISTS `technicien`;
CREATE TABLE IF NOT EXISTS `technicien` (
  `idT` int(11) NOT NULL AUTO_INCREMENT,
  `cin` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numtel` int(11) NOT NULL,
  PRIMARY KEY (`idT`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `technicien`
--

INSERT INTO `technicien` (`idT`, `cin`, `nom`, `prenom`, `email`, `numtel`) VALUES
(2, 12345645, 'Zeineb', 'ZeinebB', 'Nouay@Nouay.tn', 12345678);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `picturePath` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cin` int(11) DEFAULT NULL,
  `numtel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `picturePath`, `cin`, `numtel`) VALUES
(2, 'livreur', 'livreur', 'livreur@gmail.com', 'livreur@gmail.com', 1, NULL, '$2y$13$tlQMGW5X693PcrBLgSNeG.esxjNIznu/wxH7Bmy.WmwczsOe8KUFW', '2020-02-24 19:26:23', NULL, NULL, 'a:1:{i:0;s:12:\"ROLE_LIVREUR\";}', NULL, NULL, NULL),
(9, 'nour', 'nour', 'nour.khedher@esprit.tn', 'nour.khedher@esprit.tn', 1, NULL, '$2y$13$GrFiPpxIfR4xI6dv2NYzL.ogihFG.vttiD83A3U.IxdgaLj7hdihe', '2020-05-10 04:32:11', NULL, NULL, 'a:2:{i:0;s:11:\"ROLES_ADMIN\";i:1;s:12:\"ROLE_LIVREUR\";}', NULL, NULL, NULL),
(10, 'bb', 'bb', 'gonklovox@gmail.com', 'gonklovox@gmail.com', 1, NULL, '$2y$13$J6UP6cudvnLh3GlncIqIb.GLn9k.yJZf2wjhagy0ucoWocfStwxBa', '2020-02-24 21:28:52', NULL, NULL, 'a:0:{}', NULL, NULL, NULL),
(12, 'achraf', 'achraf', 'achraf@gmail.com', 'achraf@gmail.com', 1, NULL, '$2y$13$ljiUeMVKA93SkxJJ8hwCE.wlNF3E5uncDd7uYHL8wAF0PJdLmLKYq', '2020-02-24 21:07:43', NULL, NULL, 'a:1:{i:0;s:12:\"ROLE_LIVREUR\";}', NULL, NULL, NULL),
(14, 'gonne', 'gonne', 'gon@esprit.tn', 'gon@esprit.tn', 1, NULL, '$2y$13$EEZauBee2Tw7eJGy9EjZqOnmDlK8hAOFXkinWwWDRY5vIbqSyisn6', NULL, NULL, NULL, 'a:0:{}', NULL, 123456, NULL),
(15, 'nouay', 'nouay', 'nouay@nour.com', 'nouay@nour.com', 1, NULL, '$2y$13$pDHpcY6sh0dyKPBWDUkwyOTKrh2kAFR82pXFeWn275aj5O3rhpjc.', NULL, NULL, NULL, 'a:0:{}', NULL, 25687469, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `matricule` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `marque` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `livreur_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_292FFF1D12B2DC9C` (`matricule`),
  KEY `IDX_292FFF1DF8646701` (`livreur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`matricule`, `marque`, `type`, `id`, `livreur_id`) VALUES
('123kl', 'nouay', 'nouay', 1, NULL),
('mac', '1254HFD', 'Vehicule', 3, 9);

-- --------------------------------------------------------

--
-- Structure de la table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `nom_prod` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix_prod` double NOT NULL,
  `qte_prod` int(11) NOT NULL,
  `image_prod` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `refP` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9CE12A31F6A22315` (`refP`),
  KEY `IDX_9CE12A31E173B1B8` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `wishlist`
--

INSERT INTO `wishlist` (`id`, `id_client`, `nom_prod`, `prix_prod`, `qte_prod`, `image_prod`, `refP`) VALUES
(16, 9, 'veloo', 23, 23, 'f_5e98b359f3dec.jpg', 266266),
(17, 9, 'veloo', 23, 23, 'f_5e98b359f3dec.jpg', 266266),
(18, 9, 'veloo', 23, 23, 'f_5e98b359f3dec.jpg', 266266),
(19, 9, 'basklea', 25, 25, 'f_5e98b3a81e9d0.jpg', 265268),
(20, 9, 'veloo', 23, 23, 'f_5e98b359f3dec.jpg', 266266),
(21, 9, 'veloo', 23, 23, 'f_5e98b359f3dec.jpg', 266266),
(22, 9, 'veloo', 23, 23, 'f_5e98b359f3dec.jpg', 266266),
(23, 9, 'basklea', 25, 25, 'f_5e98b3a81e9d0.jpg', 265268),
(24, 9, 'basklea', 25, 25, 'f_5e98b3a81e9d0.jpg', 265268),
(25, 9, 'basklea', 25, 25, 'f_5e98b3a81e9d0.jpg', 265268),
(27, 10, 'veloo', 23, 22, 'f_5e98b359f3dec.jpg', 266266),
(28, 10, 'bask', 26, 26, 'f_5e98bcbd1aae9.jpg', 666666),
(31, 10, 'velo', 52, 49, 'f_5e98b3e9837e4.jpg', 299299);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contrat`
--
ALTER TABLE `contrat`
  ADD CONSTRAINT `FK_60349993977523A4` FOREIGN KEY (`id_partenaire`) REFERENCES `partenaire` (`id`),
  ADD CONSTRAINT `FK_60349993D52B4B97` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`);

--
-- Contraintes pour la table `details_commande`
--
ALTER TABLE `details_commande`
  ADD CONSTRAINT `FK_4BCD5F63D498C26` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `FK_A60C9F1F3D498C26` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `FK_A60C9F1FFBC3259F` FOREIGN KEY (`idLivreur`) REFERENCES `livreur` (`id`);

--
-- Contraintes pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD CONSTRAINT `FK_EB7A4E6DA885EAE2` FOREIGN KEY (`id_username`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `postscomments`
--
ALTER TABLE `postscomments`
  ADD CONSTRAINT `FK_84B66E3C29773213` FOREIGN KEY (`idPost`) REFERENCES `postsforum` (`id`),
  ADD CONSTRAINT `FK_84B66E3CFE6E88D7` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `postsforum`
--
ALTER TABLE `postsforum`
  ADD CONSTRAINT `FK_625EF76F9C370B71` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_625EF76FFE6E88D7` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `FK_BE2DDF8CEC0A8568` FOREIGN KEY (`ref_c`) REFERENCES `categories` (`ref_c`);

--
-- Contraintes pour la table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `FK_D8892622A455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_D8892622C6494F09` FOREIGN KEY (`idProd`) REFERENCES `produits` (`ref_p`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_CE606404F132696E` FOREIGN KEY (`userid`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C849556B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_42C84955D52B4B97` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`);

--
-- Contraintes pour la table `r_d_v`
--
ALTER TABLE `r_d_v`
  ADD CONSTRAINT `FK_1DC5AD28D12D9D92` FOREIGN KEY (`technicienid`) REFERENCES `technicien` (`idT`),
  ADD CONSTRAINT `FK_1DC5AD28F132696E` FOREIGN KEY (`userid`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `FK_292FFF1DF8646701` FOREIGN KEY (`livreur_id`) REFERENCES `livreur` (`id`);

--
-- Contraintes pour la table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FK_9CE12A31E173B1B8` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_9CE12A31F6A22315` FOREIGN KEY (`refP`) REFERENCES `produits` (`ref_p`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
