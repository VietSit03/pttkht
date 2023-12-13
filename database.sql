
DROP DATABASE if exists qlcafe;
CREATE DATABASE qlcafe;

USE qlcafe;

DROP TABLE if exists account;
CREATE TABLE `account` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `accessRight` int NOT NULL,
  PRIMARY KEY (`username`)
);

DROP TABLE if exists promotion;
CREATE TABLE `promotion` (
  `idpromotion` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `priority` int NOT NULL,
  `datestart` date NOT NULL,
  `dateend` date NOT NULL,
  PRIMARY KEY (`idpromotion`)
);

DROP TABLE if exists drink;
CREATE TABLE `drink` (
  `iddrink` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `idpromotion` varchar(45),
  `enable` int NOT NULL default 1,
  PRIMARY KEY (`iddrink`),
  FOREIGN KEY (idpromotion) REFERENCES promotion(idpromotion)
);

DROP TABLE if exists employee;
CREATE TABLE `employee` (
  `idemployee` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `idcard` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  `gender` int NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`idemployee`),
  UNIQUE KEY `idcard_UNIQUE` (`idcard`),
  FOREIGN KEY (username) REFERENCES account(username)
);


DROP TABLE if exists ingredient;
CREATE TABLE `ingredient` (
  `idingredient` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `unit` varchar(45) NOT NULL,
  PRIMARY KEY (`idingredient`)
);

DROP TABLE if exists ingredient_details;
CREATE TABLE `ingredient_details` (
  `id` VARCHAR(45) NOT NULL,
  `idingredient` VARCHAR(45) NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `price` int NOT NULL,
  `expire` DATE,
  PRIMARY KEY (`id`),
  FOREIGN KEY (idingredient) REFERENCES ingredient(idingredient)
);

DROP TABLE if exists orders;
CREATE TABLE `orders` (
  `idorder` varchar(45) NOT NULL,
  `datetime` datetime NOT NULL,
  `methodpay` int,
  `status` int NOT NULL,
  `idemp` varchar(45) NOT NULL,
  PRIMARY KEY (`idorder`),
  FOREIGN KEY (idemp) REFERENCES employee(idemployee)
);

DROP TABLE if exists order_details;
CREATE TABLE `order_details` (
  `idorder` VARCHAR(45) NOT NULL,
  `iddrink` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `price` INT NOT NULL,
  `note` VARCHAR(45),
  PRIMARY KEY (`idorder`, `iddrink`)
);


DROP TABLE if exists phieudongca;
CREATE TABLE `phieudongca` (
  `idphieudongca` int NOT NULL,
  `time` datetime NOT NULL,
  `idemp` varchar(45) NOT NULL,
  PRIMARY KEY (`idphieudongca`)
);

DROP TABLE if exists invoice;
CREATE TABLE `invoice` (
  `idinvoice` VARCHAR(45) NOT NULL,
  `idemp` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `type` INT NOT NULL,
  PRIMARY KEY (`idinvoice`),
  FOREIGN KEY (idemp) REFERENCES employee(idemployee)
);

DROP TABLE if exists invoicenhap;
CREATE TABLE `invoicenhap` (
  `idinvoice` VARCHAR(45) NOT NULL,
  `idingredientdetails` VARCHAR(45) NOT NULL,
  FOREIGN KEY (idinvoice) REFERENCES invoice(idinvoice),
  FOREIGN KEY (idingredientdetails) REFERENCES ingredient_details(id)
);


DROP TABLE if exists invoicexuathuy;
CREATE TABLE `invoicexuathuy` (
  `idinvoice` VARCHAR(45) NOT NULL,
  `idingredientdetails` VARCHAR(45) NOT NULL,
  `quantity` DOUBLE NOT NULL,
  FOREIGN KEY (idinvoice) REFERENCES invoice(idinvoice),
  FOREIGN KEY (idingredientdetails) REFERENCES ingredient_details(id)
);


DROP TABLE if exists invoicetly;
CREATE TABLE `invoicetly` (
  `idinvoice` VARCHAR(45) NOT NULL,
  `idingredientdetails` VARCHAR(45) NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `pricetly` INT NOT NULL,
  FOREIGN KEY (idinvoice) REFERENCES invoice(idinvoice),
  FOREIGN KEY (idingredientdetails) REFERENCES ingredient_details(id)
);



INSERT INTO `qlcafe`.`account` (`username`, `password`, `accessRight`) VALUES ('admin', 'admin', '1');
INSERT INTO `qlcafe`.`account` (`username`, `password`, `accessRight`) VALUES ('nvbh01', '1', '2');
INSERT INTO `qlcafe`.`account` (`username`, `password`, `accessRight`) VALUES ('nvbh02', '1', '2');
INSERT INTO `qlcafe`.`account` (`username`, `password`, `accessRight`) VALUES ('nvbh03', '1', '2');
INSERT INTO `qlcafe`.`account` (`username`, `password`, `accessRight`) VALUES ('nvk01', '1', '3');


INSERT INTO `qlcafe`.`promotion` (`idpromotion`, `price`, `priority`, `datestart`, `dateend`) VALUES ('PA01', '33333', '1', '2023-11-01', '2023-11-30');
INSERT INTO `qlcafe`.`promotion` (`idpromotion`, `price`, `priority`, `datestart`, `dateend`) VALUES ('PA02', '44444', '1', '2023-12-01', '2023-12-30');
INSERT INTO `qlcafe`.`promotion` (`idpromotion`, `price`, `priority`, `datestart`, `dateend`) VALUES ('PB01', '31313', '2', '2023-12-01', '2023-12-30');


INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('CF01', 'Đen đá', 'Cà phê', '30000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('CF02', 'Nâu đá', 'Cà phê', '30000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('CF03', 'Bạc xỉu', 'Cà phê', '35000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('ST01', 'Sinh tố xoài', 'Sinh tố', '45000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('ST02', 'Sinh tố chanh leo', 'Sinh tố', '45000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('ST03', 'Sinh tố chanh tuyết', 'Sinh tố', '45000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('ST04', 'Sinh tố bơ', 'Sinh tố', '50000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('NE01', 'Nước ép táo', 'Nước ép', '45000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('NE02', 'Nước ép ổi', 'Nước ép', '45000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('NE03', 'Nước ép xoài', 'Nước ép', '45000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TS01', 'Hồng trà sữa', 'Trà sữa', '35000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TS02', 'Trà sữa matcha', 'Trà sữa', '35000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TS03', 'Trà sữa bạc hà', 'Trà sữa', '40000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TS04', 'Trà sữa trân châu đường đen', 'Trà sữa', '40000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TS05', 'Trà sữa trân châu trắng', 'Trà sữa', '40000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TP01', 'Trân châu đường đen', 'Topping', '5000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TP02', 'Trân châu hoàng kim', 'Topping', '5000');
INSERT INTO `qlcafe`.`drink` (`iddrink`, `name`, `type`, `price`) VALUES ('TP03', 'Thạch dừa', 'Topping', '5000');



INSERT INTO `qlcafe`.`employee` (`idemployee`, `name`, `idcard`, `position`, `gender`, `username`) VALUES ('admin', 'admin', '000000000001', 'admin', '1', 'admin');
INSERT INTO `qlcafe`.`employee` (`idemployee`, `name`, `idcard`, `position`, `gender`, `username`) VALUES ('NVBH01', 'Trịnh Hoàng Việt', '001203011467', 'Nhân viên bán hàng', '1', 'nvbh01');
INSERT INTO `qlcafe`.`employee` (`idemployee`, `name`, `idcard`, `position`, `gender`, `username`) VALUES ('NVBH02', 'Hoàng Thị Hoa', '001301043245', 'Nhân viên bán hàng', '0', 'nvbh02');
INSERT INTO `qlcafe`.`employee` (`idemployee`, `name`, `idcard`, `position`, `gender`, `username`) VALUES ('NVBH03', 'Lê Thắm', '001308472734', 'Nhân viên bán hàng', '0', 'nvbh03');
INSERT INTO `qlcafe`.`employee` (`idemployee`, `name`, `idcard`, `position`, `gender`, `username`) VALUES ('NVK01', 'Nguyễn Đình Phú', '001203056789', 'Nhân viên kho', '1', 'nvk01');


INSERT INTO `qlcafe`.`orders` (`idorder`, `datetime`, `status`, `idemp`) VALUES ('OD0001', '2023-12-02 00:00:00', '2', 'NVBH01');
INSERT INTO `qlcafe`.`orders` (`idorder`, `datetime`, `status`, `idemp`) VALUES ('OD0002', '2023-12-03 00:00:00', '2', 'NVBH02');
INSERT INTO `qlcafe`.`orders` (`idorder`, `datetime`, `status`, `idemp`) VALUES ('OD0003', '2023-12-04 07:00:00', '2', 'NVBH03');
INSERT INTO `qlcafe`.`orders` (`idorder`, `datetime`, `status`, `idemp`) VALUES ('OD0004', '2023-12-04 07:45:00', '2', 'admin');


INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`) VALUES ('OD0001', 'TS01', '2', '70000');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`, `note`) VALUES ('OD0001', 'TS02', '3', '105000', 'ít đá');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`, `note`) VALUES ('OD0001', 'ST03', '2', '80000', 'ít đường');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`) VALUES ('OD0001', 'CF02', '1', '30000');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`) VALUES ('OD0002', 'TS04', '1', '35000');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`) VALUES ('OD0002', 'TP03', '1', '5000');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`) VALUES ('OD0003', 'NE01', '2', '70000');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`, `note`) VALUES ('OD0003', 'NE02', '3', '120000', '');
INSERT INTO `qlcafe`.`order_details` (`idorder`, `iddrink`, `quantity`, `price`, `note`) VALUES ('OD0004', 'CF01', '2', '60000', 'không đường');

/*Thêm promotion cho loại đồ uống trà sữa*/
UPDATE `qlcafe`.`drink` SET `idpromotion` = 'PB01' WHERE (`iddrink` = 'TS01');
UPDATE `qlcafe`.`drink` SET `idpromotion` = 'PB01' WHERE (`iddrink` = 'TS02');
UPDATE `qlcafe`.`drink` SET `idpromotion` = 'PB01' WHERE (`iddrink` = 'TS03');
UPDATE `qlcafe`.`drink` SET `idpromotion` = 'PB01' WHERE (`iddrink` = 'TS04');
UPDATE `qlcafe`.`drink` SET `idpromotion` = 'PB01' WHERE (`iddrink` = 'TS05');


/*Thêm nguyên liệu*/
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL01', 'Cà phê bột', 'Gói');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL02', 'Đường', 'Gói');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL03', 'Sữa đặc', 'Hộp');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL04', 'Sữa tươi', 'Hộp');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL05', 'Đá', 'Túi');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL06', 'Táo', 'Kg');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL07', 'Ổi', 'Kg');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL08', 'Xoài', 'Kg');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL09', 'Chanh leo', 'Kg');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL10', 'Chanh', 'Kg');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL11', 'Bơ', 'Kg');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL12', 'Siro socola', 'Chai');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL13', 'Siro bạc hà', 'Chai');
INSERT INTO `qlcafe`.`ingredient` (`idingredient`, `name`, `unit`) VALUES ('NL14', 'Bột Matcha', 'Hộp');


/*Thêm chi tiết NL*/
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL01001', 'NL01', '1', '50000', '2023-12-01');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL01002', 'NL01', '3', '50000', '2023-12-20');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL01003', 'NL01', '4', '55000', '2023-12-20');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL02001', 'NL02', '2', '25000', '2023-12-02');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL02002', 'NL02', '5', '25000', '2023-12-20');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL03001', 'NL03', '5', '30000', '2023-12-22');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL04001', 'NL04', '5', '40000', '2023-12-10');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL05001', 'NL05', '3', '8000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL06001', 'NL06', '3', '22000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL07001', 'NL07', '5', '30000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL08001', 'NL08', '4', '25000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL09001', 'NL09', '2', '15000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL10001', 'NL10', '2', '18000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`) VALUES ('NL11001', 'NL11', '2', '23000');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL12001', 'NL12', '2', '50000', '2023-12-15');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL13001', 'NL13', '3', '60000', '2023-12-20');
INSERT INTO `qlcafe`.`ingredient_details` (`id`, `idingredient`, `quantity`, `price`, `expire`) VALUES ('NL14001', 'NL14', '2', '40000', '2023-12-30');
