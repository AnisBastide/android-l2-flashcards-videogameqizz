package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_card);
        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        RadioGroup radioGroup = findViewById(R.id.radioAnswerRadioGroup);
        Log.d("Onclick", "CLICK");
        // Returns an integer which represents the selected radio button's ID
        int selected = radioGroup.getCheckedRadioButtonId();
        if (selected == -1) {
            Toast.makeText(FlashCard.this, "veuillez selectionner une reponse", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioButton = findViewById(R.id.radioAnswerRadionButton2);
        Boolean checked = radioButton.isChecked();
        Log.d("Onclick", checked.toString());

        TextView result = findViewById(R.id.resultTextView);
        // Check which radio button was clicked

        switch (v.getId()) {
            case R.id.submitButton:
                if (checked) {
                    result.setText("Bien joué");
                    result.setTextColor(Color.GREEN);
                }
                else{
                    result.setText("T'es nul bg la bonne réponse cest super smash");
                    result.setTextColor(Color.RED);
                }
                break;
            default:
                break;
        }
    }

}