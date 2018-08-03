package io.presentation.jpa.entitymapping.test.service;

import io.presentation.jpa.entitymapping.config.ServiceConfig;
import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.entity.Team;
import io.presentation.jpa.entitymapping.repository.TeamRepository;
import io.presentation.jpa.entitymapping.service.TeamServiceable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@WebAppConfiguration
public class TeamServiceTest {

    @Autowired
    private TeamServiceable teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testDI() {
        assertTrue(teamService != null);
        assertTrue(teamRepository != null);
    }

    @Test
    public void testGetTeamById() {

        //Given
        Team team = new Team("TeamA");
        Member member = new Member("Member01", 20, new Address("city","street", "123-123"));
        team.addMember(member);

        teamRepository.save(team);

        //When
        Team findTeam = teamService.getTeamById(team.getTeamId());

        //Then
        assertTrue(findTeam != null);
        assertTrue(findTeam.getTeamName().equals(team.getTeamName()));
        assertTrue(findTeam.getMembers().size() == 1);

    }
}
