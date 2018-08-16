package com.campbell.jess.baking_app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

//this class only is used only for phones to display the details fragment
//(on tablets the details fragment is displayed in the steps activity)
public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button nextButton = (Button) findViewById(R.id.btn_next);
        Button previousButton = (Button) findViewById(R.id.btn_previous);

        if (savedInstanceState == null) {
            int recipeId = getIntent().getIntExtra("recipe", 0);
            int stepId = getIntent().getIntExtra("step", 0);

            //launch using step instructions string instead
            DetailsFragment detailsFragment = DetailsFragment.newInstance(recipeId, stepId);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.details_container, detailsFragment)
                    .commit();

            VideoFragment videoFragment = VideoFragment.newInstance(recipeId, stepId, "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

            fragmentManager.beginTransaction()
                    .add(R.id.video_container, videoFragment)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
