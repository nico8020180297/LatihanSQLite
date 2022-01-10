package com.example.latihansqllite;

import android.content.ContentValues;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.net.StandardSocketOptions;

public class MyDBHelper extends SQLiteOpenHelper {
    public Context context;
    private static final String DATABASE_NAME = "film.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "film";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JUDUL = "judul_film";
    private static final String COLUMN_GENRE = "genre_film";
    private static final String COLUMN_TAHUN = "tahun_film";




    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryKita ="CREATE TABLE " + TABLE_NAME +
                            " (" +
                                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COLUMN_JUDUL + " TEXT, " +
                                COLUMN_GENRE + " TEXT, " +
                                COLUMN_TAHUN + " INTEGER" +
                            ");";

        db.execSQL(queryKita);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void tambahFilm(String judul, String genre, int tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_GENRE, genre);
        cv.put(COLUMN_TAHUN, tahun);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "GAGAL !", Toast.LENGTH_SHORT).show();
        }else  {
            Toast.makeText(context, "Berhasil ditambah !!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor bacaSemuaData() {
        String queryKita = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase dbKita = this.getReadableDatabase();

        Cursor dataKita = null;
        if (dbKita != null) {
            dataKita = dbKita.rawQuery(queryKita, null);
        }
        return dataKita;
    }

    void ubahFilm(String baris_id, String judul, String genre, String tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataKita = new ContentValues();
        dataKita.put(COLUMN_JUDUL, judul);
        dataKita.put(COLUMN_GENRE, genre);
        dataKita.put(COLUMN_TAHUN, tahun);

        long hasil = db.update(TABLE_NAME, dataKita, "_id=?", new String[]{baris_id});

        if (hasil == -1){
            Toast.makeText(context, "Ada Gangguan !!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil di Ubah !!", Toast.LENGTH_SHORT).show();
        }


    }

    void hapusFilm(String row_id){
        SQLiteDatabase dbKita = this.getReadableDatabase();
        long result = dbKita.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "gagal Delete!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Delete", Toast.LENGTH_SHORT).show();
        }
    }

}
