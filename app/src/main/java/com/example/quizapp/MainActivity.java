package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button start;

    // Starts neq quiz activity, sending username and unique 'source' string identifier.
    public void start(View view)
    {
        Intent intent = new Intent(this, start.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("source", "new");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editTextName);
        start = findViewById(R.id.startButton);

        // Carries over name if one has been recieved from 'EndScreen' activity.
        if (getIntent().getStringExtra("name") != null)
        {
            name.setText(getIntent().getStringExtra("name"));
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });
    }
}