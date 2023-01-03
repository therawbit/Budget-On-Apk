package np.com.sudarshandevkota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    EditText emailET,passwordET;
    Button loginBtn,toSignUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = findViewById(R.id.et_email);
        passwordET = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.btn_login);
        toSignUpBtn = findViewById(R.id.btn_toSignup);
        loginBtn.setOnClickListener(loginListener);
        toSignUpBtn.setOnClickListener(view -> startActivity(new Intent(view.getContext(),SignupActivity.class)));
    }

    View.OnClickListener loginListener = view -> {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        if(!email.isEmpty() && !password.isEmpty())
            login(email,password);
        else{
            startActivity(new Intent(this,MainActivity.class));
        }

    };
    private void login(String username,String password){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<String> call = api.login(username,password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("TAG", "onResponse: "+response.code());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t);

            }
        });



    }
    private void showToast(String r){
        Toast.makeText(this, r, Toast.LENGTH_SHORT).show();
    }
}