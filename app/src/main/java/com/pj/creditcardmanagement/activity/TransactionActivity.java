package com.pj.creditcardmanagement.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.pj.creditcardmanagement.dao.CreditCardDao;
import com.pj.creditcardmanagement.dao.TransactionDao;
import com.pj.creditcardmanagement.gui.InfoDialog;
import com.pj.creditcardmanagement.model.CreditCard;
import com.pj.creditcardmanagement.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button saveButton;
    private Spinner creditCardSpinner;
    private EditText amountField;
    private EditText transactionDateField;
    private EditText approvalCodeField;

    private CreditCardDao creditCardDao = new CreditCardDao();
    private TransactionDao transactionDao = new TransactionDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        initializeControls();
    }

    private void initializeControls() {
        initializeCreditCardSpinner();

        amountField = (EditText)findViewById(R.id.amountField);
        transactionDateField = (EditText)findViewById(R.id.transactionDateField);
        approvalCodeField = (EditText)findViewById(R.id.approvalCodeField);

        saveButton = (Button)findViewById(R.id.saveTransactionButton);
        saveButton.setOnClickListener(this);

        creditCardSpinner.setOnItemSelectedListener(this);
    }

    private void initializeCreditCardSpinner() {
        creditCardSpinner = (Spinner)findViewById(R.id.creditCardSpinner);

        List<CreditCard> creditCards = creditCardDao.getAllCreditCards(this);
        creditCards.add(0, new CreditCard(0L, "Select one"));

        ArrayAdapter<CreditCard> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                creditCards);
        creditCardSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
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
            case R.id.saveTransactionButton:
                saveTransaction();
                break;
        }
    }

    private void saveTransaction() {
        Transaction transaction = new Transaction();
        transaction.setCreditCard((CreditCard)creditCardSpinner.getSelectedItem());
        transaction.setAmount(new BigDecimal(amountField.getText().toString()));
        transaction.setTransactionDate(new Date());
        transaction.setApprovalCode(approvalCodeField.getText().toString());

        transactionDao.save(transaction, this);

        InfoDialog.show(this, "Transaction saved!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToTransactionList();
            }
        });
    }

    private void goToTransactionList() {
        startActivity(new Intent(this, TransactionListActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
