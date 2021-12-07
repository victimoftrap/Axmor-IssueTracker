package ru.axmor.trial.tracker.service;

import ru.axmor.trial.tracker.exception.IssueNotFoundException;
import ru.axmor.trial.tracker.model.Issue;
import ru.axmor.trial.tracker.model.IssueStatus;
import ru.axmor.trial.tracker.model.Comment;
import ru.axmor.trial.tracker.repository.IssueRepository;
import ru.axmor.trial.tracker.repository.CommentRepository;
import ru.axmor.trial.tracker.dto.response.issue.*;
import ru.axmor.trial.tracker.dto.request.issue.CreateIssueDtoRequest;
import ru.axmor.trial.tracker.dto.request.issue.CreateCommentDtoRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link IssueService} interface with business logic.
 */
@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    /**
     * Create issue service.
     *
     * @param issueRepository   some {@link IssueRepository} that works with issues in database
     * @param commentRepository some {@link CommentRepository} that works with comments in database
     */
    @Autowired
    public IssueServiceImpl(
            final IssueRepository issueRepository,
            final CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CreateIssueDtoResponse createIssue(final CreateIssueDtoRequest request) {
        final var issue = new Issue(
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                request.getAuthor(),
                request.getName(),
                request.getDescription()
        );
        final Issue savedIssue = issueRepository.save(issue);
        return new CreateIssueDtoResponse(savedIssue.getId());
    }

    @Override
    public CreateCommentDtoResponse addComment(
            final long issueId,
            final CreateCommentDtoRequest request) throws IssueNotFoundException {
        final Issue issue = issueRepository.getById(issueId);
        if (issue == null) {
            throw new IssueNotFoundException("No issue found for id " + issueId);
        }

        final var comment = new Comment(
                request.getAuthor(),
                request.getText(),
                IssueStatus.valueOf(request.getStatus()),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );
        issue.setStatus(comment.getNewStatus());
        commentRepository.saveForIssue(issue, comment);
        return new CreateCommentDtoResponse(comment.getId());
    }

    @Override
    public IssueDtoResponse getIssueById(final long id) throws IssueNotFoundException {
        final Issue foundIssue = issueRepository.getById(id);
        if (foundIssue == null) {
            throw new IssueNotFoundException("No issue found for id " + id);
        }

        return new IssueDtoResponse(
                foundIssue.getId(),
                foundIssue.getName(),
                foundIssue.getStatus().getName(),
                foundIssue.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                foundIssue.getAuthor(),
                foundIssue.getDescription(),
                foundIssue.getComments()
                        .stream()
                        .map(comment -> new CommentDtoResponse(
                                comment.getId(),
                                comment.getAuthor(),
                                comment.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                comment.getNewStatus().getName(),
                                comment.getText()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public IssuesListDtoResponse getAllIssues() {
        final List<Issue> issues = issueRepository.getAll();

        final List<ShortIssueDtoResponse> responseList = new ArrayList<>();
        issues.forEach(aIssue ->
                responseList.add(new ShortIssueDtoResponse(
                        aIssue.getId(),
                        aIssue.getName(),
                        aIssue.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        aIssue.getStatus().getName()
                ))
        );
        return new IssuesListDtoResponse(responseList);
    }
}
