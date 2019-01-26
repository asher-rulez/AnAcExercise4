package com.example.asher.anacexercize4.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asher.anacexercize4.R;
import com.example.asher.anacexercize4.SingleActivity;
import com.example.asher.anacexercize4.adapters.MoviePagerAdapter;
import com.example.asher.anacexercize4.data.MovieItemDTO;
import com.example.asher.anacexercize4.data.Result;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesPagerFragment extends Fragment {
    List<Result> _movieDtos;
    ViewPager _pager;
    MoviePagerAdapter _pagerAdapter;
    IFragmentInteractionListener _interactionListener;
    int _movieIndex;

    public static MoviesPagerFragment newInstance(IFragmentInteractionListener interactionListener) {
        MoviesPagerFragment fragment = new MoviesPagerFragment();
        fragment.setFragmentInteractionListener(interactionListener);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if(savedInstanceState != null)
//            _movieDtos = (ArrayList<MovieItemDTO>)savedInstanceState.get(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME);
        _movieDtos = (List<Result>)getArguments().getSerializable(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME);
        _movieIndex = getArguments().getInt(MovieItemDTO.MOVIE_INDEX_BUNDLE_NAME);
        return inflater.inflate(
                R.layout.movies_pager_fragment, container, false);
    }

    private void setFragmentInteractionListener(IFragmentInteractionListener interactionListener) {
        _interactionListener = interactionListener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _pager = view.findViewById(R.id.vp_movie_pager);
        _pagerAdapter = new MoviePagerAdapter(getFragmentManager());
        _pagerAdapter.set_movieItemDTOs(_movieDtos);
        _pagerAdapter.setFragmentInteractionListener(_interactionListener);
        _pager.setAdapter(_pagerAdapter);
        //_pager.addOnPageChangeListener(this);
        SetMovieIndex(_movieIndex);
    }

    public void SetMovieIndex(int movieIndex){
        if(_pager != null)
            _pager.setCurrentItem(movieIndex);
    }

}
