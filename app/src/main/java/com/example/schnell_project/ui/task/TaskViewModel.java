package com.example.schnell_project.ui.task;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.example.schnell_project.R;

public class TaskViewModel extends AppCompatActivity  {
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    private int xDelta;
    private int yDelta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //init ui views
        imageView = findViewById(R.id.imageView);
        relativeLayout = findViewById(R.id.relative_layout);

        //setup layout params
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(800, 600);
        imageView.setLayoutParams(layoutParams);
        //setup touch listener
        imageView.setOnTouchListener(new TaskViewModel.CustomTouchListener());

    }

    private class CustomTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

                    xDelta = X - lParams.leftMargin;
                    yDelta = Y - lParams.topMargin;

                    break;

                case MotionEvent.ACTION_UP:
                    Toast.makeText(TaskViewModel.this, "Image is on new Location!", Toast.LENGTH_SHORT).show();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.leftMargin = X - xDelta;
                    layoutParams.topMargin = Y - yDelta;
                    layoutParams.rightMargin = 0;
                    layoutParams.bottomMargin = 0;
                    v.setLayoutParams(layoutParams);
                    break;

            }
            relativeLayout.invalidate();
            return true;
        }
    }
}
