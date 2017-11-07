package com.example.swu.typingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView a, b;
    private ImageButton aB, bB, cB, dB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();

    }

    private void wireWidgets() {
        aB= (ImageButton) findViewById(R.id.button1);
        bB= (ImageButton) findViewById(R.id.button2);
        cB= (ImageButton) findViewById(R.id.button3);
        dB= (ImageButton) findViewById(R.id.button4);
        a = (TextView) findViewById(R.id.textView);
        b = (TextView) findViewById(R.id.textView2);



    }

    private void setListeners() {
        aB.setOnClickListener(this);
        bB.setOnClickListener(this);
        cB.setOnClickListener(this);
        dB.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button4:
                Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, TypingActivity.class);
                startActivity(i);

                break;
            case R.id.button1:
                Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
