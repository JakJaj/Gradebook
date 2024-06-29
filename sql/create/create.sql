CREATE DATABASE IF NOT EXISTS `grade_book`;
USE `grade_book`;

DROP TABLE IF EXISTS Messages;
DROP TABLE IF EXISTS Notes;
DROP TABLE IF EXISTS Grades;
DROP TABLE IF EXISTS Students_Parents;
DROP TABLE IF EXISTS Attendances;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Timetables;
DROP TABLE IF EXISTS Classes;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Announcements;
DROP TABLE IF EXISTS Parents;
DROP TABLE IF EXISTS Teachers;
DROP TABLE IF EXISTS Users;

-- Tworzenie tabeli Users 
CREATE TABLE Users(
	user_id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    salt VARCHAR(255),
    token VARCHAR(255),
    enabled BOOL,
    role VARCHAR(255),
    
    PRIMARY KEY (user_id)
);

-- Tworzenie tabeli Teachers
CREATE TABLE Teachers(
	teacher_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    date_of_employment DATE,
    user_id BIGINT NOT NULL,
    
    PRIMARY KEY(teacher_id),
    FOREIGN KEY(user_id) REFERENCES Users(user_id)
);

-- Tworzenie tabeli Parents
CREATE TABLE Parents(
	parent_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    user_id BIGINT NOT NULL,
    
    PRIMARY KEY (parent_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Tworzenie tabeli Announcements
CREATE TABLE Announcements(
	announcement_id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    content TEXT,
    date_time DATETIME,
    teacher_id BIGINT NOT NULL,
    PRIMARY KEY (announcement_id),
    FOREIGN KEY (teacher_id) REFERENCES Teachers(teacher_id)
);

-- Tworzenie tabeli Courses
CREATE TABLE Courses(
	course_id BIGINT NOT NULL AUTO_INCREMENT,
    course_type VARCHAR(255),
    teacher_id BIGINT NOT NULL,
    description VARCHAR(255),
    
    PRIMARY KEY(course_id),
    FOREIGN KEY(teacher_id) REFERENCES Teachers(teacher_id)
);

-- Tworzenie tabeli Classes
CREATE TABLE Classes(
	class_id BIGINT NOT NULL AUTO_INCREMENT,
    class_name VARCHAR(255),
    teacher_id BIGINT NOT NULL,
    start_year SMALLINT,
    
    PRIMARY KEY(class_id),
    FOREIGN KEY(teacher_id) REFERENCES Teachers(teacher_id)
);

-- Tworzenie tabeli Students
CREATE TABLE Students(
	student_id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    city VARCHAR(255),
    street VARCHAR(255),
    house_number INT,
    class_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    active BOOL,
    
    PRIMARY KEY(student_id),
    FOREIGN KEY(class_id) REFERENCES Classes(class_id),
    FOREIGN KEY(user_id) REFERENCES Users(user_id)
);

-- Tworzenie tabeli Students_Parents
CREATE TABLE Students_Parents(
	student_id BIGINT NOT NULL,
    parent_id BIGINT NOT NULL,
    
    CONSTRAINT PK_Students_Parents PRIMARY KEY(student_id, parent_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (parent_id) REFERENCES Parents(parent_id)
);

-- Tworzenie tabeli Timetables
CREATE TABLE Timetables(
	timetable_id BIGINT NOT NULL AUTO_INCREMENT,
    course_id BIGINT NOT NULL,
    class_id BIGINT NOT NULL,
    start_time TIME,
    end_time TIME,
    classroom_number VARCHAR(255),
    day_of_week INT,
    
    PRIMARY KEY(timetable_id),
    FOREIGN KEY(course_id) REFERENCES Courses(course_id),
    FOREIGN KEY(class_id) REFERENCES Classes(class_id)
);

-- Tworzenie tabeli Grades
CREATE TABLE Grades(
	grade_id BIGINT NOT NULL AUTO_INCREMENT,
    course_id BIGINT NOT NULL,
    mark INT,
    student_id BIGINT NOT NULL,
    description TEXT,
    magnitude INT,
    
    PRIMARY KEY(grade_id),
    FOREIGN KEY(course_id) REFERENCES Courses(course_id),
    FOREIGN KEY(student_id) REFERENCES Students(student_id)
);

-- Tworzenie tabeli Attendance
CREATE TABLE Attendances(
	attendance_id BIGINT NOT NULL AUTO_INCREMENT,
    date_time DATETIME,
    status VARCHAR(255),
    student_id BIGINT NOT NULL,
    timetable_id BIGINT NOT NULL,
    
    PRIMARY KEY(attendance_id),
    FOREIGN KEY(student_id) REFERENCES Students(student_id),
    FOREIGN KEY(timetable_id) REFERENCES Timetables(timetable_id)
);

-- Tworzenie tabeli Notes
CREATE TABLE Notes(
	note_id BIGINT NOT NULL AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    timetable_id BIGINT NOT NULL,
    description text,
    date_time DATETIME,
    
    PRIMARY KEY(note_id),
    FOREIGN KEY(student_id) REFERENCES Students(student_id),
    FOREIGN KEY(timetable_id) REFERENCES Timetables(timetable_id)
);

-- Tworzenie tabeli Messages
CREATE TABLE Messages(
	message_id BIGINT NOT NULL AUTO_INCREMENT,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    title VARCHAR(255),
    content TEXT,
    date_time DATETIME,
    
    PRIMARY KEY(message_id),
    FOREIGN KEY(sender_id) REFERENCES Users(user_id),
    FOREIGN KEY(receiver_id) REFERENCES Users(user_id)
);