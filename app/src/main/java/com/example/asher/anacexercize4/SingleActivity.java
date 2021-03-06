package com.example.asher.anacexercize4;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asher.anacexercize4.async_util.ObservableObject;
import com.example.asher.anacexercize4.data.DataGetter;
import com.example.asher.anacexercize4.data.MovieItemDTO;
import com.example.asher.anacexercize4.data.Result;
import com.example.asher.anacexercize4.fragments.AsyncCounterFragment;
import com.example.asher.anacexercize4.fragments.DownloadPosterFragment;
import com.example.asher.anacexercize4.fragments.MoviesPagerFragment;
import com.example.asher.anacexercize4.fragments.ServicesFragment;
import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;
import com.example.asher.anacexercize4.fragments.MoviesListGridFragment;
import com.example.asher.anacexercize4.interfaces.IServiceFragmentInteractionListener;
import com.example.asher.anacexercize4.receivers.MyReceiver;
import com.example.asher.anacexercize4.services.MyService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.example.asher.anacexercize4.services.MyIntentService.INTENT_EXTRA;
import static com.example.asher.anacexercize4.services.MyIntentService.UPDATE_TYPE_EXTRA;

public class SingleActivity extends AppCompatActivity implements IFragmentInteractionListener, Observer {

    private static final String TAG_SERVICE_FRAGMENT = "TAG_SERVICE_FRAGMENT";
    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";

    private List<Result> movies;

    private final MyReceiver receiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);
        //movies = DataGetter.GetData(this);
        if (savedInstanceState == null) {
            MoviesListGridFragment fragment = MoviesListGridFragment.newInstance(this);
            Bundle bundle = new Bundle();
            bundle.putSerializable(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME, (Serializable)movies);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, TAG_LIST_FRAGMENT)
                    .commit();
        }
        ObservableObject.getInstance().addObserver(this);
        registerReceiver(receiver, new IntentFilter(MyReceiver.MY_BROADCAST_RECEIVER_ACTION));
    }

    @Override
    public void OnListOrGridItemClicked(int movieIndex) {
        MoviesPagerFragment fragment = MoviesPagerFragment.newInstance(this);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME, (Serializable)movies);
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
    public void SwitchToDownloadPosterFragment(String posterUrl) {
        DownloadPosterFragment fragment = DownloadPosterFragment.NewInstance(posterUrl);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void NotifyGotMovies(List<Result> movies) {
        this.movies = movies;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SendProgressToServicesActivity(resultCode, data);
    }

    public void OnIntentServiceReported(Intent intent) {
    }

    private void SendProgressToServicesActivity(int resultCode, Intent data){
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void update(Observable o, Object arg) {
        Intent intent = (Intent)arg;
        Intent receivedIntent = intent.getParcelableExtra(INTENT_EXTRA);
        int code = intent.getIntExtra(UPDATE_TYPE_EXTRA, -1);
        if(receivedIntent == null || code == -1)
            throw new IllegalArgumentException();
        SendProgressToServicesActivity(code, receivedIntent);
    }
}
