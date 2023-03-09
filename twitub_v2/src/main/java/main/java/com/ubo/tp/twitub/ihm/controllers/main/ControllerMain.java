package main.java.com.ubo.tp.twitub.ihm.controllers.main;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentFeedWithPost;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentFeedWithSearchBar;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentFollowersWithSearchBar;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentNotifications;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentProfil;
import main.java.com.ubo.tp.twitub.ihm.components.home.ComponentTwitWritter;
import main.java.com.ubo.tp.twitub.ihm.controllers.complogin.ControllerCompLogin;
import main.java.com.ubo.tp.twitub.ihm.controllers.compprofil.ControllerProfil;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.ControllerSearch;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearch;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsignup.ControllerSignUp;
import main.java.com.ubo.tp.twitub.ihm.controllers.comptwitwritter.ControllerTwitWritter;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.ControllerUserReaderFollow;
import main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader.ControllerUserReaderUnfollow;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.DatabaseReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.DatabaseWritter;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseWritter;
import main.java.com.ubo.tp.twitub.ihm.controllers.routers.ControllerRouterHome;
import main.java.com.ubo.tp.twitub.ihm.controllers.routers.IControllerRouterHome;
import main.java.com.ubo.tp.twitub.ihm.controllers.session.ControllerSession;
import main.java.com.ubo.tp.twitub.ihm.controllers.session.IControllerSession;
import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowers;
import main.java.com.ubo.tp.twitub.ihm.models.followers.ModelDatabaseFollowerFiltered;
import main.java.com.ubo.tp.twitub.ihm.models.followers.ModelDatabaseFollowers;
import main.java.com.ubo.tp.twitub.ihm.models.followers.views.ModelViewFollowerListFollow;
import main.java.com.ubo.tp.twitub.ihm.models.followers.views.ModelViewFollowerListUnfollow;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.IModelNotifications;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.ModelNotifications;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSessionObserver;
import main.java.com.ubo.tp.twitub.ihm.models.session.ModelSession;
import main.java.com.ubo.tp.twitub.ihm.models.twits.IModelDatabaseTwits;
import main.java.com.ubo.tp.twitub.ihm.models.twits.ModelDatabaseTwits;
import main.java.com.ubo.tp.twitub.ihm.models.twits.ModelDatabaseTwitsFiltered;
import main.java.com.ubo.tp.twitub.ihm.models.twits.ModelDatabaseTwitsNotifications;
import main.java.com.ubo.tp.twitub.ihm.models.users.IModelDatabaseUsers;
import main.java.com.ubo.tp.twitub.ihm.models.users.ModelDatabaseUsers;
import main.java.com.ubo.tp.twitub.ihm.views.ATwitubView;
import main.java.com.ubo.tp.twitub.ihm.views.ViewNotLogged;
import main.java.com.ubo.tp.twitub.ihm.views.ViewSignUp;
import main.java.com.ubo.tp.twitub.ihm.views.ViewTwitubFeed;

public class ControllerMain implements IControllerMain, IModelSessionObserver {

	private TwitubMainView mWindow;

	private ViewNotLogged vViewLogin;
	private ViewSignUp vViewSignUp;
	private ViewTwitubFeed vViewTwitFeed;
	
	private IControllerRouterHome cControllerRouter;
	private IControllerSession cControllerSession;

	private IModelSession mModelSession;
	private IModelDatabaseFollowers mModelDatabaseFollowerUsers;
	private IModelDatabaseUsers mModelDatabaseAllUsers;
	private IModelDatabaseTwits mModelDatabaseAllTwits;
	private IModelNotifications mModelNotifications;

	private IDatabaseWritter mDatabaseWritter;
	private IDatabaseReader mDatabaseReader;
	
	public ControllerMain(TwitubMainView window, IDatabase database, EntityManager entityManager) {
		this.mDatabaseWritter = new DatabaseWritter(entityManager);
		this.mDatabaseReader = new DatabaseReader(database);
	
		this.mWindow = window;
		
		
		
		this.cControllerRouter = new ControllerRouterHome();

		this.mModelDatabaseAllUsers = new ModelDatabaseUsers(this.mDatabaseReader);
		this.mModelDatabaseAllTwits = new ModelDatabaseTwits(this.mDatabaseReader);
		this.mModelSession = new ModelSession();
		this.cControllerSession = new ControllerSession(this.mModelSession, this.mModelDatabaseAllUsers);
		this.mModelSession.addObserver(this);
		
		this.mModelDatabaseFollowerUsers = new ModelDatabaseFollowers(this.mModelDatabaseAllUsers);
		
		this.mModelNotifications = new ModelNotifications(this.mModelSession, this.mDatabaseReader);
		
		this.vViewLogin = new ViewNotLogged(new ControllerCompLogin(this, this.cControllerSession, mModelSession));
		this.vViewSignUp = new ViewSignUp(new ControllerSignUp(this.mDatabaseWritter, this, this.mModelDatabaseAllUsers, this.mModelSession));

		this.vViewTwitFeed = new ViewTwitubFeed(
				this.cControllerRouter,
				this.makeComponentProfil(),
				this.makeComponentFeedWithPost(),
				this.makeComponentFollowersWithSearchBar(),
				this.makeComponentNotification()
		);
	}

	private ComponentNotifications makeComponentNotification() {
		IModelDatabaseTwits databaseTwits = new ModelDatabaseTwitsNotifications(this.mModelNotifications);
		return new ComponentNotifications(databaseTwits);
	}

	private ComponentFeedWithPost makeComponentFeedWithPost() {
		return new ComponentFeedWithPost(this.makeComponentTwitWritter(), this.makeComponentFeedWithSearchBar());
	}

	private ComponentProfil makeComponentProfil() {
		return new ComponentProfil(
				this.cControllerRouter,
				new ControllerProfil(this.cControllerRouter, this.mModelNotifications),
				this.cControllerSession,
				this.mModelSession,
				this.mModelNotifications);
	}

	private ComponentTwitWritter makeComponentTwitWritter() {
		return new ComponentTwitWritter(
				new ControllerTwitWritter(this.mDatabaseWritter, this.mModelSession));
	}

	private ComponentFeedWithSearchBar makeComponentFeedWithSearchBar() {
		IControllerSearch controllerSearchFollowers = new ControllerSearch();
		
		IModelDatabaseTwits databaseTwits = new ModelDatabaseTwitsFiltered(controllerSearchFollowers, this.mModelDatabaseAllTwits);
		
		return new ComponentFeedWithSearchBar(controllerSearchFollowers, databaseTwits);
	}

	private ComponentFollowersWithSearchBar makeComponentFollowersWithSearchBar() {

		IControllerSearch controllerSearchFollowers = new ControllerSearch();

		IModelDatabaseFollowers databaseFollowers = new ModelDatabaseFollowerFiltered(
				controllerSearchFollowers,
				this.mModelDatabaseFollowerUsers);
		
		return new ComponentFollowersWithSearchBar(
				controllerSearchFollowers,
				new ControllerUserReaderFollow(this.mDatabaseWritter, this.mModelSession),
				new ControllerUserReaderUnfollow(this.mDatabaseWritter, this.mModelSession),
				new ModelViewFollowerListFollow(databaseFollowers, this.mModelSession),
				new ModelViewFollowerListUnfollow(databaseFollowers, this.mModelSession),
				databaseFollowers);
	}

	@Override
	public void gotoLogin() {
		this.changeView(vViewLogin);
	}

	@Override
	public void gotoSignUp() {
		this.changeView(vViewSignUp);
	}

	@Override
	public void gotoTwitFeed() {
		this.changeView(vViewTwitFeed);
	}

	@Override
	public void notifyConnect() {
		this.gotoTwitFeed();
	}

	@Override
	public void notifyDisconnect() {
		this.gotoLogin();
	}
	
	private void changeView(ATwitubView view) {
		this.mWindow.setCurrentView(view.getView());
	}

}
