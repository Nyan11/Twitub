package main.java.com.ubo.tp.twitub.ihm.controllers.compsearch;

public interface IControllerSearch {

	void addObserver(IControllerSearchObserver observer);
	void deleteObserver(IControllerSearchObserver observer);
	
	void actionCaretUpdated(String text);

}
