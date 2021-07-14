CREATE SCHEMA stc;
USE stc;


CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE `cars` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`make` VARCHAR(20) NOT NULL,
`model` VARCHAR(20),
`year` INT NOT NULL DEFAULT 0,
`mileage` INT DEFAULT 0,
`condition` CHAR(1) NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT fk_cars_categories
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`)
);

CREATE TABLE `drivers` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`age` INT NOT NULL,
`rating` FLOAT DEFAULT 5.5
);

CREATE TABLE `cars_drivers` (
`car_id` INT NOT NULL,
`driver_id` INT NOT NULL,
CONSTRAINT fk_cars_drivers_cars
FOREIGN KEY (`car_id`)
REFERENCES `cars`(`id`),
CONSTRAINT fk_cars_drivers_drivers
FOREIGN KEY (`driver_id`)
REFERENCES `drivers`(`id`),
PRIMARY KEY (`car_id`, `driver_id`)
);

CREATE TABLE `clients` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL
);

CREATE TABLE `courses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`from_address_id` INT NOT NULL,
`start` DATETIME NOT NULL,
`bill` DECIMAL(10,2) DEFAULT 10,
`car_id` INT NOT NULL,
`client_id` INT NOT NULL,
CONSTRAINT fk_courses_addresses
FOREIGN KEY (`from_address_id`)
REFERENCES `addresses`(`id`),
CONSTRAINT fk_courses_cars
FOREIGN KEY (`car_id`)
REFERENCES `cars`(`id`),
CONSTRAINT fk_courses_clients
FOREIGN KEY (`client_id`)
REFERENCES `clients`(`id`)
);

INSERT INTO `clients`(`full_name`, `phone_number`) SELECT CONCAT_WS(' ',`first_name`,`last_name`) AS 'full_name', CONCAT('(088) 9999', (`id` * 2)) AS 'phone_number'
FROM `drivers`
WHERE `id` BETWEEN 10 AND 20;

UPDATE `cars`
SET `condition` = 'C'
WHERE (`mileage` >= 80000 OR `mileage` IS NULL) AND `year` <=2010 AND `make` <> 'Mercedes-Benz';

DELETE FROM `clients`
WHERE `id` NOT IN (SELECT `client_id` FROM `courses`) AND CHAR_LENGTH(`full_name`) > 3;

SELECT `make`, `model`, `condition` FROM `cars`
ORDER BY `id`;

SELECT d.`first_name`, d.`last_name`, c.`make`, c.`model`, c.`mileage` FROM `cars_drivers` AS cd
JOIN `cars` AS c ON c.`id` = cd.`car_id`
JOIN `drivers` AS d ON d.`id` = cd.`driver_id`
WHERE c.`mileage` IS NOT NULL
ORDER BY c.`mileage` DESC, d.`first_name`;

SELECT c.`id`, c.`make`, c.`mileage`, COUNT(cr.`id`) AS 'count_of_courses', ROUND(AVG(cr.`bill`), 2) AS 'avg_bill' FROM `cars` AS c
LEFT JOIN `courses` AS cr ON cr.`car_id` = c.`id`
GROUP BY c.`id`
HAVING `count_of_courses` <> 2
ORDER BY `count_of_courses` DESC, c.`id`;

SELECT c.`full_name`, COUNT(c.`id`) AS 'count_of_cars', SUM(cr.`bill`) AS 'total_sum' FROM `clients` AS c
JOIN `courses` AS cr ON cr.`client_id` = c.`id`
JOIN `cars` AS cs ON cs.`id` = cr.`car_id`
GROUP BY c.`id`
HAVING `count_of_cars` > 1 AND c.`full_name` LIKE '_a%'
ORDER BY c.`full_name`;

SELECT a.`name`, IF(HOUR(cr.`start`) BETWEEN 6 AND 20, 'Day', 'Night'), cr.`bill`, cl.`full_name`, ca.`make`, ca.`model`, cg.`name` FROM `courses` AS cr
JOIN `addresses` AS a ON a.`id` =  cr.`from_address_id`
JOIN `clients` AS cl ON cr.`client_id` = cl.`id`
JOIN `cars` AS ca ON cr.`car_id` = ca.`id`
JOIN `categories` AS cg ON ca.`category_id` = cg.`id`
ORDER BY cr.`id`;

DELIMITER %%%
CREATE FUNCTION udf_courses_by_client (phone_number VARCHAR (20))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN (SELECT COUNT(cr.`id`) FROM `courses` AS cr
JOIN `clients` AS cl ON cr.`client_id` = cl.`id`
WHERE cl.`phone_number` = phone_number);
END %%%
DELIMITER ;

DELIMITER %%%
CREATE PROCEDURE udp_courses_by_address (address_name VARCHAR(100))
BEGIN
SELECT a.`name`, cl.`full_name`, (CASE
WHEN cr.`bill` <= 20 THEN 'Low'
WHEN cr.`bill` <= 30 THEN 'Medium'
ELSE 'High'
END
) AS 'level_of_bill', ca.`make`, ca.`condition`, cg.`name` FROM `courses` AS cr
JOIN `addresses` AS a ON cr.`from_address_id` = a.`id`
JOIN `clients` AS cl ON cr.`client_id` = cl.`id`
JOIN `cars` AS ca ON cr.`car_id` = ca.`id`
JOIN `categories` AS cg ON ca.`category_id` = cg.`id`
WHERE a.`name` = address_name
ORDER BY ca.`make`, cl.`full_name`;
END %%%
DELIMITER ;












