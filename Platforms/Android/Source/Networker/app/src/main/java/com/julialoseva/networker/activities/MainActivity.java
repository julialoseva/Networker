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
import com.julialoseva.networker.data.engine.Store;
import com.julialoseva.networker.networking.apis.ipapi.client.IpApiClient;
import com.julialoseva.networker.networking.apis.ipapi.response.GetIpResponse;

import java.util.Date;

public class MainActivity extends Activity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView ipTextView;

    private TextView instructionTextView;

    private Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(
                savedInstanceState
        );
        setContentView(
                R.layout.activity_main
        );

        this.initializeSwipeRefreshLayout();
        this.initializeIpTextView();
        this.initializeInstructionTextView();
        this.initializeHistoryButton();

        this.updateCurrentIPAddress();
    }

    private void initializeSwipeRefreshLayout() {
        this.swipeRefreshLayout = this.findViewById(
                R.id.swipe_refresh_layout
        );
        this.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        updateCurrentIPAddress();
                    }
                }
        );
    }

    private void initializeIpTextView() {
        this.ipTextView = this.findViewById(
                R.id.ip_text_view
        );
    }

    private void initializeInstructionTextView() {
        this.instructionTextView = this.findViewById(
                R.id.instruction_text_view
        );
        this.instructionTextView.setText(
                this.getResources().getString(
                        R.string.instruction
                )
        );
    }

    private void updateIpTextView(String text) {
        this.ipTextView.setText(
                text
        );
    }

    private void initializeHistoryButton() {
        this.historyButton = this.findViewById(
                R.id.history_button
        );
        this.historyButton.setText(
                this.getResources().getString(
                        R.string.history
                ).toUpperCase()
        );
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

    private void beforeCurrentIpRequest() {
        updateIpTextView(
                this.getResources().getString(
                        R.string.updating
                )
        );
    }

    private void afterCurrentIpRequestResult(GetIpResponse response, boolean success) {
        if (success && response != null) {
            Store.getInstance().createIpAddressInformation(
                    response.getQuery(),
                    response.getIsp(),
                    new Date().getTime()
            );

            this.updateIpTextView(
                    response.getQuery()
            );
            this.swipeRefreshLayout.setRefreshing(false);
        } else {
            Toast.makeText(
                    this,
                    this.getResources().getString(
                            R.string.error
                    ),
                    Toast.LENGTH_LONG
            );
        }
    }

    private void updateCurrentIPAddress() {
        new IpApiClient().getIp(new IpApiClient.OnChangeListener() {
            @Override
            public void onStarted() {
                beforeCurrentIpRequest();
            }

            @Override
            public void onSuccess(GetIpResponse response) {
                afterCurrentIpRequestResult(
                        response,
                        true
                );
            }

            @Override
            public void onFailed() {
                afterCurrentIpRequestResult(
                        null,
                        false
                );
            }
        });
    }
}
