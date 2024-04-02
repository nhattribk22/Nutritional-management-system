package com.healthy.food.User;

public class BMI extends User  {

    public Double CalBMI() {
        return Weight/(Height*Height);

    }
}
