package com.example.schnell_project.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schnell_project.R;

import java.util.ArrayList;
import java.util.List;

public class TrelloActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter mAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trello);

        recyclerView = findViewById(R.id.todoRecyclerView);
        mAdapter = new TaskAdapter(taskList); // Replace taskList with your actual list of tasks
        recyclerView.setAdapter(mAdapter);

        populateRecyclerView();
    }

    private void populateRecyclerView() {

        mAdapter = new TaskAdapter(taskList);

        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(mAdapter);
    }

}