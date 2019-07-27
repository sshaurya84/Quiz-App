package com.shaurya.database;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

public class Quiz extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG ="Quiz" ;
    LinearLayout option1box,option2box,option3box,option4box,submit1,exit;
    TextView option1,option2,option3,option4,question,timer,questionnumber;
    int first,last,score=0,correct,i,count=1,time1=0;
    String answer,timetaken;
    RelativeLayout timerlayout;
    MyCountDownTimer time=null;
    int quiztime,number=6;
    Animation anim;
    ArrayList<String> data=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question=findViewById(R.id.question);
       option1=findViewById(R.id.option1);
       option2=findViewById(R.id.option2);
       option3=findViewById(R.id.option3);
       option4=findViewById(R.id.option4);
       option1box=findViewById(R.id.option1box);
       option2box=findViewById(R.id.option2box);
       option3box=findViewById(R.id.option3box);
       option4box=findViewById(R.id.option4box);
       timer=findViewById(R.id.timer);
       submit1=findViewById(R.id.submit1);
       questionnumber=findViewById(R.id.number);
       exit=findViewById(R.id.exit1);
       timerlayout=findViewById(R.id.timerlayout);


       option1box.setOnClickListener(this);
       option2box.setOnClickListener(this);
       option3box.setOnClickListener(this);
       option4box.setOnClickListener(this);
       submit1.setOnClickListener(this);
       exit.setOnClickListener(this);

        //Getting the Questions and Options.
        data=getIntent().getStringArrayListExtra("data");

        quiztime=Integer.parseInt(data.get(number))*1000;
        time = new MyCountDownTimer(quiztime, 1000);
        time.start();
        Log.e(TAG, "onCreate: "+data);



        //Setting questions and Options.
        question.setText(data.get(0));
        option1.setText(data.get(1));
        option2.setText(data.get(2));
        option3.setText(data.get(3));
        option4.setText(data.get(4));
        first=7;
        i=first;
        correct=5;
        number+=7;
        last=data.size();

         //Animation
        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(60); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option1box:

                option1box.setBackgroundColor(Color.parseColor("#bf2a842d"));
                option1box.setSelected(true);
                option2box.setBackgroundColor(Color.parseColor("#3f000000"));
                option3box.setBackgroundColor(Color.parseColor("#3f000000"));
                option4box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setSelected(false);
                option3box.setSelected(false);
                option4box.setSelected(false);
                break;
            case R.id.option2box:
                option2box.setBackgroundColor(Color.parseColor("#bf2a842d"));
                option2box.setSelected(true);
                option1box.setBackgroundColor(Color.parseColor("#3f000000"));
                option3box.setBackgroundColor(Color.parseColor("#3f000000"));
                option4box.setBackgroundColor(Color.parseColor("#3f000000"));
                option4box.setSelected(false);
                option3box.setSelected(false);
                option1box.setSelected(false);
                break;
            case R.id.option3box:
                option3box.setBackgroundColor(Color.parseColor("#bf2a842d"));
                option3box.setSelected(true);
                option1box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setBackgroundColor(Color.parseColor("#3f000000"));
                option4box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setSelected(false);
                option4box.setSelected(false);
                option1box.setSelected(false);
                break;
            case R.id.option4box:
                option4box.setBackgroundColor(Color.parseColor("#bf2a842d"));
                option4box.setSelected(true);
                option1box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setBackgroundColor(Color.parseColor("#3f000000"));
                option3box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setSelected(false);
                option3box.setSelected(false);
                option1box.setSelected(false);
                break;
            case R.id.submit1:
                timerlayout.clearAnimation();
                timerlayout.setBackgroundColor(Color.parseColor("#5f000000"));
                Log.e(TAG, "onClick: "+time1 );
                count++;
                time.cancel();

                if (option1box.isSelected()) {
                    answer = option1.getText().toString().trim();

                } else if (option2box.isSelected()) {
                    answer = option2.getText().toString().trim();

                } else if (option3box.isSelected()) {
                    answer = option3.getText().toString().trim();

                } else if (option4box.isSelected()) {
                    answer = option4.getText().toString().trim();

                } else {
                    answer = "";
                }

                if (answer.equals(data.get(correct))) {

                    score++;
                    correct += 7;
                } else {
                    correct += 7;
                }
                i = first;
                option4box.setBackgroundColor(Color.parseColor("#3f000000"));
                option1box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setBackgroundColor(Color.parseColor("#3f000000"));
                option3box.setBackgroundColor(Color.parseColor("#3f000000"));

                //Quiz over
                if (first == last) {
                    time.cancel();
                    timetaken=timer.getText().toString().trim();
                    time1+=quiztime/1000-Integer.parseInt(timetaken);
                    killActivity();
                    Intent intent = new Intent(Quiz.this,ScoreCard.class);
                    String result=Integer.toString(score)+"/"+last/7;
                    String stime=Integer.toString(time1-data.size()/7+1)+" seconds";
                    intent.putExtra("score",result);
                    intent.putExtra("timetaken",stime);
                    startActivity(intent);
                } else {

                    timetaken=timer.getText().toString().trim();
                    time1+=quiztime/1000-Integer.parseInt(timetaken);
                    questionnumber.setText(String.valueOf(count));
                    quiztime = Integer.parseInt(data.get(number)) * 1000;
                    time = new MyCountDownTimer(quiztime, 1000);
                    time.start();
                    question.setText(data.get(i));
                    option1.setText(data.get(i + 1));
                    option2.setText(data.get(i + 2));
                    option3.setText(data.get(i + 3));
                    option4.setText(data.get(i + 4));
                    first = i + 7;
                    number += 7;

                }


                Log.e("quiz", "Quiz: " + score);
                break;

            case R.id.exit1:
                time.cancel();
                finish();
                Intent exitintent = new Intent(Quiz.this, HomeScreen.class);
                startActivity(exitintent);

                break;


        }
        }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        //when Timer finishes
        public void onFinish() {
            //reset button
            time.cancel();
            timerlayout.clearAnimation();
            timerlayout.setBackgroundColor(Color.parseColor("#5f000000"));
            count++;
            //Quiz over
            if(first==last){
                time1+=quiztime/1000;
                time.cancel();
                killActivity();
                Intent intent=new Intent(Quiz.this,ScoreCard.class);
                String result=Integer.toString(score)+"/"+last/7;
                String stime=Integer.toString(time1-data.size()/7+1)+" seconds";
                intent.putExtra("score",result);
                intent.putExtra("timetaken",stime);

                startActivity(intent);

            }
            else {
                time1+=quiztime/1000;
                Log.e(TAG, "timer: "+time1 );
                quiztime=Integer.parseInt(data.get(number))*1000;

                time = new MyCountDownTimer(quiztime, 1000);
                time.start();
                i = first;
                option4box.setBackgroundColor(Color.parseColor("#3f000000"));
                option1box.setBackgroundColor(Color.parseColor("#3f000000"));
                option2box.setBackgroundColor(Color.parseColor("#3f000000"));
                option3box.setBackgroundColor(Color.parseColor("#3f000000"));
                questionnumber.setText(String.valueOf(count));
                question.setText(data.get(i));
                option1.setText(data.get(i + 1));
                option2.setText(data.get(i + 2));
                option3.setText(data.get(i + 3));
                option4.setText(data.get(i + 4));
                first = i + 7;
                correct+=7;
                number+=7;


            }
            Log.e("Quiz", "Quiz: "+score);






        }

        @Override
        public void onTick(long millisUntilFinished) {
            //do what ever You want
            timer.setText(""+millisUntilFinished / 1000);
            //Timer warning
            if(timer.getText().toString().trim().equals("5")) {
                timerlayout.setBackgroundColor(Color.parseColor("#d81b24"));
                timerlayout.startAnimation(anim);
            }

        }
    }
    //disable back press
    @Override
    public void onBackPressed()
    {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }


    private void killActivity() {
        finish();
    }
}

