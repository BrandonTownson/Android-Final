package com.example.brandon.lab1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class listItems extends AppCompatActivity {
    private final int REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

         ImageButton imgbutton = (ImageButton) findViewById(R.id.imageButton);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        imgbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (photoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);

                }


            }

        });




        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged (CompoundButton click, boolean isChecked){

           Toast.makeText(getApplication(), "the switch is "+ (isChecked ? "on" : "off"),
                   Toast.LENGTH_SHORT).show();
                /**
                if (isChecked){
                    CharSequence text = "switch is on";
                } else {
                    CharSequence text = "Switch is off";
                }
**/
            }

        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listItems.this);
// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "My information to share");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .show();

            }
        });







}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ImageButton imgbutton = (ImageButton) findViewById(R.id.imageButton);

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgbutton.setImageBitmap(imageBitmap);
        }
    }
    protected static final String ACTIVITY_NAME= "ListItems";


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");

    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");

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
