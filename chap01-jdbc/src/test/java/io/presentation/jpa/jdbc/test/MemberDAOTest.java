package io.presentation.jpa.jdbc.test;

import io.presentation.jpa.jdbc.DDLExecutor;
import io.presentation.jpa.jdbc.before.MemberDAO;
import io.presentation.jpa.jdbc.domain.Member;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class MemberDAOTest {

    private MemberDAO memberDAO;
    private DDLExecutor executor;

    @Before
    public void doBeforeTask() {
        this.executor = new DDLExecutor();
        this.memberDAO = new MemberDAO();

        executeDDL();
        deleteAll();
    }

    @Test
    public void testInsert() {

        //Given
        Member member = new Member();
        member.setId("id");
        member.setName("name");
        member.setAge(12);

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

        member = memberDAO.selectOne(member.getId());

        //Then
        assertEquals(newName, member.getName());
        assertTrue(newAge == member.getAge());
    }

    @Test
    public void testDelete() {
        //Given
        Member member = insertDummy();
        String id = member.getId();

        //When
        memberDAO.deleteMember(id);

        Member findMember = memberDAO.selectOne(id);

        //Then
        assertTrue(findMember == null);
    }

    private Member insertDummy() {
        Member member = new Member();
        member.setId("id");
        member.setName("name");
        member.setAge(27);
        memberDAO.insertMember(member);

        return member;
    }

    private void executeDDL() {
        executor.makeTable();
    }

    private void deleteAll() {
        memberDAO.deleteAll();
    }
}
