package info.yuri.msapps;

/**
 * Created by Yuri on 30/12/2017.
 */


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import info.yuri.msapps.model.Movie;


public class Utils {

    private static final String TAG = "Utils";

    public static List<Movie> loadMovies(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "movies.json"));
            List<Movie> moviesList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Movie profile = gson.fromJson(array.getString(i), Movie.class);
                moviesList.add(profile);
            }
            return moviesList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
