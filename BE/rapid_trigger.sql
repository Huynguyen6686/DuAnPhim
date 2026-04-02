
CREATE DATABASE RapidTriggerDB;
USE RapidTriggerDB;

CREATE TABLE Brands (
    brand_id INT IDENTITY(1,1) PRIMARY KEY,
    brand_name VARCHAR(100),
    country VARCHAR(50)
);
CREATE TABLE Categories (
    category_id INT IDENTITY(1,1) PRIMARY KEY,
    category_name VARCHAR(100)
);
CREATE TABLE SwitchTypes (
    switch_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(50),
    description TEXT
);
CREATE TABLE Keyboards (
    keyboard_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2),
    stock INT,
    brand_id INT,
    category_id INT,
    switch_id INT,
    has_rapid_trigger bit,
    actuation_min FLOAT,
    release_year INT,
    description TEXT,
    FOREIGN KEY (brand_id) REFERENCES Brands(brand_id),
    FOREIGN KEY (category_id) REFERENCES Categories(category_id),
    FOREIGN KEY (switch_id) REFERENCES SwitchTypes(switch_id)
);
CREATE TABLE KeyboardImages (
    image_id INT IDENTITY(1,1) PRIMARY KEY,
    keyboard_id INT,
    image_url VARCHAR(255),
    FOREIGN KEY (keyboard_id) REFERENCES Keyboards(keyboard_id)
);
CREATE TABLE Roles (
    role_id INT IDENTITY(1,1) PRIMARY KEY,
    role_name VARCHAR(50)
);
CREATE TABLE Users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(255),
    role_id INT,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);
CREATE TABLE Carts (
    cart_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
CREATE TABLE CartItems (
    cart_item_id INT IDENTITY(1,1) PRIMARY KEY,
    cart_id INT,
    keyboard_id INT,
    quantity INT,
    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
    FOREIGN KEY (keyboard_id) REFERENCES Keyboards(keyboard_id)
);
CREATE TABLE Orders (
    order_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    total_amount DECIMAL(10,2),
    status VARCHAR(50),
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
CREATE TABLE OrderDetails (
    order_detail_id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT,
    keyboard_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (keyboard_id) REFERENCES Keyboards(keyboard_id)
);
CREATE TABLE Reviews (
    review_id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    keyboard_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (keyboard_id) REFERENCES Keyboards(keyboard_id)
);
CREATE TABLE Profiles (
    profile_id INT IDENTITY(1,1) PRIMARY KEY,
    keyboard_id INT,
    actuation_point FLOAT,
    release_point FLOAT,
    rapid_trigger_enabled bit,
    description TEXT,
    FOREIGN KEY (keyboard_id) REFERENCES Keyboards(keyboard_id)
);
INSERT INTO Brands (brand_name, country) VALUES
('Wooting','Netherlands'),
('SteelSeries','Denmark'),
('Razer','USA'),
('Keychron','China'),
('DrunkDeer','China'),
('Corsair','USA'),
('Logitech','Switzerland'),
('Akko','China'),
('HyperX','USA'),
('Asus','Taiwan');
INSERT INTO Categories (category_name) VALUES
('60%'),('65%'),('TKL'),('Fullsize'),
('Gaming'),('Wireless'),('Budget'),
('Premium'),('Custom'),('Office');
INSERT INTO SwitchTypes (name, description) VALUES
('Hall Effect','Magnetic'),
('Optical','Light'),
('Linear','Smooth'),
('Tactile','Bump'),
('Clicky','Click'),
('Low Profile','Slim'),
('Analog','Variable'),
('Hybrid','Mixed'),
('Membrane','Rubber'),
('Capacitive','Electrostatic');
INSERT INTO Roles (role_name) VALUES
('Admin'),
('Employee'),
('Customer');
INSERT INTO Users (username,email,password,role_id) VALUES
('admin1','admin@gmail.com','123',1),
('emp1','emp1@gmail.com','123',2),
('emp2','emp2@gmail.com','123',2),
('cus1','cus1@gmail.com','123',3),
('cus2','cus2@gmail.com','123',3),
('cus3','cus3@gmail.com','123',3),
('cus4','cus4@gmail.com','123',3),
('cus5','cus5@gmail.com','123',3),
('cus6','cus6@gmail.com','123',3),
('cus7','cus7@gmail.com','123',3);
INSERT INTO Keyboards (name,price,stock,brand_id,category_id,switch_id,has_rapid_trigger,actuation_min,release_year,description) VALUES
('Wooting 60HE',4500000,10,1,1,1,1,0.1,2023,'Best RT'),
('Wooting Two HE',5000000,8,1,4,1,1,0.1,2022,'Fullsize'),
('Apex Pro',4200000,15,2,3,2,1,0.2,2023,'SteelSeries'),
('Huntsman V3',4800000,12,3,3,2,1,0.1,2024,'Razer'),
('Keychron Q1 HE',4000000,9,4,2,1,1,0.1,2024,'Custom'),
('DrunkDeer A75',3000000,20,5,2,1,1,0.1,2023,'Budget'),
('Corsair K70',3500000,18,6,4,3,0,2.0,2022,'No RT'),
('Logitech G Pro',3200000,14,7,3,3,0,1.5,2021,'Esport'),
('Akko MOD007',2800000,11,8,2,4,0,1.8,2022,'Custom'),
('HyperX Alloy',3000000,16,9,3,3,0,1.8,2021,'Gaming');
INSERT INTO KeyboardImages (keyboard_id,image_url) VALUES
(1,'img1.jpg'),(2,'img2.jpg'),(3,'img3.jpg'),(4,'img4.jpg'),(5,'img5.jpg'),
(6,'img6.jpg'),(7,'img7.jpg'),(8,'img8.jpg'),(9,'img9.jpg'),(10,'img10.jpg');
INSERT INTO Carts (user_id) VALUES
(4),(5),(6),(7),(8),(9),(10);
INSERT INTO CartItems (cart_id,keyboard_id,quantity) VALUES
(1,1,1),(2,2,1),(3,3,2),(4,4,1),(5,5,1),(6,6,2),(7,7,1);
INSERT INTO Orders (user_id,total_amount,status) VALUES
(4,4500000,'Done'),
(5,5000000,'Done'),
(6,8400000,'Pending'),
(7,4800000,'Shipping'),
(8,4000000,'Done'),
(9,6000000,'Pending'),
(10,3000000,'Done');
INSERT INTO OrderDetails (order_id,keyboard_id,quantity,price) VALUES
(1,1,1,4500000),
(2,2,1,5000000),
(3,3,2,4200000),
(4,4,1,4800000),
(5,5,1,4000000),
(6,6,2,3000000),
(7,10,1,3000000);
INSERT INTO Reviews (user_id,keyboard_id,rating,comment) VALUES
(4,1,5,'Best'),
(5,2,5,'Good'),
(6,3,4,'Nice'),
(7,4,5,'Pro'),
(8,5,4,'Smooth'),
(9,6,5,'Cheap'),
(10,7,3,'Normal');
INSERT INTO Profiles (keyboard_id,actuation_point,release_point,rapid_trigger_enabled,description) VALUES
(1,0.1,0.1,1,'Valorant'),
(2,0.1,0.15,1,'CS2'),
(3,0.2,0.2,1,'Default'),
(4,0.1,0.1,1,'Pro'),
(5,0.15,0.15,1,'Smooth'),
(6,0.1,0.1,1,'Fast'),
(7,1.5,1.5,0,'Normal'),
(8,1.2,1.2,0,'Office'),
(9,1.8,1.8,0,'Typing'),
(10,1.8,1.8,0,'Gaming');
