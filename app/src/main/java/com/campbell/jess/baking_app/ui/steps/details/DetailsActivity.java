package com.campbell.jess.baking_app.ui.steps.details;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.campbell.jess.baking_app.R;

//this class only is used only for phones to display the details fragment
//(on tablets the details fragment is displayed in the steps activity)
public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener, VideoFragment.OnFragmentInteractionListener {
    private int recipeId;
    private int stepId;
    private int mStepSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button nextButton = (Button) findViewById(R.id.btn_next);
        Button previousButton = (Button) findViewById(R.id.btn_previous);

        if (savedInstanceState == null) {
            recipeId = getIntent().getIntExtra("recipe", 0);
            stepId = getIntent().getIntExtra("step", 0);
            mStepSize = getIntent().getIntExtra("listSize", 0);

            //launch using step instructions string instead
            final DetailsFragment detailsFragment = DetailsFragment.newInstance(recipeId, stepId);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.details_container, detailsFragment)
                    .commit();

            final VideoFragment videoFragment = VideoFragment.newInstance(recipeId, stepId, "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

            fragmentManager.beginTransaction()
                    .add(R.id.video_container, videoFragment)
                    .commit();

            //on next button pressed
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (stepId < mStepSize-1) {
                        stepId = stepId+1;
                    } else {
                        stepId = 0;
                    }
                        DetailsFragment nextDetailsFragment = DetailsFragment.newInstance(recipeId, stepId);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.details_container, nextDetailsFragment)
                                .commit();

                        VideoFragment nextVideoFragment = VideoFragment.newInstance(recipeId, stepId, "");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.video_container, nextVideoFragment)
                                .commit();
                    }


            });

            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (stepId > 0) {
                        stepId = stepId-1;
                    } else {
                        stepId = mStepSize-1;
                    }
                        DetailsFragment previousDetailsFragment = DetailsFragment.newInstance(recipeId, stepId);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.details_container, previousDetailsFragment)
                                .commit();

                        VideoFragment previousVideoFragment = VideoFragment.newInstance(recipeId, stepId, "");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.video_container, previousVideoFragment)
                                .commit();

                }

            });
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
