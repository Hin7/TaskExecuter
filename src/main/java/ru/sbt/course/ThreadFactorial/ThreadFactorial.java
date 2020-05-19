package ru.sbt.course.ThreadFactorial;

/**
 * Многопоточный подсчет факториала.
 * Лекция 11. Multithreading. Part 1.
 * Домашнее задание от Позднякова Александра.
 *
 * @author Hin7
 * @version 1.0 19/05/2020
 */

import ru.sbt.course.TaskExecuter.FactorialCounter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ThreadFactorial {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(Paths.get("input.txt"), "UTF-8");
        while (in.hasNextInt()){
            int val = in.nextInt();
            new Thread(new FactorialCounter(val)).start();
        }
    }
}
