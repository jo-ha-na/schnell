// Generated by view binder compiler. Do not edit!
package com.example.schnell.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.schnell.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEventListBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnAddEvent1;

  @NonNull
  public final Button btnDeleteSelected;

  @NonNull
  public final RecyclerView recyclerViewAllEvents;

  @NonNull
  public final TextView textView2;

  private ActivityEventListBinding(@NonNull RelativeLayout rootView, @NonNull Button btnAddEvent1,
      @NonNull Button btnDeleteSelected, @NonNull RecyclerView recyclerViewAllEvents,
      @NonNull TextView textView2) {
    this.rootView = rootView;
    this.btnAddEvent1 = btnAddEvent1;
    this.btnDeleteSelected = btnDeleteSelected;
    this.recyclerViewAllEvents = recyclerViewAllEvents;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEventListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEventListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_event_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEventListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddEvent1;
      Button btnAddEvent1 = ViewBindings.findChildViewById(rootView, id);
      if (btnAddEvent1 == null) {
        break missingId;
      }

      id = R.id.btnDeleteSelected;
      Button btnDeleteSelected = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteSelected == null) {
        break missingId;
      }

      id = R.id.recyclerViewAllEvents;
      RecyclerView recyclerViewAllEvents = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewAllEvents == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new ActivityEventListBinding((RelativeLayout) rootView, btnAddEvent1,
          btnDeleteSelected, recyclerViewAllEvents, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
