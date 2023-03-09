package main.java.com.ubo.tp.twitub.ihm.components.home;

import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.components.feed.twits.ComponentTwitsListReader;
import main.java.com.ubo.tp.twitub.ihm.models.twits.IModelDatabaseTwits;

public class ComponentNotifications extends ATwithubComponent {

	private ComponentTwitsListReader mTwitListReader;
	
	public ComponentNotifications(IModelDatabaseTwits notificationFilteredDatabase) {
		this.mTwitListReader = new ComponentTwitsListReader(notificationFilteredDatabase);
	}

	@Override
	protected void initComponent() {
		this.mComponent = this.mTwitListReader.getComponent();
	}

}
