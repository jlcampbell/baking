package com.campbell.jess.baking_app.ui.steps.details;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campbell.jess.baking_app.InjectorUtils;
import com.campbell.jess.baking_app.R;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.remote.ApiUtils;
import com.campbell.jess.baking_app.data.remote.RecipeService;
import com.campbell.jess.baking_app.ui.steps.SharedViewModel;
import com.campbell.jess.baking_app.ui.steps.SharedViewModelFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RECIPE = "recipe";
    private static final String ARG_STEP = "step";

    private RecipeService mService;
    private SharedViewModel mViewModel;
    private Recipe mRecipe;

    private TextView mRecipeDetailTV;
    private TextView mIngredients;

    // TODO: Rename and change types of parameters
    private int mRecipeId;
    private int mStepId;

    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(int param1, int param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE, param1);
        args.putInt(ARG_STEP, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mService = ApiUtils.getRecipeService();
        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE);
            mStepId = getArguments().getInt(ARG_STEP);
        }
        SharedViewModelFactory factory = InjectorUtils.provideSharedActivityViewModelFactory(getContext(), mRecipeId, getActivity().getApplication() );
        mViewModel = ViewModelProviders.of(getActivity(), factory).get(SharedViewModel.class);
        loadRecipeDataFromViewModel();


        Log.d("oncreate", String.valueOf(mRecipeId));
        Log.d("oncreate", String.valueOf(mStepId));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle onSavedInstanceState){
        super.onActivityCreated(onSavedInstanceState);
        mRecipeDetailTV = (TextView) getActivity().findViewById(R.id.recipe_detail_instruction);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public void loadRecipeDataFromViewModel() {

        mViewModel.getRecipe().observe(this, recipe -> {
            mRecipe = recipe;
            populateUI();
        });
    }

    public void populateUI(){

        mRecipeDetailTV.setText(mRecipe.getSteps().get(mStepId).getDescription());
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
