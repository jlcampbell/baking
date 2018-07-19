package com.campbell.jess.baking_app.data.remote;

/**
 * Created by jlcampbell on 7/19/2018.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    public static RecipeService getRecipeService() {
        return RetrofitClient.getClient(BASE_URL).create(RecipeService.class);
    }

}
