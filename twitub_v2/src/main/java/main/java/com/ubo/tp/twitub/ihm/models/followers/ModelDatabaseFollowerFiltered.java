package main.java.com.ubo.tp.twitub.ihm.models.followers;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearch;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearchObserver;

public class ModelDatabaseFollowerFiltered
		implements IModelDatabaseFollowers, IControllerSearchObserver, IModelDatabaseFollowersObserver {

	private IModelDatabaseFollowers mOrigin;
	private Set<IModelDatabaseFollowersObserver> mObservers;
	private boolean searching;
	private String textSearch;

	public ModelDatabaseFollowerFiltered(IControllerSearch controllerSearch, IModelDatabaseFollowers orgin) {
		this.mObservers = new HashSet<>();
		this.mOrigin = orgin;

		orgin.addObserver(this);
		controllerSearch.addObserver(this);
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
		for (User u : this.mOrigin.getNotFollowUsers(user)) {
			if (this.searching && !u.getName().toLowerCase().contains(this.textSearch.toLowerCase()))
				continue;
			returnUsers.add(u);
		}
		return returnUsers;
	}

	@Override
	public Collection<User> getFollowUsers(User user) {
		List<User> returnUsers = new LinkedList<>();
		for (User u : this.mOrigin.getFollowUsers(user)) {
			if (this.searching && !u.getName().toLowerCase().contains(this.textSearch.toLowerCase()))
				continue;
			returnUsers.add(u);
		}
		return returnUsers;
	}

	@Override
	public void notifyNoSearch() {
		this.searching = false;
		this.triggerFollowersUpdate();
	}

	@Override
	public void notifySearch(String text) {
		this.searching = true;
		this.textSearch = text;
		this.triggerFollowersUpdate();
	}

	@Override
	public void notifyFollowersUpdate() {
		this.triggerFollowersUpdate();
	}

	private void triggerFollowersUpdate() {
		Set<IModelDatabaseFollowersObserver> clonedList = this.mObservers;
		for (IModelDatabaseFollowersObserver observer : clonedList) {
			observer.notifyFollowersUpdate();
		}
	}
}
