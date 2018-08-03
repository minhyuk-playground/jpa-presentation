package io.presentation.jpa.entitymapping.service;

import io.presentation.jpa.entitymapping.entity.Team;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
public interface TeamServiceable {

    Team getTeamById(Long teamId);
}
