package ru.axmor.trial.tracker.exception;

/**
 * Exception class that throws when issue not found.
 */
public class IssueNotFoundException extends Exception {
    public IssueNotFoundException() {
    }

    public IssueNotFoundException(final String message) {
        super(message);
    }

    public IssueNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
