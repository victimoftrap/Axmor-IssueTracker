package ru.axmor.trial.tracker.service;

import org.mockito.ArgumentCaptor;
import ru.axmor.trial.tracker.dto.request.issue.CreateCommentDtoRequest;
import ru.axmor.trial.tracker.dto.request.issue.CreateIssueDtoRequest;
import ru.axmor.trial.tracker.dto.response.issue.*;
import ru.axmor.trial.tracker.exception.IssueNotFoundException;
import ru.axmor.trial.tracker.model.Comment;
import ru.axmor.trial.tracker.model.Issue;
import ru.axmor.trial.tracker.model.IssueStatus;
import ru.axmor.trial.tracker.repository.CommentRepository;
import ru.axmor.trial.tracker.repository.IssueRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IssueServiceImplTest {
    private IssueRepository mockIssueRepository;
    private CommentRepository mockCommentRepository;
    private IssueService issueService;

    @BeforeEach
    void initMocks() {
        mockIssueRepository = mock(IssueRepository.class);
        mockCommentRepository = mock(CommentRepository.class);
        issueService = new IssueServiceImpl(
                mockIssueRepository,
                mockCommentRepository
        );
    }

    @Test
    void testCreateIssue() {
        final var request = new CreateIssueDtoRequest(
                "Test issue",
                "John Doe",
                "Lorem ipsum dolor sit amet"
        );
        final var expectedResponse = new CreateIssueDtoResponse(123L);

        when(mockIssueRepository.save(any(Issue.class)))
                .thenAnswer((Answer<Issue>) invocationOnMock -> {
                    final Issue issue = invocationOnMock.getArgument(0);
                    issue.setId(expectedResponse.getId());
                    return issue;
                });

        final var actualResponse = issueService.createIssue(request);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(mockIssueRepository, times(1))
                .save(any(Issue.class));
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoInteractions(mockCommentRepository);
    }

    @Test
    void testGetIssueById() throws IssueNotFoundException {
        final List<Comment> comments = List.of(
                new Comment(
                        111L, "John Doe", "Fixed",
                        IssueStatus.RESOLVED, LocalDateTime.now().minusDays(1)
                ),
                new Comment(
                        112L, "Jack Smith", "OK",
                        IssueStatus.CLOSED, LocalDateTime.now().minusHours(1)
                )
        );
        final var issue = new Issue(
                234L,
                IssueStatus.RESOLVED,
                LocalDateTime.now().minusDays(2),
                "Neo",
                "Regular issue",
                "Nothing at all",
                comments
        );

        final List<CommentDtoResponse> commentsResponse = comments
                .stream()
                .map(comm -> new CommentDtoResponse(
                        comm.getId(),
                        comm.getAuthor(),
                        comm.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        comm.getNewStatus().getName(),
                        comm.getText()
                ))
                .collect(Collectors.toList());
        final var expectedResponse = new IssueDtoResponse(
                issue.getId(),
                issue.getName(),
                issue.getStatus().getName(),
                issue.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                issue.getAuthor(),
                issue.getDescription(),
                commentsResponse
        );

        when(mockIssueRepository.getById(anyLong()))
                .thenReturn(issue);

        final var actualResponse = issueService.getIssueById(issue.getId());
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(mockIssueRepository, times(1))
                .getById(anyLong());
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoInteractions(mockCommentRepository);
    }

    @Test
    public void testGetIssueById_issueNotFound_shouldThrowException() {
        when(mockIssueRepository.getById(anyLong()))
                .thenReturn(null);

        assertThrows(IssueNotFoundException.class, () -> issueService.getIssueById(999L));
        verify(mockIssueRepository, times(1))
                .getById(anyLong());
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoInteractions(mockCommentRepository);
    }

    @Test
    public void testGetAllIssues() {
        final List<Issue> issues = List.of(
                new Issue(
                        234L,
                        IssueStatus.RESOLVED,
                        LocalDateTime.now().minusDays(2),
                        "Neo",
                        "Regular issue",
                        "Nothing at all",
                        List.of(
                                new Comment(
                                        112L, "Jack Smith", "OK",
                                        IssueStatus.CLOSED, LocalDateTime.now().minusHours(1)
                                )
                        )
                ),
                new Issue(
                        567L,
                        IssueStatus.CREATED,
                        LocalDateTime.now(),
                        "Jonas",
                        "Fatal issue",
                        "Some description",
                        Collections.emptyList()
                )
        );
        final var expectedResponse = new IssuesListDtoResponse(
                issues.stream().map(iss -> new ShortIssueDtoResponse(
                                iss.getId(),
                                iss.getName(),
                                iss.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                iss.getStatus().getName()
                        ))
                        .collect(Collectors.toList())
        );

        when(mockIssueRepository.getAll())
                .thenReturn(issues);

        final var response = issueService.getAllIssues();
        assertEquals(expectedResponse, response);

        verify(mockIssueRepository, times(1))
                .getAll();
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoInteractions(mockCommentRepository);
    }

    @Test
    public void testGetAllIssues_noIssues_shouldReturnEmptyList() {
        final var expectedResponse = new IssuesListDtoResponse(Collections.emptyList());
        when(mockIssueRepository.getAll())
                .thenReturn(Collections.emptyList());

        final var response = issueService.getAllIssues();
        assertEquals(expectedResponse, response);

        verify(mockIssueRepository, times(1))
                .getAll();
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoInteractions(mockCommentRepository);
    }

    @Test
    public void testAddComment() throws IssueNotFoundException {
        final var request = new CreateCommentDtoRequest(
                IssueStatus.RESOLVED.getName(),
                "Abraham Shaw",
                "I made it"
        );

        var statusCaptor = ArgumentCaptor.forClass(Issue.class);
        final var issue = new Issue(
                234L,
                IssueStatus.CREATED,
                LocalDateTime.now().minusDays(2),
                "Neo",
                "Regular issue",
                "Nothing at all",
                Collections.emptyList()
        );
        final var comment = new Comment(
                1112L, "Jack Smith", "OK",
                IssueStatus.RESOLVED, LocalDateTime.now().minusHours(1)
        );
        final var expectedResponse = new CreateCommentDtoResponse(comment.getId());
        when(mockIssueRepository.getById(anyLong()))
                .thenReturn(issue);
        when(mockCommentRepository.saveForIssue(statusCaptor.capture(), any(Comment.class)))
                .thenAnswer((Answer<Comment>) invocationOnMock -> {
                    final Comment invocation = invocationOnMock.getArgument(1);
                    invocation.setId(expectedResponse.getId());
                    return invocation;
                });

        final var actualResponse = issueService.addComment(999L, request);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(IssueStatus.RESOLVED, statusCaptor.getValue().getStatus());

        verify(mockIssueRepository, times(1))
                .getById(anyLong());
        verify(mockCommentRepository, times(1))
                .saveForIssue(any(Issue.class), any(Comment.class));
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoMoreInteractions(mockCommentRepository);
    }

    @Test
    public void testAddComment_issueNotFound_shouldThrowException() {
        final var request = new CreateCommentDtoRequest(
                IssueStatus.RESOLVED.getName(),
                "Abraham Shaw",
                "I made it"
        );
        when(mockIssueRepository.getById(anyLong()))
                .thenReturn(null);

        assertThrows(IssueNotFoundException.class, () -> issueService.addComment(999L, request));
        verify(mockIssueRepository, times(1))
                .getById(anyLong());
        verifyNoMoreInteractions(mockIssueRepository);
        verifyNoInteractions(mockCommentRepository);
    }
}