package io.presentation.jpa.entitymapping.test.entity;

import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.entity.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 1.
 */
public class MemberTest {

    private Member member;
    private Address address;
    private Team team;

    @Before
    public void setUp() {
        this.address = new Address("seoul", "street", "123-123");
        this.member = new Member("member01", 20, address);
        this.team = new Team("TeamA");
    }

    @Test
    public void testJoinTeam() {
        //Given * When
        member.joinTeam(team);

        //Then
        assertTrue(member.getTeam() == team);
        assertTrue(team.getMembers().size()==1);
        assertTrue(team.getMembers().contains(member));
    }

    @Test
    public void testJoinTeamFailureCausedByMemberAlreadyHasTeam() {
        //Given
        member.joinTeam(team);
        Team newTeam = new Team("TeamB");

        //When
        member.joinTeam(newTeam);

        //Then
        assertTrue(member.getTeam() == team);
        assertTrue(team.getMembers().contains(member));
    }

    @Test
    public void testTransferTeam() {
        //Given
        member.joinTeam(team);
        Team newTeam = new Team("newTeam");

        //When
        member.transferTeam(newTeam);

        //Then
        assertFalse(team.getMembers().contains(member));
        assertTrue(member.getTeam() == newTeam);
        assertTrue(newTeam.getMembers().contains(member));
    }

    @Test
    public void testTransferTeamFailureCausedByMemberHasNotTeam() {
        //Given & When
        member.transferTeam(team);

        //Then
        assertTrue(member.getTeam() == null);
        assertFalse(team.getMembers().contains(member));
    }

    @Test
    public void testTransferTeamFailureCausedByTeamIsSame() {
        //Given
        member.joinTeam(team);

        //When
        member.transferTeam(team);

        //Then
        assertTrue(member.getTeam() == team);
        assertTrue(team.getMembers().contains(member));
    }

    @Test
    public void testClearTeam() {

        //Given
        member.joinTeam(team);

        //When
        member.clearTeam(team);

        //Then
        assertTrue(member.getTeam() == null);
        assertTrue(!team.getMembers().contains(member));
    }

    @Test
    public void testClearTeamFailureCausedByMemberHasNotTeam() {

        //Given & When
        member.clearTeam(team);

        //Then
        assertTrue(member.getTeam() == null);
        assertTrue(!team.getMembers().contains(member));
    }

    @Test
    public void testClearTeamFailureCausedByTeamIsNotSame() {

        //Given
        member.joinTeam(team);
        Team newTeam = new Team("TeamB");


        //When
        member.clearTeam(newTeam);

        //Then
        assertTrue(member.getTeam() == team);
        assertTrue(team.getMembers().contains(member));
        assertFalse(newTeam.getMembers().contains(member));
    }
}


