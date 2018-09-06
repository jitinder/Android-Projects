package com.example.android.quizappproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int score = 0;

    public void increaseScore(){
        score = score + 1;
    }

    public void answerOne(){ //Checks if first answer is correct
        RadioButton pikachu = (RadioButton) findViewById(R.id.answerOne);
        if (pikachu.isChecked()){ //As only one option can be chosen
            increaseScore();
        }
    }

    public void answerTwo(){ //Checks if second answer is correct
        CheckBox starly = (CheckBox) findViewById(R.id.starly);
        CheckBox chimchar = (CheckBox) findViewById(R.id.chimchar);
        CheckBox bidoof = (CheckBox) findViewById(R.id.bidoof);
        CheckBox piplup = (CheckBox) findViewById(R.id.piplup);
        //Score increased only with this specific condition
        if(chimchar.isChecked() && piplup.isChecked() && !starly.isChecked() && !bidoof.isChecked()){
            increaseScore();
        }
    }

    public void answerThree(){
        TextView region = (TextView) findViewById(R.id.region);
        if(region.getText().toString().equalsIgnoreCase("hoenn")){
            increaseScore();
        }
    }

    public void answerFour(){
        TextView houndourEvolution = (TextView) findViewById(R.id.houndour_evolution);
        if(houndourEvolution.getText().toString().equalsIgnoreCase("houndoom")){
            increaseScore();
        }
    }

    public void answerFive(){
        CheckBox glaceon = (CheckBox) findViewById(R.id.glaceon);
        CheckBox lumineon = (CheckBox) findViewById(R.id.lumineon);
        CheckBox espeon = (CheckBox) findViewById(R.id.espeon);
        CheckBox vaporeon = (CheckBox) findViewById(R.id.vaporeon);

        if(glaceon.isChecked() && !lumineon.isChecked() && espeon.isChecked() && vaporeon.isChecked()){
            increaseScore();
        }
    }

    public void showScore(View v){ //Shows score after checking all answers as a toast
        String toastMessage = "";
        answerOne();
        answerTwo();
        answerThree();
        answerFour();
        answerFive();
        if(score == 5){
            toastMessage = "Congratulations! All your answers are correct! " + score + "/5";
        } else {
            toastMessage = "You have scored " + score + " out of 5";
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        score = 0;
    }
}
