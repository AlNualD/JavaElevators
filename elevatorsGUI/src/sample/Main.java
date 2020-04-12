package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("WTF2");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Симулятор ЛИФТОВ");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        Controller controller =loader.getController();
        primaryStage.setOnShowing(controller.getOpenEventHandler());
        System.out.println("WTF3");
        primaryStage.show();
        System.out.println("WYF4");

        primaryStage.setOnCloseRequest(controller.getCloseEventHandler());


    }


    public static void main(String[] args)
    {
        System.out.println("WTF1");
        launch(args);
    }
}
