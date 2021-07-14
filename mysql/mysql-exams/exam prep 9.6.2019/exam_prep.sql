CREATE SCHEMA ruk_database;
USE ruk_database;
CREATE TABLE `branches` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(20) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL,
`started_on` DATE NOT NULL,
`branch_id` INT NOT NULL,
CONSTRAINT fk_employees_branches
FOREIGN KEY (`branch_id`)
REFERENCES `branches`(`id`)
);

CREATE TABLE `clients` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`age` INT NOT NULL
);

CREATE TABLE `employees_clients` (
`employee_id` INT,
`client_id` INT,
CONSTRAINT fk_employees_clients_employees
FOREIGN KEY (`employee_id`)
REFERENCES `employees`(`id`),
CONSTRAINT fk_employees_clients_clients
FOREIGN KEY (`client_id`)
REFERENCES `clients`(`id`)
);
CREATE TABLE `bank_accounts` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`account_number` VARCHAR(10) NOT NULL,
`balance` DECIMAL(10,2) NOT NULL,
`client_id` INT NOT NULL UNIQUE,
CONSTRAINT fk_bank_accounts_clients
FOREIGN KEY (`client_id`)
REFERENCES `clients`(`id`)
);
CREATE TABLE `cards` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`card_number` VARCHAR(19) NOT NULL,
`card_status` VARCHAR(7) NOT NULL,
`bank_account_id` INT NOT NULL,
CONSTRAINT fk_cards_bank_accounts
FOREIGN KEY (`bank_account_id`)
REFERENCES `bank_accounts`(`id`)
);

INSERT INTO `cards` (`card_number`, `card_status`, `bank_account_id`) 
SELECT REVERSE(`full_name`) AS 'card_number', 'Active' AS 'card_status', `id` AS 'bank_account_id' FROM `clients`
WHERE `id` BETWEEN 191 AND 200;

UPDATE `employees_clients` AS ec1
SET ec1.`employee_id` = (SELECT * FROM (SELECT ec2.`employee_id` FROM `employees_clients` AS ec2 GROUP BY ec2.`employee_id` ORDER BY COUNT(ec2.`client_id`) ASC, `employee_id` ASC LIMIT 1) as ec3)
WHERE ec1.`client_id` = ec1.`employee_id`;

DELETE FROM `employees`
WHERE `id` NOT IN (SELECT `employee_id` FROM `employees_clients`);

SELECT `id`, `full_name` FROM `clients`
ORDER BY `id` ASC;

SELECT `id`, CONCAT_WS(' ', `first_name`, `last_name`) AS 'full_name', CONCAT('$', `salary`) AS 'salary', `started_on` FROM `employees`
WHERE `salary` >= 100000 AND `started_on` > '2018-01-01'
ORDER BY `salary` DESC, `id`;

SELECT cd.`id`, CONCAT_WS(' ', cd.`card_number`, ':', cl.`full_name`) AS 'card_token' FROM `cards` AS cd
JOIN `bank_accounts` AS ba ON ba.`id` = cd.`bank_account_id`
JOIN `clients` AS cl ON  ba.`client_id` = cl.`id`
ORDER BY cd.`id` DESC;

SELECT CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS 'full_name', e.`started_on`, COUNT(ec.`client_id`) AS 'count_of_clients' FROM `employees_clients` AS ec
JOIN `employees` AS e ON e.`id` = ec.`employee_id`
GROUP BY e.`id`
ORDER BY `count_of_clients` DESC, ec.`employee_id`
LIMIT 5;

SELECT b.`name`, COUNT(cd.`id`) AS 'count_of_cards' FROM `branches` AS b
LEFT JOIN `employees` AS e ON b.`id` = e.`branch_id`
LEFT JOIN `employees_clients` AS ec ON e.`id` = ec.`employee_id`
LEFT JOIN `clients` AS c ON c.`id` = ec.`client_id`
LEFT JOIN `bank_accounts` AS ba ON ba.`client_id` = c.`id`
LEFT JOIN `cards` AS cd ON cd.`bank_account_id` = ba.`id`
GROUP BY b.`id`
ORDER BY `count_of_cards` DESC, b.`name`;
DELIMITER %%%
CREATE FUNCTION udf_client_cards_count(client_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN IF((SELECT COUNT(cd.`id`) FROM `cards` AS cd
JOIN `bank_accounts` AS ba ON cd.`bank_account_id` = ba.`id`
JOIN `clients` AS cl ON ba.`client_id` = cl.`id`
WHERE cl.`full_name` = client_name) IS NULL, 0 ,(SELECT COUNT(cd.`id`) FROM `cards` AS cd
JOIN `bank_accounts` AS ba ON cd.`bank_account_id` = ba.`id`
JOIN `clients` AS cl ON ba.`client_id` = cl.`id`
WHERE cl.`full_name` = client_name) );
END %%%
DELIMITER ;

SELECT c.full_name, udf_client_cards_count('Baxy') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David';

DELIMITER %%%
CREATE PROCEDURE udp_clientinfo (full_name VARCHAR(50))
BEGIN
SELECT c.`full_name`,c.`age`, ba.`account_number`, CONCAT('$', ba.`balance`) AS 'balance' FROM `clients` AS c
LEFT JOIN `bank_accounts` AS ba ON ba.`client_id` = c.`id`
WHERE c.`full_name` = full_name;
END %%%
DELIMITER ;


CALL  udp_clientinfo('Hunter Wesgate');
