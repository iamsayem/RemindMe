package com.example.sayem.remindme;

/**
 * Created by Sayem on 1/20/2016.
 */
public class ToTimeClass {

    int to_hourOfDay;
    int to_minute;

    public ToTimeClass(int to_hourOfDay, int to_minute) {
        this.to_hourOfDay = to_hourOfDay;
        this.to_minute = to_minute;
    }

    public int getTo_hourOfDay() {
        return to_hourOfDay;
    }

    public void setTo_hourOfDay(int to_hourOfDay) {
        this.to_hourOfDay = to_hourOfDay;
    }

    public int getTo_minute() {
        return to_minute;
    }

    public void setTo_minute(int to_minute) {
        this.to_minute = to_minute;
    }
}
