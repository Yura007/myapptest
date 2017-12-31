package info.yuri.msapps.screen;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import info.yuri.msapps.model.Movie;
import info.yuri.msapps.R;

/**
 * Created by Yuri on 30/12/2017.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailsActivity";

    public static final String MOVIE_ID = "MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ImageView mImage=findViewById(R.id.image);
        TextView textTitle=findViewById(R.id.title);
        TextView qr=findViewById(R.id.qr);
        TextView releaseYear=findViewById(R.id.releaseYear);
        TextView genre=findViewById(R.id.genre);
        RatingBar rating=findViewById(R.id.rating);


        final Movie movie = getIntent().getParcelableExtra(MainActivity.MOVIE);
        Picasso.with(mImage.getContext())
                .load(movie.getImage())
        .placeholder(R.drawable.progress_animation)
                .into(mImage);
        textTitle.setText(movie.getTitlel());
        releaseYear.setText(movie.getReleaseYear().toString());
        rating.setRating(Float.parseFloat(movie.getRating()));
        genre.setText(movie.getGenreString());
        qr.setText(movie.getQr());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MovieDetailsActivity.this, BarcodeActivity.class);
                intent.putExtra(MOVIE_ID,movie.get_id());
                startActivity(intent);
            }
        });
    }


}
