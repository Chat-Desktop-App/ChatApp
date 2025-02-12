CREATE DATABASE  IF NOT EXISTS `chat_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chat_app`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: chat_app
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcements` (
  `message` text NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `contact_user_id` int NOT NULL AUTO_INCREMENT,
  `contact_id` varchar(15) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `status` enum('PENDING','ACCEPTED','REJECTED','BLOCKED') NOT NULL,
  `last_chat_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`contact_user_id`),
  KEY `contact_id` (`contact_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `contacts_ibfk_1` FOREIGN KEY (`contact_id`) REFERENCES `users` (`phone_number`),
  CONSTRAINT `contacts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`phone_number`),
  CONSTRAINT `contacts_chk_1` CHECK ((`contact_id` <> `user_id`))
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (2,'1234567890','0987654321','ACCEPTED','2025-01-31 23:24:12'),(3,'1122334455','1234567890','ACCEPTED','2025-01-31 23:26:12'),(4,'2233445566','0987654321','ACCEPTED','2025-01-31 23:10:12'),(5,'3344556677','2233445566','ACCEPTED','2025-02-10 13:43:15'),(6,'4455667788','1122334455','ACCEPTED','2025-01-31 22:55:12'),(7,'5566778899','2233445566','PENDING','2025-01-31 23:50:12'),(8,'6677889900','3344556677','BLOCKED','2025-01-31 23:34:12'),(9,'7788990011','4455667788','REJECTED','2025-01-31 23:29:12'),(10,'8899001122','5566778899','ACCEPTED','2025-01-31 22:54:12'),(11,'3344556677','1234567890','ACCEPTED','2025-01-31 23:15:12'),(12,'2233445566','1234567890','ACCEPTED','2025-02-10 18:24:16'),(13,'7788990011','1234567890','ACCEPTED','2025-02-12 16:05:38'),(14,'5566778899','1234567890','ACCEPTED','2025-01-31 22:53:12');
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `files` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `file_type` enum('MUSIC','FILE','IMAGE') NOT NULL DEFAULT 'FILE',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (52,'acceptIcon.png','server_files/acceptIcon.png','FILE'),(53,'acceptIcon.png','server_files/acceptIcon.png','IMAGE'),(54,'114 الناس.mp3','server_files/114 الناس.mp3','MUSIC'),(55,'114 الناس.mp3','server_files/114 الناس.mp3','MUSIC'),(56,'acceptIcon.png','server_files/acceptIcon.png','IMAGE');
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_members` (
  `group_id` int NOT NULL,
  `member_id` varchar(15) NOT NULL,
  `unreaded` int DEFAULT '0',
  PRIMARY KEY (`group_id`,`member_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `group_members_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  CONSTRAINT `group_members_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `users` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
INSERT INTO `group_members` VALUES (1,'0987654321',0),(1,'1122334455',0),(1,'1234567890',0),(2,'0987654321',0),(2,'1234567890',0),(2,'2233445566',0),(3,'1122334455',0),(3,'1234567890',0),(3,'3344556677',0),(4,'1234567890',0),(4,'2233445566',0),(4,'7788990011',0),(5,'1234567890',0),(5,'3344556677',0),(6,'1234567890',0),(7,'1234567890',0),(8,'1234567890',0),(11,'1234567890',0),(11,'5566778899',0),(11,'7788990011',0),(12,'1234567890',0),(12,'7788990011',0),(13,'1234567890',0),(13,'7788990011',0),(14,'1234567890',0),(14,'7788990011',0),(15,'1234567890',0),(15,'7788990011',0);
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  `admin_id` varchar(255) NOT NULL,
  `picture` text,
  `last_chat_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `users` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'Developers','1234567890','pictures\\1_group_pic.jpg','2025-01-31 23:29:12'),(2,'Travel Buddies','0987654321','pictures\\2_group_pic.jpg','2025-02-10 13:45:40'),(3,'Book Club','1122334455','pictures\\3_group_pic.jpg','2025-01-31 23:06:12'),(4,'Sports Fans','2233445566','pictures\\4_group_pic.jpg','2025-02-11 23:36:56'),(5,'Music Lovers','3344556677','pictures\\5_group_pic.jpg','2025-01-31 23:08:12'),(6,'Foodies','4455667788','pictures\\6_group_pic.jpg','2025-01-31 22:53:12'),(7,'Artists','5566778899','pictures\\7_group_pic.jpg','2025-01-31 23:11:12'),(8,'Gamers','6677889900','pictures\\8_group_pic.jpg','2025-01-31 23:24:12'),(9,'Fashionistas','7788990011','pictures\\9_group_pic.jpg','2025-01-31 23:40:12'),(10,'Photographers','8899001122','pictures\\10_group_pic.jpg','2025-01-31 23:15:12'),(11,'kkk','1234567890','pictures\\11_group_pic.jpg','2025-02-12 11:40:07'),(12,'mm','1234567890',NULL,'2025-02-12 11:51:46'),(13,'ll','1234567890',NULL,'2025-02-12 11:54:57'),(14,'lk','1234567890',NULL,'2025-02-12 11:55:41'),(15,'nnn','7788990011',NULL,'2025-02-12 12:13:31');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `sender_id` varchar(15) NOT NULL,
  `recipient_type` enum('PRIVATE','GROUP') NOT NULL,
  `receiver_id` varchar(15) DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  `content` longtext NOT NULL,
  `file_id` int DEFAULT NULL,
  `font_size` int NOT NULL DEFAULT '14',
  `font_style` varchar(50) NOT NULL DEFAULT 'sans-serif',
  `font_color` varchar(25) DEFAULT NULL,
  `is_bold` tinyint(1) NOT NULL DEFAULT '0',
  `is_italic` tinyint(1) NOT NULL DEFAULT '0',
  `text_background_color` varchar(20) DEFAULT NULL,
  `time_stand` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `emoji` varchar(255) DEFAULT NULL,
  `isRead` int DEFAULT '0',
  `is_underlined` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `group_id` (`group_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`phone_number`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  CONSTRAINT `messages_ibfk_3` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'1234567890','PRIVATE','0987654321',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1,0),(2,'0987654321','PRIVATE','1234567890',NULL,'Hi John, I\'m good!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1,0),(3,'1122334455','GROUP',NULL,3,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1,0),(4,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1,0),(5,'3344556677','GROUP',NULL,5,'Check out this new song!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1,0),(6,'1234567890','PRIVATE','7788990011',NULL,'You can customize the ListCell items of your ListView by setting the background to transparent. You need to adjust the cell factory for the ListView to remove the background from individual cells.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-21 18:18:08',NULL,1,0),(7,'1234567890','PRIVATE','7788990011',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-07-13 06:13:33',NULL,1,0),(8,'1234567890','PRIVATE','7788990011',NULL,'To remove the white background of the ListView cells in JavaFX, you need to apply a transparent background not just to the ListView itself but also to its individual ListCell elements. It looks like the ListView background is already set to transparent, but the cell background might still be white.\n\nHere’s how to fix that:',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-12-28 19:13:24',NULL,1,0),(9,'7788990011','PRIVATE','1234567890',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-05-15 09:26:22',NULL,1,0),(10,'7788990011','PRIVATE','1234567890',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-11-13 08:31:40',NULL,1,0),(11,'7788990011','PRIVATE','1234567890',NULL,'You also have CSS rules in your stylesheet. To ensure that there is no unwanted white background on buttons and the ListView, you can adjust these styles.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-25 17:19:13',NULL,1,0),(12,'1234567890','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-07-19 14:01:07',NULL,1,0),(13,'1234567890','GROUP',NULL,4,'To remove the white background of the ListView cells in JavaFX, you need to apply a transparent background not just to the ListView itself but also to its individual ListCell elements. It looks like the ListView background is already set to transparent, but the cell background might still be white.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-01-17 13:07:59',NULL,1,0),(14,'1234567890','GROUP',NULL,4,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-08-03 03:36:35',NULL,1,0),(15,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-18 22:39:01',NULL,1,0),(16,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-24 04:25:21',NULL,1,0),(17,'3344556677','GROUP',NULL,4,'Check out this new song!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-09-26 06:09:43',NULL,1,0),(18,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-12-31 12:32:34',NULL,1,0),(19,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-16 00:13:42',NULL,1,0),(20,'3344556677','GROUP',NULL,4,'Check out this new song!',NULL,14,'sans-serif','#000000',0,0,'rgb(255, 255, 77)','2023-12-13 06:30:49',NULL,1,0),(21,'1234567890','GROUP',NULL,4,'To remove the white background of the ListView cells in JavaFX, you need to apply a transparent background not just to the ListView itself but also to its individual ListCell elements. It looks like the ListView background is already set to transparent, but the cell background might still be white.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-05-18 11:53:03',NULL,1,0),(22,'1234567890','GROUP',NULL,4,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-01-14 10:52:46',NULL,1,0),(23,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-01-20 21:44:43',NULL,1,0),(24,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-01 04:05:21',NULL,1,0),(25,'3344556677','GROUP',NULL,4,'Check out this new song!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-08-25 04:12:35',NULL,1,0),(26,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-01 04:46:54',NULL,1,0),(27,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-20 10:16:47',NULL,1,0),(28,'3344556677','GROUP',NULL,4,'Check out this new song!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-05 12:03:12',NULL,1,0),(39,'1234567890','PRIVATE','7788990011',NULL,'hello\n',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 00:29:14',NULL,1,0),(40,'1234567890','PRIVATE','7788990011',NULL,'helll\n',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 00:46:03',NULL,1,0),(41,'1234567890','PRIVATE','7788990011',NULL,'skldf\n',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 00:46:44',NULL,1,0),(42,'1234567890','PRIVATE','7788990011',NULL,'sdkf\nsfvs\nsvsd\nsgvsd\nsdv\nsdv\nvsd\n',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 00:46:51',NULL,1,0),(43,'1234567890','PRIVATE','7788990011',NULL,'das\n',NULL,12,'Sans-serif','rgb(230, 77, 77)',1,1,'rgb(230, 230, 77)','2025-02-08 00:51:57',NULL,1,0),(44,'1234567890','PRIVATE','7788990011',NULL,'afda\n',NULL,12,'Monospace','rgb(230, 77, 77)',1,1,'rgb(230, 230, 77)','2025-02-08 00:52:08',NULL,1,0),(45,'1234567890','PRIVATE','7788990011',NULL,'hhh\n',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 00:57:26',NULL,1,0),(46,'1234567890','PRIVATE','7788990011',NULL,'mm\n',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 01:00:01',NULL,1,0),(47,'1234567890','PRIVATE','7788990011',NULL,'hi\n',NULL,12,'Serif','rgb(255, 0, 0)',1,1,'rgb(255, 255, 77)','2025-02-08 01:20:28',NULL,1,0),(48,'1234567890','PRIVATE','7788990011',NULL,'hi\n',NULL,12,'Serif','rgb(255, 0, 0)',0,1,'rgb(255, 255, 77)','2025-02-08 01:20:53',NULL,1,0),(49,'1234567890','PRIVATE','7788990011',NULL,'hi\n',NULL,12,'Serif','rgb(255, 0, 0)',0,0,'rgb(255, 255, 77)','2025-02-08 01:21:01',NULL,1,0),(50,'1234567890','PRIVATE','7788990011',NULL,'hi\n',NULL,12,'Serif','rgb(255, 0, 0)',0,0,'rgb(255, 255, 77)','2025-02-08 01:21:04',NULL,1,0),(51,'1234567890','PRIVATE','7788990011',NULL,'hi\n',NULL,12,'Monospace','rgb(255, 0, 0)',0,0,'rgb(255, 255, 77)','2025-02-08 01:21:14',NULL,1,0),(52,'1234567890','PRIVATE','7788990011',NULL,'kkk',NULL,14,'Sans-serif','rgb(204, 51, 51)',0,0,'rgb(0, 255, 0)','2025-02-08 01:29:48',NULL,1,0),(53,'1234567890','PRIVATE','7788990011',NULL,'fsdfsd',NULL,14,'Sans-serif','rgb(255, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-08 01:37:29',NULL,1,0),(54,'1234567890','PRIVATE','7788990011',NULL,'hi',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 01:43:45',NULL,1,0),(55,'1234567890','PRIVATE','7788990011',NULL,'h',NULL,14,'Sans-serif','rgb(0, 255, 0)',0,0,'#3d7eb6','2025-02-08 01:44:02',NULL,1,0),(56,'1234567890','PRIVATE','7788990011',NULL,'jsdhjsd',NULL,14,'Sans-serif','rgb(153, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-08 01:49:28',NULL,1,0),(57,'1234567890','PRIVATE','7788990011',NULL,'kk',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 01:58:23',NULL,1,0),(58,'1234567890','GROUP',NULL,4,'hello',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 02:18:05',NULL,1,0),(59,'1234567890','GROUP',NULL,4,'sfd',NULL,14,'Sans-serif','rgb(0, 255, 255)',0,0,'rgb(204, 204, 102)','2025-02-08 02:18:21',NULL,1,0),(60,'1234567890','PRIVATE','7788990011',NULL,'hsdjddddddddddddddddddddddddddddddd',NULL,14,'Sans-serif','#000000',0,0,'#3d7eb6','2025-02-08 02:24:50',NULL,1,0),(61,'1234567890','PRIVATE','7788990011',NULL,'jkdsfh',NULL,14,'Algerian','#000000',0,0,'#3d7eb6','2025-02-08 02:44:42',NULL,1,0),(62,'1234567890','PRIVATE','7788990011',NULL,'dsfds ?',NULL,14,'Segoe UI Emoji','#000000',0,0,'#3d7eb6','2025-02-08 03:17:15',NULL,1,0),(63,'1234567890','PRIVATE','7788990011',NULL,'ffxb',NULL,14,'Segoe UI Emoji','#000000',0,0,'#3d7eb6','2025-02-08 03:17:41',NULL,1,0),(64,'1234567890','PRIVATE','7788990011',NULL,'kjhsdg',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 00:35:11',NULL,1,0),(65,'1234567890','PRIVATE','7788990011',NULL,'jkhzk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 00:35:30',NULL,1,0),(66,'7788990011','PRIVATE','1234567890',NULL,'sdhkj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 00:35:44',NULL,1,0),(67,'7788990011','PRIVATE','1234567890',NULL,'hello',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:36:59',NULL,1,0),(68,'7788990011','PRIVATE','1234567890',NULL,'asnfk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:37:01',NULL,1,0),(69,'7788990011','GROUP','1234567890',4,'sjdnfk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:37:07',NULL,1,0),(70,'7788990011','GROUP','1234567890',4,'sdv',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:37:08',NULL,1,0),(71,'7788990011','GROUP','1234567890',4,'jaksf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:37:33',NULL,1,0),(72,'7788990011','GROUP','1234567890',4,'fnfnf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:37:41',NULL,1,0),(73,'7788990011','GROUP','1234567890',4,'ndf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:37:54',NULL,1,0),(74,'1234567890','PRIVATE','7788990011',NULL,'sdkjf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:38:02',NULL,1,0),(75,'7788990011','PRIVATE','1234567890',4,'sjkdf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:38:08',NULL,1,0),(76,'7788990011','PRIVATE','1234567890',4,'hello',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:39:30',NULL,1,0),(77,'1234567890','PRIVATE','7788990011',4,'sdkfl',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 02:40:31',NULL,1,0),(78,'7788990011','PRIVATE','1234567890',NULL,'jsdnkf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:16:29',NULL,0,0),(79,'7788990011','PRIVATE','1234567890',NULL,'klsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:16:37',NULL,0,0),(80,'1234567890','PRIVATE','7788990011',NULL,'kjsfd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:16:44',NULL,0,0),(81,'1234567890','PRIVATE','7788990011',NULL,'kvjsn',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:16:46',NULL,0,0),(82,'1234567890','GROUP','7788990011',4,'ho',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:17:19',NULL,0,0),(83,'1234567890','GROUP','7788990011',4,'bjshd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:17:25',NULL,0,0),(84,'1234567890','GROUP',NULL,4,'hi',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:21:24',NULL,0,0),(85,'7788990011','GROUP',NULL,4,'sdf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:21:37',NULL,0,0),(86,'7788990011','PRIVATE','1234567890',NULL,'jkfd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:45:08',NULL,0,0),(87,'7788990011','GROUP',NULL,4,'md',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:46:29',NULL,0,0),(88,'7788990011','PRIVATE','1234567890',4,',masd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:46:33',NULL,0,0),(89,'1234567890','GROUP',NULL,4,'mdn',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:46:37',NULL,0,0),(90,'7788990011','PRIVATE','1234567890',NULL,'jkds',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:50:48',NULL,0,0),(91,'7788990011','PRIVATE','1234567890',NULL,'heloo',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:56:19',NULL,0,0),(92,'7788990011','PRIVATE','1234567890',NULL,'sdm',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:56:21',NULL,0,0),(93,'7788990011','GROUP','1234567890',4,'ksjdv',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:56:24',NULL,0,0),(94,'1234567890','GROUP',NULL,4,'am,s',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:56:42',NULL,0,0),(95,'1234567890','PRIVATE','7788990011',4,'kla',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 04:56:56',NULL,0,0),(96,'1234567890','GROUP',NULL,4,'hello',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:02:31',NULL,0,0),(97,'1234567890','GROUP',NULL,2,'d',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:07:41',NULL,0,0),(98,'1234567890','PRIVATE','7788990011',2,'f',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:07:55',NULL,0,0),(99,'1234567890','PRIVATE','7788990011',2,'dfkl',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:09:38',NULL,0,0),(100,'1234567890','GROUP',NULL,4,'jkdf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:16:24',NULL,0,0),(101,'1234567890','PRIVATE','2233445566',NULL,'l',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:22:43',NULL,0,0),(102,'1234567890','GROUP',NULL,4,'mrkd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:29:30',NULL,0,0),(103,'1234567890','PRIVATE','7788990011',NULL,'jkf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:32:41',NULL,0,0),(104,'1234567890','PRIVATE','2233445566',NULL,'m',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:38:11',NULL,0,0),(105,'1234567890','GROUP',NULL,4,'j',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:40:59',NULL,0,0),(106,'1234567890','GROUP',NULL,2,'jkds',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:46:12',NULL,0,0),(107,'1234567890','PRIVATE','7788990011',NULL,'hajds',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 11:49:17',NULL,0,0),(108,'1234567890','GROUP',NULL,4,'k',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:22:53',NULL,0,0),(109,'2233445566','GROUP',NULL,4,'hi',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:35:13',NULL,0,0),(110,'2233445566','GROUP',NULL,4,'dalks',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:35:14',NULL,0,0),(111,'1234567890','GROUP',NULL,4,'djf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:35:21',NULL,0,0),(112,'1234567890','GROUP',NULL,4,'fkndj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:35:28',NULL,0,0),(113,'7788990011','GROUP',NULL,4,'dms',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:35:37',NULL,0,0),(114,'1234567890','PRIVATE','2233445566',4,'bsdjf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:35:49',NULL,0,0),(115,'2233445566','GROUP',NULL,4,'ajs',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:36:42',NULL,0,0),(116,'1234567890','PRIVATE','7788990011',4,'sahjd\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:37:04',NULL,0,0),(117,'1234567890','PRIVATE','7788990011',4,'sahjd\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:37:11',NULL,0,0),(118,'1234567890','PRIVATE','7788990011',4,'sahjd\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:37:13',NULL,0,0),(119,'2233445566','PRIVATE','1234567890',NULL,'hi',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:39:44',NULL,0,0),(120,'2233445566','PRIVATE','3344556677',NULL,'ajs',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:43:15',NULL,0,0),(121,'2233445566','GROUP','3344556677',4,'akj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:43:22',NULL,0,0),(122,'2233445566','GROUP','3344556677',2,'hi',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:43:41',NULL,0,0),(123,'2233445566','PRIVATE','1234567890',2,'jkxv',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:43:46',NULL,0,0),(124,'1234567890','GROUP',NULL,4,'bjd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:43:56',NULL,0,0),(125,'1234567890','GROUP',NULL,4,'bjd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:43:58',NULL,0,0),(126,'1234567890','GROUP',NULL,2,'nxz',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:40',NULL,0,0),(127,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(128,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(129,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(130,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(131,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(132,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(133,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(134,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(135,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(136,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(137,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(138,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(139,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(140,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(141,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(142,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(143,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(144,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(145,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(146,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(147,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(148,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(149,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(150,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(151,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(152,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(153,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(154,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(155,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(156,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(157,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(158,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(159,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(160,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(161,'1234567890','GROUP',NULL,4,'nsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:45:48',NULL,0,0),(162,'2233445566','PRIVATE','1234567890',NULL,'sdkj\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:47:11',NULL,0,0),(163,'2233445566','PRIVATE','1234567890',NULL,'sdkj\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:47:11',NULL,0,0),(164,'2233445566','PRIVATE','1234567890',NULL,'sdkj\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:47:11',NULL,0,0),(165,'2233445566','PRIVATE','1234567890',NULL,'sdkj\'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:47:11',NULL,0,0),(166,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(167,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(168,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(169,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(170,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(171,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(172,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(173,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(174,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(175,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(176,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(177,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(178,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(179,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(180,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(181,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(182,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(183,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(184,'2233445566','GROUP',NULL,4,'sdj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:48:16',NULL,0,0),(185,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(186,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(187,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(188,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(189,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(190,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(191,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(192,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(193,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(194,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(195,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(196,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(197,'2233445566','GROUP',NULL,4,'mdfa',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:51:45',NULL,0,0),(198,'1234567890','GROUP',NULL,4,'al',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:09',NULL,0,0),(199,'1234567890','GROUP',NULL,4,'mlcd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:10',NULL,0,0),(200,'1234567890','PRIVATE','2233445566',4,'jka',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:17',NULL,0,0),(201,'1234567890','PRIVATE','2233445566',4,',msvld',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:18',NULL,0,0),(202,'1234567890','PRIVATE','7788990011',4,'jsdv',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:29',NULL,0,0),(203,'1234567890','PRIVATE','7788990011',4,'vsml',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:30',NULL,0,0),(204,'7788990011','GROUP',NULL,4,'jnak',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:35',NULL,0,0),(205,'1234567890','GROUP','7788990011',4,'jksd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:41',NULL,0,0),(206,'1234567890','GROUP','7788990011',4,'nkvs',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:45',NULL,0,0),(207,'7788990011','GROUP',NULL,4,'svdk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:50',NULL,0,0),(208,'7788990011','PRIVATE','1234567890',4,'najd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:54',NULL,0,0),(209,'1234567890','GROUP','7788990011',4,'jka',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:54:58',NULL,0,0),(210,'7788990011','PRIVATE','1234567890',4,'ac',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:55:00',NULL,0,0),(211,'1234567890','GROUP',NULL,4,'bj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:56:21',NULL,0,0),(212,'1234567890','PRIVATE','7788990011',4,'hj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:56:34',NULL,0,0),(213,'7788990011','GROUP',NULL,4,'jhb',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 13:56:39',NULL,0,0),(214,'1234567890','PRIVATE','7788990011',NULL,'vs',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 14:06:13',NULL,0,0),(215,'7788990011','GROUP',NULL,4,'a,k',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 14:06:23',NULL,0,0),(216,'1234567890','PRIVATE','2233445566',NULL,'dgf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 18:24:16',NULL,0,0),(217,'1234567890','GROUP',NULL,4,'sjfo',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 18:44:02',NULL,0,0),(218,'1234567890','GROUP',NULL,4,'dsf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 18:44:22',NULL,0,0),(219,'7788990011','PRIVATE','1234567890',NULL,'سيتن',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 22:56:43',NULL,0,0),(220,'7788990011','PRIVATE','1234567890',NULL,'sldk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-10 22:56:45',NULL,0,0),(230,'7788990011','GROUP',NULL,4,'kjsd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 00:23:19',NULL,0,0),(235,'7788990011','GROUP',NULL,4,'dsaf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 01:41:42',NULL,0,0),(236,'7788990011','PRIVATE','1234567890',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 02:55:37',NULL,0,0),(237,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 03:12:38',NULL,0,0),(238,'7788990011','PRIVATE','1234567890',NULL,'dsf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 03:17:29',NULL,0,0),(239,'7788990011','PRIVATE','1234567890',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 03:17:37',NULL,0,0),(240,'1234567890','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 11:55:23',NULL,0,0),(241,'7788990011','PRIVATE','1234567890',NULL,'jkasbdjk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:01:13',NULL,0,0),(242,'7788990011','PRIVATE','1234567890',NULL,'jsef',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:01:15',NULL,0,0),(243,'7788990011','GROUP','1234567890',4,'klsndg',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:01:24',NULL,0,0),(244,'7788990011','GROUP','1234567890',4,'m,f sd,',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:01:44',NULL,0,0),(245,'7788990011','PRIVATE','1234567890',4,'skd',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:01:55',NULL,0,0),(246,'7788990011','PRIVATE','1234567890',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:02:18',NULL,0,0),(247,'7788990011','PRIVATE','1234567890',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:02:37',NULL,0,0),(248,'7788990011','PRIVATE','1234567890',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:03:01',NULL,0,0),(249,'1234567890','PRIVATE','7788990011',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 12:03:19',NULL,0,0),(250,'7788990011','GROUP',NULL,4,'ايب',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 20:40:07',NULL,0,0),(251,'7788990011','PRIVATE','1234567890',4,'اهلا',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 20:40:22',NULL,0,0),(252,'1234567890','PRIVATE','7788990011',NULL,'ايه الاخبار',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 20:40:56',NULL,0,0),(253,'1234567890','PRIVATE','7788990011',NULL,'عامل ابه',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 20:41:04',NULL,0,0),(254,'1234567890','PRIVATE','7788990011',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 20:41:18',NULL,0,0),(255,'1234567890','GROUP','7788990011',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 20:41:43',NULL,0,0),(256,'1234567890','PRIVATE','7788990011',NULL,'انش',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 21:54:18',NULL,0,0),(257,'1234567890','PRIVATE','7788990011',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 21:54:35',NULL,0,0),(258,'1234567890','GROUP','7788990011',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 21:55:52',NULL,0,0),(259,'7788990011','PRIVATE','1234567890',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 22:26:53',NULL,0,0),(260,'1234567890','PRIVATE','7788990011',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 22:29:58',NULL,0,0),(262,'1234567890','PRIVATE','7788990011',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 22:58:32',NULL,0,0),(263,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 22:59:05',NULL,0,0),(264,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 22:59:54',NULL,0,0),(265,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:00:02',NULL,0,0),(266,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:01:52',NULL,0,0),(267,'7788990011','PRIVATE','1234567890',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:02:04',NULL,0,0),(268,'7788990011','GROUP',NULL,4,'dhafskj',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:02:49',NULL,0,0),(269,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:05:28',NULL,0,0),(270,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:05:39',NULL,0,0),(271,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:05:55',NULL,0,0),(272,'1234567890','PRIVATE','7788990011',NULL,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:06:13',NULL,0,0),(273,'7788990011','GROUP',NULL,4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:07:33',NULL,0,0),(274,'1234567890','GROUP','7788990011',4,'',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:10:37',NULL,0,0),(275,'1234567890','PRIVATE','7788990011',NULL,'ewf',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:14:59',NULL,0,0),(276,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(277,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(278,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(279,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(280,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(281,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(282,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(283,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(284,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(285,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(286,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(287,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(288,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(289,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(290,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(291,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(292,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(293,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(294,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(295,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(296,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(297,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(298,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(299,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(300,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(301,'7788990011','PRIVATE','1234567890',NULL,'سىبيتن',NULL,14,'System Regular','rgb(204, 51, 51)',0,0,'rgb(255, 255, 153)','2025-02-11 23:16:10',NULL,0,0),(302,'7788990011','PRIVATE','1234567890',NULL,'hi',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-11 23:29:54',NULL,0,0),(303,'1234567890','PRIVATE','7788990011',NULL,'fn',NULL,14,'System Regular','rgb(128, 0, 128)',0,0,'rgb(0, 255, 0)','2025-02-11 23:33:32',NULL,0,0),(304,'1234567890','PRIVATE','7788990011',NULL,'',53,14,'System Regular','rgb(128, 0, 128)',0,0,'rgb(0, 255, 0)','2025-02-11 23:33:48',NULL,0,0),(305,'1234567890','PRIVATE','7788990011',NULL,'df',NULL,14,'System Regular','rgb(255, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-11 23:34:09',NULL,0,0),(306,'1234567890','PRIVATE','7788990011',NULL,'',54,14,'System Regular','rgb(255, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-11 23:34:15',NULL,0,0),(307,'1234567890','GROUP','7788990011',4,'dhfb',NULL,14,'System Regular','rgb(255, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-11 23:34:34',NULL,0,0),(308,'1234567890','GROUP','7788990011',4,'',55,14,'System Regular','rgb(255, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-11 23:34:43',NULL,0,0),(309,'1234567890','GROUP','7788990011',4,'fdg',NULL,14,'System Regular','rgb(255, 0, 0)',0,0,'rgb(0, 255, 0)','2025-02-11 23:34:47',NULL,0,0),(310,'1234567890','GROUP','7788990011',4,'df',NULL,14,'System Regular','rgb(255, 0, 0)',0,0,'rgb(179, 26, 26)','2025-02-11 23:34:56',NULL,0,0),(311,'1234567890','GROUP','7788990011',4,'sdgsdgc',NULL,14,'System Regular','rgb(255, 255, 102)',0,0,'rgb(179, 26, 26)','2025-02-11 23:36:35',NULL,0,0),(312,'1234567890','GROUP','7788990011',4,'fgh',NULL,14,'System Regular','rgb(255, 204, 179)',0,0,'rgb(179, 26, 26)','2025-02-11 23:36:42',NULL,0,0),(313,'1234567890','GROUP','7788990011',4,'dfb',NULL,14,'System Regular','rgb(255, 204, 179)',0,0,'rgb(179, 26, 26)','2025-02-11 23:36:47',NULL,0,0),(314,'1234567890','GROUP','7788990011',4,'dfhb',NULL,14,'System Regular','rgb(255, 204, 179)',0,0,'rgb(179, 26, 26)','2025-02-11 23:36:48',NULL,0,0),(315,'1234567890','GROUP','7788990011',4,'dfb',NULL,14,'System Regular','rgb(255, 204, 179)',0,0,'rgb(179, 26, 26)','2025-02-11 23:36:52',NULL,0,0),(316,'1234567890','GROUP','7788990011',4,'fn',NULL,14,'System Regular','rgb(255, 204, 179)',0,0,'rgb(179, 26, 26)','2025-02-11 23:36:56',NULL,0,0),(317,'7788990011','PRIVATE','1234567890',NULL,'اه',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-12 00:41:50',NULL,0,0),(318,'7788990011','PRIVATE','1234567890',NULL,'',56,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-12 00:42:17',NULL,0,0),(319,'1234567890','PRIVATE','7788990011',NULL,'bsdfjk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-12 12:14:06',NULL,0,0),(320,'1234567890','PRIVATE','7788990011',NULL,'mdsk',NULL,14,'System Regular','#000000',0,0,'#3d7eb6','2025-02-12 16:05:38',NULL,0,0);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `message` text NOT NULL,
  `sent_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  `type` enum('MESSAGE','ANNOUNCEMENT','FRIENDREQUEST','ADDTOGROUP') DEFAULT NULL,
  `sender_id` varchar(15) NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `user_id` (`user_id`),
  KEY `sender_id` (`sender_id`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`phone_number`),
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`sender_id`) REFERENCES `users` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'0987654321','You have a new message from John.','2025-01-31 22:50:12',0,'MESSAGE','1234567890'),(3,'1122334455','Alice sent you a contact request.','2025-01-31 22:50:12',0,'FRIENDREQUEST','1234567890'),(4,'2233445566','Bob added you to the group \"Sports Fans\".','2025-01-31 22:50:12',0,'ADDTOGROUP','1234567890'),(5,'3344556677','Charlie sent you a file.','2025-01-31 22:50:12',0,'MESSAGE','1234567890'),(6,'5566778899','You have been added to kkk group','2025-02-12 11:40:07',0,'ADDTOGROUP','1234567890'),(11,'1234567890','You have been added to nnn group','2025-02-12 12:13:31',0,'ADDTOGROUP','7788990011');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `phone_number` varchar(15) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `gender` enum('FEMALE','MALE') NOT NULL,
  `country` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `bio` text,
  `status` enum('AVAILABLE','AWAY','BUSY','OFFLINE') NOT NULL,
  `num_entries` bigint NOT NULL,
  `Last_seen` datetime NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('0987654321','Jane','Smith','jane.smith@example.com','pictures/0987654321_profile_pic.jpg','c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91','FEMALE','Canada','1992-05-15','Travel enthusiast','AWAY',5,'2025-01-31 22:50:12',0),('1122334455','Alice','Johnson','alice.j@example.com','pictures/1122334455_profile_pic.jpg','5efc2b017da4f7736d192a74dde5891369e0685d4d38f2a455b6fcdab282df9c','FEMALE','UK','1985-07-20','Book lover','BUSY',3,'2025-01-31 22:50:12',0),('1234567890','John','Doe','john.doe@example.com','pictures\\1234567890_profile_pic.jpg','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','MALE','USA','1990-01-01','I love coding!','AVAILABLE',1,'2025-02-12 16:17:27',0),('1234567891','ahmed','ashraf','ahmed@gmail.com',NULL,'a60f2f5a3d320dc2dbf00b471fa4df74dc21594bb891ed3587cddea7fab64d0c','MALE','Egypt','2025-02-02','hello ','OFFLINE',1,'2025-02-10 00:27:16',0),('2233445566','Bob','Brown','bob.b@example.com','pictures/2233445566_profile_pic.jpg','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','MALE','Australia','1988-11-30','Sports fan','AVAILABLE',1,'2025-02-10 13:51:14',0),('3344556677','Charlie','Davis','charlie.d@example.com','pictures/3344556677_profile_pic.jpg','28e91b84bd4ac1d95d81b4510777d2b12f3dffa848bb6e219a42f98cdfa06d7d','MALE','Germany','1995-03-25','Music producer','AWAY',2,'2025-01-31 22:50:12',0),('4455667788','David','Wilson','david.w@example.com','pictures/4455667788_profile_pic.jpg','f6537a5a2f097921d1d1ab410facd30c4356da7326783c2f9ed29f093852cfe2','MALE','France','1993-09-12','Foodie','AVAILABLE',7,'2025-01-31 22:50:12',0),('5566778899','Eva','Martinez','eva.m@example.com','pictures/5566778899_profile_pic.jpg','0cd1a34f93572bb80a5bf493d220bb881030335ec17a4e358c6da73a1ec30b4a','FEMALE','Spain','1991-04-18','Artist','OFFLINE',4,'2025-01-31 22:50:12',0),('6677889900','Frank','Garcia','frank.g@example.com','pictures/6677889900_profile_pic.jpg','92c25c70925b616b031e8b7a9ecbbc82c4b151e6ba72b89feb8bc3622b2f2a5d','MALE','Mexico','1987-12-22','Gamer','BUSY',6,'2025-01-31 22:50:12',0),('7788990011','Grace','Lee','grace.l@example.com','pictures/7788990011_profile_pic.jpg','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','FEMALE','South Korea','1994-08-05','Fashionista','AVAILABLE',1,'2025-02-12 12:12:58',0),('8899001122','Henry','Taylor','henry.t@example.com','pictures/8899001122_profile_pic.jpg','31457e06e2adb2178358e9fc6705e0b6f37e9b6ec9456e8890d28f292be9adc4','MALE','USA','1989-06-30','Photographer','AWAY',3,'2025-01-31 22:50:12',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-12 17:48:13
