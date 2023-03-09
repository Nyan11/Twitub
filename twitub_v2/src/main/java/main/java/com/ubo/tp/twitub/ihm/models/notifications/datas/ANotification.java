package main.java.com.ubo.tp.twitub.ihm.models.notifications.datas;

import main.java.com.ubo.tp.twitub.datamodel.Twit;

public abstract class ANotification {
	
	private long timestamp;
	private Twit mTwit;

	protected ANotification(Twit twit) {
		this.timestamp = System.currentTimeMillis();
		this.mTwit = twit;
	}

	public Twit getTwit() {
		return this.mTwit;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
}
