package main.java.com.ubo.tp.twitub.ihm.controllers.compsearch;

import java.util.HashSet;
import java.util.Set;

public class ControllerSearch implements IControllerSearch {

	private Set<IControllerSearchObserver> mObservers;
	
	public ControllerSearch() {
		this.mObservers = new HashSet<>();
	}

	@Override
	public void addObserver(IControllerSearchObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IControllerSearchObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public void actionCaretUpdated(String text) {
		Set<IControllerSearchObserver> clonedList = this.mObservers;
		if (text.isEmpty()) {
			for (IControllerSearchObserver observer : clonedList) {
				observer.notifyNoSearch();
			}
		} else {
			for (IControllerSearchObserver observer : clonedList) {
				observer.notifySearch(text);
			}
		}
	}
	

}
