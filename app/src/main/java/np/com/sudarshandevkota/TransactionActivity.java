package np.com.sudarshandevkota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.CacheRequest;

import np.com.sudarshandevkota.model.NewTransaction;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.model.TransactionType;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {
    EditText senderOrReceiverET, noteET, amountET;
    Spinner spinner;
    CheckBox pendingCB;
    Button addUpdateBtn, deleteBtn;
    char type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        senderOrReceiverET = findViewById(R.id.et_sender_receiver);
        noteET = findViewById(R.id.et_tranNote);
        amountET = findViewById(R.id.et_tranAmount);
        pendingCB = findViewById(R.id.cb_pending);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, TransactionType.values()));
        addUpdateBtn = findViewById(R.id.btn_addUpdate);
        deleteBtn = findViewById(R.id.btn_delete);
        addUpdateBtn.setOnClickListener(addListener);
        deleteBtn.setOnClickListener(deleteListener);
        deleteBtn.setVisibility(View.INVISIBLE);

        type = getIntent().getCharExtra("type", 'a');
        if (type == 'u') {
            addUpdateBtn.setText("Update");
            deleteBtn.setVisibility(View.VISIBLE);
            fillFields();
            addUpdateBtn.setOnClickListener(updateListener);
        }
    }

    View.OnClickListener addListener = view -> {
        String senderOrReceiver = senderOrReceiverET.getText().toString();
        boolean isValid = amountET.getText().toString().trim().isEmpty();
        double amount = -1;
        if(!isValid)
            amount = Double.parseDouble(amountET.getText().toString());
        TransactionType type = (TransactionType) spinner.getSelectedItem();
        boolean isPending = pendingCB.isChecked();
        String note = noteET.getText().toString();
        if (senderOrReceiver.isEmpty()) {
            senderOrReceiverET.setError("Cannot be Empty.");
        } else if (amount <= 0) {
            amountET.setError("Must be greater than 0.");
        } else if (note.isEmpty()) {
            noteET.setError("Cannot be Empty.");
        } else {
            addTransaction(new NewTransaction(type, senderOrReceiver, amount, note, isPending));
            Toast.makeText(this, "Transaction Added Successfully.", Toast.LENGTH_SHORT).show();
            finish();

        }
    };
    View.OnClickListener deleteListener = view -> {
        Transaction t = (Transaction) getIntent().getSerializableExtra("object");
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);

        Call<String> call = api.deleteTransaction(t.getId());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        Toast.makeText(this, "Transaction Deleted Successfully.", Toast.LENGTH_SHORT).show();

        finish();
    };

    private void addTransaction(NewTransaction transaction) {
       ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<String> call = api.addTransaction(transaction);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    View.OnClickListener updateListener = v->{
        String senderOrReceiver = senderOrReceiverET.getText().toString();
        double amount = Double.parseDouble(amountET.getText().toString());
        TransactionType type = (TransactionType) spinner.getSelectedItem();
        boolean isPending = pendingCB.isChecked();
        String note = noteET.getText().toString();
        Transaction t = (Transaction) getIntent().getSerializableExtra("object");
        if (senderOrReceiver.isEmpty()) {
            senderOrReceiverET.setError("Cannot be Empty.");
        } else if (amount <= 0) {
            amountET.setError("Must be greater than 0.");
        } else if (note.isEmpty()) {
            noteET.setError("Cannot be Empty.");
        } else {
            t.setNote(note);
            t.setPending(isPending);
            t.setTransactionType(type);
            t.setAmount(amount);
            t.setSenderOrReceiver(senderOrReceiver);
            t.setTimestamp(null);
            updateTransaction(t);
        }
    };

    private void fillFields() {
        Transaction t = (Transaction) getIntent().getSerializableExtra("object");
        amountET.setText(String.valueOf(t.getAmount()));
        noteET.setText(t.getNote());
        senderOrReceiverET.setText(t.getSenderOrReceiver());
        pendingCB.setChecked(t.isPending());
        spinner.setSelection(t.getTransactionType().compareTo(TransactionType.INCOME));
    }
    private void updateTransaction(Transaction t){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<String> call = api.updateTransaction(t);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        Toast.makeText(this, "Transaction Updated Successfully.", Toast.LENGTH_SHORT).show();
        finish();


    }
}