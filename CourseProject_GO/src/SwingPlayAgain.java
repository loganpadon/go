import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;


public class SwingPlayAgain extends JFrame {
	final static private JButton yes = new JButton("Yes");
	final static private JButton no = new JButton("No");
	final static private JTextField message = new JTextField("Want to play again?");
	private JTextField winnerMessage;
	private boolean playAgain;
	
	SwingPlayAgain(int winner){
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		JTextField winnerMessage = new JTextField("Player " + winner + " wins!");
		cp.add(winnerMessage);
		
		
		
		
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				playAgain = true;
				System.out.println(playAgain);
			}
		});
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				playAgain = false;
				System.out.println(playAgain);
			}
		});
		cp.add(message);
		cp.add(no);
		cp.add(yes);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Go");
		setSize(300, 125);
		setVisible(true);
	}
	public boolean isPlayAgain(){
		return playAgain;
	}

}
