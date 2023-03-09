package main.java.com.ubo.tp.twitub.ihm.models.users;

import java.util.Collection;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IModelDatabaseUsers {

	public void addObserver(IModelDatabaseUsersObserver observer);
	public void deleteObserver(IModelDatabaseUsersObserver observer);
	
	Collection<User> getUsers();
}
