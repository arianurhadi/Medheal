package com.one.medheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPass;
    private EditText inputConPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        inputConPass = findViewById(R.id.inputConfPass);
        AppCompatButton btnRegister = findViewById(R.id.btnRegister);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            if (!validate()){
                return;
            }

            String email = inputEmail.getText().toString().trim();
            String pass = inputPass.getText().toString().trim();

            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
        });

    }
    private boolean validate(){
        boolean validate = true;

        if (TextUtils.isEmpty(inputEmail.getText().toString())){
            inputEmail.setError("Email harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputPass.getText().toString())){
            inputPass.setError("Password harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputConPass.getText().toString())){
            inputConPass.setError("Konfirmasi password harus di isi");
            validate = false;
        }
        if (!inputConPass.getText().toString().equals(inputPass.getText().toString())){
            inputConPass.setError("Password tidak sama");
            validate = false;
        }
        return validate;
    }

}