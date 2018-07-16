package com.campbell.jess.baking_app.recipe_data;

import android.content.Context;

import com.campbell.jess.baking_app.R;
import com.campbell.jess.baking_app.model.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

public class baking_data_utils {
    private JSONObject[] jsonObject;
    private Context mContext;
    private String[] titles;
    private Recipe[] recipes;
    private String[] imgUrls;

    public void getBakingData(Context context){
        String bakingJson = context.getResources().getString(R.string.baking_json);
        try {
            //JSONArray bakingJsonArray = new JSONArray(bakingJson);
            recipes = recipeUtils.getRecipes(context, bakingJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

}
