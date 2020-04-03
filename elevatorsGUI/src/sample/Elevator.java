package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


public class Elevator {
    private int nTreads = 2;
    //private Elevator[] treads;
    private Thread[] treads;
    private LinkedList queue;

    public Elevator(GridPane firstElevatorPane, GridPane secondElevatorPane, Label firstElevator, Label secondElevator) {
        queue = new LinkedList();
        treads = new Thread[2];
        treads[0] = new Thread(new ElevatorTread(firstElevatorPane,firstElevator));
        treads[0].start();
        treads[1] = new Thread(new ElevatorTread(secondElevatorPane,secondElevator));
        treads[1].start();
//            treads = new Elevator[nTreads];
//            treads[0] = new Elevator(firstElevatorPane,firstElevator);
//            treads[1] = new Elevator(secondElevatorPane,secondElevator);


    }

    private class ElevatorTread implements Runnable {

        private GridPane elevatorPane;
        private Label elevatorObj;
        private int floorNOW;
        private int floorNEXT;

        public ElevatorTread(GridPane elevatorPane, Label elevatorObj){
            this.elevatorObj = elevatorObj;
            this.elevatorPane = elevatorPane;
            floorNOW = 0;
            //floorNOW = elevatorPane.getRowIndex(elevatorObj);
            floorNEXT = floorNOW;
        }

        public int getFloorNOW() {
            return floorNOW;
        }

        public int getFloorNEXT() {
            return floorNEXT;
        }

        public void setFloorNEXT(int floorNEXT) {
            this.floorNEXT = floorNEXT;
        }

        private int movementDown(){
            floorNOW--;
            return floorNOW;
        }

        private int movementUp(){
            floorNOW++;
            return floorNOW;
        }

        private void moveElevator(){
            elevatorPane.setRowIndex(elevatorObj,floorNOW);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            int taskFloor;
            while (true){
                synchronized (queue){
                    while (queue.isEmpty()){
                        try {
                            queue.wait();
                        }
                         catch (InterruptedException e) {
                            //e.printStackTrace();
                             System.out.println("it's fine! tread was interrupted");
                        }
                    }
                    taskFloor = (Integer) queue.removeFirst();
                }
                this.setFloorNEXT(taskFloor);

                while(floorNEXT != floorNOW){
                    if (floorNEXT > floorNOW){
                        movementUp();
                    }
                    else {
                        movementDown();
                    }
                    moveElevator();
                }

            }



        }
    }

    public void addTask(int floorNum){
        synchronized (queue){
            floorNum--;
            queue.addLast(floorNum);
            queue.notify();
        }
    }

    public  void stopTreads(){
        treads[0].interrupt();
        treads[1].interrupt();
    }
}

