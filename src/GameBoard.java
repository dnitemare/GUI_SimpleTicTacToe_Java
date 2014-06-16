
public class GameBoard {
	public final byte BOARD_SIZE = 9;
	public final char PLAYER1 = 'X', PLAYER2 = 'O', NOPLAYER = ' ';
	private char currentPlayer;
	private byte turnCount;
	private char winningPlayer;
	private char [] boardPiece = new char[BOARD_SIZE];
	
	public GameBoard() {
		resetGameBoard();
	}
	
	public void resetGameBoard() {
		currentPlayer = PLAYER1;
		turnCount = 0;
		winningPlayer = NOPLAYER;
		
		for (byte index = 0; index < BOARD_SIZE; index++) {
			boardPiece[index] = NOPLAYER;
		}
	}
	
	public char getWinningPlayer() {
		return winningPlayer;
	}
	public char getCurrentPlayer() {
		return currentPlayer;
	}
	public char getBoardPieceAtLocation(byte location) {
		return boardPiece[location];
	}
	public byte getTurnCount() {
		return turnCount;
	}
	
	public boolean updateGameBoard(byte location) {
		boolean boardUpdateStatus = false;
		if (boardPiece[location] == NOPLAYER && winningPlayer == NOPLAYER) {
			boardPiece[location] = currentPlayer;
			
			//change the current user and count the turn
			if (currentPlayer == PLAYER1)
				currentPlayer = PLAYER2;
			else
				currentPlayer = PLAYER1;
			
			turnCount++;
			boardUpdateStatus = true;
			checkForWinningPlayer();
		}
		
		return boardUpdateStatus;
	}
	
	public void checkForWinningPlayer() {
		
		//Loop through the array to run tests
for (int i = 0; i < BOARD_SIZE; i++) {
			
			//variable to decide if the first piece is free or taken
			boolean spaceIsEmpty = boardPiece[i] == NOPLAYER;
			
			//If it's the first space(0) check for both horizontal and vertical
			if (i == 0) {
				boolean horizontalMatchOne = boardPiece[i] == boardPiece[i + 1];
				boolean horizontalMatchTwo = boardPiece[i + 1] == boardPiece[i + 2];
				boolean horizontalMatchThree = boardPiece[i] == boardPiece[i + 2];
				boolean verticalMatchOne = boardPiece[i] == boardPiece[i + 3];
				boolean verticalMatchTwo = boardPiece[i] == boardPiece[i + 6];
				boolean verticalMatchThree = boardPiece[i + 3] == boardPiece[i + 6];
				
				//Check Horizontally for a match
				if (!spaceIsEmpty && horizontalMatchOne && horizontalMatchTwo && horizontalMatchThree) {
					winningPlayer = boardPiece[i];
				}
					
				//Check Vertically for a match
				else if (!spaceIsEmpty && verticalMatchOne && verticalMatchTwo && verticalMatchThree) {
					winningPlayer = boardPiece[i];
				}
			}
			
			//If it's the first 3 pieces, check Vertically
			else if (i < 3) {
				boolean verticalMatchOne = boardPiece[i] == boardPiece[i + 3];
				boolean verticalMatchTwo = boardPiece[i] == boardPiece[i + 6];
				boolean verticalMatchThree = boardPiece[i + 3] == boardPiece[i + 6];
				
				if (!spaceIsEmpty && verticalMatchOne && verticalMatchTwo && verticalMatchThree) {
					winningPlayer = boardPiece[i];
				}
			}

			//If i is divisible by 3 evenly check Horizontally
			else if (i % 3 == 0) {
				boolean horizontalMatchOne = boardPiece[i] == boardPiece[i + 1];
				boolean horizontalMatchTwo = boardPiece[i + 1] == boardPiece[i + 2];
				boolean horizontalMatchThree = boardPiece[i] == boardPiece[i + 2];
				
				//Check Horizontally for a match
				if (!spaceIsEmpty && horizontalMatchOne && horizontalMatchTwo && horizontalMatchThree) {
					winningPlayer = boardPiece[i];
				}
			}
			//check diagonal
			else if (i == 4) {
				boolean diagonalMatchOne = boardPiece[0] == boardPiece[4];
				boolean diagonalMatchTwo = boardPiece[0] == boardPiece[8];
				boolean diagonalMatchThree = boardPiece[4] == boardPiece[8];
				boolean diagonalMatchFour = boardPiece[2] == boardPiece[4];
				boolean diagonalMatchFive = boardPiece[2] == boardPiece[6];
				boolean diagonalMatchSix = boardPiece[4] == boardPiece[6];
				
				if (!spaceIsEmpty && diagonalMatchOne && diagonalMatchTwo && diagonalMatchThree) {
					winningPlayer = boardPiece[4];
				}
				else if (!spaceIsEmpty && diagonalMatchFour && diagonalMatchFive && diagonalMatchSix) {
					winningPlayer = boardPiece[4];
				}
			}
		}
	}
	
}
