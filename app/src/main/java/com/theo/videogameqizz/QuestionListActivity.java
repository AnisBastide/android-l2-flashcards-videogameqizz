package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.cast.framework.media.MediaQueue;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity{

    private List<Question> questions = new ArrayList<>();
    private QuestionsList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        Intent intent = getIntent();
        questions = intent.getParcelableArrayListExtra("question");
        adapter = new QuestionsList(questions);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}