package com.shaurya.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ReviewQuestions extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="review";
    ArrayList<String > data=new ArrayList<>();
    myDbAdapter helper;
    RecyclerView reviewrecyclerview;
    LinearLayoutManager linearLayoutManager;
    ReviewRecyclerAdapter reviewRecyclerAdapter;
    RelativeLayout relativeLayout;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_questions);

        helper = new myDbAdapter(this);
        data = helper.getData();

        reviewrecyclerview=findViewById(R.id.reviewrecyclerview);
        back=findViewById(R.id.back);
        linearLayoutManager=new LinearLayoutManager(this);
        back.setOnClickListener(this);
        recycler();

    }

    private void recycler() {
        reviewRecyclerAdapter=new ReviewRecyclerAdapter(this,data);
        reviewrecyclerview.setAdapter(reviewRecyclerAdapter);
        reviewrecyclerview.setLayoutManager(linearLayoutManager);
        reviewrecyclerview.getRecycledViewPool().setMaxRecycledViews(0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back:
                finish();
                Intent backintent=new Intent(ReviewQuestions.this,HomeScreen.class);
                startActivity(backintent);

        }
    }
}
