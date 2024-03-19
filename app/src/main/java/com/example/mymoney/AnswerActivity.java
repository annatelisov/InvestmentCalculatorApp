package com.example.mymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private Button buttonBack;
    private ImageView setting_IMG_img;
    private TextView textViewAnswerMSG;
    public static final String KEY_ANSWER = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        buttonBack = findViewById(R.id.buttonBack);
        setting_IMG_img = findViewById(R.id.setting_IMG_img);
        textViewAnswerMSG = findViewById(R.id.textViewAnswerMSG);

        Intent previousIntent = getIntent();
        double answer = previousIntent.getExtras().getDouble(KEY_ANSWER);
        textViewAnswerMSG.setText(""+answer);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnswerActivity.this, MainActivity.class));
                finish();
            }
        });

        setting_IMG_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnswerActivity.this, SettingActivity.class));
                finish();
            }
        });
    }
}