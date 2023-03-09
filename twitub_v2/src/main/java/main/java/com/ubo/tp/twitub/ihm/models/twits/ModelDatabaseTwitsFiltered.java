package main.java.com.ubo.tp.twitub.ihm.models.twits;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import main.java.com.ubo.tp.twitub.common.Constants;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearch;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearchObserver;

public class ModelDatabaseTwitsFiltered
		implements IModelDatabaseTwits, IModelDatabaseTwitsObserver, IControllerSearchObserver {

	private IModelDatabaseTwits mOrigin;
	private Set<IModelDatabaseTwitsObserver> mObservers;
	private boolean searching;
	private String textSearch;

	public ModelDatabaseTwitsFiltered(IControllerSearch controllerSearch, IModelDatabaseTwits orgin) {
		this.mObservers = new HashSet<>();
		this.mOrigin = orgin;
		this.mOrigin.addObserver(this);
		controllerSearch.addObserver(this);
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
	public Collection<Twit> getTwits() {
		List<Twit> returnTwits = new LinkedList<>();
		for (Twit t : this.mOrigin.getTwits()) {
			this.searchFor(t, returnTwits);
		}
		return returnTwits;
	}

	private void searchFor(Twit t, List<Twit> returnTwits) {
		if (!this.searching) {
			returnTwits.add(t);
			return;
		}
		if (this.isSearchUser()) {
			this.searchUserFor(t, returnTwits);
		} else if (this.isSearchTag()) {
			this.searchTagFor(t, returnTwits);
		} else {
			if (this.textSearch.length() <= 1)
				return;
			if (!this.searchUserFor(t, returnTwits))
				this.searchTagFor(t, returnTwits);
			// this.searchGenericFor(t, returnTwits);
		}
	}

	private boolean searchGenericFor(Twit t, List<Twit> returnTwits) {
		String[] words = this.textSearch.split(" ");
		String text = t.getText().toLowerCase();
		for (String w : words) {
			if (text.contains(w)) {
				returnTwits.add(t);
				return true;
			}
		}
		return false;
	}

	private boolean searchTagFor(Twit t, List<Twit> returnTwits) {
		String tag = this.textSearch.substring(1);
		if (t.containsTag(tag)) {
			returnTwits.add(t);
			return true;
		}
		return false;
	}

	private boolean searchUserFor(Twit t, List<Twit> returnTwits) {
		String userTag = this.textSearch.substring(1);
		if (t.getTwiter().getUserTag().equals(userTag)) {
			returnTwits.add(t);
			return true;
		}
		if (t.containsUserTag(userTag)) {
			returnTwits.add(t);
			return true;
		}
		return false;
	}

	private boolean isSearchTag() {
		return this.textSearch.length() <= 1
				|| String.valueOf(this.textSearch.charAt(0)).equals(Constants.WORD_TAG_DELIMITER);
	}

	private boolean isSearchUser() {
		return this.textSearch.length() <= 1
				|| String.valueOf(this.textSearch.charAt(0)).equals(Constants.USER_TAG_DELIMITER);
	}

	@Override
	public void notifyTwitsFeedUpdate() {
		this.triggerTwitsFeedUpdate();
	}

	@Override
	public void notifyNoSearch() {
		this.searching = false;
		this.triggerTwitsFeedUpdate();
	}

	@Override
	public void notifySearch(String text) {
		this.searching = true;
		this.textSearch = text;
		this.triggerTwitsFeedUpdate();
	}

	private void triggerTwitsFeedUpdate() {
		Set<IModelDatabaseTwitsObserver> clonedList = this.mObservers;
		for (IModelDatabaseTwitsObserver observer : clonedList) {
			observer.notifyTwitsFeedUpdate();
		}
	}

}
