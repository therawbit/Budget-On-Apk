package np.com.sudarshandevkota;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import np.com.sudarshandevkota.model.UserRegister;
import np.com.sudarshandevkota.retrofit.ApiCalls;
import np.com.sudarshandevkota.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText nameET,emailET,passwordET,confirmPasswordET;
    Button signupBtn,toLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameET = findViewById(R.id.et_name);
        emailET = findViewById(R.id.et_emailReg);
        passwordET = findViewById(R.id.et_passwordReg);
        confirmPasswordET = findViewById(R.id.et_conPassword);
        signupBtn = findViewById(R.id.btn_signup);
        toLoginBtn = findViewById(R.id.btn_toLogin);
        signupBtn.setOnClickListener(signUpListener);
        toLoginBtn.setOnClickListener(v->{
            startActivity(new Intent(v.getContext(),LoginActivity.class));
            finish();
        });

    }

    View.OnClickListener signUpListener = v->{
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        if(name.isEmpty()){
            nameET.setError("Name Cannot be empty");
        }else if(email.isEmpty()){
            emailET.setError("Email Cannot be empty");
        }else if(password.isEmpty() || password.length()<8){
            passwordET.setError("Password should be of at least 8 characters.");
        }else if(!password.equals(confirmPassword)){
            confirmPasswordET.setError("Password do no match.");
        }
        else
            signup(new UserRegister(name,email,password,confirmPassword));

    };
    private void signup(UserRegister register){
        ApiCalls api = RetrofitClient.getInstance().create(ApiCalls.class);
        Call<String> call = api.registerUser(register);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("TAG", "onResponse: "+response.code());
                if(response.code()==200){
                   goToLogin();
                }else{
                    showToast();
                    goToLogin();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
    private void goToLogin(){
        finish();
    }
    private void showToast(){
        Log.d("TAG", "showToast: "+"into the toast");
        Toast.makeText(this, "User Already Exist", Toast.LENGTH_SHORT).show();
    }
}