package com.example.schnell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class locationpage extends AppCompatActivity {

    Button btn_pos;
    EditText saisie_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationpage);

        btn_pos = findViewById(R.id.btn_pos);
        saisie_position = findViewById(R.id.saisie_position);

        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = saisie_position.getText().toString();

                if (!pos.isEmpty()) {
                    Intent intent = new Intent(locationpage.this, HomeActivity.class);
                    intent.putExtra("lieu", pos);
                    startActivity(intent);
                } else {
                    Toast.makeText(locationpage.this, "Veuillez saisir une position.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
