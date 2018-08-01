package com.campbell.jess.baking_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campbell.jess.baking_app.data.model.Recipe;

import java.util.List;

public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepRecyclerViewAdapter.ViewHolder> {
    private String TAG = "rv adapter";
    private List<Recipe> mRecipes;
    private final RecipesFragment.OnListFragmentInteractionListener mListener;

    public StepRecyclerViewAdapter(List<Recipe> recipes, RecipesFragment.OnListFragmentInteractionListener listener) {
        mRecipes = recipes;
        mListener = listener;
    }

    @Override
    public StepRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_step, parent, false);
        return new StepRecyclerViewAdapter.ViewHolder(view);
    }

    public void updateSteps(List<Recipe> steps) {
        mRecipes = steps;
        notifyDataSetChanged();
    }
    

    @Override
    public void onBindViewHolder(@NonNull StepRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Recipe mRecipe;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
}
