package io.presentation.jpa.domain;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class TableMember {

    private String id;
    private long teamId;
    private String name;
    private int age;

    public TableMember(String id, long teamId, String name, int age) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
