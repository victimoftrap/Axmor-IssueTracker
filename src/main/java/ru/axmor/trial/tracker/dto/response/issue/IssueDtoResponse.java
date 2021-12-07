package ru.axmor.trial.tracker.dto.response.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class IssueDtoResponse {
    private long id;
    private String name;
    private String status;
    private String createdAt;
    private String author;
    private String description;
    private List<CommentDtoResponse> comments;

    @JsonCreator
    public IssueDtoResponse(
            @JsonProperty("id") final long id,
            @JsonProperty("name") final String name,
            @JsonProperty("status") final String status,
            @JsonProperty("createdAt") final String createdAt,
            @JsonProperty("author") final String author,
            @JsonProperty("description") final String description,
            @JsonProperty("comments") final List<CommentDtoResponse> comments
    ) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.author = author;
        this.description = description;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public List<CommentDtoResponse> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueDtoResponse that = (IssueDtoResponse) o;
        return getId() == that.getId()
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getDescription(), that.getDescription())
                && Objects.equals(getComments(), that.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getCreatedAt(), getAuthor(), getDescription(), getComments());
    }

    @Override
    public String toString() {
        return "IssueDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }
}
