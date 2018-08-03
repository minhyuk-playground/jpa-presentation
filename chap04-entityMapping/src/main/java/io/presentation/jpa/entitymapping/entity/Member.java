package io.presentation.jpa.entitymapping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;

    @Column(name = "age", nullable = false)
    private int age;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "team_id",
            referencedColumnName = "team_id",
            foreignKey = @ForeignKey(name = "fk_member_team"))
    @JsonIgnoreProperties("members")
    private Team team;

    protected Member() {
    }

    public Member(String memberName, int age, Address address) {
        this.memberName = memberName;
        this.age = age;
        this.address = address;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void joinTeam(Team team) {
        if (!hasTeam()) {
            this.team = team;
            team.addMember(this);
        }
    }

    public void transferTeam(Team newTeam) {
        if (hasTeam() && !isSameTeam(newTeam)) {
            team.removeMember(this);
            team = newTeam;
            newTeam.addMember(this);
        }
    }

    public void clearTeam(Team team) {
        if (hasTeam() && isSameTeam(team)) {
            this.team = null;
            team.removeMember(this);
        }
    }


    private boolean hasTeam() {
        return this.team != null;
    }

    private boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public Team getTeam() {
        return team;
    }
}
