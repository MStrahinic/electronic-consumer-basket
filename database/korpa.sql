/*
SQLyog Community Edition- MySQL GUI v5.32
Host - 5.0.41-community-nt : Database - korpa
*********************************************************************
Server version : 5.0.41-community-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `korpa`;

USE `korpa`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `KorisnikId` int(10) NOT NULL auto_increment,
  `Ime` varchar(15) default NULL,
  `Prezime` varchar(20) default NULL,
  `Email` varchar(40) default NULL,
  `Adresa` varchar(40) default NULL,
  `Telefon` varchar(20) default NULL,
  `korisnickoIme` varchar(20) default NULL,
  `lozinka` varchar(20) default NULL,
  `DatumZaposlenja` varchar(20) default NULL,
  `Administrator` int(1) default NULL,
  PRIMARY KEY  (`KorisnikId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `korisnik` */

insert  into `korisnik`(`KorisnikId`,`Ime`,`Prezime`,`Email`,`Adresa`,`Telefon`,`korisnickoIme`,`lozinka`,`DatumZaposlenja`,`Administrator`) values (1,'Milan','Strahinic','strahinicmilan@yahoo.com','Losinjska 58','011/233-2323','smilan','123','21.03.2008',1),(5,'Pera','Peric','pera@gmail.com','pericina 25','123223','peraperic','peraperic',NULL,0);

/*Table structure for table `potrosackakorpa` */

DROP TABLE IF EXISTS `potrosackakorpa`;

CREATE TABLE `potrosackakorpa` (
  `KorisnikId` int(10) NOT NULL default '0',
  `PKID` int(10) NOT NULL default '0',
  `DatumKreiranja` varchar(50) default NULL,
  `AdresaIsporuke` varchar(40) default NULL,
  `Status` int(1) default NULL,
  `DatumNarucivanja` varchar(50) default NULL,
  PRIMARY KEY  (`KorisnikId`,`PKID`),
  CONSTRAINT `FK_potrosackakorpa` FOREIGN KEY (`KorisnikId`) REFERENCES `korisnik` (`KorisnikId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `potrosackakorpa` */

insert  into `potrosackakorpa`(`KorisnikId`,`PKID`,`DatumKreiranja`,`AdresaIsporuke`,`Status`,`DatumNarucivanja`) values (1,1,'03.05.2009','Losinjaska 58',0,'01.06.2009'),(1,2,'01.05.2009','Svetosavska 22',0,'04.05.2009'),(1,3,'02.09.2009.','Losinjska 58',0,'29.09.2010.'),(1,4,'29.09.2010.','Losinjska 58',0,'29.09.2010.'),(1,6,'29.09.2010.','Losinjska 58',0,'29.09.2010.'),(1,7,'29.09.2010.','Losinjska 58',0,'29.09.2010.'),(1,8,'29.09.2010.','Losinjska 58',1,NULL),(5,5,'29.09.2010.','Bla 31',0,'29.09.2010.'),(5,9,'29.09.2010.','pericina 25',0,'29.09.2010.'),(5,10,'29.09.2010.','pericina 25',1,NULL);

/*Table structure for table `proizvod` */

DROP TABLE IF EXISTS `proizvod`;

CREATE TABLE `proizvod` (
  `ProizvodId` int(10) NOT NULL auto_increment,
  `Naziv` varchar(50) default NULL,
  `Opis` varchar(200) default NULL,
  `Kolicina` int(10) default NULL,
  `Cena` double default NULL,
  `DatumPoslednjeIzmene` varchar(50) default NULL,
  `AdminPoslednjeIzmene` varchar(20) default NULL,
  `Slika` text,
  PRIMARY KEY  (`ProizvodId`),
  KEY `FK_proizvod` (`AdminPoslednjeIzmene`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proizvod` */

insert  into `proizvod`(`ProizvodId`,`Naziv`,`Opis`,`Kolicina`,`Cena`,`DatumPoslednjeIzmene`,`AdminPoslednjeIzmene`,`Slika`) values (1,'Dres Novog Zelanda','Kooga',9,600,'29.09.2010.','nmitic','slike/novizeland.jpg'),(2,'Dres Irske','Kooga',9,300,'23.08.2009','smilan','slike/irska.jpg'),(3,'Dres Australije','Kooga',6,400,'25.08.2009','nmitic','slike/australija.jpg'),(4,'Dres Engleske','Kooga',7,400,'02.09.2009.','smilan','slike/engleska.jpg'),(5,'Dres Welsa','Kooga',8,500,'31.08.2009.','nmitic','slike/vels.jpg'),(6,'Dres Skotske','Kooga',11,600,'23.10.2010.','smilan','slike/skotska.jpg'),(7,'dssd','dsf',333,444,NULL,NULL,'ddsf');

/*Table structure for table `stavkapk` */

DROP TABLE IF EXISTS `stavkapk`;

CREATE TABLE `stavkapk` (
  `KorisnikId` int(10) NOT NULL default '0',
  `PKID` int(10) NOT NULL default '0',
  `RB` int(10) NOT NULL default '0',
  `ProizvodId` int(10) default '0',
  `Kolicina` int(10) default NULL,
  PRIMARY KEY  (`KorisnikId`,`PKID`,`RB`),
  KEY `FK_proizvodID` (`ProizvodId`),
  CONSTRAINT `FK_stavkapk` FOREIGN KEY (`KorisnikId`, `PKID`) REFERENCES `potrosackakorpa` (`KorisnikId`, `PKID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_stavkapk_2` FOREIGN KEY (`ProizvodId`) REFERENCES `proizvod` (`ProizvodId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stavkapk` */

insert  into `stavkapk`(`KorisnikId`,`PKID`,`RB`,`ProizvodId`,`Kolicina`) values (1,1,1,2,3),(1,2,1,1,3),(1,2,2,3,1),(1,2,3,5,5),(1,3,1,1,1),(1,3,2,3,1),(1,3,3,2,2),(1,3,4,2,1),(1,4,1,1,1),(1,6,1,1,1),(1,7,1,1,1),(1,8,2,2,1),(5,5,1,1,1),(5,5,2,1,1),(5,9,1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
