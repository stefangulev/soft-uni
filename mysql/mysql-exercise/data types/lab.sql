USE gamebar;
CREATE TABLE employees (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(45) NOT NULL,
`last_name` VARCHAR(45) NOT NULL
);
CREATE TABLE categories (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);
CREATE TABLE products (
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`category_id` VARCHAR(45) NOT NULL
);
INSERT INTO employees (`first_name`, `last_name`) VALUES('Stefan', 'Gulev');
INSERT INTO employees (`first_name`, `last_name`) VALUES ('Test', 'Testov');
INSERT INTO employees (`first_name`, `last_name`) VALUES ('Eho', 'Meho');

ALTER TABLE employees
ADD COLUMN `middle_name` VARCHAR(45) NOT NULL;

ALTER TABLE products
ADD CONSTRAINT fk_products_categories
FOREIGN KEY (`category_id`)
REFERENCES categories(`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE employees
CHANGE COLUMN `middle_name` `middle_name` VARCHAR(100) NOT NULL;


