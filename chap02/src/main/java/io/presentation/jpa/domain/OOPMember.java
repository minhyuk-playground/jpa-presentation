package io.presentation.jpa.domain;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class OOPMember {

    private String id;
    private String name;
    private int age;
    private Team team;

    public OOPMember() {
    }

    public OOPMember(String id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
