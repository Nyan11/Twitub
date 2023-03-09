package main.java.com.ubo.tp.twitub.ihm.models.followers.views;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowersObserver;


public abstract class AModelViewFollowers implements IModelDatabaseFollowersObserver {
	
	private Set<IModelViewFollowersObserver> mObservers;
	
	protected AModelViewFollowers() {
		this.mObservers = new HashSet<>();
	}
	
	@Override
	public void notifyFollowersUpdate() {
		Set<IModelViewFollowersObserver> clonedList = this.mObservers;
		for (IModelViewFollowersObserver observer : clonedList) {
			observer.notifyFollowersViewUpdate();
		}
	}

	public void addObserver(IModelViewFollowersObserver observer) {
		this.mObservers.add(observer);
	}

	public void deleteObserver(IModelViewFollowersObserver observer) {
		this.mObservers.remove(observer);
	}
}
