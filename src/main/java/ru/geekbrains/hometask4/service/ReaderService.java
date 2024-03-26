package ru.geekbrains.hometask4.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.hometask4.model.Reader;
import ru.geekbrains.hometask4.repository.ReaderRepository;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader getReaderById(long id) {
        return readerRepository.getReaderById(id);
    }

    public Reader addReader(Reader reader) {
        return readerRepository.addReader(reader);
    }

    public Reader updateReader(long id, Reader reader) {
        return readerRepository.updateReader(id, reader);
    }

    public void deleteReader(long id) {
        readerRepository.deleteReader(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }

}
