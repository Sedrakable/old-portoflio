/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static network.FXMLMenuController.getStage2;

public class FXMLDocumentController implements Initializable {

    @FXML
    public Button exit;

    @FXML
    public Button menuButton;

    @FXML
    public Button minimise;

    @FXML
    public Label x_position;

    @FXML
    public Label y_position;

    @FXML
    public AnchorPane AnchorPane;

    @FXML
    public Pane pane;

    @FXML
    public AnchorPane menuPane;

    @FXML
    public ImageView mirror;

    @FXML
    public ImageView light_1, light_2, light_3, light_4, light_5, light_6, light_7, light_8;

    @FXML
    public Slider speedSlider;

    @FXML
    public Slider velocitySlider;
    
    @FXML
    public Slider rateSlider;
    
    @FXML
    public Label speedLabel;

    @FXML
    public Label velocityLabel;
    
    @FXML
    public Label rateLabel;

    @FXML
    public Button lvl1Button;

    @FXML
    public Button lvl2Button;

    @FXML
    public Button lvl3Button;
    
    @FXML
    public CheckBox blockCheck;
    
    @FXML
    public CheckBox soundCheck;
    
    @FXML
    public Label checkLabel;
    
    @FXML
    public Label soundLabel;
    
    @FXML
    public Label SPEED_LABEL;
    
    @FXML
    public Label endLabel;
    
    //menu pane on or off
    public boolean pane_disabled = false;
    
    public int devidor = 1;

    
    //for animation timer
    public double lastFrameTime = 0.0;

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public double StageWidth() {
        return getStage2().getWidth();
    }

    public double StageHeight() {
        return getStage2().getHeight();
    }

    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }
    
    private final AudioClip backgroundMusic = new AudioClip("file:./assets/yeet.wav");;
    
    private boolean lvl1 = true;
    private boolean lvl2 = false;
    private boolean lvl3 = false;

    private double h = 72;
    private double w = 180;
    //LVL1 lines and stops
    private double bottom_stop_1 = 265;
    private double bottom_stop_2 = 1345;
    private double top_stop_1 = 1815;
    private double top_stop_2 = 735;
    private double under_stop = 815;

    private double topLine = 480;
    private double bottomLine = 600;
    private double rightLine = 1580;
    private double underLine = 500;

    //LVL2 lines and stops
    private double radius = 30 + w / 2;
    private double radius2 = 90 + w / 2;

    private final double LEFT_LINE_R = 330;
    private final double LEFT_LINE_L = 270;
    private final double LEFT_MIDDLE_LINE_R = 810;
    private final double LEFT_MIDDLE_LINE_L = 750;
    private final double RIGHT_MIDDLE_LINE_R = 1170;
    private final double RIGHT_MIDDLE_LINE_L = 1110;
    private final double RIGHT_LINE_R = 1650;
    private final double RIGHT_LINE_L = 1590;
    private final double TOP_LINE_B = 330;
    private final double TOP_LINE_T = 270;
    private final double BOTTOM_LINE_T = 750;
    private final double BOTTOM_LINE_B = 810;

    private final double STOP_1 = 240;
    private final double STOP_2 = 360;
    private final double STOP_3 = 720;
    private final double STOP_4 = 840;
    private final double STOP_5 = 1080;
    private final double STOP_6 = 1200;
    private final double STOP_7 = 1560;
    private final double STOP_8 = 1680;

    private ArrayList<Rectangle> lvl1_rec = new ArrayList<>();
    private ArrayList<Rectangle> lvl2_rec = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Boolean> lightCheckOnce = new ArrayList<>();
    private ArrayList<Boolean> priorityInter1 = new ArrayList<>();
    private ArrayList<Boolean> priorityInter2 = new ArrayList<>();
    private ArrayList<Double> turnAngs = new ArrayList<>();
    private ArrayList<Double> turnTimes = new ArrayList<>();
    private ArrayList<Double> finalAngs = new ArrayList<>();
    private ArrayList<Double> initialAngs = new ArrayList<>();
    private ArrayList<String> directions = new ArrayList<>();
    private ArrayList<Boolean> onces = new ArrayList<>();
    private ArrayList<Boolean> oncesAgain = new ArrayList<>();
    private ArrayList<Boolean> didColide = new ArrayList<>();
    private ArrayList<Integer> pathNumbers = new ArrayList<>();
    private ArrayList<Double> xs = new ArrayList<>();
    private ArrayList<Double> ys = new ArrayList<>();
    private ArrayList<String> paths = new ArrayList<>();
    private ArrayList<Integer> randoms = new ArrayList<>();
    private ArrayList<Bounds> bounds = new ArrayList<>();

    private double carSpeed = 400;
    private double circVelocity = carSpeed/200;
    private double circAccel = carSpeed/400;
    private final double deccelDistance = w * 0.8;
    private double specialDis = 0;
    private double specialDis2 = 305;
    private final double gap = 200;
    private final double rx_1 = (top_stop_2 - underLine - specialDis) + w / 2;
    private final double ry_1 = (under_stop - bottomLine) + w / 2;
    private final double rx_3 = (top_stop_1 - rightLine) + (w / 2);
    private final double ry_3 = (topLine - specialDis2) + w / 2;
    private final double rx_2 = (rightLine - bottom_stop_2) + (w / 2);
    private final double ry_2 = (bottomLine - specialDis2) + w / 2;
    
    private double rate = 10;
    
    private double actualRate = 100/rate;
//    private final double rate = 100/actualRate;
    private double time = 0;
    private double time2 = 0;
    private double speed = 1;
    private boolean stoped1 = false;
    private boolean stoped2 = false;
    private boolean stoped3 = false;
    private boolean stoped4 = false;
    private boolean stoped5 = false;
    private boolean stoped6 = false;
    private boolean stoped11 = false;
    private boolean stoped22 = false;
    private boolean stoped33 = false;
    private boolean stoped44 = false;
    private boolean stoped55 = false;
    private boolean stoped66 = false;
    private Integer carStoped1 = -1;
    private Integer carStoped2 = -1;
    private Integer carStoped3 = -1;
    private Integer carStoped4 = -1;
    private double initialPos1 = -200;
    private double initialPos2 = 600;
    private double initialPos3 = 500;
    private final double InitialBL = 500;
    private final double InitialB = 500;
    private final double InitialR = 500;
    private final double InitialTR = -500;
    private final double InitialT = -500;
    private final double InitialL = -500;
    private Boolean horizontal = false;
    private Boolean vertical = true;
    private Integer reee = 0;
    private Boolean on = false;
    private Boolean musicOn = true;
    private double error = 8;
    
    

    public void getPosition(MouseEvent event) {
        x_position.setText(Double.toString(event.getX()));
        y_position.setText(Double.toString(event.getY()));
    }

    private Double createAccel(double Vi, double Vf, double Xi, double Xf) {
        double a = ((Vf * Vf) - (Vi * Vi)) / (2 * (Xf - Xi));
        return a;

    }

    public void adder() {
        randoms.add(random());
        xs.add(0.0);
        ys.add(0.0);
        onces.add(true);
        oncesAgain.add(true);
        finalAngs.add(0.0);
        initialAngs.add(0.0);
        didColide.add(false);
        pathNumbers.add(1);
        directions.add("0");
        priorityInter1.add(false);
        priorityInter2.add(false);
        turnAngs.add(3 * (Math.PI / 2));
        turnTimes.add(0.0);
        lightCheckOnce.add(true);
    }

    public void carCreator1(String dir) {
        Vector a = new Vector(0, 0);
        Vector v = new Vector(0.0, 0.0);
        Vector p = new Vector(0.0, 0.0);
        switch (dir) {
            case "r":
                v = new Vector(carSpeed, 0.0);
                p = new Vector(initialPos1, bottomLine);
                paths.add("1");
                break;
            case "l":
                v = new Vector(-carSpeed, 0.0);
                p = new Vector(StageWidth() + initialPos2, topLine);
                paths.add("2");
                break;
            case "u":
                v = new Vector(0, -carSpeed);
                p = new Vector(underLine, StageHeight() + initialPos3);
                paths.add("3");
                break;
            default:
                break;
        }

        Car car = new Car(p, v, a, w, h);
        cars.add(car);
        addToPane(car.getRectangle());
        adder();
    }

    public void carCreator2(String dir) {
        Vector a = new Vector(0, 0);
        Vector v = new Vector(0.0, 0.0);
        Vector p = new Vector(0.0, 0.0);
        switch (dir) {
            case "BL":
                v = new Vector(0, -carSpeed);
                p = new Vector(LEFT_LINE_R, StageHeight() + InitialBL);
                paths.add("BL");
                break;
            case "B":
                v = new Vector(0, -carSpeed);
                p = new Vector(RIGHT_MIDDLE_LINE_R, StageHeight() + InitialB);
                paths.add("B");
                break;
            case "R":
                v = new Vector(-carSpeed, 0);
                p = new Vector(StageWidth() + InitialR, BOTTOM_LINE_T);
                paths.add("R");
                break;
            case "TR":
                v = new Vector(0, carSpeed);
                p = new Vector(RIGHT_LINE_L, InitialTR);
                paths.add("TR");
                break;
            case "T":
                v = new Vector(0, carSpeed);
                p = new Vector(LEFT_MIDDLE_LINE_L, InitialT);
                paths.add("T");
                break;
            case "L":
                v = new Vector(carSpeed, 0);
                p = new Vector(InitialL, TOP_LINE_B);
                paths.add("L");
                break;
            default:
                break;
        }
        adder();
        Car car = new Car(p, v, a, w, h);
        
        cars.add(car);
        addToPane(car.getRectangle());
        
    }
    
    public void carPainter(){
        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i) != null)
            {
                if(null!= randoms.get(i))
                switch (randoms.get(i)) {
                    case 1:{
                        Image img = new Image("file:./assets/lada.png");
                        cars.get(i).getRectangle().setFill(new ImagePattern(img));
                            break;
                        }
                    case 2:{
                        Image img = new Image("file:./assets/lada_taxi.png");
                        cars.get(i).getRectangle().setFill(new ImagePattern(img));
                            break;
                        }
                    case 3:{
                        Image img = new Image("file:./assets/red_lada.png");
                        cars.get(i).getRectangle().setFill(new ImagePattern(img));
                            break;
                        }
                    case 4:{
                        Image img = new Image("file:./assets/green_lada.png");
                        cars.get(i).getRectangle().setFill(new ImagePattern(img));
                            break;
                        }
                    case 5:{
                        Image img = new Image("file:./assets/blue_lada.png");
                        cars.get(i).getRectangle().setFill(new ImagePattern(img));
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        
       
    }
    
    private void Remover(int i) {
        double x = cars.get(i).getPosition().getX();
        double y = cars.get(i).getPosition().getY();
        if (x > StageWidth() + 1000 || x < -1000 || y > StageHeight() + 1000 || y < -1000) {
            removeFromPane(cars.get(i).getRectangle());
            cars.set(i, null);
            randoms.set(i, null);
            xs.set(i, null);
            ys.set(i, null);
            onces.set(i, null);
            finalAngs.set(i, null);
            initialAngs.set(i, null);
            didColide.set(i, null);
            pathNumbers.set(i, null);
            directions.set(i, null);
            cars.set(i, null);
            turnAngs.set(i, null);
            turnTimes.set(i, null);
            oncesAgain.set(i, null);
            lightCheckOnce.set(i, null);
        }
    }

    public void reseter() {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != null) {
                removeFromPane(cars.get(i).getRectangle());
            }
        }
        for (int i = 0; i < lvl1_rec.size(); i++) {
            removeFromPane(lvl1_rec.get(i));
        }

        for (int i = 0; i < lvl2_rec.size(); i++) {
            removeFromPane(lvl2_rec.get(i));
        }
        endLabel.setVisible(false);
        cars = new ArrayList<>();
        lvl1_rec = new ArrayList<>();
        lvl2_rec = new ArrayList<>();
        priorityInter1 = new ArrayList<>();
        priorityInter2 = new ArrayList<>();
        turnAngs = new ArrayList<>();
        turnTimes = new ArrayList<>();
        finalAngs = new ArrayList<>();
        initialAngs = new ArrayList<>();
        directions = new ArrayList<>();
        onces = new ArrayList<>();
        oncesAgain = new ArrayList<>();
        lightCheckOnce = new ArrayList<>();
        didColide = new ArrayList<>();
        pathNumbers = new ArrayList<>();
        xs = new ArrayList<>();
        ys = new ArrayList<>();
        paths = new ArrayList<>();
        randoms = new ArrayList<>();
        bounds = new ArrayList<>();
        lastFrameTime = 0;
        stoped1 = false;
        stoped2 = false;
        stoped3 = false;
        stoped4 = false;
        stoped5 = false;
        stoped6 = false;
        stoped11 = false;
        stoped22 = false;
        stoped33 = false;
        stoped44 = false;
        stoped55 = false;
        stoped66 = false;
        carStoped1 = -1;
        carStoped2 = -1;
        carStoped3 = -1;
        carStoped4 = -1;
        time = 0;
        time2 = 0;

    }

    public void collisions(int j) {

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != null && directions.get(j).equals("r") && directions.get(i).equals("r")) {

                if (!didColide.get(j) && cars.get(i).getPosition().getX() - cars.get(j).getPosition().getX() <= w * 2 && cars.get(i).getPosition().getX() > cars.get(j).getPosition().getX() && cars.get(j).getPosition().getY() == cars.get(i).getPosition().getY()) {
                    cars.get(j).setAcceleration(new Vector(createAccel(cars.get(j).getVelocity().getX(), 0, 0, w * 1.4), 0));
                    didColide.set(j, true);
                } else if (didColide.get(j) && cars.get(j).getVelocity().getX() < 0) {
                    cars.get(j).setAcceleration(new Vector(0, 0));
                    cars.get(j).getVelocity().setX(0);
                } else if (didColide.get(j) && cars.get(i).getPosition().getX() - cars.get(j).getPosition().getX() >= w * 2.1) {
                    didColide.set(j, false);
                }

//                    cars.get(j).setAcceleration(new Vector(createAccel(0, carSpeed, 0, w * 1.4), 0));
//                } else if (didColide.get(j) && cars.get(j).getVelocity().getX() > carSpeed) {
//                    didColide.set(j, false);
//                    cars.get(j).getVelocity().setX(carSpeed);
//                    cars.get(j).getAcceleration().setX(0);
//                }
            } else if (cars.get(i) != null && directions.get(j).equals("l") && directions.get(i).equals("l")) {

                if (!didColide.get(j) && cars.get(j).getPosition().getX() - cars.get(i).getPosition().getX() <= w * 1.5 && cars.get(i).getPosition().getX() < cars.get(j).getPosition().getX() && cars.get(j).getPosition().getY() == cars.get(i).getPosition().getY()) {
                    cars.get(j).setAcceleration(new Vector(createAccel(cars.get(j).getVelocity().getX(), 0, 0, -w * 1.2), 0));
                    didColide.set(j, true);
                } else if (didColide.get(j) && cars.get(j).getVelocity().getX() > 0) {
                    cars.get(j).setAcceleration(new Vector(0, 0));
                    cars.get(j).getVelocity().setX(0);
                } else if (didColide.get(j) && cars.get(j).getPosition().getX() - cars.get(i).getPosition().getX() >= w) {
                    cars.get(j).setAcceleration(new Vector(createAccel(0, -carSpeed, 0, -w * 0.8), 0));
                } else if (didColide.get(j) && cars.get(j).getVelocity().getX() < -carSpeed) {
                    didColide.set(j, false);
                    cars.get(j).getVelocity().setX(-carSpeed);
                    cars.get(j).getAcceleration().setX(0);
                }

            } else if (cars.get(i) != null && directions.get(j).equals("u") && directions.get(i).equals("u")) {

                if (!didColide.get(j) && cars.get(i).getPosition().getY() - cars.get(j).getPosition().getY() <= w * 1.5 && cars.get(i).getPosition().getY() > cars.get(j).getPosition().getY() && cars.get(j).getPosition().getX() == cars.get(i).getPosition().getX()) {
                    cars.get(j).setAcceleration(new Vector(0, createAccel(cars.get(j).getVelocity().getY(), 0, 0, w * 1.2)));
                    didColide.set(j, true);
                } else if (didColide.get(j) && cars.get(j).getVelocity().getY() < 0) {
                    cars.get(j).setAcceleration(new Vector(0, 0));
                    cars.get(j).getVelocity().setY(0);
                } else if (didColide.get(j) && cars.get(i).getPosition().getY() - cars.get(j).getPosition().getY() >= w) {
                    cars.get(j).setAcceleration(new Vector(0, createAccel(0, carSpeed, 0, w * 0.8)));
                } else if (didColide.get(j) && cars.get(j).getVelocity().getY() > carSpeed) {
                    didColide.set(j, false);
                    cars.get(j).getVelocity().setY(carSpeed);
                    cars.get(j).getAcceleration().setY(0);
                }

            } else if (cars.get(i) != null && directions.get(j).equals("d") && directions.get(i).equals("d")) {

                if (!didColide.get(j) && cars.get(j).getPosition().getY() - cars.get(i).getPosition().getY() <= w * 1.5 && cars.get(i).getPosition().getY() < cars.get(j).getPosition().getY() && cars.get(j).getPosition().getX() == cars.get(i).getPosition().getX()) {
                    cars.get(j).setAcceleration(new Vector(0, createAccel(cars.get(j).getVelocity().getY(), 0, 0, w * -1.2)));
                    didColide.set(j, true);
                } else if (didColide.get(j) && cars.get(j).getVelocity().getY() > 0) {
                    cars.get(j).setAcceleration(new Vector(0, 0));
                    cars.get(j).getVelocity().setY(0);
                } else if (didColide.get(j) && cars.get(j).getPosition().getY() - cars.get(i).getPosition().getY() >= w) {
                    cars.get(j).setAcceleration(new Vector(0, createAccel(0, -carSpeed, 0, w * 0.8)));
                } else if (didColide.get(j) && cars.get(j).getVelocity().getY() < -carSpeed) {
                    didColide.set(j, false);
                    cars.get(j).getVelocity().setY(-carSpeed);
                    cars.get(j).getAcceleration().setY(0);
                }
            }
        }
    }

    public void blockCreator() {

        Rectangle stop1 = new Rectangle(260 / devidor, 535 / devidor, 20 / devidor, 125 / devidor);
        bounds.add(stop1.getBoundsInParent());
        Rectangle stop2 = new Rectangle(420 / devidor, 800 / devidor, 160 / devidor, 20 / devidor);
        bounds.add(stop2.getBoundsInParent());
        Rectangle stop3 = new Rectangle(1340 / devidor, 535 / devidor, 20 / devidor, 125 / devidor);
        bounds.add(stop3.getBoundsInParent());
        Rectangle stop4 = new Rectangle(1800 / devidor, 420 / devidor, 20 / devidor, 125 / devidor);
        bounds.add(stop4.getBoundsInParent());

        Rectangle stop1end = new Rectangle(720 / devidor, 535 / devidor, 20 / devidor, 125 / devidor);
        Rectangle stop2end = new Rectangle(720 / devidor, 535 / devidor, 20 / devidor, 125 / devidor);
        Rectangle stop3end = new Rectangle(1500 / devidor, 340 / devidor, 160 / devidor, 20 / devidor);
        Rectangle stop4end = new Rectangle(1550 / devidor, 360 / devidor, 160 / devidor, 20 / devidor);
        Rectangle stop4end2 = new Rectangle(1400 / devidor, 445 / devidor, 20 / devidor, 70 / devidor);
        Rectangle stop3end2 = new Rectangle(1800 / devidor, 535 / devidor, 20 / devidor, 125 / devidor);
        bounds.add(stop1end.getBoundsInParent());
        bounds.add(stop2end.getBoundsInParent());
        bounds.add(stop3end.getBoundsInParent());
        bounds.add(stop4end.getBoundsInParent());
        bounds.add(stop4end2.getBoundsInParent());
        bounds.add(stop3end2.getBoundsInParent());

        lvl1_rec.add(stop1);
        lvl1_rec.add(stop2);
        lvl1_rec.add(stop3);
        lvl1_rec.add(stop4);
        addToPane(stop1);
        addToPane(stop2);
        addToPane(stop3);
        addToPane(stop4);
        stop1.setOpacity(0.5);
        stop2.setOpacity(0.5);
        stop3.setOpacity(0.5);
        stop4.setOpacity(0.5);

        lvl1_rec.add(stop1end);
        lvl1_rec.add(stop2end);
        lvl1_rec.add(stop3end);
        lvl1_rec.add(stop4end);
        lvl1_rec.add(stop4end2);
        lvl1_rec.add(stop3end2);
        addToPane(stop1end);
        addToPane(stop2end);
        addToPane(stop3end);
        addToPane(stop4end);
        addToPane(stop4end2);
        addToPane(stop3end2);
        stop1end.setOpacity(0.5);
        stop2end.setOpacity(0.5);
        stop3end.setOpacity(0.5);
        stop4end.setOpacity(0.5);
        stop4end2.setOpacity(0.5);
        stop3end2.setOpacity(0.5);
    }

    public void priority() {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != null) {
                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(0))) {
                    stoped1 = true;
                    carStoped1 = i;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(1))) {
                    stoped2 = true;
                    carStoped2 = i;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(2))) {
                    stoped3 = true;
                    carStoped3 = i;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(3))) {
                    stoped4 = true;
                    carStoped4 = i;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(4))) {
                    stoped1 = false;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(5))) {
                    stoped2 = false;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(6))) {
                    stoped3 = false;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(7))) {
                    stoped4 = false;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(8)) && cars.get(i).getVelocity().getY() == 0) {
                    stoped4 = false;
                }

                if (cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(9))) {
                    stoped3 = false;
                }

            }
        }

        if (stoped1 && !stoped2) {
            priorityInter1.set(carStoped1, true);
        }

        if (!stoped1 && stoped2) {
            priorityInter1.set(carStoped2, true);
        }

        if (stoped3 && !stoped4) {
            priorityInter2.set(carStoped3, true);
        }

        if (!stoped3 && stoped4) {
            priorityInter2.set(carStoped4, true);
        }

        if (stoped3 && stoped4) {
            priorityInter2.set(carStoped4, true);
        }

    }

    public Double blockPos(double x) {
//        double r = x + 30 - ((30 * carSpeed) / 100);
        double r = x -200;
        return r;
    }

    public void turnPriority() {
        Rectangle block1 = new Rectangle(blockPos(240), 300,400 - blockPos(240), 50);
        Rectangle block11 = new Rectangle(260, 240, 420 - blockPos(260), 60);
        Rectangle block2 = new Rectangle(blockPos(1080), 300, 1240 - blockPos(1080), 50);
        Rectangle block22 = new Rectangle(1100, 240, 1260 - blockPos(1100), 60);
        Rectangle block3 = new Rectangle(1570, blockPos(240), 50, 400 - blockPos(240));
        Rectangle block33 = new Rectangle(1620, 260, 60, 420 - blockPos(260));
        Rectangle block4 = new Rectangle(300, 680, 50, 840 - blockPos(690));
        Rectangle block44 = new Rectangle(240,blockPos(660), 60,820- blockPos(660));
        Rectangle block5 = new Rectangle(680, 730, 840 - blockPos(680), 50);
        Rectangle block55 = new Rectangle(blockPos(660), 780, 820 - blockPos(660), 60);
        Rectangle block6 = new Rectangle(1520, 730, 1680 - blockPos(1520), 50);
        Rectangle block66 = new Rectangle(blockPos(1500), 780, 1660 - blockPos(1500), 60);
        bounds.add(block1.getBoundsInParent());
        bounds.add(block11.getBoundsInParent());
        bounds.add(block2.getBoundsInParent());
        bounds.add(block22.getBoundsInParent());
        bounds.add(block3.getBoundsInParent());
        bounds.add(block33.getBoundsInParent());
        bounds.add(block4.getBoundsInParent());
        bounds.add(block44.getBoundsInParent());
        bounds.add(block5.getBoundsInParent());
        bounds.add(block55.getBoundsInParent());
        bounds.add(block6.getBoundsInParent());
        bounds.add(block66.getBoundsInParent());
        lvl2_rec.add(block1);
        lvl2_rec.add(block11);
        lvl2_rec.add(block2);
        lvl2_rec.add(block22);
        lvl2_rec.add(block3);
        lvl2_rec.add(block33);
        lvl2_rec.add(block4);
        lvl2_rec.add(block44);
        lvl2_rec.add(block5);
        lvl2_rec.add(block55);
        lvl2_rec.add(block6);
        lvl2_rec.add(block66);
        
        for (int i = 0; i < lvl2_rec.size(); i++) {
            addToPane(lvl2_rec.get(i));
            lvl2_rec.get(i).setVisible(false);
        }
        
        block1.setOpacity(0.5);
        block11.setOpacity(0.5);
        block2.setOpacity(0.5);
        block22.setOpacity(0.5);
        block3.setOpacity(0.5);
        block33.setOpacity(0.5);
        block4.setOpacity(0.5);
        block44.setOpacity(0.5);
        block5.setOpacity(0.5);
        block55.setOpacity(0.5);
        block6.setOpacity(0.5);
        block66.setOpacity(0.5);
    }

    public void stopPriority() {
        stoped1 = false;stoped2 = false;stoped3 = false;stoped4 = false;stoped5 = false;stoped6 = false;
        stoped11 = false;stoped22 = false;stoped33 = false;stoped44 = false;stoped55 = false;stoped66 = false;
        
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != null) {
                if(!stoped1){
                stoped1 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(0));
                }
                
                if(!stoped2){
                stoped2 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(1));
                }
                
                if(!stoped3){
                stoped3 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(2));
                }
                
                if(!stoped4){
                stoped4 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(3));
                }
                
                if(!stoped5){
                stoped5 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(4));
                }
                
                if(!stoped6){
                stoped6 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(5));
                }
                
                if(!stoped11){
                stoped11 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(6));
                }
                
                if(!stoped22){
                stoped22 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(7));
                }
                
                if(!stoped33){
                stoped33 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(8));
                }
                
                if(!stoped44){
                stoped44 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(9));
                }
                
                if(!stoped55){
                stoped55 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(10));
                }
                
                if(!stoped66){
                stoped66 = cars.get(i).getRectangle().getBoundsInParent().intersects(bounds.get(11));
                }
                
                
            }
        }       
    }

    public void lightPriority(double ct) {
        if ((Math.round(ct * 10)) % 60 == 0 && time2 != (Math.round(ct * 10))) {
            time2 = (Math.round(ct * 10));
            horizontal ^= true;
            vertical ^= true;
        }

        if (horizontal) {
            light_1.setId("green_light");
            light_2.setId("green_light");
            light_3.setId("red_light");
            light_4.setId("red_light");
            light_5.setId("green_light");
            light_6.setId("green_light");
            light_7.setId("red_light");
            light_8.setId("red_light");
        }

        if (vertical) {
            light_1.setId("red_light");
            light_2.setId("red_light");
            light_3.setId("green_light");
            light_4.setId("green_light");
            light_5.setId("red_light");
            light_6.setId("red_light");
            light_7.setId("green_light");
            light_8.setId("green_light");
        }
    }

    private void runLvl1(double ct, double fd) {
        if ((Math.round(ct * 10)) % rate == 0 && time != (Math.round(ct * 10))) {
            time = (Math.round(ct * 10));
            carCreator1("r");
            carCreator1("l");
            carCreator1("u");

        }
        priority();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != null) {
                //scalar(i);
                cars.get(i).update(fd);
                //collisions(i);
                switch (paths.get(i)) {
                    case "1":
                        myPath1(i, fd);
                        break;
                    case "2":
                        myPath2(i, fd);
                        break;
                    case "3":
                        myPath3(i, fd);
                        break;
                    default:
                        break;
                }
                Remover(i);
            }
        }
    }

    private void runLvl2(double ct, double fd) {
        if ((Math.round(ct * 10)) % rate == 0 && time != (Math.round(ct * 10))) {
            time = (Math.round(ct * 10));
            carCreator2("BL");
            carCreator2("B");
            carCreator2("R");
            carCreator2("TR");
            carCreator2("T");
            carCreator2("L");
            carPainter();
        }
        
        SPEED_LABEL.setText(Math.round(carSpeed)+"");
        lightPriority(ct);
        light2Top();
        stopPriority();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i) != null) {
                cars.get(i).update(fd);
                //collisions(i);
                switch (paths.get(i)) {
                    case "L":
                        myPathL(i, fd);
                        break;
                    case "R":
                        myPathR(i, fd);
                        break;
                    case "T":
                        myPathT(i, fd);
                        break;
                    case "B":
                        myPathB(i, fd);
                        break;
                    case "BL":
                        myPathBL(i, fd);
                        break;
                    case "TR":
                        myPathTR(i, fd);
                        break;
                    default:
                        break;
                }
                Remover(i);
            }
        }
    }

    private void Play() {
        blockCreator();
        long initialTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / (1000000000.0 / speed);
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                menuPane.toFront();

                if (lvl1) {
                    runLvl1(currentTime, frameDeltaTime);
                } else if (lvl2) {
                    runLvl2(currentTime, frameDeltaTime);
                }

            }
        }.start();
    }

    public void Left2Right(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "r");
            cars.get(i).setVelocity(new Vector(carSpeed, 0));
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).getRectangle().setRotate(0);
            cars.get(i).getPosition().setY(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
        }

        if (cars.get(i).getPosition().getX() >= stop - w / 2 - (carSpeed/400)*(error * speed)) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setVelocity(new Vector(0, 0));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
        }
    }

    public void Left2RightStop(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "r");
            cars.get(i).setVelocity(new Vector(carSpeed, 0));
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).getRectangle().setRotate(0);
            cars.get(i).getPosition().setY(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
            lightCheckOnce.set(i, true);
        }

        if (cars.get(i).getPosition().getX() + w / 2 >= stop - deccelDistance && cars.get(i).getVelocity().getX() > 0 && oncesAgain.get(i) && !didColide.get(i)) {
            cars.get(i).setAcceleration(new Vector(createAccel(cars.get(i).getVelocity().getX(), 0.0, cars.get(i).getPosition().getX() + w / 2, stop), 0.0));
            oncesAgain.set(i, false);
        } else if (cars.get(i).getPosition().getX() >= stop - w / 2 - (carSpeed/400)*(error * speed)) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(stop - (w / 2), line));
            cars.get(i).setVelocity(new Vector(0, 0));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
            oncesAgain.set(i, true);
        }
    }

    public void Right2Left(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "l");
            cars.get(i).setVelocity(new Vector(-carSpeed, 0));
            cars.get(i).getRectangle().setRotate(-180);
            cars.get(i).getPosition().setY(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
        }

        if (cars.get(i).getPosition().getX() <= stop + w / 2 + (carSpeed/400)*(error * speed)) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(stop + (w / 2), line));
            cars.get(i).setVelocity(new Vector(0, 0));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
        }
    }

    public void Right2LeftStop(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "l");
            cars.get(i).setVelocity(new Vector(-carSpeed, 0));
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).getRectangle().setRotate(-180);
            cars.get(i).getPosition().setY(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
            lightCheckOnce.set(i, true);
        }

        if (cars.get(i).getPosition().getX() - w / 2 <= stop + deccelDistance && cars.get(i).getVelocity().getX() < 0 && oncesAgain.get(i)) {
            cars.get(i).setAcceleration(new Vector(createAccel(cars.get(i).getVelocity().getX(), 0.0, cars.get(i).getPosition().getX() - w / 2, stop), 0.0));
            oncesAgain.set(i, false);
        } else if (cars.get(i).getVelocity().getX() >= 0 && cars.get(i).getPosition().getX() <= stop + w/2 + (carSpeed/400)*(error * speed)) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setVelocity(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(stop + (w / 2), line));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
            oncesAgain.set(i, true);
        }
    }

    public void Top2Bottom(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "d");
            cars.get(i).setVelocity(new Vector(0, carSpeed));
            cars.get(i).getRectangle().setRotate(90);
            cars.get(i).getPosition().setX(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
        }

        if (cars.get(i).getPosition().getY() >= stop - w / 2) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(line, stop - (w / 2) - (carSpeed/400)*(error * speed)));
            cars.get(i).setVelocity(new Vector(0, 0));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
        }
    }

    public void Top2BottomStop(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "d");
            cars.get(i).setVelocity(new Vector(0, carSpeed));
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).getRectangle().setRotate(90);
            cars.get(i).getPosition().setX(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
            lightCheckOnce.set(i, true);
        }

        if (cars.get(i).getPosition().getY() + w / 2 >= stop - deccelDistance && cars.get(i).getVelocity().getY() > 0 && oncesAgain.get(i) && !didColide.get(i)) {
            cars.get(i).setAcceleration(new Vector(0.0, createAccel(cars.get(i).getVelocity().getY(), 0.0, cars.get(i).getPosition().getY() + w / 2, stop)));
            oncesAgain.set(i, false);
        } else if (cars.get(i).getVelocity().getY() <= 0 && cars.get(i).getPosition().getY() >= stop - w / 2 - (carSpeed/400)*(error * speed)) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setVelocity(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(line, stop - (w / 2)));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
            oncesAgain.set(i, true);
        }
    }

    public void Bottom2Top(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "u");
            cars.get(i).setVelocity(new Vector(0, -carSpeed));
            cars.get(i).getRectangle().setRotate(-90);
            cars.get(i).getPosition().setX(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
        }

        if (cars.get(i).getPosition().getY() <= stop + w / 2) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setVelocity(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(line, stop + (w / 2) + (carSpeed/400)*(error * speed)));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
        }
    }

    public void Bottom2TopStop(int i, double stop, double line) {
        if (onces.get(i)) {
            directions.set(i, "u");
            cars.get(i).setVelocity(new Vector(0, -carSpeed));
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).getRectangle().setRotate(-90);
            cars.get(i).getPosition().setX(line);
            onces.set(i, false);
            turnTimes.set(i, 0.0);
            priorityInter1.set(i, false);
            priorityInter2.set(i, false);
            lightCheckOnce.set(i, true);
        }

        if (cars.get(i).getPosition().getY() - w / 2 <= stop + deccelDistance && cars.get(i).getVelocity().getY() < 0 && oncesAgain.get(i)) {
            cars.get(i).setAcceleration(new Vector(0.0, createAccel(carSpeed, 0.0, stop + deccelDistance, stop)));
            oncesAgain.set(i, false);
        } else if (cars.get(i).getVelocity().getY() >= 0 && cars.get(i).getPosition().getY() <= stop + w / 2 + (carSpeed/400)*(error * speed)) {
            cars.get(i).setAcceleration(new Vector(0, 0));
            cars.get(i).setVelocity(new Vector(0, 0));
            cars.get(i).setPosition(new Vector(line, stop + (w / 2)));
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            onces.set(i, true);
            oncesAgain.set(i, true);
        }
    }

    public void dirCheckRight(int i) {
        switch (directions.get(i)) {
            case "r":
                turnAngs.set(i, Math.PI / 2);
                break;
            case "l":
                turnAngs.set(i, 3 * (Math.PI / 2));
                break;
            case "u":
                turnAngs.set(i, Math.PI);
                break;
            case "d":
                turnAngs.set(i, Math.PI*2);
                break;
            default:
                break;
        }
    }

    public void dirCheckLeft(int i) {
        switch (directions.get(i)) {
            case "r":
                turnAngs.set(i, 3 * (Math.PI / 2));
                break;
            case "l":
                turnAngs.set(i, (Math.PI / 2));
                break;
            case "u":
                turnAngs.set(i, 0.0);
                break;
            case "d":
                turnAngs.set(i, Math.PI);
                break;
            default:
                break;
        }
    }

    public void Gap(int i) {

        double xfinal = cars.get(i).getPosition().getX();
        double yfinal = cars.get(i).getPosition().getY();
        if (directions.get(i).equals("r") && onces.get(i)) {
            xs.set(i, cars.get(i).getPosition().getX());
            Vector a = new Vector(createAccel(0, carSpeed, 0, gap), 0);
            cars.get(i).setAcceleration(a);
            onces.set(i, false);
        } else if (directions.get(i).equals("l") && onces.get(i)) {
            xs.set(i, cars.get(i).getPosition().getX());
            Vector a = new Vector(-createAccel(0, carSpeed, 0, gap), 0);
            cars.get(i).setAcceleration(a);
            onces.set(i, false);
        } else if (directions.get(i).equals("u") && onces.get(i)) {
            ys.set(i, cars.get(i).getPosition().getY());
            Vector a = new Vector(0, -createAccel(0, carSpeed, 0, gap));
            cars.get(i).setAcceleration(a);
            onces.set(i, false);
        } else if (directions.get(i).equals("d") && onces.get(i)) {
            ys.set(i, cars.get(i).getPosition().getY());
            Vector a = new Vector(0, createAccel(0, carSpeed, 0, gap));
            cars.get(i).setAcceleration(a);
            onces.set(i, false);
        }

        if (directions.get(i).equals("r") && xfinal - xs.get(i) >= gap) {
            onces.set(i, true);
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            cars.get(i).setAcceleration(new Vector(0, 0));
        } else if (directions.get(i).equals("l") && xs.get(i) - xfinal >= gap) {
            onces.set(i, true);
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            cars.get(i).setAcceleration(new Vector(0, 0));
        } else if (directions.get(i).equals("u") && yfinal - ys.get(i) <= -gap) {
            onces.set(i, true);
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            cars.get(i).setAcceleration(new Vector(0, 0));
        } else if (directions.get(i).equals("d") && yfinal - ys.get(i) >= gap) {
            onces.set(i, true);
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            cars.get(i).setAcceleration(new Vector(0, 0));
        }

    }

    public void myPathL(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                if (randoms.get(i) == 1) {
                    Left2Right(i, STOP_1, TOP_LINE_B);
                    break;
                } else {
                    Left2RightStop(i, STOP_3, TOP_LINE_B);
                }
                break;
            case 2:
                switch (randoms.get(i)) {
                    case 1:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 2:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (randoms.get(i)) {
                    case 1:
                        Top2BottomStop(i, StageHeight() + 2000, LEFT_LINE_L);
                        break;
                    case 2:
                        Bottom2Top(i, -2000, LEFT_MIDDLE_LINE_R);
                        break;
                    case 3:
                        Left2Right(i, STOP_5, TOP_LINE_B);
                        break;
                    case 4:
                        Left2Right(i, STOP_5, TOP_LINE_B);
                        break;
                    case 5:
                        Left2RightStop(i, STOP_7, TOP_LINE_B);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (randoms.get(i)) {
                    case 3:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 4:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 5:
                        if ((!stoped3 && !stoped33) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (randoms.get(i)) {
                    case 3:
                        Top2BottomStop(i, STOP_3, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 4:
                        Top2BottomStop(i, STOP_3, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 5:
                        Bottom2Top(i, -2000, RIGHT_LINE_R);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (randoms.get(i)) {
                    case 3:
                        if (vertical || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 7:
                switch (randoms.get(i)) {
                    case 3:
                        Top2BottomStop(i, StageHeight() + 2000, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 4:
                        Left2Right(i, StageWidth() + 2000, BOTTOM_LINE_B);
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    public void myPathR(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                if (randoms.get(i) == 1) {
                    Right2Left(i, STOP_8, BOTTOM_LINE_T);
                    break;
                } else {
                    Right2LeftStop(i, STOP_6, BOTTOM_LINE_T);
                }
                break;
            case 2:
                switch (randoms.get(i)) {
                    case 1:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 2:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (randoms.get(i)) {
                    case 1:
                        Bottom2TopStop(i, -2000, RIGHT_LINE_R);
                        break;
                    case 2:
                        Top2Bottom(i, StageHeight() + 2000, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 3:
                        Right2Left(i, STOP_4, BOTTOM_LINE_T);
                        break;
                    case 4:
                        Right2Left(i, STOP_4, BOTTOM_LINE_T);
                        break;
                    case 5:
                        Right2LeftStop(i, STOP_2, BOTTOM_LINE_T);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (randoms.get(i)) {
                    case 3:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 4:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 5:
                        if ((!stoped4 && !stoped44) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (randoms.get(i)) {
                    case 3:
                        Bottom2TopStop(i, STOP_2, LEFT_MIDDLE_LINE_R);
                        break;
                    case 4:
                        Bottom2TopStop(i, STOP_2, LEFT_MIDDLE_LINE_R);
                        break;
                    case 5:
                        Top2Bottom(i, StageHeight() + 2000, LEFT_LINE_L);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (randoms.get(i)) {
                    case 3:
                        if (vertical || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 7:
                switch (randoms.get(i)) {
                    case 3:
                        Bottom2TopStop(i, -2000, LEFT_MIDDLE_LINE_R);
                        break;
                    case 4:
                        Right2Left(i, -2000, TOP_LINE_T);
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    public void myPathT(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                Top2BottomStop(i, STOP_1, LEFT_MIDDLE_LINE_L);
                break;
            case 2:
                switch (randoms.get(i)) {
                    case 1:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, circVelocity);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 2:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if (vertical || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (randoms.get(i)) {
                    case 1:
                        Right2Left(i, -2000, TOP_LINE_T);
                        break;
                    case 2:
                        Left2RightStop(i, STOP_7, TOP_LINE_B);
                        break;
                    case 3:
                        Left2Right(i, STOP_5, TOP_LINE_B);
                        break;
                    case 4:
                        Left2Right(i, STOP_5, TOP_LINE_B);
                        break;
                    case 5:
                        Top2BottomStop(i, STOP_3, LEFT_MIDDLE_LINE_L);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (randoms.get(i)) {
                    case 2:
                        if ((!stoped3 && !stoped33) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 4:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 5:
                        if (!stoped5 || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (randoms.get(i)) {
                    case 2:
                        Bottom2Top(i, -2000, RIGHT_LINE_R);
                        break;
                    case 3:
                        Top2BottomStop(i, STOP_3, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 4:
                        Top2BottomStop(i, STOP_3, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 5:
                        Right2LeftStop(i, STOP_2, BOTTOM_LINE_T);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (randoms.get(i)) {
                    case 3:
                        if (vertical || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if ((!stoped4 && !stoped44) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                }
                break;
            case 7:
                switch (randoms.get(i)) {
                    case 3:
                        Top2Bottom(i, StageHeight() + 2000, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 4:
                        Left2Right(i, StageWidth() + 2000, BOTTOM_LINE_B);
                        break;
                    case 5:
                        Top2Bottom(i, StageHeight() + 2000, LEFT_LINE_L);
                        break;
                }
                break;
        }
    }

    public void myPathB(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                Bottom2TopStop(i, STOP_4, RIGHT_MIDDLE_LINE_R);
                break;
            case 2:
                switch (randoms.get(i)) {
                    case 1:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, circVelocity);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 2:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if (vertical || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (randoms.get(i)) {
                    case 1:
                        Left2Right(i, StageWidth() + 2000, BOTTOM_LINE_B);
                        break;
                    case 2:
                        Right2LeftStop(i, STOP_2, BOTTOM_LINE_T);
                        break;
                    case 3:
                        Right2Left(i, STOP_4, BOTTOM_LINE_T);
                        break;
                    case 4:
                        Right2Left(i, STOP_4, BOTTOM_LINE_T);
                        break;
                    case 5:
                        Bottom2TopStop(i, STOP_2, RIGHT_MIDDLE_LINE_R);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (randoms.get(i)) {
                    case 2:
                        if ((!stoped4 && !stoped44) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 4:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 5:
                        if (!stoped2 || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (randoms.get(i)) {
                    case 2:
                        Top2Bottom(i, StageHeight() + 2000, LEFT_LINE_L);
                        break;
                    case 3:
                        Bottom2TopStop(i, STOP_2, LEFT_MIDDLE_LINE_R);
                        break;
                    case 4:
                        Bottom2TopStop(i, STOP_2, LEFT_MIDDLE_LINE_R);
                        break;
                    case 5:
                        Left2RightStop(i, STOP_7, TOP_LINE_B);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (randoms.get(i)) {
                    case 3:
                        if (vertical || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (vertical || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if ((!stoped3 && !stoped33) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                }
                break;
            case 7:
                switch (randoms.get(i)) {
                    case 3:
                        Bottom2Top(i, -2000, LEFT_MIDDLE_LINE_R);
                        break;
                    case 4:
                        Right2Left(i, -2000, TOP_LINE_T);
                        break;
                    case 5:
                        Bottom2Top(i, -2000, RIGHT_LINE_R);
                        break;
                }
                break;
        }
    }

    public void myPathBL(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                switch (randoms.get(i)) {
                    case 1:
                        Bottom2TopStop(i, STOP_2, LEFT_LINE_R);
                        break;
                    case 2:
                        Bottom2TopStop(i, STOP_2, LEFT_LINE_R);
                        break;
                    case 3:
                        Bottom2TopStop(i, STOP_2, LEFT_LINE_R);
                        break;
                    case 4:
                        Bottom2Top(i, STOP_4, LEFT_LINE_R);
                        break;
                    case 5:
                        Bottom2Top(i, STOP_4, LEFT_LINE_R);
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (randoms.get(i)) {
                    case 1:
                        if ((!stoped1 && !stoped11) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 2:
                        if (!stoped1 || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (!stoped1 || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 5:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (randoms.get(i)) {
                    case 1:
                        Right2Left(i, -2000, TOP_LINE_T);
                        break;
                    case 2:
                        Left2RightStop(i, STOP_3, TOP_LINE_B);
                        break;
                    case 3:
                        Left2RightStop(i, STOP_3, TOP_LINE_B);
                        break;
                    case 4:
                        Left2RightStop(i, STOP_5, BOTTOM_LINE_B);
                        break;
                    case 5:
                        Left2RightStop(i, STOP_5, BOTTOM_LINE_B);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (randoms.get(i)) {
                    case 2:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (randoms.get(i)) {
                    case 2:
                        Bottom2Top(i, -2000, LEFT_MIDDLE_LINE_R);
                        break;
                    case 3:
                        Left2RightStop(i, STOP_7, TOP_LINE_B);
                        break;
                    case 4:
                        Left2Right(i, StageWidth() + 2000, BOTTOM_LINE_B);
                        break;
                    case 5:
                        Top2Bottom(i, StageHeight() + 2000, RIGHT_MIDDLE_LINE_L);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (randoms.get(i)) {
                    case 3:
                        if ((!stoped3 && !stoped33) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                }
                break;
            case 7:
                switch (randoms.get(i)) {
                    case 3:
                        Bottom2Top(i, -2000, RIGHT_LINE_R);
                        break;
                }
                break;
        }
    }

    public void myPathTR(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                switch (randoms.get(i)) {
                    case 1:
                        Top2BottomStop(i, STOP_3, RIGHT_LINE_L);
                        break;
                    case 2:
                        Top2BottomStop(i, STOP_3, RIGHT_LINE_L);
                        break;
                    case 3:
                        Top2BottomStop(i, STOP_3, RIGHT_LINE_L);
                        break;
                    case 4:
                        Top2Bottom(i, STOP_1, RIGHT_LINE_L);
                        break;
                    case 5:
                        Top2Bottom(i, STOP_1, RIGHT_LINE_L);
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (randoms.get(i)) {
                    case 1:
                        if ((!stoped6 && !stoped66) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 2:
                        if (!stoped6 || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (!stoped6 || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                    case 4:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    case 5:
                        turnRight(i, dt, radius, radius, circVelocity);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (randoms.get(i)) {
                    case 1:
                        Left2Right(i, StageWidth() + 2000, BOTTOM_LINE_B);
                        break;
                    case 2:
                        Right2LeftStop(i, STOP_6, BOTTOM_LINE_T);
                        break;
                    case 3:
                        Right2LeftStop(i, STOP_6, BOTTOM_LINE_T);
                        break;
                    case 4:
                        Right2LeftStop(i, STOP_4, TOP_LINE_T);
                        break;
                    case 5:
                        Right2LeftStop(i, STOP_4, TOP_LINE_T);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (randoms.get(i)) {
                    case 2:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 3:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 4:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            Gap(i);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    case 5:
                        if (horizontal || !lightCheckOnce.get(i)) {
                            turnRight(i, dt, radius, radius, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (randoms.get(i)) {
                    case 2:
                        Top2Bottom(i, StageHeight() + 2000, RIGHT_MIDDLE_LINE_L);
                        break;
                    case 3:
                        Right2LeftStop(i, STOP_2, BOTTOM_LINE_T);
                        break;
                    case 4:
                        Right2Left(i, -2000, TOP_LINE_T);
                        break;
                    case 5:
                        Bottom2Top(i, -2000, LEFT_MIDDLE_LINE_R);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (randoms.get(i)) {
                    case 3:
                        if ((!stoped4 && !stoped44) || !lightCheckOnce.get(i)) {
                            turnLeft(i, dt, radius2, radius2, 0);
                            lightCheckOnce.set(i, false);
                        }
                        break;
                }
                break;
            case 7:
                switch (randoms.get(i)) {
                    case 3:
                        Top2Bottom(i, StageHeight() + 2000, LEFT_LINE_L);
                        break;
                }
                break;
        }
    }

    public void myPath1(int i, double dt) {

        switch (pathNumbers.get(i)) {
            case 1:
                Left2RightStop(i, bottom_stop_1, bottomLine);
                break;
            case 2:
                if (priorityInter1.get(i)) {
                    Gap(i);
                }
                break;
            case 3:
                Left2RightStop(i, bottom_stop_2, bottomLine);
                break;
            case 4:
                if (randoms.get(i) == 1) {
                    Gap(i);
                } else if (randoms.get(i) == 2 && priorityInter2.get(i)) {
                    turnLeft(i, dt, rx_2, ry_2, 0);
                }
                break;
            case 5:
                if (randoms.get(i) == 1) {
                    Left2RightStop(i, StageWidth() + 1200, bottomLine);
                } else if (randoms.get(i) == 2) {
                    Bottom2TopStop(i, -1200, rightLine);
                }
                break;
        }

    }

    public void myPath2(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                Right2LeftStop(i, top_stop_1, topLine);
                break;
            case 2:
                if (randoms.get(i) == 1 && priorityInter2.get(i)) {
                    Gap(i);
                } else if (randoms.get(i) == 2 && priorityInter2.get(i)) {
                    turnRight(i, dt, rx_3, ry_3, 0);
                }
                break;
            case 3:
                if (randoms.get(i) == 1) {
                    Right2LeftStop(i, top_stop_2, topLine);
                } else if (randoms.get(i) == 2) {
                    Bottom2TopStop(i, -1200, rightLine);
                }
                break;
            case 4:
                if (randoms.get(i) == 1) {
                    Gap(i);
                }
                break;
            case 5:
                if (randoms.get(i) == 1) {
                    Right2LeftStop(i, -1200, topLine);
                }
                break;

        }

    }

    public void myPath3(int i, double dt) {
        switch (pathNumbers.get(i)) {
            case 1:
                Bottom2TopStop(i, under_stop, underLine);
                break;
            case 2:
                if (priorityInter1.get(i)) {
                    turnRight(i, dt, rx_1, ry_1, 0);
                }
                break;
            case 3:
                Left2RightStop(i, bottom_stop_2, bottomLine);
                break;
            case 4:
                if (randoms.get(i) == 1 && priorityInter2.get(i)) {
                    Gap(i);
                } else if (randoms.get(i) == 2 && priorityInter2.get(i)) {
                    turnLeft(i, dt, rx_2, ry_2, 0);
                }
                break;
            case 5:
                if (randoms.get(i) == 1) {
                    Left2RightStop(i, StageWidth() + 1200, bottomLine);
                } else if (randoms.get(i) == 2) {
                    Bottom2TopStop(i, -1200, rightLine);
                }
                break;

        }

    }

    public Integer random() {
        int random = -1;
        if (lvl1) {
            random = (int) (Math.random() * 2 + 1);
        } else if (lvl2) {
            random = (int) (Math.random() * 5 + 1);
        }
        return random;
    }

    public void turnLeft(int i, double dt, double radX, double radY, double V) {

        if (onces.get(i)) {
            dirCheckLeft(i);
            finalAngs.set(i, turnAngs.get(i) + Math.PI / 2);
            initialAngs.set(i, turnAngs.get(i));
            onces.set(i, false);
        }

        double timee = turnTimes.get(i);
        double ang = (V * timee) + initialAngs.get(i);
        double x = 0;
        double y = 0;
        if (V != 0) {
            x = Math.sin(ang) * radX * V;
            y = Math.cos(ang) * radY * V;
        } else if (V == 0) {
            ang = (circAccel * timee * timee) + initialAngs.get(i);
            x = -Math.sin(ang) * 2 * radX * Math.sqrt(circAccel * (ang - initialAngs.get(i)));
            y = -Math.cos(ang) * 2 * radY * Math.sqrt(circAccel * (ang - initialAngs.get(i)));
        }
        Vector a = new Vector(x, y);

        if (ang <= finalAngs.get(i) && directions.get(i).equals("r")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(270 - ((ang * 180) / Math.PI));
        } else if (ang <= finalAngs.get(i) && directions.get(i).equals("l")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(270 - ((ang * 180) / Math.PI));
        } else if (ang <= finalAngs.get(i) && directions.get(i).equals("u")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(270 - ((ang * 180) / Math.PI));
        } else if (ang <= finalAngs.get(i) && directions.get(i).equals("d")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(270 - ((ang * 180) / Math.PI));
        } else {
            onces.set(i, true);
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            cars.get(i).setAcceleration(new Vector(0, 0));
        }

        turnTimes.set(i, turnTimes.get(i) + dt);
    }

    public void turnRight(int i, double dt, double radX, double radY, double V) {

        if (onces.get(i)) {
            dirCheckRight(i);
            finalAngs.set(i, turnAngs.get(i) - Math.PI / 2);
            initialAngs.set(i, turnAngs.get(i));
            onces.set(i, false);
        }

        double timee = turnTimes.get(i);
        double ang = (-V * timee) + initialAngs.get(i);
        double x = 0;
        double y = 0;
        if (V != 0) {
            x = Math.sin(ang) * radX * V;
            y = Math.cos(ang) * radY * V;
        } else if (V == 0) {
            ang = (-circAccel * timee * timee) + initialAngs.get(i);
            x = Math.sin(ang) * 2 * radX * Math.sqrt(-circAccel * (ang - initialAngs.get(i)));
            y = Math.cos(ang) * 2 * radY * Math.sqrt(-circAccel * (ang - initialAngs.get(i)));
        }
        Vector a = new Vector(x, y);

        if (ang >= finalAngs.get(i) && directions.get(i).equals("r")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(90 - ((ang * 180) / Math.PI));
        } else if (ang >= finalAngs.get(i) && directions.get(i).equals("l")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(90 - ((ang * 180) / Math.PI));
        } else if (ang >= finalAngs.get(i) && directions.get(i).equals("u")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(90 - ((ang * 180) / Math.PI));
        } else if (ang >= finalAngs.get(i) && directions.get(i).equals("d")) {
            cars.get(i).setVelocity(a);
            cars.get(i).getRectangle().setRotate(90 - ((ang * 180) / Math.PI));
        } else {
            onces.set(i, true);
            pathNumbers.set(i, pathNumbers.get(i) + 1);
            cars.get(i).setAcceleration(new Vector(0, 0));
        }
        turnTimes.set(i, turnTimes.get(i) + dt);
    }

    public void light2Top() {
        light_1.toFront();
        light_2.toFront();
        light_3.toFront();
        light_4.toFront();
        light_5.toFront();
        light_6.toFront();
        light_7.toFront();
        light_8.toFront();
    }
    
    @FXML
    public void onSlide(MouseEvent event) 
    {
        velocityLabel.setText("Car Velocity: " + Math.round(velocitySlider.getValue()));
        speedLabel.setText("Network Speed: " + (double)(Math.round(speedSlider.getValue()*10)/10));
        rateLabel.setText("Rate: " + (double)(Math.round(rateSlider.getValue()*10)/10));
        SPEED_LABEL.setText(Math.round(velocitySlider.getValue())+"");
    }
    
    @FXML
    public void blockView() 
    {
       on ^= true;
            if(on){
                for (int i = 0; i < lvl2_rec.size(); i++) {
                    lvl2_rec.get(i).setVisible(true);
                }
            }
            else if (!on) {
                for (int i = 0; i < lvl2_rec.size(); i++) {
                    lvl2_rec.get(i).setVisible(false);
                }
            }
    }
    
    @FXML
    public void soundOn() 
    {
       musicOn ^= true;
            if(musicOn){
                backgroundMusic.stop();
            }
            else if (!musicOn) {
                backgroundMusic.play(0.1);
            }
    }
    
    private void menuCreator() {
        pane.setId("pane1");
        light_1.setId("red_light");
        light_2.setId("red_light");
        light_3.setId("red_light");
        light_4.setId("red_light");
        light_5.setId("red_light");
        light_6.setId("red_light");
        light_7.setId("red_light");
        light_8.setId("red_light");
        light_1.setVisible(false);
        light_2.setVisible(false);
        light_3.setVisible(false);
        light_4.setVisible(false);
        light_5.setVisible(false);
        light_6.setVisible(false);
        light_7.setVisible(false);
        light_8.setVisible(false);
        menuPane.setId("menuPane");
        menuPane.setVisible(false);      
        exit.setId("exit");
        minimise.setId("minimise");
        mirror.setId("mirror");
        mirror.setVisible(false);
        mirror.toFront();
        lvl1Button.setId("lvl1Button");
        lvl2Button.setId("lvl2Button");
        lvl3Button.setId("lvl3Button");
        velocitySlider.setId("slider");
        speedSlider.setId("slider");
        rateSlider.setId("slider");
        velocityLabel.setId("lables");
        speedLabel.setId("lables");
        rateLabel.setId("lables");
        blockCheck.setId("blockCheck");
        soundCheck.setId("blockCheck");
        checkLabel.setId("checkLabel");
        soundLabel.setId("checkLabel");
        menuButton.setId("menuButton");
        SPEED_LABEL.setId("speed");
        endLabel.setId("end");
        endLabel.setVisible(false);
        menuPane.toFront();
        SPEED_LABEL.toFront();
    }

    @FXML
    private void openLvl1(ActionEvent event) {
        speed = speedSlider.getValue();
        carSpeed = velocitySlider.getValue();
        rate = Math.round(100.0/rateSlider.getValue());
        circVelocity = carSpeed/200; 
        circAccel = carSpeed/400;
        reseter();
        h = 72;
        w = 180;
        blockCreator();
        light_1.setVisible(false);
        light_2.setVisible(false);
        light_3.setVisible(false);
        light_4.setVisible(false);
        light_5.setVisible(false);
        light_6.setVisible(false);
        light_7.setVisible(false);
        light_8.setVisible(false);
        lvl1 = true;
        lvl2 = false;
        lvl3 = false;
        pane.setId("pane1");

    }

    @FXML
    private void openLvl2(ActionEvent event) {
        speed = speedSlider.getValue();
        carSpeed = velocitySlider.getValue();
        rate = Math.round(100.0/rateSlider.getValue());
        circVelocity = carSpeed/200; 
        circAccel = carSpeed/400;
        reseter();
        light_1.setVisible(true);
        light_2.setVisible(true);
        light_3.setVisible(true);
        light_4.setVisible(true);
        light_5.setVisible(true);
        light_6.setVisible(true);
        light_7.setVisible(true);
        light_8.setVisible(true);
        h = 36;
        w = 90;
        radius = 30 + w / 2;
        radius2 = 90 + w / 2;
        lvl1 = false;
        lvl2 = true;
        lvl3 = false;
        turnPriority();
        pane.setId("pane2");
        
    }

    @FXML
    private void openLvl3(ActionEvent event) {
        reseter();
        lvl1 = false;
        lvl2 = false;
        lvl3 = true;
        pane.setId("pane3");
        endLabel.setVisible(true);
        light_1.setVisible(false);
        light_2.setVisible(false);
        light_3.setVisible(false);
        light_4.setVisible(false);
        light_5.setVisible(false);
        light_6.setVisible(false);
        light_7.setVisible(false);
        light_8.setVisible(false);
    }

    @FXML
    private void openMenu(ActionEvent event) {
        menuPane.setDisable(pane_disabled);
        menuPane.setVisible(!pane_disabled);
        mirror.setVisible(!pane_disabled);
        if (!pane_disabled) {
            pane.setOpacity(0.5);
        }
        if (pane_disabled) {
            pane.setOpacity(1);
        }
        pane_disabled ^= true;
    }

    @FXML
    private void Exit(ActionEvent event) {
        getStage2().close();
    }

    @FXML
    private void Minimise(ActionEvent event) {
        getStage2().setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        menuCreator();
        Play();

    }

}
