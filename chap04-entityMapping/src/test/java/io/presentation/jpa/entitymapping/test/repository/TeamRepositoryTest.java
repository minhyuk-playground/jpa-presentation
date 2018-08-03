package io.presentation.jpa.entitymapping.test.repository;

import io.presentation.jpa.entitymapping.config.ServiceConfig;
import io.presentation.jpa.entitymapping.entity.Team;
import io.presentation.jpa.entitymapping.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@WebAppConfiguration
@Transactional
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testDI() {
        assertTrue(teamRepository != null);
    }

    @Test
    public void testSave() {
        //Given
        Team team = new Team("teamA");

        //When
        teamRepository.save(team);
        Team findTeam = teamRepository.findOne(team.getTeamId());

        //Then
        assertTrue(findTeam != null);
        assertEquals(findTeam, team);
    }

    @Test
    public void testFindAll() {
        //Given
        int size = 100;
        IntStream.range(0, size)
                .forEach(i -> {
                    Team team = new Team("Team" + i);
                    teamRepository.save(team);
                });

        //When
        List<Team> teams = teamRepository.findAll();

        //Then
        assertTrue(teams.size() == size);
    }

    @Test
    public void testRemove() {
        //Given
        Team team = new Team("TeamA");
        teamRepository.save(team);

        //When
        teamRepository.delete(team);

        //Then
        assertTrue(teamRepository.findOne(team.getTeamId()) == null);
    }

    @Test
    public void testFindByName() {
        //Given
        Team team = new Team("TeamA");
        teamRepository.save(team);

        //When
        List<Team> findTeams = teamRepository.findByTeamName(team.getTeamName());

        //Then
        assertTrue(findTeams.get(0) == team);
    }
}
