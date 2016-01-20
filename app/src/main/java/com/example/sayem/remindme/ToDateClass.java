package com.example.sayem.remindme;

/**
 * Created by Sayem on 1/20/2016.
 */
public class ToDateClass {

    int to_dayOfMonth;
    int to_monthOfYear;
    int to_year;

    public ToDateClass(int to_dayOfMonth, int to_monthOfYear, int to_year) {
        this.to_dayOfMonth = to_dayOfMonth;
        this.to_monthOfYear = to_monthOfYear;
        this.to_year = to_year;
    }

    public int getTo_dayOfMonth() {
        return to_dayOfMonth;
    }

    public void setTo_dayOfMonth(int to_dayOfMonth) {
        this.to_dayOfMonth = to_dayOfMonth;
    }

    public int getTo_monthOfYear() {
        return to_monthOfYear;
    }

    public void setTo_monthOfYear(int to_monthOfYear) {
        this.to_monthOfYear = to_monthOfYear;
    }

    public int getTo_year() {
        return to_year;
    }

    public void setTo_year(int to_year) {
        this.to_year = to_year;
    }
}
