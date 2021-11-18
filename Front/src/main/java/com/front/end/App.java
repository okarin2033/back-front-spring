package com.front.end;

import com.front.end.APICALL.ApiInterface;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login_page.fxml")));
        Scene scene= new Scene(login, 500, 500);
        stage.setTitle("2C Enterprise");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/ico.jpg"))));

        stage.show();
    }
}
