package org.bitbucket.dannyvantol.rekenlokaal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    public static final String dbName = "rekenlokaal.db";
    public static final int dbVersion = 1;

    public DatabaseHelper(Context context){
        super(context, dbName, null, dbVersion);
    }

    public static synchronized DatabaseHelper getHelper (Context context){
        if (mInstance == null){
            mInstance = new DatabaseHelper(context);
            mSQLDB = mInstance.getWritableDatabase();
        }

        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTestTable = "CREATE TABLE " + DatabaseInfo.Tests.TESTS + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.TestsColumn.DIFFICULTY + " TEXT," +
                DatabaseInfo.TestsColumn.TABLE + " INTEGER," +
                DatabaseInfo.TestsColumn.GRADE + " DECIMAL(3,1)" +
                ");";
        sqLiteDatabase.execSQL(createTestTable);
    }
    // CREATE TABLE test (_id INTEGER PRIMARY KEY AUTOINCREMENT, test_id TEXT, difficulty TEXT, table TEXT, grade TEXT);


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.Tests.TESTS);
        onCreate(sqLiteDatabase);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }
}
