package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dialog = new Dialog(this);
        findViewById(R.id.startButton).setOnClickListener(this);
        findViewById(R.id.qizzListButton).setOnClickListener(this);
        findViewById(R.id.aboutButton).setOnClickListener(this);
    }

    public void ShowPopup(){
        dialog.setContentView(R.layout.difficulty_popup);
        dialog.setCanceledOnTouchOutside(false);
        Button easyButton = dialog.findViewById(R.id.easyButton);
        Button mediumButton = dialog.findViewById(R.id.mediumButton);
        Button hardButton = dialog.findViewById(R.id.hardButton);
        dialog.findViewById(R.id.exitTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        Intent about = new Intent(this, AboutActivity.class);
        Intent questions = new Intent(this,FlashCardActivity.class);
        Intent questionList = new Intent(this,QuestionListActivity.class);
        switch (view.getId()){
            case R.id.startButton:
                ShowPopup();
                break;
            case R.id.qizzListButton:
                startActivity(questionList);
                break;
            case R.id.aboutButton:
                String[] name = {"Anis Bastide", "Jimmy Lai", "Theovady Moutty"};
                String[] mail = {"anis.bastide@edu.itescia.fr", "jimmy.lai@edu.itescia.fr", "theovady.moutty@edu.itescia.fr"};
                about.putExtra("name", name);
                about.putExtra("mail", mail);
                startActivity(about);
                break;
            case R.id.easyButton:
                startActivity(questions);
                break;
            case R.id.mediumButton:
                startActivity(questions);
                break;
            case R.id.hardButton:
                startActivity(questions);
                break;
        }
    }
}