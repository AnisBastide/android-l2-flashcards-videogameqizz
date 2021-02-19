package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

    Question question;
    Questions questions;
    Dialog dialog;
    boolean answered;
    private ImageView imageView;
    private TextView textQuestion;
    private RadioButton answerOne;
    private RadioButton answerTwo;
    private RadioButton answerThree;
    private Button submitButton;
    private TextView questionIndex;
    private TextView difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_card);

        dialog = new Dialog(this);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        imageView = findViewById(R.id.imageQuestionImageView);
        textQuestion = findViewById(R.id.QuestionTextView);
        answerOne = findViewById(R.id.radioAnswerRadionButtonOne);
        answerTwo = findViewById(R.id.radioAnswerRadionButtonTwo);
        answerThree = findViewById(R.id.radioAnswerRadionButtonThree);
        questionIndex = findViewById(R.id.questionIndexTextView);

        imageView.setOnClickListener(this);

        Intent intent = getIntent();
        String difficultyIntent = intent.getStringExtra("DifficultyString");

        Log.d("theotagdifficulty", difficultyIntent);

        if(intent != null && intent.hasExtra("questions")){
        this.questions =intent.getParcelableExtra("questions");
        
        if(this.questions.getIndex()==this.questions.getQuestions().size()){
            Intent result = new Intent(this,ResultActivity.class);
            result.putExtra("questions",questions);
            startActivity(result);
            finish();
            return;
        }
        question=this.questions.getQuestions().get(this.questions.getIndex());
        }
        else{
            if(difficultyIntent == "Easy") {
                Question questionTest = new Question("Quel est ce jeu?", R.drawable.supersmashbros,
                        new Answer("super smash bros", "mario", "call of duty")
                );
                Question questionTestTwo = new Question("Quel est ce jeu 2?", R.drawable.supersmashbros,
                        new Answer("super smash bros", "mario", "call of duty")
                );
                this.questions = new Questions("easy", questionTest, questionTestTwo);
                this.questions.shuffleQuestions();
                question = this.questions.getQuestions().get(this.questions.getIndex());
                return;
            }


            if(difficultyIntent == "Medium"){
                Question questionTest = new Question("Quel est ce jeezrreezraonizbiobzaiobarzu?", R.drawable.supersmashbros,
                        new Answer("super smash bros", "mario", "call of duty")
                );
                Question questionTestTwo = new Question("Quel est ce jeu 2?", R.drawable.supersmashbros,
                        new Answer("super smash bros", "mario", "call of duty")
                );
                this.questions = new Questions("easy", questionTest, questionTestTwo);
                this.questions.shuffleQuestions();
                question = this.questions.getQuestions().get(this.questions.getIndex());
                return;
            }


            if(difficultyIntent == "Hard"){
                Question questionTest = new Question("Quel est ce jeu?feifebofeobie", R.drawable.supersmashbros,
                        new Answer("super smash bros", "mario", "call of duty")
                );
                Question questionTestTwo = new Question("Quel est ce jeu 2?", R.drawable.supersmashbros,
                        new Answer("super smash bros", "mario", "call of duty")
                );
                this.questions = new Questions("easy", questionTest, questionTestTwo);
                this.questions.shuffleQuestions();
                question = this.questions.getQuestions().get(this.questions.getIndex());
                return;
            }

        }
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
        if(answered && v.getId()== R.id.submitButton){
            ViewGroup viewGroup=findViewById(R.id.flashCard);
            viewGroup.invalidate();
            Intent nextQuestions = new Intent(this,FlashCardActivity.class);
            questions.incrementIndex();
            nextQuestions.putExtra("questions",questions);
            startActivity(nextQuestions);
            finish();
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
                    questions.incrementPoints();
                }
                else{
                    result.setText("T'es nul bg la bonne réponse etait " + question.answer.getGoodAnswer());
                    result.setTextColor(Color.RED);
                }
                answered=true;
                submitButton.setText("Next Question");
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

        String questionIndexText = questions.getIndex()+1 + "/" + questions.getQuestions().size();
        questionIndex.setText(questionIndexText);
        difficulty = findViewById(R.id.difficultyTextView);
        difficulty.setText(questions.getDifficulty());


        Collections.shuffle(answers);

        answerOne.setText(answers.get(0));
        answerTwo.setText(answers.get(1));
        answerThree.setText(answers.get(2));
    }
    public void ShowPopup() {
        dialog.setContentView(R.layout.popup_image);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}