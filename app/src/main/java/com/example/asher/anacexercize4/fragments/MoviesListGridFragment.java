package com.example.asher.anacexercize4.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;
import com.example.asher.anacexercize4.R;
import com.example.asher.anacexercize4.adapters.MoviesListRWAdapterGrid;
import com.example.asher.anacexercize4.adapters.MoviesListRWAdapterList;
import com.example.asher.anacexercize4.data.DataGetter;
import com.example.asher.anacexercize4.data.MovieItemDTO;

import java.util.ArrayList;

public class MoviesListGridFragment extends Fragment implements View.OnClickListener {

    IFragmentInteractionListener _interactionListener;

    private boolean IS_LIST;

    android.support.v7.widget.Toolbar _toolbar;
    MenuItem _menu_item_list, _menu_item_grid;
    ArrayList<MovieItemDTO> mi_source;
    MoviesListRWAdapterList listAdapter;
    MoviesListRWAdapterGrid gridAdapter;
    RecyclerView rw_movies_list;
    ImageButton _ibtn_showList, _ibtn_showGrid;

    public IFragmentInteractionListener get_interactionListener() {
        return _interactionListener;
    }

    public void set_interactionListener(IFragmentInteractionListener _interactionListener) {
        this._interactionListener = _interactionListener;
    }

    public static MoviesListGridFragment newInstance(IFragmentInteractionListener listener) {
        MoviesListGridFragment fragment = new MoviesListGridFragment();
        fragment.set_interactionListener(listener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mi_source = (ArrayList<MovieItemDTO>)getArguments().getSerializable(MovieItemDTO.MOVIE_ITEM_BUNDLE_NAME);
        return inflater.inflate(R.layout.single_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _toolbar = view.findViewById(R.id.ml_tb_main_hrd);
        _toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(_toolbar);
        IS_LIST = true;
        rw_movies_list = view.findViewById(R.id.rw_ml_movies_list);
        _ibtn_showGrid = view.findViewById(R.id.ibtn_show_grid);
        _ibtn_showGrid.setOnClickListener(this);
        _ibtn_showList = view.findViewById(R.id.ibtn_show_list);
        _ibtn_showList.setOnClickListener(this);
        CreateAndAttachAdapter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _interactionListener = (IFragmentInteractionListener)context;
        }
        catch (ClassCastException ex){
            Log.e("MLGFr","context is not instance of listener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void CreateAndAttachAdapter(){
        if(mi_source == null)
            return;
        if(IS_LIST)
            CreateAndAttachListAdapter();
        else
            CreateAndAttachGridAdapter();
    }

    private void CreateAndAttachListAdapter(){
        _ibtn_showGrid.setVisibility(View.VISIBLE);
        _ibtn_showList.setVisibility(View.GONE);
        LinearLayoutManager llm_movies_list = new LinearLayoutManager(getContext());
        rw_movies_list.setLayoutManager(llm_movies_list);
        listAdapter = new MoviesListRWAdapterList(mi_source, getContext(), get_interactionListener());
        rw_movies_list.setAdapter(listAdapter);
    }

    private void CreateAndAttachGridAdapter(){
        _ibtn_showGrid.setVisibility(View.GONE);
        _ibtn_showList.setVisibility(View.VISIBLE);
        StaggeredGridLayoutManager sglm_movies_list = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rw_movies_list.setLayoutManager(sglm_movies_list);
        gridAdapter = new MoviesListRWAdapterGrid(mi_source, getContext(), get_interactionListener());
        rw_movies_list.setAdapter(gridAdapter);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.movies_list_toolbar_menu, menu);
//        _menu_item_grid = menu.findItem(R.id.ml_tb_menu_item_grid);
//        _menu_item_grid.setVisible(true);
//        _menu_item_list = menu.findItem(R.id.ml_tb_menu_item_list);
//        _menu_item_list.setVisible(false);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int itemId = item.getItemId();
//        _menu_item_grid.setVisible(itemId == R.id.ml_tb_menu_item_list);
//        _menu_item_list.setVisible(itemId == R.id.ml_tb_menu_item_grid);
//        switch (itemId){
//            case R.id.ml_tb_menu_item_grid:
//                break;
//            case R.id.ml_tb_menu_item_list:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        rw_movies_list.setLayoutManager(null);
        rw_movies_list.setAdapter(null);
        switch (v.getId()){
            case R.id.ibtn_show_grid:
                IS_LIST = true;
                CreateAndAttachGridAdapter();
                break;
            case R.id.ibtn_show_list:
                IS_LIST = false;
                CreateAndAttachListAdapter();
                break;
        }
    }
}
