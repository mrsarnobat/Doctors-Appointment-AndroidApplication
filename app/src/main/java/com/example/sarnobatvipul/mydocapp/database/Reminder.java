package com.example.sarnobatvipul.mydocapp.database;

/**
 * Created by mr. A on 28-02-2019.
 */

public class Reminder
{
    public String id,title,dose,timing,days,duration;

    public Reminder()
    {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Reminder(String id, String title, String dose, String timing, String days, String duration) {
        this.id = id;
        this.title=title;
        this.dose=dose;
        this.timing=timing;

        this.days=days;
        this.duration=duration;
    }

}
