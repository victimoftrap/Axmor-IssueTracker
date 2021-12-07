package ru.axmor.trial.tracker.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation interface that validates issue statuses in dto objects.
 */
@Documented
@Constraint(validatedBy = IssueStatusValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIssueStatus {
    /**
     * Get annotation error.html message when validation is failed.
     *
     * @return error.html string with all available statuses
     */
    String message() default "Issue state must be one of this: %s";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
