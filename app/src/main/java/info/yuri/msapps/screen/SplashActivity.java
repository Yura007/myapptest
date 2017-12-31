package info.yuri.msapps.screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import info.yuri.msapps.database.DBManager;
import info.yuri.msapps.model.Movie;

import static info.yuri.msapps.Utils.loadMovies;

/**
 * Created by Yuri on 30/12/2017.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        List<Movie> movies = loadMovies(this);


        dbManager = new DBManager(this);
        dbManager.open();
      if (dbManager.empty())
        for (Movie item :movies) {
            dbManager.insert(item);
        }
        dbManager.close();


        // Start home activity
        startActivity(new Intent(SplashActivity.this, MainActivity.class));

        // close splash activity
        finish();
    }


}

