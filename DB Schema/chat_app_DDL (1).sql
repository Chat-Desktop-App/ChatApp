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
  `last_chat_at` datetime DEFAULT NULL,
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
INSERT INTO `contacts` VALUES (1,'0987654321','1234567890','ACCEPTED','2025-01-31 22:50:12'),(2,'1234567890','0987654321','ACCEPTED','2025-01-31 22:50:12'),(3,'1122334455','1234567890','PENDING','2025-01-31 22:50:12'),(4,'2233445566','0987654321','ACCEPTED','2025-01-31 22:50:12'),(5,'3344556677','2233445566','ACCEPTED','2025-01-31 22:50:12'),(6,'4455667788','1122334455','ACCEPTED','2025-01-31 22:50:12'),(7,'5566778899','2233445566','PENDING','2025-01-31 22:50:12'),(8,'6677889900','3344556677','ACCEPTED','2025-01-31 22:50:12'),(9,'7788990011','4455667788','REJECTED','2025-01-31 22:50:12'),(10,'8899001122','5566778899','BLOCKED','2025-01-31 22:50:12'),(11,'3344556677','1234567890','BLOCKED','2025-01-31 22:50:12'),(12,'2233445566','1234567890','ACCEPTED','2025-01-31 22:50:12'),(13,'7788990011','1234567890','ACCEPTED','2025-01-31 22:50:12'),(14,'5566778899','1234567890','ACCEPTED','2025-01-31 22:50:12');
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
INSERT INTO `group_members` VALUES (1,'0987654321'),(2,'0987654321'),(1,'1122334455'),(3,'1122334455'),(1,'1234567890'),(4,'1234567890'),(2,'2233445566'),(4,'2233445566'),(3,'3344556677'),(5,'3344556677');
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
  `last_chat_at` datetime DEFAULT NULL,
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
INSERT INTO `groups` VALUES (1,'Developers','1234567890','dev_group.jpg','2025-01-31 22:50:12'),(2,'Travel Buddies','0987654321','travel_group.jpg','2025-01-31 22:50:12'),(3,'Book Club','1122334455','book_club.jpg','2025-01-31 22:50:12'),(4,'Sports Fans','2233445566','sports_fans.jpg','2025-01-31 22:50:12'),(5,'Music Lovers','3344556677','music_lovers.jpg','2025-01-31 22:50:12'),(6,'Foodies','4455667788','foodies.jpg','2025-01-31 22:50:12'),(7,'Artists','5566778899','artists.jpg','2025-01-31 22:50:12'),(8,'Gamers','6677889900','gamers.jpg','2025-01-31 22:50:12'),(9,'Fashionistas','7788990011','fashionistas.jpg','2025-01-31 22:50:12'),(10,'Photographers','8899001122','photographers.jpg','2025-01-31 22:50:12');
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
  PRIMARY KEY (`message_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `group_id` (`group_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`phone_number`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`),
  CONSTRAINT `messages_ibfk_3` FOREIGN KEY (`file_id`) REFERENCES `files` (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'1234567890','PRIVATE','0987654321',NULL,'Hey Jane, how are you?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL),(2,'0987654321','PRIVATE','1234567890',NULL,'Hi John, I\'m good!',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL),(3,'1122334455','GROUP',NULL,3,'Has anyone read the new book?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL),(4,'2233445566','GROUP',NULL,4,'Who\'s watching the game tonight?',NULL,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL),(5,'3344556677','GROUP',NULL,5,'Check out this new song!',3,14,'sans-serif','#000000',0,0,'#FFFFFF','2025-01-31 22:50:12',NULL);
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
INSERT INTO `notifications` VALUES (1,'0987654321','You have a new message from John.','2025-01-31 22:50:12',0),(2,'1234567890','Jane added you to the group \"Travel Buddies\".','2025-01-31 22:50:12',0),(3,'1122334455','Alice sent you a contact request.','2025-01-31 22:50:12',0),(4,'2233445566','Bob added you to the group \"Sports Fans\".','2025-01-31 22:50:12',0),(5,'3344556677','Charlie sent you a file.','2025-01-31 22:50:12',0);
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
  PRIMARY KEY (`phone_number`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('0987654321','Jane','Smith','jane.smith@example.com','jane.jpg','password456','FEMALE','Canada','1992-05-15','Travel enthusiast','AWAY',5,'2025-01-31 22:50:12',0),('1122334455','Alice','Johnson','alice.j@example.com','alice.jpg','password789','FEMALE','UK','1985-07-20','Book lover','BUSY',3,'2025-01-31 22:50:12',0),('1234567890','John','Doe','john.doe@example.com','john.jpg','password123','MALE','USA','1990-01-01','I love coding!','AVAILABLE',10,'2025-01-31 22:50:12',0),('2233445566','Bob','Brown','bob.b@example.com','bob.jpg','password101','MALE','Australia','1988-11-30','Sports fan','BUSY',8,'2025-01-31 22:50:12',1),('3344556677','Charlie','Davis','charlie.d@example.com','charlie.jpg','password202','MALE','Germany','1995-03-25','Music producer','AWAY',2,'2025-01-31 22:50:12',0),('4455667788','David','Wilson','david.w@example.com','david.jpg','password303','MALE','France','1993-09-12','Foodie','AVAILABLE',7,'2025-01-31 22:50:12',0),('5566778899','Eva','Martinez','eva.m@example.com','eva.jpg','password404','FEMALE','Spain','1991-04-18','Artist','OFFLINE',4,'2025-01-31 22:50:12',0),('6677889900','Frank','Garcia','frank.g@example.com','frank.jpg','password505','MALE','Mexico','1987-12-22','Gamer','BUSY',6,'2025-01-31 22:50:12',0),('7788990011','Grace','Lee','grace.l@example.com','grace.jpg','password606','FEMALE','South Korea','1994-08-05','Fashionista','AVAILABLE',9,'2025-01-31 22:50:12',0),('8899001122','Henry','Taylor','henry.t@example.com','henry.jpg','password707','MALE','USA','1989-06-30','Photographer','AWAY',3,'2025-01-31 22:50:12',0);
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

-- Dump completed on 2025-02-04 10:56:59
