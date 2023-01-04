package np.com.sudarshandevkota.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.stream.Collectors;

import np.com.sudarshandevkota.R;
import np.com.sudarshandevkota.adapter.StatementAdapter;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PendingFragment extends Fragment {
    RecyclerView pendingRv;
    ArrayList<Transaction> list;
    StatementAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pending, container, false);
        pendingRv = view.findViewById(R.id.rv_pending);
        pendingRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadTransactions();
        return view;
    }
    private void loadTransactions(){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<ArrayList<Transaction>> call = api.getAllTransactions();
        call.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    list =(ArrayList<Transaction>) response.body().stream().filter(t->t.isPending()).collect(Collectors.toList());
                }
                adapter = new StatementAdapter(getActivity(),list);
                pendingRv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {

            }
        });
    }
}