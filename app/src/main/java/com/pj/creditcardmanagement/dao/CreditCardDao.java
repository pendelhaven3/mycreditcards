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

    public void save(Context context, CreditCard creditCard) {
        if (creditCard.isNew()) {
            insert(context, creditCard);
        } else {
            update(context, creditCard);
        }
    }

    private static final String INSERT_SQL =
            "INSERT INTO creditCard (name, bank, cardNumber) VALUES (?, ?, ?)";

    private void insert(Context context, CreditCard creditCard) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement(INSERT_SQL);
        stmt.bindString(1, creditCard.getName());
        stmt.bindString(2, creditCard.getBank());
        stmt.bindString(3, creditCard.getCardNumber());

        creditCard.setId(stmt.executeInsert());
    }

    private static final String UPDATE_SQL =
            "UPDATE creditCard SET name = ?, bank = ?, cardNumber = ? WHERE id = ?";

    private void update(Context context, CreditCard creditCard) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement(UPDATE_SQL);
        stmt.bindString(1, creditCard.getName());
        stmt.bindString(2, creditCard.getBank());
        stmt.bindString(3, creditCard.getCardNumber());
        stmt.bindLong(4, creditCard.getId());

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
                new String[]{KEY_ID + " as _id", KEY_NAME, KEY_BANK, KEY_CARD_NUMBER},
                null, null, null, null, KEY_NAME, null);
        while (c.moveToNext()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setId(c.getLong(0));
            creditCard.setName(c.getString(1));
            creditCards.add(creditCard);
        }

        return creditCards;
    }

    public CreditCard findByName(Context context, String name) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(false, TABLE_NAME,
                new String[]{KEY_ID + " as _id", KEY_NAME, KEY_BANK, KEY_CARD_NUMBER},
                KEY_NAME + " = ?",
                new String[]{name},
                null, null, null, null);
        if (c.moveToNext()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setId(c.getLong(0));
            creditCard.setName(c.getString(1));
            return creditCard;
        } else {
            return null;
        }
    }

    public CreditCard findByCardNumber(Context context, String cardNumber) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(false, TABLE_NAME,
                new String[]{KEY_ID + " as _id", KEY_NAME, KEY_BANK, KEY_CARD_NUMBER},
                KEY_CARD_NUMBER + " = ?",
                new String[]{cardNumber},
                null, null, null, null);
        if (c.moveToNext()) {
            return mapRow(c);
        } else {
            return null;
        }
    }

    private CreditCard mapRow(Cursor c) {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(c.getLong(0));
        creditCard.setName(c.getString(1));
        creditCard.setBank(c.getString(2));
        creditCard.setCardNumber(c.getString(3));
        return creditCard;
    }

    public CreditCard get(Context context, long id) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(false, TABLE_NAME,
                new String[]{KEY_ID + " as _id", KEY_NAME, KEY_BANK, KEY_CARD_NUMBER},
                KEY_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (c.moveToNext()) {
            return mapRow(c);
        } else {
            return null;
        }
    }

}