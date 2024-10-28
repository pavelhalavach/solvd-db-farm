USE farm;

DELETE FROM farms
WHERE name = 'Harvest Haven';


DELETE FROM farms
WHERE name = 'Farmtastic Fields';

DELETE FROM fields
WHERE coordinates = '200X:100Y:15Z';

DELETE FROM crops 
WHERE date_to_seed = DATE('2020-04-01') OR date_to_harvest = DATE('2020-10-01');


DELETE FROM responsibilities
WHERE description = 'Bring the fertilizer to the field with potatoes';

DELETE FROM buildings 
WHERE name = 'main storage';

DELETE FROM building_animal_storages
WHERE quantity = 23;

DELETE FROM vehicles
WHERE brand = 'volvo' OR type_of_fuel = 'gas';


DELETE FROM tools
WHERE name = 'chainsaw';

DELETE FROM animals
WHERE name = 'lama';
