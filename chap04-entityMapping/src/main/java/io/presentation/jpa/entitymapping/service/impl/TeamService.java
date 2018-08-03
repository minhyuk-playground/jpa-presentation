package io.presentation.jpa.entitymapping.service.impl;

import io.presentation.jpa.entitymapping.entity.Team;
import io.presentation.jpa.entitymapping.repository.TeamRepository;
import io.presentation.jpa.entitymapping.service.TeamServiceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@Service
@Transactional(readOnly = true)
public class TeamService implements TeamServiceable {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team getTeamById(Long teamId) {
        Team team = teamRepository.findOne(teamId);
//        Hibernate.initialize(team.getMembers());
        team.getMembers().get(0);
        return team;
    }
}
