package ru.geekbrains.hometask4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hometask4.model.Issue;
import ru.geekbrains.hometask4.service.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

// @Slf4j - модуль lombok для логирования (см. в коде)
@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // GET /issue/{id} - получить описание факта выдачи книги по id выдачи
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable long id) {
        final Issue issue;
        issue = issueService.getIssueById(id);
        if (issue == null) {
            System.out.println("Выдача: " + id + " не найдена");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Выдача: " + issueService.getIssueById(id));
            return ResponseEntity.status(HttpStatus.OK).body(issue);
        }
    }

    // GET /issue - получить все записи о выдаче книг
    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.status(HttpStatus.OK).body(issueService.getAllIssues());
    }

    // POST /issue - Создать новую запись о выдаче книги
    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        // log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
        final Issue issue;
        try {
            issue = issueService.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        if (issue != null)
            return ResponseEntity.status(HttpStatus.OK).body(issue);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // PUT /issue/{id} - Сделать запись о возврате книги по id выдачи
    @PutMapping("/{id}")
    public void returnBook(@PathVariable long id) {
        issueService.returnBook(id);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> notFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
