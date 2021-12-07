package ru.axmor.trial.tracker.repository.mybatis;

import ru.axmor.trial.tracker.mapper.CommentMapper;
import ru.axmor.trial.tracker.mapper.IssueMapper;
import ru.axmor.trial.tracker.model.Comment;
import ru.axmor.trial.tracker.model.Issue;
import ru.axmor.trial.tracker.repository.CommentRepository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.axmor.trial.tracker.repository.IssueRepository;

import java.util.List;

/**
 * Implementation of {@link CommentRepository} based on MyBatis framework.
 */
@Repository
public class MyBatisCommentRepositoryImpl implements CommentRepository {
    private final CommentMapper commentMapper;
    private final IssueMapper issueMapper;

    /**
     * Create comment repository implementation.
     *
     * @param commentMapper MyBatis {@link CommentMapper} that will operate with database
     * @param issueMapper MyBatis {@link IssueMapper} for updating issue status
     */
    @Autowired
    public MyBatisCommentRepositoryImpl(
            final CommentMapper commentMapper,
            final IssueMapper issueMapper) {
        this.commentMapper = commentMapper;
        this.issueMapper = issueMapper;
    }

    @Override
    @Transactional
    public Comment saveForIssue(final Issue issue, final Comment comment) {
        commentMapper.insertForIssue(issue.getId(), comment);
        issueMapper.updateStatus(issue);
        return comment;
    }

    @Override
    public List<Comment> getAllForIssue(final long issueId) {
        return commentMapper.getAllByIssueId(issueId);
    }
}
