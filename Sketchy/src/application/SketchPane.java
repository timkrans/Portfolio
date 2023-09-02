package application;

//Description: builds gui and sets button and mouse 
//event to handlers and 
//then sets up the handlers

import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class SketchPane extends BorderPane {
	
	//Task 1: Declare all instance variables listed in UML diagram
	private ArrayList <Shape> tempList;
	private ArrayList <Shape> shapeList;
	private Button undoButton;
	private Button eraseButton;
	private Label fillColorLabel;
	private Label strokeColorLabel;
	private Label strokeWidthLabel;
	private ComboBox<String> fillColorCombo;
	private ComboBox<String> strokeWidthCombo;
	private ComboBox<String> strokeColorCombo;
	private RadioButton radioButtonLine;
	private RadioButton radioButtonRectangle;
	private RadioButton radioButtonCircle;
	private Pane sketchCanvas;
	private Color[]colors;
	private String[]strokeWidth;
	private String[]colorLabels;
	private Color currentStrokeColor;
	private Color currentFillColor;
	private int currentStrokeWidth;
	private Line line;
	private Circle circle;
	private Rectangle rectangle;
	private double x1;
	private double y1;
	//Task 2: Implement the constructor
	public SketchPane() {
		// Colors, labels, and stroke widths that are available to the user
		colors = new Color[] {Color.BLACK, Color.GREY, Color.YELLOW, Color.GOLD, Color.ORANGE, Color.DARKRED, Color.PURPLE, Color.HOTPINK, Color.TEAL, Color.DEEPSKYBLUE, Color.LIME} ;
		colorLabels = new String[] {"black", "grey", "yellow", "gold", "orange", "dark red", "purple", "hot pink", "teal", "deep sky blue", "lime"};
		fillColorLabel = new Label("Fill Color:");
		strokeColorLabel = new Label("Stroke Color:");
		strokeWidthLabel = new Label("Stroke Width:");
		strokeWidth = new String[] {"1", "3", "5", "7", "9", "11", "13"};   
		currentStrokeColor = colors[0];
		currentFillColor = colors[0];
		currentStrokeWidth = 1;
		//implement the two shape array list
		this.tempList = new ArrayList <>();
		this.shapeList = new ArrayList <>();
		//created fill color and set it equal to handler
		fillColorCombo = new ComboBox<>();
		fillColorCombo.setValue(colorLabels[0]);
		fillColorCombo.getItems().addAll(colorLabels);
		fillColorCombo.setOnAction(new ColorHandler());
		//created stroke color and set it equal to handler
		strokeColorCombo = new ComboBox<>();
		strokeColorCombo.setValue(colorLabels[0]);
		strokeColorCombo.getItems().addAll(colorLabels);
		strokeColorCombo.setOnAction(new ColorHandler());
		//created stroke width and set it equal to handler
		strokeWidthCombo = new ComboBox<>();
		strokeWidthCombo.setValue(strokeWidth[0]);
		strokeWidthCombo.getItems().addAll(strokeWidth);
		strokeWidthCombo.setOnAction(new WidthHandler());
		//Set up the radio Button with toggle group and line is selected
		ToggleGroup toggleGroup = new ToggleGroup();
		radioButtonLine = new RadioButton();
		Label buttonLine = new Label("Line");
		radioButtonRectangle = new RadioButton();
		Label buttonRectangle = new Label("Rectangle");
		radioButtonCircle =  new RadioButton();
		Label buttonCircle = new Label("Circle");
		radioButtonLine.setToggleGroup(toggleGroup);
		radioButtonRectangle.setToggleGroup(toggleGroup);
		radioButtonCircle.setToggleGroup(toggleGroup);
		radioButtonLine.setSelected(true);
		//set up the erase and undo and set them to handler
		undoButton = new Button("Undo");
		undoButton.setOnAction(new ButtonHandler());
		eraseButton = new Button("Erase");
		eraseButton.setOnAction(new ButtonHandler());
		//set up GUI
		sketchCanvas = new Pane();
		sketchCanvas.setStyle("-fx-background-color: White;");
		HBox hBoxCombo = new HBox();
		hBoxCombo.getChildren().addAll(fillColorLabel,fillColorCombo,strokeWidthLabel,strokeWidthCombo,strokeColorLabel,strokeColorCombo);
		hBoxCombo.setMinWidth(20);
		hBoxCombo.setMinHeight(40);
		hBoxCombo.setAlignment(Pos.CENTER);
		hBoxCombo.setStyle("-fx-background-color: LightGrey;");
		HBox hBoxButton = new HBox();
		hBoxButton.getChildren().addAll(radioButtonLine,buttonLine,radioButtonRectangle, buttonRectangle, radioButtonCircle,buttonCircle, undoButton, eraseButton);
		hBoxButton.setMinWidth(20);
		hBoxButton.setMinHeight(40);
		hBoxButton.setAlignment(Pos.CENTER);
		hBoxButton.setStyle("-fx-background-color: LightGrey;");
		//add to the borderpane
		this.setCenter(sketchCanvas);
		this.setTop(hBoxCombo);
		this.setBottom(hBoxButton);
		//set handler
		sketchCanvas.setOnMousePressed(new MouseHandler());
		sketchCanvas.setOnMouseDragged(new MouseHandler());
		sketchCanvas.setOnMouseReleased(new MouseHandler());
		x1=0;
		y1=0;
 }

	private class MouseHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TASK 3: Implement the mouse handler for Circle and Line
			// Rectange Example given!
			if (radioButtonRectangle.isSelected()) {
				//Mouse is pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					x1 = event.getX();
					y1 = event.getY();
					rectangle = new Rectangle();
					rectangle.setX(x1);
					rectangle.setY(y1);
					shapeList.add(rectangle);
					rectangle.setFill(Color.WHITE);
					rectangle.setStroke(Color.BLACK);	
					sketchCanvas.getChildren().add(rectangle);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					rectangle.setWidth(Math.abs(event.getX() - x1));
					rectangle.setHeight(Math.abs(event.getY() - y1));

				}
				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					rectangle.setFill(currentFillColor);
					rectangle.setStroke(currentStrokeColor);
					rectangle.setStrokeWidth(currentStrokeWidth);
				}
			}
			//circle handler
			if (radioButtonCircle.isSelected()) {
				//mouse pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					x1 = event.getX();
					y1 = event.getY();
					circle = new Circle();
					circle.setCenterX(x1);
					circle.setCenterY(y1);
					shapeList.add(circle);
					circle.setFill(Color.WHITE);
					circle.setStroke(Color.BLACK);	
					sketchCanvas.getChildren().add(circle);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					circle.setRadius(getDistance(x1,y1,event.getX(),event.getY()));
				}
				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					circle.setFill(currentFillColor);
					circle.setStroke(currentStrokeColor);
					circle.setStrokeWidth(currentStrokeWidth);
				}
			}
			//line hnadler
			if (radioButtonLine.isSelected()) {
				//mouse pressed
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					x1 = event.getX();
					y1 = event.getY();
					line = new Line();
					line.setStartX(x1);
					line.setStartY(y1);
					shapeList.add(line);
					line.setFill(Color.WHITE);
					line.setStroke(Color.BLACK);	
					sketchCanvas.getChildren().add(line);
				}
				//Mouse is dragged
				else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					line.setEndX(event.getX());
					line.setEndY(event.getY());
				}
				//Mouse is released
				else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
					line.setFill(currentFillColor);
					line.setStroke(currentStrokeColor);
					line.setStrokeWidth(currentStrokeWidth);
				}
			}
		}
	}
		
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			// TASK 4: Implement the button handler
			//undo button handler
			if(event.getSource() == undoButton) {
				if(shapeList.size()>0){
					sketchCanvas.getChildren().remove(shapeList.get(shapeList.size()-1));
					shapeList.remove(shapeList.size()-1);
				}
				else {
					shapeList.addAll(tempList);
					tempList.removeAll(tempList);
					sketchCanvas.getChildren().addAll(shapeList);
				}
			}
			//erase button handler
			if(event.getSource() == eraseButton) {
				sketchCanvas.getChildren().removeAll(shapeList);
				tempList.addAll(shapeList);
				shapeList.removeAll(shapeList);
			}
		}
	}

	private class ColorHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			// TASK 5: Implement the color handler
			//fill color handler
			if(event.getSource() == fillColorCombo) {
				currentFillColor = colors[fillColorCombo.getSelectionModel().getSelectedIndex()];
			}
			//stroke color handler
			if(event.getSource() == strokeColorCombo) {
				currentStrokeColor = colors[strokeColorCombo.getSelectionModel().getSelectedIndex()];
			}
		}
	}
	
	private class WidthHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event){
			// TASK 6: Implement the stroke width handler
			currentStrokeWidth = Integer.parseInt(strokeWidth[strokeWidthCombo.getSelectionModel().getSelectedIndex()]);
		}
	}
	
		
	// Get the Euclidean distance between (x1,y1) and (x2,y2)
 private double getDistance(double x1, double y1, double x2, double y2)  {
     return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
 }

}
