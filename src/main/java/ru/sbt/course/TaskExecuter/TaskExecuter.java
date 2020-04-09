/**
 * Домашенне задание по 11 уроку СБТ
 * Пулл потоков. Класс проверки.
 *
 * @author: HIN7
 * @version: 1.1 09/04/2020
 */

package ru.sbt.course.TaskExecuter;

import java.util.Scanner;

public class TaskExecuter {
    public static void main(String[] args) {

        System.out.println("Тест пула потоков с фиксированной количеством потоков.");
        ThreadPool tPool = new FixedThreadPool(100);

        for (int i = 1; i < 1000; i++){
            tPool.execute(new SummCounter(i));
        }
        tPool.start();

        Scanner in = new Scanner(System.in);

        do{
            System.out.println("Для продолжения введите continue");
        }
        while (!"continue".equals(in.nextLine()));
        tPool.stop();

        System.out.println("===================================================");
        System.out.println("Тест пула потоков с переменным количеством потоков.");
        tPool = new ScalableThreadPool(10, 300);
        for (int i = 1; i<1000; i++)
            tPool.execute(new SummCounter(i));
        tPool.start();
        for (int i = 1; i<1000; i++)
            tPool.execute(new SummCounter(i));

        do{
            System.out.println("Для выхода введите exit");
        }
        while (!"exit".equals(in.nextLine()));
        tPool.stop();
    }
}
