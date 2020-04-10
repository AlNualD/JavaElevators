package sample;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class ElevatorsControl implements Runnable {
    private final ElevatorObj[] elevators;
    private final LinkedList<Integer> queue;
    public ElevatorsControl(ElevatorObj[] elevators){
        this.elevators = elevators;
        queue = new LinkedList<>();
    }

    private ElevatorObj findClosest(int taskFloor){
        System.out.println("findClosest");
        int dist = 6;
        ElevatorObj answ = null;
        System.out.println("task floor " + taskFloor);
        for (ElevatorObj elevator : elevators) {

            int cur = elevator.getLastFloor();
            System.out.println("el floor: " + cur);
            if(abs(cur - taskFloor) < dist){
                answ = elevator;
                dist = cur - taskFloor;
            }
        }
        return answ;
    }

    public synchronized void addTask(int floor){
        System.out.println("addTask" + floor);
        queue.addLast(floor);
        System.out.println("add Task " + queue.isEmpty());
    }



    @Override
    public void run() {
        System.out.println("RUNN");
        int taskFloor;
//        ElevatorObj elevator;
        while (true){
//

            for (ElevatorObj elevator : elevators) {
                synchronized (queue) {
                    if (!queue.isEmpty()) {
                        System.out.println("QIE");
                        taskFloor = queue.removeFirst();
                        findClosest(taskFloor).addTask(taskFloor);
                    }
                }
                
                elevator.followTasks();
            }

        }

    }
}
