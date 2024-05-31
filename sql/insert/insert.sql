-- Wstawianie uzytkownikow o roli teacher
INSERT INTO Users (password, enabled, role) VALUES("Test1", 0, "TEACHER");
INSERT INTO Users (password, enabled, role) VALUES("Test2", 0, "TEACHER");
INSERT INTO Users (password, enabled, role) VALUES("Test3", 0, "TEACHER");

-- Wstawianie nauczycieli polaczonych z uzytkownikami o roli teacher
INSERT INTO Teachers (first_name, last_name, date_of_birth, email, date_of_employment, user_id) VALUES("Test1", "Nauczyciel1", STR_TO_DATE('01/09/1993', '%d/%m/%Y'), "test1@nauczyciel.com", STR_TO_DATE('11/10/2015', '%d/%m/%Y'), 1);
INSERT INTO Teachers (first_name, last_name, date_of_birth, email, date_of_employment, user_id) VALUES("Test2", "Nauczyciel2", STR_TO_DATE('02/10/1994', '%d/%m/%Y'), "test2@nauczyciel.com", STR_TO_DATE('12/11/2016', '%d/%m/%Y'), 2);
INSERT INTO Teachers (first_name, last_name, date_of_birth, email, date_of_employment, user_id) VALUES("Test3", "Nauczyciel3", STR_TO_DATE('03/11/1995', '%d/%m/%Y'), "test3@nauczyciel.com", STR_TO_DATE('13/12/2017', '%d/%m/%Y'), 3);

-- Wstawianie class do do ktorych przypisywani sa wychowacy z teachers
INSERT INTO Classes (class_name, teacher_id, start_year) VALUES("IVD", 1, 2020);
INSERT INTO Classes (class_name, teacher_id, start_year) VALUES("VE", 2, 2021);
INSERT INTO Classes (class_name, teacher_id, start_year) VALUES("VIF", 3, 2022);

-- Wstawianie uzytkownikow o roli Student
INSERT INTO Users (password, enabled, role) VALUES("Test1", 0, "STUDENT");
INSERT INTO Users (password, enabled, role) VALUES("Test2", 0, "STUDENT");
INSERT INTO Users (password, enabled, role) VALUES("Test3", 0, "STUDENT");

-- Wstawianie uczniow polaczonych z uzytkownikami o roli student
INSERT INTO Students (first_name, last_name, email, date_of_birth, city, street, house_number, class_id, user_id, active) VALUES("Test1", "Uczen1", "test1@uczen.com", STR_TO_DATE('01/01/2005', '%d/%m/%Y'), "Bytom", "Katowicka", 1, 1, 4, 0);
INSERT INTO Students (first_name, last_name, email, date_of_birth, city, street, house_number, class_id, user_id, active) VALUES("Test2", "Uczen2", "test2@uczen.com", STR_TO_DATE('02/02/2006', '%d/%m/%Y'), "Katowice", "Katowicka", 2, 2, 5, 0);
INSERT INTO Students (first_name, last_name, email, date_of_birth, city, street, house_number, class_id, user_id, active) VALUES("Test3", "Uczen3", "test3@uczen.com", STR_TO_DATE('03/03/2007', '%d/%m/%Y'), "Chorzow", "Katowicka", 3, 3, 6, 0);


-- Wstawianie rodzicow polaczonych z uzytkownikami o roli parent
INSERT INTO Users (password, enabled, role) VALUES("Test1", 0, "PARENT");
INSERT INTO Users (password, enabled, role) VALUES("Test2", 0, "PARENT");
INSERT INTO Users (password, enabled, role) VALUES("Test3", 0, "PARENT");

-- Wstawianie rodzicow polaczonych z uzytkownikami o roli parent
INSERT INTO Parents (first_name, last_name, email, user_id) VALUES("Test1", "Rodzic1", "test1@rodzic.com", 7);
INSERT INTO Parents (first_name, last_name, email, user_id) VALUES("Test2", "Rodzic2", "test2@rodzic.com", 8);
INSERT INTO Parents (first_name, last_name, email, user_id) VALUES("Test3", "Rodzic3", "test3@rodzic.com", 9);

