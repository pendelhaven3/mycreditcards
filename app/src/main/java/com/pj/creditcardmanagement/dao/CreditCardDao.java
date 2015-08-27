package com.pj.creditcardmanagement.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.pj.creditcardmanagement.model.CreditCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PJ on 8/24/2015.
 */
public class CreditCardDao {

    public static final String TABLE_NAME = "creditCard";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_BANK = "bank";
    public static final String KEY_CARD_NUMBER = "cardNumber";

    public void save(CreditCard creditCard, Context context) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO creditCard (name, bank, cardNumber) VALUES (?, ?, ?)");
        stmt.bindString(1, creditCard.getName());
        stmt.bindString(2, creditCard.getBank());
        stmt.bindString(3, creditCard.getNumber());

        creditCard.setId(stmt.executeInsert());
    }

    public Cursor getAll(Context context) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(false, TABLE_NAME,
                new String[] {KEY_ID + " as _id", KEY_NAME, KEY_BANK, KEY_CARD_NUMBER},
                null, null, null, null, KEY_NAME, null);
    }

    public List<CreditCard> getAllCreditCards(Context context) {
        List<CreditCard> creditCards = new ArrayList<>();

        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(false, TABLE_NAME,
                new String[] {KEY_ID + " as _id", KEY_NAME, KEY_BANK, KEY_CARD_NUMBER},
                null, null, null, null, KEY_NAME, null);
        while (c.moveToNext()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setId(c.getLong(0));
            creditCard.setName(c.getString(1));
            creditCards.add(creditCard);
        }

        return creditCards;
    }

}