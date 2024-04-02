package com.healthy.food.Ingredients;


import com.healthy.food.Service.CollectionName;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CollectionName("Ingredient")
public class RawMaterials {

    @Positive
    private Double Protein;
    @Positive
    private Double Carb;
    @Positive
    private Double Fat;
    @Positive
    private Double Calories;

    // public RawMaterials(String name, Double Protein, Double Carb, Double Fat, Double Calories) {
        // this.name = name;
        // this.Protein = Protein;
        // this.Calories = Calories;
        // this.Fat = Fat;
        // this.Carb = Carb;
    // }

//    private
}
