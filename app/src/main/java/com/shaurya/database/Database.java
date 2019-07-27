package com.shaurya.database;

import android.app.Notification;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database extends AppCompatActivity implements View.OnClickListener {

    EditText question,option1,option2,option3,option4,coption,timeofquiz;
    CardView submit;
    String q,o1,o2,o3,o4,co,to;
    myDbAdapter helper;
    ArrayList<String> data=new ArrayList<>();
    ImageView back;


//    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        question=findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        coption=findViewById(R.id.coption);
        submit=findViewById(R.id.submit);
        timeofquiz=findViewById(R.id.timeofquiz);
        back=findViewById(R.id.back);

        helper = new myDbAdapter(this);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);



    }
    public void addQuestion()
    {
         q = question.getText().toString();
         o1= option1.getText().toString();
         o2 = option2.getText().toString();
         o3 = option3.getText().toString();
         o4 = option4.getText().toString();
         co = coption.getText().toString();
         to=timeofquiz.getText().toString();




        if(q.isEmpty() || o1.isEmpty() || o2.isEmpty() || o3.isEmpty() || o4.isEmpty() || co.isEmpty()||to.isEmpty())
        {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Add question and options
            long id = helper.insertData(q,o1,o2,o3,o4,co,to);
            if(id<=0)
            {
                Toast.makeText(this, "Question could not be added.", Toast.LENGTH_SHORT).show();
                question.setText("");
                option1.setText("");
                option2.setText("");
                option3.setText("");
                option4.setText("");
                coption.setText("");
                timeofquiz.setText("");

            }
            else
            {
                Toast.makeText(this, "Question added.", Toast.LENGTH_SHORT).show();
                question.setText("");
                option1.setText("");
                option2.setText("");
                option3.setText("");
                option4.setText("");
                coption.setText("");
                timeofquiz.setText("");
            }
        }
    }





    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submit:
                addQuestion();
                break;
            case R.id.back:
//                Intent backintent=new Intent(Database.this,HomeScreen.class);
//                startActivity(backintent);
                onBackPressed();
                break;

        }
    }
}
