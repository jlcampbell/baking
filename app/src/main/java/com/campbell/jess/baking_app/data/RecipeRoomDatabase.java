package com.campbell.jess.baking_app.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.campbell.jess.baking_app.data.model.Ingredient;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;

/**
 * Created by jlcampbell on 8/20/2018.
 */

@Database(entities = {Recipe.class, Step.class, Ingredient.class}, version = 1)
public abstract class RecipeRoomDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    private static RecipeRoomDatabase INSTANCE;

    static RecipeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class, "recipe_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
