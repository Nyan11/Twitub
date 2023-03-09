package main.java.com.ubo.tp.twitub.ihm.views;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentFeedWithPost;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentFollowersWithSearchBar;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentNotifications;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentProfil;
import main.java.com.ubo.tp.twitub.ihm.controllers.routers.IControllerRouterHome;
import main.java.com.ubo.tp.twitub.ihm.controllers.routers.IControllerRouterHomeObserver;

public class ViewTwitubFeed extends ATwitubView implements IControllerRouterHomeObserver  {

	private ComponentProfil userInfo;
	private ComponentFollowersWithSearchBar followerPanel;
	private ComponentFeedWithPost feedPanel;
	private ComponentNotifications notificationPanel;

	private IControllerRouterHome mRouter;

	public ViewTwitubFeed(IControllerRouterHome router, ComponentProfil profil, ComponentFeedWithPost makeComponentFeedWithPost, ComponentFollowersWithSearchBar followersWithSearchBar, ComponentNotifications notificationPanel) {
		this.mRouter = router;
		this.mRouter.addObserver(this);
		this.userInfo = profil;
		this.followerPanel = followersWithSearchBar;
		this.feedPanel = makeComponentFeedWithPost;
		this.notificationPanel = notificationPanel;
		this.initView();
	}

	@Override
	protected void initView() {	
		this.mView = new JPanel(new BorderLayout());
		this.mView.add(this.userInfo.getComponent(), BorderLayout.WEST);
		this.mView.add(this.feedPanel.getComponent(), BorderLayout.CENTER);
	}

	@Override
	public void notifyRouterFeed() {
		this.updateView(this.feedPanel.getComponent());
	}

	@Override
	public void notifyRouterFollow() {
		this.updateView(followerPanel.getComponent());
	}
	
	private void updateView(JComponent toShow) {
		this.getView();
		this.mView.removeAll();
		this.mView.add(this.userInfo.getComponent(), BorderLayout.WEST);
		this.mView.add(toShow, BorderLayout.CENTER);
		this.mView.setVisible(false);
		this.mView.setVisible(true);
	}

	@Override
	public void notifyRouterNotification() {
		this.updateView(this.notificationPanel.getComponent());
	}
}
