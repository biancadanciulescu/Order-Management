 drop database if exists TP;
create database if not exists TP;
use TP;

 drop table if exists Clients;
create table if not exists Clients(
id int primary key auto_increment,
nume varchar(45),
prenume varchar(45));

 drop table if exists Products;
create table if not exists Products(
id int primary key auto_increment,
denumire varchar(45),
stoc int,
pret float);

 drop table if exists Orders;
create table if not exists Orders(
id int primary key auto_increment,
idClient int,
idProdus int,
nrProduse int,
valoare float);

 drop table if exists LogTable;
create table if not exists LogTable(
-- id int primary key auto_increment,
idClient int,
idProdus int,
nrProduse int,
valoare float);

