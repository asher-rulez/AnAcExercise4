package com.example.asher.anacexercize4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asher.anacexercize4.ui.single.MoviesListGridFragment;

public class SingleActivity extends AppCompatActivity implements IFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MoviesListGridFragment.newInstance(this))
                    .commitNow();
        }
    }

    @Override
    public void OnListOrGridItemClicked(int movieIndex) {

    }
}
