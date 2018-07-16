package com.julialoseva.networker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.julialoseva.networker.R;
import com.julialoseva.networker.networking.apis.ipapi.client.IpApiClient;
import com.julialoseva.networker.networking.apis.ipapi.response.GetIpResponse;

public class MainActivity extends Activity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private Button historyButton;

    private TextView ipTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.swipeRefreshLayout = this.findViewById(R.id.swipe_refresh_layout);
        this.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        updateIPAddress();
                    }
                }
        );

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

        this.ipTextView = this.findViewById(R.id.ip_text_view);
    }

    public void updateIPAddress() {
        new IpApiClient().getIp(new IpApiClient.OnChangeListener() {
            @Override
            public void onStarted() {
            }

            @Override
            public void onSuccess(GetIpResponse response) {
                ipTextView.setText(response.getQuery().toString());
            }

            @Override
            public void onFailed() {
                Toast.makeText(
                        MainActivity.this,
                        "Error",
                        Toast.LENGTH_LONG
                );
            }
        });
    }
}
