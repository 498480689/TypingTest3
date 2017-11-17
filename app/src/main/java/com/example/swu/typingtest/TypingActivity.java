package com.example.swu.typingtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TypingActivity extends AppCompatActivity{

    private TextView textView1, textView2, timeView;
    private ArrayList text = new ArrayList<String>();
    private EditText et;

    private String userInput;
    private int word;
    private int length = 0;
    private int score = 0;
    private int place = 0;
    private int uselessTimeNumber = 0;
    private int totalCharacters = 0;
    private String passage1 = "One of our premises here is that writing about literature, as about any subject, gains in urgence, motivation, and engagement when the writer responds to the work not in a vacuum, but in conversation with other readers and critics. We believe that engaging with other readers, far from distracting attention from the literary text itself, should help bring that text into sharper focus. Another premise is that the class discussions that are a daily feature of literature courses can be a rich and provocative source of they says that student writers can respond to in generating their own interpretations.";
    private String passage2 = "Finally, this edition adds a new chapter on writing online exploring the debate about whether digital technologies improve or degrade the way we think and write, and whether they foster or impeded the meeting of minds. And given the importance of online communication, we're pleased that our book now has its own blog, theysayiblog. Updated monthly with current articles from across media, this blog provides a space where students and teachers can literally join the conversation.";
    private String passage3 = "It was the hunter's first time outside Montana. He woke, stricken still with the hours-old vision of ascending through rose-lit cumulus, of houses and barns like specks deep in the snowed-in valleys, all the scrolling country below looking December—brown and black hills streaked with snow, flashes of iced-over lakes, the long braids of a river gleaming at the bottom of a canyon. Above the wing the sky had deepened to a blue so pure he knew it would bring tears to his eyes if he looked long enough.Now it was dark.";
    private String passageText="";
    private String currentPassage ="";
    private String updatePassage = "";
    private String savedText= "";
    private String remainingSentence="";
    private static final String TAG = "hi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_typing);



        wireWidgets();
        initPassage();
        spacePressed();










        //textView1.setText(et.getText().toString());



    }
    private void wireWidgets() {
        textView1 = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView2);
        et = (EditText) findViewById(R.id.editText);
        timeView = (TextView) findViewById(R.id.timeView);
        timeView.setText("Time Remaining: 60");


    }

    private void initPassage() {
        int passNumber = (int)(Math.random()*3+1);
        if(passNumber ==1 ) {
            currentPassage = passage1;
        }

        else if (passNumber ==2 ){//randomly select passage
            currentPassage = passage2;
        }
        else{
            currentPassage = passage3;
        }



        updatePassage = currentPassage;


        while(updatePassage.indexOf(" ")>0 && updatePassage.length()>0){
            text.add(new String(updatePassage.substring(0, (updatePassage.indexOf(" ")+1))));

            updatePassage = updatePassage.substring(updatePassage.indexOf(" ")+1);


        }
        text.add(updatePassage);


//        text.add(new String("Hello "));
//        text.add(new String("hey "));
//        text.add(new String("ma "));
//        text.add(new String("boi "));


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
                    uselessTimeNumber++;


                    Log.d(TAG, "afterTextChanged: checked word length");
                    s.getChars((s.length() - 1), (s.length()), c, 0);
                    if (c[c.length-1]==' '){
                            //||c[c.length-1]=='.'||c[c.length-1]==','||c[c.length-1]=='?'||c[c.length-1]=='!') {
                        Log.d(TAG, "checked last letter");

                            for(int n = place+1; n<text.size(); n++){
                            remainingSentence= remainingSentence + text.get(n);
                            }
                            if (et.getText().toString().equals(text.get(place))) {




                                String styledText = savedText + "<font color='green' >"+ text.get(place) + "<font color='grey' >"+remainingSentence ;
                                savedText=savedText+"<font color='green' >"+text.get(place);
                                        //passageText.substring(0, length);
                                        //"<font color='grey' >"+passageText.substring(length, passageText.length()-1);
                                textView1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                score++;
                                textView2.setText("Score: "+score);
                                place ++;
                                totalCharacters= totalCharacters + ((String)text.get(place)).length();
                            }
                            else{

                                String styledText = savedText + "<font color='red' >"+ text.get(place) + "<font color='grey' >"+remainingSentence ;
                                savedText=savedText+"<font color='red' >"+ text.get(place);
                                //passageText.substring(0, length);
                                //"<font color='grey' >"+passageText.substring(length, passageText.length()-1);
                                textView1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
                                place ++;
                            }
                            remainingSentence="";



                        //textView1.setText(et.getText().toString());
                        et.setText("");
                    }
                }
                if(uselessTimeNumber == 1) {

                    new CountDownTimer(60000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timeView.setText("Time Remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            timeView.setText("Done!");
                            et.setEnabled(false);
                            Intent i = new Intent(TypingActivity.this, endingActivity.class);
                            i.putExtra("message1", "THE END");
                            i.putExtra("m2", ("Your Speed: "+ "\n" +score + "WPM" + "\n" + totalCharacters + "CPM"));
                            startActivity(i);


                        }
                    }.start();
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
//









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
