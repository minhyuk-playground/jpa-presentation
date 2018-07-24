package io.presentation.jpa.jdbc.test;

import io.presentation.jpa.jdbc.DDLExecutor;
import io.presentation.jpa.jdbc.after.JdbcContext;
import io.presentation.jpa.jdbc.after.RMemberDAO;
import io.presentation.jpa.jdbc.domain.Member;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class RMemberDAOTest {

    private DDLExecutor executor;
    private RMemberDAO memberDAO;
    private JdbcContext jdbcContext;

    @Before
    public void doBeforeTask() {
        this.executor = new DDLExecutor();
        this.jdbcContext = new JdbcContext();
        this.memberDAO = new RMemberDAO(jdbcContext);

        generateTable();
        deleteAll();
    }

    @Test
    public void testInsert() {

        //Given
        Member member = new Member();
        member.setId("id");
        member.setName("name");
        member.setAge(27);

        //When
        memberDAO.insertMember(member);

        Member findMember = memberDAO.selectOne(member.getId());

        //Then
        assertTrue(member.getId().equals(findMember.getId()));
        assertTrue(member.getName().equals(findMember.getName()));
        assertTrue(member.getAge() == findMember.getAge());
    }

    @Test
    public void testUpdate() {

        //Given
        String newName = "newName";
        int newAge = 10;
        Member member = insertDummy();

        member.setName(newName);
        member.setAge(newAge);

        //When
        memberDAO.updateMember(member);

        Member findMember = memberDAO.selectOne(member.getId());

        //Then
        assertEquals(newName, findMember.getName());
        assertEquals(newAge, findMember.getAge());
    }

    @Test
    public void testDelete() {

        //Given
        Member member = insertDummy();

        memberDAO.insertMember(member);

        //When
        memberDAO.deleteMember(member.getId());

        //Then
        assertTrue(memberDAO.selectOne(member.getId()) == null);
    }

    private Member insertDummy() {
        Member member = new Member();
        member.setName("name");
        member.setId("id");
        member.setAge(27);

        memberDAO.insertMember(member);

        return member;
    }

    private void deleteAll() {
        memberDAO.deleteAll();
    }

    private void generateTable() {
        executor.makeTable();
    }

}
