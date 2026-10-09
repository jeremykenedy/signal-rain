package com.jeremykenedy.signalrain;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public final class SettingsActivity extends Activity {
  private SharedPreferences preferences;

  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);
    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    ScrollView scroll = new ScrollView(this);
    LinearLayout content = new LinearLayout(this);
    content.setOrientation(LinearLayout.VERTICAL);
    content.setPadding(56, 32, 56, 32);
    scroll.addView(content);
    TextView title = new TextView(this);
    title.setText("Signal Rain settings");
    title.setTextSize(28);
    title.setPadding(0, 0, 0, 20);
    content.addView(title);
    Button preview = new Button(this);
    preview.setText("Preview animation");
    preview.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            startActivity(new Intent(SettingsActivity.this, PreviewActivity.class));
          }
        });
    content.addView(preview);
    addChoice(
        content,
        "Stream density",
        "density",
        new String[] {"sparse", "balanced", "dense", "packed", "random"},
        new String[] {"Sparse", "Balanced", "Dense", "Packed", "Random"},
        "balanced");
    addChoice(
        content,
        "Fall speed",
        "motion",
        new String[] {"slow", "normal", "fast", "random"},
        new String[] {"Slow", "Normal", "Fast", "Random"},
        "normal");
    addChoice(
        content,
        "Stream colors",
        "palette",
        new String[] {"green", "blue", "amber", "random"},
        new String[] {"Green", "Cool blue", "Warm amber", "Random"},
        "green");
    addChoice(
        content,
        "Glow strength",
        "brightness",
        new String[] {"dim", "standard", "bright", "random"},
        new String[] {"Dim", "Standard", "Bright", "Random"},
        "standard");
    addChoice(
        content,
        "Glyphs",
        "glyphs",
        new String[] {"bars", "fragments", "circuits", "random"},
        new String[] {"Bars", "Fragments", "Circuits", "Random"},
        "fragments");
    CheckBox randomize = new CheckBox(this);
    randomize.setText("Randomize all settings each time the screensaver starts");
    randomize.setTextSize(18);
    randomize.setChecked(preferences.getBoolean("randomize_all", false));
    randomize.setOnCheckedChangeListener(
        new android.widget.CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(android.widget.CompoundButton button, boolean checked) {
            preferences.edit().putBoolean("randomize_all", checked).apply();
          }
        });
    content.addView(randomize);
    setContentView(scroll);
  }

  private void addChoice(
      LinearLayout content,
      String title,
      String key,
      String[] values,
      String[] labels,
      String fallback) {
    TextView label = new TextView(this);
    label.setText(title);
    label.setTextSize(18);
    label.setPadding(0, 16, 0, 4);
    content.addView(label);
    Spinner spinner = new Spinner(this);
    spinner.setFocusable(true);
    spinner.setFocusableInTouchMode(true);
    spinner.setGravity(Gravity.CENTER_VERTICAL);
    ArrayAdapter<String> adapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    String selected = preferences.getString(key, fallback);
    int selection = 0;
    for (int i = 0; i < values.length; i++) if (values[i].equals(selected)) selection = i;
    spinner.setSelection(selection);
    spinner.setOnItemSelectedListener(
        new android.widget.AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(
              android.widget.AdapterView<?> parent, View view, int position, long id) {
            preferences.edit().putString(key, values[position]).apply();
          }

          @Override
          public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    content.addView(spinner);
  }
}
