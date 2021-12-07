package ru.axmor.trial.tracker.repository.mybatis;

import ru.axmor.trial.tracker.mapper.AuthorMapper;
import ru.axmor.trial.tracker.model.Author;
import ru.axmor.trial.tracker.repository.AuthorRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisAuthorRepositoryImpl implements AuthorRepository {
    private AuthorMapper authorMapper;

    @Autowired
    public MyBatisAuthorRepositoryImpl(final AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Override
    @Transactional
    public Author save(final Author author) {
        authorMapper.insert(author);
        return author;
    }

    @Override
    public Author getById(final long id) {
        return authorMapper.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorMapper.getAll();
    }
}
