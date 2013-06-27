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