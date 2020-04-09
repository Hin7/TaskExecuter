/**
 * Тестовая задача - класс, вычисляющий факториал.
 * Считает факториал, заданный в конструкторе и выводит результата на экран.
 */

package ru.sbt.course.TaskExecuter;

public class FactorialCounter implements Runnable {
    private int value;

    public FactorialCounter(int val) {
        value = val;
    }

    @Override
    public void run() {
        int result = 1;
        for (int i = 2; i <= value; i++) {
            result *= i;
        }
        System.out.println("Factorial " + value + " = " + result + " " + Thread.currentThread().getName());
    }
}
