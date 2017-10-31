package org.pocketx.knell.domain;

public final class Birthday {

    public final int year;
    public final int month;
    public final int day;

    private Birthday(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        }

    public static Birthday create(int year, int month, int day) {
        return new Birthday(year, month, day);
    }
}
