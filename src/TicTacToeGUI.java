import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicTacToeGUI extends JFrame implements ActionListener {
	private JPanel menuPanel;
	private JPanel gamePanel;
	private JPanel rightStatPanel;
	private JPanel leftStatPanel;
	private JPanel infoPanel;
	
	private JLabel difficultyStatLabel;
	private JLabel gameOverLabel;
	private JLabel playerOneWinCount;
	private JLabel playerTwoWinCount;
	private JLabel gameModeLabel;
	
	private ButtonGamePiece [] moveLocation;
	JButton newGameButton;
	JButton forfeitButton;
	JButton exitButton;
	
	public void loadGUI() {
		//main window settings
		this.setTitle("Tic-Tac-Toe by Ruben Morgan");
		this.setSize(350, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//panels
		this.populateMenuPanel();
		this.add(menuPanel, BorderLayout.NORTH);
		this.populateGamePanel();
		this.add(gamePanel, BorderLayout.CENTER);
		this.populateRightStatPanel();
		this.add(rightStatPanel, BorderLayout.EAST);
		this.populateLeftStatPanel();
		this.add(leftStatPanel, BorderLayout.WEST);
		this.populateInfoPanel();
		this.add(infoPanel, BorderLayout.SOUTH);
		
		
	}
	
	private void populateInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		gameModeLabel = new JLabel("Game Mode: Player Vs Player");
		JLabel spacing = new JLabel(" ");
		gameOverLabel = new JLabel("Game Over!   ");
		
		gameModeLabel.setAlignmentX(CENTER_ALIGNMENT);
		spacing.setAlignmentX(CENTER_ALIGNMENT);
		gameOverLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		infoPanel.add(gameModeLabel);
		infoPanel.add(spacing);
	}

	private void populateRightStatPanel() {
		rightStatPanel = new JPanel();
		rightStatPanel.setLayout(new BoxLayout(rightStatPanel, BoxLayout.Y_AXIS));
		JLabel playerTwo = new JLabel(" Player 2 ");
		playerTwoWinCount = new JLabel("0");
		
		playerTwo.setAlignmentX(CENTER_ALIGNMENT);
		playerTwoWinCount.setAlignmentX(CENTER_ALIGNMENT);
		
		rightStatPanel.add(playerTwo);
		rightStatPanel.add(playerTwoWinCount);
	}
	
	private void populateLeftStatPanel() {
		leftStatPanel = new JPanel();
		leftStatPanel.setLayout(new BoxLayout(leftStatPanel, BoxLayout.Y_AXIS));
		JLabel playerOne = new JLabel(" Player 1 ");
		playerOneWinCount = new JLabel("0");
		
		playerOne.setAlignmentX(CENTER_ALIGNMENT);
		playerOneWinCount.setAlignmentX(CENTER_ALIGNMENT);
		
		leftStatPanel.add(playerOne);
		leftStatPanel.add(playerOneWinCount);
	}

	private void populateMenuPanel() {
		menuPanel = new JPanel();
		
		newGameButton = new JButton("New Game");
		forfeitButton = new JButton("Forfeit");
		exitButton = new JButton("Exit");
		
		newGameButton.addActionListener(this);
		forfeitButton.addActionListener(this);
		exitButton.addActionListener(this);

		menuPanel.add(newGameButton);
		menuPanel.add(forfeitButton);
		menuPanel.add(exitButton);
	}
	
	private void populateGamePanel() {
		gamePanel = new JPanel();
		moveLocation = new ButtonGamePiece[GameEngine.gameBoard.BOARD_SIZE];
		
		gamePanel.setLayout(new GridLayout((int)Math.sqrt(GameEngine.gameBoard.BOARD_SIZE), (int)Math.sqrt(GameEngine.gameBoard.BOARD_SIZE)));
		
		for (byte i = 0; i < GameEngine.gameBoard.BOARD_SIZE; i++) {
			moveLocation[i] = new ButtonGamePiece(i);
			gamePanel.add(moveLocation[i]);
		}
		
	}
	public void setGameOverLabel() {
		char winningPlayer = GameEngine.gameBoard.getWinningPlayer();
		if (winningPlayer != GameEngine.gameBoard.NOPLAYER) {
			gameOverLabel.setText("Game Over!   Player " + winningPlayer + " Wins!");
		}
		else {
			gameOverLabel.setText("Game Over!   Noone Won!");
		}
		
		infoPanel.add(gameOverLabel);
		
	}
	
	public void setGameMode(String currentMode) {
		gameModeLabel.setText("Game Mode: " + currentMode);
	}
	public void resetGamePieceButtons() {
		for (byte i = 0; i < GameEngine.gameBoard.BOARD_SIZE; i++) {
			moveLocation[i].resetIcon();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGameButton) {
			//handle the win count
			char winner = GameEngine.gameBoard.getWinningPlayer();
			GameEngine.increaseWinCount(winner);
			//update the labels
			playerOneWinCount.setText(Integer.toString(GameEngine.showPlayerOneWins()));
			playerTwoWinCount.setText(Integer.toString(GameEngine.showPlayerTwoWins()));
			
			
			resetGamePieceButtons();
			GameEngine.gameBoard.resetGameBoard();
			infoPanel.remove(gameOverLabel);
		}
		else if (e.getSource() == forfeitButton) {
			//handle the win count
			if (GameEngine.gameBoard.getWinningPlayer() == GameEngine.gameBoard.NOPLAYER) {
				char winner;
				if (GameEngine.gameBoard.getCurrentPlayer() == GameEngine.gameBoard.PLAYER1)
					winner = GameEngine.gameBoard.PLAYER2;
				else
					winner = GameEngine.gameBoard.PLAYER1;
				
				GameEngine.increaseWinCount(winner);
				
				//update the labels
				playerOneWinCount.setText(Integer.toString(GameEngine.showPlayerOneWins()));
				playerTwoWinCount.setText(Integer.toString(GameEngine.showPlayerTwoWins()));
				
				resetGamePieceButtons();
				GameEngine.gameBoard.resetGameBoard();
				infoPanel.remove(gameOverLabel);
			}
			
			
		}
		else if (e.getSource() == exitButton) {
			this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
	}
}
