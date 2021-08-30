package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.common.collect.Sets;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {
    private String identifier;
    private WorkoutLog workoutLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Intent i = getIntent();

        int year = i.getIntExtra("year", -1);
        int month = i.getIntExtra("month", -1);
        int dayOfMonth = i.getIntExtra("dayOfMonth", -1);

        if (year == -1  || month == -1 || dayOfMonth == -1) {
            throw new IllegalArgumentException("Invalid date parameter: " + year + " " + month + " " + dayOfMonth);
        }

        this.identifier = year + "-" + month + "-" + dayOfMonth;
        setTitle((month + 1) + "/" + dayOfMonth + "/" + year);

        this.workoutLog = LogFileHelper.loadLog(this.identifier, getApplicationContext());

        if (this.workoutLog == null) {
            this.workoutLog = new WorkoutLog("", "");
        }

        EditText workoutTitleEditText = findViewById(R.id.workoutTitle);
        EditText workoutActivitiesEditText = findViewById(R.id.workoutActivities);

        if (!this.workoutLog.getWorkoutTitle().isEmpty()) {
            workoutTitleEditText.setText(this.workoutLog.getWorkoutTitle());
        }
        if (!this.workoutLog.getWorkoutActivities().isEmpty()) {
            workoutActivitiesEditText.setText(this.workoutLog.getWorkoutActivities());
        }

        workoutTitleEditText.addTextChangedListener(new TitleTextWatcher());
        workoutActivitiesEditText.addTextChangedListener(new ActivitiesTextWatcher());

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new SaveClickListener());

        KeyboardVisibilityEvent.setEventListener(this, new ClearFocusListener(Sets.newHashSet(workoutTitleEditText, workoutActivitiesEditText)));

        System.out.println(this.workoutLog);
    }

    private class TitleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            workoutLog.setWorkoutTitle(editable.toString());
        }
    }

    private class ActivitiesTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            workoutLog.setWorkoutActivities(editable.toString());
        }
    }

    private class SaveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            saveWorkoutLog();
        }
    }

    private static class ClearFocusListener implements KeyboardVisibilityEventListener {
        private final Set<EditText> viewsToUnfocus;

        public ClearFocusListener(Set<EditText> viewsToUnfocus) {
            this.viewsToUnfocus = viewsToUnfocus;
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            if (!visible) {
                viewsToUnfocus.forEach(EditText::clearFocus);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: Save the workout information
        this.saveWorkoutLog();

        System.out.println("YOU PRESSED BACK FROM THE LOG ACTIVITY");
        finish();
    }

    private void saveWorkoutLog() {
        LogFileHelper.saveLog(this.workoutLog, this.identifier, getApplicationContext());
    }
}
