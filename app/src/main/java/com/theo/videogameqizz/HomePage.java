package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private HomePage TAG;
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
        switch (view.getId()){
            case R.id.startButton:
                ShowPopup();
                break;
            case R.id.qizzListButton:
                Toast.makeText(this, "qizz List", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutButton:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.easyButton:
                break;
            case R.id.mediumButton:
                break;
            case R.id.hardButton:
                break;
        }
    }
}