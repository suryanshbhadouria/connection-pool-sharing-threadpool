import database.DataBaseUtility;
import factory.CustomThreadFactoryBuilder;
import thread.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author suryansh
 */
public class Demo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
                .setNamePrefix("DemoPool-Thread")
                .setDaemon(false)
                .setPriority(Thread.MAX_PRIORITY)
                .setDataSource(DataBaseUtility.getDataSource())
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(3,
                customThreadfactory);

        // Create three simple tasks with 1000 ms sleep time
        Task task1 = new Task(1000);
        Task task2 = new Task(1000);
        Task task3 = new Task(1000);

        // Execute three simple tasks with 1000 ms sleep time
        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);

    }

}