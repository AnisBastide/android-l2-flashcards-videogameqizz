package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    Questions easy;
    Questions normal;
    Questions hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dialog = new Dialog(this);
        Object qizz = loadJSONFromAsset();
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
                dialog.dismiss();
                break;
            case R.id.mediumButton:
                startActivity(questions);
                dialog.dismiss();
                break;
            case R.id.hardButton:
                startActivity(questions);
                dialog.dismiss();
                break;
        }
    }

    public Object loadJSONFromAsset(){
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
                    json = new JSONObject(response.body());
                    Object easy = json.get("easy");
                    Object normal = json.get("normal");
                    Object hard = json.get("hard");

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
        return qizz;
    }
}