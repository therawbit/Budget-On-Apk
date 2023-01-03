package np.com.sudarshandevkota.retrofit;

import java.util.ArrayList;

import np.com.sudarshandevkota.model.Transaction;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCalls {
    @GET("/transaction")
    Call<ArrayList<Transaction>> getAllTransactions();
}
