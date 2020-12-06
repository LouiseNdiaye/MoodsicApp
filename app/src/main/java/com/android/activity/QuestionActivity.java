package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.model.Question;
import com.android.model.Results;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question;
    private ImageView barProgress;
    private Button option1, option2, option3, option4;
    private List<Question> questionList;
    private int questNum;
    private int points;
    private String era;
    private Results result = new Results(0, "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        barProgress = findViewById(R.id.barProgress);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        getQuestionList();
    }

    private void getQuestionList(){
        questionList = new ArrayList<>();
        questionList.add(new Question("What is your current mood?", "", "", "", ""));
        questionList.add(new Question("Which part of the day?", "", "", "", ""));
        questionList.add(new Question("What do you need?", "", "", "", ""));
        questionList.add(new Question("Which category?", "Modern", "Vintage", "", ""));
        setQuestion();
    }

    private void setQuestion() {
        questNum = 0;
        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());
    }

    @Override
    public void onClick(View v) {
        if (questNum ==3){
            switch(v.getId()) {
                case R.id.option1:
                    era = "option1";
                    break;
                case R.id.option2:
                    era = "option2";
                    break;
            }
        } else {
                switch(v.getId()){
                    case R.id.option1:
                        points =points+ 100;
                        break;
                    case R.id.option2:
                        points =points+ 50;
                        break;
                    case R.id.option3:
                        points =points+ 25;
                        break;
                    case R.id.option4:
                        points =+ 0;
                        break;
                    default:

                }
            }
        changeQuestion();
    }

    private void changeQuestion() {

        if (questNum < questionList.size() - 1) {

            questNum ++;

            question.setText(questionList.get(questNum).getQuestion());
            option1.setText(questionList.get(questNum).getOptionA());
            option2.setText(questionList.get(questNum).getOptionB());
            option3.setText(questionList.get(questNum).getOptionC());
            option4.setText(questionList.get(questNum).getOptionD());

            if (questNum == 1) {
                barProgress.setImageResource(R.drawable.barprogress2);
                option1.setBackgroundResource(R.drawable.smiley_sunny);
                option2.setBackgroundResource(R.drawable.smiley_night);
                option3.setVisibility(View.INVISIBLE);
                option4.setVisibility(View.INVISIBLE);
            }

            if (questNum == 2) {
                barProgress.setImageResource(R.drawable.barprogress3);
                option1.setBackgroundResource(R.drawable.smiley_flash);
                option2.setBackgroundResource(R.drawable.smiley_chill);
                option3.setVisibility(View.INVISIBLE);
                option4.setVisibility(View.INVISIBLE);
            }

            if (questNum == 3) {
                barProgress.setImageResource(R.drawable.barprogress4);
                option1.setBackgroundColor(Color.WHITE);
                option2.setBackgroundColor(Color.WHITE);
                option3.setVisibility(View.INVISIBLE);
                option4.setVisibility(View.INVISIBLE);
            }

        } else {
            result.setPoints(points);
            result.setEra(era);
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);

            QuestionActivity.this.finish();
        }
    }

}