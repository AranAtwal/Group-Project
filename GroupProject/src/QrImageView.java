import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QrImageView extends ImageView {

	private int xPos;
	private int yPos;
	private boolean isClicked;
	private int player;

	// player pawns
	Image emptySpaceImage = new Image(getClass().getResourceAsStream("format/emptySpace.png"));
    Image pawn1 = new Image(getClass().getResourceAsStream("format/pawn1.png"));
	Image pawn2 = new Image(getClass().getResourceAsStream("format/pawn2.png"));
	Image pawn3 = new Image(getClass().getResourceAsStream("format/pawn3.png"));
	Image pawn4 = new Image(getClass().getResourceAsStream("format/pawn4.png"));

	public QrImageView(int x, int y, boolean isClicked) {

		this.yPos = y;
		this.xPos = x;
		this.isClicked = false;
	}

	public QrImageView(int x, int y, int player) {
		
		this.yPos = y;
		this.xPos = x;
		this.player = player;
		setPlayer(player);
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setPlayer(int player) {
		if (player == 0 ) {
			this.setImage(emptySpaceImage);
		}
		if (player == 1) {

			this.setImage(pawn1);

		}
		if (player == 2) {

			this.setImage(pawn2);

		}
		if (player == 3) {

			this.setImage(pawn3);

		}
		if (player == 4) {

			this.setImage(pawn4);

		}

	}

	public void click() {
		isClicked = true;
	}

	public boolean isClicked() {
		return isClicked;
	}

}
