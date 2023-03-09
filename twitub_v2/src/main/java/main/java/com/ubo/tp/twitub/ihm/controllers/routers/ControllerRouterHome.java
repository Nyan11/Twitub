package main.java.com.ubo.tp.twitub.ihm.controllers.routers;

import java.util.HashSet;
import java.util.Set;

public class ControllerRouterHome implements IControllerRouterHome {

	private enum Router { FEED, FOLLOW, NOTIFICATION }

	private Set<IControllerRouterHomeObserver> mObservers;
	private Router currentView;
	
	public ControllerRouterHome() {
		this.mObservers = new HashSet<>();
		this.currentView = Router.FEED;
	}
	
	@Override
	public void addObserver(IControllerRouterHomeObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IControllerRouterHomeObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public void gotoFeed() {
		this.currentView = Router.FEED;
		Set<IControllerRouterHomeObserver> clonedList = this.mObservers;
		for (IControllerRouterHomeObserver observer : clonedList) {
			observer.notifyRouterFeed();
		}
	}

	@Override
	public void gotoFollow() {
		this.currentView = Router.FOLLOW;
		Set<IControllerRouterHomeObserver> clonedList = this.mObservers;
		for (IControllerRouterHomeObserver observer : clonedList) {
			observer.notifyRouterFollow();
		}
	}

	@Override
	public void gotoNotifications() {
		this.currentView = Router.NOTIFICATION;
		Set<IControllerRouterHomeObserver> clonedList = this.mObservers;
		for (IControllerRouterHomeObserver observer : clonedList) {
			observer.notifyRouterNotification();
		}
	}
	
	@Override
	public void actionRefreshView() {
		if (this.currentView == Router.FEED) {
			this.gotoFeed();
		} else {
			this.gotoFollow();
		}
	}

}
