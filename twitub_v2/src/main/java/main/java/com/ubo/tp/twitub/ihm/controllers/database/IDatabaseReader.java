package main.java.com.ubo.tp.twitub.ihm.controllers.database;

import java.util.Collection;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IDatabaseReader {

	void addObserver(IDatabaseReaderObserver observer);
	void removeObserver(IDatabaseReaderObserver observer);
	Collection<Twit> getTwits();
	Collection<User> getUsers();

}
