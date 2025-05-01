create database if not exists swuber;
use swuber;
-- Users table
CREATE TABLE Users (
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
    cardId VARCHAR(50) PRIMARY KEY,
    cardName VARCHAR(100) NOT NULL,
    cardNumber VARCHAR(19) NOT NULL, -- 16 digits plus possible spaces
    expirationDate VARCHAR(7) NOT NULL, -- MM/YYYY
    cardHolderName VARCHAR(100) NOT NULL,
    userId int NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(userId)
);

-- RideHistory table
CREATE TABLE RideHistory (
    rideHistoryId VARCHAR(50) PRIMARY KEY,
    userId int NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(userId)
);

-- Ride table
CREATE TABLE Ride (
    rideId VARCHAR(50) PRIMARY KEY,
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
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId)
);

-- Add rides to rideHistory
CREATE TABLE RideHistoryRides (
    rideHistoryId VARCHAR(50) NOT NULL,
    rideId VARCHAR(50) NOT NULL,
    PRIMARY KEY (rideHistoryId, rideId),
    FOREIGN KEY (rideHistoryId) REFERENCES RideHistory(rideHistoryId),
    FOREIGN KEY (rideId) REFERENCES Ride(rideId)
);

-- Payment table
CREATE TABLE Payment (
    paymentId VARCHAR(50) PRIMARY KEY,
    rideId VARCHAR(50) NOT NULL,
    userId int NOT NULL,
    cardId VARCHAR(50),
    cost DECIMAL(10, 2) NOT NULL,
    discountCode VARCHAR(50),
    isProcessed BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (rideId) REFERENCES Ride(rideId),
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (cardId) REFERENCES Card(cardId)
);

-- Update foreign key in Ride table
ALTER TABLE Ride
ADD CONSTRAINT fk_payment
FOREIGN KEY (paymentId) REFERENCES Payment(paymentId);

-- RidePlanner table
-- CREATE TABLE RidePlanner (
--     ridePlannerId VARCHAR(50) PRIMARY KEY,
--     activeRides VARCHAR(50) REFERENCES Ride(rideId),
--     activeDrivers int REFERENCES Driver(driverId),
--     createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- Service for calculating and assigning drivers to rides
CREATE TABLE DriverAssignment (
    assignmentId VARCHAR(50) PRIMARY KEY,
    ridePlannerId VARCHAR(50) NOT NULL,
    rideId VARCHAR(50) NOT NULL,
    driverId int NOT NULL,
    scheduledPickupLocation VARCHAR(255) NOT NULL,
    estimatedArrivalTime TIMESTAMP,
    isCompleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (ridePlannerId) REFERENCES RidePlanner(ridePlannerId),
    FOREIGN KEY (rideId) REFERENCES Ride(rideId),
    FOREIGN KEY (driverId) REFERENCES Driver(driverId)
);

-- ShuttleRide extends Ride
CREATE TABLE ShuttleRide (
    rideId VARCHAR(50) PRIMARY KEY,
    maxPassengers INT NOT NULL DEFAULT 8,
    availableSeats INT NOT NULL DEFAULT 8,
    route VARCHAR(255) NOT NULL,
    startTime TIMESTAMP NOT NULL,
    shuttleArrivalTime VARCHAR(50), -- Changed from TIMESTAMP to VARCHAR
    pricePerSeatFactor DECIMAL(3, 2) DEFAULT 0.75, -- Discount factor for shared rides
    FOREIGN KEY (rideId) REFERENCES Ride(rideId)
);

-- ShuttleRide_Passengers junction table
CREATE TABLE ShuttleRide_Passengers (
    shuttleRideId VARCHAR(50) NOT NULL,
    userId int NOT NULL,
    pickupLocation VARCHAR(255) NOT NULL,
    dropoffLocation VARCHAR(255) NOT NULL,
    PRIMARY KEY (shuttleRideId, userId),
    FOREIGN KEY (shuttleRideId) REFERENCES ShuttleRide(rideId),
    FOREIGN KEY (userId) REFERENCES Users(userId)
);

-- Add indexes for performance
CREATE INDEX idx_user_email ON Users(email);
CREATE INDEX idx_driver_location ON Driver(location);
CREATE INDEX idx_ride_status ON Ride(status);
CREATE INDEX idx_ride_user ON Ride(userId);
CREATE INDEX idx_ride_driver ON Ride(driverId);
CREATE INDEX idx_payment_processed ON Payment(isProcessed);