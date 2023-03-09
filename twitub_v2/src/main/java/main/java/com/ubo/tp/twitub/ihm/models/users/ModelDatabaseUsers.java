package main.java.com.ubo.tp.twitub.ihm.models.users;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReaderObserver;

public class ModelDatabaseUsers implements IModelDatabaseUsers, IDatabaseReaderObserver {

	private Set<IModelDatabaseUsersObserver> mObservers;
	private Collection<User> users;
	private boolean retreiveUsers;
	private IDatabaseReader mDatabase;
	
	public ModelDatabaseUsers(IDatabaseReader databaseReader) {
		this.retreiveUsers = true;
		this.mDatabase = databaseReader;
		this.mDatabase.addObserver(this);
		this.mObservers = new HashSet<>();
	}

	@Override
	public void addObserver(IModelDatabaseUsersObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IModelDatabaseUsersObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public Collection<User> getUsers() {
		if (this.shouldRetreiveUsers()) {
			this.retreiveUsers();
		}
		return this.users;
	}

	private void retreiveUsers() {
		this.users = this.mDatabase.getUsers();
		this.retreiveUsers = false;
	}

	private boolean shouldRetreiveUsers() {
		return this.retreiveUsers;
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		this.retreiveUsers = true;
		Set<IModelDatabaseUsersObserver> clonedList = this.mObservers;
		for (IModelDatabaseUsersObserver observer : clonedList) {
			observer.notifyUsersUpdate();
		}
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		this.retreiveUsers = true;
		Set<IModelDatabaseUsersObserver> clonedList = this.mObservers;
		for (IModelDatabaseUsersObserver observer : clonedList) {
			observer.notifyUsersUpdate();
		}
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		this.retreiveUsers = true;
		Set<IModelDatabaseUsersObserver> clonedList = this.mObservers;
		for (IModelDatabaseUsersObserver observer : clonedList) {
			observer.notifyUsersUpdate();
		}
	}
}
