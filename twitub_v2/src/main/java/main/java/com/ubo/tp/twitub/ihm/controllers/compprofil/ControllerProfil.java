package main.java.com.ubo.tp.twitub.ihm.controllers.compprofil;

import main.java.com.ubo.tp.twitub.ihm.controllers.routers.IControllerRouterHome;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.IModelNotifications;

public class ControllerProfil implements IControllerProfil {

	private IControllerRouterHome mRouterHome;
	private IModelNotifications mModelNotifications;
	
	public ControllerProfil(IControllerRouterHome routerHome, IModelNotifications modelNotifications) {
		this.mRouterHome = routerHome;
		this.mModelNotifications = modelNotifications;
	}

	@Override
	public void actionFeed() {
		this.mRouterHome.gotoFeed();
	}

	@Override
	public void actionFollow() {
		this.mRouterHome.gotoFollow();
	}

	@Override
	public void actionNotifications() {
		this.mRouterHome.gotoNotifications();
		this.mModelNotifications.clearNotifications();
	}

}
