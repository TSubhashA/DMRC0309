package com.nextgentele.dmrc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.nextgentele.dmrc.apis.ApiClient;
import com.nextgentele.dmrc.apis.ApiInterface;
import com.nextgentele.dmrc.apis.apiModel.LoginModule;
import com.nextgentele.dmrc.apis.apiModel.LoginResponse;
import com.nextgentele.dmrc.util.ConnectionDetector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDb extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Users";
    private static final String DATABASE_NAME = "user.db";
    public static final String COLUMN_ID = "userId";
    public static final String COLUMN_NAME = "userName";
    public static final String COLUMN_BAL = "userBal";
    public static final String COLUMN_DATE_TIME_IN = "dateTimeIn";
    public static final String COLUMN_LAT_IN = "latIn";
    public static final String COLUMN_LOG_IN = "longIn";
    public static final String COLUMN_DATE_TIME_OUT = "dateTemeOut";
    public static final String COLUMN_LAT_OUT = "latOut";
    public static final String COLUMN_LOG_OUT = "longOut";
    public static final String COLUMN_FARE = "fare";

    public MyDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String Create_Table="CREATE TABLE "+TABLE_NAME + " (" +
                COLUMN_ID + "INTEGER AUTOINCREAMENT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_BAL + " FLOAT, "
                + COLUMN_DATE_TIME_IN + " LONG, "
                + COLUMN_LAT_IN + " TEXT, "
                + COLUMN_LOG_IN + " TEXT, "
                + COLUMN_DATE_TIME_OUT + " LONG, "
                + COLUMN_LAT_OUT + " TEXT, "
                + COLUMN_LOG_OUT + " TEXT, "
                +COLUMN_FARE+" INTEGER)";

        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public Users getUser(String userName1) {

        Users users;
        String userName = "";
        float userBalance = 0;
        long dateTimeIn = 0;

        String latIn = "";
        String longIn = "";
        long dateTimeOut = 0;

        String latOut = "";
        String longOut = "";
        long fare = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ?", new String[]{userName1});
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    // Passing values
                    userName = c.getString(1);
                    userBalance = c.getFloat(2);
                    dateTimeIn = c.getLong(3);
                    latIn = c.getString(4);
                    longIn = c.getString(5);
                    dateTimeOut = c.getLong(6);
                    latOut = c.getString(7);
                    longOut = c.getString(8);
                    fare = c.getLong(9);

                    // Do something Here with values
                } while (c.moveToNext());
            }
            c.close();
            db.close();
            users = new Users(userName, userBalance, dateTimeIn, dateTimeOut, latIn, longIn, latOut, longOut, fare);

            return users;
        } else {
            return null;
        }
    }


    public boolean insertData(Users user){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,user.getUserName());
        cv.put(COLUMN_BAL,user.getUserBal());
        cv.put(COLUMN_DATE_TIME_IN,user.getDateTimeIn());
        cv.put(COLUMN_LAT_IN,user.getLatIn());
        cv.put(COLUMN_LOG_IN,user.getLongIn());
        cv.put(COLUMN_DATE_TIME_OUT,user.getDatetimeOut());
        cv.put(COLUMN_LAT_OUT,user.getLatOut());
        cv.put(COLUMN_LOG_OUT,user.getLongOut());
        cv.put(COLUMN_FARE,user.getFare());
        long result=db.insert(TABLE_NAME,null,cv);
        db.close();
        if (result==-1)
        {
            return false;
        }else
            return true;

    }


    public boolean update(String Username,long OutDate, String latOut, String longOut, double fare){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv= new ContentValues();
        cv.put(COLUMN_DATE_TIME_OUT,OutDate);
        cv.put(COLUMN_LAT_OUT,latOut);
        cv.put(COLUMN_LOG_OUT,longOut);
        cv.put(COLUMN_FARE,fare);
        db.update(TABLE_NAME,cv,COLUMN_NAME+" = ?", new String[]{Username});

        db.close();


        return true;
    }

    public int deleteUser(String user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(TABLE_NAME,COLUMN_NAME+" = ?",new String[]{user});
        db.close();
        return result;
    }



}

