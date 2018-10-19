package com.mick.example.metronome.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mick.example.metronome.R;
import com.mick.example.metronome.utilities.Metronome;

public class MainActivity extends AppCompatActivity {
    private static Metronome metronome;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metronome = new Metronome(this, "Test Session");
        status = (TextView) findViewById(R.id.status);
    }

    public void onClick(View v) {
        EditText edit = (EditText) findViewById(R.id.input_text);
        String input = edit.getText().toString();
        boolean ret = true;
        if (!input.equalsIgnoreCase("")) {
            int newBpm = Integer.parseInt(input);
            ret = metronome.setBpm(newBpm);
        }
        if (ret) {
            if (v.getId() == R.id.start) {
                metronome.start();
            } else if (v.getId() == R.id.stop) {
                metronome.stop();
            }
        }
        status.setText(metronome.getStatus());
    }
}
