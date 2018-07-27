package io.presentation.jpa.chap01.test;

import io.presentation.jpa.chap01.Member;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Minhyuk Yoon on 2018. 7. 23
 */
public class MemberTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void doBeforeTask() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("chap01");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Test
    public void testPersist() {

        try {
            transaction.begin();

            //Given
            String id = "id";
            String name = "name";
            int age = 10;

            //비영속 상태
            Member member = new Member(id, name, age);

            //When
            entityManager.persist(member);  // 영속상태 + 1차 캐시 저장

            Member findMember = entityManager.find(Member.class, member.getId()); // SELECT 1건

            //Then
            assertTrue(findMember != null);
            assertEquals(member, findMember);
            assertTrue(member.getName().equals(findMember.getName()));
            assertTrue(member.getAge() == findMember.getAge());

            transaction.commit();   //쓰기 지연 SQL 저장소 Query DB 반영 (사실 commit 바로 직전에 flush() 메소드가 호출되어 DB와 동기화됌)
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    @Test
    public void testUpdate() {
        wrapTryCatch(() -> {

            //Given
            String id = "id";
            String newName = "New Name";
            int newAge = 100;

            Member member = insertDummy(id, "hello", 20);

            //When
            member.changeName(newName);
            member.changeAge(newAge);

            Member findMember = entityManager.find(Member.class, id);

            //Then
            assertTrue(findMember.getName().equals(newName));
            assertTrue(findMember.getAge() == newAge);
        });
    }

    @Test
    public void testSelectAll() {
        wrapTryCatch(() -> {

            //Given
            int size = 100;
            for (int i = 0; i < size; i++) {
                Member member = new Member("" + i, "name" + i, i);
                entityManager.persist(member);
            }

            //When
            List<Member> members = entityManager.createQuery("select m from Member m", Member.class)
                    .getResultList();

            //Then
            assertTrue(members.size() == size);
        });
    }

    private Member insertDummy(String id, String name, int age) {
        Member member = new Member(id, name, age);
        entityManager.persist(member);
        return entityManager.find(Member.class, id);
    }

    private interface Command {
        void execute();
    }

    private void wrapTryCatch(Command command) {
        try {
            transaction.begin();

            command.execute();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
