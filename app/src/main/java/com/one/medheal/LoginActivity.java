package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.one.medheal.preferences.Preferences;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton btnLogin;
    private AppCompatButton btnRegister;
    private AppCompatButton btnSkip;

    private EditText inputEmail;
    private EditText inputPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnDaftar);
        btnSkip = findViewById(R.id.btnSkip);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);

        Preferences.setLogged(LoginActivity.this, false);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        btnClick();

        btnLogin.setOnClickListener(view -> {
            if (!validate()){
                return;
            }

            String email = inputEmail.getText().toString().trim();
            String pass = inputPass.getText().toString().trim();

            auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()){
                            Preferences.setLogged(LoginActivity.this, true);
                            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }
                    });
        });

    }

    private void btnClick(){
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        btnSkip.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean validate(){
        boolean validate = true;

        if (TextUtils.isEmpty(inputEmail.getText().toString())){
            inputEmail.setError("Email harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputPass.getText().toString())){
            inputEmail.setError("Password harus di isi");
            validate = false;
        }
        if (inputPass.getText().length() < 6 ){
            inputEmail.setError("Harus 6 karakter");
            validate = false;
        }

        return validate;
    }


}