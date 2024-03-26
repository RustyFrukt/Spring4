package ru.geekbrains.hometask4.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.hometask4.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Репозиторий хранения информации о выдаче книг
 */
@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public List<Issue> getIssues() {
        return List.copyOf(issues);
    }

    /**
     * Метод создания новой записи в репозитории объектов типа Issue
     * @param issue новый объект типа Issue
     */
    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    /**
     * Метод получения из репозитория объекта типа Issue по его идентификатору
     * @param issueId идентификатор объекта типа Issue
     * @return объект типа Issue
     */
    public Issue getIssueById(long issueId) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), issueId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод получения информации, сколько книг уже взял читатель
     * @param readerId идентификатор читателя
     * @return возвращает количество книг, которое уже взял читатель
     */
    public int countBooksIssuedToReader(long readerId) {
        return issues.stream().filter(it -> Objects.equals(it.getReaderId(), readerId) && it.getReturned_at() == null).toList().size();
//                .collect(Collectors.toList()).size();
    }

    /**
     * Метод получения информации обо всех выдачах книг читателю
     * @param readerId идентификатор читателя
     * @return возвращает список объектов типа Issue для конкретного читателя
     */
    public List<Issue> getIssuesByReader(long readerId) {
        return issues.stream().filter(it -> Objects.equals(it.getReaderId(), readerId)).collect(Collectors.toList());
    }

    /**
     * Метод обработки процедуры возврата книги читателем
     * @param issueId идентификатор выдачи
     * @return возвращает объект типа Issue с новым значением даты возврата книги
     */
    public Issue returnBook(long issueId) {
        Issue issue = getIssueById(issueId);
        if(issue != null) {
            issue.setReturned_at(LocalDateTime.now());
        }
        return issue;
    }

}
