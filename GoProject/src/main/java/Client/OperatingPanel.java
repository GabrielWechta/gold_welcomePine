package Client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OperatingPanel extends JPanel{

	private Button giveUpButton;
	private Button passButton;
	private JLabel colorLabel;


	public OperatingPanel(GuiFacade guiFacade) {
		colorLabel = new JLabel();
		if(guiFacade.getPlayerColor() == 'b' ) {
			colorLabel.setText("Black");
		} else {
			colorLabel.setText("White");

		}
		colorLabel.setFont(colorLabel.getFont().deriveFont(64.0f));
		colorLabel.setVerticalAlignment(SwingConstants.CENTER);
		colorLabel.setHorizontalAlignment(SwingConstants.CENTER);

		passButton = new Button("Pass");
		passButton.addActionListener(e -> {
			guiFacade.pass();
		});

		giveUpButton = new Button("Give up");
		giveUpButton.addActionListener(e -> {
			guiFacade.giveUp();
		});

		setLayout(new GridLayout(3,1));
		add(colorLabel);
		add(giveUpButton);
		add(passButton);

	}
}
