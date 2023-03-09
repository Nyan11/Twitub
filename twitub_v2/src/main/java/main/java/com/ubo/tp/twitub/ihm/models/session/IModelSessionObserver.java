package main.java.com.ubo.tp.twitub.ihm.models.session;

public interface IModelSessionObserver {

	public void notifyConnect();
	public void notifyDisconnect();

}
