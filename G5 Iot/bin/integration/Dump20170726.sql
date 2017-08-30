CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `impianti`
--

DROP TABLE IF EXISTS `impianti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `impianti` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cliente` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Nome` (`Nome`,`Cliente`),
  KEY `IDcliente_idx` (`Cliente`),
  CONSTRAINT `IDcliente` FOREIGN KEY (`Cliente`) REFERENCES `utenti` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impianti`
--

LOCK TABLES `impianti` WRITE;
/*!40000 ALTER TABLE `impianti` DISABLE KEYS */;
INSERT INTO `impianti` VALUES (22,'axKJV1',17),(1,'Impianto1',6),(2,'Impianto2',6),(10,'Impianto3',4),(4,'Impianto4',4);
/*!40000 ALTER TABLE `impianti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modellisensori`
--

DROP TABLE IF EXISTS `modellisensori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modellisensori` (
  `Codice` varchar(5) NOT NULL,
  `Tipo` varchar(15) NOT NULL,
  `Marca` varchar(45) NOT NULL,
  PRIMARY KEY (`Codice`),
  UNIQUE KEY `Codice_UNIQUE` (`Codice`),
  KEY `Tipo_idx` (`Tipo`),
  CONSTRAINT `Tipo` FOREIGN KEY (`Tipo`) REFERENCES `tipi` (`Tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modellisensori`
--

LOCK TABLES `modellisensori` WRITE;
/*!40000 ALTER TABLE `modellisensori` DISABLE KEYS */;
INSERT INTO `modellisensori` VALUES ('P01SE','Pressione','Set Sensori Solution'),('P02SE','Pressione','Set Sensori Solution'),('P13US','Pressione','Uva Soluzioni'),('T01MA','Temperatura','Mandriota Sensori'),('T04US','Temperatura','Uva Soluzioni'),('U01MA','Umidità','Mandriota Sensori'),('U01SE','Umidità','Set Sensori Solution');
/*!40000 ALTER TABLE `modellisensori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensori`
--

DROP TABLE IF EXISTS `sensori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sensori` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `modello` varchar(5) NOT NULL,
  `Impianto` int(11) NOT NULL,
  `rilevazione` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Impianto_idx` (`Impianto`),
  KEY `Modello_idx` (`modello`),
  CONSTRAINT `Impianto` FOREIGN KEY (`Impianto`) REFERENCES `impianti` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `Modello` FOREIGN KEY (`modello`) REFERENCES `modellisensori` (`Codice`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensori`
--

LOCK TABLES `sensori` WRITE;
/*!40000 ALTER TABLE `sensori` DISABLE KEYS */;
INSERT INTO `sensori` VALUES (1,'T01MA',1,'T01MA1129201707131030SUC'),(2,'T01MA',1,'T01MA403ERR'),(3,'U01MA',1,'U01MA113020170713047SUC'),(4,'U01SE',1,'U01SE060071320171140SUC'),(5,'P01SE',1,'P01SE01010071320171156SUC'),(6,'P02SE',1,'P02SE402ERR'),(7,'U01SE',2,''),(8,'P01SE',2,''),(9,'T01MA',2,''),(13,'T01MA',4,''),(14,'U01SE',4,''),(15,'U01SE',4,''),(17,'P13US',1,'P13US13072017010131152SUC'),(18,'T04US',1,'T04US1307201710281211SUC'),(19,'U01SE',1,'U01SE096071320171227ANM');
/*!40000 ALTER TABLE `sensori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipi`
--

DROP TABLE IF EXISTS `tipi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipi` (
  `Tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`Tipo`),
  UNIQUE KEY `Tipo_UNIQUE` (`Tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipi`
--

LOCK TABLES `tipi` WRITE;
/*!40000 ALTER TABLE `tipi` DISABLE KEYS */;
INSERT INTO `tipi` VALUES ('Pressione'),('Temperatura'),('Umidità');
/*!40000 ALTER TABLE `tipi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utenti` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(15) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `admin` tinyint(1) unsigned zerofill DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (1,'Matteo','Longano','matteolongano@gmail.com','12345678',1),(2,'Matteo','Lacitignola','matteolacitignola@gmail.com','bicchiere1',1),(3,'Maria','Diplma','mariadipalma@gmail.com','macchia123',0),(4,'Vittoria','Dimola','vittoriadimola@gmail.com','87654321',0),(5,'admin','admin','admin','aaaaaaaa',1),(6,'user','user','user','aaaaaaaa',0),(8,'Marco','Mirizio','marcomiri@gmail.com','asdasdasd',0),(17,'asiodh','aoisdj','siojdoa@oidna.it','asdasdasd',0);
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-26 12:17:31
