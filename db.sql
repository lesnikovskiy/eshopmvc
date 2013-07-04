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