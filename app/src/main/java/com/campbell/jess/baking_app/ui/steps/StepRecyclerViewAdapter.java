package com.campbell.jess.baking_app.ui.steps;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campbell.jess.baking_app.R;

import com.campbell.jess.baking_app.data.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepRecyclerViewAdapter.ViewHolder> {
    private String TAG = "rv adapter";
    private List<Step> mSteps;
    private final StepsFragment.OnListFragmentInteractionListener mListener;

    public StepRecyclerViewAdapter(ArrayList<Step> steps, StepsFragment.OnListFragmentInteractionListener listener) {
        mSteps = steps;
        mListener = listener;
    }

    @Override
    public StepRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_step, parent, false);
        return new StepRecyclerViewAdapter.ViewHolder(view);
    }

    public void updateSteps(List<Step> steps) {
        mSteps = steps;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull StepRecyclerViewAdapter.ViewHolder holder, final int position) {
        Step step = mSteps.get(position);
        holder.mContentView.setText(step.getShortDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(position);
                    Log.d(TAG, "onClick: click");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mSteps != null){
            return mSteps.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Step mStep;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.step_short_description);
        }
}}
