package com.example.asher.anacexercize4.interfaces;

import android.view.View;

public interface IFragmentInteractionListener {
    void OnListOrGridItemClicked(int movieIndex);

    void OnShowTrailerClickListener(String intentAddress);

    void SwitchToAsyncFragment(int asyncTypeId);

    void SwitchToServicesFragment();
}
