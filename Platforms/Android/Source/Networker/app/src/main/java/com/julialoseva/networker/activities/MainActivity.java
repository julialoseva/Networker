package com.julialoseva.networker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.julialoseva.networker.R;

public class MainActivity extends Activity {

    private Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.historyButton = this.findViewById(R.id.history_button);
        this.historyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(
                                MainActivity.this,
                                HistoryActivity.class
                        );
                        startActivity(intent);
                    }
                }
        );
    }
}
