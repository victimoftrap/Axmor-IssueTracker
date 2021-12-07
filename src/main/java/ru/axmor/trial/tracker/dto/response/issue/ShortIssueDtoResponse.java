package ru.axmor.trial.tracker.dto.response.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ShortIssueDtoResponse {
    private long id;
    private String name;
    private String createdAt;
    private String status;

    @JsonCreator
    public ShortIssueDtoResponse(
            @JsonProperty("id") final long id,
            @JsonProperty("name") final String name,
            @JsonProperty("createdAt") final String createdAt,
            @JsonProperty("status") final String status
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortIssueDtoResponse that = (ShortIssueDtoResponse) o;
        return getId() == that.getId()
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreatedAt(), getStatus());
    }

    @Override
    public String toString() {
        return "ShortIssueDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
