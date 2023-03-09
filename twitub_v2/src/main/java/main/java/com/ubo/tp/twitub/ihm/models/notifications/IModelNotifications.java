package main.java.com.ubo.tp.twitub.ihm.models.notifications;

import java.util.Collection;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.datas.ANotification;

public interface IModelNotifications {

	void addObserver(IModelNotificationsObserver observer);
	void removeObserver(IModelNotificationsObserver observer);
	
	boolean hasNotifications();
	int numberNotifications();
	Collection<ANotification> getNotifications();
	Collection<Twit> getTwits();
	void clearNotifications();
}
