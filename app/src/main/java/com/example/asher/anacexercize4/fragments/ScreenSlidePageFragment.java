package com.example.asher.anacexercize4.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asher.anacexercize4.R;
import com.example.asher.anacexercize4.data.MovieItemDTO;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;

public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener {
    MovieItemDTO _movieDto;
    IFragmentInteractionListener _interactionListener;
    ImageView iv_bigPoster, iv_smallPoster;
    TextView tv_releasedDate, tv_title, tv_description, tv_wiki;
    Button btn_showTrailer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.movie_overview_fragment, container, false);
        if(savedInstanceState != null)
            _movieDto = (MovieItemDTO)savedInstanceState.get(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME);
        return rootView;
    }

    public static ScreenSlidePageFragment NewInstance(){
        return new ScreenSlidePageFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_bigPoster = view.findViewById(R.id.iv_mof_big);
        iv_bigPoster.setImageResource(_movieDto.get_imageBigResId());
        iv_smallPoster = view.findViewById(R.id.iv_mof_small);
        iv_smallPoster.setImageResource(_movieDto.get_imageSmallResId());
        tv_releasedDate = view.findViewById(R.id.ma_tv_released);
        tv_releasedDate.setText(_movieDto.get_movieReleaseDate());
        tv_title = view.findViewById(R.id.ma_tv_movie_title);
        tv_title.setText(_movieDto.get_movieTitle());
        tv_description = view.findViewById(R.id.tv_mof_overview);
        tv_description.setText(_movieDto.get_movieTitle());
        tv_wiki = view.findViewById(R.id.tv_mof_wiki);
        tv_wiki.setText(_movieDto.get_movieWiki());
        btn_showTrailer = view.findViewById(R.id.btn_mof_trailer);
        btn_showTrailer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(_interactionListener != null)
            _interactionListener.OnShowTrailerClickListener(_movieDto.get_trailerIntentAddress());
    }

    public void setMovie(MovieItemDTO movieItemDTO) {
        _movieDto = movieItemDTO;
    }

    public void setInteractionListener(IFragmentInteractionListener interactionListener) {
        _interactionListener = interactionListener;
    }
}
