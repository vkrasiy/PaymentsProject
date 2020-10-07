package org.payments.repository;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBRepository {
    protected Connection connection;

    public DBRepository()
    {
        try
        {

            connection = DBManager.getInstance().getConnection();;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}


