package network;

import network.Vector;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameObject {
    protected Circle circle;

    
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    
    public GameObject(Vector position, Vector velocity, Vector acceleration, double radius)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration; 
        
        circle = new Circle(0.0, 0.0, radius);
        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());
    }
    
    
    public Circle getCircle()
    {
        return circle;
    }
    
    
    
    public void update(double dt)
    {
        // Euler Integration
        // Update velocity
//        Vector frameAcceleration = getAcceleration().mult(dt);
//        velocity = getVelocity().add(frameAcceleration);

        // Update position
        position = getPosition().add(getVelocity().mult(dt));
        circle.setCenterX(getPosition().getX());
        circle.setCenterY(getPosition().getY());

        
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }
}
