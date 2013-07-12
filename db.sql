CREATE TABLE categories (
	id int not null auto_increment primary key, 
	name varchar(100) not null, 
	shortname varchar(100) not null, 
	logicalorder int
);
insert into categories (name, shortname, logicalorder) values ('Books', 'books', 1); 
insert into categories (name, shortname, logicalorder) values ('Sports', 'sports', 2);
insert into categories (name, shortname, logicalorder) values ('Groceries', 'groceries', 3);

CREATE TABLE products (
	id int not null auto_increment primary key, 
	name varchar(500) not null, 
	price decimal(19,2) not null, 
	mime varchar(100), 
	file mediumblob, 
	isdeleted bit not null default 0, 
	categoryid int not null, 
	foreign key (categoryid) references categories (id)
);

-- create users
CREATE TABLE `users` (
	`user_id` INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(45) NOT NULL,
	`password` VARCHAR(45) NOT NULL,
	`enabled` TINYINT(1) NOT NULL
);

CREATE UNIQUE INDEX `unique_user` ON `users` (`username`);

CREATE TABLE `user_roles` (
	`user_role_id` INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`user_id` INT(10) NOT NULL,
	`authority` VARCHAR(45) NOT NULL,
	KEY `FK_USER_ROLES` (`user_id`),
	CONSTRAINT `FK_USER_ROLES` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

INSERT INTO users (username, password, enabled) VALUES ('admin', 'test', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('root', 'ruslan', TRUE);

INSERT INTO user_roles(user_id, authority) SELECT user_id, 'ROLE_USER' from users WHERE username = 'admin';
INSERT INTO user_roles(user_id, authority) SELECT user_id, 'ROLE_USER' from users WHERE username = 'root';

CREATE TABLE `orders` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`firstname` VARCHAR(50) NOT NULL,
	`middlename` VARCHAR(50) NULL,
	`lastname` VARCHAR(50) NOT NULL,
	`email` VARCHAR(150) NULL,
	`phone` VARCHAR(50) NOT NULL,
	`address1` VARCHAR(250) NOT NULL,
	`address2` VARCHAR(250) NULL,
	`zipcode` VARCHAR(50) NOT NULL,
	`comment` VARCHAR(500) NOT NULL,
	`isdeleted` BIT NOT NULL
);

CREATE UNIQUE INDEX `unique_email` ON `orders` (`email`);

CREATE TABLE `orders_products` (
	`order_id` INT NOT NULL,
	`product_id` INT NOT NULL,
	PRIMARY KEY(`order_id`, `product_id`),
	KEY `FK_orders_orders` (`order_id`),
	CONSTRAINT `FK_orders_orders` FOREIGN KEY(`order_id`) REFERENCES `orders`(`id`),
	KEY `FK_orders_products`(`product_id`),
	CONSTRAINT `FK_orders_products` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
);