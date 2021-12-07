package ru.axmor.trial.tracker.mapper;

import ru.axmor.trial.tracker.model.Issue;
import ru.axmor.trial.tracker.model.IssueStatus;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface IssueMapper {
    @Insert({
            "INSERT INTO issues (name, status, created_at, author, description)",
            "VALUES (#{name}, #{status.name}, #{createdAt}, #{author}, #{description})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insert(Issue issue);

    @Select({
            "SELECT id, name, status, created_at, author, description FROM issues WHERE id = #{id}"
    })
    @Results(
            id = "issueResult",
            value = {
                    @Result(property = "id", column = "id", javaType = long.class),
                    @Result(property = "name", column = "name", javaType = String.class),
                    @Result(property = "status", column = "status", javaType = IssueStatus.class),
                    @Result(property = "createdAt", column = "created_at", javaType = LocalDateTime.class),
                    @Result(property = "author", column = "author", javaType = String.class),
                    @Result(property = "description", column = "description", javaType = String.class),
                    @Result(property = "comments", column = "id", javaType = List.class,
                            many = @Many(
                                    select = "ru.axmor.trial.tracker.mapper.CommentMapper.getAllByIssueId",
                                    fetchType = FetchType.LAZY
                            )
                    ),
            }
    )
    Issue getById(@Param("id") long id);

    @Select("SELECT id, name, status, created_at, author, description FROM issues")
    @ResultMap("issueResult")
    List<Issue> getAll();

    @Update("UPDATE issues SET status = #{status.name} WHERE id = #{id}")
    void updateStatus(Issue issue);

    @Delete("DELETE FROM issues")
    void deleteAll();
}
