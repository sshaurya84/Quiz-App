package com.shaurya.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateQuestion extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="Update" ;
    myDbAdapter helper;
    EditText eactualquestion,eactualoption1,eactualoption2,eactualoption3,eactualoption4,ecorrectanswer,updatetime;
    String squestion,soption1,soption2,soption3,soption4,scorrectanswer,timequiz;
    Button updatequestion;
    ImageView back;
    String q1,o1,o2,o3,o4,cop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        eactualquestion=findViewById(R.id.eactualquestion);
        eactualoption1=findViewById(R.id.eactualoption1);
        eactualoption2=findViewById(R.id.eactualoption2);
        eactualoption3=findViewById(R.id.eactualoption3);
        eactualoption4=findViewById(R.id.eactualoption4);
        ecorrectanswer=findViewById(R.id.ecorrectanswer);
        updatequestion=findViewById(R.id.updatequestion);
        back=findViewById(R.id.back);

        updatequestion.setOnClickListener(this);
        back.setOnClickListener(this);

        squestion=getIntent().getExtras().getString("actualquestion");
        soption1=getIntent().getExtras().getString("actualoption1");
        soption2=getIntent().getExtras().getString("actualoption2");
        soption3=getIntent().getExtras().getString("actualoption3");
        soption4=getIntent().getExtras().getString("actualoption4");
        scorrectanswer=getIntent().getExtras().getString("correctanswer");
//        timequiz=getIntent().getExtras().getString("timequiz");

        eactualquestion.setText(squestion);
        eactualoption1.setText(soption1);
        eactualoption2.setText(soption2);
        eactualoption3.setText(soption3);
        eactualoption4.setText(soption4);
        ecorrectanswer.setText(scorrectanswer);
//        updatetime.setText(timequiz);

        helper = new myDbAdapter(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.updatequestion:
                //Setting string of typed values
                q1=eactualquestion.getText().toString().trim();
                o1=eactualoption1.getText().toString().trim();
                o2=eactualoption2.getText().toString().trim();
                o3=eactualoption3.getText().toString().trim();
                o4=eactualoption4.getText().toString().trim();
                cop=ecorrectanswer.getText().toString().trim();
//                newtime=updatetime.getText().toString().trim();

                 helper.updateName( squestion, q1);
                 helper.updateOption1( soption1, o1);
                 helper.updateOption2( soption2, o2);
                 helper.updateOption3( soption3, o3);
                 helper.updateOption4( soption4, o4);
                 helper.updateCoption( scorrectanswer, cop);
//          int count=   helper.updatetime( timequiz,newtime );
//                Log.e(TAG, "onClick: "+String.valueOf(count) );
//          if(count<=0)
//          {
//              Toast.makeText(UpdateQuestion.this, "Not update", Toast.LENGTH_SHORT).show();
//          }
//          else
//          {
//              Toast.makeText(this, "Question updated!", Toast.LENGTH_SHORT).show();
//              Intent intent=new Intent(UpdateQuestion.this,ReviewQuestions.class);
//              startActivity(intent);
//          }

                break;

            case R.id.back:
                Intent backintent=new Intent(UpdateQuestion.this,ReviewQuestions.class);
                startActivity(backintent);
                break;

        }
    }
}
