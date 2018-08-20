package com.campbell.jess.baking_app.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.campbell.jess.baking_app.data.model.Recipe;

import java.util.List;

/**
 * Created by jlcampbell on 8/20/2018.
 */

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe_table")
    LiveData<List<Recipe>> getAllRecipes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAll();
}
