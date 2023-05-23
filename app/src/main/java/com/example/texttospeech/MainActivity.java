package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    EditText et1;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv1= findViewById(R.id.tv1);
        et1= findViewById(R.id.et1);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                //주로 랭귀지를 설정함.
                //i 에는 tts 성공 여부가 들어온다.

                if(i==TextToSpeech.SUCCESS){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {
                //s 에 어터런스 아이디가 들어온다. s에 따라 ㅎ야할일이 다르기 때문에.
                if( s.equals("idtts")){
                    tv1.setText("입력 내용 음성 변환중.....");
                }
                if(s.equals("idtts2")){
                    tv1.setText("학부 소개중.....");
                }
            }

            @Override
            public void onDone(String s) {
                tv1.setText("음성 변환 성공");

            }

           @Override
            public void onError(String s) {
               tv1.setText("음성 변환 실패");

            }
        });

        Button btn1 = findViewById(R.id.btnTTS);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str =et1.getText().toString();
                tts.speak(str,TextToSpeech.QUEUE_FLUSH, null,"idtts");


            }
        });
        Button btn2 = findViewById(R.id.btnSmartIT);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = getString(R.string.smartIT);
                et1.setText(str);
                tts.speak(str,TextToSpeech.QUEUE_FLUSH,null,"idtts2");
            }
        });


        Button btn3 = findViewById(R.id.btnFinish);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.stop();
                tts.shutdown();
                finish();
            }
        });
    }
}