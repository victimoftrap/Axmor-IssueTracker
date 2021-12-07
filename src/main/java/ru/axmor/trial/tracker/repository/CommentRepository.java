package ru.axmor.trial.tracker.repository;

import ru.axmor.trial.tracker.model.Comment;
import ru.axmor.trial.tracker.model.Issue;

import java.util.List;

/**
 * Interface that operates with comments in database.
 */
public interface CommentRepository {
    /**
     * Save new comment for issue.
     *
     * @param issue   issue that received new comment
     * @param comment comment object that prepared to be saved
     * @return same comment object
     */
    Comment saveForIssue(Issue issue, Comment comment);

    /**
     * Get all comments of an issue
     *
     * @param issueId id of issue
     * @return list with comments
     */
    List<Comment> getAllForIssue(long issueId);
}
