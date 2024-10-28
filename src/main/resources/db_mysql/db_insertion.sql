USE farm;

INSERT INTO owners (first_name, second_name)
VALUES 
	('John', 'Smith'),
    ('Stan', 'White');
    
INSERT INTO farms (name, location, owner_id)
VALUES
	('A', 'Germany', 1),
    ('B', 'USA', 1);

INSERT INTO roles (profession)
VALUES
	('vet'),
    ('driver'),
    ('harvester'),
    ('seeder');
    
INSERT INTO responsibilities (task, description, role_id)
VALUES
	('Check animal health', 'Every week checking', 1),
    ('Bring the fertilizer', 'Bring the fertilizer to the field with wheat', 2);
    
INSERT INTO workers (first_name, second_name, farm_id)
VALUES
	('Adam', 'Higgs', 1),
    ('Boris', 'Red', 1);
    
INSERT INTO worker_responsibilities (worker_id, responsibility_id)
VALUES (1, 1), (2, 2), (1, 2);
    
INSERT INTO crops (name)
VALUES
	('wheat'),
    ('potato'),
    ('tomato'),
    ('grass'),
    ('canola'),
    ('cucumber');
    
    
INSERT INTO fields (area_in_acres, coordinates, farm_id, crop_id)
VALUES
	(11.1, '5:21:12', 1, 5),
    (12.4, '5:66:53', 1, 4),
    (21.3, '89:21:53', 1, 3),
    (18.6, '23:13:50', 1, 2),
    (15.7, '56:17:90', 1, 1),
    (10.1, '54:32:43', 1, 1),
    (15.7, '523:04:123', 1, 2);
    
INSERT INTO building_types (type)
VALUES
	('barn'),
    ('shed'),
    ('animal house');
    
INSERT INTO buildings (name, farm_id, building_type_id)
VALUES
	('A', 1, 1),
    ('B', 1, 2),
    ('C', 1, 3),
    ('D', 1, 3);

INSERT INTO vehicles (name, brand)
VALUES
	('mini-tractor', 'GermanMotors'),
    ('combiner', 'Ford');
    
INSERT INTO building_vehicle_storages (quantity, building_id, vehicle_id)
VALUES
	(2, 2, 1),
    (1, 2, 2);

INSERT INTO tools (name)
VALUES
	('shovel'),
    ('saw'),
    ('hammer'),
    ('sharpener');
    
INSERT INTO building_tool_storages (quantity, building_id, tool_id)
VALUES
	(5, 1, 1),
    (2, 1, 2),
    (3, 1, 3);
    
INSERT INTO animals (name)
VALUES
	('cow'),
    ('pig'),
    ('chicken');
    
INSERT INTO building_animal_storages (quantity, building_id, animal_id)
VALUES
	(20, 3, 1),
    (10, 4, 2);
    
INSERT INTO building_tool_storages (quantity, building_id, tool_id)
VALUES (2, 4, 1);

INSERT INTO buildings (name, farm_id, building_type_id)
VALUES ('F', 2, 1);

