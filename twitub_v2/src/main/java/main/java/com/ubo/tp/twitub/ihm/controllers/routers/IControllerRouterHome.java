package main.java.com.ubo.tp.twitub.ihm.controllers.routers;

public interface IControllerRouterHome {

	void addObserver(IControllerRouterHomeObserver observer);
	void deleteObserver(IControllerRouterHomeObserver observer);

	void gotoFeed();
	void gotoFollow();
	void gotoNotifications();
	
	void actionRefreshView();

}
