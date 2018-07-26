package io.presentation.jpa.paradigm.problem.relationship;

/**
 * Created By Minhyuk Yoon on 2018. 7. 26.
 */
public class TblTeam {

    private long teamId;
    private String name;

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
}
