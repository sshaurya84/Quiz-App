package com.shaurya.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class LandingPage extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home,about,services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);
        services=findViewById(R.id.services);
        services.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.services:
                Intent service=new Intent(LandingPage.this,HomeScreen.class);
                startActivity(service);
                break;
        }
    }
}
