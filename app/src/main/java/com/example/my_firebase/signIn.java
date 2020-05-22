package com.example.my_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signIn extends AppCompatActivity {
    EditText email_field,password_field;
    String email,password;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email_field = findViewById(R.id.email_field);
        password_field = findViewById(R.id.password_field);
        auth = FirebaseAuth.getInstance();
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public void signin(View view) {
        email = email_field.getText().toString();
        password = password_field.getText().toString();

        if (email.isEmpty())
        {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length()<6)
        {
            Toast.makeText(this, "Enter Right password more than 6 chars", Toast.LENGTH_SHORT).show();
            return;
        }

        Signin(email,password);
    }

    private void Signin(String email, String password)
    {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(getApplicationContext(),Welcome.class));
                            finish();
                        }else
                        {
                            Toast.makeText(signIn.this, task.getException().getMessage(),    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
