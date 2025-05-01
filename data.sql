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

select * from Driver;
-- update driver set isAvailable =1 where driverId in (1,2,3,4,5,6,7,8,9); 
select * from card;
select * from Ride;
select * from users;
-- select u.* , r.* , d.* from users u , ride r , driver d where u.userId = r.userId and d.driverId = r.driverId and u.userid = 1;
select u.* , r.* , d.name as dname , d.location as dlocation , d.vehicleId as dvehicleId , d.completedRides as dCompletedRides , d.rating as drating, d.isAvailable as davailable from users u , ride r , driver d where u.userId = r.userId and d.driverId = r.driverId and u.userid = 1;
-- select * from Vehicle where vehicleId=1 limit 1;


insert into ShuttleRide (startLocation,endLocation,maxPassengers,price) values 
("New Cairo", "6th October", 10, 50.0),
("Abood", "Alexandria", 16, 80.0);

insert into scheduledRides (shuttleId , userId) values (1,1);

select 
shuttleId,
	case 
		when userid in (select userid from scheduledRides where shuttleId = 1) then true
        else false
	END as tmp
from scheduledRides;
