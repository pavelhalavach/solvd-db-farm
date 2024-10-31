USE farm;

-- SELECT 
-- 	farms.name AS farm_name, 
-- 	farms.location AS farm_location, 
-- 	owners.first_name AS owner_first_name, 
-- 	owners.second_name AS owner_second_name 
-- FROM farms 
-- LEFT JOIN owners ON owner_id = owners.id;

-- SELECT 
-- 	roles.profession AS worker_profession, 
-- 	resp.task AS worker_task, 
--     resp.description AS task_description
-- FROM responsibilities AS resp
-- RIGHT JOIN roles ON  resp.role_id = roles.id; 

SELECT 
	roles.profession AS worker_profession, 
	resp.task AS worker_task, 
    resp.description AS task_description,
    resp.id AS resp_id,
    w.id AS worker_id,
    w.first_name
FROM responsibilities AS resp
LEFT JOIN worker_responsibilities AS wr ON resp.id = wr.responsibility_id
LEFT JOIN workers AS w ON wr.worker_id = w.id
RIGHT JOIN roles ON  resp.role_id = roles.id; 

-- SELECT   
--     farms.name AS farm_name,
--     f.area_in_acres AS field_area_in_acres,
--     c.name AS crop_name
-- FROM fields AS f
-- INNER JOIN farms ON f.farm_id = farms.id
-- INNER JOIN crops AS c ON f.crop_id = c.id;

-- SELECT 
-- 	b.name AS building_name,
--     bt.type AS building_type
-- FROM buildings AS b
-- INNER JOIN building_types AS bt ON b.building_type_id = bt.id;



