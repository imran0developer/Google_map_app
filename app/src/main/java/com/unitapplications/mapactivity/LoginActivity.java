package com.unitapplications.mapactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String IS_USER = "is_user";
    EditText username,password;
        Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(view -> {
        if (username.getText().toString().equals("123")&& password.getText().toString().equals("123")){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_USER,true);
            editor.apply();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        else{
            // for debugging
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        });


    }
}