package com.momobites.www.speaknepali;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.momobites.www.speaknepali.Basic.Colors;
import com.momobites.www.speaknepali.Basic.Family;
import com.momobites.www.speaknepali.Basic.Letters;
import com.momobites.www.speaknepali.Basic.Numbers;
import com.momobites.www.speaknepali.Basic.Phrases;
import com.momobites.www.speaknepali.Basic.Time;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the particular category
        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView familyMembers = (TextView) findViewById(R.id.family);
        TextView colors = (TextView) findViewById(R.id.colors);
        TextView time = (TextView) findViewById(R.id.time);
        TextView phrases = (TextView) findViewById(R.id.phrases);
        TextView letters = (TextView) findViewById(R.id.letters);

        // Set a click listener on Letter View
        letters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lettersIntent = new Intent(MainActivity.this, Letters.class);
                startActivity(lettersIntent);
            }
        });

        // Set a click listener on Number View
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, Numbers.class);
                startActivity(numbersIntent);
            }
        });

        // Set a click listener on Family Members View
        familyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyMembersIntent = new Intent(MainActivity.this, Family.class);
                startActivity(familyMembersIntent);
            }
        });

        // Set a click listener on Colors View
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, Colors.class);
                startActivity(colorsIntent);
            }
        });

        // Set a click listener on Time View
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timeIntent = new Intent(MainActivity.this, Time.class);
                startActivity(timeIntent);
            }
        });

        // Set a click listener on Phrases View
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesIntent = new Intent(MainActivity.this, Phrases.class);
                startActivity(phrasesIntent);
            }
        });



    }
}
