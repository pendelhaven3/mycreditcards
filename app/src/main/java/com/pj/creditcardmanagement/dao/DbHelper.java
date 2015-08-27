package com.pj.creditcardmanagement.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PJ on 8/24/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private final String CREATE_CREDIT_CARD_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS creditCard (id INTEGER PRIMARY KEY, name, bank, cardNumber)";

    private final String CREATE_TRANSACTION_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS creditCardTransaction (id INTEGER PRIMARY KEY, creditCardId, amount, transactionDate, approvalCode)";

    public DbHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "creditCardManagement", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CREDIT_CARD_TABLE_SQL);
        db.execSQL(CREATE_TRANSACTION_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}