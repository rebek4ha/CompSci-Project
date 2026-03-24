package com.trial;

//import java.io.IOException;
//import javafx.fxml.FXML;

//public class Sprite {

    //@FXML
    //private void switchToSecondary() throws IOException {
        //App.setRoot("secondary");
    //}
//}
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SpriteMovement extends Application {
    
    @Override
    public void start(Stage stage) {
        // Create canvas (800x600 pixels)
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Load sprite image
        Image sprite = new Image("sprite.png");
        
        // Draw sprite at position (100, 150)
        gc.drawImage(sprite, 100, 150);
        
        // Set up window
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sprite Display");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

