package io.presentation.jpa.entitymapping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
@Table(name = "team")
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("team")
    private List<Member> members;

    protected Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }

    public void addMember(Member member) {
        if (!isAlreadyMember(member)) {
            members.add(member);
            member.joinTeam(this);
        }
    }

    public void removeMember(Member member) {
        if (isAlreadyMember(member)) {
            members.remove(member);
            member.clearTeam(this);
        }
    }

    private boolean isAlreadyMember(Member member) {
        return this.members.contains(member);
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Member> getMembers() {
        return members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId);
    }
}
