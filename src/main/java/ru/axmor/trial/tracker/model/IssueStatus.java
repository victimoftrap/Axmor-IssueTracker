package ru.axmor.trial.tracker.model;

/**
 * Enum with all available issue statuses.
 */
public enum IssueStatus {
    CREATED("CREATED"),
    RESOLVED("RESOLVED"),
    CLOSED("CLOSED");

    private final String name;

    /**
     * Create enum value. Constructor only available in this package.
     *
     * @param name issue status name
     */
    IssueStatus(final String name) {
        this.name = name;
    }

    /**
     * Get name of an issue status.
     *
     * @return name string stored in enum
     */
    public String getName() {
        return name;
    }
}
