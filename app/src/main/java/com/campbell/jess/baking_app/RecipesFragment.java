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
import android.widget.Adapter;
import android.widget.AdapterView;

import com.campbell.jess.baking_app.data.model.Recipe;

import com.campbell.jess.baking_app.data.remote.ApiUtils;
import com.campbell.jess.baking_app.data.remote.RecipeService;
import com.campbell.jess.baking_app.dummy.DummyContent;
import com.campbell.jess.baking_app.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RecipesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;

    private RecipeService mService;
    private MyRecipeRecyclerViewAdapter mAdapter;

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Recipe recipe);
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")

    public static RecipesFragment newInstance(int columnCount) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //recipes=baking_data_utils.getBakingData(getContext());
        mService = ApiUtils.getRecipeService();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        loadRecipes();
    }

    public void loadRecipes(){
        Log.d(TAG, "loadRecipes: loading recipes");
        mService.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    //TODO iterate through response.body to populate a list or something that can
                    mAdapter.updateRecipes(response.body());
                    Log.d(TAG, "success");
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter = new MyRecipeRecyclerViewAdapter(new ArrayList<Recipe>(0), mListener);

            //set the adapter on the rv
            recyclerView.setAdapter(mAdapter);
/**
            recyclerView.setOnClickListener(new AdapterView.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (view instanceof )
                    mListener.onListFragmentInteraction();
                }


            });
 **/
            //recyclerView.setAdapter(new MyRecipeRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
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
    public void onDetach() {
        super.onDetach();
       mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */



}
