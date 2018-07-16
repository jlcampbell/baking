package com.campbell.jess.baking_app.model;

import org.json.JSONArray;

public class Recipe {
    private int mId;
    private String mName;
    private JSONArray mIngredients;
    private JSONArray mSteps;
    private int mNumberOfSteps;
    private String mImgUrl;

    public Recipe(int id, String name, JSONArray ingredients, JSONArray steps, int numberOfSteps, String url){
        this.mId = id;
        this.mName = name;
        this.mIngredients = ingredients;
        this.mSteps = steps;
        this.mNumberOfSteps = numberOfSteps;
        this.mImgUrl = url;
    }

    public int getId(){
        return mId;
    }
    public String getName(){
        return mName;
    }
    public JSONArray getIngredients(){
        return mIngredients;
    }
    public JSONArray getSteps(){
        return mSteps;
    }
    public int getNumberOfSteps(){
        return mNumberOfSteps;
    }
    public String getImageUrl(){
        return mImgUrl;
    }


}
