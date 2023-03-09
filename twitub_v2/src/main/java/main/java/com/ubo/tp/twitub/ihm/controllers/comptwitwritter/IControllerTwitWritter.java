package main.java.com.ubo.tp.twitub.ihm.controllers.comptwitwritter;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

public interface IControllerTwitWritter {
	
	void addObserver(IControllerTwitWritterObserver observer);
	void deleteObserver(IControllerTwitWritterObserver observer);

	void actionPost(String content);
	void actionCaretUpdated(String content);
	void addTwit(Twit twit);
}
