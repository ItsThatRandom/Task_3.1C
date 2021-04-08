package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuestionResult extends AppCompatActivity {
    ProgressBar progress;
    String answer, name;
    Button a, b, c, next;
    int total;


    public void next(View view)
    {
        // Increments the progress bar value and provides unique 'source' string to identify as not
        // starting a new quiz but continuing one. Also sends the 'total' and 'name' values for use.
        if (progress.getProgress() != progress.getMax())
        {
            progress.setProgress(progress.getProgress() + 1);
            Intent intent = new Intent(this, start.class);
            intent.putExtra("currentProgress", progress.getProgress());
            intent.putExtra("name", name);
            intent.putExtra("total", total);
            intent.putExtra("source", "continued");
            startActivity(intent);
        }
        // When all questions complete, sends 'name', 'max' and 'total' values to EndScreen activity.
        else
        {
            Intent intent = new Intent(this, EndScreen.class);
            intent.putExtra("name", name);
            intent.putExtra("max", progress.getMax());
            intent.putExtra("total", total);
            startActivity(intent);
        }
    }

    // Sets the values of all text relevant to the question.
    public void setTexts()
    {
        TextView status = findViewById(R.id.progressNumber);
        TextView title = findViewById(R.id.qTitleText);
        TextView question = findViewById(R.id.qDetailsText);
        a = findViewById(R.id.aButton);
        b = findViewById(R.id.bButton);
        c = findViewById(R.id.cButton);

        status.setText(progress.getProgress() + "/" + progress.getMax());
        int i = progress.getProgress() - 1;

        // Create usable arrays from 'String Arrays' to iterate over with each new question.
        String[] q_titles = getResources().getStringArray(R.array.question_titles);
        String[] q_descriptions = getResources().getStringArray(R.array.questions);
        String[] option_1 = getResources().getStringArray(R.array.button_a_answers);
        String[] option_2 = getResources().getStringArray(R.array.button_b_answers);
        String[] option_3 = getResources().getStringArray(R.array.button_c_answers);
        String[] answers = getResources().getStringArray(R.array.answers);

        answer = answers[i];
        title.setText(q_titles[i]);
        question.setText(q_descriptions[i]);
        a.setText(option_1[i]);
        b.setText(option_2[i]);
        c.setText(option_3[i]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_result);
        progress = findViewById(R.id.progressBar);
        next = findViewById(R.id.nextButton);
        Intent intent = getIntent();

        // Update progress bar, identify chosen button, update texts and set total.
        progress.setProgress(intent.getIntExtra("currentProgress", 0));
        int choice = intent.getIntExtra("choice", 0);
        name = intent.getStringExtra("name");
        total = intent.getIntExtra("total", 0);
        Button chosen = findViewById(choice);
        setTexts();

        // Compare string from chosen answer to correct answer and colour buttons accordingly.
        // If correct, increment total.
        if (chosen.getText().toString().equals(answer))
        {
            chosen.setBackgroundColor(Color.GREEN);
            total++;
        }
        else if (a.getText().toString().equals(answer))
        {
            a.setBackgroundColor(Color.GREEN);
            chosen.setBackgroundColor(Color.RED);
        }
        else if (b.getText().toString().equals(answer))
        {
            b.setBackgroundColor(Color.GREEN);
            chosen.setBackgroundColor(Color.RED);
        }
        else
        {
            c.setBackgroundColor(Color.GREEN);
            chosen.setBackgroundColor(Color.RED);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
            }
        });
    }
}