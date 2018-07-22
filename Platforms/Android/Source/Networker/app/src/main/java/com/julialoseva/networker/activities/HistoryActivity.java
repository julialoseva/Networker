package com.julialoseva.networker.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.julialoseva.networker.R;
import com.julialoseva.networker.adapters.HistoryAdapter;
import com.julialoseva.networker.data.engine.Store;
import com.julialoseva.networker.data.entity.IpAddressInformation;

import java.util.ArrayList;
import java.util.Collection;

public class HistoryActivity extends Activity {

    private LinearLayoutManager collectionViewLayoutManager;

    private HistoryAdapter collectionViewAdapter;

    private RecyclerView collectionView;

    private ArrayList<IpAddressInformation> ipAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(
                savedInstanceState
        );
        setContentView(
                R.layout.activity_history
        );

        this.ipAddresses = new ArrayList<IpAddressInformation>(
                Store.getInstance()
                        .getAllIpAddressInformation()
        );

        this.initializeActionBar();
        this.initializeCollectionView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeActionBar() {
        final ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(
                this.getResources().getString(
                        R.string.history
                )
        );
    }

    private void initializeCollectionView() {
        this.collectionViewLayoutManager = new LinearLayoutManager(
                this
        );

        this.collectionViewAdapter = new HistoryAdapter(
                this.ipAddresses
        );

        this.collectionView = this.findViewById(
                R.id.recycler_view
        );
        this.collectionView.setHasFixedSize(true);
        this.collectionView.setLayoutManager(
                this.collectionViewLayoutManager
        );
        this.collectionView.setAdapter(
                this.collectionViewAdapter
        );
    }
}
