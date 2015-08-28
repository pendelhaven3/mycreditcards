package com.pj.creditcardmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pj.creditcardmanagement.dao.CreditCardDao;
import com.pj.creditcardmanagement.gui.ShowDialog;
import com.pj.creditcardmanagement.model.CreditCard;

public class CreditCardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button saveButton;
    private EditText nameField;
    private EditText bankField;
    private EditText cardNumberField;

    private CreditCardDao creditCardDao = new CreditCardDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        setTitle("Add Credit Card");

        initializeControls();
    }

    private void initializeControls() {
        nameField = (EditText)findViewById(R.id.creditCardNameField);
        bankField = (EditText)findViewById(R.id.creditCardBankField);
        cardNumberField = (EditText)findViewById(R.id.creditCardNumberField);

        saveButton = (Button)findViewById(R.id.saveCreditCardButton);
        saveButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_credit_card, menu);
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
            case R.id.saveCreditCardButton:
                saveCreditCard();
                break;
        }
    }

    private void saveCreditCard() {
        if (validateCard()) {
            CreditCard creditCard = new CreditCard();
            creditCard.setName(nameField.getText().toString());
            creditCard.setBank(bankField.getText().toString());
            creditCard.setNumber(cardNumberField.getText().toString());

            creditCardDao.save(creditCard, this);

            ShowDialog.info(this, "Credit Card saved!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToCreditCardList();
                }
            });
        }
    }

    private boolean validateCard() {
        String cardName = nameField.getText().toString();
        if (cardName.isEmpty()) {
            ShowDialog.error(this, "Name must be specified");
            return false;
        }

        if (isCardNameExisting(cardName)) {
            ShowDialog.error(this, "Name is already used by an existing card");
            return false;
        }

        if (bankField.getText().toString().isEmpty()) {
            ShowDialog.error(this, "Bank must be specified");
            return false;
        }

        if (isCardNumberExisting(cardName)) {
            ShowDialog.error(this, "Card number is already used by an existing card");
            return false;
        }

        return true;
    }

    private boolean isCardNumberExisting(String cardNumber) {
        return creditCardDao.findByCardNumber(this, cardNumber) != null;
    }

    private boolean isCardNameExisting(String name) {
        return creditCardDao.findByName(this, name) != null;
    }

    private void goToCreditCardList() {
        startActivity(new Intent(this, CreditCardListActivity.class));
    }

}