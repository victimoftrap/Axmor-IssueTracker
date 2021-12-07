package ru.axmor.trial.tracker.mapper;

import ru.axmor.trial.tracker.model.Comment;
import ru.axmor.trial.tracker.model.IssueStatus;

import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert({
            "INSERT INTO comments (issue_id, author, text, new_status, updated_at)",
            "VALUES(#{issueId}, #{comment.author}, #{comment.text}, #{comment.newStatus}, #{comment.updatedAt})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "comment.id")
    long insertForIssue(@Param("issueId") long issueId, @Param("comment") Comment comment);

    @Select("SELECT id, issue_id, author, text, new_status, updated_at FROM comments WHERE issue_id = #{issueId}")
    @Results(
            id = "commentResult",
            value = {
                    @Result(property = "id", column = "id", javaType = long.class),
                    @Result(property = "author", column = "author", javaType = String.class),
                    @Result(property = "text", column = "text", javaType = String.class),
                    @Result(property = "newStatus", column = "new_status", javaType = IssueStatus.class),
                    @Result(property = "updatedAt", column = "updated_at", javaType = LocalDateTime.class),
            }
    )
    List<Comment> getAllByIssueId(@Param("issueId") long issueId);

    @Delete("DELETE FROM comments")
    void deleteAll();
}
