package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearch;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.ControllerUserReaderFollow;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.ControllerUserReaderUnfollow;
import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowers;
import main.java.com.ubo.tp.twitub.ihm.models.followers.views.ModelViewFollowerListFollow;
import main.java.com.ubo.tp.twitub.ihm.models.followers.views.ModelViewFollowerListUnfollow;

public class ComponentFollowersWithSearchBar extends ATwithubComponent {

	private ComponentSearch searchPane;
	private ComponentFollowers followersPane;

	public ComponentFollowersWithSearchBar(IControllerSearch controllerSearchFollowers,
			ControllerUserReaderFollow controllerUserReaderFollow, ControllerUserReaderUnfollow controllerUserReaderUnfollow,
			ModelViewFollowerListFollow modelViewFollowerListFollow, ModelViewFollowerListUnfollow modelViewFollowerListUnfollow,
			IModelDatabaseFollowers databaseFollowers) {
		this.searchPane = new ComponentSearch(controllerSearchFollowers);
		this.followersPane = new ComponentFollowers(controllerUserReaderFollow, controllerUserReaderUnfollow, modelViewFollowerListFollow, modelViewFollowerListUnfollow, databaseFollowers);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		this.mComponent.add(this.searchPane.getComponent(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4,4,4,4), 0, 0));
		this.mComponent.add(this.followersPane.getComponent(), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(4,4,4,4), 0, 0));
	}

}
