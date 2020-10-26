package network;

import network.Vector;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameObjectRect{
    
    protected Rectangle rectangle; // For the player
    
    private Vector position;
    private Vector velocity;
    private Vector acceleration;

    
    public GameObjectRect(Vector position,Vector velocity, Vector acceleration,double width, double height)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration; 
        
        rectangle = new Rectangle(0, 0, width, height);
        
        rectangle.setLayoutX(-0.5*width); // may need to change later to add + 1/2width
        rectangle.setLayoutY(-0.5*height);
        
        rectangle.setX(position.getX()); // may need to change later to add + 1/2width
        rectangle.setY(position.getY());
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }
    
    public GameObjectRect(Vector position, double width, double height)
    {
        this.position = position;
        
        rectangle = new Rectangle(0, 0, width, height);
        
//        rectangle.setLayoutX(-0.5*width); // may need to change later to add + 1/2width
//        rectangle.setLayoutY(-0.5*height);
        
        rectangle.setX(position.getX()); // may need to change later to add + 1/2width
        rectangle.setY(position.getY());
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }
    
    public void update(double dt)
    { 
        Vector frameAcceleration = getAcceleration().mult(dt);
        velocity = getVelocity().add(frameAcceleration);
        position = getPosition().add(getVelocity().mult(dt));
        rectangle.setX(getPosition().getX());
        rectangle.setY(getPosition().getY());            
    }
    
    
    public Vector getVelocity() 
    {
        return velocity;
    }
    
    public Vector getAcceleration() {
        return acceleration;
    }

}
