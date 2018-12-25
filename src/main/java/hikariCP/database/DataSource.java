package hikariCP.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final Logger logger = LoggerFactory.getLogger(DataSource.class);

    private static HikariDataSource ds;
    private static HikariConfig config;
//    private static MetricRegistry metricRegistry = new MetricRegistry();
//    private static MetricsTrackerFactory metricsTrackerFactory = new CodahaleMetricsTrackerFactory(metricRegistry);
//    private static HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

    static {
        config = new HikariConfig("src/main/resources/datasource.properties");
        config.setRegisterMbeans(true);
      /*  config.setMetricRegistry(metricRegistry);
        config.setHealthCheckRegistry(healthCheckRegistry);
        */
        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}