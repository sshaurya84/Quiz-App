package com.shaurya.database;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class ReviewRecyclerAdapter extends RecyclerView.Adapter {
    Dialog dialog;
    myDbAdapter helper;
    private static final String TAG ="reviewrecycler" ;
    ArrayList<String> data=new ArrayList<String>();
    ArrayList<String> questions=new ArrayList<String>();
    ArrayList<String> option1s=new ArrayList<String>();
    ArrayList<String> option2s=new ArrayList<String>();
    ArrayList<String> option3s=new ArrayList<String>();
    ArrayList<String> option4s=new ArrayList<String>();
    ArrayList<String> correctanswers=new ArrayList<String>();
    ArrayList<String> timearray=new ArrayList<String>();
    Context context;


    public ReviewRecyclerAdapter(Context context, ArrayList<String> data)
    {
        this.context=context;
        this.data=data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reviewrecyclerview, parent, false);
        for (int i=0;i<data.size();i+=7)
        {
            questions.add(data.get(i));
        }
        for (int i=1;i<data.size();i+=7){
            option1s.add(data.get(i));
        }

        for (int i=2;i<data.size();i+=7){
            option2s.add(data.get(i));
        }
        for (int i=3;i<data.size();i+=7){
            option3s.add(data.get(i));
        }
        for (int i=4;i<data.size();i+=7){
            option4s.add(data.get(i));
        }
        for (int i=5;i<data.size();i+=7){
            correctanswers.add(data.get(i));
        }


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((MyViewHolder) holder).actualquestion.setText(questions.get(position));
        ((MyViewHolder) holder).actualoption1.setText(option1s.get(position));
        ((MyViewHolder) holder).actualoption2.setText(option2s.get(position));
        ((MyViewHolder) holder).actualoption3.setText(option3s.get(position));
        ((MyViewHolder) holder).actualoption4.setText(option4s.get(position));
        ((MyViewHolder) holder).correctanswer.setText(correctanswers.get(position));
        ((MyViewHolder) holder).questionno.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return data.size()/7;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        CardView questionlayout;
        LinearLayout editquestion;
        TextView actualquestion,actualoption1,actualoption2,actualoption3,actualoption4,correctanswer,questionno;
        public MyViewHolder(View itemView) {
            super(itemView);
            actualquestion=itemView.findViewById(R.id.actualquestion);
            actualoption1=itemView.findViewById(R.id.actualoption1);
            actualoption2=itemView.findViewById(R.id.actualoption2);
            actualoption3=itemView.findViewById(R.id.actualoption3);
            actualoption4=itemView.findViewById(R.id.actualoption4);
            correctanswer=itemView.findViewById(R.id.correctanswer);
            questionlayout=itemView.findViewById(R.id.questionlayout);
            editquestion=itemView.findViewById(R.id.editquestion);
            questionno=itemView.findViewById(R.id.questionno);




            dialog = new Dialog(context);
            helper = new myDbAdapter(context);

            editquestion.setOnLongClickListener(this);

        }


        @Override
        public boolean onLongClick(View v)
        {
            switch (v.getId())
            {
                case R.id.editquestion:
                    Log.e(TAG, "onLongClick: "+getAdapterPosition());
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.dialog);
                    TextView update =dialog.findViewById(R.id.update);
                    TextView delete =dialog.findViewById(R.id.delete1);
                    update.setOnClickListener(this);
                    delete.setOnClickListener(this);
                    dialog.show();
                    break;

            }
            return  false;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //Deleting the question
                case R.id.delete1:
                    String qname = actualquestion.getText().toString();
                    int a = helper.delete(qname);
                    Log.e(TAG, "onClick: "+helper.getData() );

                    if (a<0)
                    {
                        Toast.makeText(context, "Unsuccessful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Question Deleted.", Toast.LENGTH_SHORT).show();


                        data.remove(getAdapterPosition()*7);
                        data.remove(getAdapterPosition()*7);
                        data.remove(getAdapterPosition()*7);
                        data.remove(getAdapterPosition()*7);
                        data.remove(getAdapterPosition()*7);
                        data.remove(getAdapterPosition()*7);
                        data.remove(getAdapterPosition()*7);
                        questions.remove(getAdapterPosition());
                        option1s.remove(getAdapterPosition());
                        option2s.remove(getAdapterPosition());
                        option3s.remove(getAdapterPosition());
                        option4s.remove(getAdapterPosition());
                        correctanswers.remove(getAdapterPosition());
                        timearray.remove(getAdapterPosition());

                        //If all questions deleted
//                        if (data.isEmpty()){
//                            Toast.makeText(context, "No Questions!", Toast.LENGTH_SHORT).show();
//                            Intent refresh=new Intent(context,HomeScreen.class);
//                            context.startActivity(refresh);
//                        }

                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(),data.size()/7);
                        notifyDataSetChanged();


                    }

//
                    dialog.dismiss();

                    break;

                case R.id.update:
                    //Sending question that needs to be updated.
                    Intent updateintent=new Intent(context,UpdateQuestion.class);
                    String sactualquestion=actualquestion.getText().toString().trim();
                    String sactualoption1=actualoption1.getText().toString().trim();
                    String sactualoption2=actualoption2.getText().toString().trim();
                    String sactualoption3=actualoption3.getText().toString().trim();
                    String sactualoption4=actualoption4.getText().toString().trim();
                    String scorrectanswer=correctanswer.getText().toString().trim();
                    updateintent.putExtra("actualquestion",sactualquestion);
                    updateintent.putExtra("actualoption1",sactualoption1);
                    updateintent.putExtra("actualoption2",sactualoption2);
                    updateintent.putExtra("actualoption3",sactualoption3);
                    updateintent.putExtra("actualoption4",sactualoption4);
                    updateintent.putExtra("correctanswer",scorrectanswer);
                    context.startActivity(updateintent);
                    dialog.dismiss();

                    break;
            }


        }
    }



}
