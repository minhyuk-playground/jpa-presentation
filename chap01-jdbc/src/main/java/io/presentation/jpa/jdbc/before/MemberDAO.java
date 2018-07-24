package io.presentation.jpa.jdbc.before;

import io.presentation.jpa.jdbc.domain.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minhyuk Yoon on 2018. 7. 24.
 */
public class MemberDAO {

    private final String driverClassName;
    private final String user;
    private final String password;
    private final String url;

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public MemberDAO() {
        this.driverClassName = "com.mysql.jdbc.Driver";
        this.user = "root";
        this.password = "root";
        this.url = "jdbc:mysql://127.0.0.1:3306/TestDB?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8";
    }

    /**
     * INSERT
     **/
    public int insertMember(Member member) {

        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO member(id, `name`, age) VALUES(?,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getName());
            pstmt.setInt(3, member.getAge());

            int affected = pstmt.executeUpdate();

            return affected;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * SELECT ONE
     **/
    public Member selectOne(String id) {
        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM member WHERE id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            Member member = null;
            while (rs.next()) {
                member = new Member();
                member.setId(rs.getString(1));
                member.setName(rs.getString(2));
                member.setAge(rs.getInt(3));
            }

            return member;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * SELECT ALL
     **/
    public List<Member> selectAll() {

        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM member";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = null;

            while (rs.next()) {
                members = new ArrayList<>();
                Member member = new Member();
                member.setId(rs.getString("id"));
                member.setAge(rs.getInt("age"));
                member.setName(rs.getString("name"));

                members.add(member);
            }

            return members;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * UPDATE
     **/
    public int updateMember(Member member) {
        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "UPDATE member SET `name`=?, age=? WHERE id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getName());
            pstmt.setInt(2, member.getAge());
            pstmt.setString(3, member.getId());

            int affected = pstmt.executeUpdate();

            return affected;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * DELETE
     **/
    public int deleteMember(String id) {
        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "DELETE FROM member WHERE id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            int affected = pstmt.executeUpdate();

            return affected;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * DELETE ALL
     **/
    public int deleteAll() {
        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            String sql = "DELETE FROM member";

            pstmt = conn.prepareStatement(sql);

            int affected = pstmt.executeUpdate();

            return affected;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
