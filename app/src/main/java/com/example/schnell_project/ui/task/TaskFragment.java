package com.example.schnell_project.ui.task;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schnell_project.R;
import com.example.schnell_project.databinding.ActivityTaskBinding;
import com.example.schnell_project.databinding.FragmentSlideshowBinding;
import com.example.schnell_project.ui.slideshow.SlideshowViewModel;

public class TaskFragment extends AppCompatActivity {
    private ActivityTaskBinding binding;
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    private int xDelta;
    private int yDelta;


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
        imageView.setOnTouchListener(new TaskFragment.CustomTouchListener());

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ;

        binding = ActivityTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
    //declare ui views
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
                    Toast.makeText(TaskFragment.this, "Image is on new Location!", Toast.LENGTH_SHORT).show();
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


