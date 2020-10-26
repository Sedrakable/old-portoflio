
package network;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static network.Network.getStage;


public class FXMLMenuController implements Initializable {
    private static Stage guiStage2;
    
    public static Stage getStage2() {
        return guiStage2;
    }
    
    public boolean full_screen = true;
    
    @FXML
    public AnchorPane pane;
    
    @FXML
    public Button exit;

    @FXML
    public Button full;

    @FXML
    public Button minimise;
    
    @FXML
    public Button startButton;
    
    @FXML
    public ImageView title;
    
    @FXML
    public Label titleLabel;
    
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private void Full(ActionEvent event) {
        getStage().setMaximized(full_screen);
        full_screen ^= true;
        if (full_screen) {
            pane.setPrefSize(960, 540);
        } else if (!full_screen) {
            pane.setPrefSize(960 * 2, 540 * 2);
        }

    }

    @FXML
    private void Exit(ActionEvent event) {
        getStage().close();

    }

    @FXML
    private void Minimise(ActionEvent event) {
        getStage().setIconified(true);
    }
    
    @FXML
    private void Go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("CSS.css").toExternalForm());
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        
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
                myStage.setX(event.getScreenX() - xOffset);
                myStage.setY(event.getScreenY() - yOffset);
            }
        });
        
        //myStage.getIcons().s
        myStage.hide();
        guiStage2 = myStage;
        
        myStage.setFullScreen(true);
        myStage.setWidth(1920);
        myStage.setHeight(1080);
        
        myStage.setScene(scene);
        //myStage.initStyle(StageStyle.UNDECORATED);
        myStage.show();       
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setId("mainMenu");
        title.setId("title");
        startButton.setId("startButton");
        titleLabel.setId("titleLabel");
    }    
    
}
