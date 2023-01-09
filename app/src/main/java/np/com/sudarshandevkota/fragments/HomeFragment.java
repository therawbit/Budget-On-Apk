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
       Call<ArrayList<String>> call = api.getTransactionSummary();
       call.enqueue(new Callback<ArrayList<String>>() {
           @Override
           public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
               Log.d("TAG", "onResponse: "+response.body());
               updateFields(response.body());
           }

           @Override
           public void onFailure(Call<ArrayList<String>> call, Throwable t) {

           }
       });
    }
    private void updateFields(ArrayList<String> str){
        if(str==null){
            return;
        }
        if(str.get(0).equals("null") || str.get(1).equals("null"))
            return;
       double total = Double.parseDouble(str.get(0))-Double.parseDouble(str.get(1));
        totalTV.setText("Rs. "+ String.valueOf(total));
        incomeTV.setText("Rs. "+ str.get(0));
        expenseTV.setText("Rs. "+ str.get(1));
        incomePendingTV.setText("Rs. "+ str.get(2));
        expensePendingTV.setText("Rs. "+ str.get(3));
    }
}