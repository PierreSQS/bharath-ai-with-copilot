create database clinicals;

use clinicals;

CREATE TABLE patient (
    id int NOT NULL AUTO_INCREMENT,
    last_name varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    age int,
    PRIMARY KEY (id)
);

CREATE TABLE clinicaldata (
    id int NOT NULL AUTO_INCREMENT,
    patient_id int,
    component_name varchar(255) NOT NULL,
    component_value varchar(255) NOT NULL,
    measured_date_time TIMESTAMP,
    PRIMARY KEY (id),
     CONSTRAINT fk_patient FOREIGN KEY (patient_id)
  REFERENCES patient(id)
);


insert into patient values(1,'John','Mccain',52);
insert into patient values(2,'Siva','Shankar',32);
insert into patient values(3,'Anthony','Simon',22);
insert into patient values(4,'Bruce','Sanhurst',33);
insert into patient values(5,'Abhram','Mani',55);
insert into patient values(6,'Gandhi','Singh',12);
insert into patient values(7,'Antti','Krovinan',27);
insert into patient values(8,'Simba','White',24);
insert into patient values(9,'Rose','Tanic',29);
insert into patient values(10,'Rowling','Lte',49);



insert into clinicaldata  values('1', '1', 'bp', '67/119', '2018-07-09 19:34:24');
insert into clinicaldata  values('2', '2', 'bp', '63/115', '2018-06-19 19:34:24');
insert into clinicaldata  values('3', '3', 'bp', '72/129', '2018-07-26 19:34:24');
insert into clinicaldata  values('4', '4', 'bp', '74/139', '2018-08-03 19:34:24');
insert into clinicaldata  values('5', '5', 'bp', '67/119', '2018-08-29 19:34:24');
insert into clinicaldata  values('6', '6', 'bp', '62/109', '2018-07-12 19:34:24');
insert into clinicaldata  values('7', '7', 'bp', '55/102', '2018-06-13 19:34:24');
insert into clinicaldata  values('8', '8', 'bp', '47/90', '2018-08-02 19:34:24');
insert into clinicaldata  values('9', '9', 'bp', '90/149', '2018-06-01 19:34:24');
insert into clinicaldata  values('10', '10', 'bp', '50/109', '2018-07-09 19:34:24');
insert into clinicaldata  values('11', '1', 'bp', '55/102', '2018-11-02 19:34:24');
insert into clinicaldata  values('12', '2', 'bp', '67/119', '2018-08-09 19:34:24');
insert into clinicaldata  values('13', '3', 'bp', '63/115', '2018-09-09 19:34:24');
insert into clinicaldata  values('14', '4', 'bp', '55/102', '2018-09-13 19:34:24');
insert into clinicaldata  values('15', '5', 'bp', '50/109', '2018-10-12 19:34:24');
insert into clinicaldata  values('16', '6', 'bp', '67/119', '2018-11-29 19:34:24');
insert into clinicaldata  values('17', '7', 'bp', '63/115', '2018-11-03 19:34:24');
insert into clinicaldata  values('18', '8', 'bp', '63/115', '2018-10-26 19:34:24');
insert into clinicaldata  values('19', '9', 'bp', '55/102', '2018-09-11 19:34:24');
insert into clinicaldata  values('20', '10', 'bp', '72/129', '2018-09-29 19:34:24');
insert into clinicaldata  values('21', '1', 'bp', '55/102', '2018-10-09 19:34:24');
insert into clinicaldata  values('22', '2', 'bp', '63/115', '2018-10-20 19:34:24');
insert into clinicaldata  values('23', '3', 'bp', '55/102', '2018-11-19 19:34:24');
insert into clinicaldata  values('24', '5', 'bp', '72/129', '2018-12-30 19:34:24');
insert into clinicaldata  values('25', '6', 'bp', '63/115', '2018-10-21 19:34:24');
insert into clinicaldata  values('26', '7', 'bp', '47/90', '2018-12-29 19:34:24');
insert into clinicaldata  values('27', '8', 'bp', '63/115', '2018-11-08 19:34:24');
insert into clinicaldata  values('28', '9', 'bp', '90/149', '2018-10-03 19:34:24');
insert into clinicaldata  values('29', '10', 'bp', '55/102', '2018-10-29 19:34:24');
insert into clinicaldata  values('30', '1', 'bp', '90/149', '2018-03-19 19:34:24');
insert into clinicaldata  values('31', '2', 'bp', '55/102', '2018-04-03 19:34:24');
insert into clinicaldata  values('32', '3', 'bp', '63/115', '2018-05-21 19:34:24');
insert into clinicaldata  values('33', '4', 'bp', '47/90', '2018-03-31 19:34:24');
insert into clinicaldata  values('34', '5', 'bp', '55/102', '2018-04-19 19:34:24');
insert into clinicaldata  values('35', '6', 'bp', '72/129', '2018-04-28 19:34:24');
insert into clinicaldata  values('36', '7', 'bp', '67/119', '2018-03-19 19:34:24');
insert into clinicaldata  values('37', '8', 'bp', '50/109', '2018-05-21 19:34:24');
insert into clinicaldata  values('38', '9', 'bp', '67/119', '2018-04-12 19:34:24');
insert into clinicaldata  values('39', '10', 'bp', '67/119', '2018-03-19 19:34:24');
insert into clinicaldata  values('40', '1', 'bp', '67/119', '2019-06-09 19:34:24');
insert into clinicaldata  values('41', '2', 'bp', '55/102', '2019-05-23 19:34:24');
insert into clinicaldata  values('42', '3', 'bp', '67/119', '2019-06-01 19:34:24');
insert into clinicaldata  values('43', '4', 'bp', '90/149', '2019-03-01 19:34:24');
insert into clinicaldata  values('44', '5', 'bp', '55/102', '2019-01-06 19:34:24');
insert into clinicaldata  values('45', '6', 'bp', '67/119', '2019-03-29 19:34:24');
insert into clinicaldata  values('46', '7', 'bp', '55/102', '2019-02-21 19:34:24');
insert into clinicaldata  values('47', '8', 'bp', '50/109', '2019-02-15 19:34:24');
insert into clinicaldata  values('48', '9', 'bp', '67/119', '2019-04-19 19:34:24');
insert into clinicaldata  values('49', '10', 'bp', '37/89', '2019-05-29 19:34:24');

insert into clinicaldata  values('51', '1', 'heartrate', '67', '2018-07-09 19:34:24');
insert into clinicaldata  values('52', '2', 'heartrate', '115', '2018-06-19 19:34:24');	
insert into clinicaldata  values('53', '3', 'heartrate', '72', '2018-07-26 19:34:24');
insert into clinicaldata  values('54', '4', 'heartrate', '74', '2018-08-03 19:34:24');
insert into clinicaldata  values('55', '5', 'heartrate', '119', '2018-08-29 19:34:24');
insert into clinicaldata  values('56', '6', 'heartrate', '62', '2018-07-12 19:34:24');
insert into clinicaldata  values('57', '7', 'heartrate', '102', '2018-06-13 19:34:24');
insert into clinicaldata  values('58', '8', 'heartrate', '90', '2018-08-02 19:34:24');
insert into clinicaldata  values('59', '9', 'heartrate', '90', '2018-06-01 19:34:24');
insert into clinicaldata  values('50', '10', 'heartrate', '109', '2018-07-09 19:34:24');
insert into clinicaldata  values('61', '1', 'heartrate', '55', '2018-11-02 19:34:24');
insert into clinicaldata  values('62', '2', 'heartrate', '67', '2018-08-09 19:34:24');
insert into clinicaldata  values('63', '3', 'heartrate', '63', '2018-09-09 19:34:24');
insert into clinicaldata  values('64', '4', 'heartrate', '55', '2018-09-13 19:34:24');
insert into clinicaldata  values('65', '5', 'heartrate', '50', '2018-10-12 19:34:24');
insert into clinicaldata  values('66', '6', 'heartrate', '119', '2018-11-29 19:34:24');
insert into clinicaldata  values('67', '7', 'heartrate', '115', '2018-11-03 19:34:24');
insert into clinicaldata  values('68', '8', 'heartrate', '63', '2018-10-26 19:34:24');
insert into clinicaldata  values('69', '9', 'heartrate', '55', '2018-09-11 19:34:24');
insert into clinicaldata  values('60', '10', 'heartrate', '72', '2018-09-29 19:34:24');
insert into clinicaldata  values('71', '1', 'heartrate', '55', '2018-10-09 19:34:24');
insert into clinicaldata  values('72', '2', 'heartrate', '115', '2018-10-20 19:34:24');
insert into clinicaldata  values('73', '3', 'heartrate', '55', '2018-11-19 19:34:24');
insert into clinicaldata  values('74', '5', 'heartrate', '129', '2018-12-30 19:34:24');
insert into clinicaldata  values('75', '6', 'heartrate', '63', '2018-10-21 19:34:24');
insert into clinicaldata  values('76', '7', 'heartrate', '90', '2018-12-29 19:34:24');
insert into clinicaldata  values('77', '8', 'heartrate', '63', '2018-11-08 19:34:24');
insert into clinicaldata  values('78', '9', 'heartrate', '149', '2018-10-03 19:34:24');
insert into clinicaldata  values('79', '10', 'heartrate', '55', '2018-10-29 19:34:24');
insert into clinicaldata  values('70', '1', 'heartrate', '149', '2018-03-19 19:34:24');
insert into clinicaldata  values('81', '2', 'heartrate', '55', '2018-04-03 19:34:24');
insert into clinicaldata  values('82', '3', 'heartrate', '115', '2018-05-21 19:34:24');
insert into clinicaldata  values('83', '4', 'heartrate', '47', '2018-03-31 19:34:24');
insert into clinicaldata  values('84', '5', 'heartrate', '102', '2018-04-19 19:34:24');
insert into clinicaldata  values('85', '6', 'heartrate', '72', '2018-04-28 19:34:24');
insert into clinicaldata  values('86', '7', 'heartrate', '67', '2018-03-19 19:34:24');
insert into clinicaldata  values('87', '8', 'heartrate', '109', '2018-05-21 19:34:24');
insert into clinicaldata  values('88', '9', 'heartrate', '67', '2018-04-12 19:34:24');
insert into clinicaldata  values('89', '10', 'heartrate', '119', '2018-03-19 19:34:24');
insert into clinicaldata  values('80', '1', 'heartrate', '119', '2019-06-09 19:34:24');
insert into clinicaldata  values('91', '2', 'heartrate', '55/102', '2019-05-23 19:34:24');
insert into clinicaldata  values('92', '3', 'heartrate', '67', '2019-06-01 19:34:24');
insert into clinicaldata  values('93', '4', 'heartrate', '149', '2019-03-01 19:34:24');
insert into clinicaldata  values('94', '5', 'heartrate', '102', '2019-01-06 19:34:24');
insert into clinicaldata  values('95', '6', 'heartrate', '67/119', '2019-03-29 19:34:24');
insert into clinicaldata  values('96', '7', 'heartrate', '55', '2019-02-21 19:34:24');
insert into clinicaldata  values('97', '8', 'heartrate', '50', '2019-02-15 19:34:24');
insert into clinicaldata  values('98', '9', 'heartrate', '67', '2019-04-19 19:34:24');
insert into clinicaldata  values('99', '10', 'heartrate', '89', '2019-05-29 19:34:24');


drop table patient;
drop table clinicaldata;
drop database clinicals;