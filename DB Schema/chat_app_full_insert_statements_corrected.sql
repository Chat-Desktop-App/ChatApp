-- Insert data into users table
INSERT INTO `users` (`phone_number`, `fname`, `lname`, `email`, `picture`, `password`, `gender`, `country`, `dob`, `bio`, `status`, `num_entries`, `Last_seen`, `is_admin`)
VALUES
('1234567890', 'John', 'Doe', 'john.doe@example.com', 'profile1.jpg', 'hashed_password1', 'MALE', 'USA', '1990-01-01', 'Hello, I am John!', 'AVAILABLE', 150, NOW(), FALSE),
('9876543210', 'Jane', 'Smith', 'jane.smith@example.com', 'profile2.jpg', 'hashed_password2', 'FEMALE', 'Canada', '1985-05-15', 'This is Jane’s bio.', 'AWAY', 220, NOW(), FALSE),
('1122334455', 'Alice', 'Johnson', 'alice.johnson@example.com', 'profile3.jpg', 'hashed_password3', 'FEMALE', 'UK', '1992-07-23', 'I am Alice, nice to meet you!', 'BUSY', 180, NOW(), TRUE);

-- Insert data into contacts table
INSERT INTO `contacts` (`contact_id`, `user_id`, `status`, `last_chat_at`)
VALUES
('1234567890', '9876543210', 'ACCEPTED', NOW()),
('9876543210', '1122334455', 'PENDING', NULL),
('1122334455', '1234567890', 'BLOCKED', '2025-01-01 12:00:00');

-- Insert data into groups table
INSERT INTO `groups` (`group_name`, `admin_id`, `picture`, `last_chat_at`)
VALUES
('Tech Group', '1234567890', 'group1.jpg', NOW()),
('Travel Enthusiasts', '9876543210', 'group2.jpg', NOW());

-- Insert data into group_members table
INSERT INTO `group_members` (`group_id`, `member_id`)
VALUES
(1, '1234567890'),
(1, '9876543210'),
(2, '9876543210'),
(2, '1122334455');

-- Insert data into files table
INSERT INTO `files` (`file_name`, `file_type`)
VALUES
('document1.pdf', 'PDF'),
('image1.png', 'PNG');

-- Insert data into messages table
INSERT INTO `messages` (`sender_id`, `recipient_type`, `receiver_id`, `group_id`, `content`, `file_id`, `font_size`, `font_style`, `font_color`, `is_bold`, `is_italic`, `text_background_color`, `time_stand`, `emoji`)
VALUES
('1234567890', 'PRIVATE', '9876543210', NULL, 'Hello, Jane!', NULL, 14, 'sans-serif', '#000000', 0, 0, '#FFFFFF', NOW(), NULL),
('9876543210', 'GROUP', NULL, 1, 'Welcome to the Tech Group!', 1, 16, 'Arial', '#FF5733', 1, 0, '#EFEFEF', NOW(), '😊');

-- Insert data into notifications table
INSERT INTO `notifications` (`user_id`, `message`, `sent_at`, `is_read`)
VALUES
('1234567890', 'You have a new message from Jane.', NOW(), FALSE),
('9876543210', 'Your group “Tech Group” has new activity.', NOW(), TRUE);
