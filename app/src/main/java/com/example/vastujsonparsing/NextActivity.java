package com.example.vastujsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    TextView txtvalue,txtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        txtvalue = findViewById(R.id.value);
        txtid = findViewById(R.id.id);

        Intent intent = getIntent();
//get the attached extras from the intent
//we should use the same key as we used to attach the data.
        String user_name = intent.getStringExtra("USER_NAME");
        String value = intent.getStringExtra("value");

        txtvalue.setText(value);
        txtid.setText(user_name);

        Log.d("value",value);
        Log.d("username",user_name);

    }
}
