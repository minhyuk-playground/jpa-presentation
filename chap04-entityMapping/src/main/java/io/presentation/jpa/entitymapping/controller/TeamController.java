package io.presentation.jpa.entitymapping.controller;

import io.presentation.jpa.entitymapping.entity.Team;
import io.presentation.jpa.entitymapping.service.TeamServiceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@RestController
@RequestMapping(value = "/teams")
public class TeamController {

    @Autowired
    private TeamServiceable teamServiceable;

    @RequestMapping(value = "/{teamId}", method = RequestMethod.GET)
    public Team getTeam(@PathVariable("teamId") Long teamId) {
        return teamServiceable.getTeamById(teamId);
    }
}
