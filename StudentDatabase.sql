create database studentmanagementsystem;
use studentmanagementsystem;
create table login(username varchar(30) PRIMARY KEY,password varchar(30));
create table student(name varchar(25), entrynumber integer(10),email varchar(30),contactnumber integer(15),homecity varchar(30));
select * from student;
select * from login;