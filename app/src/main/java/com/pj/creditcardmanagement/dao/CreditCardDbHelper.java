package com.pj.creditcardmanagement.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PJ on 8/24/2015.
 */
public class CreditCardDbHelper extends SQLiteOpenHelper {

    private final String CREATE_DATABASE_SQL =
            "CREATE TABLE IF NOT EXISTS creditCard (id INTEGER PRIMARY KEY, name VARCHAR, bank VARCHAR, cardNumber VARCHAR);";

    public CreditCardDbHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "creditCardManagement", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}