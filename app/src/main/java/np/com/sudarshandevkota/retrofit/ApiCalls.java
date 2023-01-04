package np.com.sudarshandevkota.retrofit;

import java.util.ArrayList;

import np.com.sudarshandevkota.model.NewTransaction;
import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.model.UserRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCalls {
    @GET("/transaction")
    Call<ArrayList<Transaction>> getAllTransactions();

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("username")String username,@Field("password") String password);

    @GET("/")
    Call<String> test();

    @POST("/user/register")
    Call<String> registerUser(@Body UserRegister register);

    @POST("/transaction")
    Call<String> addTransaction(@Body NewTransaction transaction);

    @DELETE("/transaction/{id}")
    Call<String> deleteTransaction(@Path("id") int id);

    @PUT("/transaction")
    Call<String> updateTransaction(@Body Transaction transaction);
}
