package com.campbell.jess.baking_app.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.campbell.jess.baking_app.data.model.Ingredient;

import java.util.List;

/**
 * Created by jlcampbell on 8/20/2018.
 */

@Dao
public interface IngredientDao {
    @Insert
    void insert(Ingredient ingredient);

    @Query("DELETE FROM ingredient_table")
    void deleteAll();

    @Query("SELECT * FROM ingredient_table")
    List<Ingredient> getAllIngredients();

}