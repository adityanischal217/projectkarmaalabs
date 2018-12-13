package com.example.aditya.projectkarma.LocalDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.aditya.projectkarma.UTILS.AppConstantKeys;
import com.example.aditya.projectkarma.Model.CustomerDetails;
import com.example.aditya.projectkarma.Model.UserDetails;

import java.util.ArrayList;
import java.util.List;


public class DatabaseCURDOperations {

    private Context mContext;
    private static final String LOG_TAG = DatabaseCURDOperations.class.getName();
    DataBaseHelper dataBaseHelper;

    public DatabaseCURDOperations(Context context) {
        mContext = context;
        dataBaseHelper = new DataBaseHelper(mContext);
    }

    public void insertUserInfo(UserDetails userDetails) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
            try {
                String query = "insert into " + AppConstantKeys.USER_TABLE + " ("
                        + AppConstantKeys.USER_NAME + ","
                        + AppConstantKeys.USER_EMAIL + ","
                        + AppConstantKeys.USER_PASSWORD + ","
                        + AppConstantKeys.USER_MOBILE + ") values (?,?,?,?)";
                SQLiteStatement insertStmt = sqLiteDatabase.compileStatement(query);
                insertStmt.clearBindings();
                insertStmt.bindString(1, userDetails.getUserName());
                insertStmt.bindString(2, userDetails.getUserEmail());
                insertStmt.bindString(3, userDetails.getUserPassword());
                insertStmt.bindString(4, userDetails.getUserMobile());
                insertStmt.executeInsert();
                Log.v(LOG_TAG, "New User Inserted Successfully");
            } catch (Exception ex) {
                Log.v(LOG_TAG, ex.getMessage());
            }
            dataBaseHelper.close();
            sqLiteDatabase.close();
        }
    }

    public void insertCustomerInfo(CustomerDetails customerDetails) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
            try {
                String query = "insert into " + AppConstantKeys.CUSTOMER_TABLE + " ("
                        + AppConstantKeys.USER_EMAIL + ","
                        + AppConstantKeys.CUSTOMER_NAME + ","
                        + AppConstantKeys.CUSTOMER_AGE + ","
                        + AppConstantKeys.CUSTOMER_DOB + ","
                        + AppConstantKeys.LATITUTE + ","
                        + AppConstantKeys.LONGITUTE + ") values (?,?,?,?,?,?)";
                SQLiteStatement insertStmt = sqLiteDatabase.compileStatement(query);
                insertStmt.clearBindings();
                insertStmt.bindString(1, customerDetails.getUserEmail());
                insertStmt.bindString(2, customerDetails.getCustomerName());
                insertStmt.bindString(3, customerDetails.getCustomersAge());
                insertStmt.bindString(4, customerDetails.getCustomersDob());
                insertStmt.bindString(5, customerDetails.getCustLat());
                insertStmt.bindString(6, customerDetails.getCustLong());
                insertStmt.executeInsert();
                Log.v(LOG_TAG, "Customer Inserted Successfully");
            } catch (Exception ex) {
                Log.v(LOG_TAG, ex.getMessage());
            }
            dataBaseHelper.close();
            sqLiteDatabase.close();
        }
    }


    public UserDetails getUserInfo(String userEmail) {
        String selectQuery = "SELECT * FROM " + AppConstantKeys.USER_TABLE + " where " + AppConstantKeys.USER_EMAIL + " =='" + userEmail + "'";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        UserDetails userDetails = new UserDetails();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String email = cursor.getString(cursor.getColumnIndex(AppConstantKeys.USER_EMAIL));
                if (email.equalsIgnoreCase(userEmail)) {
                    String id = cursor.getString(cursor.getColumnIndex(AppConstantKeys.USER_ID));
                    String name = cursor.getString(cursor.getColumnIndex(AppConstantKeys.USER_NAME));
                    String pass = cursor.getString(cursor.getColumnIndex(AppConstantKeys.USER_PASSWORD));
                    String mobile = cursor.getString(cursor.getColumnIndex(AppConstantKeys.USER_MOBILE));
                    userDetails = new UserDetails(name, email, pass, mobile);
                    break;
                }
            }
            cursor.close();
        }
        dataBaseHelper.close();
        sqLiteDatabase.close();
        return userDetails;
    }

    public List<CustomerDetails> getCustomerInfo1(String userEmail) {
        String selectQuery = "SELECT DISTINCT * FROM " + AppConstantKeys.CUSTOMER_TABLE + " where " + AppConstantKeys.USER_EMAIL + " =='" + userEmail + "'";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        List<CustomerDetails> partList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String email = cursor.getString(cursor.getColumnIndex(AppConstantKeys.USER_EMAIL));
                if (email.equalsIgnoreCase(userEmail)) {
                    String name = cursor.getString(cursor.getColumnIndex(AppConstantKeys.CUSTOMER_NAME));
                    String age = cursor.getString(cursor.getColumnIndex(AppConstantKeys.CUSTOMER_AGE));
                    String dob = cursor.getString(cursor.getColumnIndex(AppConstantKeys.CUSTOMER_DOB));
                    String lat = cursor.getString(cursor.getColumnIndex(AppConstantKeys.LATITUTE));
                    String lon = cursor.getString(cursor.getColumnIndex(AppConstantKeys.LONGITUTE));
                    partList.add(new CustomerDetails(email, name, age, dob, lat, lon));

                }
            }
            cursor.close();
        }


        dataBaseHelper.close();
        sqLiteDatabase.close();
        return partList;
    }


}