package io.presentation.jpa.entitymapping.test.repository;

import io.presentation.jpa.entitymapping.config.ServiceConfig;
import io.presentation.jpa.entitymapping.entity.Address;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
@WebAppConfiguration
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Address address;
    private Member member;

    @Before
    public void setUp() {
        this.address = new Address("city", "street", "123-123");
        this.member = new Member("member01", 20, address);
    }

    @Test
    public void testDI() {
        assertTrue(memberRepository != null);
    }

    @Test
    public void testSave() {
        //Given
        Address address = new Address("city", "street", "123-123");
        Member member = new Member("member01", 20, address);

        //When
        memberRepository.save(member);
        Member findMember = memberRepository.findOne(member.getMemberId());

        //Then
        assertTrue(findMember != null);
        assertEquals(findMember, member);
    }

    @Test
    public void findAll() {
        //Given
        int size = 100;
        IntStream.range(0, size)
                .forEach(i -> {
                    Address address = new Address("city" + i, "street" + i, "123-" + i);
                    Member member = new Member("member" + i, i, address);
                    memberRepository.save(member);
                });

        //When
        List<Member> members = memberRepository.findAll();

        //Then
        assertTrue(members.size() == size);
    }

    @Test
    public void testRemove() {
        //Given
        memberRepository.save(member);

        //When
        memberRepository.remove(member);

        //Then
        assertTrue(memberRepository.findOne(member.getMemberId()) == null);
    }
}
