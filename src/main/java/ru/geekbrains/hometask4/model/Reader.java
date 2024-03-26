package ru.geekbrains.hometask4.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Reader {

    public static long sequence = 1L;

    private final long id;
    private String name;

    public Reader() {
        this.id = sequence++;
    }

    public Reader(String name) {
        this.id = sequence++;
        this.name = name;
    }

    @JsonCreator
    public Reader(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id + ".   " + '"' + this.name + '"';
    }

}
