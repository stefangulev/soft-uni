CREATE SCHEMA colonial_journey_management_system_db;
USE colonial_journey_management_system_db;
/*0*/
CREATE TABLE `planets` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);
CREATE TABLE `spaceports` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`planet_id` INT,
CONSTRAINT fk_spaceports_planets
FOREIGN KEY(`planet_id`)
REFERENCES `planets`(`id`)
);
CREATE TABLE `spaceships` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL ,
`manufacturer` VARCHAR(30) NOT NULL,
`light_speed_rate` INT DEFAULT 0
);
CREATE TABLE `colonists` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(20) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`ucn` CHAR(10) NOT NULL UNIQUE,
`birth_date` DATE NOT NULL
);
CREATE TABLE `journeys` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`journey_start` DATETIME NOT NULL,
`journey_end` DATETIME NOT NULL,
`purpose` ENUM('Medical', 'Technical', 'Educational', 'Military'),
`destination_spaceport_id` INT,
`spaceship_id` INT,
CONSTRAINT fk_journeys_spaceports
FOREIGN KEY (`destination_spaceport_id`)
REFERENCES `spaceports`(`id`),
CONSTRAINT fk_journeys_spaceships
FOREIGN KEY (`spaceship_id`)
REFERENCES `spaceships`(`id`)
);

CREATE TABLE `travel_cards` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`card_number` CHAR(10) NOT NULL UNIQUE,
`job_during_journey` ENUM('Pilot', 'Engineer', 'Trooper', 'Cleaner','Cook'),
`colonist_id` INT,
`journey_id` INT,
CONSTRAINT fk_travel_cards_colonists
FOREIGN KEY (`colonist_id`)
REFERENCES `colonists`(`id`),
CONSTRAINT fk_travel_cards_journeys
FOREIGN KEY (`journey_id`)
REFERENCES `journeys`(`id`)
);



/*1*/
INSERT INTO `travel_cards` (`card_number`, `job_during_journey`, `colonist_id`, `journey_id`) 
SELECT (CASE
WHEN c.`birth_date` > '1980-01-01' THEN CONCAT(YEAR(c.`birth_date`), DAY(c.`birth_date`), SUBSTR(c.`ucn`, 1 ,4))
ELSE CONCAT(YEAR(c.`birth_date`),MONTH(c.`birth_date`), SUBSTR(`ucn`, 7,10))
END) 
AS 'card_number', 
(CASE
WHEN c.`id` % 2 = 0 THEN 'Pilot'
WHEN c.`id` % 3 = 0 THEN 'Cook'
ELSE 'Engineer'
END) AS 'job_during_journey', c.`id` AS `colonist_id`, LEFT(`ucn`, 1) AS `journey_id`FROM `colonists` AS c WHERE c.`id` BETWEEN 96 AND 100;
/*2*/
UPDATE `journeys` AS j
SET `purpose` = (
CASE
WHEN j.`id` % 2 = 0 THEN 'Medical'
WHEN j.`id` % 3 = 0 THEN 'Technical'
WHEN j.`id` % 5 = 0 THEN 'Educational'
WHEN j.`id` % 7 = 0 THEN 'Military'
ELSE `purpose`
END);

/*3*/
DELETE FROM `colonists` AS c
WHERE c.`id` NOT IN ( SELECT tc.`colonist_id` FROM `travel_cards` AS tc);

/*4*/
SELECT `card_number`, `job_during_journey` FROM `travel_cards`
ORDER BY `card_number`;
/*5*/
SELECT `id`, CONCAT_WS(' ',`first_name`,`last_name`) AS `full_name`, `ucn` FROM `colonists`
ORDER BY `first_name`, `last_name`, `id`;
/*6*/
SELECT `id`, `journey_start`, `journey_end` FROM `journeys`
WHERE `purpose` = 'Military'
ORDER BY `journey_start`;
/*7*/
SELECT c.`id`, CONCAT_WS(' ', c.`first_name`, c.`last_name`) AS 'full_name' FROM `colonists` AS c
JOIN `travel_cards` AS t ON c.`id` = t.`colonist_id`
WHERE t.`job_during_journey` = 'Pilot'
ORDER BY c.`id`;
/*8*/
SELECT COUNT(*) AS 'Count' FROM `colonists` AS c
JOIN `travel_cards`AS t ON c.`id` = t.`colonist_id`
JOIN `journeys` AS j ON t.`journey_id` = j.`id`
GROUP BY j.`purpose`
HAVING j.`purpose` = 'Technical';
/*9*/
SELECT s.`name`, sp.`name` FROM `spaceships` AS s
JOIN `journeys` AS j ON s.`id` = j.`spaceship_id`
JOIN `spaceports` AS sp ON j.`destination_spaceport_id` = sp.`id`
ORDER BY s.`light_speed_rate` DESC
LIMIT 1;
/*10*/
SELECT s.`name`, s.`manufacturer` FROM `spaceships` AS s
JOIN `journeys` AS j ON s.`id` = j.`spaceship_id`
JOIN `travel_cards` AS tc ON j.`id` = tc.`journey_id`
JOIN `colonists` AS c on tc.`colonist_id` = c.`id`
WHERE (c.`birth_date` BETWEEN '1989-01-01' AND '2019-01-01') AND tc.`job_during_journey` = 'Pilot'
ORDER BY s.`name`;
/*11*/
SELECT p.`name` AS 'planet_name', s.`name` AS 'spaceport_name' FROM `planets` AS p
JOIN `spaceports` AS s ON p.`id` = s.`planet_id`
JOIN `journeys` AS j ON j.`destination_spaceport_id` = s.`id`
WHERE j.`purpose` = 'Educational'
ORDER BY `spaceport_name` DESC;
/*12*/
SELECT p.`name`, COUNT(j.`id`) AS 'journeys_count' FROM `planets` AS p
JOIN `spaceports` AS s ON p.`id` = s.`planet_id`
JOIN `journeys` AS j ON j.`destination_spaceport_id` = s.`id`
GROUP BY p.`name`
ORDER BY `journeys_count` DESC, p.`name` ASC;
/*13*/
SELECT j.`id`, p.`name`, s.`name`, j.`purpose` FROM `journeys` AS j
JOIN `spaceports` AS s ON j.`destination_spaceport_id` = s.`id`
JOIN `planets` AS p ON p.`id` = s.`planet_id`
ORDER BY DATEDIFF(j.`journey_end`, j.`journey_start`)
LIMIT 1;
/*14*/
SELECT tc.`job_during_journey` FROM `travel_cards` AS tc
JOIN `journeys` AS j ON j.`id` = tc.`journey_id`
WHERE j.`id` =  (SELECT `id` FROM `journeys` AS j1 ORDER BY DATEDIFF(j1.`journey_end`, j1.`journey_start`) DESC LIMIT 1)
GROUP BY tc.`job_during_journey`
ORDER BY COUNT(tc.`job_during_journey`) ASC
LIMIT 1;
/*15*/
DELIMITER %%%
CREATE FUNCTION udf_count_colonists_by_destination_planet (planet_name VARCHAR (30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN (SELECT COUNT(*) FROM `colonists` as c
JOIN `travel_cards` AS tc ON c.`id` = tc.`colonist_id`
JOIN `journeys` AS j ON j.`id` = tc.`journey_id`
JOIN `spaceports` AS s ON s.`id` = j.`destination_spaceport_id`
JOIN `planets` AS p ON p.`id` = s.`planet_id`
WHERE p.`name` = planet_name);
END %%%
DELIMITER ;
/*16*/
DELIMITER %%%
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
IF (SELECT COUNT(*) FROM `spaceships` WHERE `name` = spaceship_name) = 0 THEN ROLLBACK;
ELSE UPDATE `spaceships`
SET `light_speed_rate` = `light_speed_rate` + light_speed_rate_increse
WHERE `name` = spaceship_name; 
END IF;
END %%%
DELIMITER ;
CALL udp_modify_spaceship_light_speed_rate('USS Templar', 5);
SELECT * FROM `spaceships` WHERE `name` = 'USS Templar';

