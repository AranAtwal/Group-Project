
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to handle graphical representations of board and handle user input.
 * Separate to @see BoardModel as board model handles non grpahical input and
 * logic, however, does need to utilise methods set in BoardModel in order to
 * confirm that graphic change is required.
 * 
 * @author Jordan
 *
 */
public class BoardGUI {
	// BoardModel board = new BoardModel(2);
	int[][] boardArray = new int[18][18];
	Stage window;
	private int currentPlayer;

	public Scene BoardGui;

	private ImageView[][] guiArray;

	// private boolean isSmallWallTaken;
	private boolean isPlacedWall;
	private int numPlayers;

	// Images
	Image emptySpaceImage = new Image(getClass().getResourceAsStream("format/emptySpace.png"));
	Image emptyVerticalWallImage = new Image(getClass().getResourceAsStream("format/emptyVWall.png"));
	Image emptyHorizontalWallImage = new Image(getClass().getResourceAsStream("format/emptyHWall.png"));
	Image emptySmallWallImage = new Image(getClass().getResourceAsStream("format/emptySmallWall.png"));
	Image takenVerticalWallImage = new Image(getClass().getResourceAsStream("format/takenVWall.png"));
	Image hoverVerticalWallImage = new Image(getClass().getResourceAsStream("format/emptyVWallHover.png"));
	Image takenHorizontalWallImage = new Image(getClass().getResourceAsStream("format/hoverHWallTaken.png"));
	Image hoverSmallImage = new Image(getClass().getResourceAsStream("format/hoverSmallWall.png"));
	Image hoverHorizontalWallImage = new Image(getClass().getResourceAsStream("format/hoverHWall.png"));
	Image hoverSmallWallTaken = new Image(getClass().getResourceAsStream("format/hoverSmallWallTaken.png"));

	public BoardGUI(int numPlayers) {

		BoardModel model = new BoardModel(numPlayers);
		this.numPlayers = numPlayers;

	}

	public Scene getScene() {
		Label playLabel = new Label("Play!");
		guiArray = new QrImageView[17][17];
		// String boardString = board.toString();

		// Populate Image array
		for (int i = 0; i <= 16; i++) {
			for (int y = 0; y <= 16; y++) {
				if (i % 2 == 0 && y % 2 == 0) {

					QrImageView image = new QrImageView(i, y, 0);
					image.setImage(emptySpaceImage);

					image.setOnMouseClicked(e -> {
						int player = getCurrentPlayer();
						image.setPlayer(player);

					});

					guiArray[i][y] = image;

					if (numPlayers <= 2) {
						QrImageView pawn1 = new QrImageView(8, 0, 1);
						QrImageView pawn2 = new QrImageView(8, 16, 2);
						guiArray[8][0] = pawn1;
						guiArray[8][16] = pawn2;
					}
					if (numPlayers <= 3) {
						QrImageView pawn3 = new QrImageView(0, 8, 3);
						guiArray[0][8] = pawn3;
					}
					if (numPlayers <= 4) {
						QrImageView pawn4 = new QrImageView(16, 8, 4);
						guiArray[16][8] = pawn4;
					}

				}

				// Fills the space between two walls
				else if (i % 2 == 1 && y % 2 == 1) {

					QrImageView emptySmallWall = new QrImageView(i, y, false);
					emptySmallWall.setImage(emptySmallWallImage);
					guiArray[i][y] = emptySmallWall;
				}
				// Adds Empty Horizontal walls to the board
				else if (y % 2 == 1) {

					// H WALL
					QrImageView imageH = new QrImageView(i, y, false);
					imageH.setImage(emptyHorizontalWallImage);

					// Horizontol wall is hovered over (mouse Enters)
					imageH.setOnMouseEntered(e -> {
						if (imageH.isClicked()) {
							// do nothing

						} else {
							int x = imageH.getXPos();
							int y2 = imageH.getYPos();
							if (((QrImageView) guiArray[x + 2][y2]).isClicked()
									|| ((QrImageView) guiArray[x + 1][y2]).isClicked()) {

								System.out.println("TAKEN!!");

							} else {
								imageH.setImage(hoverHorizontalWallImage);

								QrImageView longImage = (QrImageView) guiArray[x + 2][y2];
								longImage.setImage(hoverHorizontalWallImage);

								QrImageView smallImage = (QrImageView) guiArray[x + 1][y2];
								smallImage.setImage(hoverSmallImage);
							}
						}
					});

					// Changes wall to taken wall
					imageH.setOnMouseClicked(e -> {
						if (!imageH.isClicked()) {

							imageH.setImage(takenHorizontalWallImage);
							imageH.click();

							int x = imageH.getXPos();
							int y2 = imageH.getYPos();

							QrImageView longImage = (QrImageView) guiArray[x + 2][y2];
							longImage.setImage(takenHorizontalWallImage);
							longImage.click();

							QrImageView smallImage = (QrImageView) guiArray[x + 1][y2];
							smallImage.setImage(hoverSmallWallTaken);
							smallImage.click();
						}

					});

					// Mouse no longer hovers
					imageH.setOnMouseExited(e -> {
						if (!imageH.isClicked()) {
							imageH.setImage(emptyHorizontalWallImage);
							int x = imageH.getXPos();
							int y2 = imageH.getYPos();

							QrImageView longImage = (QrImageView) guiArray[x + 2][y2];
							if (!longImage.isClicked()) {
								longImage.setImage(emptyHorizontalWallImage);
							}

							QrImageView smallImage = (QrImageView) guiArray[x + 1][y2];
							if (!smallImage.isClicked()) {
								smallImage.setImage(emptySmallWallImage);
							}
						} else {

							// do nothing

						}
					});

					guiArray[i][y] = imageH;

					// Adds Vertical Walls
				} else if (i % 2 == 1) {

					// V WALL
					QrImageView image = new QrImageView(i, y, false);
					image.setImage(emptyVerticalWallImage);

					// Vertical wall is hovered over (mouse Enters)
					image.setOnMouseEntered(e -> {

						if (image.isClicked()) {
							// do nothing
						} else {

							int x = image.getXPos();
							int y2 = image.getYPos();

							if (((QrImageView) guiArray[x][y2 + 2]).isClicked()
									|| ((QrImageView) guiArray[x][y2 + 1]).isClicked()) {
								// Do Nothing
							} else {
								image.setImage(hoverVerticalWallImage);

								QrImageView longImage = (QrImageView) guiArray[x][y2 + 2];
								longImage.setImage(hoverVerticalWallImage);

								QrImageView smallImage = (QrImageView) guiArray[x][y2 + 1];
								smallImage.setImage(hoverSmallImage);
							}
						}

					});

					// Changes wall to taken wall
					image.setOnMouseClicked(e -> {
						if (!image.isClicked()) {
							image.setImage(takenVerticalWallImage);
							image.click();

							int x = image.getXPos();
							int y2 = image.getYPos();

							QrImageView longImage = (QrImageView) guiArray[x][y2 + 2];
							longImage.setImage(takenVerticalWallImage);
							longImage.click();

							QrImageView smallImage = (QrImageView) guiArray[x][y2 + 1];
							smallImage.setImage(hoverSmallWallTaken);
							smallImage.click();
						}
					});

					// Mouse now longer hovers
					image.setOnMouseExited(e -> {
						if (!image.isClicked()) {

							image.setImage(emptyVerticalWallImage);
							int x = image.getXPos();
							int y2 = image.getYPos();

							QrImageView longImage = (QrImageView) guiArray[x][y2 + 2];
							if (!longImage.isClicked()) {
								longImage.setImage(emptyVerticalWallImage);
							}

							QrImageView smallImage = (QrImageView) guiArray[x][y2 + 1];
							if (!smallImage.isClicked()) {
								smallImage.setImage(emptySmallWallImage);
							}
						} else {

							// do nothing

						}
					});

					guiArray[i][y] = image;

				}
			}

		}

		GridPane layout = new GridPane();
		layout.getChildren().addAll(playLabel);
		// DisplayBoard

		for (int i = 0; i <= 16; i++) {
			for (int y = 0; y <= 16; y++) {

				ImageView toAdd = guiArray[i][y];
				layout.add(toAdd, i, y);
			}

		}

		/**
		 * Statistics
		 */
		VBox root = new VBox(10);
		VBox stats = new VBox();

		int playerOneWalls = 10;
		int playerTwoWalls = 10;
		int playerThreeWalls = 10;
		int playerFourWalls = 10;
		Label test = new Label("Walls Remaining");
		stats.getChildren().add(test);

		int players = 2; // Test Variable, use board.getNumPlayers()
		if (players == 2) {
			Label playerOne = new Label("Player 1: " + playerOneWalls);
			Label playerTwo = new Label("Player 2: " + playerTwoWalls);
			stats.getChildren().addAll(playerOne, playerTwo);
		} else if (players == 3) {
			Label playerThree = new Label("Player 1: " + playerThreeWalls);
			stats.getChildren().add(playerThree);

		} else if (players == 4) {
			Label playerFour = new Label("Player 1: " + playerFourWalls);
			stats.getChildren().add(playerFour);
		}

		Scene gui = new Scene(root);
		root.getChildren().addAll(layout, stats);

		return gui;
	}

	private int getCurrentPlayer() {
		currentPlayer++;
		return currentPlayer;
	}

}
