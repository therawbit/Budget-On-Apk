package np.com.sudarshandevkota.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import np.com.sudarshandevkota.R;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.model.TransactionType;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    TextView totalTV,incomeTV,expenseTV,incomePendingTV,expensePendingTV;
    Button addTransactionBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        totalTV = view.findViewById(R.id.tv_total);
        incomeTV = view.findViewById(R.id.tv_income);
        expenseTV = view.findViewById(R.id.tv_expense);
        incomePendingTV = view.findViewById(R.id.tv_income_pending);
        expensePendingTV = view.findViewById(R.id.tv_expense_pending);
        addTransactionBtn = view.findViewById(R.id.btn_addTransaction);
        loadTransactions();

        return view;
    }
    private void loadTransactions(){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<ArrayList<Transaction>> call = api.getAllTransactions();
        call.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                updateFields(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {

            }
        });
    }
    private void updateFields(ArrayList<Transaction> transactions){
        int total=0,income=0,expense=0,incomePending=0,expensePending=0;
        for(Transaction transaction:transactions){
            if(!transaction.isPending()){
                if(transaction.getTransactionType().equals(TransactionType.INCOME))
                    income+=transaction.getAmount();
                else
                    expense+=transaction.getAmount();
            }
            else{
                if(transaction.getTransactionType().equals(TransactionType.INCOME))
                    incomePending+=transaction.getAmount();
                else
                    expensePending+=transaction.getAmount();
            }
        }
        total=income-expense;
        totalTV.setText("Rs. "+ total);
        incomeTV.setText("Rs. "+ income);
        expenseTV.setText("Rs. "+ expense);
        incomePendingTV.setText("Rs. "+ incomePending);
        expensePendingTV.setText("Rs. "+ expensePending);
    }
}