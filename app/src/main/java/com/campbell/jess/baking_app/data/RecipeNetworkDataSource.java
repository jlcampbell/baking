package com.campbell.jess.baking_app.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.remote.ApiUtils;
import com.campbell.jess.baking_app.data.remote.RecipeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RecipeNetworkDataSource {
    private static final String LOG_TAG = RecipeNetworkDataSource.class.getSimpleName();

    private RecipeService mService;
    private final MutableLiveData<List<Recipe>> mDownloadedRecipes;

    // For singleton instantiation
    private static final Object LOCK = new Object();
    private static RecipeNetworkDataSource sInstance;
    private final Context mContext;

    private RecipeNetworkDataSource(Context context){
        mContext = context;
        mService = ApiUtils.getRecipeService();
        mDownloadedRecipes = new MutableLiveData<List<Recipe>>();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mDownloadedRecipes;
    }

    public void loadRecipes(){
        Log.d(TAG, "loadRecipes: loading recipes");
        mService.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    //mAdapter.updateRecipes(response.body());
                    mDownloadedRecipes.postValue(response.body());
                    Log.d(TAG, "success");
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }

     /**
     * Get the singleton for this class
     */
    public static RecipeNetworkDataSource getsInstance(Context context){
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeNetworkDataSource(context.getApplicationContext());
                Log.d(LOG_TAG, "made new network data source");

            }
        }
        if (sInstance != null) {
            Log.d(LOG_TAG, "sInstance not null");
        }
        return sInstance;
    }

}
