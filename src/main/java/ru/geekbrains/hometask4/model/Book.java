package ru.geekbrains.hometask4.model;


import com.fasterxml.jackson.annotation.JsonCreator;

public class Book {
    public static long sequence = 1L;
    private final long id;
    private final String name;

    public Book() {
        this.id = sequence++;
        name = null;
    }

    public Book(String name) {
        this.id = sequence++;
        this.name = name;
    }

    @JsonCreator
    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.id + ".   " + '"' + this.name + '"';
    }

}
