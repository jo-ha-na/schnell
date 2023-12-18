package com.example.schnell;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<com.example.schnell.EventModel> eventList;
    private Context context;
    private Set<com.example.schnell.EventModel> selectedEvents = new HashSet<>();
    private OnItemClickListener onItemClickListener;

    public EventAdapter(List<com.example.schnell.EventModel> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(com.example.schnell.EventModel event);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        com.example.schnell.EventModel event = eventList.get(position);
        holder.bind(event);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(event);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            toggleSelection(event);
            return true;
        });

        if (selectedEvents.contains(event)) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public List<com.example.schnell.EventModel> getSelectedEvents() {
        return new ArrayList<>(selectedEvents);
    }

    public void clearSelection() {
        selectedEvents.clear();
        notifyDataSetChanged();
    }

    private void toggleSelection(com.example.schnell.EventModel event) {
        if (selectedEvents.contains(event)) {
            selectedEvents.remove(event);
        } else {
            selectedEvents.add(event);
        }
        notifyItemChanged(eventList.indexOf(event));
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewEventName;
        private TextView textViewEventDescription;
        private TextView textViewEventDate;
        private Button btnModifyDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewEventName = itemView.findViewById(R.id.textViewEventName);
            textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
            textViewEventDate = itemView.findViewById(R.id.textViewEventDate);

        }

        public void bind(com.example.schnell.EventModel event) {
            textViewEventName.setText(event.getName());
            textViewEventDescription.setText(event.getDescription());
            textViewEventDate.setText(String.format("%02d/%02d/%d", event.getDayOfMonth(), event.getMonth(), event.getYear()));

            btnModifyDate.setOnClickListener(v -> {
                openModifyDateActivity(event.getId());
            });
        }

        private void openModifyDateActivity(Object id) {
            Intent intent = new Intent(context, ModifyDateActivity.class);
            intent.putExtra("eventId", id.toString());
            context.startActivity(intent);
        }
    }
}
