package com.shaurya.database;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="Home" ;
    CardView play,create,review,exit;
    ArrayList<String >  data=new ArrayList<>();
    ArrayList<String > data1=new ArrayList<>();
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit=findViewById(R.id.exit);
        play=findViewById(R.id.play);
        create=findViewById(R.id.create);
        review=findViewById(R.id.review);

        exit.setOnClickListener(this);
        play.setOnClickListener(this);
        create.setOnClickListener(this);
        review.setOnClickListener(this);

        helper = new myDbAdapter(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.play:
                if(helper.getData().isEmpty()){
                    Toast.makeText(this, "Please Create a Quiz first!", Toast.LENGTH_SHORT).show();
                }
                else {
                    helper.getData().clear();
                    data = helper.getData();
                    Log.e(TAG, "data " + data);
                    Intent intent = new Intent(HomeScreen.this, Quiz.class);
                    intent.putStringArrayListExtra("data", data);
                    startActivity(intent);

                }
                    break;
            case R.id.create:
                Intent createintent=new Intent(HomeScreen.this,Database.class);
                startActivity(createintent);
                break;
            case R.id.review:
                Intent viewintent=new Intent(HomeScreen.this,ReviewQuestions.class);
                startActivity(viewintent);
            case R.id.exit:
                finish();
                break;
        }
    }

    public void viewdata(View view)
    {





    }
}
