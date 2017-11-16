package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import dkeep.logic.GameMain;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Interface {
	
	private char[][] maze ={{'X','X','X','X','X','X','X','X','X','X'},
	  		{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
	  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
	  		{'X',' ','X','X',' ','X',' ',' ',' ','X'},  
	  		{'X',' ','X','X',' ','X',' ','X',' ','X'},
	  		{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},  
	  		{'X',' ','X','X',' ','X',' ','X',' ','X'},  
	  		{'X',' ',' ',' ',' ','X',' ','X',' ','X'},  
	  		{'X',' ','X','X',' ',' ',' ',' ',' ','X'},  
	  		{'X','X','X','X','X','X','X','X','X','X'}
	  	   };

	private JFrame frame;
	private JTextField textFieldNumberDrakes;
	private String[] DragonMov = { "Random", "Sleepy"};
	private JTextArea board = new JTextArea();
	private JLabel lblGameState = new JLabel("New Game to Start..");
	
	private GameMain game = new GameMain(maze);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}
	
	protected void printCurrentState(GameMain game) {
		// TODO Auto-generated method stub
		lblGameState.setText(String.valueOf(game.getState()));
		
	}
	
	protected void printMap(GameMain game) {
		// TODO Auto-generated method stub
		String value = "";
		for (int i = 0; i < game.map.getScreenHeight(); i++) {
			for (int j = 0; j < game.map.getScreenWidth(); j++) {
				value += String.valueOf(game.map.board[i][j]);
				value += " ";
			}
			value += "\n";
		}
		board.setText(value);
	}
	
	protected void continueGame() {
		game.updateState();
		game.moveAllDragons();
		game.updateState();
		printMap(game);
		printCurrentState(game);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		GridBagConstraints gbc_lblNumberOfDragons = new GridBagConstraints();
		gbc_lblNumberOfDragons.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfDragons.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfDragons.gridx = 3;
		gbc_lblNumberOfDragons.gridy = 0;
		frame.getContentPane().add(lblNumberOfDragons, gbc_lblNumberOfDragons);
		
		textFieldNumberDrakes = new JTextField();
		textFieldNumberDrakes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textFieldNumberDrakes = new GridBagConstraints();
		gbc_textFieldNumberDrakes.fill = GridBagConstraints.BOTH;
		gbc_textFieldNumberDrakes.gridwidth = 7;
		gbc_textFieldNumberDrakes.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNumberDrakes.gridx = 4;
		gbc_textFieldNumberDrakes.gridy = 0;
		frame.getContentPane().add(textFieldNumberDrakes, gbc_textFieldNumberDrakes);
		textFieldNumberDrakes.setColumns(10);
		
		JLabel lblDragonMovement = new JLabel("Dragon Movement");
		GridBagConstraints gbc_lblDragonMovement = new GridBagConstraints();
		gbc_lblDragonMovement.anchor = GridBagConstraints.WEST;
		gbc_lblDragonMovement.insets = new Insets(0, 0, 5, 5);
		gbc_lblDragonMovement.gridx = 3;
		gbc_lblDragonMovement.gridy = 1;
		frame.getContentPane().add(lblDragonMovement, gbc_lblDragonMovement);
		
		final JComboBox comboBoxDragonMov = new JComboBox(DragonMov);
		comboBoxDragonMov.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxDragonMov = new GridBagConstraints();
		gbc_comboBoxDragonMov.gridwidth = 7;
		gbc_comboBoxDragonMov.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDragonMov.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDragonMov.gridx = 4;
		gbc_comboBoxDragonMov.gridy = 1;
		frame.getContentPane().add(comboBoxDragonMov, gbc_comboBoxDragonMov);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.newExit('E',9,5);
				game.newSword('S');
				game.newDragons('D',Integer.parseInt(textFieldNumberDrakes.getText()),comboBoxDragonMov.getSelectedIndex()+1);
				game.newHero('H');
				game.startGame();
				printCurrentState(game);
				printMap(game);
			}
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 12;
		gbc_btnNewGame.gridy = 1;
		frame.getContentPane().add(btnNewGame, gbc_btnNewGame);
		
		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 2;
		frame.getContentPane().add(label, gbc_label);
		
		
		GridBagConstraints gbc_board = new GridBagConstraints();
		gbc_board.gridwidth = 8;
		gbc_board.gridheight = 3;
		gbc_board.insets = new Insets(0, 0, 5, 5);
		gbc_board.fill = GridBagConstraints.BOTH;
		gbc_board.gridx = 3;
		gbc_board.gridy = 2;
		board.setFont(new Font("Courier New", Font.PLAIN, 15));
		board.setText("Loading...");
		frame.getContentPane().add(board, gbc_board);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.hero.moveU(game);
				continueGame();
			}
		});
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 12;
		gbc_btnUp.gridy = 3;
		frame.getContentPane().add(btnUp, gbc_btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.hero.moveL(game);
				continueGame();
			}
		});
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 11;
		gbc_btnLeft.gridy = 4;
		frame.getContentPane().add(btnLeft, gbc_btnLeft);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.hero.moveD(game);
				continueGame();
			}
		});
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 5, 5);
		gbc_btnDown.gridx = 12;
		gbc_btnDown.gridy = 4;
		frame.getContentPane().add(btnDown, gbc_btnDown);
		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.hero.moveR(game);
				continueGame();
			}
		});
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.gridx = 13;
		gbc_btnRight.gridy = 4;
		frame.getContentPane().add(btnRight, gbc_btnRight);
		
		
		lblGameState.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblGameState = new GridBagConstraints();
		gbc_lblGameState.anchor = GridBagConstraints.WEST;
		gbc_lblGameState.insets = new Insets(0, 0, 0, 5);
		gbc_lblGameState.gridx = 3;
		gbc_lblGameState.gridy = 5;
		frame.getContentPane().add(lblGameState, gbc_lblGameState);
	}

	

}
