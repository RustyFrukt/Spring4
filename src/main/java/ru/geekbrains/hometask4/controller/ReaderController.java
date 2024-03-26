package ru.geekbrains.hometask4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hometask4.model.Issue;
import ru.geekbrains.hometask4.model.Reader;
import ru.geekbrains.hometask4.service.IssueService;
import ru.geekbrains.hometask4.service.ReaderService;

import java.util.List;

// @Slf4j - модуль lombok для логирования (см. в коде)
@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired

    private final ReaderService readerService;
    @Autowired
    private final IssueService issueService;

    public ReaderController(ReaderService readerService, IssueService issueService) {
        this.readerService = readerService;
        this.issueService = issueService;
    }

    // GET /reader/{id} - получить читателя по ID
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getBookName(@PathVariable long id) {
        //log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
        final Reader reader;
        reader = readerService.getReaderById(id);
        if (reader == null) {
            System.out.println("Читатель: не найден");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Читатель: " + readerService.getReaderById(id));
            return ResponseEntity.status(HttpStatus.OK).body(reader);
        }
    }

    // GET /reader - получить всех читателей
    @GetMapping
    public ResponseEntity<List<Reader>> getAllReaders() {
        return ResponseEntity.status(HttpStatus.OK).body(readerService.getAllReaders());
    }

    // POST /reader - добавить читателя (принимает JSON)
    @PostMapping
    public ResponseEntity<Reader> addReader(@RequestBody Reader reader) {
        readerService.addReader(reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }

    // PUT /reader/{id} - обновить данные читателя (принимает JSON)
    @PutMapping("/{id}")
    public ResponseEntity<Reader> updateTaskById(@PathVariable long id, @RequestBody Reader reader) {
        readerService.updateReader(id, reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }

    // DELETE /reader/{id} - удалить читателя
    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReader(@PathVariable long id) {
        readerService.deleteReader(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getBooksByReader(@PathVariable long id) {
        final List<Issue> readerIssues;
        readerIssues = issueService.getIssuesByReader(id);
        if (readerIssues.size() < 1) {
            System.out.println("Выдачи по читателю не найдены");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Читатель: " + readerService.getReaderById(id));
            return ResponseEntity.status(HttpStatus.OK).body(readerIssues);
        }
    }

}
