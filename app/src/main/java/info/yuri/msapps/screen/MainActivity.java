package info.yuri.msapps.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Collections;
import java.util.List;

import info.yuri.msapps.database.DBManager;
import info.yuri.msapps.model.Movie;
import info.yuri.msapps.MoviesAdapter;
import info.yuri.msapps.R;
import info.yuri.msapps.RecyclerTouchListener;

/**
 * Created by Yuri on 30/12/2017.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String MOVIE = "movie";
    private List<Movie> movieList ;
    private boolean ascending = true;
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        DBManager coreData = new DBManager(this);
        coreData.open();


        movieList = coreData.getAllData();

        Collections.sort(movieList);
        mAdapter = new MoviesAdapter(movieList,MainActivity.this);
        int  id = getIntent().getIntExtra(MovieDetailsActivity.MOVIE_ID,-1);
        if (id>=0)
            mAdapter.notifyItemChanged(id);
        coreData.close();
        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                 Movie movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra(MOVIE,movie);
                startActivity( intent);

              // Toast.makeText(getApplicationContext(), movie.getTitlel() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.reverse:
                Collections.reverse(movieList);
                mAdapter.setItems(movieList);
                mAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
