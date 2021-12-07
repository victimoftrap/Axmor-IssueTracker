package ru.axmor.trial.tracker.controller;

import ru.axmor.trial.tracker.exception.IssueNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class-controller that used to handle global exceptions
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    /**
     * Handle {@link IssueNotFoundException} and return error page.
     *
     * @param throwable exception with id of not found issue
     * @return error page
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IssueNotFoundException.class)
    public String handleIssueNotFoundException(final IssueNotFoundException throwable) {
        return "error";
    }
}
