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
	
	Button giveUpButton;
	Button passButton;
	JLabel colorLabel;
	
	public OperatingPanel(ClientServerBridge clientServerBridge) {
		colorLabel = new JLabel();
		if(clientServerBridge.getPlayerColor() == 1 ) {
			colorLabel.setText("Black");
		} else {
			colorLabel.setText("White");

		}
		colorLabel.setFont(colorLabel.getFont().deriveFont(64.0f));
		colorLabel.setVerticalAlignment(SwingConstants.CENTER);
		colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		passButton = new Button("Pass");
		passButton.addActionListener(e -> {
			clientServerBridge.pass();
		});
		
		giveUpButton = new Button("Give up");
		giveUpButton.addActionListener(e -> {
			clientServerBridge.giveUp();
		});
		
		setLayout(new GridLayout(3,1));
		add(colorLabel);
		add(giveUpButton);
		add(passButton);
		
	}
}
