package com.campbell.jess.baking_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.campbell.jess.baking_app.data.RecipeRepository;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;

import java.util.List;

/**
 * Created by jlcampbell on 8/22/2018.
 */

public class SharedViewModel extends ViewModel {
    private final RecipeRepository mRecipeRepository;
    private final int mRecipeId;
    private final LiveData<Recipe> mRecipe;

    public SharedViewModel(RecipeRepository recipeRepository, int recipeId){
        mRecipeRepository = recipeRepository;
        mRecipeId = recipeId;
        mRecipe = mRecipeRepository.getRecipeById(mRecipeId);
    }

    public LiveData<Recipe> getRecipe() {
        return mRecipe;
    }

}
