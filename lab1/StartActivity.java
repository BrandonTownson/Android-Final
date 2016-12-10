package com.example.brandon.lab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME= "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

       Button b3= (Button) findViewById(R.id.button);
        b3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){
                Intent intent = new Intent(StartActivity.this,listItems.class);
                startActivityForResult(intent,5);
            }
        });

        Button b4= (Button) findViewById(R.id.start_Chat);
        b4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){
               Log.i(ACTIVITY_NAME,"User Clicked Start Chat");
                Intent intentchat = new Intent (StartActivity.this,MessageListActivity.class);
                startActivity(intentchat);
            }
        });

        Button b5 = (Button) findViewById(R.id.weatherbutton);
        b5.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v){
                Log.i(ACTIVITY_NAME,"User clicked weatherForecast");
                Intent intentWeather = new Intent (StartActivity.this,com.example.brandon.lab1.WeatherForecastTest.class);
                startActivity(intentWeather);
            }

        });


    }
@Override
    public void onActivityResult (int requestCode, int responseCode, Intent data){
    if (requestCode ==5){
        Log.i("StartActivity: " ,"Returned to StartOnActivity.onActivityResult");
    } else {
        Log.i("StartActivity: ","Request Code was not 5");
    }
    if (responseCode== Activity.RESULT_OK){
        String messagePassed = data.getStringExtra("Response");
        Toast.makeText(getApplication(), messagePassed,
                Toast.LENGTH_LONG).show();
    }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop");

    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause");

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart");

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume");

    }
}
