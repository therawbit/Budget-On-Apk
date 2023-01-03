package np.com.sudarshandevkota;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if(password.equals(confirmPassword)){
                signup();
            }
        }
    };
    private void signup(){

    }
}