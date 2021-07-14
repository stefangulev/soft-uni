DELIMITER %%%
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(45))
RETURNS INT
DETERMINISTIC
BEGIN

RETURN (SELECT COUNT(*) FROM `employees`
JOIN `addresses` USING (`address_id`)
JOIN `towns` USING (`town_id`)
WHERE `name` = town_name);
END%%%

SELECT ufn_count_employees_by_town('Berlin');

DELIMITER %%%
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(45))
BEGIN
UPDATE `employees` AS e
JOIN `departments` AS d USING (`department_id`)
SET `salary` = `salary` * 1.05
WHERE d.`name` = department_name;
END %%%

CALL usp_raise_salaries('Engineering')%%%

DELIMITER %%%
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
START TRANSACTION;
IF((SELECT COUNT(*) FROM `employees` WHERE `employee_id` = id) < 1) THEN ROLLBACK;
ELSE 
UPDATE `employees`
SET `salary` = `salary` * 1.05
WHERE `employee_id` = id;
END IF;
END%%%
DELIMITER ;

CREATE TABLE employees1(
employee_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20),
last_name VARCHAR(20),
middle_name VARCHAR(20),
job_title VARCHAR(50),
department_id INT,
salary FLOAT
);


CREATE TABLE deleted_employees(
employee_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20),
last_name VARCHAR(20),
middle_name VARCHAR(20),
job_title VARCHAR(50),
department_id INT,
salary FLOAT
);

DELIMITER %%%
CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON `employees`
FOR EACH ROW
BEGIN
INSERT INTO `deleted_employees` (`first_name`, `last_name`, `middle_name`, `job_title`,`department_id`, `salary`) VALUES 
(OLD.`first_name`, OLD.`last_name`, OLD.`middle_name`, OLD.`job_title`,OLD.`department_id`, OLD.`salary`);
END %%%
DELIMITER ;



