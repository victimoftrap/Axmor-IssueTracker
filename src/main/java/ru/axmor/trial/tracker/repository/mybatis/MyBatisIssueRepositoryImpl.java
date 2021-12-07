package ru.axmor.trial.tracker.repository.mybatis;

import ru.axmor.trial.tracker.model.Issue;
import ru.axmor.trial.tracker.mapper.IssueMapper;
import ru.axmor.trial.tracker.repository.IssueRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link IssueRepository} based on MyBatis framework.
 */
@Repository
public class MyBatisIssueRepositoryImpl implements IssueRepository {
    private final IssueMapper issueMapper;

    /**
     * Create issue repository implementation.
     *
     * @param issueMapper MyBatis {@link IssueMapper} that will operate with database
     */
    @Autowired
    public MyBatisIssueRepositoryImpl(final IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    @Transactional
    public Issue save(final Issue issue) {
        issueMapper.insert(issue);
        return issue;
    }

    @Override
    @Transactional
    public Issue update(final Issue issue) {
        issueMapper.updateStatus(issue);
        return issue;
    }

    @Override
    public Issue getById(final long id) {
        return issueMapper.getById(id);
    }

    @Override
    public List<Issue> getAll() {
        return issueMapper.getAll();
    }
}
