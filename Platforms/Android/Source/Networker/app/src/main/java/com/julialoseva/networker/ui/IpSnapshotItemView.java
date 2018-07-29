package com.julialoseva.networker.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.julialoseva.networker.R;
import com.julialoseva.networker.data.entity.IpSnapshot;
import com.julialoseva.networker.tools.DateFormatter;

public class IpSnapshotItemView extends FrameLayout {

    private RelativeLayout itemLayout;
    private TextView currentIPTextView;
    private TextView providerTextView;
    private TextView timeTextView;

    private IpSnapshot ipSnapshot;

    public IpSnapshot getIpSnapshot() {
        return this.ipSnapshot;
    }

    public void setIpSnapshot(IpSnapshot ipSnapshot) {
        this.ipSnapshot = ipSnapshot;
        this.currentIPTextView.setText(ipSnapshot.getIp());
        this.providerTextView.setText(ipSnapshot.getProviderName());
        this.timeTextView.setText(
                new DateFormatter()
                        .getFormattedDate(
                                ipSnapshot.getTimestamp()
                        )
        );
    }

    public IpSnapshotItemView(@NonNull Context context) {
        super(context);

        LayoutInflater.from(context)
                .inflate(
                        R.layout.item_ip_snapshot,
                        this
                );

        this.itemLayout = this.findViewById(R.id.item_layout);
        this.currentIPTextView = this.findViewById(R.id.current_ip_text_view);
        this.providerTextView = this.findViewById(R.id.provider_text_view);
        this.timeTextView = this.findViewById(R.id.time_text_view);
    }
}
