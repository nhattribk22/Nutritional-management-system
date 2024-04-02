package com.healthy.food.Controller;

import com.google.cloud.firestore.WriteResult;
import com.healthy.food.Ingredients.RawMaterials;
import com.healthy.food.Repository.RawMaterialsRepository;
import com.healthy.food.Service.FoodService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/Home/Ingredients")
public class Controll {


    public FoodService foodService;

    public Controll(FoodService foodService){
        this.foodService = foodService;
    }
    @GetMapping("/Firebase/get")
    public RawMaterials getOneFromFirebase(@RequestParam String name) throws InterruptedException, ExecutionException {
        // return
        RawMaterials result = foodService.getMaterials(name);
        return result;
    }
    
    @PutMapping("/Firebase/update")
    public String UpdateForFirebase(@RequestBody @Valid RawMaterials rawMaterials, @RequestParam String name) throws InterruptedException, ExecutionException {
        //TODO: process PUT request
        
        return foodService.updateMaterials(rawMaterials, name);
    }

    @DeleteMapping("/Firebase/delete")
    public String DeleteForFirebase(@RequestParam String name) throws InterruptedException, ExecutionException {
        //TODO: process DELETE request
        return foodService.deleteMaterials(name);
    }

    @PostMapping("Firebase/create")
    public String saveForFirebase(@RequestBody @Valid RawMaterials rawMaterials, @RequestParam String name) throws InterruptedException, ExecutionException {
        //TODO: process POST request
        
        return foodService.createMaterials(rawMaterials, name);
    }

}
