package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private GridPane queueGridPane;
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
    @FXML
    private CheckBox autoModFlag;
    //  private ImageView firstFloorButton;


    private ElevatorsControl WorkQueue;
    private Thread elevatorsTread;
    private Thread generationTread = null;

    private void tasksGeneration(){
        if(generationTread == null) {
            generationTread = new Thread(new TasksGenerator(WorkQueue));
            generationTread.start();
        }
        else {
            if(generationTread.isAlive()){
                generationTread.stop();
            }
            else {
                generationTread.start();
            }
        }
    }

    public void autoModFlagClicked(){
        if(autoModFlag.isSelected()){
            System.out.println("FlagIsSelected");
        }
        tasksGeneration();
    }

    private void sendTasks(int task){
        if(!autoModFlag.isSelected())
        WorkQueue.addTask(task);
    }

    public void firstFloorButtonClicked(){
      //  WorkQueue.addTask(1);
        sendTasks(1);
    }

    public void secondFloorButtonClicked(){
        //WorkQueue.addTask(2);
        sendTasks(2);
    }

    public void thirdFloorButtonClicked(){
        //WorkQueue.addTask(3);
        sendTasks(3);
    }

    public void fourthFloorButtonClicked(){
        //WorkQueue.addTask(4);
        sendTasks(4);
    }

    public void fifthFloorButtonClicked(){
        //WorkQueue.addTask(5);
        sendTasks(5);
    }


    private javafx.event.EventHandler<WindowEvent> openEventHandler = new javafx.event.EventHandler<WindowEvent>(){

        @Override
        public void handle(WindowEvent windowEvent) {
            //TODO: stage opening
            Object[] labels = queueGridPane.getChildren().toArray();
            ElevatorObj[] elevatorObjs = new ElevatorObj[2];
            elevatorObjs[0] = new ElevatorObj(firstElevatorPane,firstElevator);
            elevatorObjs[1] = new ElevatorObj(secondElevatorPane,secondElevator);
            WorkQueue = new ElevatorsControl(elevatorObjs, labels);
            elevatorsTread = new Thread(WorkQueue);
            elevatorsTread.start();

            //WorkQueue = new Elevator(firstElevatorPane,secondElevatorPane,firstElevator,secondElevator);
        }
    };

    public javafx.event.EventHandler<WindowEvent> getOpenEventHandler(){
        return openEventHandler;
    }


    private  javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>(){

        @Override
        public void handle(WindowEvent windowEvent) {
            //WorkQueue.stopTreads();
            elevatorsTread.stop();
        }
    };

    public  javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){
        return closeEventHandler;
    }



}
