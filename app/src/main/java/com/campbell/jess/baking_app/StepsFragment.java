package com.campbell.jess.baking_app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campbell.jess.baking_app.data.model.Ingredient;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.model.Step;
import com.campbell.jess.baking_app.data.remote.ApiUtils;
import com.campbell.jess.baking_app.data.remote.RecipeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class StepsFragment extends Fragment {
    private RecipeService mService;
    private StepRecyclerViewAdapter mAdapter;
    private int mRecipeId;

    private Recipe mRecipe;

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
            //stepListSize = mAdapter.getItemCount();

        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mService = ApiUtils.getRecipeService();
        mIngredients = (TextView) getActivity().findViewById(R.id.tv_indredients);
        loadRecipes();

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

//    public void loadRecipes(){
//        Log.d(TAG, "loadRecipes: loading recipes");
//
//        mService.getRecipes().enqueue(new Callback<List<Recipe>>() {
//            @Override
//            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
//                if(response.isSuccessful()){
//                    mRecipe = response.body().get(mRecipeId);
//                    stepListSize = mRecipe.getSteps().size();
//                    mListener.listSize(stepListSize);
//
//                    mAdapter.updateSteps(mRecipe.getSteps());
//                    populateIngredientsTV();
//                    Log.d(TAG, "success");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Recipe>> call, Throwable t) {
//                Log.d(TAG, "failure");
//            }
//        });
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setRecipeId(int recipeId) { mRecipeId = recipeId; }

}
