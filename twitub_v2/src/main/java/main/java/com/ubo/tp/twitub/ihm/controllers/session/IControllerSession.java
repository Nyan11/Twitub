package main.java.com.ubo.tp.twitub.ihm.controllers.session;

public interface IControllerSession {

	void disconnect();
	void connect(String name, String password);
}
