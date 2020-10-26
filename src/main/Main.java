package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Ventana.fxml"));
        primaryStage.setTitle("Resultados Elecciones");
        primaryStage.setScene(new Scene(root, 748, 623));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
