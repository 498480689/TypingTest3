package com.example.swu.typingtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TypingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView1, textView2, timeView, hintView;
    private ArrayList text = new ArrayList<String>();
    private EditText et;

    private String userInput;
    private Button giveUp;
    private int word;
    private int length = 0;
    private int score = 0;
    private int place = 0;
    private int uselessTimeNumber = 0;
    private int totalCharacters = 0;
    private String passageEasy1 = "five port map lay country his put low wheel must appear horse their fire cry fall yet stand seem people miss air ago port make never add mind right say everything sit color yet letter vowel teach even eat those sing world miss these yes was seem mother door vowel watch surface area life game leave get fine brought air them deep sleep cross final start wind white keep teach get";
    private String passageEasy2 = "give watch slow most game home here sentence found watch yes where live walk out cut rule something play for had to who were them strong hot family which well hear ship distant moy wonder under very soon short down study questions use game of cover piece money end note once new with speed only head wait by happen also cross what told any thousand inch during box cold than up self let only sit plan stood through first far help problem ever possible";
    private String passageEasy3 = "how low head house state through ease look man go land ran what real kind measure horse low bed table made color those children toward my money hold fact this between and old were had simple end distant had should group five feet close rock draw why star product night add order body toward truestudy fast sound last heard cut cross free true horse class hand develop father north course star";
    private String passageMedium1 = "One of our premises here is that writing about literature, as about any subject, gains in urgence, motivation, and engagement when the writer responds to the work not in a vacuum, but in conversation with other readers and critics. We believe that engaging with other readers, far from distracting attention from the literary text itself, should help bring that text into sharper focus. Another premise is that the class discussions that are a daily feature of literature courses can be a rich and provocative source of they says that student writers can respond to in generating their own interpretations.";
    private String passageMedium2 = "Finally, this edition adds a new chapter on writing online exploring the debate about whether digital technologies improve or degrade the way we think and write, and whether they foster or impeded the meeting of minds. And given the importance of online communication, we're pleased that our book now has its own blog. Updated monthly with current articles from across media, this blog provides a space where students and teachers can literally join the conversation.";
    private String passageMedium3 = "It was the hunter's first time outside Montana. He woke, stricken still with the hours-old vision of ascending through rose-lit cumulus, of houses and barns like specks deep in the snowed-in valleys, all the scrolling country below looking December—brown and black hills streaked with snow, flashes of iced-over lakes, the long braids of a river gleaming at the bottom of a canyon. Above the wing the sky had deepened to a blue so pure he knew it would bring tears to his eyes if he looked long enough.Now it was dark.";
    private String passageHard1 = "molybdenum piazzas pizazz foramens o bstinance assuming suburban pizzas lin oleum aluminum unmanageable, problematical, unaccomodating, troublesome, perplexing, formidable, uncooperative, intransigent description of a sentence. Weird accommodate handkerchief indict cemetery conscience rhythm playwright stewardesses johnny-jump-jump caresses pizzazz suburban obstinate pneumonoultramicroscopicsilicovolcanoconiosis";
    private String passageHard2 = "otorhinolaryngological immunoelectrohoretically psychophysicotherapeutics thyroparathyroidectomized pneumoencephalographically rodioimmunoelectrophoresis psychoneuroendocrinological hepaticocholangiogastrostomy spectrophotofluorometrically pseudopseudohypoparathyroidism pneumonoultramicroscopicsilicovolcanoconiosis pseudopseudohypoparathyroidism floccinaucinihilipilification antidisestablishmentariansm";
    private String passageHard3 = "supercalifragilisticexpialidocious incomprehensibilities strengths euouae unimaginatively honorificabilitudinitatibus tsktsk uncopyrightable subdermatoglyphic sesquipedalianism honorificabilitudinitatibus antidisestablishmentariansm pneumoencephalographically rodioimmunoelectrophoresis psychoneuroendocrinological hepaticocholangiogastrostomy spectrophotofluorometrically";
    private String passageText="";
    private String currentPassage ="";
    private String updatePassage = "";
    private String savedText= "";
    private String remainingSentence="";
    private int beginningIndex = 0;
    private int letterLengthOfPassage = 0;
    private int endIndex = 0;
    private SpannableString styledString;
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
        setListeners();










        //textView1.setText(et.getText().toString());



    }


    private void wireWidgets() {
        textView1 = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView2);
        et = (EditText) findViewById(R.id.editText);
        timeView = (TextView) findViewById(R.id.timeView);
        timeView.setText("Time Remaining: 60");
        giveUp = (Button) findViewById(R.id.button_giveUp);
        hintView = (TextView) findViewById(R.id.textView_hint);
        hintView.setText("*The timer will start once you start typing in the box!" + "\n"+"\n" +" ** Capitalizations are IGNORED!!" );



    }

    private void setListeners() {
        giveUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_giveUp:

                timeView.setText("Done!");
                et.setEnabled(false);
                Intent i = new Intent(TypingActivity.this, endingActivity.class);
                i.putExtra("message1", "THE END");
                i.putExtra("m2", ("Your Speed: "+ "\n" +score + "WPM" + "\n" + totalCharacters + "CPM"));

                startActivity(i);
                finish();

                break;

        }
    }

    private void initPassage() {
        ArrayList easy = new ArrayList<String>();
        easy.add(passageEasy1);
        easy.add(passageEasy2);
        easy.add(passageEasy3);
        ArrayList medium = new ArrayList<String>();
        medium.add(passageMedium1);
        medium.add(passageMedium2);
        medium.add(passageMedium3);
        ArrayList hard = new ArrayList<String>();
        hard.add(passageHard1);
        hard.add(passageHard2);
        hard.add(passageHard3);
        Intent i = getIntent();
        String s = i.getStringExtra("difficulty");
        if (((String)s).equals("easy"))
        {
            int passNumber = (int)(Math.random()*3+1);
            if(passNumber ==1 ) {
                currentPassage = (String)easy.get(passNumber-1);
            }

            else if (passNumber ==2 ){//randomly select passage
                currentPassage = (String)easy.get(passNumber-1);
            }
            else{
                currentPassage = (String)easy.get(passNumber-1);
            }
        }
        if (((String)s).equals("medium"))
        {
            int passNumber = (int)(Math.random()*3+1);
            if(passNumber ==1 ) {
                currentPassage = (String)medium.get(passNumber-1);
            }

            else if (passNumber ==2 ){//randomly select passage
                currentPassage = (String)medium.get(passNumber-1);
            }
            else{
                currentPassage = (String)medium.get(passNumber-1);
            }
        }
        if (((String)s).equals("hard"))
        {
            int passNumber = (int)(Math.random()*3+1);
            if(passNumber ==1 ) {
                currentPassage = (String)hard.get(passNumber-1);
            }

            else if (passNumber ==2 ){//randomly select passage
                currentPassage = (String)hard.get(passNumber-1);
            }
            else{
                currentPassage = (String)hard.get(passNumber-1);
            }
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


        for(int n =0; n<text.size(); n++){

            passageText=(String)passageText+text.get(n);

        }
        textView1.setText(passageText);
        styledString= new SpannableString(passageText);

        for(int n = place; n<text.size(); n++){
            letterLengthOfPassage= letterLengthOfPassage + ((String)text.get(n)).length();
            Log.d(TAG, "length of passage = "+letterLengthOfPassage);
        }
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
                    hintView.setText("");


                    Log.d(TAG, "afterTextChanged: checked word length");
                    s.getChars((s.length() - 1), (s.length()), c, 0);
                    if (c[c.length-1]==' '){
                            //||c[c.length-1]=='.'||c[c.length-1]==','||c[c.length-1]=='?'||c[c.length-1]=='!') {
                        Log.d(TAG, "checked last letter");



                            if(endIndex>= letterLengthOfPassage){
                            timeView.setText("Done!");
                            et.setEnabled(false);
                            Intent i = new Intent(TypingActivity.this, endingActivity.class);
                            i.putExtra("message1", "THE END");
                            i.putExtra("m2", ("Your Speed: "+ "\n" +score + "WPM" + "\n" + totalCharacters + "CPM"));

                            startActivity(i);
                            finish();
                        }
                            if (et.getText().toString().equalsIgnoreCase((String)text.get(place))) {
                                Log.d(TAG, "current place checking"+place);
                                endIndex = endIndex + ((String)text.get(place)).length();
                                beginningIndex = endIndex - ((String)text.get(place)).length();
                                Log.d(TAG, "current beginning index"+beginningIndex);
                                Log.d(TAG, "current ending index"+endIndex);





                                styledString.setSpan(new ForegroundColorSpan(Color.GREEN),beginningIndex, endIndex, 0);
                                textView1.setText(styledString);


//                                String styledText = savedText + "<font color='green' >"+ text.get(place) + "<font color='grey' >"+remainingSentence ;
//                                savedText=savedText+"<font color='green' >"+text.get(place);
//
//                                if (Build.VERSION.SDK_INT >= 24) {
//                                    textView1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE); // for 24 api and more
//                                } else {
//                                    textView1.setText(Html.fromHtml(styledText)); // or for older api
//                                }

                                score++;
                                textView2.setText("Score: "+score);
                                place ++;
                                totalCharacters= totalCharacters + ((String)text.get(place)).length();
                            }
                            else{
                                Log.d(TAG, "current place checking"+place);
                                endIndex = endIndex + ((String)text.get(place)).length();
                                beginningIndex = endIndex - ((String)text.get(place)).length();
                                Log.d(TAG, "current beginning index"+beginningIndex);
                                Log.d(TAG, "current ending index"+endIndex);
//                                if(beginningIndex> letterLengthOfPassage){
//                                    timeView.setText("Done!");
//                                    et.setEnabled(false);
//                                    Intent i = new Intent(TypingActivity.this, endingActivity.class);
//                                    i.putExtra("message1", "THE END");
//                                    i.putExtra("m2", ("Your Speed: "+ "\n" +score + "WPM" + "\n" + totalCharacters + "CPM"));
//
//                                    startActivity(i);
//                                    finish();
//                                }

                                styledString.setSpan(new ForegroundColorSpan(Color.RED),beginningIndex, endIndex, 0);
                                textView1.setText(styledString);

//                                String styledText = savedText + "<font color='red' >"+ text.get(place) + "<font color='grey' >"+remainingSentence ;
//                                savedText=savedText+"<font color='red' >"+ text.get(place);
//                                //passageText.substring(0, length);
//                                //"<font color='grey' >"+passageText.substring(length, passageText.length()-1);
//                                if (Build.VERSION.SDK_INT >= 24) {
//                                    textView1.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE); // for 24 api and more
//                                } else {
//                                    textView1.setText(Html.fromHtml(styledText)); // or for older api
//                                }
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
                            finish();


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
