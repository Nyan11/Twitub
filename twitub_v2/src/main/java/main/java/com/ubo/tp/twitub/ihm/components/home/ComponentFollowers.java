package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.components.feed.users.ComponentUsersListReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.IControllerUserReader;
import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowers;
import main.java.com.ubo.tp.twitub.ihm.models.followers.views.IModelViewFollowers;

public class ComponentFollowers extends ATwithubComponent {

	private ComponentUsersListReader followList;
	private ComponentUsersListReader notFollowList;

	public ComponentFollowers(IControllerUserReader controllerUserReaderFollow,
			IControllerUserReader controllerUserReaderUnfollow,
			IModelViewFollowers modelViewFollowerListFollow,
			IModelViewFollowers modelViewFollowerListUnfollow, IModelDatabaseFollowers databaseFollowers) {
		this.followList = new ComponentUsersListReader(TwithubRessources.FOLLOW_UNFOLLOW, controllerUserReaderUnfollow, controllerUserReaderFollow, databaseFollowers, modelViewFollowerListFollow);
		this.notFollowList = new ComponentUsersListReader(TwithubRessources.FOLLOW_FOLLOW, controllerUserReaderFollow, controllerUserReaderUnfollow, databaseFollowers, modelViewFollowerListUnfollow);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		this.resetComponent();
	}

	private void resetComponent() {
		if (this.mComponent == null) {
			return;
		}
		this.mComponent.removeAll();
		this.makeFollowersList();
	}

	private void makeFollowersList() {
		JScrollPane scrollPaneAllUser;
		JScrollPane scrollPaneFollowUser;
		scrollPaneAllUser = new JScrollPane(this.notFollowList.getComponent());
		scrollPaneFollowUser = new JScrollPane(this.followList.getComponent());

		this.mComponent.add(scrollPaneAllUser, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(4,4,4,4), 0, 0));
		this.mComponent.add(scrollPaneFollowUser, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(4,4,4,4), 0, 0));
	}
}
