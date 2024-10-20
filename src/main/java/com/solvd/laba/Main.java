package com.solvd.laba;

import com.solvd.laba.model.*;
import com.solvd.laba.service.*;
import com.solvd.laba.service.impl.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Crop crop = new Crop();
        crop.setName("sunflower");

        Field field = new Field();
        field.setAreaInAcres(40.5f);
        field.setCoordinates("NW200");
        field.setCrop(crop);

        Role role = new Role();
        role.setProfession("beekeeper");
        List<Responsibility> resps = new ArrayList<>();
        Responsibility responsibility = new Responsibility();
        resps.add(responsibility);
        responsibility.setRole(role);
        responsibility.setTask("clean up bee hives");
        responsibility.setDescription("need to clean up 5 bee hives");
        Responsibility responsibility2 = new Responsibility();
        resps.add(responsibility2);
        responsibility2.setRole(role);
        responsibility2.setTask("collect honey");
        responsibility2.setDescription("collect honey from 5 hives");
        Worker worker1 = new Worker();
        worker1.setFirstName("Sam");
        worker1.setSecondName("Smith");
//        System.out.println(worker1.getId());
        worker1.setResponsibilities(resps);
//        System.out.println(worker1.getResponsibilities());

        WorkerService workerService = new WorkerServiceImpl();
        RoleService roleService = new RoleServiceImpl();
        ResponsibilityService responsibilityService = new ResponsibilityServiceImpl();

//        System.out.println(roleService.getById(6));
//        System.out.println(roleService.getByProfession(role.getProfession()));
//        roleService.insert(role);
//        System.out.println(roleService.getByProfession("beekeeper"));

//        roleService.deleteByProfession("beekeeper");
//        System.out.println(roleService.getAll());
//        System.out.println(role.getId());
//        System.out.println(responsibility.getId());
//        System.out.println(responsibilityService.getById(1));
//        System.out.println(responsibilityService.getById(8));
//        System.out.println(responsibilityService.getAll());
//        responsibilityService.insert(responsibility);
//        System.out.println(responsibilityService.getAll());
//        System.out.println(roleService.getAll());
//        responsibility.setTask("BREDIK");
//        role.setProfession("new role");
//        responsibilityService.update(responsibility);
//        System.out.println(responsibilityService.getAll());
//        responsibilityService.deleteById(responsibility.getId());
//        System.out.println(responsibilityService.getAll());
//        System.out.println(roleService.getAll());

        CropService cropService = new CropServiceImpl();
//        System.out.println(cropService.getAll());
//        cropService.insert(crop);
//        System.out.println(cropService.getAll());
//        crop.setDateToSeed(Date.valueOf("2024-04-01"));
//        crop.setDateToHarvest(Date.valueOf("2024-09-01"));
//        cropService.update(crop);
//        System.out.println(cropService.getAll());
//        cropService.deleteByName(crop.getName());
//        System.out.println(cropService.getAll());
//
        FieldService fieldService = new FieldServiceImpl();
//        System.out.println(fieldService.getAllByFarmId(1));
//        System.out.println(fieldService.getAllByFarmId(2));
//        System.out.println(fieldService.getById(2));
//        fieldService.insert(field, 2);
//        System.out.println(fieldService.getAllByFarmId(2));
//        fieldService.deleteById(field.getId());
//        System.out.println(fieldService.getAllByFarmId(2));

//
//        System.out.println(workerService.getAll());
//        workerService.insert(worker1, 1);
//        System.out.println(workerService.getAll());
//        Responsibility responsibility3 = new Responsibility();
//        resps.add(responsibility3);
//        responsibility3.setRole(role);
//        responsibility3.setTask("a");
//        responsibility3.setDescription("a");
//        Responsibility responsibility4 = new Responsibility();
//        resps.add(responsibility4);
//        responsibility4.setRole(role);
//        responsibility4.setTask("b");
//        responsibility4.setDescription("b");
//        Responsibility responsibility5 = new Responsibility();
//        resps.add(responsibility5);
//        responsibility5.setRole(role);
//        responsibility5.setTask("c");
//        responsibility5.setDescription("c");
//        List<Responsibility> resps2 = new ArrayList<>();
//        resps2.add(responsibility3);
//        resps2.add(responsibility4);
//        resps2.add(responsibility5);
//        worker1.setResponsibilities(resps2);
//
//        workerService.update(worker1, 1);
//
//        System.out.println(workerService.getAll());
//        workerService.deleteById(worker1.getId());
//        System.out.println(workerService.getById(5));
        OwnerService ownerService = new OwnerServiceImpl();
//        System.out.println(ownerService.getAll());
//        Owner owner = new Owner();
//        owner.setFirstName("a");
//        owner.setSecondName("b");
//        ownerService.update(owner);
//        System.out.println(owner.getId());
//        ownerService.deleteById(1);
//        System.out.println(ownerService.getAll());

        Farm farm = new Farm();
        farm.setName("Farmtastic hills");
        farm.setOwner(ownerService.getById(2));
        farm.setWorkers(workerService.getAll());
        farm.setFields(fieldService.getAllByFarmId(1));
        farm.setLocation("Finland");


        FarmService farmService = new FarmServiceImpl();
        System.out.println(farmService.getAll());
        farmService.insert(farm);
        System.out.println(farmService.getAll());
        farm.setFields(new ArrayList<>());
        farm.getFields().add(field);
        farmService.update(farm);
        System.out.println(farmService.getAll());
        System.out.println(farmService.getById(3));
        farmService.deleteById(3);
        System.out.println(farmService.getAll());
    }
}
