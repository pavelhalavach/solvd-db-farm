USE farm;

UPDATE farms
SET name = 'Harvest Haven'
WHERE id = 1;

UPDATE farms
SET name = 'Farmtastic Fields'
WHERE id = 2;

UPDATE fields
SET coordinates = '200X:100Y:15Z'
WHERE id = 1;

UPDATE crops 
SET date_to_seed = DATE('2020-04-01'), date_to_harvest = DATE('2020-10-01')
WHERE id = 1;

UPDATE responsibilities
SET description = 'Bring the fertilizer to the field with potatoes'
WHERE id = 2;

UPDATE buildings 
SET name = 'main storage'
WHERE id = 1;

UPDATE building_animal_storages
SET quantity = 23
WHERE animal_id = 1;

UPDATE vehicles
SET brand = 'volvo', type_of_fuel = 'gas'
WHERE id = 1;

UPDATE tools
SET name = 'chainsaw'
WHERE id = 2;

UPDATE animals
SET name = 'lama'
WHERE id = 2;