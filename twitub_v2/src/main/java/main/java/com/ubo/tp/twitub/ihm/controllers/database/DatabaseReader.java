package main.java.com.ubo.tp.twitub.ihm.controllers.database;

import java.util.Collection;
import java.util.HashSet;

import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class DatabaseReader implements IDatabaseReader, IDatabaseObserver {

	private HashSet<IDatabaseReaderObserver> mObservers;
	private IDatabase mDatabase;

	public DatabaseReader(IDatabase database) {
		this.mObservers = new HashSet<>();
		this.mDatabase = database;
		database.addObserver(this);
	}

	@Override
	public void addObserver(IDatabaseReaderObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void removeObserver(IDatabaseReaderObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public Collection<Twit> getTwits() {
		return this.mDatabase.getTwits();
	}

	@Override
	public Collection<User> getUsers() {
		return this.mDatabase.getUsers();
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		for (IDatabaseReaderObserver observer : this.mObservers) {
			observer.notifyTwitAdded(addedTwit);
		}
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		for (IDatabaseReaderObserver observer : this.mObservers) {
			observer.notifyTwitDeleted(deletedTwit);
		}
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		for (IDatabaseReaderObserver observer : this.mObservers) {
			observer.notifyTwitModified(modifiedTwit);
		}
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		for (IDatabaseReaderObserver observer : this.mObservers) {
			observer.notifyUserAdded(addedUser);
		}
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		for (IDatabaseReaderObserver observer : this.mObservers) {
			observer.notifyUserDeleted(deletedUser);
		}
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		for (IDatabaseReaderObserver observer : this.mObservers) {
			observer.notifyUserModified(modifiedUser);
		}
	}

}
