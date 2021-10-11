package com.front.end;

import com.front.end.APICALL.ApiInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/1234.fxml"));
        Scene scene= new Scene(root, 900, 600);
        stage.setTitle("2C Enterprise");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/ico.jpg")));

        stage.show();
    }
}
