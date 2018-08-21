package com.campbell.jess.baking_app.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.campbell.jess.baking_app.data.model.Ingredient;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;

import java.util.List;

/**
 * Created by jlcampbell on 8/20/2018.
 */

//get the network data in here somewhere
public class RecipeRepository {
    private RecipeDao mRecipeDao;
    private final RecipeNetworkDataSource mRecipeNetworkDataSource;

    private static final Object LOCK = new Object();

    private final AppExecutors mExecutors;

    private static RecipeRepository sInstance;

    private LiveData<List<Recipe>> mAllRecipes;
    private LiveData<List<Step>> mAllSteps;
    private LiveData<List<Ingredient>> mAllIngredients;

    private boolean mInitialized;



    private RecipeRepository(Application application, RecipeNetworkDataSource recipeNetworkDataSource, AppExecutors appExecutors) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mRecipeNetworkDataSource = recipeNetworkDataSource;
        mExecutors = appExecutors;
        mInitialized = false;

//        mAllRecipes = mRecipeDao.getAllRecipes();
//        mAllSteps = mRecipeDao.getAllSteps();
//        mAllIngredients = mRecipeDao.getAllIngredients();

        //get the data from the network data source and insert into DAO
        LiveData<List<Recipe>> recipeNetworkData = mRecipeNetworkDataSource.getRecipes();
        recipeNetworkData.observeForever(newRecipes -> {
            mExecutors.diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    for (Recipe recipe: newRecipes
                         ) {
                        mRecipeDao.insert(recipe);
                    }
                }
            });

        });

    }
    //get instance
    public synchronized static RecipeRepository getInstance(Application application, RecipeNetworkDataSource recipeNetworkDataSource, AppExecutors appExecutors ){
        if (sInstance == null){
            synchronized (LOCK) {
                sInstance = new RecipeRepository(application, recipeNetworkDataSource, appExecutors);
            }
        }
        return sInstance;
    }

    //initialize data - in this case only occurs once because data is static
    private synchronized void initializeData() {
        if (!mInitialized) {
            mRecipeNetworkDataSource.loadRecipes();
            mInitialized = true;
        }
    }


    //database operations- called by view models to get recipes from DAO
    public LiveData<List<Recipe>> getRecipes() {
        initializeData();
        return mRecipeDao.getAllRecipes();
    }

    }






