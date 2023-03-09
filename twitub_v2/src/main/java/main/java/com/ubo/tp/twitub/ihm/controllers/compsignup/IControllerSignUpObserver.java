package main.java.com.ubo.tp.twitub.ihm.controllers.compsignup;

public interface IControllerSignUpObserver {

	void notifySignUpSuccess();
	void notifySignUpErrorCouldNotCreateNewUser();
	void notifySignUpErrorPasswordsNotIdentical();
	void notifySignUpErrorMissingCredential();
}
