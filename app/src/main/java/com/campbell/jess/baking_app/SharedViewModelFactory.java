package com.campbell.jess.baking_app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.campbell.jess.baking_app.data.RecipeRepository;

/**
 * Created by jlcampbell on 8/22/2018.
 */

public class SharedViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final static String TAG = SharedViewModel.class.getSimpleName();

    private final RecipeRepository mRecipesRepository;
    private final int mRecipeId;


    public SharedViewModelFactory(RecipeRepository recipesRepository, int recipeId) {
        this.mRecipesRepository = recipesRepository;
        this.mRecipeId = recipeId;}




    @Override

    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SharedViewModel(mRecipesRepository, mRecipeId);
    }
}
