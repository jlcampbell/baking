package com.campbell.jess.baking_app.data.remote;

import com.campbell.jess.baking_app.data.model.Recipe;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jlcampbell on 7/19/2018.
 */

public interface RecipeService {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    //TODO FIX THIS I THINK THIS CALL SHOULD NOT BE OF TYPE RECIPE
    Call<Recipe> getRecipes();
}
