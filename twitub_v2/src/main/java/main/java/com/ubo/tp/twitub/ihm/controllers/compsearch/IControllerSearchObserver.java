package main.java.com.ubo.tp.twitub.ihm.controllers.compsearch;

public interface IControllerSearchObserver {

	void notifyNoSearch();
	void notifySearch(String text);

}
