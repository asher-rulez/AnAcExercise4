package com.example.asher.anacexercize4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.asher.anacexercize4.data.DataGetter;
import com.example.asher.anacexercize4.data.MovieItemDTO;
import com.example.asher.anacexercize4.fragments.AsyncCounterFragment;
import com.example.asher.anacexercize4.fragments.MoviesPagerFragment;
import com.example.asher.anacexercize4.fragments.ServicesFragment;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;
import com.example.asher.anacexercize4.fragments.MoviesListGridFragment;
import com.example.asher.anacexercize4.interfaces.IServiceFragmentInteractionListener;
import com.example.asher.anacexercize4.services.MyService;

import java.util.ArrayList;

public class SingleActivity extends AppCompatActivity implements IFragmentInteractionListener {

    private static final String TAG_SERVICE_FRAGMENT = "TAG_SERVICE_FRAGMENT";
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
    public void SwitchToAsyncFragment(int asyncTypeId) {
        AsyncCounterFragment fragment = AsyncCounterFragment.newInstance(this, asyncTypeId);
        Bundle bundle = new Bundle();
        bundle.putInt(AsyncCounterFragment.ASYNC_TYPE_PARAM_NAME, asyncTypeId);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void SwitchToServicesFragment() {
        ServicesFragment fragment = ServicesFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, TAG_SERVICE_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IServiceFragmentInteractionListener fragmentListener
                = (IServiceFragmentInteractionListener) getSupportFragmentManager()
                .findFragmentByTag(TAG_SERVICE_FRAGMENT);
        switch (resultCode) {
            case MyService.CODE_SERVICE_RUNNING:
                int progress = data.getIntExtra(MyService.SERVICE_PROGRESS_EXTRA, -1);
                fragmentListener.SetLog(ServicesFragment.LOG_TYPE_PROGRESS, String.valueOf(progress));
                break;
            case MyService.CODE_SERVICE_FINISHED:
                fragmentListener.SetStatus(ServicesFragment.SERVICE_TYPE_SERVICE,
                        getString(R.string.service_stopped) + data.getIntExtra(MyService.ON_DESTROY_EXTRA, -1));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
