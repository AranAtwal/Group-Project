import java.util.Scanner;

public class BoardModel {
	private int[][] board;
	private static Player[] players;
	private int numPlayers;
	private static Scanner userInput;
	public BoardModel(int numPlayers) {
		int x = 0;
		this.numPlayers = numPlayers;
		players = new Player[numPlayers];

		// TODO : Create Board with all players
		// Currently 2 player starting at [9,0] and [9,18]
		board = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }

		};
	}

	public boolean movePawn(int x, int y) {
		return true;
	}
	
	public void createPawns() {
		if(numPlayers >= 2) {
			Location pos1 = new Location(8,0);
			Player player1 = new Player(pos1);
			players[0] = player1;
			
			Location pos2 = new Location(8,17);
			Player player2 = new Player(pos2);
			players[1] = player2;
		}
		if(numPlayers >= 3) {
			Location pos3 = new Location(0,8);
			Player player3 = new Player(pos3);
			players[2] = player3;
		}
		if(numPlayers == 4) {
			Location pos4 = new Location(17,8);
			Player player4 = new Player(pos4);
			players[3] = player4;
		}
	}
	
	
	public boolean movePawn(Player playerToMove, int newRow, int newCol) {
		boolean madeMove = false;
		
		int currentRow = playerToMove.getCurrentLocation().getRow();
		int currentCol = playerToMove.getCurrentLocation().getColumn();
		
		if((newRow == currentRow + 2 || newRow == currentRow - 2) && (newRow == currentCol + 2 || newRow == currentCol - 2))
		{
			if ((newRow >= 0 && newRow <= 17) && (newCol >= 0 && newCol <= 17)) {
				board[currentRow][currentCol] = 0;
			}
		}

		return false;
	}
	public void setUpBoard() {
		System.out.println(players.length);
		for(Player player : players) {
			Location firstLocation = player.getCurrentLocation();
			int firstRow = firstLocation.getRow();
			int firstColumn = firstLocation.getColumn();
			board[firstColumn][firstRow] = 1;
			
			System.out.println(player.getCurrentLocation().getRow());

		}
	}

	public String boardToString() {
		String boardString = "";

		for (int row = 0; row <= 17; row++) {
			for (int col = 0; col <= 17; col++) {
				boardString = boardString + (board[row][col]);
			}
			boardString += "\n";
		}
		return boardString;
	}

	public static void main(String[] args) {
		userInput = new Scanner( System.in );
		
		System.out.println("Enter number of players");
		int numberOfPlayers = Integer.parseInt(userInput.next());
				
		BoardModel board = new BoardModel(numberOfPlayers);
		board.createPawns();
		board.setUpBoard();

		String boardString = board.boardToString();
		System.out.println(boardString);
		System.out.println();
		
		userInput = new Scanner( System.in );
		
		Boolean run = true;
		
		int playerNum = 0;
		while(run) {
			System.out.println("Make Move for player " + playerNum);
			String move = userInput.next();
			
			if(move.equals("exit")) { run = false; }
			else if(move.equals("print")) {System.out.println(boardString);}
			else if(move.equals("move")) {
			}

		}
	}
}
