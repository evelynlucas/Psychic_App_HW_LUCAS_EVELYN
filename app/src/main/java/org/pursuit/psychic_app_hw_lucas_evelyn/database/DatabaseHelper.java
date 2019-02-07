package org.pursuit.psychic_app_hw_lucas_evelyn.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.pursuit.psychic_app_hw_lucas_evelyn.model.GuessModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;
    private static final String DATABASE_NAME = "guess_database1.db";
    private static final String TABLE_NAME = "guess_table_name1";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(correct_guess INTEGER, wrong_guess INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No-op
    }

    public void addEntry(GuessModel guessModel) {

            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                    "(correct_guess, wrong_guess) VALUES('" +
                    guessModel.getCorrectGuess() + "', '" +
                    guessModel.getWrongGuess() + "');");
    }

    public List<GuessModel> getGuessList() {
        List<GuessModel> guessList = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + ";", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    GuessModel guessModel = new GuessModel(
                            cursor.getInt(cursor.getColumnIndex("correct_guess")),
                            cursor.getInt(cursor.getColumnIndex("wrong_guess")));
                    guessList.add(guessModel);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return guessList;
    }

    public double guessCorrectPercentage() {
        double average = 0;
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT AVG(correct_guess) FROM " + TABLE_NAME + ";", null);

        if (cursor.moveToFirst())
            average = cursor.getDouble(0);

        int averageInt = (int) (average * 100);
        return averageInt;
    }
}
