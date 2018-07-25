package io.presentation.jpa.mybatis.mapper;

import io.presentation.jpa.mybatis.domain.Member;

import java.util.List;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public interface XmlMemberMapper {

    void insertMember(Member member);

    Member selectOne(String id);

    List<Member> selectAll();

    void updateMember(Member member);

    void deleteMember(String id);

    void deleteAll();
}
