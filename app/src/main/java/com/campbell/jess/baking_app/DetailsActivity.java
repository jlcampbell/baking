package com.campbell.jess.baking_app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

//this class only is used only for phones to display the details fragment
//(on tablets the details fragment is displayed in the steps activity)
public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            int recipeId = getIntent().getIntExtra("recipe", 0);
            int stepId = getIntent().getIntExtra("step", 0);

            //launch using step instructions string instead
            DetailsFragment detailsFragment = DetailsFragment.newInstance(recipeId, stepId);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.details_container, detailsFragment)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
