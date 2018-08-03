package io.presentation.jpa.entitymapping.repository;

import io.presentation.jpa.entitymapping.entity.Member;

import java.util.List;

/**
 * Created By Minhyuk Yoon on 2018. 8. 2.
 */
public interface MemberRepository  {

    Member save(Member member);

    Member findOne(Long memberId);

    List<Member> findAll();

    void remove(Member member);
}
