package np.com.sudarshandevkota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            login();
        else{
            startActivity(new Intent(this,MainActivity.class));
        }

    };
    private void login(){

    }
}