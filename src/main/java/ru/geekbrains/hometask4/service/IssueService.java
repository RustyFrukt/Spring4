package ru.geekbrains.hometask4.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.hometask4.controller.IssueRequest;
import ru.geekbrains.hometask4.model.Issue;
import ru.geekbrains.hometask4.repository.IssueRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IssueService {

    private final BookService bookService;
    private final ReaderService readerService;
    private final IssueRepository issueRepository;
    // application.max-allowed-books default = 1
    @Value("${application.max-allowed-books:1}")
    private int maxAllowedBooks;

    public IssueService(BookService bookService, ReaderService readerService, IssueRepository issueRepository) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.issueRepository = issueRepository;
    }

    public Issue issue(IssueRequest request) {
        if (bookService.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerService.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (issueRepository.getIssues().stream()
                .filter(it -> it.getBookId() == request.getBookId()
                        && it.getReturned_at() == null)
                .toList().size() != 0) {
            throw new NoSuchElementException("Книга с идентификатором \"" + request.getBookId() +
                    "\"находится на руках");
        }
        // Проверяеть, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        int booksInHand = issueRepository.countBooksIssuedToReader(request.getReaderId());
        if (booksInHand < maxAllowedBooks) {
            Issue issue = new Issue(request.getBookId(), request.getReaderId());
            issueRepository.save(issue);
            return issue;
        } else {
            return null;
        }
    }

    public Issue getIssueById(Long id) {
        return issueRepository.getIssueById(id);
    }

    public void returnBook(long issueId) {
        issueRepository.returnBook(issueId);
    }

    public List<Issue> getIssuesByReader(long id) {
        return issueRepository.getIssuesByReader(id);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.getIssues();
    }

}
