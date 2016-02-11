package com.example.android.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class overallscore extends AppCompatActivity {

    TextView quizcounter;
    TextView lessoncounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overallscore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        int quizcntr = extras.getInt("quizcounter");
        int lessoncntr = extras.getInt("lessoncounter");

        lessoncounter = (TextView)findViewById(R.id.textView_1);
        lessoncounter.setText(String.valueOf(lessoncntr));
        quizcounter = (TextView)findViewById(R.id.textView_2);
        quizcounter.setText(String.valueOf(quizcntr));
        //Intent intent = getIntent();
        //int temp = intent.getIntExtra("quizcounter",1);



    }

}
