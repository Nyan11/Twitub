package main.java.com.ubo.tp.twitub.ihm.controllers.database;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IDatabaseWritter {

	void addUser(User user);
	void addTwit(Twit twit);
	void modifyUser(User user);

}
