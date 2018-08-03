package io.presentation.jpa.entitymapping.test;

import io.presentation.jpa.entitymapping.config.ServiceConfig;
import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.entity.Team;
import io.presentation.jpa.entitymapping.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@WebAppConfiguration
public class InsertDummy {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void insertDummyTeamAndMembers() {

        Team team = new Team("TeamA");
        Address address = new Address("city", "street", "123-123");

        for (int i = 0; i < 100; i++) {
            Member member = new Member("member"+i, i, address);
            team.addMember(member);
        }

        teamRepository.save(team);
    }

}
