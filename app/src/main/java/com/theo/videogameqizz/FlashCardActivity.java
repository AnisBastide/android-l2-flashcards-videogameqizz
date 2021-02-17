package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_card);

        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(this);

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
        if (selected == -1) {
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
            default:
                break;
        }
    }

    private void setQuestionView(Question question){
        ImageView imageView =findViewById(R.id.imageQuestionImageView);
        imageView.setImageResource(question.media);

        TextView textQuestion = findViewById(R.id.QuestionTextView);
        textQuestion.setText(question.question);

        RadioButton answerOne = findViewById(R.id.radioAnswerRadionButtonOne);
        RadioButton answerTwo = findViewById(R.id.radioAnswerRadionButtonTwo);
        RadioButton answerThree = findViewById(R.id.radioAnswerRadionButtonThree);

        List<String> answers = new ArrayList<>();
        answers.add(question.answer.getGoodAnswer());
        answers.add(question.answer.getOtherAnswerOne());
        answers.add(question.answer.getOtherAnswerTwo());

        Collections.shuffle(answers);

        answerOne.setText(answers.get(0));
        answerTwo.setText(answers.get(1));
        answerThree.setText(answers.get(2));
    }
}