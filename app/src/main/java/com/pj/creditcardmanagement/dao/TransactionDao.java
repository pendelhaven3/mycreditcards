package com.pj.creditcardmanagement.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.pj.creditcardmanagement.model.CreditCard;
import com.pj.creditcardmanagement.model.Transaction;

import java.text.MessageFormat;

/**
 * Created by PJ on 8/24/2015.
 */
public class TransactionDao {

    public static final String TABLE_NAME = "creditCardTransaction";
    public static final String KEY_ID = "id";
    public static final String KEY_CREDIT_CARD_ID = "creditCardId";
    public static final String KEY_CREDIT_CARD_NAME = "creditCardName";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_TRANSACTION_DATE = "transactionDate";
    public static final String KEY_APPROVAL_CODE = "approvalCode";

    public void save(Transaction transaction, Context context) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement(
                "INSERT INTO creditCardTransaction (creditCardId, amount, transactionDate, approvalCode) VALUES (?, ?, ?, ?)");
        stmt.bindLong(1, transaction.getCreditCard().getId());
        stmt.bindDouble(2, transaction.getAmount().doubleValue());
        stmt.bindString(3, transaction.getTransactionDate().toString());
        stmt.bindString(4, transaction.getApprovalCode());

        transaction.setId(stmt.executeInsert());
    }

    private static final String BASE_SELECT_SQL =
            "SELECT a.id AS _id, creditCardId, amount, transactionDate, approvalCode," +
                    " b.name AS creditCardName" +
                    " FROM creditCardTransaction a" +
                    " JOIN creditCard b" +
                    "   ON b.id = a.creditCardId";

    public Cursor getAll(Context context) {
        DbHelper dbHelper = new DbHelper(context, null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return db.rawQuery(BASE_SELECT_SQL, null);
    }

}