package ru.axmor.trial.tracker.controller;

import org.springframework.security.test.context.support.WithMockUser;
import ru.axmor.trial.tracker.dto.request.issue.CreateCommentDtoRequest;
import ru.axmor.trial.tracker.dto.request.issue.CreateIssueDtoRequest;
import ru.axmor.trial.tracker.dto.response.issue.*;
import ru.axmor.trial.tracker.model.IssueStatus;
import ru.axmor.trial.tracker.service.IssueService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.ArgumentMatchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class IssueControllerTest {
    @Autowired
    private MockMvc mvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private IssueService mockIssueService;

    @Test
    @WithMockUser
    public void testCreateNewIssue() throws Exception {
        final var request = new CreateIssueDtoRequest(
                "Issue #42",
                "Arthur Dent",
                "No answer for this question"
        );
        final var response = new CreateIssueDtoResponse(123L);

        when(mockIssueService.createIssue(ArgumentMatchers.any(CreateIssueDtoRequest.class)))
                .thenReturn(response);

        mvc.perform(post("/issues")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", request.getName())
                        .param("author", request.getAuthor())
                        .param("description", request.getDescription())
                )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/issues"));

        verify(mockIssueService, times(1))
                .createIssue(request);
        verifyNoMoreInteractions(mockIssueService);
    }

    @Test
    @WithMockUser
    public void testSendNewIssuePage() throws Exception {
        final MvcResult result = mvc.perform(get("/issues/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create_issue_page"))
                .andReturn();
        verifyNoInteractions(mockIssueService);
        assertEquals(new CreateIssueDtoRequest(), result.getModelAndView().getModel().get("issue"));
    }

    @Test
    @WithMockUser
    public void testGetAll() throws Exception {
        var issue1 = new ShortIssueDtoResponse(
                1L, "Issue #1", LocalDateTime.now().toString(), IssueStatus.CLOSED.getName());
        var issue2 = new ShortIssueDtoResponse(
                2L, "Issue #2", LocalDateTime.now().minusDays(1).toString(), IssueStatus.RESOLVED.getName()
        );

        when(mockIssueService.getAllIssues())
                .thenReturn(new IssuesListDtoResponse(List.of(issue1, issue2)));

        mvc.perform(get("/issues/"))
                .andExpect(status().isOk())
                .andExpect(view().name("issues_page"))
                .andExpect(model().attribute("issues", hasSize(2)))
                .andExpect(model().attribute("issues", hasItem(
                        allOf(
                                hasProperty("id", is(issue1.getId())),
                                hasProperty("name", is(issue1.getName())),
                                hasProperty("createdAt", is(issue1.getCreatedAt())),
                                hasProperty("status", is(issue1.getStatus()))
                        )
                )))
                .andExpect(model().attribute("issues", hasItem(
                        allOf(
                                hasProperty("id", is(issue2.getId())),
                                hasProperty("name", is(issue2.getName())),
                                hasProperty("createdAt", is(issue2.getCreatedAt())),
                                hasProperty("status", is(issue2.getStatus()))
                        )
                )));
        verify(mockIssueService, times(1))
                .getAllIssues();
        verifyNoMoreInteractions(mockIssueService);
    }

    @Test
    @WithMockUser
    public void testGetById() throws Exception {
        var issue = new IssueDtoResponse(
                1L,
                "Issue #1",
                IssueStatus.CLOSED.getName(),
                LocalDateTime.now().toString(),
                "John Doe",
                "Lorem ipsum dolor sit amet.",
                List.of(
                        new CommentDtoResponse(
                                23L,
                                "Jane Doe",
                                LocalDateTime.now().toString(),
                                IssueStatus.CLOSED.getName(),
                                "Done"
                        )
                )
        );

        when(mockIssueService.getIssueById(anyLong()))
                .thenReturn(issue);

        final MvcResult result = mvc.perform(get("/issues/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("single_issue_page"))
                .andReturn();

        verify(mockIssueService, times(1)).
                getIssueById(1L);
        verifyNoMoreInteractions(mockIssueService);
        assertEquals(issue, result.getModelAndView().getModel().get("issue"));
        assertEquals(new CreateCommentDtoRequest(), result.getModelAndView().getModel().get("commentDto"));
    }

    @Test
    @WithMockUser
    public void testAddComment() throws Exception {
        final var request = new CreateCommentDtoRequest(
                IssueStatus.RESOLVED.getName(),
                "John Doe",
                "Now it has answer for 42-question"
        );
        final var response = new CreateCommentDtoResponse(123L);

        when(mockIssueService.addComment(anyLong(), ArgumentMatchers.any(CreateCommentDtoRequest.class)))
                .thenReturn(response);

        mvc.perform(post("/issues/{id}/comments", 23L)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("status", request.getStatus())
                        .param("author", request.getAuthor())
                        .param("text", request.getText())
                )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/issues/23"));

        verify(mockIssueService, times(1))
                .addComment(23L, request);
        verifyNoMoreInteractions(mockIssueService);
    }
}