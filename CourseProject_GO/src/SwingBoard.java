import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SwingBoard extends JFrame {
	JPanel cards;
	private JButton confirmation;
	private int player;
	private Board b;
	private String[]coordinates;
	private int[]numbers;
	/**
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(int player) {
		this.player = player;
	}

	/**
	 * @return the b
	 */
	public Board getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(Board b) {
		this.b = b;
	}

	/**
	 * @return the numbers
	 */
	public int[] getNumbers() {
		return numbers;
	}

	/**
	 * @param numbers the numbers to set
	 */
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	private static final String BLACK_PATH = "src/images/black.png";
	private static final String WHITE_PATH = "src/images/white.png";
	private static final String RED_PATH = "src/images/red.jpg";
	private static final String GREEN_PATH = "src/images/green.jpg";
	
	public SwingBoard(int size, Board b) throws IOException{
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(size + 1, size));
		
		BufferedImage blackBI = ImageIO.read(new File(BLACK_PATH));
		ImageIcon blackII = new ImageIcon(blackBI);
		
		BufferedImage whiteBI = ImageIO.read(new File(WHITE_PATH));
		ImageIcon whiteII = new ImageIcon(whiteBI);
		
		BufferedImage greenBI = ImageIO.read(new File(GREEN_PATH));
		ImageIcon greenII = new ImageIcon(greenBI);
		
		BufferedImage redBI = ImageIO.read(new File(RED_PATH));
		ImageIcon redII = new ImageIcon(redBI);
		
		JPanel sizePanel = new JPanel();
		
		for(int x = 0; x < b.getSize(); x++){
			for(int y = 0; y < b.getSize(); y++){
				if(b.getPoint(x, y) == 'W'){
					JLabel white = new JLabel(whiteII);
					cp.add(white);
				} else if(b.getPoint(x, y) == 'B'){
					JLabel black = new JLabel(blackII);
					cp.add(black);
				} else if(b.getPoint(x, y) == 'X'){
					JLabel green = new JLabel(greenII);
					cp.add(green);
				} else if(b.getPoint(x, y) == 'L'){
					JLabel red = new JLabel(redII);
					cp.add(red);
				}
			}
		}
		
		player = 1;
		
		confirmation = new JButton("Play");
		cp.add(confirmation);
		
		coordinates = b.getCoordinates(player);
		JList<String> listOfCoordinates = new JList<String>(coordinates);
		cp.add(listOfCoordinates);
		confirmation.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt){
				listOfCoordinates.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent evt){
						String choice = listOfCoordinates.getSelectedValue();
						String[]numbersInString = choice.split(",");
						numbers = new int[2];
						
						for(int x = 0; x < 2; x++){
							numbers[x] = Integer.parseInt(numbersInString[x]);
						}
						b.takeTurn(player, numbers[0], numbers[1]);
						if(player == 1){
							player = 0;
						} else {
							player = 1;
						}
					}
				});
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Go");
		setSize(1000, 700);
		setVisible(true);
	}

	public SwingBoard() {
		// TODO Auto-generated constructor stub
	}
	

}
