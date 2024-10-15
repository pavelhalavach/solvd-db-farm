DROP DATABASE IF EXISTS farm;
CREATE DATABASE farm;

USE farm;

DROP TABLE IF EXISTS farms;
CREATE TABLE farms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,          
    location VARCHAR(255)
);

DROP TABLE IF EXISTS owners;
CREATE TABLE owners (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,          
    second_name VARCHAR(45)  NOT NULL
);

DROP TABLE IF EXISTS workers;
CREATE TABLE workers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,          
    second_name VARCHAR(45) NOT NULL 
);

DROP TABLE IF EXISTS responsibilities;
CREATE TABLE responsibilities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(45) NOT NULL,          
    description VARCHAR(225)  
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profession VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS fields;
CREATE TABLE fields (
    id INT AUTO_INCREMENT PRIMARY KEY,
    area_in_acres FLOAT,          
    coordinates VARCHAR(225)  
);

DROP TABLE IF EXISTS crops;
CREATE TABLE crops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,          
    month_to_seed DATE,
    month_to_harvest DATE
);

DROP TABLE IF EXISTS buildings;
CREATE TABLE buildings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,          
    month_to_seed DATE,
    month_to_harvest DATE
);



