package org.profilingsampleapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.profilingsampleapp.runner.ObjectCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static final int OBJECT_COUNT = 10000;
    private static final int THREAD_MULTIPLIER = 2;

    public static void main(String[] args) throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        int threads = cores * THREAD_MULTIPLIER;
        System.out.println("Found " + cores + " cores");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - create " + OBJECT_COUNT + " objects in N threads, where N = coresCount*2 (now is " + threads + ")");
            System.out.print("Please make your choice: ");
            switch (scanner.nextInt()) {
                case 1:
                    ObjectCreator.reset();
                    List<Thread> workingThreads = Stream.generate(() -> new Thread(new ObjectCreator(OBJECT_COUNT / threads)))
                            .limit(threads)
                            .collect(Collectors.toList());
                    workingThreads.forEach(Thread::start);
                    for (Thread thread : workingThreads) {
                        thread.join();
                    }
                    System.out.println("Done.");
                    break;
                case 2:
                    // Perform "encrypt number" case.
                    break;
                case 3:
                    // Perform "decrypt number" case.
                    break;
                case 4:
                    // Perform "quit" case.
                    break;
                default:
                    return;
            }
        }
    }
}
