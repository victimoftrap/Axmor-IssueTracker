package ru.axmor.trial.tracker.dto.request.issue;

import ru.axmor.trial.tracker.validation.ValidIssueStatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CreateCommentDtoRequest {
    @NotBlank
    @ValidIssueStatus
    private String status;

    @NotBlank
    private String author;

    @NotBlank
    private String text;

    public CreateCommentDtoRequest() {
    }

    @JsonCreator
    public CreateCommentDtoRequest(
            @JsonProperty("status") final String status,
            @JsonProperty("author") final String author,
            @JsonProperty("text") final String text) {
        this.status = status;
        this.author = author;
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCommentDtoRequest that = (CreateCommentDtoRequest) o;
        return Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getAuthor(), getText());
    }

    @Override
    public String toString() {
        return "CreateCommentDtoRequest{" +
                "status='" + status + '\'' +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
