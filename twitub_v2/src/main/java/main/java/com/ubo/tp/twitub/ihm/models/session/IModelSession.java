package main.java.com.ubo.tp.twitub.ihm.models.session;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IModelSession {

	public void addObserver(IModelSessionObserver observer);
	public void deleteObserver(IModelSessionObserver observer);

	public boolean isConnected();
	public User getUser();
	public void setUser(User u);

}
