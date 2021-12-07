package ru.axmor.trial.tracker.model;

import java.util.Objects;

public class Author {
    private long id;
    private String name;
    private String password;

    public Author(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Author(String name, String password) {
        this(0L, name, password);
    }

    public long getId() {
        return id;
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
        Author author = (Author) o;
        return getId() == author.getId()
                && Objects.equals(getName(), author.getName())
                && Objects.equals(getPassword(), author.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword());
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
