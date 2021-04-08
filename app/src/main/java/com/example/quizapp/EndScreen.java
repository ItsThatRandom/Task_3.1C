package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {
    TextView congrats, result;
    Button restart, finish;
    String name;
    int total, max;

    public void restart(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    public void finish(View view)
    {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        congrats = findViewById(R.id.congratsText);
        result = findViewById(R.id.resultText);
        restart = findViewById(R.id.restartButton);
        finish = findViewById(R.id.finishButton);
        Intent intent = getIntent();

        total = intent.getIntExtra("total", 0);
        name = intent.getStringExtra("name");
        max = intent.getIntExtra("max", 0);

        // Insert values 'name' and the users 'total' against the 'max' potential score.
        congrats.setText("Congratulations " + name + "!");
        result.setText(total + "/" + max);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart(v);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(v);
            }
        });
    }
}