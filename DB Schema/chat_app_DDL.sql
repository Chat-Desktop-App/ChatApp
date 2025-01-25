USE chat_app;
CREATE TABLE `users`(
    `phone_number` VARCHAR(15),
    `fname` VARCHAR(100) NOT NULL,
    `lname` VARCHAR(100) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `picture` VARCHAR(255),
    `password` VARCHAR(255) NOT NULL,
    `gender` ENUM('FEMALE','MALE') NOT NULL,
    `country` VARCHAR(255) NOT NULL,
    `dob` DATE NOT NULL,
    `bio` TEXT ,
    `status` ENUM('AVAILABLE','AWAY','BUSY') NOT NULL,
    `num_entries` BIGINT NOT NULL,
    `Last_seen` DATETIME NOT NULL,
    `is_admin` BOOLEAN NOT NULL,
    PRIMARY KEY(`phone_number`)
);

CREATE TABLE `contacts`(
    `contact_user_id` INT AUTO_INCREMENT PRIMARY KEY,
    `contact_id` VARCHAR(15) NOT NULL,
    `user_id` VARCHAR(15) NOT NULL,
    `status` ENUM('PENDING','ACCEPTED','REJECTED') NOT NULL,
    FOREIGN KEY (`contact_id`) REFERENCES `users` (`phone_number`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`phone_number`),
    CHECK (`contact_id` != `user_id`)
);

CREATE TABLE `groups`(
    `group_id` INT AUTO_INCREMENT PRIMARY KEY,
    `group_name` VARCHAR(255) NOT NULL,
    `admin_id` VARCHAR(255) NOT NULL,
    `picture` TEXT NOT NULL,
    FOREIGN KEY (`admin_id`) REFERENCES `users` (`phone_number`)
);

CREATE TABLE `group_members`(
    `group_id` INT NOT NULL,
    `member_id` VARCHAR(15) NOT NULL,
    PRIMARY KEY(`group_Id`,`member_id`),
    FOREIGN KEY(`group_id`) REFERENCES `groups` (`group_id`),
    FOREIGN KEY(`member_id`) REFERENCES `users` (`phone_number`)
);

CREATE TABLE `files`(
    `file_id` INT AUTO_INCREMENT PRIMARY KEY,
    `file_name` VARCHAR(255) NOT NULL,
    `file_type`  VARCHAR(50) NOT NULL
);

CREATE TABLE `messages`(
    `message_id` INT AUTO_INCREMENT PRIMARY KEY,
    `sender_id` VARCHAR(15) NOT NULL,
    `recipient_type` ENUM('PRIVATE','GROUP') NOT NULL,
    `receiver_id` VARCHAR(15),
    `group_id` INT,
    `content` LONGTEXT NOT NULL,
    `file_id` INT,
    `font_size` INT NOT NULL DEFAULT 14,
    `font_style` VARCHAR(50) NOT NULL DEFAULT 'sans-serif',
    `font_color` VARCHAR(7) NOT NULL DEFAULT '#000000',
    `is_bold` BOOLEAN NOT NULL DEFAULT 0,
    `is_italic` BOOLEAN NOT NULL DEFAULT 0,
    `text_background_color` VARCHAR(255) NOT NULL DEFAULT '#FFFFFF', 
    `time_stand` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    `emoji` VARCHAR(255),
	FOREIGN KEY (`receiver_id`) REFERENCES `users`(`phone_number`),
    FOREIGN KEY (`group_id`) REFERENCES `groups`(`group_id`),
    FOREIGN KEY (`file_id`) REFERENCES `files`(`file_id`)
);

CREATE TABLE `notifications`(
    `notification_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(15) NOT NULL,
    `message` TEXT NOT NULL,
    `sent_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `is_read` BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`phone_number`)
    
);







