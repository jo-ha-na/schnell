package com.example.schnell;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<EventModel> eventList;
    private Context context;

    public EventAdapter(List<EventModel> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
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

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewEventName;
        private TextView textViewEventDescription;
        private TextView textViewEventDate;
        private Button btnModifyDate;
        private Button btnDeleteSelected;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewEventName = itemView.findViewById(R.id.textViewEventName);
            textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
            textViewEventDate = itemView.findViewById(R.id.textViewEventDate);
            btnModifyDate = itemView.findViewById(R.id.btnModifyDate);
            btnDeleteSelected = itemView.findViewById(R.id.btnDeleteSelected);
        }

        public void bind(final EventModel event) {
            textViewEventName.setText(event.getName());
            textViewEventDescription.setText(event.getDescription());
            textViewEventDate.setText(event.getFormattedDate()); // Assurez-vous que la date est formatée correctement

            btnModifyDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ModifyDateActivity.class);
                    intent.putExtra("eventId", event.getEventId());
                    intent.putExtra("year", event.getYear());
                    intent.putExtra("month", event.getMonth() - 1);
                    intent.putExtra("dayOfMonth", event.getDayOfMonth());
                    context.startActivity(intent);
                }
            });
            btnDeleteSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Obtenez l'événement correspondant à cette position
                        EventModel eventToDelete = eventList.get(position);

                        // Implémentez ici la logique pour supprimer l'événement de la base de données Firebase
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("events");
                        databaseReference.child(eventToDelete.getEventId()).removeValue(); // Supprimez l'événement de la base de données

                        // Supprimez également l'événement de l'adapter
                        eventList.remove(position);
                        notifyItemRemoved(position);
                    }
                }
            });

        }
    }
}
