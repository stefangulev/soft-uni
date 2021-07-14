CREATE SCHEMA minions;
USE minions;
CREATE TABLE `minions` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20),
`age` INT
);
CREATE TABLE `towns` (
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20)
);
ALTER TABLE `minions`
ADD COLUMN `town_id` INT,
ADD CONSTRAINT fk_minions_town
FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`);

INSERT INTO `towns` VALUES (1, 'Sofia'), (2, 'Plovdiv'), (3, 'Varna');
INSERT INTO `minions` VALUES (1, 'Kevin', 22, 1), (2, 'Bob', 15, 3),(3, 'Steward', NULL, 2);

TRUNCATE `minions`;
DROP TABLE `minions`;
DROP TABLE `towns`;

CREATE TABLE `people` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` FLOAT(5, 2),
`weight` FLOAT(5, 2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);
INSERT INTO `people` VALUES (1, 'Stefan', 1111, 1.94, 100.2, 'm', '1997/03/13', 'tetewttwetwtwtw');
INSERT INTO `people` VALUES (2, 'EHO', 1111, 1.94, 100.2, 'm', '1997/03/13', 'tetewttwetwtwtw');
INSERT INTO `people` VALUES (3, 'rewr', 1111, 1.94, 100.2, 'm', '1997/03/13', 'tetewttwetwtwtw');
INSERT INTO `people` VALUES (4, 'tqtq', 1111, 1.94, 100.2, 'm', '1997/03/13', 'tetewttwetwtwtw');
INSERT INTO `people` VALUES (5, 'tqttqtqqt', 1111, 1.94, 100.2, 'm', '1997/03/13', 'tetewttwetwtwtw');


CREATE TABLE `users` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` MEDIUMBLOB,
`last_login_time` TIMESTAMP,
`is_deleted` BOOL
);
INSERT INTO `users` VALUES(1, 'Stefan', '123', 11111, '14:12:12', true), (2, 'Stfan', '123', 11111, '14:12:12', true),
(3, 'Sten', '123', 11111, '14:12:12', true), (4, 'fan', '123', 11111, '14:12:12', true), (5, 'Sn', '123', 11111, '14:12:12', true);


ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY (`id`, `username`);

ALTER TABLE `users`
CHANGE COLUMN `last_login_time` `last_login_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;


ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_id
PRIMARY KEY (`id`),
CHANGE COLUMN `username` `username` VARCHAR(30) NOT NULL UNIQUE;

CREATE SCHEMA `movies`;
USE `movies`;
CREATE TABLE `directors`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);
INSERT INTO `directors` VALUES (1, 'Stefan Gulev', 'tetetetwrwrewrewr'), (2, 'rwer wer', 'uuuuu'),
 (3, 'Best rewrew', '3333333'), (4, 'POTT wervvv', '314241'), (5, 'fwfwff zzzz', '444444');
 CREATE TABLE `genres` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `genre_name` VARCHAR(50) NOT NULL,
 `notes` TEXT
 );
 INSERT INTO `genres` VALUES (1, 'Horror', 'tetetetwrwrewrewr'), (2, 'rwer wer', '3342'),
 (3, 'Comedy', '3333333'), (4, 'Study', '314241'), (5, 'Historical', '444444');
 CREATE TABLE `categories` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `category_name` VARCHAR(50) NOT NULL,
 `notes` TEXT
 );
 INSERT INTO `categories` VALUES (1, 'first', 'tetetetwrwrewrewr'), (2, 'second', '3342'),
 (3, 'third', '3333333'), (4, 'fourth', '314241'), (5, 'fifth', '444444');
 CREATE TABLE `movies` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `title` VARCHAR(50) NOT NULL,
 `director_id` INT,
 `copyright_year` YEAR,
 `length` TIME,
 `genre_id` INT,
 `category_id` INT,
 `rating` FLOAT(4,2),
 `notes` TEXT
 );

 INSERT INTO `movies` VALUES (1, 'movie1', 2, '1992', '01:34:12', 3, 1, 1.23, 'textextextex'), (2, 'movie2', 5, '1992', '01:34:12', 3, 1, 5.34, 'textextextex'),
 (3, 'movie3', 1, '1992', '01:34:12', 3, 1, 5.32, 'textextextex'), (4, 'movie4', 3, '1992', '01:34:12', 3, 1, 2.34, 'textextextex'),
 (5, 'movie5', 2, '1992', '01:34:12', 3, 1, 5.22, 'textextextex');
 
 CREATE SCHEMA `car_rental`;
 USE `car_rental`;
 CREATE TABLE `categories` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `category` VARCHAR(50) NOT NULL,
 `daily_rate` FLOAT(6, 2),
 `weekly_rate` FLOAT (6,2),
 `monthly_rate` FLOAT (6,2),
 `weekend_rate` FLOAT (6,2)
 );
 INSERT INTO `categories` VALUES (1, 'first_category', 3.15, 21.15, 30.15, 60.15), (2, 'second_category', 5.15, 21.10, 30.15, 60.15),
 (3, 'third_category', 3.15, 1.15, 6.15, 60.15);
 CREATE TABLE `cars` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `plate_number` INT NOT NULL,
 `make` VARCHAR(50) NOT NULL,
 `model` VARCHAR(50) NOT NULL,
 `car_year` YEAR NOT NULL,
 `category_id` INT NOT NULL,
 `doors` INT NOT NULL,
 `picture` BLOB,
 `car_condition` VARCHAR(50) NOT NULL,
 `available` BOOL );
 INSERT INTO `cars` VALUES (1 , 423432432, 'Germany', 'CLK', 2002, 1, 2 , 11111, 'good', true), (2 , 11122332, 'England', 'Scenic', 2002, 3, 4 , 11111, 'good', true),
 (3 , 95959, 'Argentina', 'M6', 2010, 2, 4 , 11111, 'good', true);
 CREATE TABLE `employees` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `first_name` VARCHAR(50) NOT NULL,
 `second_name` VARCHAR(50) NOT NULL,
`title` VARCHAR(50) NOT NULL,
`notes` TEXT
 );
 INSERT INTO `employees` VALUES (1, 'Ivan', 'Petrov', 'Worker', 'tetwtw'), (2, 'Georgi', 'Ivanov', 'security', 'ewqeqe'),
 (3, 'Ivo', 'Ivanov', 'security', 'qqqqq');

CREATE TABLE `customers` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`driver_licence_number` INT NOT NULL,
`full_name` VARCHAR(50) NOT NULL,
`address` VARCHAR(50),
`city` VARCHAR(50),
`zipcode` INT,
`notes` TEXT
);
INSERT INTO `customers` VALUES (1, 143424, 'Gosho Toshev', 'bul. Vasil Levski', 'Sofia', 141, 'rwerrw'), (2, 65555, 'Ivo Zezov', 'bul. Hristo Botev', 'Sofia', 5551, 'rwerrw'),
(3, 22222, 'Best Toshev', 'bul. Vasil Levski', 'Plovdiv', 222, 'aaaa');
-- •	rental_orders (id, employee_id, customer_id, car_id, car_condition, tank_level,
--  kilometrage_start, kilometrage_end, total_kilometrage, start_date, end_date, total_days, rate_applied, tax_rate, order_status, notes)
 CREATE TABLE `rental_orders` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `employee_id` INT NOT NULL,
 `customer_id`INT NOT NULL,
 `car_id` INT NOT NULL,
 `car_condition` VARCHAR(50) NOT NULL,
 `tank_level` DOUBLE(5, 2),
 `kilometrage_start` DOUBLE(10, 2),
 `kilometrage_end` DOUBLE(10, 2),
 `total_kilometrage` DOUBLE(10, 2),
 `start_date` DATE NOT NULL,
 `end_date` DATE NOT NULL,
 `total_days` INT NOT NULL,
 `rate_applied` FLOAT (3, 2),
 `tax_rate` FLOAT (3, 2),
 `order_status` BOOL,
 `notes` TEXT
 );
 INSERT INTO `rental_orders` VALUES (1, 1, 1 ,1 ,'good',50.5, 10000.32, 15000.23, 150000.10, '2021-03-21', '2021-04-21', 31, 2.54, 4.32, true, 'rwrwrq'),
 (2, 2, 2 ,2 ,'good',50.5, 10000.32, 15000.23, 150000.10, '2021-03-21', '2021-04-21', 31, 2.54, 4.32, true, 'rwrwrq'),
 (3,3, 3 ,3 ,'good', 50.5, 10000.32, 15000.23, 150000.10, '2021-03-21', '2021-04-21', 31, 2.54, 4.32, true, 'rwrwrq');
 
 CREATE DATABASE `soft_uni`;
 USE `soft_uni`;
 
CREATE TABLE `towns` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`address_text` VARCHAR(100) NOT NULL,
`town_id` INT NOT NULL,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);
CREATE TABLE `departments` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);
 
CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`middle_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`job_title` VARCHAR(50),
`department_id` INT NOT NULL,
`hire_date` DATE,
`salary` DOUBLE(6, 2),
`address_id` INT,
CONSTRAINT fk_employees_departments
FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`),
CONSTRAINT fk_employees_address
FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);
 INSERT INTO `towns` (`name`) VALUES ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');
INSERT INTO `departments` (`name`) VALUES ('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');
INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`) 
VALUES ('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
 ('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
 ('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
 ('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
 ('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);

SELECT * FROM `towns` ORDER BY `name`;
SELECT * FROM `departments` ORDER BY `name`;
SELECT * FROM `employees` ORDER BY `salary` DESC;

SELECT `name` FROM `towns`  ORDER BY `name`;
SELECT `name` FROM `departments` ORDER BY `name`;
SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees` ORDER BY `salary` DESC;

UPDATE `employees`
SET `salary` = `salary` * 1.1;
SELECT `salary` FROM `employees`;

DELETE FROM `occupancies`;


 
 
 
 

 














