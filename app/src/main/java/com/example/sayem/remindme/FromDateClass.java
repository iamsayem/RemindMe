package com.example.sayem.remindme;

/**
 * Created by Sayem on 1/20/2016.
 */
public class FromDateClass {

    int from_dayOfMonth;
    int from_monthOfYear;
    int from_year;

    public FromDateClass(int from_dayOfMonth, int from_monthOfYear, int from_year) {
        this.from_dayOfMonth = from_dayOfMonth;
        this.from_monthOfYear = from_monthOfYear;
        this.from_year = from_year;
    }

    public int getFrom_dayOfMonth() {
        return from_dayOfMonth;
    }

    public void setFrom_dayOfMonth(int from_dayOfMonth) {
        this.from_dayOfMonth = from_dayOfMonth;
    }

    public int getFrom_monthOfYear() {
        return from_monthOfYear;
    }

    public void setFrom_monthOfYear(int from_monthOfYear) {
        this.from_monthOfYear = from_monthOfYear;
    }

    public int getFrom_year() {
        return from_year;
    }

    public void setFrom_year(int from_year) {
        this.from_year = from_year;
    }
}
