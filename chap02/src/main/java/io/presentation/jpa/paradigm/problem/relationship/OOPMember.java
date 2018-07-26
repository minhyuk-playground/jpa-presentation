package io.presentation.jpa.paradigm.problem.relationship;

/**
 * Created By Minhyuk Yoon on 2018. 7. 26.
 */
public class OOPMember {

    private String id;
    private OOPTeam team;       //객체 중심의 모델링. member.getTeam()으로 조회 연관객체인 Team 조회 가능
    private String name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OOPTeam getTeam() {
        return team;
    }

    public void setTeam(OOPTeam team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
