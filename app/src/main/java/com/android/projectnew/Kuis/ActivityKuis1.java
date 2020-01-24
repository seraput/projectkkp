package com.android.projectnew.Kuis;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.projectnew.ArraySoal.Pertanyaan1;
import com.android.projectnew.R;

import java.util.Locale;

public class ActivityKuis1 extends AppCompatActivity {

private Pertanyaan1 pertanyaan1 = new Pertanyaan1();
    Button btn3,btn4;
    Button btn1,btn2;
    TextView txtsoal,jmlsoal,textViewCountDown;

    private String mAnswer ;
    private String akhir;
    private String soalakhir;
    private int mScore =0;
    private int mQuestionNumber=0;
    private int mJmlSoal =1;
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis1);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 =(Button) findViewById(R.id.btn2);
        btn3 =(Button) findViewById(R.id.btn3);
        btn4 =(Button) findViewById(R.id.btn4);
        txtsoal = (TextView) findViewById(R.id.soalbindo);
        jmlsoal =(TextView) findViewById(R.id.jmlsoal);
        textViewCountDown = (TextView) findViewById(R.id.text_view_countdown);
        updatdeQuestion();
        updateSoal(mJmlSoal);

        textColorDefaultCd = textViewCountDown.getTextColors();
        startCountDown();
    }

    private void updatdeQuestion(){
        if (mQuestionNumber<pertanyaan1.getLength()){
            txtsoal.setText(pertanyaan1.getQuestion(mQuestionNumber));
            btn1.setText(pertanyaan1.getChoice(mQuestionNumber, 1));
            btn2.setText(pertanyaan1.getChoice(mQuestionNumber, 2));
            btn3.setText(pertanyaan1.getChoice(mQuestionNumber, 3));
            btn4.setText(pertanyaan1.getChoice(mQuestionNumber, 4));
            mAnswer= String.valueOf((pertanyaan1.getcorretAnswer(mQuestionNumber)));
            akhir = String.valueOf (mQuestionNumber);
            mQuestionNumber++;

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
//            startCountDown();
        }
        else {
            Intent intent = new Intent(ActivityKuis1.this, NextKuis2Activity.class);
            intent.putExtra("score", mScore);
            startActivity(intent);
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
                int minutes = (int) (timeLeftInMillis / 1000) / 60;
                int seconds = (int) (timeLeftInMillis / 1000) % 60;

                String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                textViewCountDown.setText(timeFormatted);

                if (timeLeftInMillis < 10000){
                    textViewCountDown.setTextColor(Color.RED);
                }else{
                    textViewCountDown.setTextColor(textColorDefaultCd);
                }
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
//                updateCountDownText();
                Intent intent = new Intent(ActivityKuis1.this, NextKuis2Activity.class);
                intent.putExtra("score", mScore);
                startActivity(intent);
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }
    private void updateSoal(int soal) {jmlsoal.setText(""+mJmlSoal+""); }

    public void jawab(View view){
        Button answer =(Button) view;

//        countDownTimer.cancel();

        soalakhir = String.valueOf(49);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}
