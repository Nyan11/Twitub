package main.java.com.ubo.tp.twitub.ihm.models.session;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReaderObserver;

public class ModelSession implements IModelSession, IDatabaseReaderObserver {

	private User user;
	private Set<IModelSessionObserver> mObservers;
	
	public ModelSession() {
		this.mObservers = new HashSet<>();
	}

	@Override
	public void addObserver(IModelSessionObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IModelSessionObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public boolean isConnected() {
		return this.user != null;
	}

	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
		this.notifyObservers();
	}

	private void notifyObservers() {
		Set<IModelSessionObserver> clonedList = this.mObservers;
		if (this.isConnected()) {
			for (IModelSessionObserver observer : clonedList) {
				observer.notifyConnect();
			}
		} else {
			for (IModelSessionObserver observer : clonedList) {
				observer.notifyDisconnect();
			}
		}
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		if (!this.isConnected()) return;
		if (this.user.getUserTag().equals(deletedUser.getUserTag())) {
			this.setUser(null);
		}
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		if (!this.isConnected()) return;
		if (this.user.getUserTag().equals(modifiedUser.getUserTag())) {
			this.setUser(modifiedUser);
		}
	}
}
