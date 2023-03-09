package main.java.com.ubo.tp.twitub.ihm.components.feed.users;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.IControllerUserReader;

public class ComponentUserReader extends ATwithubComponent {

	private JLabel nameLabel;
	private String mButtonLabel;
	private IControllerUserReader mController;
	
	private User mModelUser;
	
	public ComponentUserReader(String buttonLabel, IControllerUserReader controller) {
		this.mButtonLabel = buttonLabel;
		this.mController = controller;
	}
	
	public void makeFromUser(User user) {
		this.nameLabel.setText(user.getName() + " -- @" + user.getUserTag());
		this.mModelUser = user;
	}
	
	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		this.nameLabel = new JLabel(" ");
		this.mComponent.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4,4,4,4), 0, 0));
		
		JButton followButton = new JButton(this.mButtonLabel);
		followButton.addActionListener(e -> {
			this.mController.setUser(this.mModelUser);
			this.mController.actionFollow();
		});
		this.mComponent.add(followButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4,4,4,4), 0, 0));

	}
}
