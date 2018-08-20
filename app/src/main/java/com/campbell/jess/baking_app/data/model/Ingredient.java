package com.campbell.jess.baking_app.data.model;

/**
 * Created by jlcampbell on 7/19/2018.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ingredient_table")
public class Ingredient {

    //table columns
    @SerializedName("quantity")
    @Expose
    private Number quantity;

    @SerializedName("measure")
    @Expose
    private String measure;

    @PrimaryKey
    @NonNull
    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    //constructor
    public Ingredient(@NonNull String ingredient, Number quantity, String measure) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measure = measure;
    }


    //getters and setters
    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

}