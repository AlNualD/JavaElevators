package sample;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class ElevatorsControl implements Runnable {
    private ElevatorObj[] elevators;
    private LinkedList<Integer> queue;
    public ElevatorsControl(ElevatorObj[] elevators){
        this.elevators = elevators;
        queue = new LinkedList<>();
    }

    private ElevatorObj findClosest(int taskFloor){
        ElevatorObj answ = elevators[0];
        int dist = abs(answ.getFloor() - taskFloor);
        for (int i = 1; i < elevators.length; i++) {
            if(abs(elevators[i].getFloor() - taskFloor) < dist){
                dist = abs(elevators[i].getFloor() - taskFloor);
                answ = elevators[i];
            }
        }
        return answ;
    }

    public void addTask(int floor){
        queue.addLast(floor);
    }

    @Override
    public void run() {
        int taskFloor;
        while (true){
            for (ElevatorObj elevator : elevators) {
                if(!elevator.taskCheck()){
                    if(!queue.isEmpty()){
                        elevator.setNextFloor(queue.removeFirst());
                    }

                }
                elevator.moveToNextFloor();

            }

        }

    }
}
