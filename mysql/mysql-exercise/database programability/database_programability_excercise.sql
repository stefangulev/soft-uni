/*1*/
DELIMITER %%%
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT `first_name`, `last_name` FROM `employees`
WHERE `salary` > 35000
ORDER BY `first_name`, `last_name`, `employee_id`;
END%%%
DELIMITER ;
/*2*/
DELIMITER %%%
CREATE PROCEDURE usp_get_employees_salary_above(custom_salary DECIMAL(10, 4))
BEGIN
SELECT `first_name`, `last_name` FROM `employees` WHERE `salary` >= custom_salary
ORDER BY `first_name`, `last_name`, `employee_id`;
END%%%
DELIMITER ;
CALL usp_get_employees_salary_above(45000);
/*3*/
DELIMITER %%%
CREATE PROCEDURE usp_get_towns_starting_with (start_string VARCHAR(45))
BEGIN
SELECT `name` FROM `towns` WHERE LOWER(`name`) LIKE CONCAT(start_string, '%')
ORDER BY `name`;
END%%%
DELIMITER ;
CALL usp_get_towns_starting_with('b');
/*4*/
DELIMITER %%%
CREATE PROCEDURE usp_get_employees_from_town(hometown VARCHAR(45))
BEGIN
SELECT `first_name`, `last_name` FROM `employees` AS e
JOIN `addresses` AS a USING (`address_id`)
JOIN `towns` AS t USING (`town_id`)
WHERE t.`name` = hometown
ORDER BY e.`first_name`, e.`last_name`, e.`employee_id`;
END%%%
DELIMITER ;
CALL usp_get_employees_from_town('Seattle');
/*5*/
DELIMITER %%%
CREATE FUNCTION ufn_get_salary_level (custom_salary DECIMAL (10, 4))
RETURNS VARCHAR(45)
DETERMINISTIC
BEGIN
IF (custom_salary < 30000) THEN RETURN ('Low');
ELSEIF (custom_salary BETWEEN 30000 AND 50000) THEN RETURN ('Average');
ELSE RETURN ('High');
END IF;
END%%%
DELIMITER ;
SELECT  ufn_get_salary_level (40000);
/*6*/
DELIMITER %%%
CREATE PROCEDURE usp_get_employees_by_salary_level (salary_level VARCHAR(20))
BEGIN
IF(salary_level = 'Low') THEN SELECT `first_name`, `last_name` FROM `employees` WHERE `salary` < 30000 ORDER BY `first_name` DESC, `last_name` DESC;
ELSEIF (salary_level = 'Average') THEN SELECT `first_name`, `last_name` FROM `employees` WHERE `salary` BETWEEN 30000 AND 50000 ORDER BY `first_name` DESC, `last_name` DESC;
ELSEIF (salary_level = 'High') THEN SELECT `first_name`, `last_name` FROM `employees` WHERE `salary` > 50000 ORDER BY `first_name` DESC, `last_name` DESC;
END IF;
END%%%
DELIMITER ;
CALL usp_get_employees_by_salary_level('Average');
/*7*/
DELIMITER %%%
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BIT
DETERMINISTIC
BEGIN 
RETURN (SELECT word REGEXP(CONCAT('^[', set_of_letters, ']+$'))); 
END %%%
DELIMITER ;
SELECT ufn_is_word_comprised('oistmiahf', 'halves');

/*8*/
DELIMITER %%%
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
SELECT CONCAT(`first_name`, ' ', `last_name`) AS 'full_name' FROM `account_holders`
ORDER BY `full_name`;
END%%%
DELIMITER ;
CALL usp_get_holders_full_name();
/*9*/
DELIMITER %%%
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (custom_balance DECIMAL(20,4))
BEGIN
SELECT `first_name`, `last_name` FROM `account_holders` AS ah
JOIN `accounts` AS a ON a.`account_holder_id` = ah.`id`
GROUP BY a.`account_holder_id`
HAVING SUM(a.`balance`) > custom_balance;
END%%%
DELIMITER ;
CALL usp_get_holders_with_balance_higher_than(1);
/*10*/
DELIMITER %%%
CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(20,4), interest_rate DOUBLE(9,4), years INT)
RETURNS DECIMAL(20,4)
DETERMINISTIC
BEGIN
RETURN sum * POW(1 + interest_rate, years);
END%%%
DELIMITER ;
SELECT ufn_calculate_future_value(1000, 0.5, 5);
/*11*/
DELIMITER %%%
CREATE PROCEDURE usp_calculate_future_value_for_account (custom_id INT, interest_rate DOUBLE(9,4))
BEGIN
SELECT a.`id`, ah.`first_name`, ah.`last_name`, a.`balance`, ufn_calculate_future_value(a.`balance`, interest_rate, 5) AS 'balance_in_5_years'
FROM `accounts` AS a
JOIN `account_holders` AS ah ON a.`account_holder_id` = ah.`id`
WHERE a.`id` = custom_id;
END%%%
DELIMITER ;
CALL usp_calculate_future_value_for_account(1, 0.1);
/*12*/
DELIMITER %%%
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL (20,4))
BEGIN
START TRANSACTION;
IF((SELECT COUNT(*) FROM `accounts` WHERE `id` = account_id) = 0) THEN ROLLBACK;
ELSE
UPDATE `accounts`
SET `balance` = `balance` + money_amount
WHERE `id` = account_id;
END IF;
END%%%
DELIMITER ;
CALL usp_deposit_money(1, 110.11);
SELECT * FROM `accounts`;
/*13*/
DELIMITER %%%
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(20,4))
BEGIN
START TRANSACTION;
IF(money_amount <=0) THEN ROLLBACK;
ELSEIF ((SELECT `balance` FROM `accounts` WHERE `id` = account_id) < money_amount) THEN ROLLBACK;
ELSE 
UPDATE `accounts`
SET `balance` = `balance` - money_amount
WHERE `id`= account_id;
END IF;
END%%%
DELIMITER ; 
CALL usp_withdraw_money(1, 100);
SELECT * FROM `accounts`;
/*14*/
DELIMITER %%%
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(20,4))
 BEGIN
 START TRANSACTION;
 IF((SELECT COUNT(*) FROM `accounts` WHERE `id` IN (from_account_id, to_account_id)) < 2) THEN ROLLBACK;
 ELSEIF(amount <= 0) THEN ROLLBACK;
 ELSEIF((SELECT `balance` FROM `accounts` WHERE `id` = from_account_id) < amount) THEN ROLLBACK;
 ELSEIF(from_account_id = to_account_id ) THEN ROLLBACK;
 ELSE 
 UPDATE `accounts`
 SET `balance` = `balance` - amount
 WHERE `id` = from_account_id;
 UPDATE `accounts`
 SET `balance` = `balance` + amount
 WHERE `id` = to_account_id;
 END IF;
 END%%%
DELIMITER ;
CALL usp_transfer_money(2,1, 100000);
SELECT * FROM `accounts`;
/*15*/
CREATE TABLE `logs`(`log_id`INT PRIMARY KEY AUTO_INCREMENT, `account_id` INT, `old_sum` DECIMAL (19,4), `new_sum` DECIMAL(19,4));
DELIMITER %%%
CREATE TRIGGER tr_log_balance_updates
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
IF(OLD.`balance` <> NEW.`balance`) THEN 
INSERT INTO `logs` (`account_id`, `old_sum`, `new_sum`) VALUES (OLD.`id`, OLD.`balance`, NEW.`balance`); 
END IF;
END %%%
DELIMITER ;
CALL usp_transfer_money(1,2, 15);
SELECT * FROM `logs`;
/*16*/
CREATE TABLE notification_emails(`id` INT PRIMARY KEY AUTO_INCREMENT, `recipient` INT, `subject` VARCHAR(100), `body` VARCHAR(100));
DELIMITER %%%
CREATE TRIGGER tr_log_email_notifications_following_log
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
INSERT INTO `notification_emails` (`recipient`, `subject`, `body`) VALUES 
(NEW.`account_id`, CONCAT('Balance change for account: ', NEW.`account_id`), CONCAT('On ',NOW(),' your balance was changed from ', NEW.`old_sum`, ' to ',NEW.`new_sum`));
END %%%
DELIMITER ;
SELECT * FROM `notification_emails`;
DROP TRIGGER tr_log_email_notifications_following_log;
 
