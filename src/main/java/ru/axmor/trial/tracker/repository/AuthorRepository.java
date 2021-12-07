package ru.axmor.trial.tracker.repository;

import ru.axmor.trial.tracker.model.Author;

import java.util.List;

public interface AuthorRepository {
    Author save(Author author);

    Author getById(long id);

    List<Author> getAll();
}
