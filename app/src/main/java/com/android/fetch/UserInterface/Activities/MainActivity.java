package com.android.fetch.UserInterface.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.fetch.Models.Results;
import com.android.fetch.R;
import com.android.fetch.UserInterface.RecyclerViews.ResultsAdapter;
import com.android.fetch.UserInterface.ViewModels.ResultsViewModel;

public class MainActivity extends AppCompatActivity {

    private ResultsViewModel resultsViewModel;
    private RecyclerView recyclerView;
    private ResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultsViewModel = ViewModelProviders.of(this).get(ResultsViewModel.class);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        resultsViewModel.getResultsData().observe(this, results -> {
            adapter = new ResultsAdapter(results);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        resultsViewModel.unSubscribe();
        resultsViewModel.getResultsData().removeObservers(this);
    }
}