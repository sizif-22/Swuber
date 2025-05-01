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

-- select * from Vehicle where vehicleId=1 limit 1;