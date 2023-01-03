package np.com.sudarshandevkota.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import np.com.sudarshandevkota.R;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ApiCalls calls = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<ArrayList<Transaction>> call = calls.getAllTransactions();
        call.enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                Log.d("TAG", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {

            }
        });
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}