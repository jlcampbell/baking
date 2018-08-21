package com.campbell.jess.baking_app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.campbell.jess.baking_app.data.RecipeRepository;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final static String TAG = MainActivityViewModel.class.getSimpleName();

    private final RecipeRepository mRecipesRepository;



    public MainActivityViewModelFactory(RecipeRepository recipesRepository) { this.mRecipesRepository = recipesRepository; }




    @Override

    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainActivityViewModel(mRecipesRepository);
    }


}
