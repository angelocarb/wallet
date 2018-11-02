CREATE DATABASE  IF NOT EXISTS `wallet` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `wallet`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: wallet
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `transazioni`
--

DROP TABLE IF EXISTS `transazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transazioni` (
  `IDtrans` int(50) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(100) NOT NULL,
  `Valore` int(11) DEFAULT NULL,
  `Data` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IDtrans`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transazioni`
--

LOCK TABLES `transazioni` WRITE;
/*!40000 ALTER TABLE `transazioni` DISABLE KEYS */;
INSERT INTO `transazioni` VALUES (2,'abbonamento SKY',-35,'2018-01-05'),(3,'agenzia di viaggi',-300,'2018-01-10'),(5,'Sito Web cliente',450,'2018-02-06'),(7,'stipendio',1600,'2017-12-15'),(8,'stipendio',1600,'2017-11-15'),(9,'stipendio',1600,'2017-10-15'),(10,'stipendio',1600,'2017-09-15'),(11,'stipendio',1600,'2017-08-15'),(12,'stipendio',1600,'2017-07-15'),(13,'stipendio',1600,'2017-06-15'),(14,'stipendio',1600,'2017-05-15'),(15,'stipendio',1600,'2017-04-15'),(16,'stipendio',1600,'2017-03-15'),(17,'stipendio',1600,'2017-02-15'),(18,'stipendio',1600,'2017-01-15'),(19,'viaggio in Egitto',-1250,'2017-01-19'),(20,'acquisto scrivania',-178,'2017-02-25'),(21,'abbonamento SKY',-35,'2018-01-24'),(22,'affitto garage',-550,'2017-03-17'),(23,'sito web cliente',360,'2017-03-30'),(24,'sito web cliente',270,'2017-05-27'),(25,'affitto locale per festa laurea',-780,'2017-04-13'),(26,'manutenzione auto',-240,'2017-05-20'),(27,'manutenzione caldaia',-110,'2017-06-15'),(28,'vacanza Malta',-1370,'2017-07-13'),(29,'affitto camper',-370,'2017-08-23'),(30,'tassa iscrizione Università',-408,'2017-09-11'),(31,'acquisto poltrona',-600,'2017-10-20'),(32,'pagamento piastrellista',-460,'2017-11-16'),(33,'acquisto moto',-870,'2017-12-21'),(34,'sito web cliente',450,'2017-10-15'),(36,'stipendio',1600,'2018-01-15'),(82,'bolletta gas',-90,'2018-02-05');
/*!40000 ALTER TABLE `transazioni` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-20 17:04:38
