package com.example.asher.anacexercize4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asher.anacexercize4.interfaces.IFragmentInteractionListener;
import com.example.asher.anacexercize4.R;
import com.example.asher.anacexercize4.data.MovieItemDTO;

import java.util.ArrayList;

public class MoviesListRWAdapterList extends RecyclerView.Adapter<MoviesListRWAdapterList.MoviesListViewHolder> {

    ArrayList<MovieItemDTO> _items_dtos;
    LayoutInflater mLayoutInflater;
    IFragmentInteractionListener _interactionListener;

    public MoviesListRWAdapterList(ArrayList<MovieItemDTO> _items_dtos, Context context, IFragmentInteractionListener interactionListener) {
        this._items_dtos = _items_dtos;
        _interactionListener = interactionListener;
        mLayoutInflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.ml_list_item, parent, false);
        return new MoviesListViewHolder(itemView, _interactionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListViewHolder holder, int position) {
        holder.SetMovieItemDto(_items_dtos.get(position), position);
    }

    @Override
    public int getItemCount() {
        return _items_dtos.size();
    }

    public class MoviesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int _index;
        ImageView _image;
        TextView _title, _description;
        IFragmentInteractionListener _interactionListener;

        public MoviesListViewHolder(View itemView, IFragmentInteractionListener interactionListener) {
            super(itemView);
            _image = itemView.findViewById(R.id.iv_ml_rw_list_item_image);
            _title = itemView.findViewById(R.id.tv_ml_rw_list_item_title);
            _description = itemView.findViewById(R.id.tv_ml_rw_list_item_description);
            itemView.setOnClickListener(this);
        }

        public void SetMovieItemDto(MovieItemDTO dto, int index){
            _image.setImageResource(dto.get_imageSmallResId());
            _title.setText(dto.get_movieTitle());
            _description.setText(dto.get_movieDescription());
            set_index(index);
        }
        @Override
        public void onClick(View v) {
            if(_interactionListener != null)
                _interactionListener.OnListOrGridItemClicked(get_index());
        }

        public int get_index() {
            return _index;
        }

        public void set_index(int _index) {
            this._index = _index;
        }
    }
}
