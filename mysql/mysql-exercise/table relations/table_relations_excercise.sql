CREATE TABLE `people` (
`person_id` INT,
`first_name` VARCHAR(45),
`salary` DECIMAL(8, 2),
`passport_id` INT UNIQUE
);

CREATE TABLE `passports` (
`passport_id` INT PRIMARY KEY,
`passport_number` VARCHAR(45) UNIQUE
);

INSERT INTO `people` VALUES  (1, 'Roberto', 43300.00, 102), (2, 'Tom', 56100.00, 103),(3, 'Yana', 60200.00, 101);
INSERT INTO `passports` VALUES (101, 'N34FG21B'), (102, 'K65LO4R7'), (103, 'ZE657QP2');

ALTER TABLE `people`
ADD PRIMARY KEY (`person_id`),
ADD CONSTRAINT fk_people_passports
FOREIGN KEY (`passport_id`)
REFERENCES `passports`(`passport_id`);


-- 2
CREATE TABLE `manufacturers` (
`manufacturer_id` INT PRIMARY KEY,
`name` VARCHAR(45),
`established_on` DATE
);

CREATE TABLE `models` (
`model_id` INT PRIMARY KEY,
`name` VARCHAR(45),
`manufacturer_id` INT,
CONSTRAINT fk_models_manufacturers
FOREIGN KEY (`manufacturer_id`)
REFERENCES `manufacturers`(`manufacturer_id`)
);
INSERT INTO `manufacturers` VALUES (1, 'BMW', '1916-03-01'), (2, 'Tesla', '2003-01-01'),(3, 'Lada', '1966-05-01');
INSERT INTO `models` VALUES (101, 'X1', 1), (102, 'i6', 1), (103, 'Model S', 2), (104, 'Model X ', 2), (105, 'Model 3', 2), (106, 'Nova', 3);

-- 3
CREATE TABLE `students` (
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (45)
);
CREATE TABLE `exams` (
`exam_id` INT PRIMARY KEY,
`name` VARCHAR(45)
) AUTO_INCREMENT = 101;
CREATE TABLE `students_exams` (
`student_id` INT,
`exam_id` INT,
CONSTRAINT fk_student_exams_student
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`),
CONSTRAINT fk_student_exams_exams
FOREIGN KEY (`exam_id`)
REFERENCES `exams`(`exam_id`),
PRIMARY KEY (`student_id`, `exam_id`)
);

INSERT INTO `students` VALUES (1, 'Mila'), (2, 'Toni'),(3, 'Ron');
INSERT INTO `exams` VALUES (101, 'Spring MVC'), (102, 'Neo4j'),(103, 'Oracle 11g');
INSERT INTO `students_exams` VALUES (1, 101), (1, 102), (2, 101), (3, 103), (2, 102), (2, 103);

-- 4
CREATE TABLE `teachers` (
`teacher_id` INT PRIMARY KEY,
`name` VARCHAR(45),
`manager_id` INT,
CONSTRAINT fk_teachers_teachers
FOREIGN KEY (`manager_id`)
REFERENCES `teachers`(`teacher_id`)
);
INSERT INTO `teachers` VALUES (101, 'John', NULL), (105, 'Mark', 101),(106, 'Greta', 101), (102, 'Maya', 106), (103, 'Silvia', 106),(104, 'Ted', 105);

/*
OR

*/

CREATE TABLE `teachers1` (
`teacher_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45),
`manager_id` INT
) AUTO_INCREMENT = 101;

INSERT INTO `teachers1` (`name`, `manager_id`) VALUES ('John', NULL), ('Maya', 106),('Silvia', 106),('Ted', 105),
('Mark', 101), ('Greta', 101);

ALTER TABLE `teachers1`
ADD CONSTRAINT fk_teacher_id_manager_id
FOREIGN KEY (`manager_id`)
REFERENCES `teachers1`(`teacher_id`);


-- 5
CREATE SCHEMA `online_store`;
USE `online_store`;

CREATE TABLE `cities` (
`city_id` INT(11) PRIMARY KEY,
`name` VARCHAR(50)
);

CREATE TABLE `customers` (
`customer_id` INT(11) PRIMARY KEY,
`name` VARCHAR(50),
`birthday` DATE,
`city_id` INT(11),
CONSTRAINT fk_customers_cities
FOREIGN KEY (`city_id`)
REFERENCES `cities`(`city_id`)
);
CREATE TABLE `item_types` (
`item_type_id` INT(11) PRIMARY KEY,
`name` VARCHAR(50)
);
CREATE TABLE `items` (
`item_id` INT(11) PRIMARY KEY,
`name` VARCHAR(50),
`item_type_id` INT(11),
CONSTRAINT fk_items_item_types
FOREIGN KEY (`item_type_id`)
REFERENCES `item_types`(`item_type_id`)
);
CREATE TABLE `orders` (
`order_id` INT(11) PRIMARY KEY,
`customer_id` INT(11),
CONSTRAINT fk_orders_customers
FOREIGN KEY (`customer_id`)
REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `order_items` (
`order_id` INT (11),
`item_id` INT(11),
CONSTRAINT fk_order_items_orders
FOREIGN KEY (`order_id`)
REFERENCES `orders`(`order_id`),
CONSTRAINT fk_order_items_items
FOREIGN KEY (`item_id`)
REFERENCES `items`(`item_id`),
PRIMARY KEY (`order_id`, `item_id`)
);

-- 6
CREATE SCHEMA `university`;
USE `university`;

CREATE TABLE `subjects` (
`subject_id` INT(11) PRIMARY KEY,
`subject_name` VARCHAR(50)
);

CREATE TABLE `majors` (
`major_id` INT(11) PRIMARY KEY,
`name` VARCHAR(50)
);

CREATE TABLE `students` (
`student_id` INT(11) PRIMARY KEY,
`student_number` VARCHAR(12),
`student_name` VARCHAR(50),
`major_id` INT(11),
CONSTRAINT fk_students_majors
FOREIGN KEY (`major_id`)
REFERENCES `majors`(`major_id`)
);

CREATE TABLE `payments` (
`payment_id` INT(11) PRIMARY KEY,
`payment_date` DATE,
`payment_amount` DECIMAL(8,2),
`student_id` INT(11),
CONSTRAINT fk_payments_students
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`)
);

CREATE TABLE `agenda` (
`student_id` INT(11),
`subject_id` INT(11),
CONSTRAINT fk_agenda_students
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`),
CONSTRAINT fk_agenda_subjects
FOREIGN KEY (`subject_id`)
REFERENCES `subjects`(`subject_id`),
PRIMARY KEY (`student_id`, `subject_id`)
);

USE `geography`;
SELECT m.`mountain_range`, p.`peak_name`, p.`elevation` AS peak_elevation FROM `mountains` AS m
JOIN `peaks` AS p ON m.`id` = p.`mountain_id`
WHERE `mountain_range` = 'Rila'
ORDER BY `peak_elevation` DESC;






 