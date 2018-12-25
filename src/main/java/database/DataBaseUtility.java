package database;

import org.apache.commons.dbcp2.BasicDataSource;


public class DataBaseUtility {
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {

        if (dataSource == null) {
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
}
