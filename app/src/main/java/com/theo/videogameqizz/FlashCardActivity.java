package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashCardActivity extends AppCompatActivity implements View.OnClickListener {

    private Question question;
    Dialog dialog;
    private ImageView imageView;
    private TextView textQuestion;
    private RadioButton answerOne;
    private RadioButton answerTwo;
    private RadioButton answerThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_card);

        dialog = new Dialog(this);

        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(this);

        imageView = findViewById(R.id.imageQuestionImageView);
        textQuestion = findViewById(R.id.QuestionTextView);
        answerOne = findViewById(R.id.radioAnswerRadionButtonOne);
        answerTwo = findViewById(R.id.radioAnswerRadionButtonTwo);
        answerThree = findViewById(R.id.radioAnswerRadionButtonThree);

        imageView.setOnClickListener(this);

        Question questionTest = new Question("Quel est ce jeu?",R.drawable.supersmashbros,
                new Answer("super smash bros","mario","call of duty")
        );
        question =questionTest;

        setQuestionView(question);
    }


    @Override
    public void onClick(View v) {
        RadioGroup radioGroup = findViewById(R.id.radioAnswerRadioGroup);
        // Returns an integer which represents the selected radio button's ID
        int selected = radioGroup.getCheckedRadioButtonId();
        if (selected == -1 && v.getId()==R.id.submitButton) {
            Toast.makeText(FlashCardActivity.this, "veuillez selectionner une reponse", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedButton = findViewById(selected);
        TextView result = findViewById(R.id.resultTextView);

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.submitButton:
                if (selectedButton.getText().equals(question.answer.getGoodAnswer())) {
                    result.setText("Bien joué");
                    result.setTextColor(Color.GREEN);
                }
                else{
                    result.setText("T'es nul bg la bonne réponse etait " + question.answer.getGoodAnswer());
                    result.setTextColor(Color.RED);
                }
                break;
            case R.id.imageQuestionImageView:
                ShowPopup();
                break;
            default:
                break;
        }
    }

    private void setQuestionView(Question question){
        imageView.setImageResource(question.media);

        textQuestion.setText(question.question);

        List<String> answers = new ArrayList<>();
        answers.add(question.answer.getGoodAnswer());
        answers.add(question.answer.getOtherAnswerOne());
        answers.add(question.answer.getOtherAnswerTwo());

        Collections.shuffle(answers);

        answerOne.setText(answers.get(0));
        answerTwo.setText(answers.get(1));
        answerThree.setText(answers.get(2));
    }
    public void ShowPopup() {
        dialog.setContentView(R.layout.popup_card);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}