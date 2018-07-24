package io.presentation.jpa.jdbc.after;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Minhyuk Yoon on 2018. 7. 25.
 */
@FunctionalInterface
public interface RsStrategy<T> {

    T makeResult(PreparedStatement pstmt) throws SQLException;
}
