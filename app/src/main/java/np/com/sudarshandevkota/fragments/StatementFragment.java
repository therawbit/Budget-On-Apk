package np.com.sudarshandevkota.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import np.com.sudarshandevkota.Listener.ClickListener;
import np.com.sudarshandevkota.R;
import np.com.sudarshandevkota.TransactionActivity;
import np.com.sudarshandevkota.adapter.StatementAdapter;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatementFragment extends Fragment implements ClickListener {
    RecyclerView statementRV;
    ArrayList<Transaction> list;
    StatementAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_statement, container, false);
        statementRV = view.findViewById(R.id.rv_statement);
        statementRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadTransactions();

        return view;
    }
    private void loadTransactions(){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<ArrayList<Transaction>> call = api.getAllTransactions();
        call.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                if(response.body()==null){
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    list =(ArrayList<Transaction>) response.body().stream().filter(t->!t.isPending()).sorted(Comparator.comparing(Transaction::getTimestamp).reversed()).collect(Collectors.toList());
                }
                adapter = new StatementAdapter(getActivity(),list,StatementFragment.this);
               statementRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {

            }
        });
    }

    @Override
    public void click(int position) {
        Intent intent = new Intent(getActivity(), TransactionActivity.class);
        intent.putExtra("type",'u');
        intent.putExtra("object",list.get(position));
        startActivity(intent);
    }
}