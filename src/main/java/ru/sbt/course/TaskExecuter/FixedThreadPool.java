/**
 * Пулл потоков с фиксированным количеством потоков
 */
package ru.sbt.course.TaskExecuter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {

    protected ConcurrentLinkedQueue<Runnable> taskQueue;
    protected List<Thread> threads;

    protected class TaskThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    if (taskQueue.size() > 0)
                        taskQueue.poll().run();
                    sleep(10);
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }

    public FixedThreadPool(int countPool) {

        taskQueue = new ConcurrentLinkedQueue<>();
        threads = new ArrayList<>();
        for (int i = 0; i < countPool; i++)
            threads.add(new TaskThread());
    }

    @Override
    public void start() {
        for (Thread t : threads)
            t.start();
    }

    @Override
    public void execute(Runnable task) {
        taskQueue.add(task);
    }

    @Override
    public void stop() {
        for (Thread t : threads)
            t.interrupt();
    }
}
