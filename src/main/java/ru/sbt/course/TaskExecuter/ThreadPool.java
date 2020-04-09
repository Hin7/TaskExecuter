/**
 * Интерфейс пула потоков.
 *
 * @author: HIN7
 * @version: 1.1 09/04/2020
 */

package ru.sbt.course.TaskExecuter;

public interface ThreadPool {
    void start(); // запускает потоки
    void execute(Runnable task); //добавляет поток в очередь
    void stop(); //останавливает потоки
}
