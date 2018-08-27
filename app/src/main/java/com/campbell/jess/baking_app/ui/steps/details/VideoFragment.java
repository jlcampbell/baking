package com.campbell.jess.baking_app.ui.steps.details;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campbell.jess.baking_app.InjectorUtils;
import com.campbell.jess.baking_app.R;
import com.campbell.jess.baking_app.ui.steps.SharedViewModel;
import com.campbell.jess.baking_app.ui.steps.SharedViewModelFactory;
import com.campbell.jess.baking_app.data.model.Recipe;
import com.campbell.jess.baking_app.data.remote.ApiUtils;
import com.campbell.jess.baking_app.data.remote.RecipeService;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RECIPE_ID = "recipeID";
    private static final String STEP_ID = "stepID" ;
    private static final String STEP_VIDEO = "video";

    private RecipeService mService;
    private Recipe mRecipe;

    private SharedViewModel mViewModel;

    private SimpleExoPlayer mExoPlayer;
    private PlayerView mPlayerView;


    private static int mRecipeId;
    private static int mStepId;
    private static String mStepVideo;

    private OnFragmentInteractionListener mListener;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    public static VideoFragment newInstance(int param1, int param2, String param3) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putInt(RECIPE_ID, param1);
        args.putInt(STEP_ID, param2);
        args.putString(STEP_VIDEO, param3);
//        mRecipeId = param1;
//        mStepId = param2;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = ApiUtils.getRecipeService();
        SharedViewModelFactory factory = InjectorUtils.provideSharedActivityViewModelFactory(getContext(), mRecipeId, getActivity().getApplication() );

        mViewModel = ViewModelProviders.of(getActivity(), factory).get(SharedViewModel.class);

        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(RECIPE_ID);
            mStepId = getArguments().getInt(STEP_ID);
            mStepVideo = getArguments().getString(STEP_VIDEO);
        }
        loadRecipeDataFromViewModel();
    }

    /**
     * Initialize ExoPlayer.
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            Handler mainHandler = new Handler();
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
            mPlayerView.setPlayer(mExoPlayer);

            //prepare player
            // Measures bandwidth during playback. Can be null if not required.

// Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                    Util.getUserAgent(getActivity(), "yourApplicationName"));
// This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);
// Prepare the player with the source.
            mExoPlayer.prepare(videoSource);

        }
    }


    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onActivityCreated(Bundle onSavedInstanceState){
        super.onActivityCreated(onSavedInstanceState);
        mPlayerView = getActivity().findViewById(R.id.pv_video);
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
            populateVideo();
        });
    }

    public void populateVideo(){
        mStepVideo = mRecipe.getSteps().get(mStepId).getVideoURL();
        Uri uri = Uri.parse(mStepVideo);
        initializePlayer(uri);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
