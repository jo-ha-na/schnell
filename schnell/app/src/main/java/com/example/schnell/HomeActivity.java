package com.example.schnell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find ImageView elements
        ImageView notesImage = findViewById(R.id.NotesImage);
        ImageView calendarImage = findViewById(R.id.calendarImage);
        ImageView eventImage = findViewById(R.id.eventImage);
        ImageView beautyImage = findViewById(R.id.profileImage);
        ImageView landscapeImage = findViewById(R.id.landscape);

        final String userName = getIntent().getStringExtra("userName");

        // Set click listeners for each ImageView
        notesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NoteActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);

            }
        });

        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);

            }
        });

        eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for eventImage
                Intent intent = new Intent(HomeActivity.this, EventListActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);

            }
        });

        beautyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for profileImage
                Intent intent = new Intent(HomeActivity.this, userlist.class);
                intent.putExtra("userName", userName);
                startActivity(intent);

            }
        });

        landscapeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for landscapeImage
                startActivity(new Intent(HomeActivity.this, AddNoteActivity.class));
            }
        });
    }
}
