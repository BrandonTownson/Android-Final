package com.example.brandon.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";

    Button sendMsg;
    EditText textField;
    ListView listShow;
    ArrayList<String> arrayString = new ArrayList<>();

    ChatDataBaseHelper helper;
    SQLiteDatabase db;
    ContentValues vc = new ContentValues();
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_assignment);

        helper = new ChatDataBaseHelper(this);
        db=helper.getWritableDatabase();

        c= db.query(false,helper.name,new String[]{helper.KEY_ID,helper.KEY_MESSAGE},"ID NOT NULL",null,null,null,null,null);
         c.moveToFirst();

        while(!c.isAfterLast() ){
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + c.getString( c.getColumnIndex( helper.KEY_MESSAGE) ) );
            String fn = c.getString( c.getColumnIndex(helper.KEY_MESSAGE) );
            arrayString.add(fn);
            c.moveToNext();

        }



        setContentView(R.layout.activity_chat_window);
        sendMsg = (Button) findViewById(R.id.send_msg);
        textField = (EditText) findViewById(R.id.chatWindow);
        listShow = (ListView) findViewById(R.id.listView);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listShow.setAdapter(messageAdapter);


        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayString.add(textField.getText().toString());
                vc.put(helper.KEY_MESSAGE,textField.getText().toString());
                db.insert(helper.name,"NULL REPLACEMENT VALUE",vc);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                textField.setText("");
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        db.close();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop");

    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume");

    }


    class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return arrayString.size();
        }

        @Override
        public String getItem(int position) {
            return arrayString.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;
        }

    }

}
