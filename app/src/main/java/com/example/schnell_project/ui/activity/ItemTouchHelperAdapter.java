package com.example.schnell_project.ui.activity;

public interface ItemTouchHelperAdapter {
    void onItemMoved(int fromPosition, int toPosition);
    void onItemSelected(TaskAdapter.ViewHolder ViewHolder);
    void onItemClear(TaskAdapter.ViewHolder ViewHolder);
}
