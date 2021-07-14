SELECT `first_name`, `last_name` FROM `employees`
WHERE SUBSTR(`first_name`, 1 , 2) = 'sa'
ORDER BY `employee_id` ASC;

-- 2
SELECT `first_name`, `last_name` FROM `employees`
WHERE LOCATE('ei', `last_name`) != 0
ORDER BY `employee_id` ASC;

-- 3

SELECT `first_name` FROM `employees`
WHERE `department_id` IN (3, 10) AND YEAR(`hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id` ASC;

-- 4
SELECT `first_name`, `last_name` FROM `employees`
WHERE LOCATE('engineer', `job_title`) = 0
ORDER BY `employee_id` ASC;

-- 5
SELECT `name` FROM `towns`
WHERE CHAR_LENGTH(`name`) IN (5,6)
ORDER BY `name` ASC;

-- 6
SELECT * FROM `towns`
WHERE LEFT(`name`, 1) IN ('m', 'k', 'b', 'e')
ORDER BY `name` ASC;

-- 7
SELECT * FROM `towns`
WHERE LEFT(`name`, 1) NOT IN ('r', 'b', 'd')
ORDER BY `name` ASC;

-- 8
CREATE VIEW `v_employees_hired_after_2000`
AS SELECT `first_name`, `last_name` FROM `employees`
WHERE YEAR(`hire_date`) > 2000;
SELECT * FROM `v_employees_hired_after_2000`;

-- 9
SELECT `first_name`, `last_name` FROM `employees`
WHERE CHAR_LENGTH(`last_name`) = 5;

-- 10
SELECT `country_name`, `iso_code` FROM `countries`
WHERE `country_name` LIKE '%a%a%a%'
ORDER BY `iso_code`;

-- 11
SELECT `peak_name`, `river_name`, CONCAT(SUBSTR(LOWER(`peak_name`), 1, (CHAR_LENGTH(`peak_name`)-1)), LOWER(`river_name`)) as 'mix' FROM `peaks`
INNER JOIN `rivers` ON RIGHT(`peaks`.`peak_name`, 1) = LEFT(`rivers`.`river_name`, 1)
ORDER BY `mix` ASC;
-- 12
SELECT `name`, DATE_FORMAT(`start`, '%Y-%m-%d') AS 'start' FROM `games`
WHERE YEAR(`start`) BETWEEN 2011 AND 2012 
ORDER BY `start`, `name`
LIMIT 50;

-- 13
SELECT `user_name`, INSERT(`email`, 1, LOCATE('@', `email`), '') as 'email' FROM `users`
ORDER BY `email` ASC, `user_name` ASC;

-- 14
SELECT `user_name`, `ip_address` FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name` ASC;

-- 15
SELECT `name`, (CASE 
WHEN HOUR (`start`) BETWEEN 0 AND 11 THEN 'Morning'
WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
WHEN HOUR(`start`) BETWEEN 18 AND 23 THEN 'Evening'
END) AS 'Part of the Day', (CASE
WHEN `duration` <= 3 THEN 'Extra Short'
WHEN `duration` BETWEEN 4 AND 6 THEN 'Short'
WHEN `duration` BETWEEN 7 AND 10 THEN 'Long'
ELSE 'Extra Long'
END) AS 'Duration' FROM `games`;
-- 16
SELECT `product_name`, `order_date`, ADDDATE(`order_date`, INTERVAL 3 DAY) AS 'pay_due', ADDDATE(`order_date`, INTERVAL 1 MONTH) AS 'delivery_due' FROM `orders`;



