package io.presentation.jpa.domain;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class Team {

    private long id;
    private String name;

    public Team() {
    }

    public Team(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}