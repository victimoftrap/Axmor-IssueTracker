package ru.axmor.trial.tracker.mapper;

import ru.axmor.trial.tracker.model.Author;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorMapper {
    @Insert({
            "INSERT INTO authors (name, password) VALUES(#{name}, #{password})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insert(Author author);

    @Select("SELECT id, name, password FROM authors WHERE id = #{id}")
    Author getById(long id);

    @Select("SELECT id, name, password FROM authors")
    List<Author> getAll();

    @Delete("DELETE FROM authors")
    void deleteAll();
}
