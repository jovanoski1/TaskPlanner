package com.example.rafdnevnjak.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafdnevnjak.db.DataBaseHelper;
import com.example.rafdnevnjak.model.Duty;

public class Utils {
    public static void addObligation(String startTime, String endTime, String title, String description, int priority,int userId, Context context) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("start_time", startTime); // Use a string in ISO8601 format
        values.put("end_time", endTime);
        values.put("title", title);
        values.put("description", description);
        values.put("userId", userId);
        values.put("priority", priority);

        long result = db.insert("obligations", null, values);
        db.close();

        if (result == -1) {
            Log.d("Database", "Error inserting obligation");
        } else {
            Log.d("Database", "Obligation inserted successfully");
        }
    }
    public static void addObligation(Duty duty, Long userId, Context context) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("start_time",duty.getDate() + " "+ duty.getStartTime()+":00"); // Use a string in ISO8601 format
        values.put("end_time", duty.getDate() +" "+duty.getEndTime()+":00");
        values.put("title", duty.getTitle());
        values.put("description", duty.getDescription());
        values.put("userId", userId);
        values.put("priority", duty.getPriority().ordinal());

        long result = db.insert("obligations", null, values);
        db.close();

        if (result == -1) {
            Log.d("Database", "Error inserting obligation");
        } else {
            Log.d("Database", "Obligation inserted successfully with id "+result);
            duty.setId(result);
        }
    }


    public static int updateObligation(Duty duty, Context context) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("start_time",duty.getDate() + " "+ duty.getStartTime()+":00"); // Use a string in ISO8601 format
        values.put("end_time", duty.getDate() +" "+duty.getEndTime()+":00");
        values.put("title", duty.getTitle());
        values.put("description", duty.getDescription());
        values.put("priority", duty.getPriority().ordinal());

        int rowsAffected = db.update("obligations", values, "_id = ?",
                new String[] { String.valueOf(duty.getId()) });
        db.close();
        return rowsAffected;
    }

    public static void deleteObligationById(Long id, Context context) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("obligations", "_id=?", new String[] {String.valueOf(id)});
        db.close();
    }

}
