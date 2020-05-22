package com.example.my_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_firebase.Models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText email_field,password_field,conformpassword_field,phone_field,username_field;
    Button signup;

    String email,password,conformpassword,phone,username,userid;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_field = findViewById(R.id.email_field);
        username_field =findViewById(R.id.username_field);
        password_field = findViewById(R.id.password_field);
        conformpassword_field = findViewById(R.id.conform_password_field);
        phone_field = findViewById(R.id.conform_password_field);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    public void already(View view) {
        Intent intent = new Intent(getApplicationContext(),signIn.class);
        startActivity(intent);
        finish();
    }

    public void signup(View view) {
        email = email_field.getText().toString();
        password = password_field.getText().toString();
        conformpassword = conformpassword_field.getText().toString();
        username = username_field.getText().toString();
        phone = phone_field.getText().toString();

        if (email.isEmpty())
        {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            email_field.requestFocus();
            return;
        }
        if (username.isEmpty())
        {
            Toast.makeText(this, "Enter UserName", Toast.LENGTH_SHORT).show();
            username_field.requestFocus();
            return;
        }
        if (password.length()<6)
        {
            Toast.makeText(this, "Enter Right password more than 6 chars", Toast.LENGTH_SHORT).show();
            password_field.requestFocus();
            return;
        }
        if (!conformpassword.equals(password))
        {
            Toast.makeText(this, "the password don't match", Toast.LENGTH_SHORT).show();
            conformpassword_field.requestFocus();
            return;
        }
        if (phone.isEmpty())
        {
            Toast.makeText(this, "Enter phone Number", Toast.LENGTH_SHORT).show();
            password_field.requestFocus();
            return;
        }

        createUser(email,password,username,phone);
    }

    private void createUser(final String email, final String password, final String username, final String phone)
    {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            userid = task.getResult().getUser().getUid();
                            addUser(email,password,username,phone,userid);

                        }else
                        {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUser(String email, String password, String username, String phone, String userid)
    {
        UserModel userModel = new UserModel(email,password,phone,username,userid);
        databaseReference.child("Users").child(userid).setValue(userModel);

        startActivity(new Intent(getApplicationContext(),Welcome.class));
        finish();
    }





}
