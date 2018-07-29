package com.julialoseva.networker.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.julialoseva.networker.R;
import com.julialoseva.networker.adapters.HistoryAdapter;
import com.julialoseva.networker.data.engine.Store;
import com.julialoseva.networker.data.entity.IpSnapshot;
import com.julialoseva.networker.decorations.EqualSpacingItemDecoration;

import java.util.ArrayList;

public class HistoryActivity extends Activity {

    private LinearLayoutManager collectionViewLayoutManager;

    private HistoryAdapter collectionViewAdapter;

    private RecyclerView collectionView;

    private ArrayList<IpSnapshot> ipSnapshots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(
                savedInstanceState
        );
        setContentView(
                R.layout.activity_history
        );

        this.ipSnapshots = new ArrayList<IpSnapshot>(
                Store.getInstance()
                        .getAllIpSnapshotsSortedByTimestamp(true)
        );

        this.initializeActionBar();
        this.initializeCollectionView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.clear_button:
                Store.getInstance().removeAllIpAddressInformation();
                ipSnapshots.clear();
                collectionViewAdapter.notifyDataSetChanged();
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
        this.collectionView = this.findViewById(
                R.id.recycler_view
        );
        this.collectionView.setHasFixedSize(true);

        this.collectionViewLayoutManager = new LinearLayoutManager(
                this
        );
        this.collectionView.setLayoutManager(
                this.collectionViewLayoutManager
        );

        this.collectionViewAdapter = new HistoryAdapter(
                this.ipSnapshots
        );
        this.collectionView.setAdapter(
                this.collectionViewAdapter
        );

        RecyclerView.ItemDecoration decoration = new EqualSpacingItemDecoration(
                getResources().getDimensionPixelSize(
                        R.dimen.item_spacing
                )
        );
        this.collectionView.addItemDecoration(decoration);
    }
}
