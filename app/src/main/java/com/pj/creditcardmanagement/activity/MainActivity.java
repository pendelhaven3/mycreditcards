package com.pj.creditcardmanagement.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button goToCreditCardsButton;
    private Button goToTransactionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        deleteDatabase("creditCardManagement");

        goToCreditCardsButton = (Button)findViewById(R.id.goToCreditCardsButton);
        goToCreditCardsButton.setOnClickListener(this);

        goToTransactionsButton = (Button)findViewById(R.id.goToTransactionsButton);
        goToTransactionsButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.goToCreditCardsButton:
                startActivity(new Intent(this, CreditCardListActivity.class));
                break;
            case R.id.goToTransactionsButton:
                startActivity(new Intent(this, TransactionListActivity.class));
                break;
        }
    }
}
