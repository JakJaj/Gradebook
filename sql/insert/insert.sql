USE `grade_book`;

-- Insert data into Users
INSERT INTO Users (email, password, pesel, salt, role) VALUES
('john.doe@example.com', 'hashed_password_1', '92030512345', 'salt_1', 'TEACHER'),
('jane.smith@example.com', 'hashed_password_2', '85041567890', 'salt_2', 'TEACHER'),
('mike.brown@example.com', 'hashed_password_3', '78060543210', 'salt_3', 'PARENT'),
('susan.white@example.com', 'hashed_password_4', '92081234567', 'salt_4', 'STUDENT'),
('lucy.green@example.com', 'hashed_password_5', '03041109876', 'salt_5', 'STUDENT'),
('emily.johnson@example.com', 'hashed_password_6', '77030567891', 'salt_6', 'TEACHER'),
('david.lee@example.com', 'hashed_password_7', '65021523456', 'salt_7', 'PARENT'),
('olivia.adams@example.com', 'hashed_password_8', '10120554321', 'salt_8', 'STUDENT'),
('noah.evans@example.com', 'hashed_password_9', '06090112345', 'salt_9', 'STUDENT'),
('mia.baker@example.com', 'hashed_password_10', '09071198765', 'salt_10', 'STUDENT'),
('ethan.morris@example.com', 'hashed_password_11', '95030512345', 'salt_11', 'TEACHER'),
('ava.martin@example.com', 'hashed_password_12', '86041567890', 'salt_12', 'TEACHER'),
('liam.thomas@example.com', 'hashed_password_13', '79060543210', 'salt_13', 'PARENT'),
('sophia.jackson@example.com', 'hashed_password_14', '93081234567', 'salt_14', 'STUDENT'),
('isabella.white@example.com', 'hashed_password_15', '04041109876', 'salt_15', 'STUDENT'),
('mason.johnson@example.com', 'hashed_password_16', '78030567891', 'salt_16', 'TEACHER'),
('logan.lee@example.com', 'hashed_password_17', '66021523456', 'salt_17', 'PARENT'),
('emma.adams@example.com', 'hashed_password_18', '11120554321', 'salt_18', 'STUDENT'),
('lucas.evans@example.com', 'hashed_password_19', '07090112345', 'salt_19', 'STUDENT'),
('harper.baker@example.com', 'hashed_password_20', '10071198765', 'salt_20', 'STUDENT'),
('jack.wilson@example.com', 'hashed_password_21', '92030512346', 'salt_21', 'TEACHER'),
('amelia.jones@example.com', 'hashed_password_22', '85041567891', 'salt_22', 'TEACHER'),
('oliver.brown@example.com', 'hashed_password_23', '78060543211', 'salt_23', 'PARENT'),
('ava.davis@example.com', 'hashed_password_24', '92081234568', 'salt_24', 'STUDENT'),
('sophia.miller@example.com', 'hashed_password_25', '03041109877', 'salt_25', 'STUDENT'),
('charlotte.moore@example.com', 'hashed_password_26', '77030567892', 'salt_26', 'TEACHER'),
('james.taylor@example.com', 'hashed_password_27', '65021523457', 'salt_27', 'PARENT'),
('mia.anderson@example.com', 'hashed_password_28', '10120554322', 'salt_28', 'STUDENT'),
('lucas.thomas@example.com', 'hashed_password_29', '06090112346', 'salt_29', 'STUDENT'),
('ella.jackson@example.com', 'hashed_password_30', '09071198766', 'salt_30', 'STUDENT'),
('william.harris@example.com', 'hashed_password_31', '75030512345', 'salt_31', 'PARENT'),
('henry.clark@example.com', 'hashed_password_32', '76041567890', 'salt_32', 'PARENT'),
('alexander.lewis@example.com', 'hashed_password_33', '77060543210', 'salt_33', 'PARENT'),
('benjamin.walker@example.com', 'hashed_password_34', '78030567891', 'salt_34', 'PARENT'),
('daniel.hall@example.com', 'hashed_password_35', '79021523456', 'salt_35', 'PARENT'),
('matthew.allen@example.com', 'hashed_password_36', '80030512345', 'salt_36', 'PARENT'),
('joseph.young@example.com', 'hashed_password_37', '81041567890', 'salt_37', 'PARENT'),
('samuel.hernandez@example.com', 'hashed_password_38', '82060543210', 'salt_38', 'PARENT'),
('christopher.king@example.com', 'hashed_password_39', '83030567891', 'salt_39', 'PARENT'),
('andrew.wright@example.com', 'hashed_password_40', '84021523456', 'salt_40', 'PARENT'),
('joshua.lopez@example.com', 'hashed_password_41', '85030512345', 'salt_41', 'PARENT'),
('ryan.hill@example.com', 'hashed_password_42', '86041567890', 'salt_42', 'PARENT'),
('nicholas.scott@example.com', 'hashed_password_43', '87060543210', 'salt_43', 'PARENT'),
('jonathan.green@example.com', 'hashed_password_44', '88030567891', 'salt_44', 'PARENT'),
('daniel.roberts@example.com', 'hashed_password_45', '89030512345', 'salt_45', 'TEACHER'),
('sarah.clark@example.com', 'hashed_password_46', '90041567890', 'salt_46', 'TEACHER'),
('michael.lewis@example.com', 'hashed_password_47', '91060543210', 'salt_47', 'TEACHER'),
('elizabeth.walker@example.com', 'hashed_password_48', '92030567891', 'salt_48', 'TEACHER'),
('james.hall@example.com', 'hashed_password_49', '93021523456', 'salt_49', 'TEACHER'),
('patricia.allen@example.com', 'hashed_password_50', '94030512345', 'salt_50', 'TEACHER'),
('john.doe.student@example.com', 'hashed_password_51', '05030512345', 'salt_51', 'STUDENT'),
('jane.smith.student@example.com', 'hashed_password_52', '06041567890', 'salt_52', 'STUDENT'),
('mike.brown.student@example.com', 'hashed_password_53', '07060543210', 'salt_53', 'STUDENT'),
('susan.white.student@example.com', 'hashed_password_54', '08081234567', 'salt_54', 'STUDENT'),
('lucy.green.student@example.com', 'hashed_password_55', '09041109876', 'salt_55', 'STUDENT'),
('emily.johnson.student@example.com', 'hashed_password_56', '10030567891', 'salt_56', 'STUDENT'),
('david.lee.student@example.com', 'hashed_password_57', '11021523456', 'salt_57', 'STUDENT'),
('olivia.adams.student@example.com', 'hashed_password_58', '12020554321', 'salt_58', 'STUDENT'),
('noah.evans.student@example.com', 'hashed_password_59', '13090112345', 'salt_59', 'STUDENT'),
('mia.baker.student@example.com', 'hashed_password_60', '14071198765', 'salt_60', 'STUDENT');

-- Insert data into Teachers
INSERT INTO Teachers (first_name, last_name, date_of_birth, date_of_employment, user_id) VALUES
('John', 'Doe', '1992-03-05', '2020-09-01', 1),
('Jane', 'Smith', '1985-04-15', '2018-01-15', 2),
('Emily', 'Johnson', '1977-03-05', '2015-08-15', 6),
('Ethan', 'Morris', '1995-03-05', '2021-09-01', 11),
('Ava', 'Martin', '1986-04-15', '2019-01-15', 12),
('Mason', 'Johnson', '1978-03-05', '2016-08-15', 16),
('Jack', 'Wilson', '1992-03-06', '2020-09-02', 21),
('Amelia', 'Jones', '1985-04-16', '2018-01-16', 22),
('Charlotte', 'Moore', '1977-03-06', '2015-08-16', 26),
('Daniel', 'Roberts', '1989-03-05', '2017-09-01', 45),
('Sarah', 'Clark', '1990-04-15', '2018-01-15', 46),
('Michael', 'Lewis', '1991-06-05', '2019-08-15', 47),
('Elizabeth', 'Walker', '1992-03-05', '2020-09-01', 48),
('James', 'Hall', '1993-02-15', '2021-01-15', 49),
('Patricia', 'Allen', '1994-03-05', '2022-08-15', 50);

-- Insert data into Parents
INSERT INTO Parents (first_name, last_name, user_id) VALUES
('Mike', 'Brown', 3),
('David', 'Lee', 7),
('Liam', 'Thomas', 13),
('Logan', 'Lee', 17),
('Oliver', 'Brown', 23),
('James', 'Taylor', 27),
('William', 'Harris', 31),
('Henry', 'Clark', 32),
('Alexander', 'Lewis', 33),
('Benjamin', 'Walker', 34),
('Daniel', 'Hall', 35),
('Matthew', 'Allen', 36),
('Joseph', 'Young', 37),
('Samuel', 'Hernandez', 38),
('Christopher', 'King', 39),
('Andrew', 'Wright', 40),
('Joshua', 'Lopez', 41),
('Ryan', 'Hill', 42),
('Nicholas', 'Scott', 43),
('Jonathan', 'Green', 44);

-- Insert data into Announcements
INSERT INTO Announcements (title, content, date_time, teacher_id) VALUES
('Welcome Back!', 'We are excited to start the new semester!', '2023-09-01', 1),
('Exam Schedule', 'The final exams will begin next week.', '2023-12-01', 2),
('Field Trip', 'We are planning a field trip to the science museum.', '2023-10-10', 1),
('Homework Reminder', 'Please submit your assignments by Friday.', '2023-11-20', 3),
('New Course', 'We are introducing a new course on Data Science.', '2023-09-15', 4),
('Holiday Notice', 'School will be closed for the holidays from Dec 20 to Jan 5.', '2023-12-15', 5);

-- Insert data into Courses
INSERT INTO Courses (course_name, teacher_id, description) VALUES
('Mathematics 101', 1, 'Introduction to Algebra and Geometry'),
('English Literature', 2, 'Study of classic and modern literature'),
('Physics 201', 1, 'Advanced Mechanics and Thermodynamics'),
('History of Art', 6, 'Exploration of art movements across centuries'),
('Chemistry Basics', 6, 'Introduction to organic and inorganic chemistry'),
('Data Science 101', 4, 'Introduction to Data Science and Machine Learning'),
('Biology 101', 5, 'Basics of Biology and Life Sciences');

-- Insert data into Classes
INSERT INTO Classes (class_name, teacher_id, start_year) VALUES
('Class A', 1, 2023),
('Class B', 2, 2022),
('Class C', 6, 2021),
('Class D', 2, 2020),
('Class E', 4, 2023),
('Class F', 5, 2022);

-- Insert data into Students
INSERT INTO Students (first_name, last_name, date_of_birth, city, street, house_number, class_id, user_id) VALUES
('Susan', 'White', '2012-08-12', 'New York', 'Maple Street', '12A', 1, 4),
('Lucy', 'Green', '2010-04-11', 'Boston', 'Oak Avenue', '34', 2, 5),
('Olivia', 'Adams', '2010-12-05', 'Chicago', 'Pine Street', '56', 3, 8),
('Noah', 'Evans', '2009-09-01', 'Seattle', 'Cedar Lane', '22B', 1, 9),
('Mia', 'Baker', '2011-07-11', 'Austin', 'Birch Road', '78C', 2, 10),
('Sophia', 'Jackson', '2011-08-12', 'San Francisco', 'Elm Street', '45D', 4, 14),
('Isabella', 'White', '2010-04-11', 'Los Angeles', 'Spruce Avenue', '67', 5, 15),
('Lucas', 'Evans', '2009-12-05', 'Houston', 'Fir Street', '89', 6, 19),
('Harper', 'Baker', '2011-09-01', 'Dallas', 'Willow Lane', '101', 4, 20),
('Ava', 'Davis', '2012-08-13', 'New York', 'Maple Street', '12B', 1, 24),
('Sophia', 'Miller', '2010-04-12', 'Boston', 'Oak Avenue', '35', 2, 25),
('Mia', 'Anderson', '2010-12-06', 'Chicago', 'Pine Street', '57', 3, 28),
('Lucas', 'Thomas', '2009-09-02', 'Seattle', 'Cedar Lane', '23B', 1, 29),
('Ella', 'Jackson', '2011-07-12', 'Austin', 'Birch Road', '79C', 2, 30),
('John', 'Doe', '2012-05-05', 'New York', 'Maple Street', '12C', 1, 51),
('Jane', 'Smith', '2010-06-15', 'Boston', 'Oak Avenue', '34B', 2, 52),
('Mike', 'Brown', '2010-07-05', 'Chicago', 'Pine Street', '56A', 3, 53),
('Susan', 'White', '2009-08-12', 'Seattle', 'Cedar Lane', '22C', 1, 54),
('Lucy', 'Green', '2011-09-11', 'Austin', 'Birch Road', '78D', 2, 55),
('Emily', 'Johnson', '2011-10-12', 'San Francisco', 'Elm Street', '45E', 4, 56),
('David', 'Lee', '2010-11-05', 'Los Angeles', 'Spruce Avenue', '67B', 5, 57),
('Olivia', 'Adams', '2009-12-05', 'Houston', 'Fir Street', '89A', 6, 58),
('Noah', 'Evans', '2011-01-01', 'Dallas', 'Willow Lane', '101B', 4, 59),
('Mia', 'Baker', '2012-02-13', 'New York', 'Maple Street', '12D', 1, 60);

-- Insert data into Students_Parents
INSERT INTO Students_Parents (student_id, parent_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 3),
(8, 4),
(9, 5),
(10, 6),
(11, 6),
(12, 6),
(13, 7),  
(14, 8),  
(1, 9),   
(2, 10),  
(3, 11),  
(4, 11),  
(5, 11),  
(6, 12),  
(7, 12),  
(8, 13),  
(9, 13),  
(10, 14), 
(11, 15), 
(12, 16), 
(13, 17), 
(14, 17); 

-- Insert data into Timetables
INSERT INTO Timetables (course_id, class_id, start_time, end_time, classroom_number, day_of_week) VALUES
(1, 1, '09:00:00', '10:30:00', 'Room 101', 1),  -- Mathematics 101, Class A, Monday
(2, 2, '11:00:00', '12:30:00', 'Room 102', 2),  -- English Literature, Class B, Tuesday
(3, 1, '13:00:00', '14:30:00', 'Room 103', 3),  -- Physics 201, Class A, Wednesday
(4, 2, '09:00:00', '10:30:00', 'Room 104', 4),  -- History of Art, Class B, Thursday
(5, 3, '10:00:00', '11:30:00', 'Room 105', 5),  -- Chemistry Basics, Class C, Friday
(6, 4, '14:00:00', '15:30:00', 'Room 106', 1),  -- Data Science 101, Class D, Monday
(7, 5, '08:00:00', '09:30:00', 'Room 107', 2),  -- Biology 101, Class E, Tuesday
(1, 6, '11:00:00', '12:30:00', 'Room 108', 3),  -- Mathematics 101, Class F, Wednesday
(2, 1, '09:00:00', '10:30:00', 'Room 109', 4),  -- English Literature, Class A, Thursday
(3, 2, '11:00:00', '12:30:00', 'Room 110', 5),  -- Physics 201, Class B, Friday
(4, 3, '13:00:00', '14:30:00', 'Room 111', 1),  -- History of Art, Class C, Monday
(5, 4, '09:00:00', '10:30:00', 'Room 112', 2),  -- Chemistry Basics, Class D, Tuesday
(6, 5, '11:00:00', '12:30:00', 'Room 113', 3),  -- Data Science 101, Class E, Wednesday
(7, 6, '13:00:00', '14:30:00', 'Room 114', 4),  -- Biology 101, Class F, Thursday
(1, 1, '08:00:00', '09:30:00', 'Room 115', 5),  -- Mathematics 101, Class A, Friday
(2, 2, '10:00:00', '11:30:00', 'Room 116', 1),  -- English Literature, Class B, Monday
(3, 3, '12:00:00', '13:30:00', 'Room 117', 2),  -- Physics 201, Class C, Tuesday
(4, 4, '14:00:00', '15:30:00', 'Room 118', 3),  -- History of Art, Class D, Wednesday
(5, 5, '09:00:00', '10:30:00', 'Room 119', 4),  -- Chemistry Basics, Class E, Thursday
(6, 6, '11:00:00', '12:30:00', 'Room 120', 5),  -- Data Science 101, Class F, Friday
(7, 1, '13:00:00', '14:30:00', 'Room 121', 1),  -- Biology 101, Class A, Monday
(1, 2, '08:00:00', '09:30:00', 'Room 122', 2),  -- Mathematics 101, Class B, Tuesday
(2, 3, '10:00:00', '11:30:00', 'Room 123', 3),  -- English Literature, Class C, Wednesday
(3, 4, '12:00:00', '13:30:00', 'Room 124', 4),  -- Physics 201, Class D, Thursday
(4, 5, '14:00:00', '15:30:00', 'Room 125', 5),  -- History of Art, Class E, Friday
(5, 6, '09:00:00', '10:30:00', 'Room 126', 1),  -- Chemistry Basics, Class F, Monday
(6, 1, '11:00:00', '12:30:00', 'Room 127', 2),  -- Data Science 101, Class A, Tuesday
(7, 2, '13:00:00', '14:30:00', 'Room 128', 3),  -- Biology 101, Class B, Wednesday
(1, 3, '08:00:00', '09:30:00', 'Room 129', 4),  -- Mathematics 101, Class C, Thursday
(2, 4, '10:00:00', '11:30:00', 'Room 130', 5),  -- English Literature, Class D, Friday
(3, 5, '12:00:00', '13:30:00', 'Room 131', 1),  -- Physics 201, Class E, Monday
(4, 6, '14:00:00', '15:30:00', 'Room 132', 2),  -- History of Art, Class F, Tuesday
(5, 1, '09:00:00', '10:30:00', 'Room 133', 3),  -- Chemistry Basics, Class A, Wednesday
(6, 2, '11:00:00', '12:30:00', 'Room 134', 4),  -- Data Science 101, Class B, Thursday
(7, 3, '13:00:00', '14:30:00', 'Room 135', 5);  -- Biology 101, Class C, Friday

-- Insert data into Grades
INSERT INTO Grades (course_id, mark, student_id, description, magnitude, date) VALUES
(1, 5, 1, 'Good performance in the test', 3, '2023-10-10'),  -- Susan White, Mathematics 101
(2, 6, 2, 'Excellent essay submission', 2, '2023-11-15'),  -- Lucy Green, English Literature
(3, 4, 3, 'Average performance in the quiz', 4, '2023-12-10'),  -- Olivia Adams, Physics 201
(4, 6, 4, 'Outstanding project work', 1, '2023-09-25'),  -- Noah Evans, History of Art
(1, 5, 5, 'Good performance in the test', 3, '2023-10-15'),  -- Mia Baker, Mathematics 101
(2, 6, 6, 'Excellent essay submission', 2, '2023-11-20'),  -- Sophia Jackson, English Literature
(3, 4, 7, 'Average performance in the quiz', 4, '2023-12-15'),  -- Isabella White, Physics 201
(4, 6, 8, 'Outstanding project work', 1, '2023-09-30'),  -- Lucas Evans, History of Art
(1, 4, 9, 'Needs improvement in problem-solving', 3, '2023-10-20'),  -- Harper Baker, Mathematics 101
(2, 5, 10, 'Good analysis in the essay', 2, '2023-11-25'),  -- Ava Davis, English Literature
(3, 3, 11, 'Below average performance in the quiz', 4, '2023-12-20'),  -- Sophia Miller, Physics 201
(4, 5, 12, 'Excellent presentation', 1, '2023-09-15'),  -- Mia Anderson, History of Art
(1, 6, 13, 'Outstanding performance in the test', 3, '2023-10-25'),  -- Lucas Thomas, Mathematics 101
(2, 4, 14, 'Needs more depth in essay', 2, '2023-11-30'),  -- Ella Jackson, English Literature
(3, 5, 15, 'Good understanding of concepts', 4, '2023-12-25'),  -- Susan White, Physics 201
(4, 6, 16, 'Excellent project work', 1, '2023-09-20'),  -- Lucy Green, History of Art
(1, 5, 17, 'Good performance in the test', 3, '2023-10-30'),  -- Olivia Adams, Mathematics 101
(2, 6, 18, 'Excellent essay submission', 2, '2023-11-10'),  -- Noah Evans, English Literature
(3, 4, 19, 'Average performance in the quiz', 4, '2023-12-05'),  -- Mia Baker, Physics 201
(4, 6, 10, 'Outstanding project work', 1, '2023-09-25'),  -- Sophia Jackson, History of Art
(1, 5, 11, 'Good performance in the test', 3, '2023-10-15'),  -- Isabella White, Mathematics 101
(2, 6, 12, 'Excellent essay submission', 2, '2023-11-20'),  -- Lucas Evans, English Literature
(3, 4, 13, 'Average performance in the quiz', 4, '2023-12-15'),  -- Harper Baker, Physics 201
(4, 6, 14, 'Outstanding project work', 1, '2023-09-30'),  -- Ava Davis, History of Art
(1, 4, 15, 'Needs improvement in problem-solving', 3, '2023-10-20'),  -- Sophia Miller, Mathematics 101
(2, 5, 16, 'Good analysis in the essay', 2, '2023-11-25'),  -- Mia Anderson, English Literature
(3, 3, 17, 'Below average performance in the quiz', 4, '2023-12-20'),  -- Lucas Thomas, Physics 201
(4, 5, 18, 'Excellent presentation', 1, '2023-09-15'),  -- Ella Jackson, History of Art
(1, 6, 19, 'Outstanding performance in the test', 3, '2023-10-25'),  -- Susan White, Mathematics 101
(2, 4, 10, 'Needs more depth in essay', 2, '2023-11-30'),  -- Lucy Green, English Literature
(3, 5, 11, 'Good understanding of concepts', 4, '2023-12-25'),  -- Olivia Adams, Physics 201
(4, 6, 12, 'Excellent project work', 1, '2023-09-20'),  -- Noah Evans, History of Art
(1, 5, 13, 'Good performance in the test', 3, '2023-10-30'),  -- Mia Baker, Mathematics 101
(2, 6, 14, 'Excellent essay submission', 2, '2023-11-10'),  -- Sophia Jackson, English Literature
(3, 4, 15, 'Average performance in the quiz', 4, '2023-12-05'),  -- Isabella White, Physics 201
(4, 6, 16, 'Outstanding project work', 1, '2023-09-25'),  -- Lucas Evans, History of Art
(1, 5, 17, 'Good performance in the test', 3, '2023-10-15'),  -- Harper Baker, Mathematics 101
(2, 6, 18, 'Excellent essay submission', 2, '2023-11-20'),  -- Ava Davis, English Literature
(3, 4, 19, 'Average performance in the quiz', 4, '2023-12-15'),  -- Sophia Miller, Physics 201
(4, 6, 10, 'Outstanding project work', 1, '2023-09-30');  -- Mia Anderson, History of Art

-- Insert data into Attendances
INSERT INTO Attendances (date_time, status, student_id, timetable_id) VALUES
('2023-09-10', 'Present', 1, 1),  -- Susan White, Mathematics 101, Class A
('2023-09-10', 'Absent', 2, 2),  -- Lucy Green, English Literature, Class B
('2023-09-11', 'Present', 3, 3),  -- Olivia Adams, Physics 201, Class A
('2023-09-11', 'Late', 4, 4),  -- Noah Evans, History of Art, Class B
('2023-09-12', 'Present', 5, 5),  -- Mia Baker, Chemistry Basics, Class C
('2023-09-12', 'Absent', 6, 6),  -- Sophia Jackson, Data Science 101, Class D
('2023-09-13', 'Present', 7, 7),  -- Isabella White, Biology 101, Class E
('2023-09-13', 'Late', 8, 8),  -- Lucas Evans, Mathematics 101, Class F
('2023-09-14', 'Present', 9, 9),  -- Harper Baker, English Literature, Class A
('2023-09-14', 'Absent', 10, 10),  -- Ava Davis, Physics 201, Class B
('2023-09-15', 'Present', 11, 11),  -- Sophia Miller, History of Art, Class C
('2023-09-15', 'Late', 12, 12),  -- Mia Anderson, Chemistry Basics, Class D
('2023-09-16', 'Present', 13, 13),  -- Lucas Thomas, Data Science 101, Class E
('2023-09-16', 'Absent', 14, 14),  -- Ella Jackson, Biology 101, Class F
('2023-09-17', 'Present', 15, 15),  -- Susan White, Mathematics 101, Class A
('2023-09-17', 'Late', 16, 16),  -- Lucy Green, English Literature, Class B
('2023-09-18', 'Present', 17, 17),  -- Olivia Adams, Physics 201, Class C
('2023-09-18', 'Absent', 18, 18),  -- Noah Evans, History of Art, Class D
('2023-09-19', 'Present', 19, 19),  -- Mia Baker, Chemistry Basics, Class E
('2023-09-19', 'Late', 20, 20),  -- Sophia Jackson, Data Science 101, Class F
('2023-09-20', 'Present', 21, 21),  -- Isabella White, Biology 101, Class A
('2023-09-20', 'Absent', 22, 22),  -- Lucas Evans, Mathematics 101, Class B
('2023-09-21', 'Present', 23, 23),  -- Harper Baker, English Literature, Class C
('2023-09-21', 'Late', 14, 24),  -- Ava Davis, Physics 201, Class D
('2023-09-22', 'Present', 15, 25),  -- Sophia Miller, History of Art, Class E
('2023-09-22', 'Absent', 16, 26),  -- Mia Anderson, Chemistry Basics, Class F
('2023-09-23', 'Present', 7, 27),  -- Lucas Thomas, Data Science 101, Class A
('2023-09-23', 'Late', 18, 28),  -- Ella Jackson, Biology 101, Class B
('2023-09-24', 'Present', 9, 29),  -- Susan White, Mathematics 101, Class C
('2023-09-24', 'Absent', 10, 30),  -- Lucy Green, English Literature, Class D
('2023-09-25', 'Present', 1, 31),  -- Olivia Adams, Physics 201, Class E
('2023-09-25', 'Late', 2, 32),  -- Noah Evans, History of Art, Class F
('2023-09-26', 'Present', 3, 33),  -- Mia Baker, Chemistry Basics, Class A
('2023-09-26', 'Absent', 4, 14),  -- Sophia Jackson, Data Science 101, Class B
('2023-09-27', 'Present', 5, 15),  -- Isabella White, Biology 101, Class C
('2023-09-27', 'Late', 6, 16),  -- Lucas Evans, Mathematics 101, Class D
('2023-09-28', 'Present', 7, 7),  -- Harper Baker, English Literature, Class E
('2023-09-28', 'Absent', 8, 8),  -- Ava Davis, Physics 201, Class F
('2023-09-29', 'Present', 9, 9),  -- Sophia Miller, History of Art, Class A
('2023-09-29', 'Late', 10, 10),  -- Mia Anderson, Chemistry Basics, Class B
('2023-09-30', 'Present', 11, 11),  -- Lucas Thomas, Data Science 101, Class C
('2023-09-30', 'Absent', 12, 22),  -- Ella Jackson, Biology 101, Class D
('2023-10-01', 'Present', 13, 23),  -- Susan White, Mathematics 101, Class E
('2023-10-01', 'Late', 14, 4),  -- Lucy Green, English Literature, Class F
('2023-10-02', 'Present', 15, 5),  -- Olivia Adams, Physics 201, Class A
('2023-10-02', 'Absent', 16, 6),  -- Noah Evans, History of Art, Class B
('2023-10-03', 'Present', 17, 7),  -- Mia Baker, Chemistry Basics, Class C
('2023-10-03', 'Late', 18, 8),  -- Sophia Jackson, Data Science 101, Class D
('2023-10-04', 'Present', 19, 19),  -- Isabella White, Biology 101, Class E
('2023-10-04', 'Absent', 20, 10),  -- Lucas Evans, Mathematics 101, Class F
('2023-10-05', 'Present', 21, 11),  -- Harper Baker, English Literature, Class A
('2023-10-05', 'Late', 22, 2),  -- Ava Davis, Physics 201, Class B
('2023-10-06', 'Present', 3, 13),  -- Sophia Miller, History of Art, Class C
('2023-10-06', 'Absent', 4, 4),  -- Mia Anderson, Chemistry Basics, Class D
('2023-10-07', 'Present', 5, 25),  -- Lucas Thomas, Data Science 101, Class E
('2023-10-07', 'Late', 6, 6),  -- Ella Jackson, Biology 101, Class F
('2023-10-08', 'Present', 17, 17),  -- Susan White, Mathematics 101, Class A
('2023-10-08', 'Absent', 18, 28),  -- Lucy Green, English Literature, Class B
('2023-10-09', 'Present', 9, 19),  -- Olivia Adams, Physics 201, Class C
('2023-10-09', 'Late', 1, 3);  -- Noah Evans, History of Art, Class D

-- Insert data into Notes
INSERT INTO Notes (student_id, timetable_id, title, description, date_time) VALUES
(1, 1, 'Homework Reminder', 'Please complete the homework by next week.', '2023-09-15'),  -- Susan White, Mathematics 101
(2, 2, 'Essay Feedback', 'Your essay needs more depth in the analysis section.', '2023-11-20'),  -- Lucy Green, English Literature
(3, 3, 'Quiz Review', 'Review the topics on thermodynamics before the quiz next week.', '2023-12-05'),  -- Olivia Adams, Physics 201
(4, 4, 'Project Presentation', 'Prepare your project for presentation on Thursday.', '2023-09-20'),  -- Noah Evans, History of Art
(5, 5, 'Lab Report', 'Submit your lab report by Friday.', '2023-10-18'),  -- Mia Baker, Chemistry Basics
(6, 6, 'Project Feedback', 'Great job on the project, keep it up!', '2023-11-25'),  -- Sophia Jackson, Data Science 101
(7, 7, 'Test Preparation', 'Study chapters 4 and 5 for the upcoming test.', '2023-12-01'),  -- Isabella White, Biology 101
(8, 8, 'Homework Reminder', 'Complete the assigned problems by next week.', '2023-10-20'),  -- Lucas Evans, Mathematics 101
(9, 9, 'Reading Assignment', 'Read chapters 1-3 for the next class.', '2023-09-25'),  -- Harper Baker, English Literature
(10, 10, 'Lab Preparation', 'Prepare for the lab session on Friday.', '2023-10-05'),  -- Ava Davis, Physics 201
(11, 11, 'Art Project', 'Start working on your art project due next month.', '2023-09-30'),  -- Sophia Miller, History of Art
(12, 12, 'Data Analysis', 'Analyze the dataset provided and submit your report.', '2023-11-10'),  -- Mia Anderson, Data Science 101
(13, 11, 'Biology Quiz', 'Prepare for the biology quiz on Monday.', '2023-12-03'),  -- Lucas Thomas, Biology 101
(14, 12, 'Math Homework', 'Solve the problems in chapter 2.', '2023-09-18'),  -- Ella Jackson, Mathematics 101
(15, 13, 'Essay Draft', 'Submit the first draft of your essay by next week.', '2023-11-15'),  -- Susan White, English Literature
(16, 6, 'Physics Experiment', 'Complete the experiment and submit your observations.', '2023-12-08'),  -- Lucy Green, Physics 201
(17, 7, 'Art Exhibition', 'Prepare your artwork for the upcoming exhibition.', '2023-09-22'),  -- Olivia Adams, History of Art
(18, 8, 'Chemistry Homework', 'Complete the assigned problems by Friday.', '2023-10-12'),  -- Noah Evans, Chemistry Basics
(13, 9, 'Project Update', 'Provide an update on your project progress.', '2023-11-20'),  -- Mia Baker, Data Science 101
(2, 10, 'Biology Assignment', 'Submit the biology assignment by next week.', '2023-12-05'),  -- Sophia Jackson, Biology 101
(12, 4, 'Math Quiz', 'Prepare for the math quiz on Wednesday.', '2023-09-27'),  -- Harper Baker, Mathematics 101
(14, 2, 'Essay Revision', 'Revise your essay based on the feedback.', '2023-11-22'),  -- Ava Davis, English Literature
(11, 13, 'Physics Homework', 'Complete the homework problems by next class.', '2023-12-10'),  -- Sophia Miller, Physics 201
(3, 14, 'Art Critique', 'Prepare for the art critique session on Friday.', '2023-09-28'),  -- Mia Anderson, History of Art
(1, 15, 'Data Project', 'Work on the data project and submit your findings.', '2023-11-30'),  -- Lucas Thomas, Data Science 101
(6, 6, 'Biology Lab', 'Prepare for the biology lab session on Monday.', '2023-12-01'),  -- Ella Jackson, Biology 101
(4, 7, 'Math Assignment', 'Complete the math assignment by next week.', '2023-09-20'),  -- Susan White, Mathematics 101
(8, 18, 'Essay Outline', 'Submit the outline of your essay by Friday.', '2023-11-17'),  -- Lucy Green, English Literature
(19, 9, 'Physics Lab', 'Prepare for the physics lab session on Thursday.', '2023-12-07'),  -- Olivia Adams, Physics 201
(3, 3, 'Art Homework', 'Complete the art homework by next class.', '2023-09-24');  -- Noah Evans, History of Art

select * from Users;
select * from Students;
select * from Parents;
select * from Teachers;
select * from Classes;
select * from Courses;
select * from Timetables;
select * from Grades;
select * from Students_Parents;
select * from Attendances;
select * from Announcements;
select * from Notes;