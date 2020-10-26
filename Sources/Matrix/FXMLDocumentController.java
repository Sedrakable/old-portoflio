/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.net.URL;
import java.text.DecimalFormat;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


import static matrix.Matrix.getStage;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public Label Title;
    
    @FXML
    public AnchorPane pane;
    

    
    
    @FXML
    private Pane pane3 = new Pane();
    
    @FXML
    private GridPane gridPane = new GridPane();
    
    
    
    @FXML
    private ScrollPane scrollPane = new ScrollPane();
   
    @FXML
    private ChoiceBox<Integer> RowBox;
    
    @FXML
    private ChoiceBox<Integer> ColumbBox;
    
    @FXML
    private Label LabelRows;
    
    @FXML
    private Label LabelColumbs;
    
    @FXML
    private Label ErrorLabel;
    
    @FXML
    private Button StartButton;   
    
    @FXML
    private Button reduce;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Button minButton;
    
    @FXML
    int NumberRows = 0;
    
    @FXML
    int NumberColumbs = 0;
    
    @FXML
    int l = 1;
    
    @FXML
    int m = 0;
    
    @FXML
    int n = 0;
    
    @FXML
    int numo = 1;
    
    @FXML
    private double[][] numberField  = null;
    
    @FXML
    private TextField[][] ArrayField  = null;
    
    @FXML
    private ArrayList<Label> ArrayLabel  = new ArrayList<>();
    
    @FXML
    private ArrayList<Label> numLabel  = new ArrayList<>();
    
    @FXML
    private ArrayList<Label> actionLabel  = new ArrayList<>();
    
    @FXML
    private void numberGetter()
    {
        numberField = new double[NumberRows][NumberColumbs];
        int num = 0; 
        for (int i = 0; i < NumberRows; i++) 
        {
            for (int j = 0; j < NumberColumbs; j++) 
            {
                try 
                {   
                    String boi = ArrayField[i][j].getText();             
                    num = Integer.parseInt(boi);
                    numberField[i][j] = num;
                    ErrorLabel.setText("good");
                } 
                catch (Exception e) 
                {
                    ErrorLabel.setText("Every block has to be filled or be a number.");
                    num = 0;
                }
            }
        }          
    }
    
    @FXML
    private void matrixReducer(ActionEvent event)
    {
        RedReseter();
        numberGetter();   
        paneExpander(); 
        matrixPrint(numberField,l);
        l++;
        labelMatrixCreator(0,0,"null");
        int count = 0;
        if(NumberColumbs -1 >= NumberRows)
        {
            count = NumberRows;
        }
        
        if(NumberColumbs -1 < NumberRows)
        {
            count = NumberColumbs-1;
        }
        
        for (int k = 0; k < count; k++) 
        {
            ZeroCheck();
            ColumbReducer(k);
            ZeroCheck();
            TopOne(k);
            ZeroCheck();
            if(k > 0)
            {
                ColumbEquilizer(k);
                ZeroCheck();
            }
            AutoReducer(k);       
        }
        ZeroCheck();
        CleanUp();
        ZeroCheck();
        CleanUp2();
        matrixPrint(numberField,l);
        labelMatrixCreator(0,0,"null");         
    }
    
    @FXML
    private void AutoReducer(int b)
    {
        for (int i = 0; i < NumberRows; i++) {            
                if(i != b && numberField[i][b] != 0)
                {
                    RowSub(i,b);
                }
        }
    }
    
    @FXML
    private void CleanUp2()
    {
        double x = 0;
        
        for (int i = 0; i < NumberRows; i++) 
        {
            int num = 0;
            for (int j = 0; j < NumberColumbs-1; j++) 
            {
                x = numberField[i][j];
                if(x == 0)
                {
                    num++;
                    
                }
                
            }
            if(num == NumberColumbs-1)
            {
                RowDiv(i,numberField[i][NumberColumbs-1]);
            }
        }          
    }
    
    @FXML
    private void ZeroCheck()
    {
        for (int i = 0; i < NumberRows; i++) 
        {
            for (int j = 0; j < NumberColumbs; j++) 
            {
                double x = numberField[i][j];
                if(x < 0.0000001 && x > -0.0000001)
                {
                    numberField[i][j] = 0;         
                }                
            }
        }   
    }
    
    @FXML
    private void CleanUp()
    {
        double x = 0;
        int count = 0;
        if(NumberColumbs -1 >= NumberRows)
        {
            count = NumberRows;
        }
        
        if(NumberColumbs -1 < NumberRows)
        {
            count = NumberColumbs-1;
        }
        
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < NumberRows; j++) {
                x = numberField[j][i];
                if(x != 0)
                {
                    RowDiv(j,x);
                }
                
            }
        }  
    }
    
    
    @FXML
    private void RowSub(int b, int a)
    {
        
        System.out.println("Operation(RowSub): " + b + " - " + a);
        
        for (int i = 0; i < NumberColumbs; i++) 
        {
            numberField[b][i] = numberField[b][i] - numberField[a][i] ;
        }
        
        matrixPrint(numberField,l);
        l++;
        labelMatrixCreator(b,a,"RowSub");
    }
    
    @FXML
    private void RowMult(int a,double x)
    {
        
        System.out.println("Operation(RowMult): " + a + " * x:" + x);
        
        for (int i = 0; i < NumberColumbs; i++) 
        {
            numberField[a][i] = numberField[a][i] * x;
        }
        matrixPrint(numberField,l);
        l++;
        labelMatrixCreator(a,x,"RowMult");
    }
    
    @FXML
    private void RowDiv(int a,double x)
    {
        
        System.out.println("Operation(RowDiv): " + a + " / x:" + x);
        
        for (int i = 0; i < NumberColumbs; i++) 
        {
            numberField[a][i] = numberField[a][i]/x;
        }   
        matrixPrint(numberField,l);
        l++;
        labelMatrixCreator(a,x,"RowDiv");
    }
    
    
    @FXML
    private void ColumbReducer(int a)
    {
        
        System.out.println("Operation(ColumbReducer): I just made sure that Columb " + a + " has only ones and zeros" );
    
        for (int i = a; i < NumberRows; i++) 
        {
            double x = numberField[i][a];
            if(x != 0)
            {
                RowDiv(i, x);
            }    
        }
        matrixPrint(numberField,l);
        l++;
    }
    
    @FXML
    private void ColumbEquilizer(int a)
    { 
        System.out.println("Operation(ColumbEquilizer): I just made sure that Columb " + a + " has all the same numbers" );
        double num = 0;
        int t = 0;
        for (int i = a-1; i >= 0; i--) {
            
            if(t == 0)
            {
                num = numberField[i][a];
            }
            
            if(num != 0)
            {
                t = 1;
            }
        }
        
        for (int i = 0; i < NumberRows; i++) 
        {
            double x = numberField[i][a];
            if(x != 0 && num != 0)
            {
                double y = num/x;
                RowMult(i,y) ;
            }
        }
        matrixPrint(numberField,l);
        l++;
    }
    
    // AT THE END
    
    @FXML
    private boolean RowCheck(int a,int b)
    {
        
        System.out.println("Operation(RowCheck): this is to make sure that rows " + a + " and " + b + " have worth while operations" );
        
        boolean boi = false;
        int y = 0;
        int n = 0;
        for (int i = 0; i < NumberColumbs - 1; i++) 
        {
            if(numberField[a][i] == numberField[b][i] && numberField[a][i] != 0 && numberField[b][i] != 0)
            {
                y++;
            }
            
            if(numberField[a][i] != numberField[b][i])
            {
                n++;
            }
        }
        
        if(y >= n)
        {
            boi = true;
        }
        matrixPrint(numberField,l);
        l++;
        return boi;
        
    }
    
    @FXML
    private void RowSwitcher(int a, int b)
    {
        
        System.out.println("Operation(RowSwitcher): this switches rows " + a + " and " + b);
        
        double x = 0;
        for (int i = 0; i < NumberColumbs; i++) 
        {
            x = numberField[a][i];
            numberField[a][i] = numberField[b][i];
            numberField[b][i] = x;
        }
        matrixPrint(numberField,l);
        l++;
        labelMatrixCreator(a,b,"RowSwitcher");
        
    }
    
    @FXML
    private void TopOne(int b)
    {
        
        System.out.println("Operation(TopOne): this makes sure that in columb " + b  + " the 1 is on top");
        
        int num = 0;
        int x = 0;
        for (int i = b; i < NumberRows; i++) 
        {
            if(numberField[i][b] == 1 && x == 0)
            {
                x++;
                num = i;
            }      
        }
        
        if(x == 1)
        {
            RowSwitcher(b,num);
        }
        matrixPrint(numberField,l);
        l++;
        
    }
    
    
    @FXML
    private void matrixPrint(double[][] matrix, int l)
    {
        String print = " " + l + ": " + "\n";
        
        for (int i = 0; i < NumberRows; i++) 
        {
            for (int j = 0; j < NumberColumbs; j++) 
            {               
               double y = matrix[i][j];
               String formattedString = String.format("%.02f", y);
               if(j == 0)
               {
                   print = print + " [  ";
               }
               
               
               
               if(j == NumberColumbs - 1)
               {
                   print = print + " | " + formattedString + "  ] " + "\n";
               }
               else
               {
                   print = print + " " + formattedString + " " ;
               }
     
            }
        }
        
        
        System.out.println(print);

    }
    
    @FXML
    private void paneExpander()
    {
        getStage().setWidth(1600);
        pane.getChildren().add(scrollPane);
        
        scrollPane.setPrefSize(800,800);
        scrollPane.setLayoutX(800);
        scrollPane.setLayoutY(0);
        scrollPane.setId("scroll");
        
        scrollPane.setContent(pane3);
        scrollPane.setFitToWidth(true);
        pane3.setId("pane3");
        pane3.setPrefWidth(800);
        pane3.setMinHeight(800);
        pane3.setLayoutX(800);
        pane3.setLayoutY(0);
    }
    
    @FXML
    private void labelMatrixCreator(int a, double b, String c)
    {
            
            
            
            Label numero = new Label();
            
            numero.setId("numero");
            numero.setText(Integer.toString(numo));
            
            numero.setPrefWidth(40);
            numero.setPrefHeight(20);
            numero.setLayoutX(20 +(m*62*NumberColumbs));
            numero.setLayoutY(16 +(n*45*NumberRows));
            pane3.getChildren().add(numero);
            
            Rectangle rectangle = new Rectangle(20+ (45*(NumberColumbs-1)) +(m*62*NumberColumbs), 40+(n*45*NumberRows), 4, (30*NumberRows)-5);
            rectangle.setId("rec");
            
            pane3.getChildren().add(rectangle);
            
            for (int i = 0; i < NumberRows; i++) 
            {
                for (int j = 0; j < NumberColumbs; j++) 
                {
                    double x = numberField[i][j];
                    String y = Double.toString(x);
                    if(x != 0 && new Fraction(x).getDenominator() != 1)
                    {
                        y = new Fraction(x).toString();
                        if(y.length() > 4)
                        {
                            DecimalFormat df2 = new DecimalFormat("#.##");
                            y = df2.format(x);
                        }
                        
                    }
                    if( x == 0 || new Fraction(x).getDenominator() == 1)
                    {
                        int z = (int) x;
                        y = Integer.toString(z);
                    }
                    
                    Label boi = new Label();
                    boi.setText(y);
                    boi.setId("labels");
                    boi.setPrefWidth(40);
                    boi.setPrefHeight(25);
                    boi.setMaxHeight(25);
                    if(j < NumberColumbs-1)
                    {
                        boi.setLayoutX(20+ (45*j) +(m*62*NumberColumbs));
                    }
                    else
                    {
                        boi.setLayoutX(29 + (45*j) +(m*62*NumberColumbs));
                    }
                    boi.setLayoutY(40+ (30*i) +(n*45*NumberRows));
                    
                    
                    Label act = new Label();
                    act.setId("rowActions");
                    String mess = "";
                    if(j == NumberColumbs-1)
                    {
                        act.setLayoutX(29+ (45*(j+1)) +(m*62*NumberColumbs));
                        act.setLayoutY(40+ (30*i) +(n*45*NumberRows));
                    
                        if(c.equals("RowMult") && i == a)
                        {
                            mess = "[R"+(a+1)+"]*"+(int)b;
                        }
                        if(c.equals("RowDiv") && i == a)
                        {
                            mess ="[R"+(a+1)+"]/"+(int)b;
                        }
                        if(c.equals("RowSub") && i == b)
                        {
                            mess = "[R"+((int)b+1)+"]-[R"+(a+1)+"]";
                        }
                        if(c.equals("RowSwitcher") && i == a)
                        {
                            mess = "[R"+(a+1)+"]<->[R"+(int)b +"]";
                        }
                    }
                    act.setText(mess);                
                    
                    
                    pane3.getChildren().add(boi);
                    pane3.getChildren().add(act);
                    
                    actionLabel.add(act);
                    ArrayLabel.add(boi);
                    numLabel.add(numero);
               }
            }

            m++;
            if(m==3 && NumberColumbs <= 4)
            {
                m = 0;
                n++;
            }
            if(m==2 && NumberColumbs > 4)
            {
                m = 0;
                n++;
            }
            if(m==1 && NumberColumbs > 6)
            {
                m = 0;
                n++;
            }
            numo++;
    }
       
    
    
    @FXML
    private void menuCreator()
    {
        
        
        for (int i = 1; i <= 10; i++) {
            RowBox.getItems().add(i);
            ColumbBox.getItems().add(i);
        }
        
    }
    
    
    @FXML
    private void textFieldCreator()
    {
       
        try
        {           
            NumberRows = RowBox.getValue();
            NumberColumbs = ColumbBox.getValue();
            ArrayField  = new TextField[NumberRows][NumberColumbs];          
       
                    //gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
                    gridPane.setVgap(5); 
                    gridPane.setHgap(5);       
      
                    
            for (int i = 0; i < NumberRows; i++) 
            {
                for (int j = 0; j < NumberColumbs; j++) 
                {

                TextField boi = new TextField();

                boi.setPrefWidth(50);
                boi.setPrefHeight(30); 
                
                gridPane.add(boi, j, i);

                boi.setId("field");
                ArrayField[i][j] = boi;
                ErrorLabel.setText("Nice");
                }
            }
            gridPane.setLayoutX(((800 - ((50*NumberColumbs)+(5*(NumberColumbs-1))))/2));
            
            gridPane.setTranslateY(200);
            pane.getChildren().add(gridPane);
        }
        catch(Exception e)
        {
            ErrorLabel.setText("Please put in numbers");
        }  
    }
    
    
    
    @FXML
    private void RedReseter(){
        for (int i = 0; i < ArrayLabel.size(); i++) {
            pane3.getChildren().remove(ArrayLabel.get(i));
            
        }
        ArrayLabel.clear();
        
        for (int i = 0; i < numLabel.size(); i++) {
            pane3.getChildren().remove(numLabel.get(i));
           
        }
        numLabel.clear();
        
        for (int i = 0; i < actionLabel.size(); i++) {
            pane3.getChildren().remove(actionLabel.get(i));
           
        }
        actionLabel.clear();
        
        getStage().setWidth(800);
        pane.getChildren().remove(scrollPane);
        pane3.getChildren().clear();
        
        l = 1;
        m = 0;
        n = 0;
        numo = 1;
    }
    
    @FXML
    private void StartReseter()
    {
        for (int i = 0; i < ArrayLabel.size(); i++) {
            pane3.getChildren().remove(ArrayLabel.get(i));
            
        }
        ArrayLabel.clear();
        
        for (int i = 0; i < numLabel.size(); i++) {
            pane3.getChildren().remove(numLabel.get(i));
           
        }
        numLabel.clear();
        
        for (int i = 0; i < actionLabel.size(); i++) {
            pane3.getChildren().remove(actionLabel.get(i));
           
        }
        actionLabel.clear();
        
        for (int i = 0; i < NumberRows; i++) 
        {
            for (int j = 0; j < NumberColumbs; j++) 
            {
                pane.getChildren().remove(ArrayField[i][j]);
                
                ArrayField[i][j] = null;
            }        
        }
        
        gridPane.getChildren().clear();
        pane.getChildren().remove(gridPane);
        
        
        getStage().setWidth(800);
        pane.getChildren().remove(scrollPane);
        pane3.getChildren().clear();
        
        l = 1;
        m = 0;
        n = 0;
        numo = 1;
    }
    
    
    
    @FXML
    private void Start(ActionEvent event)
    {
        StartReseter();
        
        textFieldCreator();
        
        
    }
    
    @FXML
    private void Exit(ActionEvent event)
    {
        getStage().close();
      
    }
    
    @FXML
    private void Minimise(ActionEvent event)
    {
        getStage().setIconified(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuCreator();
        reduce.setId("reduce");
        reduce.setText("Reduce");
        StartButton.setId("start");
        StartButton.setText("Start");
        RowBox.setId("row_box");
        ColumbBox.setId("col_box");
        exitButton.setId("exit");
        minButton.setId("minimise");
        pane.setId("big_pane");
        Title.setId("title");
        
    }    

    

    
    
}
