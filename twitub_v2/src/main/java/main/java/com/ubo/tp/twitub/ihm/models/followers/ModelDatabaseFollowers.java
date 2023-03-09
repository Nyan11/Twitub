package main.java.com.ubo.tp.twitub.ihm.models.followers;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.models.users.IModelDatabaseUsers;
import main.java.com.ubo.tp.twitub.ihm.models.users.IModelDatabaseUsersObserver;

public class ModelDatabaseFollowers implements IModelDatabaseFollowers, IModelDatabaseUsersObserver {

	private IModelDatabaseUsers mDatabaseUsers;
	private Set<IModelDatabaseFollowersObserver> mObservers;

	public ModelDatabaseFollowers(IModelDatabaseUsers databaseUsers) {
		this.mObservers = new HashSet<>();
		this.mDatabaseUsers = databaseUsers;
		this.mDatabaseUsers.addObserver(this);
	}

	@Override
	public void addObserver(IModelDatabaseFollowersObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IModelDatabaseFollowersObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public Collection<User> getNotFollowUsers(User user) {
		List<User> returnUsers = new LinkedList<>();
		for (User u : this.mDatabaseUsers.getUsers()) {
			if (user == u || user.getFollows().contains(u.getUserTag()))
				continue;
			returnUsers.add(u);
		}
		return returnUsers;
	}

	@Override
	public Collection<User> getFollowUsers(User user) {
		List<User> returnUsers = new LinkedList<>();
		for (User u : this.mDatabaseUsers.getUsers()) {
			if (user == u || !user.getFollows().contains(u.getUserTag()))
				continue;
			returnUsers.add(u);
		}
		return returnUsers;
	}

	@Override
	public void notifyUsersUpdate() {
		this.triggerFollowersUpdate();
	}

	private void triggerFollowersUpdate() {
		Set<IModelDatabaseFollowersObserver> clonedList = this.mObservers;
		for (IModelDatabaseFollowersObserver observer : clonedList) {
			observer.notifyFollowersUpdate();
		}
	}
}
