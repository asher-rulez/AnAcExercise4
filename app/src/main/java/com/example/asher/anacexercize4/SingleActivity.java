package com.example.asher.anacexercize4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asher.anacexercize4.data.DataGetter;
import com.example.asher.anacexercize4.data.MovieItemDTO;
import com.example.asher.anacexercize4.fragments.MoviesPagerFragment;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;
import com.example.asher.anacexercize4.fragments.MoviesListGridFragment;

import java.util.ArrayList;

public class SingleActivity extends AppCompatActivity implements IFragmentInteractionListener {

    private ArrayList<MovieItemDTO> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);
        movies = DataGetter.GetData(this);
        if (savedInstanceState == null) {
            MoviesListGridFragment fragment = MoviesListGridFragment.newInstance(this);
            Bundle bundle = new Bundle();
            bundle.putSerializable(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME, movies);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public void OnListOrGridItemClicked(int movieIndex) {
        MoviesPagerFragment fragment = MoviesPagerFragment.newInstance(this);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME, movies);
        bundle.putInt(MovieItemDTO.MOVIE_INDEX_BUNDLE_NAME, movieIndex);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void OnShowTrailerClickListener(String intentAddress) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + intentAddress));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + intentAddress));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
