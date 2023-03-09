package main.java.com.ubo.tp.twitub.ihm.controllers.database;


import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class DatabaseWritter implements IDatabaseWritter {

	private EntityManager mEntityManager;
	
	public DatabaseWritter(EntityManager entityManager) {
		this.mEntityManager = entityManager;
	}

	@Override
	public void addUser(User user) {
		this.mEntityManager.sendUser(user);
	}

	@Override
	public void addTwit(Twit twit) {
		this.mEntityManager.sendTwit(twit);
	}

	@Override
	public void modifyUser(User user) {
		this.mEntityManager.sendUser(user);
	}

}
