package com.example.aditya.projectkarma.LocalDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aditya.projectkarma.UTILS.AppConstantKeys;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "projectkarmaa.db";
    private static final int DATABASE_VERSION = 1;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Called when no database exists in disk and the helper class needs
// to create a new one.
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
        createCustomerTable(db);
    }

    private void createCustomerTable(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
                + AppConstantKeys.CUSTOMER_TABLE + " ( "
                + AppConstantKeys.CUSTOMER_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + AppConstantKeys.USER_EMAIL + " TEXT, "
                + AppConstantKeys.CUSTOMER_NAME + " TEXT, "
                + AppConstantKeys.CUSTOMER_AGE + " TEXT, "
                + AppConstantKeys.CUSTOMER_DOB + " TEXT,"
                + AppConstantKeys.LATITUTE + " TEXT, "
                + AppConstantKeys.LONGITUTE + " TEXT "
                + ")";
        db.execSQL(CREATE_USER_TABLE);

    }

    private void createTableUser(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
                + AppConstantKeys.USER_TABLE + " ( "
                + AppConstantKeys.USER_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + AppConstantKeys.USER_NAME + " TEXT, "
                + AppConstantKeys.USER_EMAIL + " TEXT, "
                + AppConstantKeys.USER_PASSWORD + " TEXT, "
                + AppConstantKeys.USER_MOBILE + " TEXT "
                + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version" + _oldVersion + "to " + _newVersion + ", which will destroy all old data");

        _db.execSQL(" DROP TABLE If EXISTS " + "TEMPLATE ");
        // Create a new one.
        onCreate(_db);
    }


}