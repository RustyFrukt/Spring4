package ru.geekbrains.hometask4.controller;

public class IssueRequest {

    // Идентификатор читателя
    private long readerId;

    // Идентификатор книги
    private long bookId;

    public IssueRequest() {
    }

    public IssueRequest(long readerId, long bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "IssueRequest{" +
                "readerId=" + readerId +
                ", bookId=" + bookId +
                '}';
    }

}
