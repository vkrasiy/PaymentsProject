package org.payments.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityExtractor<T>{
    T extractEntity(ResultSet resultSet) throws SQLException;
}
