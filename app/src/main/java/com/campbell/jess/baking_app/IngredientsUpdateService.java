package com.campbell.jess.baking_app;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class IngredientsUpdateService extends IntentService {
    public static final String ACTION_UPDATE_INGREDIENTS = "com.campbell.jess.baking_app.action.update_ingredients";

//    public static final String EXTRA_RECIPE_ID = "com.campbell.jess.baking_app.extra.RECIPE_ID";

    public IngredientsUpdateService() {
        super("UpdateIngredientsService");
    }

//  this method can be called outside the class to trigger the ingredients text to be updated in
//  the widget
    public static void startActionUpdateIngredients(Context context, int recipeId){
        Intent intent = new Intent(context, IngredientsUpdateService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENTS);
        intent.putExtra(context.getString(R.string.EXTRA_RECIPE_ID), recipeId);
        context.startService(intent);
    };


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(ACTION_UPDATE_INGREDIENTS.equals(action)) {
                //TODO MAKE SURE INTENT COMES WITH recipe EXTRAS
                final int recipeId = intent.getIntExtra(getString(R.string.EXTRA_RECIPE_ID), 0);
                handleActionUpdateIngredients(recipeId);
            }
        }
    }

    private void handleActionUpdateIngredients(int recipeId) {
//todo fill in body of recipe
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidget.class));
        //update all widgets
        IngredientsWidget.updateIngredientWidgets(this, appWidgetManager, recipeId, appWidgetIds);
    }


}
