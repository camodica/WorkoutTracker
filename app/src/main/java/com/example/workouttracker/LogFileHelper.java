package com.example.workouttracker;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileHelper {

    public static WorkoutLog loadLog(String identifier, Context context) {
        Gson gson = new Gson();
        WorkoutLog result = null;

        try {
            System.out.println(makeFileName(identifier, context));
            result = gson.fromJson(new FileReader(makeFileName(identifier, context)), WorkoutLog.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void saveLog(WorkoutLog log, String identifier, Context context) {
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter(makeFileName(identifier, context));
            gson.toJson(log, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String makeFileName(String identifier, Context context) {
        return context.getFilesDir() + "/" + identifier + ".json";
    }
}
