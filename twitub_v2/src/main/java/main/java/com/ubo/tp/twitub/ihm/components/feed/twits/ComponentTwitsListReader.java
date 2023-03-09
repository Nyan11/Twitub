package main.java.com.ubo.tp.twitub.ihm.components.feed.twits;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.models.twits.IModelDatabaseTwits;
import main.java.com.ubo.tp.twitub.ihm.models.twits.IModelDatabaseTwitsObserver;

public class ComponentTwitsListReader extends ATwithubComponent implements IModelDatabaseTwitsObserver {

	IModelDatabaseTwits mDatabaseTwits;

	public ComponentTwitsListReader(IModelDatabaseTwits databaseTwits) {
		this.mDatabaseTwits = databaseTwits;
		this.mDatabaseTwits.addObserver(this);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		this.buildTwitsFeed();
	}

	private void buildTwitsFeed() {
		GridBagConstraints constraints;
		ComponentTwitReader reader;

		constraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(4, 4, 0, 4), 0, 0);

		this.getComponent().removeAll();

		for (Twit t : this.mDatabaseTwits.getTwits()) {
			reader = new ComponentTwitReader();
			reader.makeFromTwit(t);
			this.mComponent.add(reader.getComponent(), constraints);
			constraints.gridy += 1;
		}
	}

	@Override
	public void notifyTwitsFeedUpdate() {
		this.buildTwitsFeed();
		this.mComponent.setVisible(false);
		this.mComponent.setVisible(true);
	}
}
