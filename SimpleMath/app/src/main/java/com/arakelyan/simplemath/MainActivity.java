package com.arakelyan.simplemath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvTimer;
    private TextView tvQuestion;
    private TextView tvOption0;
    private TextView tvOption1;
    private TextView tvOption2;
    private TextView tvOption3;

    private String question;
    private int rightAnswer;
    private int rightAnswerPosition;
    private boolean isPositive;
    private int min = 5;
    private int max = 30;

    private ArrayList<TextView> options = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = findViewById(R.id.tv_score);
        tvTimer = findViewById(R.id.tv_timer);
        tvQuestion = findViewById(R.id.tv_question);
        tvOption0 = findViewById(R.id.tv_option0);
        tvOption1 = findViewById(R.id.tv_option1);
        tvOption2 = findViewById(R.id.tv_option2);
        tvOption3 = findViewById(R.id.tv_option3);

        options.add(tvOption0);
        options.add(tvOption1);
        options.add(tvOption2);
        options.add(tvOption3);

        playNext();

    }

    private void playNext() {

        generateQuestion();

        for (int i = 0; i < options.size(); i++) {
            if (i == rightAnswerPosition) {
                options.get(i).setText(Integer.toString(rightAnswer));
            }
            else {
                options.get(i).setText(Integer.toString(generateWrongAnswer()));
            }
        }
    }

    private void generateQuestion() {

        int firstNumber = (int) (Math.random() * (max - min + 1) + min);
        int secondNumber = (int) (Math.random() * (max - min + 1) + min);

        int mark = (int) (Math.random() * 2);
        isPositive = mark == 1;

        if (isPositive) {
            rightAnswer = firstNumber + secondNumber;
            question = String.format("%s + %s", firstNumber, secondNumber);
        }

        else {
            rightAnswer = firstNumber - secondNumber;
            question = String.format("%s - %s", firstNumber, secondNumber);
        }

        tvQuestion.setText(question);
        rightAnswerPosition = (int) (Math.random() * 4);

    }

    private int generateWrongAnswer() {

        int result;

        do {
            result = (int) (Math.random() * max * 2 + 1) - (max - min);
        }
        while (result == rightAnswer);

        return result;

    }

    public void onClickAnswer(View view) {

        TextView textView = (TextView) view;

        String answer = textView.getText().toString();

        int chosenAnswer = Integer.parseInt(answer);

        if (chosenAnswer == rightAnswer) {
           Toast toast = Toast.makeText(this, "Right :)", Toast.LENGTH_SHORT);
           toast.setGravity(Gravity.CENTER, 0, 0);
           toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "Wrong :( Right answer is " + rightAnswer, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        playNext();
    }

}
