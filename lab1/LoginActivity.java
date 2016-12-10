package com.example.brandon.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.brandon.lab1.R.layout.content_login;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        Button b = (Button) findViewById(R.id.loginButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText text = (EditText) findViewById(R.id.loginEdit);
                String string_email = text.getText().toString();

                SharedPreferences sharedPref = getSharedPreferences("Email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Email", string_email);
                editor.commit();

                text.setText(string_email);
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }


        });


    }


    protected static final String ACTIVITY_NAME = "Login Activity";

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");


    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
        SharedPreferences sharedPref = getSharedPreferences("Email", Context.MODE_PRIVATE);
        String defaultEmail = sharedPref.getString("Email", "email@domain.com");
        EditText text = (EditText) findViewById(R.id.loginEdit);
        text.setText(defaultEmail);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }


}
