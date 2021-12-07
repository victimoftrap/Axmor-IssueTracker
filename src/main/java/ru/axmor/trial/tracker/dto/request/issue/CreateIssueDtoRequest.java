package ru.axmor.trial.tracker.dto.request.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CreateIssueDtoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotBlank
    private String description;

    public CreateIssueDtoRequest() {
    }

    @JsonCreator
    public CreateIssueDtoRequest(
            @JsonProperty("name") final String name,
            @JsonProperty("author") final String author,
            @JsonProperty("description") final String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateIssueDtoRequest that = (CreateIssueDtoRequest) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuthor(), getDescription());
    }

    @Override
    public String toString() {
        return "CreateIssueDtoRequest{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
