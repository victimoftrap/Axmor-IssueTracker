package ru.axmor.trial.tracker.validation;

import ru.axmor.trial.tracker.model.IssueStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.StringJoiner;
import java.util.Arrays;

/**
 * Class that validates strings annotated by {@link ValidIssueStatus}.
 */
public class IssueStatusValidator implements ConstraintValidator<ValidIssueStatus, String> {
    private String exceptionMessage;

    /**
     * Init annotation validator. Sets available issue statuses into exception message.
     *
     * @param constraintAnnotation object of an annotation
     */
    @Override
    public void initialize(final ValidIssueStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        var joiner = new StringJoiner(", ");
        Arrays.stream(IssueStatus.values())
                .forEach(status -> joiner.add(status.getName()));
        exceptionMessage = String.format(constraintAnnotation.message(), joiner);
    }

    /**
     * Check is received issue status valid.
     *
     * @param status                     string value annotated by {@link ValidIssueStatus} issue status
     * @param constraintValidatorContext validator context
     * @return true if annotated string is valid issue status
     */
    @Override
    public boolean isValid(final String status, final ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(exceptionMessage)
                .addConstraintViolation();

        return Arrays.stream(IssueStatus.values())
                .anyMatch(availableStatus -> availableStatus.getName().equalsIgnoreCase(status));
    }
}
