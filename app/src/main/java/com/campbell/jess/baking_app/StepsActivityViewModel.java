package com.campbell.jess.baking_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.campbell.jess.baking_app.data.RecipeRepository;
import com.campbell.jess.baking_app.data.model.Step;

import java.util.List;

/**
 * Created by jlcampbell on 8/22/2018.
 */

public class StepsActivityViewModel extends ViewModel {
    private final RecipeRepository mRecipeRepository;
    private final int mRecipeId;
    private final LiveData<List<Step>> mSteps;

    public StepsActivityViewModel(RecipeRepository recipeRepository, int recipeId){
        mRecipeRepository = recipeRepository;
        mRecipeId = recipeId;
        mSteps = mRecipeRepository.getRecipes().getValue().
    }
}
