package main.java.com.ubo.tp.twitub.ihm.controllers.complogin;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.twitub.ihm.controllers.main.IControllerMain;
import main.java.com.ubo.tp.twitub.ihm.controllers.session.IControllerSession;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;

public class ControllerCompLogin implements IControllerCompLogin {

	private Set<IControllerCompLoginObserver> mObservers;
	private IControllerMain mControllerRouter;
	private IControllerSession mControllerSession;
	private IModelSession mModelSession;
	
	public ControllerCompLogin(IControllerMain controllerRouter, IControllerSession controllerSession, IModelSession modelSession) {
		this.mObservers = new HashSet<>();
		this.mControllerRouter = controllerRouter;
		this.mControllerSession = controllerSession;
		this.mModelSession = modelSession;
	}

	@Override
	public void addObserver(IControllerCompLoginObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IControllerCompLoginObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void actionLogin(String userName, String password) {
		Set<IControllerCompLoginObserver> clonedList = this.mObservers;
		
		// notifyLoginErrorMissingCredential
		if (userName.isEmpty() || password.isEmpty()) {
			for (IControllerCompLoginObserver observer : clonedList) {
				observer.notifyLoginErrorMissingCredential();
			}
			return;
		}
		
		this.mControllerSession.connect(userName, password);

		// notifyLoginErrorWrongCredential
		if (!this.mModelSession.isConnected()) {
			for (IControllerCompLoginObserver observer : clonedList) {
				observer.notifyLoginErrorMissingCredential();
			}
			return;
		}
		
		// notifyLoginSuccess
		for (IControllerCompLoginObserver observer : clonedList) {
			observer.notifyLoginSuccess();
		}
	}

	@Override
	public void actionSignUp() {
		this.mControllerRouter.gotoSignUp();
	}
}
