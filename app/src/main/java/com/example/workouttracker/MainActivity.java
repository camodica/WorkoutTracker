package com.example.workouttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setDate(System.currentTimeMillis());
        calendar.setOnDateChangeListener(new SelectedDateListener());

        setTitle("Workout Tracker");
    }

    // This is what happens when you click on a calendar cell
    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Refresh saved workout information for the calendar view
    }

    /**
     * The listener to open a new activity when the
     */
    public class SelectedDateListener implements CalendarView.OnDateChangeListener {

        @Override
        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
            Intent myIntent = new Intent(MainActivity.this, LogActivity.class);

            myIntent.putExtra("year", year);
            myIntent.putExtra("month", month);
            myIntent.putExtra("dayOfMonth", dayOfMonth);

            MainActivity.this.startActivity(myIntent);
        }
    }
}