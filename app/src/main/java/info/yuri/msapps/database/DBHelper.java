package info.yuri.msapps.database;

/**
 * Created by Yuri on 30/12/2017.
 */



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Yuri on 30/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    // Table Name
    public static final String TABLE_NAME = "movies_table4";

    // Table columns
    public static final String _ID = "_id";
    public static final String COL1_TITLE = "title";
    public static final String COL2_IMAGE = "image";
    public static final String COL3_RATING = "rating";
    public static final String COL4_RELEASE_YEAR = "release_year";
    public static final String COL5_GENRE = "genre";
    public static final String COL6_QR = "qr";

    public static final String[] ALL_COLUMNS =
            {_ID,COL5_GENRE,COL1_TITLE, COL2_IMAGE, COL3_RATING, COL4_RELEASE_YEAR,COL6_QR};

    // Database Information
    static final String DB_NAME = "MOVIES4.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(  " +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL1_TITLE  + " TEXT NOT NULL, "
            + COL2_IMAGE  + " TEXT , "
            + COL3_RATING + " REAL, "
            + COL4_RELEASE_YEAR + " INTEGER,"
            + COL5_GENRE   + " TEXT,"
            + COL6_QR  + " TEXT );";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
