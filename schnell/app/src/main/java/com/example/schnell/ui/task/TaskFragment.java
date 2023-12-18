package com.example.schnell.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schnell.R;
import com.example.schnell.databinding.ActivityTaskBinding;

public class TaskFragment extends Fragment {

    private ActivityTaskBinding binding;
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    private int xDelta;
    private int yDelta;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // init ui views
        imageView = root.findViewById(R.id.imageView);
        relativeLayout = root.findViewById(R.id.relative_layout);

        // setup layout params
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(800, 600);
        imageView.setLayoutParams(layoutParams);

        // setup touch listener
        imageView.setOnTouchListener(new CustomTouchListener());

        return root;
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
                    Toast.makeText(requireContext(), "Image is on a new location!", Toast.LENGTH_SHORT).show();
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
