package com.campbell.jess.baking_app;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//may contain two fragments
public class StepsActivity extends AppCompatActivity implements StepsFragment.OnListFragmentInteractionListener {

    private static String TAG = "steps activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if(savedInstanceState == null){
            StepsFragment stepsFragment = new StepsFragment();

            int recipeId = getIntent().getIntExtra("recipe", 0);
            stepsFragment.setRecipeId(recipeId);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepsFragment)
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(int stepIndex) {
        Log.d(TAG, "click" + stepIndex);

        //make a bundle with the position
//        Bundle b = new Bundle();
//        b.putInt("step", stepIndex);

        //make an intent, add bundle to the event
//        final Intent intent = new Intent(this, StepsActivity.class);
//        intent.putExtras(b);
//        startActivity(intent);
    }
}
