package com.julialoseva.networker.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.julialoseva.networker.R;
import com.julialoseva.networker.data.entity.IpAddress;

public class IpAddressItemView extends FrameLayout {

    private RelativeLayout itemLayout;
    private TextView currentIPTextView;
    private TextView providerTextView;
    private TextView timeTextView;

    private IpAddress ipAddress;

    public IpAddress getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public IpAddressItemView(@NonNull Context context) {
        super(context);

        LayoutInflater.from(context)
                .inflate(
                        R.layout.item_ip_address_information,
                        this
                );

        this.itemLayout = this.findViewById(R.id.item_layout);
        this.currentIPTextView = this.findViewById(R.id.current_ip_text_view);
        this.providerTextView = this.findViewById(R.id.provider_text_view);
        this.timeTextView = this.findViewById(R.id.time_text_view);
    }
}
