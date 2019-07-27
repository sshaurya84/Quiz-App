package com.shaurya.database;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class myDbAdapter {
    private static final String TAG = "myDbAdapter";
    myDbHelper myhelper;
    ArrayList<String> questions=new ArrayList<String>();
    private static final String DATABASE_NAME = "myDatabase";

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String question, String option1, String option2, String option3, String option4,String coption,String timeofquiz) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QUESTION, question);
        contentValues.put(myDbHelper.OPTION1, option1);
        contentValues.put(myDbHelper.OPTION2, option2);
        contentValues.put(myDbHelper.OPTION3, option3);
        contentValues.put(myDbHelper.OPTION4, option4);
        contentValues.put(myDbHelper.COPTION, coption);
        contentValues.put(myDbHelper.TIMEOFQUIZ, timeofquiz);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }



    public ArrayList<String> getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.QUESTION, myDbHelper.OPTION1,myDbHelper.OPTION2,myDbHelper.OPTION3,myDbHelper.OPTION4,myDbHelper.COPTION,myDbHelper.TIMEOFQUIZ};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String question = cursor.getString(cursor.getColumnIndex(myDbHelper.QUESTION));
            String option1 = cursor.getString(cursor.getColumnIndex(myDbHelper.OPTION1));
            String option2 = cursor.getString(cursor.getColumnIndex(myDbHelper.OPTION2));
            String option3 = cursor.getString(cursor.getColumnIndex(myDbHelper.OPTION3));
            String option4 = cursor.getString(cursor.getColumnIndex(myDbHelper.OPTION4));
            String coption = cursor.getString(cursor.getColumnIndex(myDbHelper.COPTION));
            String timeofquiz = cursor.getString(cursor.getColumnIndex(myDbHelper.TIMEOFQUIZ));


            questions.add(question);
            questions.add(option1);
            questions.add(option2);
            questions.add(option3);
            questions.add(option4);
            questions.add(coption);
            questions.add(timeofquiz);


        }

        return questions;

    }
    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.QUESTION+" = ?",whereArgs);
        return  count;
    }
    //updates  question
    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.QUESTION,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.QUESTION+" = ?",whereArgs );
        return count;
    }
    //updates options
    public int updateOption1(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.OPTION1,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.OPTION1+" = ?",whereArgs );
        return count;
    }
    public int updateOption2(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.OPTION2,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.OPTION2+" = ?",whereArgs );
        return count;
    }
    public int updateOption3(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.OPTION3,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.OPTION3+" = ?",whereArgs );
        return count;
    }

    public int updateOption4(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.OPTION4,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.OPTION4+" = ?",whereArgs );
        return count;
    }
    //update correct option
    public int updateCoption(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.COPTION,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.COPTION+" = ?",whereArgs );
        return count;
    }



    public static void deleteDatabase(Context mContext) {
        mContext.deleteDatabase(DATABASE_NAME);
    }

    public int updatetime(String timequiz, String newtime)
    {
        Log.e(TAG, "updatetime: "+timequiz );
        Log.e(TAG, "updatetime: "+newtime );
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TIMEOFQUIZ,newtime);
        String[] whereArgs= {timequiz};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.TIMEOFQUIZ+" = ?",whereArgs );
        return count;
    }


    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 4;   // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String QUESTION = "Question";    //Column II
        private static final String OPTION1 = "Option1";    // Column III
        private static final String OPTION2 = "Option2";    // Column IV
        private static final String OPTION3 = "Option3";    // Column V
        private static final String OPTION4 = "Option4";    // Column VI
        private static final String COPTION = "Coption";    // Column VII
        private static final String TIMEOFQUIZ = "Timeofquiz";    // Column VIII
      // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " VARCHAR(255) ," + OPTION1 + " VARCHAR(225)," + OPTION2 + "  VARCHAR(255)," + OPTION3 + "  VARCHAR(255)," + OPTION4 + "  VARCHAR(255)," + COPTION + " VARCHAR(255)," + TIMEOFQUIZ + " VARCHAR(255) );";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, TABLE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context, "e" + e, Toast.LENGTH_SHORT).show();
            }

        }



    @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "On upgrade", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Toast.makeText(context, "e" +e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

