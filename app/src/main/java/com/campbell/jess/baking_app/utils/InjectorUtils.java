package com.campbell.jess.baking_app.utils;

import android.app.Application;
import android.content.Context;

import com.campbell.jess.baking_app.data.AppExecutors;
import com.campbell.jess.baking_app.data.RecipeNetworkDataSource;
import com.campbell.jess.baking_app.data.RecipeRepository;
import com.campbell.jess.baking_app.ui.main.MainActivityViewModelFactory;
import com.campbell.jess.baking_app.ui.steps.SharedViewModelFactory;

public class InjectorUtils {
    private static String log_tag = "injectorUtils";
    public static RecipeRepository provideRepository(Context context, Application application) {
        AppExecutors executors = AppExecutors.getInstance();
        RecipeNetworkDataSource networkDataSource = RecipeNetworkDataSource.getsInstance(context.getApplicationContext());
        return RecipeRepository.getInstance(application, networkDataSource, executors);
    }


    public static MainActivityViewModelFactory provideMainActivityViewModelFactory(Context context, Application application) {
        RecipeRepository recipeRepository = provideRepository(context, application);
        return new MainActivityViewModelFactory(recipeRepository);
    }

    public static SharedViewModelFactory provideSharedActivityViewModelFactory(Context context, int id, Application application) {
        RecipeRepository recipeRepository = provideRepository(context, application);
        return new SharedViewModelFactory(recipeRepository, id);
    }
}
