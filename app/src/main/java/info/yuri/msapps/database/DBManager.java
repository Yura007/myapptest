package info.yuri.msapps.database;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.yuri.msapps.model.Movie;

/**
 * Created by Yuri on 30/12/2017.
 */
public class DBManager {
    private static final String TAG = "DBManager";
    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Boolean empty(){
        Cursor mCursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);
        Boolean rowExists=false;

        if (mCursor.moveToFirst())
        {
            // CURSOR NOT EMPTY
            rowExists = false;

        } else
        {
            // CURSOR EMPTY
            rowExists = true;
        }
        return rowExists;
    }

    public void updateQR(String code, int id) {

        String query = "UPDATE " + DBHelper.TABLE_NAME + " SET " + DBHelper.COL6_QR +
                " = ? WHERE " + DBHelper._ID + " =  ? " ;
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "code: Setting code to " + code);

//       to avoid SQL injection in Android good practise
        SQLiteStatement stmt = database.compileStatement(query);
        stmt.bindString(1, code);
        stmt.bindString(2, String.valueOf(id));
        stmt.executeUpdateDelete();

    }

    public boolean addQRData(String item) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL6_QR, item);

        Log.d(TAG, "addData: Adding " + item + " to " + DBHelper.TABLE_NAME);

        long result = database.insert(DBHelper.TABLE_NAME, null, contentValues);
        database.close();
        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void insert(Movie item) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL1_TITLE, item.getTitlel());
        values.put(DBHelper.COL2_IMAGE, item.getImage());
        values.put(DBHelper.COL3_RATING, item.getRating());
        values.put(DBHelper.COL4_RELEASE_YEAR, item.getReleaseYear());
        values.put(DBHelper.COL5_GENRE, item.getGenreString());
        database.insert(DBHelper.TABLE_NAME, null, values);
    }

    public Cursor fetch() {

        Cursor cursor = database.query(DBHelper.TABLE_NAME, DBHelper.ALL_COLUMNS, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public List<Movie> getAllData() {
        List<Movie> list = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, DBHelper.ALL_COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DBHelper._ID);
            int index1 = cursor.getColumnIndex(DBHelper.COL1_TITLE);
            int index2 = cursor.getColumnIndex(DBHelper.COL2_IMAGE);
            int index3 = cursor.getColumnIndex(DBHelper.COL3_RATING);
            int index4 = cursor.getColumnIndex(DBHelper.COL4_RELEASE_YEAR);
            int index5 = cursor.getColumnIndex(DBHelper.COL5_GENRE);
            int index6 = cursor.getColumnIndex(DBHelper.COL6_QR);

            int cid = cursor.getInt(index);
            String TITLE = cursor.getString(index1);
            String IMAGE = cursor.getString(index2);
            Double RATING = cursor.getDouble(index3);
            Integer YEAR = cursor.getInt(index4);
            String GENRE = cursor.getString(index5);
            String QR = cursor.getString(index6);

            List<String> listGENRE = new ArrayList<String>(Arrays.asList(GENRE.split(" , ")));
            Movie bean = new Movie(cid, TITLE, IMAGE, RATING.toString(),YEAR,listGENRE,QR);
            list.add(bean);
        }
        return list;
    }



}