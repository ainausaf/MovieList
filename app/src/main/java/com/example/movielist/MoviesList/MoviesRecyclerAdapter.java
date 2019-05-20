package com.example.movielist.MoviesList;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.movielist.R;
import com.example.movielist.model.MoviesData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter .ViewHolder> {

    private Context context;
    private List<MoviesData> movies;
    private final OnItemClickListener listener;


    public MoviesRecyclerAdapter(Context context, List<MoviesData> movies, OnItemClickListener listener) {
        this.context = context;
        this.movies = movies;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardview_feed)
        CardView cardViewFeed;
        @BindView(R.id.iv_movie_image)
        ImageView movieImage;
        @BindView(R.id.movie_name)
        TextView movieName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    @Override
    public MoviesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesRecyclerAdapter.ViewHolder viewHolder, int position) {

        MoviesData movie = movies.get(position);
        String movieImageUrl = movie.getImage();
        Glide.with(context).load( movieImageUrl ).placeholder(
                R.drawable.layout_placeholder).error(
                R.drawable.layout_placeholder).into(viewHolder.movieImage);

        viewHolder.movieName.setText(movie.getTitle());

        viewHolder.cardViewFeed.setOnClickListener(v -> listener.onItemClick(movies.get(viewHolder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        if(movies!=null) {
            return movies.size();
        }
        return 0;
    }

    public void addAll(List<MoviesData> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();

    }

    public interface OnItemClickListener {
        void onItemClick(MoviesData movie);
    }
}
