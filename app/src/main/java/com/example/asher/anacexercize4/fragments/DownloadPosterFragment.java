package com.example.asher.anacexercize4.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asher.anacexercize4.R;
import com.squareup.picasso.Picasso;

public class DownloadPosterFragment extends Fragment {

    private String _posterUrl;
    private ImageView _imgDownloadedPoster;

    public String get_posterUrl() {
        return _posterUrl;
    }

    public void set_posterUrl(String _posterUrl) {
        this._posterUrl = _posterUrl;
    }

    public static DownloadPosterFragment NewInstance(String posterUrl){
        DownloadPosterFragment fragment = new DownloadPosterFragment();
        fragment.set_posterUrl(posterUrl);
        return fragment;
    }

    public DownloadPosterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.download_poster_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _imgDownloadedPoster = view.findViewById(R.id.img_download_poster);

        Picasso.get()
                .load(_posterUrl)
                .into(_imgDownloadedPoster);
    }
}
