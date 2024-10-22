DROP DATABASE IF EXISTS farm;
CREATE DATABASE farm;

USE farm;

DROP TABLE IF EXISTS farms;
CREATE TABLE farms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE,          
    location VARCHAR(255),
    owner_id INT NOT NULL,
    UNIQUE(name, location)
);

DROP TABLE IF EXISTS owners;
CREATE TABLE owners (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,          
    second_name VARCHAR(45)  NOT NULL,
    UNIQUE(first_name, second_name)
);

DROP TABLE IF EXISTS workers;
CREATE TABLE workers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,          
    second_name VARCHAR(45) NOT NULL,
    farm_id INT NOT NULL,
    UNIQUE(first_name, second_name)
);

DROP TABLE IF EXISTS responsibilities;
CREATE TABLE responsibilities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(45) NOT NULL,          
    description VARCHAR(225),
    role_id INT NOT NULL,
    UNIQUE(task, description, role_id)
);

DROP TABLE IF EXISTS worker_responsibilities;
CREATE TABLE worker_responsibilities (
	responsibility_id INT,
    worker_id INT,
    UNIQUE(responsibility_id, worker_id),
    PRIMARY KEY (responsibility_id, worker_id),
    FOREIGN KEY (responsibility_id) REFERENCES responsibilities(id),
    FOREIGN KEY (worker_id) REFERENCES workers(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profession VARCHAR(45) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS fields;
CREATE TABLE fields (
    id INT AUTO_INCREMENT PRIMARY KEY,
    area_in_acres DECIMAL(3,1),          
    coordinates VARCHAR(225) UNIQUE,
    farm_id INT,
    crop_id INT
);

DROP TABLE IF EXISTS crops;
CREATE TABLE crops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE,          
    date_to_seed DATE,
    date_to_harvest DATE
);

DROP TABLE IF EXISTS buildings;
CREATE TABLE buildings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE,
    farm_id INT,
    building_type_id INT NOT NULL
);

DROP TABLE IF EXISTS building_types;
CREATE TABLE building_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS building_vehicle_storages;
CREATE TABLE building_vehicle_storages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity SMALLINT DEFAULT 0,
    building_id INT NOT NULL,
    vehicle_id INT NOT NULL
);

DROP TABLE IF EXISTS building_tool_storages;
CREATE TABLE building_tool_storages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity SMALLINT DEFAULT 0,
	building_id INT NOT NULL,
    tool_id INT NOT NULL
);

DROP TABLE IF EXISTS building_animal_storages;
CREATE TABLE building_animal_storages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity SMALLINT DEFAULT 0, 
	building_id INT NOT NULL,
    animal_id INT NOT NULL
);

DROP TABLE IF EXISTS vehicles;
CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    brand VARCHAR(45),
    fuel VARCHAR(45)
);

DROP TABLE IF EXISTS tools;
CREATE TABLE tools (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    brand VARCHAR(45)
);

DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE
);

ALTER TABLE farms
ADD CONSTRAINT fk_owner
FOREIGN KEY (owner_id) REFERENCES owners(id) ON DELETE CASCADE;

ALTER TABLE workers
ADD CONSTRAINT fk_farm_worker
FOREIGN KEY (farm_id) REFERENCES farms(id) ON DELETE CASCADE;

ALTER TABLE responsibilities
ADD CONSTRAINT fk_role_responsibility
FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE fields
ADD CONSTRAINT fk_farm_field
FOREIGN KEY (farm_id) REFERENCES farms(id) ON DELETE CASCADE;

ALTER TABLE fields
ADD CONSTRAINT fk_crop_field
FOREIGN KEY (crop_id) REFERENCES crops(id);

ALTER TABLE buildings
ADD CONSTRAINT fk_farm_building
FOREIGN KEY (farm_id) REFERENCES farms(id) ON DELETE CASCADE;

ALTER TABLE buildings
ADD CONSTRAINT fk_building_type_building
FOREIGN KEY (building_type_id) REFERENCES building_types(id);

ALTER TABLE building_vehicle_storages
ADD CONSTRAINT fk_building_vehicle_storage
FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE;

ALTER TABLE building_vehicle_storages
ADD CONSTRAINT fk_vehicle_vehicle_storage
FOREIGN KEY (vehicle_id) REFERENCES vehicles(id);

ALTER TABLE building_tool_storages
ADD CONSTRAINT fk_building_tool_storage
FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE;

ALTER TABLE building_tool_storages
ADD CONSTRAINT fk_tool_tool_storage
FOREIGN KEY (tool_id) REFERENCES tools(id);

ALTER TABLE building_animal_storages
ADD CONSTRAINT fk_building_animal_storage
FOREIGN KEY (building_id) REFERENCES buildings(id) ON DELETE CASCADE;

ALTER TABLE building_animal_storages
ADD CONSTRAINT fk_animal_animal_storage
FOREIGN KEY (animal_id) REFERENCES animals(id);

ALTER TABLE animals
ADD breed VARCHAR(45);

ALTER TABLE animals
MODIFY COLUMN breed VARCHAR(20);

ALTER TABLE animals
DROP COLUMN breed;
