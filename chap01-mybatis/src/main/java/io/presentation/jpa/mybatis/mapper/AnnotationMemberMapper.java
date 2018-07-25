package io.presentation.jpa.mybatis.mapper;

import io.presentation.jpa.mybatis.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public interface AnnotationMemberMapper {

    @Insert("INSERT INTO member(id, name, age) VALUES(#{id}, #{name}, #{age})")
    void insertMember(Member member);

    @Select("SELECT * FROM member WHERE id=#{id}")
    Member selectOne(String id);

    @Select("SELECT * FROM member")
    List<Member> selectAll();

    @Update("UPDATE member SET name=#{name}, age=#{age} WHERE id=#{id}")
    void updateMember(Member member);

    @Delete("DELETE FROM member where id=#{id}")
    void deleteMember(String id);

    @Delete("DELETE FROM member")
    void deleteAll();
}
