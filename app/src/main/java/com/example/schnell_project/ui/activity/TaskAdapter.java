package com.example.schnell_project.ui.activity;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schnell_project.R;

import java.util.ArrayList;
import java.util.List;



public class TaskAdapter extends ListAdapter<Task, TaskAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Task> taskList;

    private static final int TODO_COLUMN_START = 0;
    private static final int TODO_COLUMN_END = 4; // Adjust the end position based on your layout

    private static final int DOING_COLUMN_START = 5;
    private static final int DOING_COLUMN_END = 8;

    private static final int DONE_COLUMN_START = 9;
    private static final int DONE_COLUMN_END = 12;

    public static final DiffUtil.ItemCallback<Task> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Task>() {
                @Override
                public boolean areItemsTheSame(Task oldItem, Task newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(Task oldItem, Task newItem) {
                    return (oldItem.getTitle() == newItem.getTitle() && oldItem.getStatus() == newItem.getStatus());
                }
            };
    public TaskAdapter() {
        super(DIFF_CALLBACK);
        taskList = new ArrayList<>();
    }

    private String getColumnStatus(int position) {
        String status;

        if (position == Column.TODO) {
            status = "To Do";
        } else if (position == Column.DOING) {
            status = "Doing";
        } else if (position == Column.DONE) {
            status = "Done";
        } else {
            status = "Unknown";
            // You can handle other positions as needed
        }


        return status;
    }



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;


        public ViewHolder(View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.textView);
            }
        public TextView getTextView() {
            return textView;
        }
        }
    public void addMoreContacts(List<Task> newTask) {
        taskList.addAll(newTask);
        submitList(taskList); // DiffUtil takes care of the check
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Task task = getItem(position);
            Task task = getItem(position);
            holder.getTextView().setText(task.getTitle());
    }

    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    public void onItemMoved(int fromPosition, int toPosition) {
        Task movedTask = taskList.get(fromPosition);
        taskList.remove(fromPosition);
        taskList.add(toPosition, movedTask);
        notifyItemMoved(fromPosition, toPosition);

        // Update the task status when moved to a new column
        String newStatus = getColumnStatus(getColumn(toPosition)); // Implement getColumnStatus based on your logic
        movedTask.updateStatus(newStatus);
    }
    @Override
    public void onItemSelected(ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.GRAY);

    }

    @Override
    public void onItemClear(ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.WHITE);

    }

    private int getColumn(int position) {
        if (position >= TODO_COLUMN_START && position <= TODO_COLUMN_END) {
            return Column.TODO;
        } else if (position >= DOING_COLUMN_START && position <= DOING_COLUMN_END) {
            return Column.DOING;
        } else if (position >= DONE_COLUMN_START && position <= DONE_COLUMN_END) {
            return Column.DONE;
        } else {
            return Column.UNKNOWN; // or handle the case if the position doesn't fall into any column
        }
    }



}
