package main.java.com.ubo.tp.twitub.ihm.models.followers.views;

import java.util.Collection;
import java.util.Collections;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.models.followers.IModelDatabaseFollowers;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;

public class ModelViewFollowerListFollow extends AModelViewFollowers implements IModelViewFollowers {

	private IModelDatabaseFollowers mDatabaseFollowers;
	private IModelSession mSession;
	
	public ModelViewFollowerListFollow(IModelDatabaseFollowers databaseFollowers, IModelSession session) {
		super();
		this.mDatabaseFollowers = databaseFollowers;
		this.mSession = session;
	}

	@Override
	public Collection<User> getUsers() {
		if (this.mSession.isConnected()) {
			return this.mDatabaseFollowers.getFollowUsers(this.mSession.getUser());
		}
		return Collections.emptyList();
	}
}
