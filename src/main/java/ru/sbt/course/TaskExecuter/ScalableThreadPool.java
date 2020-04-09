/**
 * Пулл потоков с переменным количеством потоков
 */

package ru.sbt.course.TaskExecuter;

public class ScalableThreadPool extends FixedThreadPool {
    private int minSizeTaskQueue;
    private int maxSizeTaskQueue;
    private Thread serviceThread;

    private class ServiceThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    int difference = taskQueue.size() - threads.size();
                    if (difference > 0 && threads.size() < maxSizeTaskQueue) { // надо добавить потоков
                        while (threads.size() < maxSizeTaskQueue && difference > 0) {
                            TaskThread t = new FixedThreadPool.TaskThread();
                            threads.add(t);
                            t.start();
                            difference--;
                        }
                        System.out.println("Количество потоков увеличено до " + threads.size());
                    } else if (difference < 0 && threads.size() > minSizeTaskQueue) { //надо уменьшить количество потоков
                        while (threads.size() > minSizeTaskQueue && difference < 0) {
                            Thread t = threads.get(threads.size() - 1);
                            t.interrupt();
                            t.join();
                            threads.remove(t);
                            difference++;
                        }
                        System.out.println("Количество потоков уменьшено до " + threads.size());
                    }
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }


    public ScalableThreadPool(int min, int max) {
        super(min);

        minSizeTaskQueue = min;
        maxSizeTaskQueue = max;

        serviceThread = new ServiceThread();
    }


    @Override
    public void start() {
        super.start();
        serviceThread.start();
    }

    @Override
    public void stop() {
        super.stop();
        serviceThread.interrupt();
    }
}
