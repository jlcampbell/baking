package com.campbell.jess.baking_app.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.campbell.jess.baking_app.data.RecipeRepository;
import com.campbell.jess.baking_app.data.model.Recipe;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private final RecipeRepository mRecipeRepository;

    private final LiveData<List<Recipe>> mRecipes;

    public MainActivityViewModel(RecipeRepository recipeRepository){
        mRecipeRepository = recipeRepository;
        mRecipes = mRecipeRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

}
