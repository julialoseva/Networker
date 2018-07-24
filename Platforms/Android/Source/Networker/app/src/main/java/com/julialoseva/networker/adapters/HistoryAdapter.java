package com.julialoseva.networker.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.julialoseva.networker.data.entity.IpAddress;
import com.julialoseva.networker.ui.IpAddressItemView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<IpAddress> ipAddresses;

    public HistoryAdapter(ArrayList<IpAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IpAddressItemView itemView = new IpAddressItemView(
                parent.getContext()
        );
        return new ViewHolder(
                itemView
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IpAddress ipAddress = this.ipAddresses.get(
                position
        );

        if (holder.itemView != null) {
            if (holder.itemView instanceof IpAddressItemView) {
                IpAddressItemView itemView = (IpAddressItemView) holder.itemView;
                itemView.setIpAddress(
                        ipAddress
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
