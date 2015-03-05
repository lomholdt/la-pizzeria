-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Vært: mysql.itu.dk
-- Genereringstid: 02. 03 2015 kl. 17:08:21
-- Serverversion: 5.0.67
-- PHP-version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `la_pizzeria`
--

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `pizza`
--

CREATE TABLE IF NOT EXISTS `pizza` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL auto_increment,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `zipcode` int(11) NOT NULL,
  `phonenumber` int(11) NOT NULL,
  `active` boolean NOT NULL DEFAULT false,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `pin` (
  `useremail` varchar(45) NOT NULL,
  `pincode` int(4) NOT NULL,
  PRIMARY KEY (`useremail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


/* INSERT SOME PIZZAS */
INSERT INTO pizza(name, price, description) VALUES('Marianara', '75', 'tomato, anchovies, grated parmesan, black olives, oregano & olive oil');
INSERT INTO pizza(name, price, description) VALUES('Margherita', '85', 'Margherita - tomato, mozzarella, basil');
INSERT INTO pizza(name, price, description) VALUES('Salamina', '115', 'tomato, mozzarella, salame, mascarpone');
INSERT INTO pizza(name, price, description) VALUES('Burrata', '130', 'vine tomatoes, rucola & burrata');
INSERT INTO pizza(name, price, description) VALUES('''Burning Love''', '135', 'mozzarella, potatoes, fried onions, røget spæk');
INSERT INTO pizza(name, price, description) VALUES('Prosciulto', '130', 'tomato, mozzarella, prosciulto di Norcia, rucola, pesto');
INSERT INTO pizza(name, price, description) VALUES('Gorgonzola e''Nduja', '140', 'tomato, mozzarella, gorgonzola & spicy soft salame');
INSERT INTO pizza(name, price, description) VALUES('Capricciosa', '140', 'tomato, mozzarella, ham, mushrooms, artichokes, olive, egg');
INSERT INTO pizza(name, price, description) VALUES('Bufala', '140', 'tomato, vine tomatoes, buffalo mozzarella, basil');
INSERT INTO pizza(name, price, description) VALUES('Zuccone', '140', 'mozzarella, courgette, smoked salmon, olives');
INSERT INTO pizza(name, price, description) VALUES('Vegetariana', '140', 'tomato, mozzarella, seasonal vegetables, fresh basil, balsamico');
INSERT INTO pizza(name, price, description) VALUES('''Nick says it''s good''', '140', 'mozzarella, cauliflower, green olives, anchovies, capers, chili, pecorino cheese');
INSERT INTO pizza(name, price, description) VALUES('Porcella', '155', 'organic sausage, porcini, tomato, mozzarella');
INSERT INTO pizza(name, price, description) VALUES('Muuu!', '155', 'dry cured beef, vine tomatoes, rucola, shaved parmesan');
INSERT INTO pizza(name, price, description) VALUES('Pepperoni & peperoni', '155', 'tomato, mozzarella, marinaded peppers, spicy salame, smoked cheese, parsley');
INSERT INTO pizza(name, price, description) VALUES('Bieta e Ciauscolo', '155', 'tomato, mozzarella, spicy chards (sølvbede) and freshly cured soft salame');
