package main.java.com.ubo.tp.twitub.ihm.components.feed.users;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.IControllerUserReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.IControllerUserReaderObserver;
import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowers;
import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowersObserver;
import main.java.com.ubo.tp.twitub.ihm.models.followers.views.IModelViewFollowers;

public class ComponentUsersListReader extends ATwithubComponent implements IControllerUserReaderObserver, IModelDatabaseFollowersObserver {
	
	private IModelViewFollowers mModelViewUsers;
	private IControllerUserReader mControllerUserReader;
	private String mFollowButtonLabel;
	
	public ComponentUsersListReader(String followButtonLabel, IControllerUserReader controllerUserReader1, IControllerUserReader controllerUserReader2, IModelDatabaseFollowers databaseFollowers, IModelViewFollowers modelViewUsers) {
		this.mModelViewUsers = modelViewUsers;
		this.mControllerUserReader = controllerUserReader1;
		this.mFollowButtonLabel = followButtonLabel;

		controllerUserReader1.addObserver(this);
		controllerUserReader2.addObserver(this);
		databaseFollowers.addObserver(this);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		this.buildUsersFeed();
	}
	
	@Override
	public void notifyFollowersUpdate() {
		this.refreshPanel();
	}

	private void buildUsersFeed() {
		GridBagConstraints constraints;
		ComponentUserReader reader ;

		this.mComponent.removeAll();
		
		constraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4,4,0,4), 0, 0);
		
		for (User u : this.mModelViewUsers.getUsers()) {
			reader = new ComponentUserReader(this.mFollowButtonLabel, this.mControllerUserReader);
			reader.getComponent();
			reader.makeFromUser(u);
			this.mComponent.add(reader.getComponent(), constraints);
			constraints.gridy += 1;
		}
	}

	private void refreshPanel() {
		if (this.mComponent == null) return;
		this.buildUsersFeed();
		this.mComponent.setVisible(false);
		this.mComponent.setVisible(true);
	}
}
