package io.presentation.jpa.jdbc.paradigm.problem.relationship;

/**
 * Created By Minhyuk Yoon on 2018. 7. 26.
 */
public class TblMember {

    private String id;
    private long teamId;    //Team 테이블의 team_id 컬럼을 그대로 사용, member.getTeam()으로 연관 객체인 Team 참조 불가능!!
    private String name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
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
