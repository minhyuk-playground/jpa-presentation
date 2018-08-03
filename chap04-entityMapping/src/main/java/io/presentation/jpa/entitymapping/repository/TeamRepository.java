package io.presentation.jpa.entitymapping.repository;

import io.presentation.jpa.entitymapping.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

//    Team save(Team team);
//
//    Team findOne(Long teamId);
//
//    List<Team> findAll();
//
//    void remove(Team team);

    List<Team> findByTeamName(String teamName);
}
