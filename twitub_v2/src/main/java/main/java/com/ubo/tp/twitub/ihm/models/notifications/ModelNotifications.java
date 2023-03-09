package main.java.com.ubo.tp.twitub.ihm.models.notifications;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReaderObserver;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.datas.ANotification;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.datas.NotificationTwitContainsUserTag;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.datas.NotificationTwitFromFollower;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;

public class ModelNotifications implements IModelNotifications, IDatabaseReaderObserver {

	private Set<IModelNotificationsObserver> mObservers;
	private IModelSession mSession;
	private Collection<ANotification> mNotifications;
	
	public ModelNotifications(IModelSession mModelSession, IDatabaseReader mDatabaseReader) {
		this.mSession = mModelSession;
		this.mNotifications = new LinkedList<>();
		this.mObservers = new HashSet<>();
		
		mDatabaseReader.addObserver(this);
	}

	@Override
	public void addObserver(IModelNotificationsObserver observer) {
		this.mObservers.add(observer);
	}
	
	@Override
	public void removeObserver(IModelNotificationsObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		User user;
		boolean shouldNotify = false;
		
		if (!this.mSession.isConnected()) return;
		
		user = this.mSession.getUser();
		
		if (addedTwit.containsUserTag(user.getUserTag())) {
			this.addNotification(new NotificationTwitContainsUserTag(addedTwit));
			shouldNotify = true;
		}
		
		if (user.getFollows().contains(addedTwit.getTwiter().getUserTag())) {
			this.addNotification(new NotificationTwitFromFollower(addedTwit));
			shouldNotify = true;
		}
		
		if (shouldNotify) {
			this.triggerNotification();
		}
	}

	private void triggerNotification() {
		for (IModelNotificationsObserver observer : this.mObservers) {
			observer.notifyNewNotifications();
		}
	}

	private void addNotification(ANotification notification) {
		this.mNotifications.add(notification);
	}

	@Override
	public Collection<ANotification> getNotifications() {
		return this.mNotifications;
	}
	
	@Override
	public Collection<Twit> getTwits() {
		Collection<ANotification> allNotifications = this.mNotifications;
		Collection<Twit> ret = new LinkedList<>();
		
		for (ANotification notification : allNotifications) {
			ret.add(notification.getTwit());
		}
		
		return ret;
	}
	
	@Override
	public void clearNotifications() {
		this.mNotifications = new LinkedList<>();
	}

	@Override
	public boolean hasNotifications() {
		return !this.mNotifications.isEmpty();
	}

	@Override
	public int numberNotifications() {
		return this.mNotifications.size();
	}

}
