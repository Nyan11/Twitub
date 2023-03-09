package main.java.com.ubo.tp.twitub.ihm.controllers.complogin;

public interface IControllerCompLogin {

	void addObserver(IControllerCompLoginObserver observer);
	void deleteObserver(IControllerCompLoginObserver observer);

	void actionLogin(String userName, String password);
	void actionSignUp();
}
