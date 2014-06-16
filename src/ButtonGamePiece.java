import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ButtonGamePiece extends JButton implements ActionListener {
	//variables
	private static final ImageIcon gamePieceX =  new ImageIcon("images/x.png");
	private static final ImageIcon gamePieceO =  new ImageIcon("images/o.png");
	public final byte myLocation;
	
	public ButtonGamePiece(byte i) {
		myLocation = i;
		this.addActionListener(this);
	}
	
	//icon related methods
	
	public void resetIcon() {
		this.setIcon(null);
	}
	
	public void changeGamePieceImage(char currentGamePiece) {
		
		//set the imageicon to the correct game piece based off of the current user
		if ( currentGamePiece == GameEngine.gameBoard.PLAYER1)
			this.setIcon(gamePieceX);
				
		else if (currentGamePiece == GameEngine.gameBoard.PLAYER2)
			this.setIcon(gamePieceO);
		}
	
	//Action Listener
	
	public void actionPerformed(ActionEvent e) {
		boolean updateSuccessful = false;
		char currentGamePiece = GameEngine.gameBoard.getCurrentPlayer();
		
		updateSuccessful = GameEngine.gameBoard.updateGameBoard(this.myLocation);
		if (updateSuccessful) {
			changeGamePieceImage(currentGamePiece);
		}
	}
}
