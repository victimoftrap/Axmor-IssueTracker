package ru.axmor.trial.tracker.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class-model for an issue.
 */
public class Issue {
    private long id;
    private IssueStatus status;
    private LocalDateTime createdAt;
    private String author;
    private String name;
    private String description;
    private List<Comment> comments;

    protected Issue() {
    }

    /**
     * Create issue.
     *
     * @param id          unique number that identifies issue
     * @param status      status of issue, one of {@link IssueStatus}
     * @param createdAt   datetime when issue was created
     * @param author      name of author that created issue
     * @param name        issue title with general info
     * @param description details about issue
     * @param comments    list with comments
     */
    public Issue(
            final long id,
            final IssueStatus status,
            final LocalDateTime createdAt,
            final String author,
            final String name,
            final String description,
            final List<Comment> comments) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.author = author;
        this.name = name;
        this.description = description;
        this.comments = comments;
    }

    public Issue(
            final LocalDateTime createdAt,
            final String author,
            final String name,
            final String description) {
        this(0L, IssueStatus.CREATED, createdAt, author, name, description, Collections.emptyList());
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(final IssueStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return getId() == issue.getId()
                && getStatus() == issue.getStatus()
                && Objects.equals(getCreatedAt(), issue.getCreatedAt())
                && Objects.equals(getAuthor(), issue.getAuthor())
                && Objects.equals(getName(), issue.getName())
                && Objects.equals(getDescription(), issue.getDescription())
                && Objects.equals(getComments(), issue.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), getStatus(), getCreatedAt(), getAuthor(),
                getName(), getDescription(), getComments()
        );
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }
}
