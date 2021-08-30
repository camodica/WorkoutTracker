package com.example.workouttracker;

import androidx.annotation.NonNull;

public class WorkoutLog {
    private String workoutTitle;
    private String workoutActivities;

    public WorkoutLog(String workoutTitle, String workoutActivities) {
        this.workoutTitle = workoutTitle;
        this.workoutActivities = workoutActivities;
    }

    public void setWorkoutTitle(@NonNull String newWorkoutTitle) {
        this.workoutTitle = newWorkoutTitle;
    }

    public String getWorkoutTitle() {
        return this.workoutTitle;
    }

    public void setWorkoutActivities(@NonNull String newWorkoutActivities) {
        this.workoutActivities = newWorkoutActivities;
    }

    public String getWorkoutActivities() {
        return this.workoutActivities;
    }

    @NonNull
    @Override
    public String toString() {
        return "{ workoutTitle: " + workoutTitle + ", workoutActivities: " + workoutActivities + " }";
    }
}
