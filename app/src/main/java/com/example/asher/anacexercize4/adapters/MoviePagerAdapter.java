package com.example.asher.anacexercize4.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.asher.anacexercize4.data.MovieItemDTO;
import com.example.asher.anacexercize4.data.Result;
import com.example.asher.anacexercize4.fragments.ScreenSlidePageFragment;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class MoviePagerAdapter extends FragmentStatePagerAdapter {
    List<Result> _movieItemDTOs;
    private int currentIndex;
    IFragmentInteractionListener _interactionListener;

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public ScreenSlidePageFragment getItem(int position) {
        ScreenSlidePageFragment fragment = ScreenSlidePageFragment.NewInstance();
        fragment.setInteractionListener(_interactionListener);
        fragment.setMovie(_movieItemDTOs.get(position));
        //Bundle bundle = new Bundle();
        return fragment;
    }

    @Override
    public int getCount() {
        if (_movieItemDTOs != null)
            return _movieItemDTOs.size();
        return 0;
    }

    public void setFragmentInteractionListener(IFragmentInteractionListener interactionListener) {
        _interactionListener = interactionListener;
    }

    public List<Result> get_movieItemDTOs() {
        return _movieItemDTOs;
    }

    public void set_movieItemDTOs(List<Result> _movieItemDTOs) {
        this._movieItemDTOs = _movieItemDTOs;
    }
}
