import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;

public class SwingSize extends JFrame {
	private JButton confirmation;
	private JTextField sizeGetter;
	private JTextField errorMessage;
	private JList<String> gameType;
	private int size = 0;
	private String chosenGameType = null;

	SwingSize() {
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(new JLabel("Size and Type?"));

		sizeGetter = new JTextField("0", 10);
		sizeGetter.setEditable(true);
		cp.add(sizeGetter);

		String[] gameTypes = { "Player vs. Player", "Player vs. Computer",
				"Computer vs. Computer" };
		gameType = new JList<String>(gameTypes);
		cp.add(gameType);

		confirmation = new JButton("Ok");
		confirmation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					size = Integer.parseInt(sizeGetter.getText());
					System.out.println(size);
				} catch (NumberFormatException e) {
					errorMessage = new JTextField("Error: Input a number");
					cp.add(errorMessage);
				}
				chosenGameType = gameType.getSelectedValue();
			}
		});
		cp.add(confirmation);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Go");
		setSize(300, 150);
		setVisible(false);
	}

	public String getGameType() {
		do {
			setVisible(true);
		} while (chosenGameType.equals(null));
		return chosenGameType;
	}

	public int getSizeBoard() {
		do {
			setVisible(true);
		} while (size < 4 || size > 32);
		setVisible(false);
		return size;
	}
}
