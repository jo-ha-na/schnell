package com.example.schnell;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schnell.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private String firstName, lastName, age, userName;
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private static final String USERS_KEY = "Users"; // Database reference key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = binding.firstName.getText().toString();
                lastName = binding.lastName.getText().toString();
                age = binding.age.getText().toString();
                userName = binding.userName.getText().toString();


                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()) {
                    Users users = new Users(firstName, lastName, age, userName);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference(USERS_KEY); // Use the constant
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                binding.firstName.setText("");
                                binding.lastName.setText("");
                                binding.age.setText("");
                                binding.userName.setText("");
                                Toast.makeText(MainActivity2.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity2.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                Intent intent = new Intent(MainActivity2.this, HomeActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
                finish();
            }
        });
    }
}
