USE farm;

SELECT 
	b.farm_id,
	COUNT(b.id) AS number_of_building_types,
    bt.type AS building_type
FROM buildings AS b
LEFT JOIN building_types as bt ON b.building_type_id = bt.id
GROUP BY bt.type, b.farm_id;

-- SELECT 
-- 	f.farm_id AS farm_id,
-- 	COUNT(c.id) AS number_of_fields,
--     c.name AS crop_name
-- FROM fields AS f
-- LEFT JOIN crops as c ON f.crop_id = c.id
-- GROUP BY crop_name, farm_id;

-- SELECT
-- 	ROUND(AVG(f.area_in_acres), 2) AS average_field_area,
--     f.farm_id AS farm_id
-- FROM fields AS f
-- GROUP BY farm_id;

-- SELECT
-- 	ROUND(SUM(f.area_in_acres), 2) AS sum_field_area,
--     f.farm_id AS farm_id
-- FROM fields AS f
-- GROUP BY farm_id;


-- SELECT
-- 	MAX(f.area_in_acres) AS max_field_area,
--     f.farm_id AS farm_id
-- FROM fields AS f
-- GROUP BY farm_id;

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
-- GROUP BY b.id;

-- SELECT
-- 	COUNT(resp.id) AS number_of_responsibilities,
--     r.profession
-- FROM responsibilities AS resp
-- RIGHT JOIN roles AS r ON resp.role_id = r.id
-- GROUP BY r.profession
