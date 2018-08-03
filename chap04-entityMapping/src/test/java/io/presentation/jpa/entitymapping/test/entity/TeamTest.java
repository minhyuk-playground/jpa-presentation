package io.presentation.jpa.entitymapping.test.entity;

import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.entity.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
public class TeamTest {

    private Member member;
    private Address address;
    private Team team;

    @Before
    public void setUp() {
        this.address = new Address("city", "street", "123-123");
        this.member = new Member("member01", 20, address);
        this.team = new Team("TeamA");
    }

    @Test
    public void testAddMember() {
        //Given
        Member member = new Member("member01", 20, new Address("city", "street", "zipcode"));
        Team team = new Team("TeamA");

        //When
        team.addMember(member);
        Member findMember = team.getMembers().get(0);

        //Then
        assertTrue(findMember == member);
        assertTrue(member.getTeam() == team);

    }

    @Test
    public void testAddMemberFailureBecauseMemberAlreadyAMemberOfTheTeam() {
        //Given
        team.addMember(member);

        //When
        team.addMember(member);

        //Then
        assertTrue(team.getMembers().size() == 1);
        assertTrue(member.getTeam() == team);
    }

    @Test
    public void testRemoveMember() {
        //Given
        team.addMember(member);

        //When
        team.removeMember(member);

        //Then
        assertTrue(team.getMembers().size() == 0);
        assertTrue(member.getTeam() == null);
    }

    @Test
    public void testRemoveMemberFailureBecauseTeamHasNotMember() {

        //Given
        team.addMember(member);
        Member newMember = new Member("newMember", 20, new Address("newCity", "newStreet","newZipcode"));

        //When
        team.removeMember(newMember);

        //Then
        assertTrue(team.getMembers().size() == 1);
        assertTrue(member.getTeam() == team);
    }
}
