/*1*/
SELECT e.`employee_id`, e.`job_title`, a.`address_id`, a.`address_text` FROM `employees` AS e
JOIN `addresses` AS a USING(`address_id`)
ORDER BY e.`address_id`
LIMIT 5;
/*2*/
SELECT e.`first_name`, e.`last_name`, t.`name`, a.`address_text` FROM `employees` AS e
JOIN `addresses` AS a USING (`address_id`)
JOIN `towns` AS t USING (`town_id`)
ORDER BY e.`first_name`, e.`last_name`
LIMIT 5;
/*3*/
SELECT e.`employee_id`, e.`first_name`, e.`last_name`, d.`name` FROM `employees` AS e
JOIN `departments` AS d ON e.`department_id` = d.`department_id`
WHERE d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;
/*4*/
SELECT e.`employee_id`, e.`first_name`, e.`salary`, d.`name` FROM `employees` AS e
JOIN `departments` AS d USING (`department_id`)
WHERE e.`salary` >15000
ORDER BY d.`department_id` DESC
LIMIT 5;
/*5*/
SELECT e.`employee_id`, e.`first_name`  FROM `employees` AS e
LEFT JOIN `employees_projects` AS ej USING(`employee_id`)
WHERE ej.`project_id` IS NULL
ORDER BY e.`employee_id` DESC
LIMIT 3;

SELECT e.`employee_id`, e.`first_name`  FROM `employees` AS e
WHERE e.`employee_id` NOT IN (SELECT `employee_id` FROM `employees_projects`)
ORDER BY e.`employee_id` DESC
LIMIT 3;
/*6*/
SELECT e.`first_name`, e.`last_name`,e.`hire_date`, d.`name` AS 'dept_name' FROM `employees` AS e
JOIN `departments` AS d USING (`department_id`)
WHERE e.`hire_date` > '1999-01-01' AND d.`name` IN ('Sales', 'Finance')
ORDER BY e.`hire_date` ASC;
/*7*/
SELECT e.`employee_id`, e.`first_name`, p.`name` FROM `employees` AS e
JOIN `employees_projects` AS ep USING (`employee_id`)
JOIN `projects` AS p USING (`project_id`)
WHERE DATE(p.`start_date`) > '2002-08-13' AND p.`end_date` IS NULL
ORDER BY `first_name`, `name`
LIMIT 5;
/*8*/
SELECT e.`employee_id`, e.`first_name`, IF(YEAR(p.`start_date`) >= 2005, NULL, p.`name`) FROM `employees` AS e
JOIN `employees_projects` AS ep USING (`employee_id`)
JOIN `projects` AS p USING (`project_id`)
WHERE `employee_id` = 24
ORDER BY `name`;
/*9*/
SELECT e1.`employee_id`, e1.`first_name`, e1.`manager_id`, (SELECT `first_name` FROM `employees` AS e2 WHERE 
e1.`manager_id` = e2.`employee_id`) AS 'manager_name' FROM `employees` AS e1
WHERE e1.`manager_id` IN (3,7)
ORDER BY e1.`first_name`;
/*10*/
SELECT e1.`employee_id`, CONCAT(e1.`first_name`, ' ', e1.`last_name`) AS 'employee_name', 
(SELECT CONCAT(e2.`first_name`, ' ', e2.`last_name`) FROM `employees` AS e2 WHERE e1.`manager_id` = e2.`employee_id`) AS 'manager_name', d.`name` FROM `employees` AS e1
JOIN `departments` AS d ON d.`department_id` = e1.`department_id`
WHERE e1.`manager_id` IS NOT NULL
ORDER BY `employee_id`
LIMIT 5;
/*11*/
SELECT AVG(`salary`) AS `salary` FROM `employees`
GROUP BY `department_id`
ORDER BY `salary`
LIMIT 1;
/*12*/
SELECT mc.`country_code`, m.`mountain_range`, p.`peak_name`, p.`elevation` FROM `mountains_countries` AS mc
JOIN `mountains` AS m ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS p USING (`mountain_id`)
WHERE `elevation` > 2835 AND `country_code` = 'BG'
ORDER BY `elevation` DESC;
/*13*/
SELECT `country_code`, COUNT(`mountain_id`) as 'count' FROM `mountains_countries`
GROUP BY `country_code`
HAVING `country_code` IN ('BG', 'RU', 'US')
ORDER BY `count` DESC;
/*14*/
SELECT c.`country_name`, r.`river_name` FROM `countries_rivers` AS cr
JOIN `rivers` AS r ON  r.`id` = cr.`river_id`
RIGHT JOIN `countries` AS c ON c.`country_code` = cr.`country_code`
JOIN `continents` USING (`continent_code`)
WHERE `continent_name` = 'Africa'
ORDER BY `country_name`
LIMIT 5;
/*15*/
/*16*/
SELECT COUNT(c.`country_name`) AS `country_count` FROM `countries` AS c
LEFT JOIN `mountains_countries` AS cm USING (`country_code`)
WHERE `mountain_id` IS NULL;

SELECT COUNT(`country_name`) AS `country_count` FROM `countries`
WHERE `country_code` NOT IN(SELECT `country_code` FROM `mountains_countries`);
/*17*/
SELECT c.`country_name`, MAX(p.`elevation`) AS 'highest_peak_elevation', MAX(r.`length`) AS 'longest_river_length' FROM `countries_rivers` AS cr
JOIN `rivers` AS r ON r.`id` = cr.`river_id`
RIGHT JOIN `countries` AS c USING (`country_code`)
LEFT JOIN `mountains_countries` AS mc USING (`country_code`)
LEFT JOIN `peaks` AS p USING (`mountain_id`)
GROUP BY c.`country_name`
ORDER BY `highest_peak_elevation` DESC, `longest_river_length` DESC
LIMIT 5;


-- SELECT DISTINCT c.`country_name`,  (SELECT DISTINCT `elevation` FROM `peaks` AS p WHERE p.`mountain_id` = m.`id` ORDER BY p.`elevation` DESC LIMIT 1),
-- (SELECT DISTINCT `length` FROM `rivers` AS r WHERE r.`id` = cr.`river_id` ORDER BY `length` DESC LIMIT 1 )
--  AS 'longest_river_length' FROM `countries` AS c
-- LEFT JOIN `countries_rivers` AS cr USING (`country_code`)
-- JOIN `rivers` AS r ON  cr.`river_id` = r.`id`
-- LEFT JOIN `mountains_countries` AS mc USING (`country_code`)
-- JOIN `mountains` AS m ON m.`id` = mc.`mountain_id`
-- JOIN `peaks` AS p ON m.`id` = p.`mountain_id`;
