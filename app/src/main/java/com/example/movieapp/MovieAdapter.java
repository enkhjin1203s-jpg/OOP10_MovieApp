package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        TextView titleText;
        TextView yearText;
        TextView genreText;

        public ViewHolder(View view) {
            super(view);
            posterImage = view.findViewById(R.id.posterImage);
            titleText   = view.findViewById(R.id.titleText);
            yearText    = view.findViewById(R.id.yearText);
            genreText   = view.findViewById(R.id.genreText);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        // Title
        holder.titleText.setText(movie.getTitle());

        // Year 
        if (movie.getYear() != null) {
            holder.yearText.setText("Year: " + movie.getYear());
        } else {
            holder.yearText.setText("Year: Unknown");
        }

        // Genre
        holder.genreText.setText("Genre: " + movie.getGenre());

        Context ctx = holder.itemView.getContext();
        String posterName = movie.getPoster();
        int resId = ctx.getResources().getIdentifier(posterName, "drawable", ctx.getPackageName());
        holder.posterImage.setImageResource(resId != 0 ? resId : android.R.drawable.ic_menu_gallery);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovies(List<Movie> newMovies) {
        this.movies = newMovies;
        notifyDataSetChanged();
    }
}
