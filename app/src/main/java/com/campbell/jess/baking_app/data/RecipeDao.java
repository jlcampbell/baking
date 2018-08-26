package com.campbell.jess.baking_app.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.campbell.jess.baking_app.data.model.Ingredient;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;

import java.util.List;

/**
 * Created by jlcampbell on 8/20/2018.
 */

@Dao
public interface RecipeDao {

    ////////////recipes
    @Query("SELECT * FROM recipe_table")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    LiveData<Recipe> getRecipeById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(Recipe... recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAllRecipes();

    /////////////steps
    @Query("SELECT * FROM step_table")
    LiveData<List<Step>> getAllSteps();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStep(Step step);

    @Query("DELETE FROM step_table")
    void deleteAllSteps();

    /////////////ingredients
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(Ingredient ingredient);

    @Query("DELETE FROM ingredient_table")
    void deleteAllIngredients();

    @Query("SELECT * FROM ingredient_table")
    LiveData<List<Ingredient>> getAllIngredients();






}
