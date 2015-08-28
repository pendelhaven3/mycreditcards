package com.pj.creditcardmanagement.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.pj.creditcardmanagement.dao.CreditCardDao;
import com.pj.creditcardmanagement.gui.CustomColors;


public class CreditCardListActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private ListView creditCardListView;
    private Button newCreditCardButton;
    private SimpleCursorAdapter adapter;

    private CreditCardDao creditCardDao = new CreditCardDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_list);

        initializeCreditCardList();

        newCreditCardButton = (Button)findViewById(R.id.newCreditCardButton);
        newCreditCardButton.setOnClickListener(this);
    }

    private void initializeCreditCardList() {
        creditCardListView = (ListView)findViewById(R.id.creditCardList);

//        LayoutInflater inflater = getLayoutInflater();
//        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.credit_card_list_header, creditCardListView, false);
//        creditCardListView.addHeaderView(header, null, false);

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.credit_card_list_row,
                null,
                new String[] {
                        CreditCardDao.KEY_NAME,
                        CreditCardDao.KEY_BANK
                },
                new int[] {
                        R.id.creditCardRowName,
                        R.id.creditCardRowBank
                },
                0) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    view.setBackgroundColor(CustomColors.AQUA);
                } else {
                    view.setBackgroundColor(CustomColors.SOFT_BLUE);
                }
                return view;
            }
        };
        creditCardListView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_credit_card_list, menu);
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
            case R.id.newCreditCardButton:
                startActivity(new Intent(this, CreditCardActivity.class));
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final Context context = this;
        return new CursorLoader(this) {

            @Override
            public Cursor loadInBackground() {
                return creditCardDao.getAll(context);
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