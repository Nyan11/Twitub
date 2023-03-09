package main.java.com.ubo.tp.twitub.ihm.models.twits;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReader;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseReaderObserver;

public class ModelDatabaseTwits implements IModelDatabaseTwits, IDatabaseReaderObserver {

	private Set<IModelDatabaseTwitsObserver> mObservers;
	private Collection<Twit> twits;
	private boolean retreiveTwits;
	private IDatabaseReader mDatabase;
	
	public ModelDatabaseTwits(IDatabaseReader databaseReader) {
		this.retreiveTwits = true;
		this.mDatabase = databaseReader;
		this.mDatabase.addObserver(this);
		this.mObservers = new HashSet<>();
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
	public void notifyTwitAdded(Twit addedTwit) {
		this.retreiveTwits = true;
		Set<IModelDatabaseTwitsObserver> clonedList = this.mObservers;
		for (IModelDatabaseTwitsObserver observer : clonedList) {
			observer.notifyTwitsFeedUpdate();
		}
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		this.retreiveTwits = true;
		Set<IModelDatabaseTwitsObserver> clonedList = this.mObservers;
		for (IModelDatabaseTwitsObserver observer : clonedList) {
			observer.notifyTwitsFeedUpdate();
		}
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		this.retreiveTwits = true;
		Set<IModelDatabaseTwitsObserver> clonedList = this.mObservers;
		for (IModelDatabaseTwitsObserver observer : clonedList) {
			observer.notifyTwitsFeedUpdate();
		}
	}

	@Override
	public Collection<Twit> getTwits() {
		if (this.shouldRetreiveTwits()) {
			this.retreiveTwits();
		}
		return this.twits;
	}

	private void retreiveTwits() {
		this.twits = this.mDatabase.getTwits();
		this.retreiveTwits = false;
	}

	private boolean shouldRetreiveTwits() {
		return this.retreiveTwits;
	}
}
