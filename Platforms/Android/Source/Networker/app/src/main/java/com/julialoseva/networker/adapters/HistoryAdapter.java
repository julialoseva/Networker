package com.julialoseva.networker.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.julialoseva.networker.data.entity.IpSnapshot;
import com.julialoseva.networker.ui.IpSnapshotItemView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<IpSnapshot> ipSnapshots;

    public HistoryAdapter(ArrayList<IpSnapshot> ipSnapshots) {
        this.ipSnapshots = ipSnapshots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IpSnapshotItemView itemView = new IpSnapshotItemView(
                parent.getContext()
        );
        return new ViewHolder(
                itemView
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IpSnapshot ipSnapshot = this.ipSnapshots.get(
                position
        );

        if (holder.itemView != null) {
            if (holder.itemView instanceof IpSnapshotItemView) {
                IpSnapshotItemView itemView = (IpSnapshotItemView) holder.itemView;
                itemView.setIpSnapshot(
                        ipSnapshot
                );
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.ipSnapshots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
