package com.campbell.jess.baking_app.model;

public class Recipe {
    private int mId;
    private String mName;
    private Object[] mIngredients;
    private Object[] mSteps;
    private int mNumberOfSteps;
    private String mImgUrl;

    public Recipe(int id, String name, Object[] ingredients, Object[] steps, int numberOfSteps, String url){
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
    public Object[] getIngredients(){
        return mIngredients;
    }
    public Object[] getSteps(){
        return mSteps;
    }
    public int getNumberOfSteps(){
        return mNumberOfSteps;
    }
    public String getImageUrl(){
        return mImgUrl;
    }


}
