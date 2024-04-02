package com.healthy.food.Repository;


import com.healthy.food.Ingredients.RawMaterials;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Repository
public class RawMaterialsRepository {


    // private List <RawMaterials> rawMaterialsList= new ArrayList<RawMaterials>();
    // // @PostConstruct
    // // private void init() {
    // //     rawMaterialsList.add(new RawMaterials("Beef", 210.0, 20.0, 30.0, 1800.0));
    // //     rawMaterialsList.add(new RawMaterials("Chicken Breast", 315.4, 100.0, 30.0, 1300.3));
    // //     rawMaterialsList.add(new RawMaterials("Pork", 210.0, 20.0, 30.0, 1800.0));
    // //     rawMaterialsList.add(new RawMaterials("Belly", 210.0, 20.0, 30.0, 1800.0));
    // // }
    // public Optional<RawMaterials> getOne(String name) {
    //     if (rawMaterialsList.isEmpty()) return null;
    //         return rawMaterialsList.stream()
    //         .filter(rawMaterials -> rawMaterials.getName().equals(name))
    //         .findFirst();
    // }
    // public List<RawMaterials> findAll() {
    //     if (rawMaterialsList.isEmpty()) return null;
    //     return rawMaterialsList;
    // }

    // public void save(RawMaterials rawMaterials) {
    //     rawMaterialsList.add(rawMaterials);
    // }

    // public void update(RawMaterials rawMaterials, String name) {

    //     Optional<RawMaterials> exist = getOne(name);
    //     if (rawMaterialsList.indexOf(exist.get()) >= 0) {
    //         rawMaterialsList.set(rawMaterialsList.indexOf(exist.get()), rawMaterials);
    //     }

    // }

    // public void delete(String name) {

    //     Optional<RawMaterials> exist = getOne(name);
    //     if (rawMaterialsList.indexOf(exist.get()) >= 0) {
    //         rawMaterialsList.remove(exist.get());
    //     }
    // }
}