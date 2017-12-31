package info.yuri.msapps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.yuri.msapps.model.Movie;

/**
 * Created by Yuri on 30/12/2017.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private static final String TAG = "MoviesAdapter";

    private List<Movie> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;
        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            iv = (ImageView) view.findViewById(R.id.iv);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public void setItems(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public MoviesAdapter(List<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitlel());

        Picasso.with(context)
                .load(movie.getImage())
                .placeholder(R.drawable.progress_animation)
                .into(holder.iv);
        holder.year.setText(movie.getReleaseYear().toString());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
