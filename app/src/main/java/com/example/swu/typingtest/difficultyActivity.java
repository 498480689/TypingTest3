package com.example.swu.typingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class difficultyActivity extends AppCompatActivity implements View.OnClickListener{

    private Button easyButton, medButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        wireWidgets();
        setListeners();
    }
    private void wireWidgets() {
        easyButton = (Button) findViewById(R.id.button_easy);
        medButton = (Button) findViewById(R.id.button_medium);
        hardButton = (Button) findViewById(R.id.button_hard);
    }

    private void setListeners() {
        easyButton.setOnClickListener(this);
        medButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_easy:

                Intent i = new Intent(difficultyActivity.this, TypingActivity.class);
                i.putExtra("difficulty", "easy");
                startActivity(i);

                break;

            case R.id.button_medium:

                Intent k = new Intent(difficultyActivity.this, TypingActivity.class);
                k.putExtra("difficulty", "medium");
                startActivity(k);

                break;
            case R.id.button_hard:

                Intent l = new Intent(difficultyActivity.this, TypingActivity.class);
                l.putExtra("difficulty", "hard");
                startActivity(l);

                break;
        }
    }
}


