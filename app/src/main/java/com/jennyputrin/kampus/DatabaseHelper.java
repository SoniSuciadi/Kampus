package com.jennyputrin.kampus;
// Create BY Jenny Putri Nengsi
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "tbl_kampus";
    private static final int DATABASE_VERSION = 1;
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_ALAMAT = "alamat";

    public DatabaseHelper(Context ctx) {
        super(ctx, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NAMA + " TEXT, " +
                FIELD_ALAMAT + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addKampus(String nama, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_ALAMAT, alamat);
        long exc = db.insert(TABLE_NAME, null, cv);
        return exc;
    }
    public long editKampus(String id, String nama, String alamat){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FIELD_NAMA,nama);
        cv.put(FIELD_ALAMAT,alamat);

        long exc=db.update(TABLE_NAME,cv,"id=?", new String[]{id});
        return exc;
    }
    public long hapusKampus(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long exc=sqLiteDatabase.delete(TABLE_NAME,"id= ?",new String[]{id});
        return exc;
    }
    public Cursor retrivedata(){
        String querry="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(querry,null);
        if (cursor==null){
            cursor=sqLiteDatabase.rawQuery(querry,null);
        }
        return cursor;
    }
}
