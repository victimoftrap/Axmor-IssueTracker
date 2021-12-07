package ru.axmor.trial.tracker.dto.request.author;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CreateAuthorDtoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @JsonCreator
    public CreateAuthorDtoRequest(
            @JsonProperty("name") final String name,
            @JsonProperty("password") final String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAuthorDtoRequest that = (CreateAuthorDtoRequest) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword());
    }

    @Override
    public String toString() {
        return "CreateAuthorDtoRequest{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
