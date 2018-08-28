package com.campbell.jess.baking_app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.campbell.jess.baking_app.ui.main.MainActivity;
import com.campbell.jess.baking_app.ui.steps.StepsActivity;

/**
 * Implementation of App Widget functionality.
 *
 * need a ingredients switching service
 * that determines which recipe we are currently looking at
 *
 *
 */
public class IngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int recipeId,
                                int appWidgetId) {
        //create intent to launch stepsActivity (where ingredients are located)
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("recipeId",recipeId);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        views.setOnClickPendingIntent(R.id.button, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        IngredientsUpdateService.startActionUpdateIngredients(context);
    }

    public static void updateIngredientWidgets(Context context, AppWidgetManager appWidgetManager, int recipeId, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipeId, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

