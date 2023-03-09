package main.java.com.ubo.tp.twitub.ihm.controllers.session;


import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;
import main.java.com.ubo.tp.twitub.ihm.models.users.IModelDatabaseUsers;

public class ControllerSession  implements IControllerSession {

	private IModelSession mSession;
	private IModelDatabaseUsers mUserDatabase;

	public ControllerSession(IModelSession session, IModelDatabaseUsers userDatabase) {
		this.mSession = session;
		this.mUserDatabase = userDatabase;
	}

	@Override
	public void disconnect() {
		this.mSession.setUser(null);
	}

	@Override
	public void connect(String username, String password) {
		for (User u : this.mUserDatabase.getUsers()) {
			if (u.getUserTag().equals(username) && u.getUserPassword().equals(password)) {
				this.mSession.setUser(u);
				return;
			}
		}
	}
}
