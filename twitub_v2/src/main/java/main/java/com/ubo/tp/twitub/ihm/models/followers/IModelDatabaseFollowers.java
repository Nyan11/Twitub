package main.java.com.ubo.tp.twitub.ihm.models.followers;

import java.util.Collection;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IModelDatabaseFollowers {
	void addObserver(IModelDatabaseFollowersObserver observer);
	void deleteObserver(IModelDatabaseFollowersObserver observer);
	
	public Collection<User> getNotFollowUsers(User user);
	public Collection<User> getFollowUsers(User user);
}
