package com.healthy.food.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer Id;
    private String Gender;
    protected Double Weight;
    protected Double Height;
    private Integer Age;
    protected Integer WorkoutPerWeek;
}
