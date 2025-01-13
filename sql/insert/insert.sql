
insert into Classes (class_id, class_name, teacher_id, start_year)
VALUES (1, "IC", 1, 2001);

insert into Classes (class_id, class_name, teacher_id, start_year)
VALUES (2, "IVB", 1, 2005);

insert into Classes (class_id, class_name, teacher_id, start_year)
VALUES (3, "IIA", 1, 2001);

insert into Courses(course_id, course_name, teacher_id, description)
VALUES (1, "testowa lekcja", 1, "testowy opis testowej lekcji prowadzonej przez nauczyciela z id nr 1");

insert into Courses(course_id, course_name, teacher_id, description)
VALUES (2, "testowa lekcja", 1, "testowy opis testowej lekcji prowadzonej przez nauczyciela z id nr 1");

insert into Timetables(timetable_id, course_id, class_id, start_time, end_time, classroom_number, day_of_week)
VALUES (1,1,1, "9:00", "9:45", "101", 1);

insert into Timetables(timetable_id, course_id, class_id, start_time, end_time, classroom_number, day_of_week)
VALUES (2,1,1, "9:00", "9:45", "101", 2);

insert into Timetables(timetable_id, course_id, class_id, start_time, end_time, classroom_number, day_of_week)
VALUES (3,1,1, "9:00", "9:45", "101", 4);

insert into Timetables(timetable_id, course_id, class_id, start_time, end_time, classroom_number, day_of_week)
VALUES (5,1,1, "10:00", "10:45", "101", 6);

insert into Grades(grade_id, course_id, mark, student_id, description, magnitude, date)
VALUES(1, 1, 4, 1, "Opis", 3, "2021-11-01");

insert into Grades(grade_id, course_id, mark, student_id, description, magnitude, date)
VALUES(2, 1, 2, 2, "Opis2", 3, "2021-11-01");

insert into Grades(grade_id, course_id, mark, student_id, description, magnitude, date)
VALUES(3, 1, 5, 1, "Opis3", 3, "2021-11-01");

insert into Grades(grade_id, course_id, mark, student_id, description, magnitude, date)
VALUES(4, 2, 5, 1, "Opis4", 3, "2021-11-01");


insert into Grades(grade_id, course_id, mark, student_id, description, magnitude, date)
VALUES(5, 1, 5, 3, "Opis4", 3, "2021-11-01");

insert into Grades(grade_id, course_id, mark, student_id, description, magnitude, date)
VALUES(6, 2, 5, 3, "Opis4", 3, "2021-11-01");


insert into Attendances(attendance_id, date_time, status, student_id, timetable_id)
VALUES (1, "2021-11-01", "Obecny", 1, 1);

insert into Attendances(attendance_id, date_time, status, student_id, timetable_id)
VALUES (2, "2021-11-01", "Spozniony", 2, 1);

insert into Attendances(attendance_id, date_time, status, student_id, timetable_id)
VALUES (3, "2021-11-01", "present", 1, 17);

insert into Notes(note_id, student_id, timetable_id, title, description, date_time)
VALUES(1, 1, 1, "title" "Bla bla bla", "2021-11-01");

insert into Notes(note_id, student_id, timetable_id, title, description, date_time)
VALUES(2, 2, 17, "title", "Bla bla bla", "2021-11-01");

insert into Notes(note_id, student_id, timetable_id, title, description, date_time)
VALUES(3, 1, 2, "title", "Bla bla bla", "2021-11-01");
    
insert into Announcements(announcement_id, title, content, date_time, teacher_id)
VALUES(1, "tytul", "opisa calego ogloszenia", "2021-10-09", 1);

insert into Announcements(announcement_id, title, content, date_time, teacher_id)
VALUES(2, "tytul2", "opisa drugiego calego ogloszenia", "2021-10-09", 1);

insert into Students_Parents(student_id, parent_id)
VALUES(1,1);

insert into Students_Parents(student_id, parent_id)
VALUES(2,1);

USE `grade_book`;


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
