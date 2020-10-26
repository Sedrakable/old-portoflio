
package network;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Network extends Application {
    
    private static Stage guiStage;
    
    public static Stage getStage() {
        return guiStage;
    }
    
    private double xOffset = 0;
    private double yOffset = 0;

    

    @Override
    public void start(Stage stage) throws Exception {    
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("CSS.css").toExternalForm());
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        
//        stage.setWidth(960);
//        stage.setHeight(540);
//        stage.setMaximized(true);
        stage.setScene(scene);
        Image logo = new Image("file:./assets/logo.png");
        stage.getIcons().add(logo);
        stage.setTitle("Traffic Simulator");
        guiStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
