package io.presentation.jpa.jdbc.after;

import io.presentation.jpa.jdbc.domain.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class RMemberDAO {

    private JdbcContext jdbcContext;

    public RMemberDAO(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    /**
     * INSERT
     **/
    public int insertMember(Member member) {

        return jdbcContext.executeUpdate(connection -> {

            String sql = "INSERT INTO member(id, `name`, age) VALUES(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getName());
            preparedStatement.setInt(3, member.getAge());

            return preparedStatement;
        });
    }

    /**
     * SELECT ONE
     **/
    public Member selectOne(String id) {

        return jdbcContext.executeQuery(connection -> {
                    String sql = "SELECT * FROM member WHERE id=?";
]
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, id);

                    return preparedStatement;
                },
                pstmt -> {
                    ResultSet rs = pstmt.executeQuery();

                    Member member = null;
                    while (rs.next()) {
                        member = new Member();
                        member.setId(rs.getString("id"));
                        member.setName(rs.getString("name"));
                        member.setAge(rs.getInt("age"));
                    }
                    return member;
                });
    }

    /**
     * SELECT ALL
     **/
    public List<Member> selectAll() {

        return jdbcContext.executeQuery(
                connection -> connection.prepareStatement("SELECT * FROM member"),
                pstmt -> {
                    ResultSet rs = pstmt.executeQuery();

                    ArrayList<Member> members = null;

                    while (rs.next()) {
                        members = new ArrayList<>();
                        Member member = new Member();
                        member.setId(rs.getString("id"));
                        member.setName(rs.getString("name"));
                        member.setAge(rs.getInt("age"));

                        members.add(member);
                    }

                    return members;
                });
    }

    /**
     * UPDATE
     **/
    public int updateMember(Member member) {

        return jdbcContext.executeUpdate(connection -> {

            String sql = "UPDATE member SET name=?, age=? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, member.getName());
            preparedStatement.setInt(2, member.getAge());
            preparedStatement.setString(3, member.getId());

            return preparedStatement;
        });
    }

    /**
     * DELETE
     **/
    public int deleteMember(String id) {
        return jdbcContext.executeUpdate(connection -> {

            String sql = "DELETE FROM member WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            return preparedStatement;
        });
    }

    public int deleteAll() {
        return jdbcContext.executeUpdate(connection -> {
            String sql = "DELETE FROM member";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        });
    }
}
