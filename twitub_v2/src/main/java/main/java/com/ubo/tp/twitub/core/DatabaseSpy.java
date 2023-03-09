package main.java.com.ubo.tp.twitub.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class DatabaseSpy implements IDatabaseObserver {

	private Logger logger;

	public DatabaseSpy(IDatabase mDatabase) {
		mDatabase.addObserver(this);
		this.logger = Logger.getGlobal();
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		this.logger.log(Level.FINE, "notifyTwitAdded: {}", addedTwit);
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		this.logger.log(Level.FINE, "notifyTwitDeleted: {}", deletedTwit);
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		this.logger.log(Level.FINE, "notifyTwitModified: {}", modifiedTwit);
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		this.logger.log(Level.FINE, "notifyUserAdded: {}", addedUser);
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		this.logger.log(Level.FINE, "notifyUserDeleted: {}", deletedUser);
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		this.logger.log(Level.FINE, "notifyUserModified: {}", modifiedUser);
	}

}
