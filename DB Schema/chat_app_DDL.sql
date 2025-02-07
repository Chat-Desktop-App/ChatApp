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
INSERT INTO `contacts` VALUES (2,'1234567890','0987654321','ACCEPTED','2025-01-31 23:24:12'),(3,'1122334455','1234567890','ACCEPTED','2025-01-31 23:26:12'),(4,'2233445566','0987654321','ACCEPTED','2025-01-31 23:10:12'),(5,'3344556677','2233445566','ACCEPTED','2025-01-31 23:39:12'),(6,'4455667788','1122334455','ACCEPTED','2025-01-31 22:55:12'),(7,'5566778899','2233445566','PENDING','2025-01-31 23:50:12'),(8,'6677889900','3344556677','BLOCKED','2025-01-31 23:34:12'),(9,'7788990011','4455667788','REJECTED','2025-01-31 23:29:12'),(10,'8899001122','5566778899','ACCEPTED','2025-01-31 22:54:12'),(11,'3344556677','1234567890','ACCEPTED','2025-01-31 23:15:12'),(12,'2233445566','1234567890','ACCEPTED','2025-01-31 23:40:12'),(13,'7788990011','1234567890','ACCEPTED','2025-01-31 23:44:12'),(14,'5566778899','1234567890','ACCEPTED','2025-01-31 22:53:12');
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
  `file_type` varchar(50) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (1,'document.pdf','PDF'),(2,'image.png','PNG'),(3,'song.mp3','MP3'),(4,'video.mp4','MP4'),(5,'presentation.pptx','PPTX'),(6,'spreadsheet.xlsx','XLSX'),(7,'code.zip','ZIP'),(8,'photo.jpg','JPG'),(9,'audio.mp3','MP3'),(10,'report.pdf','PDF');
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
INSERT INTO `group_members` VALUES (1,'0987654321'),(2,'0987654321'),(1,'1122334455'),(3,'1122334455'),(1,'1234567890'),(2,'1234567890'),(3,'1234567890'),(4,'1234567890'),(5,'1234567890'),(6,'1234567890'),(7,'1234567890'),(8,'1234567890'),(2,'2233445566'),(4,'2233445566'),(3,'3344556677'),(5,'3344556677');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'Developers','1234567890','pictures\\1_group_pic.jpg','2025-01-31 23:29:12'),(2,'Travel Buddies','0987654321','pictures\\2_group_pic.jpg','2025-01-31 23:41:12'),(3,'Book Club','1122334455','pictures\\3_group_pic.jpg','2025-01-31 23:06:12'),(4,'Sports Fans','2233445566','pictures\\4_group_pic.jpg','2025-01-31 23:40:12'),(5,'Music Lovers','3344556677','pictures\\5_group_pic.jpg','2025-01-31 23:08:12'),(6,'Foodies','4455667788','pictures\\6_group_pic.jpg','2025-01-31 22:53:12'),(7,'Artists','5566778899','pictures\\7_group_pic.jpg','2025-01-31 23:11:12'),(8,'Gamers','6677889900','pictures\\8_group_pic.jpg','2025-01-31 23:24:12'),(9,'Fashionistas','7788990011','pictures\\9_group_pic.jpg','2025-01-31 23:40:12'),(10,'Photographers','8899001122','pictures\\10_group_pic.jpg','2025-01-31 23:15:12');
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
  `font_color` varchar(7) NOT NULL DEFAULT '#000000',
  `is_bold` tinyint(1) NOT NULL DEFAULT '0',
  `is_italic` tinyint(1) NOT NULL DEFAULT '0',
  `text_background_color` varchar(255) NOT NULL DEFAULT '#FFFFFF',
  `time_stand` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `emoji` varchar(255) DEFAULT NULL,
  `isRead` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `group_id` (`group_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`phone_number`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  CONSTRAINT `messages_ibfk_3` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'1234567890','PRIVATE','0987654321',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,0),(2,'0987654321','PRIVATE','1234567890',NULL,'Hi John, I\'m good!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,0),(3,'1122334455','GROUP',NULL,3,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1),(4,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1),(5,'3344556677','GROUP',NULL,5,'Check out this new song!',3,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL,1),(6,'1234567890','PRIVATE','7788990011',NULL,'You can customize the ListCell items of your ListView by setting the background to transparent. You need to adjust the cell factory for the ListView to remove the background from individual cells.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-21 18:18:08',NULL,0),(7,'1234567890','PRIVATE','7788990011',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-07-13 06:13:33',NULL,0),(8,'1234567890','PRIVATE','7788990011',NULL,'To remove the white background of the ListView cells in JavaFX, you need to apply a transparent background not just to the ListView itself but also to its individual ListCell elements. It looks like the ListView background is already set to transparent, but the cell background might still be white.\n\nHere’s how to fix that:',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-12-28 19:13:24',NULL,0),(9,'7788990011','PRIVATE','1234567890',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-05-15 09:26:22',NULL,0),(10,'7788990011','PRIVATE','1234567890',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-11-13 08:31:40',NULL,0),(11,'7788990011','PRIVATE','1234567890',NULL,'You also have CSS rules in your stylesheet. To ensure that there is no unwanted white background on buttons and the ListView, you can adjust these styles.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-25 17:19:13',NULL,0),(12,'1234567890','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-07-19 14:01:07',NULL,1),(13,'1234567890','GROUP',NULL,4,'To remove the white background of the ListView cells in JavaFX, you need to apply a transparent background not just to the ListView itself but also to its individual ListCell elements. It looks like the ListView background is already set to transparent, but the cell background might still be white.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-01-17 13:07:59',NULL,1),(14,'1234567890','GROUP',NULL,4,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-08-03 03:36:35',NULL,1),(15,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-18 22:39:01',NULL,1),(16,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-24 04:25:21',NULL,1),(17,'3344556677','GROUP',NULL,4,'Check out this new song!',3,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-09-26 06:09:43',NULL,1),(18,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-12-31 12:32:34',NULL,1),(19,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-16 00:13:42',NULL,1),(20,'3344556677','GROUP',NULL,4,'Check out this new song!',3,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-12-13 06:30:49',NULL,1),(21,'1234567890','GROUP',NULL,4,'To remove the white background of the ListView cells in JavaFX, you need to apply a transparent background not just to the ListView itself but also to its individual ListCell elements. It looks like the ListView background is already set to transparent, but the cell background might still be white.',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-05-18 11:53:03',NULL,1),(22,'1234567890','GROUP',NULL,4,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-01-14 10:52:46',NULL,1),(23,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-01-20 21:44:43',NULL,1),(24,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-03-01 04:05:21',NULL,1),(25,'3344556677','GROUP',NULL,4,'Check out this new song!',3,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-08-25 04:12:35',NULL,1),(26,'1122334455','GROUP',NULL,4,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-01 04:46:54',NULL,1),(27,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-20 10:16:47',NULL,1),(28,'3344556677','GROUP',NULL,4,'Check out this new song!',3,14,'sans-serif','#000000',0,0,'#FFFFFF','2023-10-05 12:03:12',NULL,1);
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
  PRIMARY KEY (`notification_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'0987654321','You have a new message from John.','2025-01-31 22:50:12',0,'MESSAGE'),(2,'1234567890','Jane added you to the group \"Travel Buddies\".','2025-01-31 22:50:12',0,'MESSAGE'),(3,'1122334455','Alice sent you a contact request.','2025-01-31 22:50:12',0,'MESSAGE'),(4,'2233445566','Bob added you to the group \"Sports Fans\".','2025-01-31 22:50:12',0,'MESSAGE'),(5,'3344556677','Charlie sent you a file.','2025-01-31 22:50:12',0,'MESSAGE');
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
INSERT INTO `users` VALUES ('0987654321','Jane','Smith','jane.smith@example.com','pictures/0987654321_profile_pic.jpg','c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91','FEMALE','Canada','1992-05-15','Travel enthusiast','AWAY',5,'2025-01-31 22:50:12',0),('1122334455','Alice','Johnson','alice.j@example.com','pictures/1122334455_profile_pic.jpg','5efc2b017da4f7736d192a74dde5891369e0685d4d38f2a455b6fcdab282df9c','FEMALE','UK','1985-07-20','Book lover','BUSY',3,'2025-01-31 22:50:12',0),('1234567890','John','Doe','john.doe@example.com','pictures/1234567890_profile_pic.jpg','ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f','MALE','USA','1990-01-01','I love coding!','AVAILABLE',10,'2025-01-31 22:50:12',0),('2233445566','Bob','Brown','bob.b@example.com','pictures/2233445566_profile_pic.jpg','a20aff106fe011d5dd696e3b7105200ff74331eeb8e865bb80ebd82b12665a07','MALE','Australia','1988-11-30','Sports fan','BUSY',8,'2025-01-31 22:50:12',1),('3344556677','Charlie','Davis','charlie.d@example.com','pictures/3344556677_profile_pic.jpg','28e91b84bd4ac1d95d81b4510777d2b12f3dffa848bb6e219a42f98cdfa06d7d','MALE','Germany','1995-03-25','Music producer','AWAY',2,'2025-01-31 22:50:12',0),('4455667788','David','Wilson','david.w@example.com','pictures/4455667788_profile_pic.jpg','f6537a5a2f097921d1d1ab410facd30c4356da7326783c2f9ed29f093852cfe2','MALE','France','1993-09-12','Foodie','AVAILABLE',7,'2025-01-31 22:50:12',0),('5566778899','Eva','Martinez','eva.m@example.com','pictures/5566778899_profile_pic.jpg','0cd1a34f93572bb80a5bf493d220bb881030335ec17a4e358c6da73a1ec30b4a','FEMALE','Spain','1991-04-18','Artist','OFFLINE',4,'2025-01-31 22:50:12',0),('6677889900','Frank','Garcia','frank.g@example.com','pictures/6677889900_profile_pic.jpg','92c25c70925b616b031e8b7a9ecbbc82c4b151e6ba72b89feb8bc3622b2f2a5d','MALE','Mexico','1987-12-22','Gamer','BUSY',6,'2025-01-31 22:50:12',0),('7788990011','Grace','Lee','grace.l@example.com','pictures/7788990011_profile_pic.jpg','e6e7b64fead0dd9a88d009b31679e4eac56aad87447b7f65e6ee87914a071d48','FEMALE','South Korea','1994-08-05','Fashionista','AVAILABLE',9,'2025-01-31 22:50:12',0),('8899001122','Henry','Taylor','henry.t@example.com','pictures/8899001122_profile_pic.jpg','31457e06e2adb2178358e9fc6705e0b6f37e9b6ec9456e8890d28f292be9adc4','MALE','USA','1989-06-30','Photographer','AWAY',3,'2025-01-31 22:50:12',0);
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

-- Dump completed on 2025-02-07  2:14:27
