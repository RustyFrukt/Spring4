package ru.geekbrains.hometask4.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hometask4.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Репозиторий хранения информации о книгах
 */
@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("Война и мир"),
                new Book("Мёртвые души"),
                new Book("Чистый код"),
                new Book("Грокаем алгоритмы"),
                new Book("Основы Agile")
        ));
    }

    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Book addBook(Book book) {
        if(book != null) {
            books.add(book);
        } else {
            throw new RuntimeException("Error: incorrect argument");
        }
        return book;
    }

    public void deleteBook(long id) {
        books.removeIf(it -> Objects.equals(it.getId(), id));
    }

    public List<Book> getAllBooks() {
        return List.copyOf(this.books);
    }

}
