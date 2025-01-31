
-- Insert data into users table
INSERT INTO `users` (`phone_number`, `fname`, `lname`, `email`, `picture`, `password`, `gender`, `country`, `dob`, `bio`, `status`, `num_entries`, `Last_seen`, `is_admin`)
VALUES
('754-570-0757', 'Adam', 'Oconnell', 'millsjose@lamb.com', 'https://dummyimage.com/234x600', ')NBXgVhO5a', 'MALE', 'Serbia', '1983-11-03', 'Impact citizen attorney game my she goal. Travel campaign trip sister theory.', 'BUSY', 166, '2023-09-05 02:07:12', FALSE),
('(734)329-9359', 'Tyler', 'Thompson', 'owalsh@yahoo.com', 'https://placekitten.com/334/983', 'Y2SIWLcf$g', 'FEMALE', 'Jamaica', '1989-01-22', 'Price radio certainly fast head tell start. On build throughout available meet business.
Point operation change surface thank like. Worker degree future. Seek right ever for method.', 'AVAILABLE', 418, '2020-09-13 03:55:15', FALSE),
('001-605-033-8663', 'Megan', 'Griffin', 'williamstimothy@jones.org', 'https://dummyimage.com/575x157', '!)6$mVCB*w', 'FEMALE', 'Mongolia', '1992-07-13', 'Expert top theory improve. Nice chair range dog improve pattern add. South teacher leg tough building room.
Begin game specific article. Management former drive enough.', 'AWAY', 892, '2020-01-09 15:24:20', FALSE);

-- Insert data into contacts table
INSERT INTO `contacts` (`contact_id`, `user_id`, `status`, `last_chat_at`)
VALUES
('754-570-0757', '(734)329-9359', 'ACCEPTED', '2023-05-01 12:05:00'),
('(734)329-9359', '001-605-033-8663', 'PENDING', '2023-07-21 08:15:00'),
('001-605-033-8663', '754-570-0757', 'REJECTED', '2023-06-25 19:30:00');

-- Insert data into groups table
INSERT INTO `groups` (`group_name`, `admin_id`, `picture`, `last_chat_at`)
VALUES
('Travel Enthusiasts', '754-570-0757', 'https://dummyimage.com/234x600', '2023-09-05 02:07:12'),
('Tech Innovators', '(734)329-9359', 'https://placekitten.com/334/983', '2023-07-21 08:15:00');

-- Insert data into group_members table
INSERT INTO `group_members` (`group_id`, `member_id`)
VALUES
(1, '754-570-0757'),
(1, '(734)329-9359'),
(2, '(734)329-9359'),
(2, '001-605-033-8663');

-- Insert data into files table
INSERT INTO `files` (`file_name`, `file_type`)
VALUES
('trip_plan.pdf', 'application/pdf'),
('innovation_discussion.docx', 'application/msword');

-- Insert data into messages table
INSERT INTO `messages` (`sender_id`, `recipient_type`, `receiver_id`, `group_id`, `content`, `file_id`, `font_size`, `font_color`, `is_bold`, `is_italic`, `text_background_color`, `time_stand`, `emoji`)
VALUES
('754-570-0757', 'PRIVATE', '(734)329-9359', NULL, 'Hey, let’s plan the trip!', NULL, 14, '#000000', FALSE, FALSE, '#FFFFFF', '2023-09-05 02:07:12', '😊'),
('(734)329-9359', 'GROUP', NULL, 1, 'Excited for the trip to Nuweiba!', NULL, 14, '#000000', TRUE, FALSE, '#FFFFFF', '2023-07-21 08:15:00', '🌍'),
('001-605-033-8663', 'GROUP', NULL, 2, 'Looking forward to the new tech innovations!', NULL, 14, '#000000', FALSE, TRUE, '#FFFFFF', '2023-07-22 10:00:00', '💻');

-- Insert data into notifications table
INSERT INTO `notifications` (`user_id`, `message`, `sent_at`, `is_read`)
VALUES
('754-570-0757', 'Your trip has been approved!', '2023-09-05 02:07:12', TRUE),
('(734)329-9359', 'Your tech group discussion has a new message.', '2023-07-21 08:15:00', FALSE),
('001-605-033-8663', 'New file uploaded to the group.', '2023-07-22 10:00:00', FALSE);
