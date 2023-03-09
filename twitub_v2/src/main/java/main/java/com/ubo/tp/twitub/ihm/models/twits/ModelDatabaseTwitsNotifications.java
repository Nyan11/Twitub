package main.java.com.ubo.tp.twitub.ihm.models.twits;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.IModelNotifications;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.IModelNotificationsObserver;

public class ModelDatabaseTwitsNotifications implements IModelDatabaseTwits, IModelNotificationsObserver {

	private Set<IModelDatabaseTwitsObserver> mObservers;
	private IModelNotifications mModelNotification;

	public ModelDatabaseTwitsNotifications(IModelNotifications modelNotification) {
		this.mObservers = new HashSet<>();
		this.mModelNotification = modelNotification;
		
		modelNotification.addObserver(this);
	}

	@Override
	public void addObserver(IModelDatabaseTwitsObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IModelDatabaseTwitsObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public Collection<Twit> getTwits() {
		return this.mModelNotification.getTwits();
	}

	@Override
	public void notifyNewNotifications() {
		for (IModelDatabaseTwitsObserver observer : this.mObservers) {
			observer.notifyTwitsFeedUpdate();
		}
	}

}
