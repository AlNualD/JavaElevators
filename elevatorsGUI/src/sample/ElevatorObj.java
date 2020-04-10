package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ElevatorObj {
    private GridPane gridPane;
    private Label label;
    private int maxWeight;
    private int weight;
    private int floor;
    private int nextFloor;
    private LinkedList<Integer> floorTasks;

    public ElevatorObj(GridPane gridPane, Label label){
        this.gridPane = gridPane;
        this.label = label;
        maxWeight = 10;
        weight = 0;
        floor = 0;
        nextFloor = 0;
        floorTasks = new LinkedList<>();
    }

    public  int getLastFloor(){
        if(floorTasks.isEmpty()){
        return nextFloor + 1;
        }
        return floorTasks.getLast();
    }

    public void followTasks(){
        if(taskCheck()){
            moveToNextFloor();
        }
        else {
            if(!floorTasks.isEmpty()){
                nextFloor = floorTasks.removeFirst();
                moveToNextFloor();
            }
        }

    }

    public synchronized void addTask(int floor){
        floorTasks.addLast(--floor);
    }

    public void moveToNextFloor(){
        if(taskCheck()){
            if(nextFloor>floor){
                movementUp();
            }
            else {
                movementDown();
            }
        }
    }

    public void movementUp(){
        floor++;
        moveElevator();
    }

    public void movementDown(){
        floor--;
        moveElevator();
    }

    private void moveElevator(){
        gridPane.setRowIndex(label,floor);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean taskCheck(){
        return !(floor == nextFloor);
    }


    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getLabel() {
        return label;
    }

    public int getFloor() {
        return floor + 1;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getNextFloor() {
        return nextFloor + 1;
    }

    public int getWeight() {
        return weight;
    }

    public void setFloor(int floor) {
        this.floor = --floor;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setNextFloor(int nextFloor) {
        this.nextFloor = --nextFloor;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
