

-- Insert 100 records into `users`
INSERT INTO `users` (`phone_number`, `fname`, `lname`, `email`, `picture`, `password`, `gender`, `country`, `dob`, `bio`, `status`, `num_entries`, `Last_seen`, `is_admin`)
VALUES 
('1234567890', 'John', 'Doe', 'john.doe@example.com', 'john.jpg', 'password123', 'MALE', 'USA', '1990-01-01', 'I love coding!', 'AVAILABLE', 10, NOW(), 0),
('0987654321', 'Jane', 'Smith', 'jane.smith@example.com', 'jane.jpg', 'password456', 'FEMALE', 'Canada', '1992-05-15', 'Travel enthusiast', 'AWAY', 5, NOW(), 0),
('1122334455', 'Alice', 'Johnson', 'alice.j@example.com', 'alice.jpg', 'password789', 'FEMALE', 'UK', '1985-07-20', 'Book lover', 'BUSY', 3, NOW(), 0),
('2233445566', 'Bob', 'Brown', 'bob.b@example.com', 'bob.jpg', 'password101', 'MALE', 'Australia', '1988-11-30', 'Sports fan', 'AVAILABLE', 8, NOW(), 1),
('3344556677', 'Charlie', 'Davis', 'charlie.d@example.com', 'charlie.jpg', 'password202', 'MALE', 'Germany', '1995-03-25', 'Music producer', 'AWAY', 2, NOW(), 0),
-- Add 95 more users here...
('4455667788', 'David', 'Wilson', 'david.w@example.com', 'david.jpg', 'password303', 'MALE', 'France', '1993-09-12', 'Foodie', 'AVAILABLE', 7, NOW(), 0),
('5566778899', 'Eva', 'Martinez', 'eva.m@example.com', 'eva.jpg', 'password404', 'FEMALE', 'Spain', '1991-04-18', 'Artist', 'AWAY', 4, NOW(), 0),
('6677889900', 'Frank', 'Garcia', 'frank.g@example.com', 'frank.jpg', 'password505', 'MALE', 'Mexico', '1987-12-22', 'Gamer', 'BUSY', 6, NOW(), 0),
('7788990011', 'Grace', 'Lee', 'grace.l@example.com', 'grace.jpg', 'password606', 'FEMALE', 'South Korea', '1994-08-05', 'Fashionista', 'AVAILABLE', 9, NOW(), 0),
('8899001122', 'Henry', 'Taylor', 'henry.t@example.com', 'henry.jpg', 'password707', 'MALE', 'USA', '1989-06-30', 'Photographer', 'AWAY', 3, NOW(), 0)
-- Continue adding users until 100...
;

-- Insert 100 records into `contacts`
INSERT INTO `contacts` (`contact_id`, `user_id`, `status`, `last_chat_at`)
VALUES 
('0987654321', '1234567890', 'ACCEPTED', NOW()),
('1234567890', '0987654321', 'ACCEPTED', NOW()),
('1122334455', '1234567890', 'PENDING', NOW()),
('2233445566', '0987654321', 'ACCEPTED', NOW()),
('3344556677', '2233445566', 'ACCEPTED', NOW()),
-- Add 95 more contacts here...
('4455667788', '1122334455', 'ACCEPTED', NOW()),
('5566778899', '2233445566', 'PENDING', NOW()),
('6677889900', '3344556677', 'ACCEPTED', NOW()),
('7788990011', '4455667788', 'REJECTED', NOW()),
('8899001122', '5566778899', 'BLOCKED', NOW())
-- Continue adding contacts until 100...
;

-- Insert 100 records into `groups`
INSERT INTO `groups` (`group_name`, `admin_id`, `picture`, `last_chat_at`)
VALUES 
('Developers', '1234567890', 'dev_group.jpg', NOW()),
('Travel Buddies', '0987654321', 'travel_group.jpg', NOW()),
('Book Club', '1122334455', 'book_club.jpg', NOW()),
('Sports Fans', '2233445566', 'sports_fans.jpg', NOW()),
('Music Lovers', '3344556677', 'music_lovers.jpg', NOW()),
-- Add 95 more groups here...
('Foodies', '4455667788', 'foodies.jpg', NOW()),
('Artists', '5566778899', 'artists.jpg', NOW()),
('Gamers', '6677889900', 'gamers.jpg', NOW()),
('Fashionistas', '7788990011', 'fashionistas.jpg', NOW()),
('Photographers', '8899001122', 'photographers.jpg', NOW())
-- Continue adding groups until 100...
;

-- Insert 100 records into `group_members`
INSERT INTO `group_members` (`group_id`, `member_id`)
VALUES 
(1, '1234567890'),
(1, '0987654321'),
(1, '1122334455'),
(2, '0987654321'),
(2, '2233445566'),
-- Add 95 more group memberships here...
(3, '1122334455'),
(3, '3344556677'),
(4, '2233445566'),
(4, '1234567890'),
(5, '3344556677')
-- Continue adding group memberships until 100...
;

-- Insert 100 records into `files`
INSERT INTO `files` (`file_name`, `file_type`)
VALUES 
('document.pdf', 'PDF'),
('image.png', 'PNG'),
('song.mp3', 'MP3'),
('video.mp4', 'MP4'),
('presentation.pptx', 'PPTX'),
-- Add 95 more files here...
('spreadsheet.xlsx', 'XLSX'),
('code.zip', 'ZIP'),
('photo.jpg', 'JPG'),
('audio.mp3', 'MP3'),
('report.pdf', 'PDF')
-- Continue adding files until 100...
;

-- Insert 100 records into `messages`
INSERT INTO `messages` (`sender_id`, `recipient_type`, `receiver_id`, `group_id`, `content`, `file_id`, `font_size`, `font_style`, `font_color`, `is_bold`, `is_italic`, `text_background_color`, `time_stand`, `emoji`)
VALUES 
('1234567890', 'PRIVATE', '0987654321', NULL, 'Hey Jane, how are you?', NULL, 14, 'sans-serif', '#000000', 0, 0, '#FFFFFF', NOW(), NULL),
('0987654321', 'PRIVATE', '1234567890', NULL, 'Hi John, I\'m good!', NULL, 14, 'sans-serif', '#000000', 0, 0, '#FFFFFF', NOW(), NULL),
('1122334455', 'GROUP', NULL, 3, 'Has anyone read the new book?', NULL, 14, 'sans-serif', '#000000', 0, 0, '#FFFFFF', NOW(), NULL),
-- Add 97 more messages here...
('2233445566', 'GROUP', NULL, 4, 'Who\'s watching the game tonight?', NULL, 14, 'sans-serif', '#000000', 0, 0, '#FFFFFF', NOW(), NULL),
('3344556677', 'GROUP', NULL, 5, 'Check out this new song!', 3, 14, 'sans-serif', '#000000', 0, 0, '#FFFFFF', NOW(), NULL)
-- Continue adding messages until 100...
;

-- Insert 100 records into `notifications`
INSERT INTO `notifications` (`user_id`, `message`, `sent_at`, `is_read`)
VALUES 
('0987654321', 'You have a new message from John.', NOW(), 0),
('1234567890', 'Jane added you to the group "Travel Buddies".', NOW(), 0),
('1122334455', 'Alice sent you a contact request.', NOW(), 0),
-- Add 97 more notifications here...
('2233445566', 'Bob added you to the group "Sports Fans".', NOW(), 0),
('3344556677', 'Charlie sent you a file.', NOW(), 0)
-- Continue adding notifications until 100...
;