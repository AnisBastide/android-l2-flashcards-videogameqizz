package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    Dialog dialog;
    Questions easyQ;
    Questions normalQ;
    Questions hardQ;
    private ArrayList<Question> quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dialog = new Dialog(this);
        loadJSONFromAsset();
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
                questionList.putExtra("question", quest);
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
                questions.putExtra("questions", easyQ);
                startActivity(questions);
                dialog.dismiss();
                break;
            case R.id.mediumButton:
                questions.putExtra("questions", normalQ);
                startActivity(questions);
                dialog.dismiss();
                break;
            case R.id.hardButton:
                questions.putExtra("questions", hardQ);
                startActivity(questions);
                dialog.dismiss();
                break;
        }
    }

    public void loadJSONFromAsset(){
        List<Object> qizz = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gryt.tech:8080/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        VideoGameQizzService service = retrofit.create(VideoGameQizzService.class);
        Call<String> call = service.load();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Flash", "onResponse: "+response);
                JSONObject json  = null;
                try {
                    List<Question> listE = new ArrayList<>();
                    List<Question> listN = new ArrayList<>();
                    List<Question> listH = new ArrayList<>();
                    json = new JSONObject(response.body());
                    JSONArray easy = (JSONArray) json.get("easy");
                    JSONArray normal = (JSONArray) json.get("normal");
                    JSONArray hard = (JSONArray) json.get("hard");
                    for (int i =0; i<easy.length(); i++){
                        listE.add(new Question(easy.getJSONObject(i).getString("question"),
                                new Media(easy.getJSONObject(i).getString("mediaType"), easy.getJSONObject(i).getString("mediaLink")),
                                new Answer(easy.getJSONObject(i).getJSONObject("answer").getString("goodAnswer"),
                                        easy.getJSONObject(i).getJSONObject("answer").getString("otherAnswerOne"),
                                        easy.getJSONObject(i).getJSONObject("answer").getString("otherAnswerTwo"))
                                ));
                    }
                    for (int i =0; i<normal.length(); i++){
                        listN.add(new Question(normal.getJSONObject(i).getString("question"),
                                new Media(normal.getJSONObject(i).getString("mediaType"), normal.getJSONObject(i).getString("mediaLink")),
                                new Answer(normal.getJSONObject(i).getJSONObject("answer").getString("goodAnswer"),
                                        normal.getJSONObject(i).getJSONObject("answer").getString("otherAnswerOne"),
                                        normal.getJSONObject(i).getJSONObject("answer").getString("otherAnswerTwo"))
                        ));
                    }
                    for (int i =0; i<hard.length(); i++){
                        listH.add(new Question(hard.getJSONObject(i).getString("question"),
                                new Media(hard.getJSONObject(i).getString("mediaType"), hard.getJSONObject(i).getString("mediaLink")),
                                new Answer(hard.getJSONObject(i).getJSONObject("answer").getString("goodAnswer"),
                                        hard.getJSONObject(i).getJSONObject("answer").getString("otherAnswerOne"),
                                        hard.getJSONObject(i).getJSONObject("answer").getString("otherAnswerTwo"))
                        ));
                    }
                    quest = new ArrayList<>();
                    quest.addAll(listE);
                    quest.addAll(listN);
                    quest.addAll(listH);
                    easyQ = new Questions("easy", listE);
                    normalQ = new Questions("normal", listN);
                    hardQ = new Questions("hard", listH);
                    qizz.add(easy);
                    qizz.add(normal);
                    qizz.add(hard);
                    Log.i("Flash", "onResponse: "+qizz);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("FlashC", "onFailure: "+t);
            }
        });
    }
}