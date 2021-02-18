package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Questions questions;
    private TextView rate;
    private TextView result;
    private TextView difficulty;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Intent intent = getIntent();
        questions = intent.getParcelableExtra("questions");
        difficulty = findViewById(R.id.difficultyTextView);
        result = findViewById(R.id.resultTextView);
        rate = findViewById(R.id.rateTextView);
        ratingBar = findViewById(R.id.ratingBar);

        difficulty.setText(questions.getDifficulty());
        result.setText(questions.getPoints() + "/" + questions.getQuestions().size());
        float rating = (((float) questions.getPoints()) / questions.getQuestions().size());
        rate.setText(rating * 100 + "%");
        ratingBar.setRating(rating * 5);

        findViewById(R.id.returnMenuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(ResultActivity.this, HomePageActivity.class);
                homePage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homePage);
                finish();
            }
        });
    }

}