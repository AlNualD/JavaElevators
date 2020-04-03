package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Симулятор ЛИФТОВ");
        primaryStage.setScene(new Scene(root));

        Controller controller =loader.getController();
        primaryStage.setOnShowing(controller.getOpenEventHandler());

        primaryStage.show();

        primaryStage.setOnCloseRequest(controller.getCloseEventHandler());


    }


    public static void main(String[] args) {
        launch(args);
    }
}
