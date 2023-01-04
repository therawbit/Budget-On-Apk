package np.com.sudarshandevkota.retrofit;

import java.util.ArrayList;

import np.com.sudarshandevkota.model.Transaction;
import np.com.sudarshandevkota.model.UserRegister;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
