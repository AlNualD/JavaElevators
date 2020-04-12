package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

import static java.lang.Math.abs;

public class ElevatorsControl implements Runnable {
    private final ElevatorObj[] elevators;
    private final LinkedList<Integer> queue;
    private Object[] labels;
    public ElevatorsControl(ElevatorObj[] elevators, Object[] queuePane){
        this.elevators = elevators;
        labels = queuePane;
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
        Label l = (Label)labels[floor - 1];
        String buff = l.getText();
        buff += "|";

        String finalBuff = buff;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                l.setText(finalBuff);
            }
        });




        System.out.println("add Task " + queue.isEmpty());
    }

    public void peopleChange(){
        boolean flag = true;
        if(elevators[0].isPeople()){
                flag = false;
        }
        for (ElevatorObj elevator : elevators) {
            elevator.setPeople(flag);
        }
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
                if(!elevator.taskCheck()){
                    Label l = (Label)labels[elevator.getFloor() - 1];

//                    if(buff != ""){
//
//                    }
                }
            }

        }

    }


}
