package ru.axmor.trial.tracker.dto.response.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateIssueDtoResponse {
    private long id;

    @JsonCreator
    public CreateIssueDtoResponse(@JsonProperty("id") final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateIssueDtoResponse that = (CreateIssueDtoResponse) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CreateIssueDtoResponse{" +
                "id=" + id +
                '}';
    }
}
