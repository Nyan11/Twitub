package main.java.com.ubo.tp.twitub.ihm.controllers.compuserreader;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseWritter;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;

public class ControllerUserReaderUnfollow extends AControllerUserReader {

	private User user;
	private IModelSession mSession;
	private IDatabaseWritter mDatabaseWritter;

	public ControllerUserReaderUnfollow(IDatabaseWritter databaseWritter, IModelSession session) {
		super();
		this.mDatabaseWritter = databaseWritter;
		this.mSession = session;
	}

	@Override
	public void actionFollow() {
		this.removeFollower(this.user);
	}

	@Override
	public boolean removeFollower(User target) {
		if (this.user == null) return false;
		if (!this.mSession.isConnected()) return false;
		this.mSession.getUser().removeFollowing(target.getUserTag());
		this.mDatabaseWritter.modifyUser(this.mSession.getUser());
		this.notifyUsersUpdate();
		return true;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean addFollower(User target) {
		return false;
	}
}
