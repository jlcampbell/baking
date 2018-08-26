package com.campbell.jess.baking_app.data;

import com.campbell.jess.baking_app.data.model.Ingredient;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by jlcampbell on 8/21/2018.
 */

public  class RecipesTypeConverter {

    static Gson gson = new Gson();

    @android.arch.persistence.room.TypeConverter
    public static List<Step> stringToStepList(String data){
        if(data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Step>>(){}.getType();
        return gson.fromJson(data, listType);
    }

    @android.arch.persistence.room.TypeConverter
    public static String stepListToString(List<Step> objectList){
        return gson.toJson(objectList);
    }

    @android.arch.persistence.room.TypeConverter
    public static List<Recipe> stringToRecipeList(String data){
        if(data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Recipe>>(){}.getType();
        return gson.fromJson(data, listType);
    }

    @android.arch.persistence.room.TypeConverter
    public static String RecipeListToString(List<Recipe> objectList){
        return gson.toJson(objectList);
    }

    @android.arch.persistence.room.TypeConverter
    public static List<Ingredient> stringToIngredientList(String data){
        if(data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Ingredient>>(){}.getType();
        return gson.fromJson(data, listType);
    }

    @android.arch.persistence.room.TypeConverter
    public static String IngredientListToString(List<Ingredient> objectList){
        return gson.toJson(objectList);
    }

    @android.arch.persistence.room.TypeConverter
    public static String NumberToString(Number number){
        return number.toString();
    }

    @android.arch.persistence.room.TypeConverter
    public static Number StringToNumber(String string){
        return Float.valueOf(string);
    }


}
