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
    private LiveData<List<Recipe>> mAllRecipes;

    private StepDao mStepDao;
    private LiveData<List<Step>> mAllSteps;

    private IngredientDao mIngredientDao;
    private LiveData<List<Ingredient>> mAllIngredients;


    RecipeRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mIngredientDao = db.ingredientDao();
        mStepDao = db.stepDao();

        mAllRecipes = mRecipeDao.getAllRecipes();
        mAllSteps = mStepDao.getAllSteps();
        mAllIngredients = mIngredientDao.getAllIngredients();
    }

    //get live data
    LiveData<List<Recipe>> getAllRecipes(){
        return mAllRecipes;
    }
    LiveData<List<Step>> getAllSteps(){
        return mAllSteps;
    }
    LiveData<List<Ingredient>> getAllIngredients(){
        return mAllIngredients;
    }

    //insert data into daos
    private static class insertAsyncRecipe extends AsyncTask<Recipe, Void, Void> {
        private RecipeDao mAsyncRecipeDao;

        insertAsyncRecipe(RecipeDao dao) {
            mAsyncRecipeDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipe... params) {
            mAsyncRecipeDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncStep extends AsyncTask<Step, Void, Void> {
        private StepDao mAsyncStepDao;

        insertAsyncStep(StepDao dao) {
            mAsyncStepDao = dao;
        }

        @Override
        protected Void doInBackground(final Step... params) {
            mAsyncStepDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncIngredient extends AsyncTask<Ingredient, Void, Void> {
        private IngredientDao mAsyncIngredientDao;

        insertAsyncIngredient(IngredientDao dao) {
            mAsyncIngredientDao = dao;
        }

        @Override
        protected Void doInBackground(final Ingredient... params) {
            mAsyncIngredientDao.insert(params[0]);
            return null;
        }
    }





}
