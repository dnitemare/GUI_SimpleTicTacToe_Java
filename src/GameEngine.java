import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEngine {
	public static GameBoard gameBoard = new GameBoard();
	private static int playerOneWinCount = 0;
	private static int playerTwoWinCount = 0;
	
	
	//main engine method
	public static void main(String[] args) {
		TicTacToeGUI gameGUI = new TicTacToeGUI();
		gameGUI.loadGUI();
		
		while (true) {
			while (gameBoard.getTurnCount() < gameBoard.BOARD_SIZE &&
					gameBoard.getWinningPlayer() == gameBoard.NOPLAYER) {
			}
			
			gameGUI.setGameOverLabel();
		}
		
	}
	
	
	
	//win related methods
	public static void increaseWinCount(char player) {
		if (player == gameBoard.PLAYER1) {
			playerOneWinCount++;
		}
		else if (player == gameBoard.PLAYER2) {
				playerTwoWinCount++;
			}
		}
	
	public static int showPlayerOneWins() {
		return playerOneWinCount;
	}
	
	public static int showPlayerTwoWins() {
		return playerTwoWinCount;
	}
}
