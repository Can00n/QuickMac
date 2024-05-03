package edu.utsa.cs3443.quickmac.model;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * The Logbook class represents all of the days logged in the app
 *
 * @author Canaan Eggers zko777
 */
public class Logbook {
    private ArrayList<Day> days = new ArrayList<>();
    private static final String FILENAME = "logbook.csv";

    /**
     * Constructor for Logbook objects
     * @param days, a list of all the days logged (ArrayList<Day>)
     */
    public Logbook(ArrayList<Day> days) {
        this.days = days;
    }

    /**
     * Constructor for Logbook objects without days ArrayLists
     */
    public Logbook() {
        this.days = new ArrayList<Day>(days);
    } //is there a way for users to login and save their logbooks to them??

    /**
     * setter for days
     * @param days, a list of all the days logged (ArrayList<Day>)
     */
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    /**
     * getter for days
     * @return ArrayList<Day>, a list of all the days logged
     */
    public ArrayList<Day> getDays() {
        return days;
    }

    /**
     * adds a new day to days
     * @param newDay, the new Day object to be added (Day)
     * @param context, the context of the app
     */
    public void addDay(Day newDay, Context context) {
        if(days != null)
            days.add(newDay);
        saveDaysToInternalStorage(context);
    }

    /**
     * removes a day from days
     * @param outDay, the day to be removed (Day)
     * @param context, the context of the app
     */
    public void removeDay(Day outDay, Context context) {
        if(days != null)
            days.remove(outDay);
        saveDaysToInternalStorage(context);
    }

    /**
     * generates a String list of the days
     * @param days,  a list of all the days logged (ArrayList<Day>)
     * @return String, a list of the days
     */
    public String listDays(ArrayList<Day> days) {
        StringBuilder dayList = new StringBuilder();
        for(Day day : days) {
            dayList.append(day);
        }
        return dayList.toString();
    }

    /**
     * saves the current state of the logbook to internal storage to be written to the csv file
     * @param context, the context of the app
     */
    private void saveDaysToInternalStorage(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            for (Day day : days) {
                writer.write(day.toCSVString()); // Assuming Day has a method to convert to CSV string
                writer.write("\n");
            }
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * generates a String representation of the logbook
     * @return
     */
    public String toString() {
        return String.format("Logbook:\n%s", listDays(days));
    }
}
