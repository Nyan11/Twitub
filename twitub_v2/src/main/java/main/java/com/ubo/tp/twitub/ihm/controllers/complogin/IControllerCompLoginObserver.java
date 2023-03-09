package main.java.com.ubo.tp.twitub.ihm.controllers.complogin;


public interface IControllerCompLoginObserver {

	void notifyLoginSuccess();
	void notifyLoginErrorWrongCredential();
	void notifyLoginErrorMissingCredential();

}
