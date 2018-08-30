package com.campbell.jess.baking_app.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.campbell.jess.baking_app.IngredientsUpdateService;
import com.campbell.jess.baking_app.R;
import com.campbell.jess.baking_app.ui.steps.StepsActivity;


public class MainActivity extends AppCompatActivity implements RecipesFragment.OnListFragmentInteractionListener {
    private String TAG = "main activity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipesFragment recipesFragment = new RecipesFragment();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.recipes_container, recipesFragment)
                .commit();
    }



    @Override
    public void onListFragmentInteraction(int position) {
        Log.d(TAG, "position" + position);
        int recipeIndex = position+1;

        IngredientsUpdateService.startActionUpdateIngredients(this, recipeIndex );


        Log.d(TAG, "recipe index" + recipeIndex);
        //make a bundle with the position
        Bundle b = new Bundle();
        b.putInt(getString(R.string.EXTRA_RECIPE_ID), recipeIndex);

        //make an intent, add bundle to the event
        final Intent intent = new Intent(this, StepsActivity.class);
        intent.putExtras(b);
        startActivity(intent);

        //update widget
        //todo add code to update widget with the recipe we selected
        IngredientsUpdateService.startActionUpdateIngredients(this, recipeIndex);
    }
}
