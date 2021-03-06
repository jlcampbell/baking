package com.campbell.jess.baking_app;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
    public void onListFragmentInteraction(int recipeIndex) {
        Log.d(TAG, "click" + recipeIndex);
        //make a bundle with the position
        Bundle b = new Bundle();
        b.putInt("recipe", recipeIndex);

        //make an intent, add bundle to the event
        final Intent intent = new Intent(this, StepsActivity.class);
        intent.putExtras(b);
        startActivity(intent);

    }
}
