package com.example.schnell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.EventViewHolder> {

    private List<EventModel> eventList;

    public UpcomingEventsAdapter(List<EventModel> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void updateEvents(List<EventModel> upcomingEvents) {
        eventList.clear();
        eventList.addAll(upcomingEvents);
        notifyDataSetChanged();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewEventName;
        private TextView textViewEventDescription;
        private TextView textViewEventDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewEventName = itemView.findViewById(R.id.textViewEventName);
            textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
            textViewEventDate = itemView.findViewById(R.id.textViewEventDate);
        }

        public void bind(EventModel event) {
            textViewEventName.setText(event.getName());
            textViewEventDescription.setText(event.getDescription());
            textViewEventDate.setText(event.getFormattedDate()); // Utilisez la méthode de formatage de date appropriée
        }
    }
}
