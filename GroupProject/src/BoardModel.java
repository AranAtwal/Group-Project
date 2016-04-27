import java.util.Scanner;

public class BoardModel {
	private static int[][] board;
	private static Player[] players;
	private static Wall[] walls;
	private static int numPlayers;
	private static Scanner userInput;
	private static String s;
	static boolean madeMove = false;
	private static boolean blocked = false;
	private static boolean wallBlocked;
	private static boolean wallPlaced;


	public BoardModel(int numPlayers) {

		this.numPlayers = numPlayers;
		players = new Player[numPlayers];
		walls = new Wall[10];

		// TODO : Create Board with all players
		// Currently 2 player starting at [9,0] and [9,18]
		board = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }


		};
	}

	public boolean movePawn(int x, int y) {
		return true;
	}

	public void createPawns(int state) {
		if (state == 1) {
			if(numPlayers >= 2) {
				Location pos1 = new Location(0,0);
				Player player1 = new Player(pos1, 16, 16);
				players[0] = player1;

				Location pos2 = new Location(0,16);
				Player player2 = new Player(pos2, 16, 0);
				players[1] = player2;
			}
			if(numPlayers >= 3) {
				Location pos3 = new Location(16,16);
				Player player3 = new Player(pos3, 0, 0);
				players[2] = player3;
			}
			if(numPlayers == 4) {
				Location pos4 = new Location(16,0);
				Player player4 = new Player(pos4, 0, 16);
				players[3] = player4;
			}
		}
		else {
			if(numPlayers >= 2) {
				Location pos1 = new Location(0,8);
				Player player1 = new Player(pos1, 16);
				players[0] = player1;

				Location pos2 = new Location(16,8);
				Player player2 = new Player(pos2, 0);
				players[1] = player2;
			}
			if(numPlayers >= 3) {
				Location pos3 = new Location(8,0);
				Player player3 = new Player(16, pos3);
				players[2] = player3;
			}
			if(numPlayers == 4) {
				Location pos4 = new Location(8,16);
				Player player4 = new Player(0, pos4);
				players[3] = player4;
			}
		}
	}

	public static void createWallH(int col, int row) {

		Location loc = new Location(row, col, 1);
		Wall wall = new Wall(loc);
		walls[wall.wallNumber()] = wall;
		board[row][col] = wall.getLocation().getOrientation();
		board[row][col+1] = wall.getLocation().getOrientation();
		board[row][col+2] = wall.getLocation().getOrientation();
		wallPlaced = true;
		//		else {
		//			System.out.println("Wall in wrong position (try even then odd)");
		//			wallBlocked = false;
		//			wallPlaced = false;

	}


	public static void createWallV(int col, int row) {

		Location loc = new Location(row, col, 2);
		Wall wall = new Wall(loc);
		walls[wall.wallNumber()] = wall;
		board[row][col] = wall.getLocation().getOrientation();
		board[row+1][col] = wall.getLocation().getOrientation();
		board[row+2][col] = wall.getLocation().getOrientation();
		wallPlaced = true;
		madeMove = true;
		//	else {
		//		System.out.println("Wall in wrong position (try even then odd)");
		//		wallBlocked = false;
		//		wallPlaced = false;

	}

	public static boolean checkBlocked(int newRow, int newCol) {
		//		Location newLoc = new Location(newRow, newCol);
		//		boolean blk = false;
		//		for (int i = 0; i >= players.length; i++) {
		//			if (newLoc.getRow() == players[i].getCurrentLocation().getRow() && newLoc.getColumn() == players[i].getCurrentLocation().getColumn()) {
		//				blk =  true;
		//			}
		//			
		//		}
		//		return blk;

		if (board[newRow][newCol] == 1 || board[newRow][newCol] == 2 || board[newRow][newCol] == 3 || board[newRow][newCol] == 4 || board[newRow][newCol] == 8) {
			madeMove = false;
			return true;
		}
		else {
			return false;
		}

	}

	public static boolean checkForWalls(int newRow, int newCol) {

		if (board[newRow][newCol] == 8) {
			madeMove = false;
			return true;

		}
		else {
			return false;
		}

	}

	public static boolean checkOutOfBounds(int newRow, int newCol) {

		if ((newRow < 0 || newRow > 16) || (newCol < 0 || newCol > 16)) {
			madeMove = false;
			return true;
		}
		else {
			return false;
		}
	}


	public boolean movePawn(Player playerToMove, int newRow, int newCol) {


		int currentRow = playerToMove.getCurrentLocation().getRow();
		int currentCol = playerToMove.getCurrentLocation().getColumn();



		//if((newRow == currentRow + 2 || newRow == currentRow - 2) || (newRow == currentCol + 2 || newRow == currentCol - 2))
		//{
		if ((newRow >= 0 && newRow <= 16) && (newCol >= 0 && newCol <= 16)) {

			board[currentRow][currentCol] = 0;

			board[newRow][newCol] = playerToMove.getPlayerNum();
			playerToMove.setLocation(newRow, newCol);
			printLocations();
			madeMove = true;


		}


		else {
			System.out.println("move not possible");
			madeMove = false;
		}
		//}

		if(madeMove == true) {
			System.out.println(madeMove);
			return true;
		} else {
			System.out.println(madeMove);
			return false;
		}
	}

	public void setUpBoard() {
		System.out.println(players.length);
		for(Player player : players) {
			Location firstLocation = player.getCurrentLocation();
			int firstRow = firstLocation.getRow();
			int firstColumn = firstLocation.getColumn();

			board[firstRow][firstColumn] = player.getPlayerNum();


			System.out.println(player.getCurrentLocation().getRow());

		}
	}

	public Location getPlayerLoc(int number) {
		return players[number].getCurrentLocation();
	}

	public String boardToString() {
		String boardString = "";

		for (int row = 0; row <= 16; row++) {
			for (int col = 0; col <= 16; col++) {
				boardString = boardString + (board[row][col]);
			}
			boardString += "\n";
		}
		return boardString;
	}


	public void printLocations() {
		String str = "";
		int num = 0;
		for (Player player : players) {

			int currentR = player.getCurrentLocation().getRow();
			int currentC = player.getCurrentLocation().getColumn();
			String st = num + "(" + currentC + "," + currentR + ")\n";
			str = str + st;
			num++;
		}

		System.out.println(str);
	}



	public static void main(String[] args) {
		userInput = new Scanner( System.in );
		System.out.println("wasd to move pawns");
		System.out.println("Enter number of players");
		int numberOfPlayers = Integer.parseInt(userInput.next());
		System.out.println("type 1 for corner starting position or 2 for middle starting position");
		int startingPosition = Integer.parseInt(userInput.next());

		BoardModel board = new BoardModel(numberOfPlayers);
		board.createPawns(startingPosition);
		board.setUpBoard();

		s = board.boardToString();
		System.out.println(s);


		userInput = new Scanner( System.in );

		Boolean run = true;

		int playerNum = 0;
		while(run) {
			if (playerNum >= numberOfPlayers) {
				playerNum = 0;
			}

			System.out.println("Make Move for player " + (playerNum + 1));
			String input = userInput.next();

			if(input.equals("exit")) { run = false; }



			else if(input.equals("print")) {System.out.println(s);}






			else if(input.equals("d")) {

				Player p = players[playerNum];


				//				if(checkBlocked(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+2) == true) {
				//					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+4);
				//					//blocked = false;
				//				}
				//				else if (checkBlocked(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+2) == false){
				//					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+2);
				//				}
				//				else if (checkForWalls(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+1) == false) {
				//
				//
				//					if (madeMove == true) {
				//						s = board.boardToString();
				//						System.out.println(s);
				//						//System.out.println("has reached end of move");
				//						if (playerNum < numPlayers) {
				//							playerNum ++;
				//						}
				//						else {
				//							playerNum = 0;
				//						}
				//					}
				//				}


				if (checkOutOfBounds(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+2) == true) {

					System.out.println("Out of Bounds");
				}
				else if (checkForWalls(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+1) == true) {

					System.out.println("theres a wall");
				}
				else if (checkBlocked(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+2) == false){
					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+2);
				}
				else {
					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()+4);
					//blocked = false;
				}	
				if (madeMove == true) {
					s = board.boardToString();
					System.out.println(s);
					//System.out.println("has reached end of move");
					if (playerNum < numPlayers) {
						playerNum ++;
					}
					else {
						playerNum = 0;
					}



				}

			}


			else if(input.equals("a")) {

				Player p = players[playerNum];


				if (checkOutOfBounds(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-2) == true) {

					System.out.println("Out of Bounds");
				}
				else if (checkForWalls(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-1) == true) {

					System.out.println("theres a wall");
				}
				else if (checkBlocked(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-2) == false){
					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-2);
				}
				else {
					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-4);
					//blocked = false;
				}	
				if (madeMove == true) {
					s = board.boardToString();
					System.out.println(s);
					//System.out.println("has reached end of move");
					if (playerNum < numPlayers) {
						playerNum ++;
					}
					else {
						playerNum = 0;
					}



				}













				//				if(checkBlocked(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-2) == true) {
				//					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-4);
				//					//blocked = false;
				//				}
				//				else if(checkBlocked(p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-2) == false){
				//					board.movePawn(p, p.getCurrentLocation().getRow(), p.getCurrentLocation().getColumn()-2);
				//				}
				//				if (madeMove == true) {
				//					s = board.boardToString();
				//					System.out.println(s);
				//					//System.out.println("has reached end of move");
				//					if (playerNum < numPlayers) {
				//						playerNum ++;
				//					}
				//					else {
				//						playerNum = 0;
				//					}
				//				}


			}

			else if(input.equals("w")) {

				Player p = players[playerNum];

				if (checkOutOfBounds(p.getCurrentLocation().getRow()-2, p.getCurrentLocation().getColumn()) == true) {

					System.out.println("Out of Bounds");
				}
				else if (checkForWalls(p.getCurrentLocation().getRow()-1, p.getCurrentLocation().getColumn()) == true) {

					System.out.println("theres a wall");
				}
				else if (checkBlocked(p.getCurrentLocation().getRow()-2, p.getCurrentLocation().getColumn()) == false){
					board.movePawn(p, p.getCurrentLocation().getRow()-2, p.getCurrentLocation().getColumn());
				}
				else {
					board.movePawn(p, p.getCurrentLocation().getRow()-4, p.getCurrentLocation().getColumn());
					//blocked = false;
				}	
				if (madeMove == true) {
					s = board.boardToString();
					System.out.println(s);
					//System.out.println("has reached end of move");
					if (playerNum < numPlayers) {
						playerNum ++;
					}
					else {
						playerNum = 0;
					}



				}






				//				if(checkBlocked(p.getCurrentLocation().getRow()-2, p.getCurrentLocation().getColumn()) == true) {
				//					board.movePawn(p, p.getCurrentLocation().getRow()-4, p.getCurrentLocation().getColumn());
				//					//blocked = false;
				//				}
				//				else if (checkBlocked(p.getCurrentLocation().getRow()-2, p.getCurrentLocation().getColumn()) == false){
				//					board.movePawn(p, p.getCurrentLocation().getRow()-2, p.getCurrentLocation().getColumn());
				//				}
				//				if (madeMove == true) {
				//					s = board.boardToString();
				//					System.out.println(s);
				//					//System.out.println("has reached end of move");
				//					if (playerNum < numPlayers) {
				//						playerNum ++;
				//					}
				//					else {
				//						playerNum = 0;
				//					}
				//				}


			}

			else if(input.equals("s")) {

				Player p = players[playerNum];

				if (checkOutOfBounds(p.getCurrentLocation().getRow()+2, p.getCurrentLocation().getColumn()) == true) {

					System.out.println("Out of Bounds");
				}
				else if (checkForWalls(p.getCurrentLocation().getRow()+1, p.getCurrentLocation().getColumn()) == true) {

					System.out.println("theres a wall");
				}
				else if (checkBlocked(p.getCurrentLocation().getRow()+2, p.getCurrentLocation().getColumn()) == false){
					board.movePawn(p, p.getCurrentLocation().getRow()+2, p.getCurrentLocation().getColumn());
				}
				else {
					board.movePawn(p, p.getCurrentLocation().getRow()+4, p.getCurrentLocation().getColumn());
					//blocked = false;
				}	
				if (madeMove == true) {
					s = board.boardToString();
					System.out.println(s);
					//System.out.println("has reached end of move");
					if (playerNum < numPlayers) {
						playerNum ++;
					}
					else {
						playerNum = 0;
					}



				}

				//				if(checkBlocked(p.getCurrentLocation().getRow()+2, p.getCurrentLocation().getColumn()) == true) {
				//					board.movePawn(p, p.getCurrentLocation().getRow()+4, p.getCurrentLocation().getColumn());
				//					//blocked = false;
				//				}
				//				else if(checkBlocked(p.getCurrentLocation().getRow()+2, p.getCurrentLocation().getColumn()) == false) {
				//					board.movePawn(p, p.getCurrentLocation().getRow()+2, p.getCurrentLocation().getColumn());
				//				}
				//				if (madeMove == true) {
				//					s = board.boardToString();
				//					System.out.println(s);
				//					//System.out.println("has reached end of move");
				//					if (playerNum < numPlayers) {
				//						playerNum ++;
				//					}
				//					else {
				//						playerNum = 0;
				//					}
				//				}


			}


			else if (input.equals("pass")) {
				if (playerNum < numPlayers) {
					playerNum ++;
				}
				else {
					playerNum = 0;
				}
			}




			else if (input.equals("h")) {

				int c = Integer.parseInt(userInput.next());
				int r = Integer.parseInt(userInput.next());
				if (c > 14) {
					c = 14;
				}

				if (BoardModel.board[r][c] == 8 || BoardModel.board[r][c+1] == 8 || BoardModel.board[r][c+2] == 8) {

					System.out.println("Wall occupied");
					wallBlocked = true;
					madeMove = false;

				}
				else if (r % 2 != 0 && c % 2 != 1) {

					createWallH(c, r);
					s = board.boardToString();
					System.out.println(s);
					if (playerNum < numPlayers) {
						playerNum ++;
					}
					else {
						playerNum = 0;
					}
					System.out.println("Wall placed");
					wallPlaced = false;
					madeMove = true;
				}


			}





			else if (input.equals("v")) {

				int c = Integer.parseInt(userInput.next());
				int r = Integer.parseInt(userInput.next());
				if (r > 14) {
					r = 14;
				}

				if (BoardModel.board[r][c] == 8 || BoardModel.board[r+1][c] == 8 || BoardModel.board[r+2][c] == 8) {

					System.out.println("Wall occupied");
					wallBlocked = true;
					madeMove = false;
				}
				else if (r % 2 != 1 && c % 2 != 0) {

					createWallV(c, r);
					s = board.boardToString();
					System.out.println(s);
					if (playerNum < numPlayers) {
						playerNum ++;
					}
					else {
						playerNum = 0;
					}
					System.out.println("Wall placed");
					wallPlaced = false;
					madeMove = true;
				}


			}

			for (Player p : players) {
				if (p.getPlayertype() == 1) {
					if (p.getCurrentLocation().getRow() == p.getTargetRow() && p.getCurrentLocation().getColumn() == p.getTargetCol()) {
						System.out.println("Player " + p.getPlayerNum() + " has won!");
						run = false;
					}
				}
				else if (p.getPlayertype() == 2) {
					if (p.getCurrentLocation().getRow() == p.getTargetRow()) {
						System.out.println("Player " + p.getPlayerNum() + " has won!");
						run = false;
					}
				}
				else if (p.getPlayertype() == 3) {
					if (p.getCurrentLocation().getColumn() == p.getTargetCol()) {
						System.out.println("Player " + p.getPlayerNum() + " has won!");
						run = false;
					}
				}
			}
		}
	}
}



//c+1 % 3 != 0
