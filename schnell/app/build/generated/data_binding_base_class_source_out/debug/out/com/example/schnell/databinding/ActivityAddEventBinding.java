// Generated by view binder compiler. Do not edit!
package com.example.schnell.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.schnell.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddEventBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnCancel;

  @NonNull
  public final Button btnSaveEvent;

  @NonNull
  public final DatePicker datePicker;

  @NonNull
  public final EditText etEventDescription;

  @NonNull
  public final EditText etEventName;

  @NonNull
  public final RelativeLayout relativeLayout;

  private ActivityAddEventBinding(@NonNull RelativeLayout rootView, @NonNull Button btnCancel,
      @NonNull Button btnSaveEvent, @NonNull DatePicker datePicker,
      @NonNull EditText etEventDescription, @NonNull EditText etEventName,
      @NonNull RelativeLayout relativeLayout) {
    this.rootView = rootView;
    this.btnCancel = btnCancel;
    this.btnSaveEvent = btnSaveEvent;
    this.datePicker = datePicker;
    this.etEventDescription = etEventDescription;
    this.etEventName = etEventName;
    this.relativeLayout = relativeLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddEventBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddEventBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_event, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddEventBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCancel;
      Button btnCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnCancel == null) {
        break missingId;
      }

      id = R.id.btnSaveEvent;
      Button btnSaveEvent = ViewBindings.findChildViewById(rootView, id);
      if (btnSaveEvent == null) {
        break missingId;
      }

      id = R.id.datePicker;
      DatePicker datePicker = ViewBindings.findChildViewById(rootView, id);
      if (datePicker == null) {
        break missingId;
      }

      id = R.id.etEventDescription;
      EditText etEventDescription = ViewBindings.findChildViewById(rootView, id);
      if (etEventDescription == null) {
        break missingId;
      }

      id = R.id.etEventName;
      EditText etEventName = ViewBindings.findChildViewById(rootView, id);
      if (etEventName == null) {
        break missingId;
      }

      RelativeLayout relativeLayout = (RelativeLayout) rootView;

      return new ActivityAddEventBinding((RelativeLayout) rootView, btnCancel, btnSaveEvent,
          datePicker, etEventDescription, etEventName, relativeLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}