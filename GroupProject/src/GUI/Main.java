package GUI;

import java.awt.Window;
import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.image.*;;

public class Main extends Application {
	Stage window;

	Button playButton;
	Button playSettingsButton;
	Button instructionsButton;
	Button menuButton, playMenuButton, settingsMenuButton, instructionsMenuButton;

	Scene menu, instructions, play, settings;

	// Fields to hold settings data
	private int numComps;
	private int numHumans;
	private int totalPlayers;
	private Toggle mode;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		/** Create Components **/
		settingsMenuButton = new Button("Menu");
		playMenuButton = new Button("Menu");
		instructionsMenuButton = new Button("Menu");

		Image playImage = new Image(getClass().getResourceAsStream("src/play.png"));
		playButton = new Button("Play", new ImageView(playImage));

		playSettingsButton = new Button("Play");

		Image instructionsImage = new Image(getClass().getResourceAsStream("src/instructions.png"));
		instructionsButton = new Button("Instructions", new ImageView(instructionsImage));

		Label quorridorLabel = new Label("Quorridor");
		quorridorLabel.setId("quorridorLabel");
		Label instructionsLabel = new Label("Instructions");
		Label playLabel = new Label("Play");
		Label settingsLabel = new Label("Settings");

		// Settings Controls
		numHumans = 0;
		numComps = 0;
		totalPlayers = 0;

		ObservableList<String> playerList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
		ComboBox<String> playerBox = new ComboBox<String>(playerList);
		ObservableList<String> computerList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
		ComboBox<String> computerBox = new ComboBox<String>(computerList);

		final ToggleGroup toggleMode = new ToggleGroup();
		RadioButton toSides = new RadioButton("Sides");
		toSides.setToggleGroup(toggleMode);
		toSides.setSelected(true);
		RadioButton toCorners = new RadioButton("Corners");
		toCorners.setToggleGroup(toggleMode);

		/** Create Layout(s) **/
		// Menu Layout
		VBox menuLayout = new VBox(20);
		menuLayout.getChildren().addAll(quorridorLabel, playButton, instructionsButton);
		menu = new Scene(menuLayout, 500, 500);
		menu.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		// Instructions Layout
		VBox instructionsLayout = new VBox(20);
		instructionsLayout.getChildren().addAll(instructionsLabel, instructionsMenuButton);
		instructions = new Scene(instructionsLayout, 500, 500);
		instructions.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		// Play Layout
		VBox playLayout = new VBox(20);
		playLayout.getChildren().addAll(playLabel, playMenuButton);
		play = new Scene(playLayout, 500, 500);
		play.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		// Settings Layout
		VBox settingsLayout = new VBox(20);
		settingsLayout.getChildren().addAll(settingsLabel, settingsMenuButton, playerBox, computerBox, toSides,
				toCorners, playSettingsButton);
		settings = new Scene(settingsLayout, 500, 500);
		settings.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		/** Buttons **/
		settingsMenuButton.setOnAction(e -> {
			window.setScene(menu);
		});

		playMenuButton.setOnAction(e -> {
			window.setScene(menu);
		});

		instructionsMenuButton.setOnAction(e -> {
			window.setScene(menu);
		});

		playButton.setOnAction(e -> {
			playerBox.setValue("1");
			computerBox.setValue("1");
			window.setScene(settings);
		});

		playSettingsButton.setOnAction(e -> {
			numComps = Integer.parseInt(computerBox.getValue());
			numHumans = Integer.parseInt(playerBox.getValue());
			// TODO : Work out what mode should be (String?)

			Toggle mode = toggleMode.getSelectedToggle();
			totalPlayers = numComps + numHumans;
			if (totalPlayers >= 2 && totalPlayers <= 4)
				window.setScene(play);
		});

		instructionsButton.setOnAction(e -> {
			window.setScene(instructions);
		});

		window.setScene(menu);
		window.setTitle("Quorridor");
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