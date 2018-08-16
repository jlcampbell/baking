package com.campbell.jess.baking_app;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
//may contain two fragments
public class StepsActivity extends AppCompatActivity implements StepsFragment.OnListFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener {

    private static String TAG = "steps activity";
    private int mRecipeId;

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if (findViewById(R.id.details_linear_layout) != null){
            mTwoPane = true;

            int recipeId = getIntent().getIntExtra("recipe", 0);
            int stepId = 0;

            //launch using step instructions string instead
            DetailsFragment detailsFragment = DetailsFragment.newInstance(recipeId, stepId);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.details_container, detailsFragment)
                    .commit();

            VideoFragment videoFragment = VideoFragment.newInstance(recipeId, stepId, "");

            fragmentManager.beginTransaction()
                    .add(R.id.video_container, videoFragment)
                    .commit();



        if(savedInstanceState == null){
            StepsFragment stepsFragment = new StepsFragment();

            mRecipeId = getIntent().getIntExtra("recipe", 0);
            stepsFragment.setRecipeId(mRecipeId);

//            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepsFragment)
                    .commit();
        }
    }
    }

    @Override
    public void onListFragmentInteraction(int stepIndex) {
        Log.d(TAG, "click" + stepIndex);

        //make a bundle with the position
        Bundle b = new Bundle();
        b.putInt("recipe", mRecipeId);
        b.putInt("step", stepIndex);

        //make an intent, add bundle to the event
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
