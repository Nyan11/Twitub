package main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IControllerUserReader {

	void addObserver(IControllerUserReaderObserver observer);
	void deleteObserver(IControllerUserReaderObserver observer);

	void actionFollow();
	void setUser(User user);
	boolean addFollower(User target);
	boolean removeFollower(User target);
}
