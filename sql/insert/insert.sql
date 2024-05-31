-- Wstawianie uzytkownikow o roli teacher
INSERT INTO Users (password, enabled, role) VALUES("Test1", 0, "TEACHER");
INSERT INTO Users (password, enabled, role) VALUES("Test2", 0, "TEACHER");
INSERT INTO Users (password, enabled, role) VALUES("Test3", 0, "TEACHER");

-- Wstawianie nauczycieli polaczonych z uzytkownikami o roli teacher
INSERT INTO Teachers (first_name, last_name, date_of_birth, email, date_of_employment, user_id) VALUES("Test1", "Nauczyciel1", STR_TO_DATE('01/09/1993', '%d/%m/%Y'), "test1@nauczyciel.com", STR_TO_DATE('11/10/2015', '%d/%m/%Y'), 1);
INSERT INTO Teachers (first_name, last_name, date_of_birth, email, date_of_employment, user_id) VALUES("Test2", "Nauczyciel2", STR_TO_DATE('02/10/1994', '%d/%m/%Y'), "test2@nauczyciel.com", STR_TO_DATE('12/11/2016', '%d/%m/%Y'), 2);
INSERT INTO Teachers (first_name, last_name, date_of_birth, email, date_of_employment, user_id) VALUES("Test3", "Nauczyciel3", STR_TO_DATE('03/11/1995', '%d/%m/%Y'), "test3@nauczyciel.com", STR_TO_DATE('13/12/2017', '%d/%m/%Y'), 3);

