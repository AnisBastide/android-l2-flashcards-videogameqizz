package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity{

    private List<Question> questions;
    private QuestionsList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        questions = new ArrayList<>();
            questions.add(new Question("Quel est ce jeu ?",R.drawable.supersmashbros,
                    new Answer("super smash bros","mario","call of duty")));
            questions.add(new Question("Quel est ce jeu ?",R.drawable.supersmashbros,
                    new Answer("super smash bros","mario","call of duty")));
            questions.add(new Question("Quel est ce jeu ?",R.drawable.supersmashbros,
                    new Answer("super smash bros","mario","call of duty")));
            questions.add(new Question("Quel est ce jeu ?",R.drawable.supersmashbros,
                    new Answer("super smash bros","mario","call of duty")));
            questions.add(new Question("Quel est ce jeu ?",R.drawable.supersmashbros,
                    new Answer("super smash bros","mario","call of duty")));
        adapter = new QuestionsList(questions);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}