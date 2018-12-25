package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import org.apache.commons.dbcp2.BasicDataSource;


public class DataBaseUtility
{
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource()
    {

        if (dataSource == null)
        {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:mysql://localhost/test");
            ds.setUsername("root");
            ds.setPassword("root@123");


            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource = ds;
        }
        return dataSource;
    }

    public static HikariConfig hikariConfig(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
        config.setUsername("root");
        config.setPassword("root@123");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return config;
    }

/*
    public static void main(String[] args) throws SQLException
    {

        try (BasicDataSource dataSource = DataBaseUtility.getDataSource();
             Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM account");)
        {
            System.out.println("The Connection Object is of Class: "+connection.getClass());
            try (ResultSet resultSet = pstmt.executeQuery();)
            {
                while (resultSet.next())
                {
                    System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
                }
            }
            catch (Exception e)
            {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }*/

}