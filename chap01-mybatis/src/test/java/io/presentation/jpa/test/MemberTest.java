package io.presentation.jpa.test;

import io.presentation.jpa.mybatis.config.RootConfig;
import io.presentation.jpa.mybatis.domain.DDLExecutor;
import io.presentation.jpa.mybatis.domain.Member;
import io.presentation.jpa.mybatis.mapper.AnnotationMemberMapper;
import io.presentation.jpa.mybatis.mapper.XmlMemberMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class MemberTest {

    @Autowired
    private AnnotationMemberMapper memberMapper;

    @Autowired
    private XmlMemberMapper xmlMemberMapper;

    @Autowired
    private DataSource dataSource;

    private DDLExecutor executor;

    @Before
    public void doBeforeTask() {
        executor = new DDLExecutor(dataSource);
        executor.makeTable();

        //xmlMemberMapper.deleteAll();
        memberMapper.deleteAll();
    }

    @Test
    public void testInsert() {
        //Given
        Member member = new Member();
        member.setId("id");
        member.setName("name");
        member.setAge(27);

        //When
        memberMapper.insertMember(member);                          //xmlMemberMapper.insertMember(member);

        Member findMember = memberMapper.selectOne(member.getId());

        //Then
        assertEquals(member.getId(), findMember.getId());
        assertEquals(member.getName(), findMember.getName());
        assertEquals(member.getAge(), findMember.getAge());
    }

    @Test
    public void testSelect() {
        //Given
        String id = "id";
        String name = "name";
        int age = 27;

        insertDummy(id, name, age);

        //When
        Member member = memberMapper.selectOne(id);                //xmlMemberMapper.selectOne(id);

        //Then
        assertEquals(id, member.getId());
        assertEquals(name, member.getName());
        assertEquals(age, member.getAge());
    }

    @Test
    public void testUpdate() {
        //Given
        String newName = "newName";
        int newAge = 10;
        Member member = new Member("id", "name", 27);

        memberMapper.insertMember(member);

        member.setName(newName);
        member.setAge(newAge);

        //When
        memberMapper.updateMember(member);                          //xmlMemberMapper.updateMember(member);
        Member findMember = memberMapper.selectOne(member.getId()); //xmlMemberMapper.selectOne(member.getId());

        //Then
        assertEquals(newName, findMember.getName());
        assertEquals(newAge, findMember.getAge());
    }

    private void insertDummy(String id, String name, int age) {
        Member member = new Member(id, name, age);
        memberMapper.insertMember(member);                          //xmlMemberMapper.insertMember(member);
    }
}
