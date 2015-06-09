-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.50-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema visitrecord
--

CREATE DATABASE IF NOT EXISTS visitrecord;
USE visitrecord;

--
-- Definition of table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order` int(10) unsigned DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `teambelong` varchar(45) DEFAULT NULL,
  `knowway` varchar(45) DEFAULT NULL,
  `counselor` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `date` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`id`,`order`,`name`,`phone`,`teambelong`,`knowway`,`counselor`,`remark`,`gender`,`date`) VALUES 
 (1,1,'sdf','12345678901','111111','aaa','ssss','sdfs','sfa','2015-05-31 09:09'),
 (2,2,'ss','12345678901','111111','233','fdfdd','sdfs','sfa','2015-05-31 09:09'),
 (3,0,'啊了','12345678906','','','','',NULL,'2015-05-31 09:09'),
 (4,0,'哈哈','14725836960','','','','',NULL,'2015-05-31 09:09'),
 (5,0,'哈哈哈','14752863906','','','sdfs','as',NULL,'2015-06-01 19:53');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
