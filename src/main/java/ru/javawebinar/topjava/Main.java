package ru.javawebinar.topjava;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!\n\n");

        IntStream.range(-1, 5)
                .forEach(i -> System.out.println("n" + i));
    }
}
