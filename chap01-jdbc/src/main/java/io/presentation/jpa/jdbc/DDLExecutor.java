package io.presentation.jpa.jdbc;

import java.sql.*;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class DDLExecutor {

    private final String driverClassName;
    private final String user;
    private final String password;
    private final String url;

    private PreparedStatement pstmt;

    public DDLExecutor() {
        this.driverClassName = "com.mysql.jdbc.Driver";
        this.user = "root";
        this.password = "root";
        this.url = "jdbc:mysql://127.0.0.1:3306/TestDB?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeTable() {
        try {
            dropMemberTable();
            makeMemberTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dropMemberTable() throws Exception {

        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "DROP TABLE IF EXISTS member";

        pstmt = connection.prepareStatement(sql);
        pstmt.executeUpdate();

        pstmt.close();
    }

    private void makeMemberTable() throws Exception {

        Connection connection = DriverManager.getConnection(url, user, password);

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE member(")
                .append("id varchar(255) not null primary key,")
                .append("name varchar(255),")
                .append("age int(11)")
                .append(")");

        pstmt = connection.prepareStatement(builder.toString());
        pstmt.executeUpdate();
        pstmt.close();
    }
}
