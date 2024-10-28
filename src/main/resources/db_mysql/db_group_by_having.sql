USE farm;

SELECT 
	b.farm_id,
	COUNT(b.id) AS number_of_building_types,
    bt.type AS building_type
FROM buildings AS b
LEFT JOIN building_types as bt ON b.building_type_id = bt.id
GROUP BY bt.type, b.farm_id
HAVING COUNT(b.id) IN (1, 2, 3);


-- SELECT 
-- 	f.farm_id AS farm_id,
-- 	COUNT(c.id) AS number_of_fields,
--     c.name AS crop_name
-- FROM fields AS f
-- LEFT JOIN crops as c ON f.crop_id = c.id
-- GROUP BY crop_name, farm_id
-- HAVING crop_name = 'potato';

-- SELECT
-- 	ROUND(AVG(f.area_in_acres), 2) AS average_field_area,
--     f.farm_id AS farm_id
-- FROM fields AS f
-- GROUP BY farm_id
-- HAVING  average_field_area BETWEEN 10 AND 20;

-- SELECT
-- 	ROUND(SUM(f.area_in_acres), 2) AS sum_field_area,
--     f.farm_id AS farm_id
-- FROM fields AS f
-- GROUP BY farm_id
-- HAVING sum_field_area > 50;


-- SELECT
-- 	MAX(f.area_in_acres) AS max_field_area,
--     f.farm_id AS farm_id
-- FROM fields AS f
-- GROUP BY farm_id
-- HAVING farm_id = 1;

-- SELECT 
-- 	b.farm_id,
-- 	b.id AS building_id,
--     SUM(bvs.quantity) AS number_of_vehicles,
--     SUM(bts.quantity) AS number_of_tools,
--     SUM(bas.quantity) AS number_of_animals
-- FROM buildings AS b
-- LEFT JOIN building_vehicle_storages AS bvs ON b.id = bvs.building_id
-- LEFT JOIN building_tool_storages AS bts ON b.id = bts.building_id
-- LEFT JOIN building_animal_storages AS bas ON b.id = bas.building_id
-- LEFT JOIN farms ON b.farm_id = farms.id
-- GROUP BY b.id
-- HAVING (number_of_vehicles > 1 OR number_of_tools > 5 OR number_of_animals > 5);

-- SELECT
-- 	COUNT(resp.id) AS number_of_responsibilities,
--     r.profession
-- FROM responsibilities AS resp
-- RIGHT JOIN roles AS r ON resp.role_id = r.id
-- GROUP BY r.profession
-- HAVING COUNT(resp.id) > 0;