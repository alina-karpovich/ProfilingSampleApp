package org.profilingsampleapp.runner;

import org.profilingsampleapp.Main;
import org.profilingsampleapp.model.TestObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectCreator implements Runnable {
    private static final double STATUS_UPDATE_PERCENT = 0.05;
    private static AtomicInteger creationCount = new AtomicInteger();
    private static AtomicInteger previousUpdate = new AtomicInteger();

    private int numberOfObjects;
    private final Random randomizer = new Random();
    private List<TestObject> createdObjects = new ArrayList<>();

    public ObjectCreator(final int numberOfObjects){
        this.numberOfObjects = numberOfObjects;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfObjects; i++) {
            createdObjects.add(new TestObject(randomizer.nextInt()));
            double completed = ((double)(creationCount.incrementAndGet() - previousUpdate.get())) / Main.OBJECT_COUNT;
            if (completed > STATUS_UPDATE_PERCENT) {
                previousUpdate.getAndSet(creationCount.get());
                System.out.println(Thread.currentThread() + ": Created " + creationCount + " of " + Main.OBJECT_COUNT);
            }
        }
        System.out.println(Thread.currentThread() + ": Finished with " + createdObjects.size() + " objects");
    }

    public static void reset() {
        creationCount.set(0);
        previousUpdate.set(0);
    }

}
