package com.julialoseva.networker.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.julialoseva.networker.data.entity.IpAddressInformation;
import com.julialoseva.networker.ui.IpAddressInformationItemView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<IpAddressInformation> ipAddresses;

    public HistoryAdapter(ArrayList<IpAddressInformation> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IpAddressInformationItemView itemView = new IpAddressInformationItemView(
                parent.getContext()
        );
        return new ViewHolder(
                itemView
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IpAddressInformation ipAddressInformation = this.ipAddresses.get(
                position
        );

        if (holder.itemView != null) {
            if (holder.itemView instanceof IpAddressInformationItemView) {
                IpAddressInformationItemView itemView = (IpAddressInformationItemView) holder.itemView;
                itemView.setIpAddressInformation(
                        ipAddressInformation
                );
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.ipAddresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
