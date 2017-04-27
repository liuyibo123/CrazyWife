package crazywife.upc.com.crazywife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/17.
 */

public class SqliteHelper extends SQLiteOpenHelper{
    private static final  String TAG = "SqliteOpenHelper";
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table food(id integer primary key autoincrement,name text,amount float,date text)";
        Log.d(TAG,sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
