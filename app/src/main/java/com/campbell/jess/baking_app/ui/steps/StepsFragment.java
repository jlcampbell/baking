package com.campbell.jess.baking_app.ui.steps;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campbell.jess.baking_app.utils.InjectorUtils;
import com.campbell.jess.baking_app.R;
import com.campbell.jess.baking_app.data.model.Ingredient;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;
import com.campbell.jess.baking_app.data.remote.ApiUtils;
import com.campbell.jess.baking_app.data.remote.RecipeService;

import java.util.ArrayList;
import java.util.List;

public class StepsFragment extends Fragment {
    private RecipeService mService;
    private StepRecyclerViewAdapter mAdapter;
    private int mRecipeId;

    private Recipe mRecipe;
    private SharedViewModel mViewModel;

    private int stepListSize;

    private List<Ingredient> ingredientsList;
    private TextView mIngredients;

    private OnListFragmentInteractionListener mListener;

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int position);
        void listSize(int stepListSize);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            mAdapter = new StepRecyclerViewAdapter(new ArrayList<Step>(0), mListener);

            //set the adapter on the rv
            recyclerView.setAdapter(mAdapter);

        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mService = ApiUtils.getRecipeService();
        mIngredients = (TextView) getActivity().findViewById(R.id.tv_indredients);
        SharedViewModelFactory factory = InjectorUtils.provideSharedActivityViewModelFactory(getContext(), mRecipeId, getActivity().getApplication() );
        mViewModel = ViewModelProviders.of(getActivity(), factory).get(SharedViewModel.class);
        loadRecipeDataFromViewModel();
    }

    public void loadRecipeDataFromViewModel() {

        mViewModel.getRecipe().observe(this, recipe -> {
            mRecipe = recipe;
            Log.d("Steps fragment", mRecipe.getName());
            populateUI();
        });
    }

    private void populateUI(){
        populateStepsRV();
        populateIngredientsTV();
    }

    private void populateStepsRV(){
        stepListSize = mRecipe.getSteps().size();
        mListener.listSize(stepListSize);
        mAdapter.updateSteps(mRecipe.getSteps());
    }

    private void populateIngredientsTV(){
        ingredientsList = mRecipe.getIngredients();
        String ingredientString = "";
        for (Ingredient ingredient: ingredientsList
             ) {
            ingredientString += ingredient.getIngredient();

        }
        Log.d("ingredients", ingredientString);
        mIngredients.setText(ingredientString);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setRecipeId(int recipeId) { mRecipeId = recipeId; }

}
