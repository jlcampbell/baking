package com.campbell.jess.baking_app.recipe_data;

import android.content.Context;
import android.util.Log;

import com.campbell.jess.baking_app.R;
import com.campbell.jess.baking_app.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class recipeUtils {
    private static String TAG = "recipeUtils";

    private static int RECIPE_ID;
    private static String RECIPE_NAME;
    private static Object[] RECIPE_INGREDIENTS;
    private static Object[] RECIPE_STEPS;
    private static int RECIPE_SERVINGS;
    private static String RECIPE_IMG_URL;






    /**
     *
     * @param context
     * @param bakingJson a JSON string containing all of the recipe data
     * @return a list of recipe objects
     * @throws JSONException
     */

    public static String[] getRecipes(Context context, String bakingJson) throws JSONException {
        JSONArray bakingJsonArray = new JSONArray(bakingJson);
        Recipe[] recipes = new Recipe[Objects.requireNonNull(bakingJsonArray).length()];

        for(int i=0; i<bakingJsonArray.length(); i++){
            JSONObject recipe = bakingJsonArray.getJSONObject(i);
            try{
                int id = recipe.getInt(context.getResources().)
            }









        }

    }
    }
