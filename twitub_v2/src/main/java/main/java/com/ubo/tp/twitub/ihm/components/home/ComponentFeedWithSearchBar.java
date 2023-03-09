package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.components.feed.twits.ComponentTwitsListReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearch;
import main.java.com.ubo.tp.twitub.ihm.models.twits.IModelDatabaseTwits;

public class ComponentFeedWithSearchBar extends ATwithubComponent {

	private ComponentSearch searchPane;
	private ComponentTwitsListReader twitsPane;

	public ComponentFeedWithSearchBar(IControllerSearch controllerSearchFollowers, IModelDatabaseTwits databaseTwits) {
		this.searchPane = new ComponentSearch(controllerSearchFollowers);
		this.twitsPane = new ComponentTwitsListReader(databaseTwits);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		this.mComponent.add(this.searchPane.getComponent(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4,4,4,4), 0, 0));
		this.mComponent.add(this.twitsPane.getComponent(), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(4,4,4,4), 0, 0));
	}
}
