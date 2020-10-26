
package network;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


public class Car extends GameObjectRect{
    Bounds bound;
    public Car(Vector position,Vector velocity, Vector acceleration, double width, double height)
    {
        super(position,velocity, acceleration,width, height);
        bound = rectangle.getBoundsInLocal();
        rectangle.setId("car");
        
    }
}
