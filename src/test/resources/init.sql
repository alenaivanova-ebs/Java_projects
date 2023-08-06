

CREATE TABLE product (
id int NOT NULL ,
product varchar(45) DEFAULT NULL,
price decimal(10,2) DEFAULT NULL,
discount_id int DEFAULT NULL,
PRIMARY KEY (id)
);

CREATE TABLE discount (
id int NOT NULL,
discount_type varchar(45) DEFAULT NULL,
discount_percent decimal(10,0) DEFAULT NULL,
PRIMARY KEY (id)
);






