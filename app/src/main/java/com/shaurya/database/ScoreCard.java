package com.shaurya.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreCard extends AppCompatActivity implements View.OnClickListener {
    TextView totaltimetaken,score;
    CardView home;
    String stotaltimetaken,sscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        totaltimetaken=findViewById(R.id.totaltimetaken);
        score=findViewById(R.id.quizscore);
        home=findViewById(R.id.home);

        home.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        stotaltimetaken=bundle.getString("timetaken");
        sscore=bundle.getString("score");
        score.setText(sscore);
        totaltimetaken.setText(stotaltimetaken);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.home:
                finish();
                Intent homeintent=new Intent(ScoreCard.this,HomeScreen.class);
                startActivity(homeintent);
                break;
        }
    }
}
