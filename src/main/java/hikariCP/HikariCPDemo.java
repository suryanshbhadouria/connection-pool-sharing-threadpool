package hikariCP;

import hikariCP.database.DataSource;
import hikariCP.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HikariCPDemo {

    private static final Logger logger = LoggerFactory.getLogger(HikariCPDemo.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("starting app");
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        logger.trace("Number of records fetched: {}", fetchData().size());
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.submit(t);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);


        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                DataSource.emitMetrics();
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 0L;
        long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    public static List<Employee> fetchData() throws SQLException {
        String SQL_QUERY = "select * from emp";
        List<Employee> employees = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY);
             ResultSet rs = pst.executeQuery();) {
            employees = new ArrayList<>();
            Employee employee;
            while (rs.next()) {
                employee = new Employee();
                employee.setEmpNo(rs.getInt("empno"));
                employee.setEname(rs.getString("ename"));
                employee.setJob(rs.getString("job"));
                employee.setMgr(rs.getInt("mgr"));
                employee.setHiredate(rs.getDate("hiredate"));
                employee.setSal(rs.getInt("sal"));
                employee.setComm(rs.getInt("comm"));
                employee.setDeptno(rs.getInt("deptno"));
                employees.add(employee);
            }
        }
        return employees;
    }
}
