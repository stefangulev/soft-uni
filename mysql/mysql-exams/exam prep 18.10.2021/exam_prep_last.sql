CREATE SCHEMA softuni_stores_system;
USE softuni_stores_system;

CREATE TABLE `towns` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE
);
CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL UNIQUE,
`town_id` INT NOT NULL,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`)
);

CREATE TABLE `stores` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE,
`rating` FLOAT 	NOT NULL,
`has_parking` BOOLEAN DEFAULT false,
`address_id` INT NOT NULL,
CONSTRAINT fk_stores_addresses
FOREIGN KEY (`address_id`)
REFERENCES `addresses`(`id`)
);


CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(15) NOT NULL,
`middle_name` CHAR(1),
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(19,2) DEFAULT 0,
`hire_date` DATE NOT NULL, 
`manager_id` INT,
`store_id` INT NOT NULL,
CONSTRAINT fk_employees_employees
FOREIGN KEY (`manager_id`)
REFERENCES `employees`(`id`),
CONSTRAINT fk_employees_stores
FOREIGN KEY (`store_id`)
REFERENCES `stores`(`id`)
);

CREATE TABLE `pictures` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`url` VARCHAR(100) NOT NULL,
`added_on` DATETIME NOT NULL
);
CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE `products` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE,
`best_before` DATE,
`price` DECIMAL(10,2) NOT NULL,
`description` TEXT,
`category_id` INT NOT NULL,
`picture_id` INT NOT NULL,
CONSTRAINT fk_products_categories
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`),
CONSTRAINT fk_products_pictures
FOREIGN KEY (`picture_id`)
REFERENCES `pictures`(`id`)
);

CREATE TABLE `products_stores` (
`product_id` INT NOT NULL,
`store_id` INT NOT NULL,
CONSTRAINT fk_product_stores_products
FOREIGN KEY (`product_id`)
REFERENCES `products`(`id`),
CONSTRAINT fk_product_stores_stores
FOREIGN KEY (`store_id`)
REFERENCES `stores`(`id`),
PRIMARY KEY (`product_id`, `store_id`)
);

INSERT INTO `products_stores` (`product_id`, `store_id`) (SELECT p.`id` , 1 FROM `products` AS p WHERE 
p.`id` NOT IN (SELECT ps2.`product_id` FROM `products_stores` AS ps2));

ALTER TABLE `employees`
DROP CONSTRAINT fk_employees_managers;
	UPDATE `employees`
	SET `manager_id` = 3,`salary` = `salary` - 500
	WHERE YEAR(`hire_date`) > 2003 AND `store_id` NOT IN (SELECT `id` FROM `stores` WHERE `name` IN ('Cardguard','Veribet'));

DELETE FROM `employees`
WHERE `manager_id` IS NOT NULL AND `salary` >= 6000;

SELECT `first_name`, `middle_name`, `last_name`, `salary`, `hire_date` FROM `employees`
ORDER BY `hire_date` DESC;

SELECT p.`name`, p.`price`, p.`best_before`, CONCAT(SUBSTR(p.`description`, 1, 10 ), '...') AS 'short_description', pc.`url` FROM `products` AS p
LEFT JOIN `pictures` AS pc ON pc.`id` = p.`picture_id`
WHERE char_length(p.`description`) > 100 AND YEAR(pc.`added_on`) < 2019 AND p.`price` > 20
ORDER BY p.`price` DESC;

SELECT s.`name`, COUNT(p.`id`) as 'product_count', ROUND(AVG(p.`price`),2) AS 'avg' FROM `products` AS p
JOIN `products_stores` AS ps ON p.`id` = ps.`product_id`
RIGHT JOIN `stores` AS s ON s.`id` = ps.`store_id`
GROUP BY s.`name`
ORDER BY `product_count` DESC, `avg` DESC, s.`id`;

SELECT CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS 'full_name', s.`name`, a.`name`, e.`salary` FROM `employees` AS e
JOIN `stores` AS s ON e.`store_id` = s.`id`
JOIN `addresses` AS a ON s.`address_id` = a.`id`
WHERE `salary` < 4000 AND a.`name` LIKE '%5%' AND CHAR_LENGTH(s.`name`) > 8 AND RIGHT(e.`last_name`,1) = 'n';

SELECT REVERSE(s.`name`) AS 'reversed_name', CONCAT(UPPER(t.`name`), '-', a.`name`) AS 'full_address', COUNT(e.`id`) AS 'employees_count' FROM `stores` AS s
JOIN `addresses` AS a ON s.`address_id` = a.`id`
JOIN `towns` AS t ON a.`town_id` = t.`id`
JOIN `employees` AS e ON e.`store_id` = s.`id`
GROUP BY s.`id`
ORDER BY `full_address`;

DELIMITER %%%
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(100)
DETERMINISTIC
BEGIN
RETURN (SELECT CONCAT_WS(' ', e.`first_name`, CONCAT(e.`middle_name`,'.'), e.`last_name`, 'works in store for',FLOOR(DATEDIFF('2020-10-18', e.`hire_date`) / 365), 'years')
FROM `employees` AS e
JOIN `stores` AS s ON e.`store_id` = s.`id`
WHERE s.`name` = store_name
ORDER BY e.`salary` DESC
LIMIT 1);
END %%%
DELIMITER ;
SELECT udf_top_paid_employee_by_store('Keylex') as 'full_info';

DELIMITER %%%
CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50))
BEGIN
UPDATE `products` AS p
JOIN `products_stores` AS ps ON ps.`product_id` = p.`id`
JOIN `stores` AS s ON ps.`store_id` = s.`id`
JOIN `addresses` AS a ON s.`address_id` = a.`id`
SET p.`price` = p.`price` + (IF (LEFT(a.`name`, 1) =0, 100,200))
WHERE a.`name` = address_name;
END %%%
DELIMITER ;
CALL udp_update_product_price('07 Armistice Parkway');
SELECT name, price FROM products WHERE id = 15;

