package io.presentation.jpa.jdbc.after;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
public class JdbcContext {

    private final String driverClassName;
    private final String user;
    private final String password;
    private final String url;


    public JdbcContext() {
        this.driverClassName = "com.mysql.jdbc.Driver";
        this.user = "root";
        this.password = "root";
        this.url = "jdbc:mysql://127.0.0.1:3306/TestDB?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8";
    }

    /**
     * INSERT, UPDATE, DELETE
     **/
    public int executeUpdate(PstmtStrategy pstmtStrategy) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            pstmt = pstmtStrategy.makePreparedStatement(conn);

            int affected = pstmt.executeUpdate();

            return affected;

        } catch (Exception e) {
            e.printStackTrace();
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
     * SELECT
     **/
    public <T> T executeQuery(PstmtStrategy pstmtStrategy, RsStrategy<T> rsStrategy) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);

            pstmt = pstmtStrategy.makePreparedStatement(conn);

            T t = rsStrategy.makeResult(pstmt);

            return t;

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
