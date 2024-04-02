package com.healthy.food.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.annotation.Nonnull;

@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionName {

    @Nonnull String value();

}
