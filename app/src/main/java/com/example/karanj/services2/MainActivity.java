package com.example.karanj.services2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText apples_et;
    Button start_btn, stop_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apples_et = (EditText) findViewById(R.id.apples_xet);
        start_btn = (Button) findViewById(R.id.start_xbtn);
        stop_btn = (Button) findViewById(R.id.stop_xbtn);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service_i = new Intent(MainActivity.this, MyService.class);
                startService(service_i);
            }
        });


        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service_i = new Intent(MainActivity.this, MyService.class);
                stopService(service_i);
            }
        });

    }
/*
    public void onStart(View v){
        Intent service_i = new Intent(this, MyService.class);
        startService(service_i);
    }*/
/*

    public void onStop(View v) {
        Intent service_i = new Intent(this, MyService.class);
        stopService(service_i);
    }
*/


    public void onClick(View v) {
        Intent i = new Intent(this, Bacon.class);
        String apples_data = apples_et.getText().toString();
        i.putExtra("apple_data", apples_data);

        startActivity(i);
    }
}
