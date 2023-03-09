package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;

public class ComponentFeedWithPost extends ATwithubComponent {
	
	private ComponentTwitWritter twitWritter;
	private ComponentFeedWithSearchBar twitsList;
	
	public ComponentFeedWithPost(ComponentTwitWritter twitWritter, ComponentFeedWithSearchBar twitsListReader) {
		this.twitWritter = twitWritter;
		this.twitsList = twitsListReader;
	}

	@Override
	protected void initComponent() {
		JScrollPane scrollPaneFeed;
		scrollPaneFeed = new JScrollPane(this.twitsList.getComponent());
		
		this.mComponent = new JPanel(new BorderLayout());
		this.mComponent.add(this.twitWritter.getComponent(), BorderLayout.NORTH);
		this.mComponent.add(scrollPaneFeed, BorderLayout.CENTER);
	}

}
