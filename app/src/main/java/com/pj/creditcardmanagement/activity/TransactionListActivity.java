package com.pj.creditcardmanagement.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.pj.creditcardmanagement.dao.CreditCardDao;
import com.pj.creditcardmanagement.dao.TransactionDao;

public class TransactionListActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private ListView transactionListView;
    private Button newTransactionButton;
    private SimpleCursorAdapter adapter;

    private TransactionDao transactionDao = new TransactionDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        initializeTransactionList();

        newTransactionButton = (Button)findViewById(R.id.newTransactionButton);
        newTransactionButton.setOnClickListener(this);
    }

    private void initializeTransactionList() {
        transactionListView = (ListView)findViewById(R.id.transactionList);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.credit_card_list_header, transactionListView, false);
        transactionListView.addHeaderView(header, null, false);

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.transaction_list_row,
                null,
                new String[] {
                        TransactionDao.KEY_CREDIT_CARD_NAME,
                        TransactionDao.KEY_AMOUNT,
                        TransactionDao.KEY_TRANSACTION_DATE,
                        TransactionDao.KEY_APPROVAL_CODE
                },
                new int[] {
                        R.id.transactionRowCreditCardName,
                        R.id.transactionRowAmount,
                        R.id.transactionRowTransactionDate,
                        R.id.transactionRowApprovalCode
                },
                0);
        transactionListView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newTransactionButton:
                System.out.println("warf");
                startActivity(new Intent(this, TransactionActivity.class));
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final Context context = this;
        return new CursorLoader(this) {

            @Override
            public Cursor loadInBackground() {
                return transactionDao.getAll(context);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}