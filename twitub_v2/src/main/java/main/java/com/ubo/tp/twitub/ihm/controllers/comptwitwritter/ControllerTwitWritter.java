package main.java.com.ubo.tp.twitub.ihm.controllers.comptwitwritter;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseWritter;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;

public class ControllerTwitWritter implements IControllerTwitWritter {

	private static final int MAXIMUM_LENGHT = 250;
	private static final int MINIMUM_LENGHT = 0;

	private Set<IControllerTwitWritterObserver> mObservers;
	private IModelSession mSession;
	private IDatabaseWritter mDatabase;

	public ControllerTwitWritter(IDatabaseWritter database, IModelSession session) {
		this.mObservers = new HashSet<>();
		this.mSession = session;
		this.mDatabase = database;
	}

	@Override
	public void addObserver(IControllerTwitWritterObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IControllerTwitWritterObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public void actionPost(String content) {
		Set<IControllerTwitWritterObserver> clonedList = this.mObservers;

		// notifyTwitPostErrorNotLogged
		if (!this.mSession.isConnected()) {
			for (IControllerTwitWritterObserver observer : clonedList) {
				observer.notifyTwitPostErrorNotLogged();
			}
			return;
		}

		Twit twit = new Twit(this.mSession.getUser(), content);
		this.addTwit(twit);

		// notifyTwitPostSuccess
		for (IControllerTwitWritterObserver observer : clonedList) {
			observer.notifyTwitPostSuccess();
		}
	}
	
	@Override
	public void addTwit(Twit twit) {
		this.mDatabase.addTwit(twit);
	}

	@Override
	public void actionCaretUpdated(String content) {
		Set<IControllerTwitWritterObserver> clonedList = this.mObservers;
		int contentSize = content.length();
		
		// notifyTwitPostErrorTwitTooLong
		if (contentSize > MAXIMUM_LENGHT) {
			for (IControllerTwitWritterObserver observer : clonedList) {
				observer.notifyTwitPostErrorTwitTooLong();
			}
			return;
		}

		// notifyTwitPostErrorTwitTooShort
		if (contentSize <= MINIMUM_LENGHT) {
			for (IControllerTwitWritterObserver observer : clonedList) {
				observer.notifyTwitPostErrorTwitTooShort();
			}
			return;
		}
		
		// notifyTwitPostErrorTwitGoodLenght
		for (IControllerTwitWritterObserver observer : clonedList) {
			observer.notifyTwitPostErrorTwitGoodLenght(contentSize);
		}
	}
}
