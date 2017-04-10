package com.wealthfront.magellan;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

public abstract class SingleActivity extends Activity {

  private static Navigator navigator;

  protected abstract Navigator createNavigator();

  public static Navigator getNavigator() {
    return navigator;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (navigator == null) {
      navigator = createNavigator();
    }
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    navigator.onCreate(this, savedInstanceState);
    super.onPostCreate(savedInstanceState);
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    navigator.onSaveInstanceState(outState);
  }

  @Override
  protected void onResume() {
    super.onResume();
    navigator.onResume(this);
  }

  @Override
  protected void onPause() {
    navigator.onPause(this);
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    navigator.onDestroy(this);
    super.onDestroy();
  }

  @Override
  public void onBackPressed() {
    if (!navigator.handleBack()) {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    navigator.onCreateOptionsMenu(menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    navigator.onPrepareOptionsMenu(menu);
    return super.onPrepareOptionsMenu(menu);
  }

}
