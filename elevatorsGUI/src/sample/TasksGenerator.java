package sample;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TasksGenerator implements Runnable {
    private final Random random = new Random();
    private final ElevatorsControl elevatorsControl;

    public TasksGenerator(ElevatorsControl elevatorsControl) {
        this.elevatorsControl = elevatorsControl;
    }

    @Override
    public void run() {
        while (true) {
            int rTime = random.nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(rTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int rTask = random.nextInt(4);
            System.out.println("Next: "+ rTask);
            elevatorsControl.addTask(rTask + 1);
        }
    }
}
