package main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader;

import java.util.HashSet;
import java.util.Set;

public abstract class AControllerUserReader implements IControllerUserReader {
	
	private Set<IControllerUserReaderObserver> mObservers;
	
	protected AControllerUserReader() {
		this.mObservers = new HashSet<>();
	}

	public void notifyUsersUpdate() {
		Set<IControllerUserReaderObserver> clonedList = this.mObservers;
		for (IControllerUserReaderObserver observer : clonedList) {
			observer.notifyFollowersUpdate();
		}
	}
	
	@Override
	public void addObserver(IControllerUserReaderObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IControllerUserReaderObserver observer) {
		this.mObservers.remove(observer);
	}

}
