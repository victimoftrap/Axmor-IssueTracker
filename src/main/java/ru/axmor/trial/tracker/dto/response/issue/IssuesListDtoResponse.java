package ru.axmor.trial.tracker.dto.response.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class IssuesListDtoResponse {
    private List<ShortIssueDtoResponse> issues;

    @JsonCreator
    public IssuesListDtoResponse(@JsonProperty("issues") List<ShortIssueDtoResponse> issues) {
        this.issues = issues;
    }

    public List<ShortIssueDtoResponse> getIssues() {
        return issues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuesListDtoResponse that = (IssuesListDtoResponse) o;
        return Objects.equals(getIssues(), that.getIssues());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIssues());
    }

    @Override
    public String toString() {
        return "IssuesListDtoResponse{" +
                "issues=" + issues +
                '}';
    }
}
