package io.presentation.jpa.test;

import io.presentation.jpa.domain.Address;
import io.presentation.jpa.domain.Member;
import io.presentation.jpa.domain.Team;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.stream.IntStream;

/**
 * Created By Minhyuk Yoon on 2018. 7. 29.
 */
public class TempTest {

    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void doBeforeTask() {
        this.factory = Persistence.createEntityManagerFactory("chap02");
        this.entityManager = factory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Test
    public void testGetMembers() {

        try{
            transaction.begin();
            Team team = new Team("teamA");

            entityManager.persist(team);

            IntStream.range(0,100)
                    .forEach(i-> {
                        Member member = new Member("id" +i, "member"+i,i, new Address("city", "street", "123-123"));
                        member.setTeam(team);
                        entityManager.persist(member);
                    });


            entityManager.flush();
            entityManager.clear();

            Team findTeam = entityManager.find(Team.class, team.getTeamId());

            for (Member member : findTeam.getMembers()) {
                System.out.println(member.getName());
            }
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            entityManager.close();
        }

        factory.close();
    }
}
