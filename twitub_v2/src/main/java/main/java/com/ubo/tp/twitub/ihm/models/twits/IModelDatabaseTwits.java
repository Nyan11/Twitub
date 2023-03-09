package main.java.com.ubo.tp.twitub.ihm.models.twits;

import java.util.Collection;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

public interface IModelDatabaseTwits {

	public void addObserver(IModelDatabaseTwitsObserver observer);
	public void deleteObserver(IModelDatabaseTwitsObserver observer);
	
	Collection<Twit> getTwits();

}
