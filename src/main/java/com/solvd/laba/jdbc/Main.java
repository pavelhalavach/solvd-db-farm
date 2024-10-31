package com.solvd.laba.jdbc;

import com.solvd.laba.jdbc.model.*;
import com.solvd.laba.jdbc.service.*;
import com.solvd.laba.jdbc.service.impl.*;

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
        List<Responsibility> resps2 = new ArrayList<>();
        Responsibility responsibility = new Responsibility();
        resps.add(responsibility);
        responsibility.setRole(role);
        responsibility.setTask("clean up bee hives");
        responsibility.setDescription("need to clean up 5 bee hives");
        Responsibility responsibility2 = new Responsibility();
        resps.add(responsibility2);
        resps2.add(responsibility2);
        responsibility2.setRole(role);
        responsibility2.setTask("collect honey");
        responsibility2.setDescription("collect honey from 5 hives");
        Worker worker1 = new Worker();
        worker1.setFirstName("Sam");
        worker1.setSecondName("Smith");
        worker1.setResponsibilities(resps);
        Worker worker2 = new Worker();
        worker2.setFirstName("abc");
        worker2.setSecondName("abc");
        worker2.setResponsibilities(resps2);
        Owner owner = new Owner();
        owner.setFirstName("Eva");
        owner.setSecondName("Teds");

        WorkerService workerService = new WorkerServiceImpl();
        RoleService roleService = new RoleServiceImpl();
        ResponsibilityService responsibilityService = new ResponsibilityServiceImpl();
        CropService cropService = new CropServiceImpl();
        FieldService fieldService = new FieldServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();

//        System.out.println(workerService.getById(1));
//        workerService.deleteById(1);
//        workerService.insert(worker1, 2 );
//        worker1.setResponsibilities(resps2);
//        workerService.update(worker1, 1);
//        System.out.println(workerService.getAll());


//        ownerService.deleteById(1);
//        System.out.println(ownerService.getById(1));
//        ownerService.insert(owner);
//        System.out.println(ownerService.getAll());
//        owner.setFirstName("test");
//        ownerService.update(owner);
//        System.out.println(ownerService.getAll());



//        System.out.println(responsibilityService.getById(1));
//        responsibilityService.deleteById(1);
//        responsibilityService.insert(responsibility);
//        System.out.println(responsibilityService.getAll());
//        responsibility.setTask("test");
//        responsibilityService.update(responsibility);
//        System.out.println(responsibilityService.getAll());



//        cropService.deleteByName("tomato");

//        System.out.println(roleService.getById(3));
//        System.out.println(roleService.getByProfession("vet"));
//        roleService.deleteByProfession("vet");
//        roleService.deleteByProfession("driver");
//        roleService.insert(role);
//        System.out.println(roleService.getAll());
//        role.setProfession("gggsdsdd");
//        roleService.update(role);
//        System.out.println(roleService.getAll());


//        fieldService.insert(field, 2);
//        System.out.println(field);
//        System.out.println(fieldService.getAllByFarmId(2));
//        System.out.println(fieldService.getById(2));
//        fieldService.deleteById(1);
//        System.out.println(fieldService.getAllByFarmId(1));



        Farm farm = new Farm();
        farm.setName("Farmtastic hills");
        farm.setOwner(ownerService.getById(2));
        farm.setOwner(owner);
        farm.setWorkers(new ArrayList<>());
        farm.getWorkers().add(worker1);
        farm.setFields(new ArrayList<>());
        farm.getFields().add(field);
        farm.setLocation("Finland");

        FarmService farmService = new FarmServiceImpl();
        System.out.println(farmService.getById(3).getOwner());
//        farmService.insert(farm);
//        System.out.println(farmService.getById(3));
//        farm.setFields(new ArrayList<>());
//        farm.getWorkers().add(worker2);
//        farm.setFields(fieldService.getAllByFarmId(1));
//        farm.setWorkers(new ArrayList<>());
//        farm.setId(farmService.getById(3).getId());;
//        farm.getWorkers().add(worker1);
//        farmService.update(farm);
//        System.out.println(farmService.getAll());
//        System.out.println(farmService.getById(3));
        farmService.deleteById(1);
//        System.out.println(farmService.getAll());

    }
}
