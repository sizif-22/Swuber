create database swuber;
use swuber;
create table user (
 userId int auto_increment primary key,
 name varchar(30) not null,
 email varchar(30) not null,
 password varchar(20) not null,
 phoneNumber char(11)
 );
create table card (
 userId int not null,
 cardName varchar(20) not null,
 cardNumber char(16) not null primary key,
 expirationDate varchar(5) not null,
 cardHolderName varchar(30) not null,
 foreign key (userId) references user(userId),
 foreign key (cardHolderName) references user(name)
);
create table driver (
 dirverId int auto_increment primary key,
 name varchar(20),
 completedRides int,
 phoneNumber char(11),
 isAvailable boolean,
 rating float,
 location varchar(20)
);
create table vehicle (
 vehicleId int auto_increment primary key,
 driverId int,
 vehicleModel varchar(25) not null ,
 color varchar(10),
 vehicleOption varchar(10),
 licenseNo varchar(15) not null,
 maxPassengers int,
 currentPassengers int,
 foreign key (driverId) references driver(driverId)
);
create table ride (
 rideId int auto_increment primary key,
 userId int,
 driverId int,
 vehicleId int,
 startLocation varchar(20),
 endLocation varchar(20),
 const double,
 status varchar(20),
 isShuttle boolean,
 rating float,
 foreign key (userId) references user(userId),
 foreign key (driverId) references driver(driverId),
 foreign key (vehicleId) references vehicle(vehicleId)
);
create table payment (
 userId int not null,
 rideId int not null,
 cardNumber char(16) not null,
 cost double not null,
 disconuntCode varchar(10),
 foreign key (userId) references user(userId),
 foreign key (rideId) references ride(rideId),
 foreign key (cardNumber) references card(cardNumber)
);
