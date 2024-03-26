package ru.geekbrains.hometask4.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hometask4.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Репозиторий хранения информации о читателях
 */
@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new Reader("Игорь"),
                new Reader("Артем"),
                new Reader("Евгений")
        ));
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Reader addReader(Reader reader) {
        readers.add(reader);
        return reader;
    }

    public Reader updateReader(long id, Reader newReader) {
        Reader reader = getReaderById(id);
        if(reader != null) {
            reader.setName(newReader.getName());
        }
        return reader;
    }

    public void deleteReader(long id) {
        readers.removeIf(it -> Objects.equals(it.getId(), id));
    }

    public List<Reader> getAllReaders() {
        return List.copyOf(this.readers);
    }

}
