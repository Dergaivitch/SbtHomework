package ru.sbt;

import java.util.Date;

public class Lesson {
    private final long id;
    private final String date;

    public Lesson(long id, String date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
