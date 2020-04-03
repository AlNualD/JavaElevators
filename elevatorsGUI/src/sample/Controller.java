package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.WindowEvent;

import java.util.LinkedList;

public class Controller {
    @FXML
    private GridPane firstElevatorPane;
    @FXML
    private GridPane secondElevatorPane;
    @FXML
    private Label firstElevator;
    @FXML
    private Label secondElevator;
    @FXML
    private Button firstFloorButton;
    @FXML
    private Button secondFloorButton;
    @FXML
    private Button thirdFloorButton;
    @FXML
    private Button fourthFloorButton;
    @FXML
    private Button fifthFloorButton;
    //  private ImageView firstFloorButton;

    public void firstFloorButtonClicked(){
        WorkQueue.addTask(1);
        

//
//       System.out.println(elevators.getRowIndex(secondElevator));
//       elevators.setRowIndex(secondElevator,4);
    }

    public void secondFloorButtonClicked(){
        WorkQueue.addTask(2);

    }

    public void thirdFloorButtonClicked(){
        WorkQueue.addTask(3);

    }

    public void fourthFloorButtonClicked(){
        WorkQueue.addTask(4);

    }

    public void fifthFloorButtonClicked(){
        WorkQueue.addTask(5);

    }


    private Elevator WorkQueue;


    private javafx.event.EventHandler<WindowEvent> openEventHandler = new javafx.event.EventHandler<WindowEvent>(){

        @Override
        public void handle(WindowEvent windowEvent) {
            //TODO: stage opening

            WorkQueue = new Elevator(firstElevatorPane,secondElevatorPane,firstElevator,secondElevator);
        }
    };

    public javafx.event.EventHandler<WindowEvent> getOpenEventHandler(){
        return openEventHandler;
    }


    private  javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>(){

        @Override
        public void handle(WindowEvent windowEvent) {
            WorkQueue.stopTreads();
        }
    };

    public  javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){
        return closeEventHandler;
    }



}
