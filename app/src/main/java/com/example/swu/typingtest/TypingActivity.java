package com.example.swu.typingtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TypingActivity extends AppCompatActivity{

    private TextView textView1, textView2;
    private ArrayList text = new ArrayList<String>();
    private EditText et;

    private String userInput;
    private int word;
    private int length = 0;
    private int score = 0;
    private String passageText="";
    private String savedText= "";
    private String remainingSentence="";
    private static final String TAG = "hi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing);



        wireWidgets();
        initPassage();
        spacePressed();









        //textView1.setText(et.getText().toString());



    }
    private void wireWidgets() {
        textView1 = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView2);
        et = (EditText) findViewById(R.id.editText);


    }

    private void initPassage() {


        text.add(new String("Hello "));
        text.add(new String("hey "));
        text.add(new String("ma "));
        text.add(new String("boi "));


        for(int i =0; i<text.size(); i++){

            passageText=(String)passageText+text.get(i);

        }textView1.setText(passageText);
    }




    private void spacePressed(){
        final char[] c = new char[1];



        et.addTextChangedListener(new TextWatcher()
        {

            public void afterTextChanged(Editable s)
            {
                Log.d(TAG, "entered afterTextChanged");
                if(s.length()>0) {
                    Log.d(TAG, "afterTextChanged: checked word length");
                    s.getChars((s.length() - 1), (s.length()), c, 0);
                    if (c[c.length-1]==' '||c[c.length-1]=='.'||c[c.length-1]==','||c[c.length-1]=='?'||c[c.length-1]=='!') {
                        Log.d(TAG, "checked last letter");
                        for(int i=0; i<text.size(); i++) {
                            for(int n = i+1; n<text.size(); n++){
                            remainingSentence= remainingSentence + text.get(n);
                            }
                            if (et.getText().toString().equals(text.get(i))) {
                                Toast.makeText(TypingActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();



                                String styledText = savedText + "<font color='green' >"+ text.get(i) + "<font color='grey' >"+remainingSentence ;
                                savedText=savedText+"<font color='green' >"+text.get(i);
                                        //passageText.substring(0, length);
                                        //"<font color='grey' >"+passageText.substring(length, passageText.length()-1);
                                textView1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                score++;
                                textView2.setText("Score"+score);
                            }
                            else{
                                Toast.makeText(TypingActivity.this, "WRONG", Toast.LENGTH_SHORT).show();
                                String styledText = savedText + "<font color='red' >"+ text.get(i) + "<font color='grey' >"+remainingSentence ;
                                savedText=savedText+"<font color='red' >"+ text.get(i);
                                //passageText.substring(0, length);
                                //"<font color='grey' >"+passageText.substring(length, passageText.length()-1);
                                textView1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                            }
                            remainingSentence="";

                        }

                        //textView1.setText(et.getText().toString());
                        et.setText("");
                    }
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
        /*This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after. It is an error to attempt to make changes to s from this callback.*/
            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

    });}

//    public String typingPhrase() {
//        String phraseRead = pass.get(word);
//        String phraseTyped = userInput;
//        if(phraseRead.compareTo(phraseTyped) == 0)
//        {
//            return phraseRead;
//        }
//
//
//
//    }










//    public void onClick(View view){
//        switch(view.getId()){
//            case R.id.button:
//                //userInput.equals(et.getText().toString());
//                textView1.setText(et.getText().toString());
//
//                break;
//
//        }
//    }






}
