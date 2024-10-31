USE farm;

SELECT 
	farms.name AS farm_name,
    b.name AS building_name,
    bt.type AS building_type,
    bvs.quantity AS vehicle_quantity,
    v.name AS vehicle_name,
    bts.quantity AS tool_quantity,
    t.name AS tool_name,
    bas.quantity AS animal_quantity,
    a.name AS animal_name
FROM buildings AS b
INNER JOIN farms ON b.farm_id = farms.id
INNER JOIN building_types AS bt ON b.building_type_id = bt.id
LEFT JOIN building_vehicle_storages AS bvs ON b.id = bvs.building_id
LEFT JOIN vehicles AS v ON bvs.vehicle_id = v.id
LEFT JOIN building_tool_storages AS bts ON b.id = bts.building_id
LEFT JOIN tools AS t ON bts.tool_id = t.id
LEFT JOIN building_animal_storages AS bas ON b.id = bas.building_id
LEFT JOIN animals AS a ON bas.animal_id = a.id;