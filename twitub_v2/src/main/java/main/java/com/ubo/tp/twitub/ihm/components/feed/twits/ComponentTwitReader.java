package main.java.com.ubo.tp.twitub.ihm.components.feed.twits;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;

public class ComponentTwitReader extends ATwithubComponent {

	private JLabel timeLabel;
	protected JTextArea textArea;
	protected JLabel userLabel;

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		JScrollPane scrollPane;

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(450, 110));

		userLabel = new JLabel("");

		this.mComponent.add(userLabel, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4,4,0,4), 0, 0));
		this.mComponent.add(scrollPane, new GridBagConstraints(0, 1, 3, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(4,4,4,4), 0, 0));
		
		this.textArea.setEditable(false);
		this.timeLabel = new JLabel(" --- ");
		this.mComponent.add(this.timeLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,4,4,4), 0, 0));

	}

	public void makeFromTwit(Twit twit) {
		this.getComponent();
		this.textArea.setText(twit.getText());
		
		this.userLabel.setText(twit.getTwiter().getName() + " -- @" + twit.getTwiter().getUserTag());

		Date date = new Date(twit.getEmissionDate());
		this.timeLabel.setText(date.toString());
	}
}
