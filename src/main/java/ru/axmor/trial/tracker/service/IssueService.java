package ru.axmor.trial.tracker.service;

import ru.axmor.trial.tracker.dto.request.issue.CreateIssueDtoRequest;
import ru.axmor.trial.tracker.dto.request.issue.CreateCommentDtoRequest;
import ru.axmor.trial.tracker.dto.response.issue.CreateCommentDtoResponse;
import ru.axmor.trial.tracker.dto.response.issue.CreateIssueDtoResponse;
import ru.axmor.trial.tracker.dto.response.issue.IssueDtoResponse;
import ru.axmor.trial.tracker.dto.response.issue.IssuesListDtoResponse;
import ru.axmor.trial.tracker.exception.IssueNotFoundException;

/**
 * Interface for business logic of issues.
 */
public interface IssueService {
    /**
     * Create new issue by user request and store it in database.
     *
     * @param request dto object that contains data for new issue
     * @return dto response of a server answer
     */
    CreateIssueDtoResponse createIssue(CreateIssueDtoRequest request);

    /**
     * Add new comment for an issue.
     *
     * @param issueId id of issue that receives new comment
     * @param request dto object with data for comment
     * @return dto response of a server answer
     * @throws IssueNotFoundException if issue by issueId not found
     */
    CreateCommentDtoResponse addComment(long issueId, CreateCommentDtoRequest request) throws IssueNotFoundException;

    /**
     * Get issue data by specific id.
     *
     * @param id id of needed issue
     * @return dto response object with full issue data
     * @throws IssueNotFoundException if issue not found
     */
    IssueDtoResponse getIssueById(long id) throws IssueNotFoundException;

    /**
     * Get all issues data.
     *
     * @return dto object that contains list of short issues info
     */
    IssuesListDtoResponse getAllIssues();
}
