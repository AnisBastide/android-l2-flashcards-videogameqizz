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

            questions.add(new Question("Quel est ce jeu ?",R.drawable.fortnite,
                    new Answer("Fortinte","PUBG","H1Z1")));

            questions.add(new Question("Quel est ce jeu ?",R.drawable.legendofzeldabreathofthewild,
                    new Answer("Zelda Breath of the wild","Zelda Ocarina of time","Zelda Oracle of season")));

            questions.add(new Question("Quel est ce jeu ?",R.drawable.persona,
                    new Answer("Persona 5","Divinity Original Sin","Mario et Luigi")));

            questions.add(new Question("Comment s’appelle ce protagoniste ?",R.drawable.geralt,
                    new Answer("Geralt de Riv","Gutz","Garen")));

            questions.add(new Question("Comment s’appelle cette map ?",R.drawable.blackopmap,
                    new Answer("yacht","nuketown","Terminal")));

            questions.add(new Question("Quel est ce jeu ?",R.drawable.finalfantasyvi,
                    new Answer("Final Fantasy 6","Final Fantasy 5","Dragon Quest 6")));

            questions.add(new Question("Qui est ce Boss ?",R.drawable.genshinimpact,
                    new Answer("boreas","Sif","Rock")));

            questions.add(new Question("Qui est ce Boss ?",R.drawable.darksoulsgwyn,
                    new Answer("Gwen","Gwendir","Gundir")));

        adapter = new QuestionsList(questions);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}