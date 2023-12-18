package com.example.schnell_project.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schnell_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TrelloActivity extends AppCompatActivity {

    RecyclerView recyclerViewTodo;
    RecyclerView recyclerViewDoing;
    RecyclerView recyclerViewDone;
    TaskAdapter mAdapterTodo;
    TaskAdapter mAdapterDoing;
    TaskAdapter mAdapterDone;

    private List<Task> taskListTodo = new ArrayList<>();
    private List<Task> taskListDoing = new ArrayList<>();
    private List<Task> taskListDone = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trello);



        recyclerViewTodo = findViewById(R.id.todoRecyclerView);
        mAdapterTodo = new TaskAdapter();
        recyclerViewTodo.setAdapter(mAdapterTodo);

        recyclerViewDoing = findViewById(R.id.inProgressRecyclerView);
        mAdapterDoing = new TaskAdapter();
        recyclerViewDoing.setAdapter(mAdapterDoing);

        recyclerViewDone = findViewById(R.id.doneRecyclerView);
        mAdapterDone = new TaskAdapter();
        recyclerViewDone.setAdapter(mAdapterDone);

        LinearLayoutManager layoutManagerHorizontalTodo = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerHorizontalDoing = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerHorizontalDone = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewTodo.setLayoutManager(layoutManagerHorizontalTodo);
        recyclerViewDoing.setLayoutManager(layoutManagerHorizontalDoing);
        recyclerViewDone.setLayoutManager(layoutManagerHorizontalDone);


        // Set up ItemTouchHelper for each RecyclerView
        //TODO
        ItemTouchHelper.Callback callbackTodo = new ItemMoveCallback(mAdapterTodo);
        ItemTouchHelper touchHelperTodo = new ItemTouchHelper(callbackTodo);
        touchHelperTodo.attachToRecyclerView(recyclerViewTodo);
        //DOING
        ItemTouchHelper.Callback callbackDoing = new ItemMoveCallback(mAdapterDoing);
        ItemTouchHelper touchHelperDoing = new ItemTouchHelper(callbackDoing);
        touchHelperDoing.attachToRecyclerView(recyclerViewDoing);

        ItemTouchHelper.Callback callbackDone = new ItemMoveCallback(mAdapterDone);
        ItemTouchHelper touchHelperDone = new ItemTouchHelper(callbackDone);
        touchHelperDone.attachToRecyclerView(recyclerViewDone);

        /*taskListTodo = new ArrayList<>();
        taskListDoing = new ArrayList<>();
        taskListDone = new ArrayList<>();*/


        FloatingActionButton buttonPopUpDialogAddTask = findViewById(R.id.buttonPopUpDialogAddTask);
        buttonPopUpDialogAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.add_card_activity, null);
        builder.setView(dialogView);

        final EditText editTextTaskTitle = dialogView.findViewById(R.id.editTextTaskTitle);
        final EditText editTextTaskDescription = dialogView.findViewById(R.id.editTextTaskDescription);
        Button buttonAddTask = dialogView.findViewById(R.id.buttonAddTask);

        AlertDialog dialog = builder.create();

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the task title from the EditText
                String taskTitle = editTextTaskTitle.getText().toString();
                String taskDescription = editTextTaskDescription.getText().toString();

                // Add the new task to your list
                Task newTask = new Task(taskTitle, taskDescription,"To Do"); // You can set the initial status
                taskListTodo.add(newTask);

                // Update the RecyclerView
                mAdapterTodo.submitList(taskListTodo);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}