package com.campbell.jess.baking_app.ui;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class IngredientsUpdateService extends IntentService {
    public static final String ACTION_UPDATE_INGREDIENTS = "com.campbell.jess.baking_app.action.update_ingredients";

    public IngredientsUpdateService() {
        super("UpdateIngredientsService");
    }

    public static void startActionUpdateIngredients(Context context)


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(ACTION_UPDATE_INGREDIENTS.equals(action)) {
                //TODO MAKE SURE INTENT COMES WITH PLANT AND STEP EXTRAS
                final long recipeId = intent.getLongExtra()
            }
        }
    }
}
