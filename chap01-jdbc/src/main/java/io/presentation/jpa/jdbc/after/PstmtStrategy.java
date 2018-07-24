package io.presentation.jpa.jdbc.after;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
@FunctionalInterface
public interface PstmtStrategy {

    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;

}
