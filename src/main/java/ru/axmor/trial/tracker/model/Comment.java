package ru.axmor.trial.tracker.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class-model for a comment.
 */
public class Comment {
    private long id;
    private String author;
    private String text;
    private IssueStatus newStatus;
    private LocalDateTime updatedAt;

    protected Comment() {
    }

    /**
     * Create comment.
     *
     * @param id        unique number that identifies comment
     * @param author    name of author that created issue
     * @param text      details of a comment
     * @param newStatus updated status of issue, one of {@link IssueStatus}
     * @param updatedAt datetime when comment was created
     */
    public Comment(long id, String author, String text, IssueStatus newStatus, LocalDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.newStatus = newStatus;
        this.updatedAt = updatedAt;
    }

    public Comment(String author, String text, IssueStatus newStatus, LocalDateTime updatedAt) {
        this(0L, author, text, newStatus, updatedAt);
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public IssueStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(final IssueStatus newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getId() == comment.getId()
                && Objects.equals(getAuthor(), comment.getAuthor())
                && Objects.equals(getText(), comment.getText())
                && Objects.equals(getNewStatus(), comment.getNewStatus())
                && Objects.equals(getUpdatedAt(), comment.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getText(), getNewStatus(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", newStatus='" + newStatus + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
