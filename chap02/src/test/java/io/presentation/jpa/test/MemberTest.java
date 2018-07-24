package io.presentation.jpa.test;

import io.presentation.jpa.domain.Address;
import io.presentation.jpa.domain.Member;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Minhyuk Yoon on 2018. 7. 24.
 */
public class MemberTest {

    private EntityManagerFactory managerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void doBeforeTask() {
        this.managerFactory = Persistence.createEntityManagerFactory("chap02");
        this.entityManager = managerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Test
    public void testPersistence() {
        wrapTryCatch(() -> {

            //Given
            String id = "id";
            String name = "makeus";
            int age = 27;

            String city = "seoul";
            String street = "street";
            String zipCode = "123-123";

            Address address = new Address(city, street, zipCode);

            Member member = new Member(id, name, age, address);

            //When
            entityManager.persist(member);
            Member findMember = entityManager.find(Member.class, id);

            //Then
            assertTrue(findMember != null);
            assertEquals(member, findMember);
        });
    }

    @Test
    public void testUpdate() {
        wrapTryCatch(() -> {

            //Given
            String id = "id";
            String name = "name";
            int age = 27;
            Address address = new Address("seoul", "street", "123-123");

            Member member = insertDummy(id, name, age, address);

            Address newAddress = new Address("seoul", "street02", "000-000");

            //When
            member.changeAddress(newAddress);

            Member findMember = entityManager.find(Member.class, id);

            //Then
            assertEquals(findMember.getAddress().getCity(), newAddress.getCity());
            assertEquals(findMember.getAddress().getZipCode(), newAddress.getZipCode());
            assertEquals(findMember.getAddress().getStreet(), newAddress.getStreet());
            System.out.println("AFTER UPDATE : " + findMember);
        });
    }

    @Test
    public void testFindOne() {

        wrapTryCatch(() -> {

            //Given
            String id = "id";
            Address address = new Address("city", "street", "zipCode");
            Member member = insertDummy(id, "nname", 27, address);

            //When
            Member findMember = entityManager.find(Member.class, id);

            //Then
            assertTrue(findMember != null);
            assertEquals(member, findMember);

        });
    }

    @Test
    public void testFindAddress() {

        wrapTryCatch(() -> {

            //Given
            String id = "id";
            Address address = new Address("city", "street", "zipCode");
            insertDummy(id, "nname", 27, address);

            Member findMember = entityManager.find(Member.class, id);

            //When
            Address findAddress = findMember.getAddress();

            //Then
            assertEquals(findAddress.getZipCode(), address.getZipCode());
            assertEquals(findAddress.getStreet(), address.getStreet());
            assertEquals(findAddress.getCity(), address.getCity());

            System.out.println("FIND ADDRESS : " + findAddress);
        });
    }

    @Test
    public void testFindAll() {

        wrapTryCatch(() -> {

            //Given
            int size = 100;
            insertDummies(size);

            //When
            List<Member> members = entityManager.createQuery("SELECT m FROM Member m", Member.class)
                    .getResultList();

            //Then
            assertTrue(members.size() == size);
        });
    }

    @Test
    public void remove() {

        wrapTryCatch(() -> {

            //Given
            String id = "id";
            Address address = new Address("city", "street", "zipCode");
            Member member = insertDummy(id, "nname", 27, address);

            //When
            entityManager.remove(member);
            member = entityManager.find(Member.class, id);

            //Then
            assertTrue(member == null);
        });
    }


    private Member insertDummy(String id, String name, int age, Address address) {
        Member member = new Member(id, name, age, address);
        entityManager.persist(member);
        return entityManager.find(Member.class, id);
    }

    private void insertDummies(int size) {
        IntStream.range(0, size)
                .forEach(i -> {
                    Address address = new Address("city" + (i % 10), "street" + (i % 10), "123-" + i);
                    Member member = insertDummy("id" + i, "name" + i, i, address);

                    entityManager.persist(member);
                });
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
        managerFactory.close();
    }
}
