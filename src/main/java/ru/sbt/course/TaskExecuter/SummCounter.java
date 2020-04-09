/**
 * Тестовая задача - класс, вычисляющий сумму всех чисел от 1 до value.
 * Выводит результат на экран с именем потока, в котором выполнялась задача.
 */

package ru.sbt.course.TaskExecuter;

public class SummCounter implements Runnable {
    private int value;

    public SummCounter(int val){
        value = val;
    }

    @Override
    public void run() {
        int result = 0;
        for (int i = 1; i <= value; i++) {
            result += i;
        }
        System.out.println("Summ " + value + " = " + result + " " + Thread.currentThread().getName());
    }
}
