// Generated by view binder compiler. Do not edit!
package com.example.schnell.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.schnell.R;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTaskBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppBarMainBinding appBarMain;

  @NonNull
  public final NavigationView navView;

  @NonNull
  public final RelativeLayout relativeLayout;

  private ActivityTaskBinding(@NonNull RelativeLayout rootView,
      @NonNull AppBarMainBinding appBarMain, @NonNull NavigationView navView,
      @NonNull RelativeLayout relativeLayout) {
    this.rootView = rootView;
    this.appBarMain = appBarMain;
    this.navView = navView;
    this.relativeLayout = relativeLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTaskBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTaskBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_task, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTaskBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_bar_main;
      View appBarMain = ViewBindings.findChildViewById(rootView, id);
      if (appBarMain == null) {
        break missingId;
      }
      AppBarMainBinding binding_appBarMain = AppBarMainBinding.bind(appBarMain);

      id = R.id.nav_view;
      NavigationView navView = ViewBindings.findChildViewById(rootView, id);
      if (navView == null) {
        break missingId;
      }

      RelativeLayout relativeLayout = (RelativeLayout) rootView;

      return new ActivityTaskBinding((RelativeLayout) rootView, binding_appBarMain, navView,
          relativeLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
