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
import np.com.sudarshandevkota.model.TransactionType;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {
    EditText senderOrReceiverET,noteET,amountET;
    Spinner spinner;
    CheckBox pendingCB;
    Button addUpdateBtn,deleteBtn;
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
        addUpdateBtn.setOnClickListener(addUpdateListener);
        deleteBtn.setOnClickListener(deleteListener);
    }

    View.OnClickListener addUpdateListener = view -> {
        String senderOrReceiver = senderOrReceiverET.getText().toString();
        double amount = Double.parseDouble(amountET.getText().toString());
        TransactionType type = (TransactionType) spinner.getSelectedItem();
        boolean isPending = pendingCB.isChecked();
        String note = noteET.getText().toString();
        if(senderOrReceiver.isEmpty()){
            senderOrReceiverET.setError("Cannot be Empty.");
        }else if(amount<=0){
            amountET.setError("Must be greater than 0.");
        }else if(note.isEmpty()){
            noteET.setError("Cannot be Empty.");
        }else{
            addTransaction(new NewTransaction(type,senderOrReceiver,amount,note,isPending));
            Toast.makeText(this, "Transaction Added Successfully.", Toast.LENGTH_SHORT).show();
            finish();

        }
    };
    View.OnClickListener deleteListener = view -> {

    };
    private void addTransaction(NewTransaction transaction){
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
}