package np.com.sudarshandevkota.fragments;

import android.content.Intent;
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
import np.com.sudarshandevkota.TransactionActivity;
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
        addTransactionBtn.setOnClickListener(v->startActivity(new Intent(getActivity(), TransactionActivity.class)));
        loadTransactions();

        return view;
    }
    private void loadTransactions(){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
       Call<ArrayList<Integer>> call = api.getTransactionSummary();
       call.enqueue(new Callback<ArrayList<Integer>>() {
           @Override
           public void onResponse(Call<ArrayList<Integer>> call, Response<ArrayList<Integer>> response) {
               Log.d("TAG", "onResponse: into the response");
           }

           @Override
           public void onFailure(Call<ArrayList<Integer>> call, Throwable t) {

           }
       });
    }
    private void updateFields(String body){
        Log.d("TAG", "updateFields: "+body.split(","));
        String[] str =body.split(",");
        Log.d("TAG", "updateFields: "+str);

        int total = Integer.parseInt(str[0])-Integer.parseInt(str[1]);
        totalTV.setText("Rs. "+ total);
        incomeTV.setText("Rs. "+ str[0]);
        expenseTV.setText("Rs. "+ str[1]);
        incomePendingTV.setText("Rs. "+ str[2]);
        expensePendingTV.setText("Rs. "+ str[3]);
    }
}