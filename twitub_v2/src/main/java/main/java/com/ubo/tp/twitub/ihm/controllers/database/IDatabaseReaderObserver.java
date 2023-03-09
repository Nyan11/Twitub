package main.java.com.ubo.tp.twitub.ihm.controllers.database;

import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public interface IDatabaseReaderObserver {

	default void notifyTwitAdded(Twit addedTwit) {}
	default void notifyTwitDeleted(Twit deletedTwit) {}
	default void notifyTwitModified(Twit modifiedTwit) {}
	default void notifyUserAdded(User addedUser) {}
	default void notifyUserDeleted(User deletedUser) {}
	default void notifyUserModified(User modifiedUser) {}

}
