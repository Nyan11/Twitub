package main.java.com.ubo.tp.twitub.ihm.controllers.compsignup;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.controllers.database.IDatabaseWritter;
import main.java.com.ubo.tp.twitub.ihm.controllers.main.IControllerMain;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;
import main.java.com.ubo.tp.twitub.ihm.models.users.IModelDatabaseUsers;

public class ControllerSignUp implements IControllerSignUp {

	private Set<IControllerSignUpObserver> mObservers;
	private IModelDatabaseUsers mDatabaseUsers;
	private IModelSession mModelSession;
	private IControllerMain mControlerRouter;
	private IDatabaseWritter mDatabase;

	public ControllerSignUp(IDatabaseWritter database, IControllerMain controlerRouter, IModelDatabaseUsers databaseUsers, IModelSession modelSession) {
		this.mObservers = new HashSet<>();
		this.mDatabaseUsers = databaseUsers;
		this.mControlerRouter = controlerRouter;
		this.mModelSession = modelSession;
		this.mDatabase = database;
	}

	@Override
	public void addObserver(IControllerSignUpObserver observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void deleteObserver(IControllerSignUpObserver observer) {
		this.mObservers.remove(observer);
	}

	@Override
	public void actionCancel() {
		this.mControlerRouter.gotoLogin();
	}

	@Override
	public void actionSignUp(String userName, String tag, String avatar, String password1, String password2) {
		User user;
		Set<IControllerSignUpObserver> clonedList = this.mObservers;
		
		// notifySignUpErrorMissingCredential
		if (userName.isEmpty() || tag.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
			for (IControllerSignUpObserver observer : clonedList) {
				observer.notifySignUpErrorMissingCredential();
			}
			return;
		}
		
		// notifySignUpErrorPasswordsNotIdentical
		if (!password1.equals(password2)) {
			for (IControllerSignUpObserver observer : clonedList) {
				observer.notifySignUpErrorPasswordsNotIdentical();
			}
			return;
		}
		
		// notifySignUpErrorCouldNotCreateNewUser
		user = new User(UUID.randomUUID(), tag, password1, userName, new HashSet<>(), avatar);
		if (!this.addUser(user)) {
			for (IControllerSignUpObserver observer : clonedList) {
				observer.notifySignUpErrorCouldNotCreateNewUser();
			}
			return;
		}
		
		// login on this user
		this.mModelSession.setUser(user);
		
		// notifySignUpSuccess
		for (IControllerSignUpObserver observer : clonedList) {
			observer.notifySignUpSuccess();
		}
	}
	
	@Override
	public boolean addUser(User user) {
		for (User u : this.mDatabaseUsers.getUsers()) {
			if (u.getName().equals(user.getName()) || u.getUserTag().equals(user.getUserTag()) || u.getUuid().equals(user.getUuid())) {
				return false;
			}
		}
		this.mDatabase.addUser(user);
		return true;
	}
}
