package com.example.sayem.remindme;

/**
 * Created by Sayem on 1/20/2016.
 */
public class FromTimeClass {

    int from_hourOfDay;
    int from_minute;

    public FromTimeClass(int from_hourOfDay, int from_minute) {
        this.from_hourOfDay = from_hourOfDay;
        this.from_minute = from_minute;
    }

    public int getFrom_hourOfDay() {
        return from_hourOfDay;
    }

    public void setFrom_hourOfDay(int from_hourOfDay) {
        this.from_hourOfDay = from_hourOfDay;
    }

    public int getFrom_minute() {
        return from_minute;
    }

    public void setFrom_minute(int from_minute) {
        this.from_minute = from_minute;
    }
}
