package com.example.swu.typingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView a;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();

    }

    private void wireWidgets() {
        startButton= (Button) findViewById(R.id.button);
        a = (TextView) findViewById(R.id.textView);



    }

    private void setListeners() {
        startButton.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:

                Intent i = new Intent(MainActivity.this, difficultyActivity.class);
                startActivity(i);

                break;

        }
    }
}
