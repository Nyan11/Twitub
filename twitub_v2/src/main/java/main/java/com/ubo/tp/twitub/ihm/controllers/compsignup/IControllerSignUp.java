package main.java.com.ubo.tp.twitub.ihm.controllers.compsignup;

import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IControllerSignUp {

	void addObserver(IControllerSignUpObserver observer);
	void deleteObserver(IControllerSignUpObserver observer);

	void actionSignUp(String name, String tag, String avatar, String password1, String password2);
	void actionCancel();
	boolean addUser(User user);
}
