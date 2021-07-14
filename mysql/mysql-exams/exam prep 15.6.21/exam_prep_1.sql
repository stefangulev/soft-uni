CREATE SCHEMA fsd;
USE fsd;

CREATE TABLE `countries` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE `towns` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`country_id` INT(11) NOT NULL,
CONSTRAINT fk_towns_countries
FOREIGN KEY (`country_id`)
REFERENCES `countries`(`id`)
);

CREATE TABLE `stadiums` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`capacity` INT(11) NOT NULL,
`town_id` INT (11) NOT NULL,
CONSTRAINT fk_stadiums_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`)
);
CREATE TABLE `teams` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`established` DATE NOT NULL,
`fan_base` BIGINT(20) NOT NULL DEFAULT 0,
`stadium_id` INT(11),
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (`stadium_id`)
REFERENCES `stadiums`(`id`)
);
CREATE TABLE `skills_data` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`dribbling` INT(11) DEFAULT 0,
`pace` INT(11) DEFAULT 0,
`passing` INT(11) DEFAULT 0,
`shooting` INT(11) DEFAULT 0,
`speed` INT(11) DEFAULT 0,
`strength` INT(11) DEFAULT 0
);

CREATE TABLE `players` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`age` INT(11) NOT NULL DEFAULT 0,
`position` CHAR(1) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`hire_date` DATETIME,
`skills_data_id` INT(11) NOT NULL,
`team_id` INT(11),
CONSTRAINT fk_players_skills_data
FOREIGN KEY (`skills_data_id`)
REFERENCES `skills_data`(`id`),
CONSTRAINT fk_players_teams
FOREIGN KEY (`team_id`)
REFERENCES `teams`(`id`)
);
CREATE TABLE `coaches` (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`coach_level` INT(11) NOT NULL DEFAULT 0
);
CREATE TABLE `players_coaches` (
`player_id` INT (11),
`coach_id` INT (11),
CONSTRAINT fk_player_coaches_players
FOREIGN KEY (`player_id`)
REFERENCES `players`(`id`),
CONSTRAINT fk_player_coaches_coaches
FOREIGN KEY (`coach_id`)
REFERENCES `coaches`(`id`),
PRIMARY KEY(`player_id`, `coach_id`)
);

INSERT INTO `coaches` (`first_name`, `last_name`,`salary`, `coach_level`)
SELECT `first_name`, `last_name`, (`salary` * 2) AS 'salary', length(`first_name`) AS 'coach_level' FROM `players`
WHERE `age` >= 45;

UPDATE `coaches`
SET `coach_level` = `coach_level` + 1
WHERE `id` IN (SELECT `coach_id` FROM `players_coaches`) AND LEFT(`first_name`, 1) = 'A';

DELETE FROM `players`
WHERE `age` >=45;

SELECT `first_name`, `age`, `salary` FROM `players`
ORDER BY `salary` DESC;

SELECT p.`id`, CONCAT_WS(' ',p.`first_name`,p.`last_name`) AS 'full_name', p.`age`, p.`position`, p.`hire_date` FROM `players` AS p
JOIN `skills_data` AS sd ON p.`skills_data_id` = sd.`id`
WHERE `age` < 23 AND `position` = 'A' AND `hire_date` IS NULL AND sd.`strength` >50
ORDER BY `salary` ASC, `age`;

SELECT t.`name`,t.`established`,t.`fan_base`, (SELECT COUNT(*) FROM `players` AS p WHERE `team_id` = t.`id`) AS 'players_count' FROM `teams` AS t
LEFT JOIN `players` AS p ON p.`team_id` = t.`id`
GROUP BY t.`id`
ORDER BY `players_count` DESC, t.`fan_base` DESC;

SELECT  MAX(sd.`speed`) AS 'max_speed', tw.`name` FROM `players` AS p
JOIN `skills_data` AS sd ON p.`skills_data_id` = sd.`id`
RIGHT JOIN `teams` AS tm ON p.`team_id` = tm.`id`
JOIN `stadiums` AS s ON tm.`stadium_id` = s.`id`
JOIN `towns` AS tw ON s.`town_id` = tw.`id`
WHERE tm.`name` <>'Devify'
GROUP BY tw.`name`
ORDER BY `max_speed` DESC, tw.`name`;

SELECT c.`name`, COUNT(p.`id`)as 'total_count_of_players', SUM(p.`salary`) AS 'total_sum_of_salaries'  FROM `players` AS p
JOIN `teams` AS tm ON p.`team_id` = tm.`id`
JOIN `stadiums` AS s ON s.`id` = tm.`stadium_id`
JOIN `towns` AS tw ON s.`town_id` = tw.`id`
RIGHT JOIN `countries` AS c ON tw.`country_id` = c.`id`
GROUP BY c.`name`
ORDER BY `total_count_of_players` DESC, c.`name`;

DELIMITER %%%
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN IF ((SELECT COUNT(p.`id`) FROM `players` AS p
JOIN `teams` AS t ON p.`team_id` = t.`id`
JOIN `stadiums` AS s ON s.`id` = t.`stadium_id`
GROUP BY s.`name`
HAVING s.`name` = stadium_name) IS NULL, 0 ,(SELECT COUNT(p.`id`) FROM `players` AS p
JOIN `teams` AS t ON p.`team_id` = t.`id`
JOIN `stadiums` AS s ON s.`id` = t.`stadium_id`
GROUP BY s.`name`
HAVING s.`name` = stadium_name));
END %%%
DELIMITER ;

SELECT udf_stadium_players_count('fdsfs');

DELIMITER %%%
CREATE PROCEDURE udp_find_playmaker (min_dribble_points INT(11), team_name VARCHAR(45))
BEGIN
SELECT CONCAT_WS(' ', p.`first_name`, p.`last_name`) AS 'full_name', p.`age`, p.`salary`, sd.`dribbling`, sd.`speed`, tm.`name` 
FROM `players` AS p
JOIN `skills_data` AS sd ON p.`skills_data_id` = sd.`id`
JOIN `teams` AS tm ON p.`team_id` = tm.`id`
WHERE tm.`name` = team_name AND sd.`dribbling` > min_dribble_points AND sd.`speed` > (SELECT AVG(sd.`speed`) FROM `players` AS p JOIN `skills_data` AS sd)
ORDER BY sd.`speed` DESC
LIMIT 1;
END %%%
DELIMITER ;
CALL udp_find_playmaker(20, 'dsadasd');






