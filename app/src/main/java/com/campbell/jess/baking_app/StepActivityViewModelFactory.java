package com.campbell.jess.baking_app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.campbell.jess.baking_app.data.RecipeRepository;

/**
 * Created by jlcampbell on 8/22/2018.
 */

public class StepActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final static String TAG = StepActivityViewModel.class.getSimpleName();

    private final RecipeRepository mRecipesRepository;



    public StepActivityViewModelFactory(RecipeRepository recipesRepository) { this.mRecipesRepository = recipesRepository; }




    @Override

    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new StepActivityViewModel(mRecipesRepository);
    }
}
