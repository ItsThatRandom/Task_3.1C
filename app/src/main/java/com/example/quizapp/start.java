package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class start extends AppCompatActivity {
    int selected = 0, total;
    ProgressBar progress;
    Button a, b, c, submit;
    String name;


    // Sets the button clicked as the value of selected and changes background of currently selected.
    public void selectA(View view)
    {
        selected = R.id.aButton;
        a = findViewById(R.id.aButton);
        a.setBackgroundColor(Color.CYAN);
        b.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
    }
    public void selectB(View view)
    {
        selected = R.id.bButton;
        b = findViewById(R.id.bButton);
        b.setBackgroundColor(Color.CYAN);
        a.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
    }
    public void selectC(View view)
    {
        selected = R.id.cButton;
        c = findViewById(R.id.cButton);
        c.setBackgroundColor(Color.CYAN);
        a.setBackgroundColor(Color.WHITE);
        b.setBackgroundColor(Color.WHITE);
    }

    // Sets the values of all text relevant to the question.
    public void setTexts()
    {
        TextView status = findViewById(R.id.progressNumber);
        TextView title = findViewById(R.id.qTitleText);
        TextView question = findViewById(R.id.qDetailsText);

        status.setText(progress.getProgress() + "/" + progress.getMax());
        int i = progress.getProgress() - 1;

        // Create usable arrays from 'String Arrays' to iterate over with each new question.
        String[] q_titles = getResources().getStringArray(R.array.question_titles);
        String[] q_descriptions = getResources().getStringArray(R.array.questions);
        String[] option_1 = getResources().getStringArray(R.array.button_a_answers);
        String[] option_2 = getResources().getStringArray(R.array.button_b_answers);
        String[] option_3 = getResources().getStringArray(R.array.button_c_answers);

        title.setText(q_titles[i]);
        question.setText(q_descriptions[i]);
        a.setText(option_1[i]);
        b.setText(option_2[i]);
        c.setText(option_3[i]);
    }

    // Ensures answer is chosen and then sends the 'choice', 'name', 'currentProgress' and
    // 'total' values to the QuestionResult activity.
    public void submit(View view)
    {
        if (selected == 0)
        {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.input_error, Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        Intent intent = new Intent(this, QuestionResult.class);
        intent.putExtra("choice", selected);
        intent.putExtra("name", name);
        intent.putExtra("currentProgress", progress.getProgress());
        intent.putExtra("total", total);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TextView welcome = findViewById(R.id.welcome);
        progress = findViewById(R.id.progressBar);
        a = findViewById(R.id.aButton);
        b = findViewById(R.id.bButton);
        c = findViewById(R.id.cButton);
        submit = findViewById(R.id.submitButton);
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        name = intent.getStringExtra("name");
        total = intent.getIntExtra("total", 0);

        if (source.equals("new"))   // If activity started from main home page
        {
            welcome.setText("Welcome " + name + "!");
        }
        else if (source.equals("continued"))    // If activity was started with next question button
        {
            welcome.setVisibility(View.GONE);
            progress.setProgress(intent.getIntExtra("currentProgress", 0));
        }
        setTexts();

        a.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectA(v);
            }
        });

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectB(v);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectC(v);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });
    }
}