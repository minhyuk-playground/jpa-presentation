package io.presentation.jpa.mybatis.domain;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class DDLExecutor {

    private DataSource dataSource;

    public DDLExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void makeTable() {
        try{
            dropMemberTable();
            makeMemberTable();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dropMemberTable() throws Exception {

        Connection connection = dataSource.getConnection();
        String sql = "DROP TABLE IF EXISTS member";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    private void makeMemberTable() throws Exception {
        Connection connection = dataSource.getConnection();

        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE member(")
                .append("id varchar(255) not null primary key,")
                .append("name varchar(255),")
                .append("age int(11)")
                .append(")");

        PreparedStatement pstmt = connection.prepareStatement(builder.toString());
        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }
}
