package io.presentation.jpa.entitymapping.repository.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import io.presentation.jpa.entitymapping.entity.Member;
import io.presentation.jpa.entitymapping.entity.QMember;
import io.presentation.jpa.entitymapping.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Member findOne(Long memberId) {
        return entityManager.find(Member.class, memberId);
    }

    @Override
    public List<Member> findAll() {
        return new JPAQuery(entityManager)
                .from(QMember.member)
                .list(QMember.member);
    }

    @Override
    public void remove(Member member) {
        entityManager.remove(member);
    }
}
