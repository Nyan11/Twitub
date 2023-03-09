package main.java.com.ubo.tp.twitub.ihm.controllers.comptwitwritter;

public interface IControllerTwitWritterObserver {

	
	public void notifyTwitPostSuccess();
	
	public void notifyTwitPostErrorNotLogged();
	public void notifyTwitPostErrorTwitTooLong();
	public void notifyTwitPostErrorTwitTooShort();
	public void notifyTwitPostErrorTwitGoodLenght(int contentSize);
	
}
