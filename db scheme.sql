create database if not exists swuber;
use swuber;
-- Users table
CREATE TABLE User (
    userId int auto_increment PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) unique NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Vehicle table
CREATE TABLE Vehicle (
    vehicleId int PRIMARY KEY auto_increment,
    vehicleModel VARCHAR(100) NOT NULL,
    color VARCHAR(50),
    licenseNo VARCHAR(20) NOT NULL,
    vehicleOption VARCHAR(10)
--     maxPassengers INT DEFAULT 4,
--     currentPassengers INT DEFAULT 0,
);

-- Driver table
CREATE TABLE Driver (
    driverId int PRIMARY KEY auto_increment,
    name VARCHAR(100) NOT NULL,
	location VARCHAR(255),
    -- phoneNumber VARCHAR(20) NOT NULL,
    vehicleId int,
    completedRides INT DEFAULT 0,
    rating FLOAT DEFAULT 5,
    isAvailable BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId)
);

-- Card table
CREATE TABLE Card (
    cardId int PRIMARY KEY auto_increment,
    cardName VARCHAR(100) NOT NULL,
    cardNumber VARCHAR(19) NOT NULL, -- 16 digits plus possible spaces
    expirationDate VARCHAR(7) NOT NULL, -- MM/YYYY
    cardHolderName VARCHAR(100) NOT NULL,
    userId int NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

-- Ride table
CREATE TABLE Ride (
    rideId int auto_increment PRIMARY KEY,
    userId int NOT NULL,
    driverId int,
    vehicleId int,
    startLocation VARCHAR(255) NOT NULL,
    endLocation VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'SCHEDULED',
    isActive BOOLEAN DEFAULT TRUE,
    paymentId VARCHAR(50),
    rating FLOAT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId)
);

-- Payment table
CREATE TABLE Payment (
    paymentId VARCHAR(50) PRIMARY KEY,
    rideId int NOT NULL,
    userId int NOT NULL,
    cardId int,
    cost DECIMAL(10, 2) NOT NULL,
    discountCode VARCHAR(50),
    isProcessed BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rideId) REFERENCES Ride(rideId),
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (cardId) REFERENCES Card(cardId)
);

-- Update foreign key in Ride table
ALTER TABLE Ride
ADD CONSTRAINT fk_payment
FOREIGN KEY (paymentId) REFERENCES Payment(paymentId);

-- ShuttleRide extends Ride
CREATE TABLE ShuttleRide (
    rideId int auto_increment PRIMARY KEY,
    startLocation Varchar(25),
    endLocation Varchar(25),
    maxPassengers INT NOT NULL DEFAULT 8,
    price float
);
CREATE TABLE ScheduledRide (
    rideId INT,
    userId INT,
    PRIMARY KEY (rideId, userId),
    FOREIGN KEY (rideId)
        REFERENCES ShuttleRide (rideId),
    FOREIGN KEY (userId)
        REFERENCES user (userId)
);

insert into vehicle (vehicleModel,color,licenseNo,vehicleOption) values
("Toyota Corolla", "Black", "Comfort", "ABC123"),
("Honda Civic", "White", "Comfort", "XYZ789"),
("Tesla Model 3", "Red", "Premium", "TSL456");
-- select * from vehicle;
insert into driver (name,location,vehicleId) values
("Ahmed", "shobra", 1),
("Sherif", "shobra", 2),
("Mohamed", "el Salam", 3),
("Nour", "el Salam", 1),
("Seif", "imbaba", 2),
("AbdelRahman", "downtown", 3),
("Yosef", "october", 1),
("Yousry", "zayed", 2),
("Amir", "zamalek", 3);
insert into ShuttleRide (startLocation,endLocation,maxPassengers,price) values 
("New Cairo", "6th October", 10, 50.0),
("Abood", "Alexandria", 16, 80.0);
-- insert into ScheduledRide (rideId , userId) values (2,1);

-- Add indexes for performance
CREATE INDEX idx_user_email ON User(email);
CREATE INDEX idx_driver_location ON Driver(location);
CREATE INDEX idx_ride_status ON Ride(status);
CREATE INDEX idx_ride_user ON Ride(userId);
CREATE INDEX idx_ride_driver ON Ride(driverId);
CREATE INDEX idx_payment_processed ON Payment(isProcessed);