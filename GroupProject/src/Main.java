

import java.io.File;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.*;;

public class Main extends Application {
	Stage window;
	

	// Fields to hold settings data
	private int numComps;
	private int numHumans;
	private int totalPlayers;
	private Toggle mode;
	
	//Images
	Image playImage = new Image(getClass().getResourceAsStream("format/play.png"));
	Image playHoverImage = new Image(getClass().getResourceAsStream("format/playHover.bmp"));

	Image instructionsImage = new Image(getClass().getResourceAsStream("format/instructions.png"));
	Image instructionsHoverImage = new Image(getClass().getResourceAsStream("format/instructionsHover.bmp"));

	BoardGUI gui = new BoardGUI(totalPlayers);
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.resizableProperty().setValue(Boolean.FALSE);

		
		/* Create Menu Scene */
		VBox menuLayout = new VBox();
		Scene menu = new Scene(menuLayout, 650, 650);
		menu.getStylesheets().add("style.css");
		
		
		Label quoridorLabel = new Label("Quoridor!");
		quoridorLabel.getStyleClass().add("buttons");
		menuLayout.setAlignment(Pos.CENTER);
		ImageView playButtonForMenu = new ImageView(playImage);		
		ImageView instructionsButtonForMenu = new ImageView(instructionsImage);
		
		
		HBox buttons = new HBox();
		buttons.getChildren().addAll(playButtonForMenu, instructionsButtonForMenu);

		menuLayout.getChildren().addAll(quoridorLabel, buttons);
		buttons.setAlignment(Pos.CENTER);

		// Instructions Layout
		VBox instructionsLayout = new VBox();
		Scene instructions = new Scene(instructionsLayout, 650, 650);
		instructions.getStylesheets().add("style.css");

		// Settings Layout
		VBox settingsLayout = new VBox(20);
		Scene settings = new Scene(settingsLayout, 650, 650);
		
		Label settingsLabel = new Label("Settings");
		Button menuButtonForSettings = new Button("Back");
		
		Image playerImage = new Image(getClass().getResourceAsStream("format/player.png"));		
		ImageView player = new ImageView(playerImage);
		ComboBox playerBox = new ComboBox();
		playerBox.getItems().addAll("2","3","4");
		playerBox.setValue("2");
		HBox playerCol = new HBox(20);
		playerCol.getChildren().addAll(player, playerBox);
		
		final ToggleGroup mode = new ToggleGroup();
		ToggleButton toSides = new ToggleButton("Sides");
		toSides.setToggleGroup(mode);
		toSides.setSelected(true);
		
		ToggleButton toCorners = new ToggleButton("Sides");
		toCorners.setToggleGroup(mode);
		toCorners.setSelected(false);
		
		Button playButton = new Button("Play");

		settingsLayout.getChildren().addAll(settingsLabel, menuButtonForSettings, playerCol, toSides,
				toCorners, playButton);
		settings.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		/** Buttons **/
		playButtonForMenu.setOnMouseClicked(e -> {
			window.setScene(settings);
		});
		
		playButtonForMenu.setOnMouseEntered(e -> {
			playButtonForMenu.setImage(playHoverImage);
		});
		
		playButtonForMenu.setOnMouseExited(e -> {
			playButtonForMenu.setImage(playImage);
		});

		playButton.setOnMouseClicked(e -> {
			numHumans = Integer.parseInt((String) playerBox.getValue());
			 //TODO : Work out what mode should be (String?)
			
			Toggle ToggleMode = mode.getSelectedToggle();
			totalPlayers = numComps + numHumans;
			if (totalPlayers >= 2 && totalPlayers <= 4)
				window.setScene(gui.getScene());
		});
		
		instructionsButtonForMenu.setOnMouseEntered(e -> {
			instructionsButtonForMenu.setImage(instructionsHoverImage);
		});
		
		instructionsButtonForMenu.setOnMouseExited(e -> {
			instructionsButtonForMenu.setImage(instructionsImage);
		});
		
		

		instructionsButtonForMenu.setOnMouseClicked(e -> {
			window.setScene(instructions);
		});

		window.setScene(menu);
		window.setTitle("Quoridor");
		window.show();
	}

	public int getNumHumans() {
		return numHumans;
	}

	public int getNumComputers() {
		return numComps;
	}

	public int getTotalPawns() {
		return totalPlayers;
	}

	public Toggle getMode() {
		return mode;
	}
}