package com.android.projectnew.Kuis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.projectnew.ArrayData.Pertanyaan3;
import com.android.projectnew.R;

public class ActivityKuis3 extends AppCompatActivity {

    private Pertanyaan3 pertanyaan3 = new Pertanyaan3();
    Button btn3,btn4;
    Button btn1,btn2;
    TextView txtsoal,jmlsoal;

    private String mAnswer ;
    private String akhir;
    private String soalakhir;
    private int mScore =0;
    private int mQuestionNumber=0;
    private int mJmlSoal =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis3);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 =(Button) findViewById(R.id.btn2);
        btn3 =(Button) findViewById(R.id.btn3);
        btn4 =(Button) findViewById(R.id.btn4);
        txtsoal = (TextView) findViewById(R.id.pertanyaan3);
        jmlsoal =(TextView) findViewById(R.id.jmlsoal);
        updatdeQuestion();
        updateSoal(mJmlSoal);
    }

    private void updatdeQuestion(){
        if (mQuestionNumber<pertanyaan3.getLength()){
            txtsoal.setText(pertanyaan3.getQuestion(mQuestionNumber));
            btn1.setText(pertanyaan3.getChoice(mQuestionNumber, 1));
            btn2.setText(pertanyaan3.getChoice(mQuestionNumber, 2));
            btn3.setText(pertanyaan3.getChoice(mQuestionNumber, 3));
            btn4.setText(pertanyaan3.getChoice(mQuestionNumber, 4));
            mAnswer= String.valueOf((pertanyaan3.getcorretAnswer(mQuestionNumber)));
            akhir = String.valueOf (mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            Intent intent = new Intent(ActivityKuis3.this, NextFinishActivity.class);
            intent.putExtra("score", mScore);
            startActivity(intent);
        }
    }
    private void updateSoal(int soal) {jmlsoal.setText(""+mJmlSoal+""); }

    public void onClick(View view){
        Button answer =(Button) view;

        soalakhir = String.valueOf(29);
        System.out.println (answer.getText());
        System.out.println(mAnswer);
        System.out.println(mJmlSoal);
        if (akhir == soalakhir){
            mJmlSoal = mJmlSoal+0;
        }
        else {
            mJmlSoal =mJmlSoal+1;
        }
        if (answer.getText() == mAnswer) {
            mScore = mScore + 1;
        }
        updatdeQuestion();
        updateSoal(mJmlSoal);
    }
}
