package ru.axmor.trial.tracker.dto.response.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CommentDtoResponse {
    private long id;
    private String author;
    private String updatedAt;
    private String status;
    private String text;

    @JsonCreator
    public CommentDtoResponse(
            @JsonProperty("id") final long id,
            @JsonProperty("author") final String author,
            @JsonProperty("updatedAt") final String updatedAt,
            @JsonProperty("status") final String status,
            @JsonProperty("text") final String text
    ) {
        this.id = id;
        this.author = author;
        this.updatedAt = updatedAt;
        this.status = status;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDtoResponse that = (CommentDtoResponse) o;
        return getId() == that.getId()
                && Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getUpdatedAt(), that.getUpdatedAt())
                && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getUpdatedAt(), getStatus(), getText());
    }

    @Override
    public String toString() {
        return "IssueCommentsDtoResponse{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", status='" + status + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
