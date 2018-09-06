package com.example.android.scoreboardappproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int engRuns = 0;
    int engWickets = 0;
    int ausRuns = 0;
    int ausWickets = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayEnglandStats(){
        TextView a = (TextView) findViewById(R.id.Eruns);
        a.setText(engRuns + "");
        TextView b = (TextView) findViewById(R.id.Ewickets);
        b.setText(engWickets + "");
    }

    public void addOneToEngland(View v){
        engRuns = engRuns + 1;
        displayEnglandStats();
    }

    public void addTwoToEngland(View v){
        engRuns = engRuns + 2;
        displayEnglandStats();
    }

    public void addFourToEngland(View v){
        engRuns = engRuns + 4;
        displayEnglandStats();
    }

    public void addSixToEngland(View v){
        engRuns = engRuns + 6;
        displayEnglandStats();
    }

    public void englandWicket(View v){
        engWickets = engWickets + 1;
        displayEnglandStats();
    }

    public void displayAustraliaStats(){
        TextView a = (TextView) findViewById(R.id.Aruns);
        a.setText(ausRuns + "");
        TextView b = (TextView) findViewById(R.id.Awickets);
        b.setText(ausWickets + "");
    }

    public void addOneToAustralia(View v){
        ausRuns = ausRuns + 1;
        displayAustraliaStats();
    }

    public void addTwoToAustralia(View v){
        ausRuns = ausRuns + 2;
        displayAustraliaStats();
    }

    public void addFourToAustralia(View v){
        ausRuns = ausRuns + 4;
        displayAustraliaStats();
    }

    public void addSixToAustralia(View v){
        ausRuns = ausRuns + 6;
        displayAustraliaStats();
    }

    public void australiaWicket(View v){
        ausWickets = ausWickets + 1;
        displayAustraliaStats();
    }

    public void resetScore(View v){
        engRuns = 0;
        engWickets = 0;
        ausRuns = 0;
        ausWickets = 0;
        displayAustraliaStats();
        displayEnglandStats();
    }

}
