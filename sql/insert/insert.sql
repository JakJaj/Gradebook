INSERT INTO Users (email, password, pesel, salt, token, enabled, role) VALUES("Teacher@test1.com","PasswordTeacher1","111111111","123SALT321", "321Token123", 1, "TEACHER");

INSERT INTO Teachers (first_name, last_name, date_of_birth, date_of_employment, user_id) VALUES("Test1", "Nauczyciel1", STR_TO_DATE('01/09/1993', '%d/%m/%Y'), STR_TO_DATE('11/10/2015', '%d/%m/%Y'), 1);

INSERT INTO Users (email, password, pesel, salt, token, enabled, role) VALUES("Teacher@test2.com","PasswordTeacher2","22222222222","123SALT321", "321Token123", 1, "TEACHER");

INSERT INTO Teachers (first_name, last_name, date_of_birth, date_of_employment, user_id) VALUES("Test1", "Nauczyciel1", STR_TO_DATE('01/09/1993', '%d/%m/%Y'), STR_TO_DATE('11/10/2015', '%d/%m/%Y'), 2);


INSERT INTO Users (email, password, pesel, salt, token, enabled, role) VALUES("Parent@test2.com","PasswordParent1","3333333333","234SALT432", "432Token234", 1, "PARENT");

INSERT INTO Parents (first_name, last_name, user_id) VALUES("Test1", "Rodzic1", 3);

INSERT INTO Users (email, password, pesel, salt, token, enabled, role) VALUES("Student@test3.com","PasswordStudent1","4444444444444","234SALT432", "432Token234", 1, "STUDENT");

INSERT INTO Classes (class_name, teacher_id, start_year, enabled) VALUES("IVD", 1, 2020, 1);

INSERT INTO Students (first_name, last_name, date_of_birth, city, street, house_number, class_id, user_id) VALUES("Test1", "Uczen1", STR_TO_DATE('02/01/2005', '%d/%m/%Y'), "Bytom", "Katowicka", 1, 1, 4);

INSERT INTO Courses (course_id, course_type, description, teacher_id) VALUES(1, "Matematyka", "Zajęcia z matematyki dla klas 1-6", 1);