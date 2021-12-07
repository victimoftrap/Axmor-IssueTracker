package ru.axmor.trial.tracker.controller;

import org.springframework.validation.BindingResult;
import ru.axmor.trial.tracker.exception.IssueNotFoundException;
import ru.axmor.trial.tracker.model.IssueStatus;
import ru.axmor.trial.tracker.service.IssueService;
import ru.axmor.trial.tracker.dto.request.issue.CreateCommentDtoRequest;
import ru.axmor.trial.tracker.dto.request.issue.CreateIssueDtoRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * Class-controller that returns Thymeleaf pages by user requests.
 */
@Controller
@RequestMapping("/issues")
public class IssueController {
    private final IssueService issueService;

    /**
     * Create controller.
     *
     * @param issueService service with all business logic for issues
     */
    @Autowired
    public IssueController(final IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public String createIssue(
            @Valid @ModelAttribute("issue") final CreateIssueDtoRequest request,
            final BindingResult bindingResult,
            final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("issue", request);
            return "create_issue_page";
        }
        final var response = issueService.createIssue(request);
        return "redirect:/issues";
    }

    @GetMapping("/create")
    public String sendNewIssuePage(final Model model) {
        model.addAttribute("issue", new CreateIssueDtoRequest());
        return "create_issue_page";
    }

    @GetMapping
    public String getAllIssues(final Model model) {
        final var response = issueService.getAllIssues();
        model.addAttribute("issues", response.getIssues());
        return "issues_page";
    }

    @GetMapping("/{id}")
    public String getIssueById(@PathVariable("id") final long id, final Model model) throws IssueNotFoundException {
        final var response = issueService.getIssueById(id);
        model.addAttribute("issue", response);
        model.addAttribute("commentDto", new CreateCommentDtoRequest());
        model.addAttribute("statusList", IssueStatus.values());
        return "single_issue_page";
    }

    @PostMapping("/{id}/comments")
    public String addComment(
            @PathVariable("id") final long id,
            @Valid @ModelAttribute("commentDto") final CreateCommentDtoRequest commentDto,
            final BindingResult bindingResult,
            final Model model) throws IssueNotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("issue", issueService.getIssueById(id));
            model.addAttribute("commentDto", commentDto);
            model.addAttribute("statusList", IssueStatus.values());
            return "single_issue_page";
        }
        final var response = issueService.addComment(id, commentDto);
        return "redirect:/issues/{id}";
    }
}
