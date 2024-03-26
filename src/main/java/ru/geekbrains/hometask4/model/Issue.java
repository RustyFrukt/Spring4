package ru.geekbrains.hometask4.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи и сдачи книги читателем
 */
public class Issue {

    public static long sequence = 1L;

    private final long id;
    private final long bookId;
    private final long readerId;

    // Дата выдачи
    private final LocalDateTime issued_at;
    // Дата возврата
    private LocalDateTime returned_at;

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
        this.returned_at = null;
    }

    public Issue() {
        this.id = sequence++;
        this.bookId = Long.parseLong(null);
        this.readerId = Long.parseLong(null);
        this.issued_at = LocalDateTime.now();
    }

    @JsonCreator
    public Issue(long id, long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
        this.returned_at = null;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public LocalDateTime getIssued_at() {
        return issued_at;
    }

    public LocalDateTime getReturned_at() {
        return returned_at;
    }

    public void setReturned_at(LocalDateTime returned_at) {
        this.returned_at = returned_at;
    }

}
