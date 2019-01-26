package com.example.asher.anacexercize4.interfaces;

import android.view.View;

import com.example.asher.anacexercize4.data.Result;

import java.util.List;

public interface IFragmentInteractionListener {
    void OnListOrGridItemClicked(int movieIndex);

    void OnShowTrailerClickListener(String intentAddress);

    void SwitchToAsyncFragment(int asyncTypeId);

    void SwitchToServicesFragment();

    void SwitchToDownloadPosterFragment(String posterUrl);

    void NotifyGotMovies(List<Result> movies);
}
