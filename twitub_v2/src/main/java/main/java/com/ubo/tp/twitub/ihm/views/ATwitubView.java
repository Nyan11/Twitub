package main.java.com.ubo.tp.twitub.ihm.views;

import javax.swing.JComponent;

public abstract class ATwitubView {

	protected JComponent mView;
	

	public JComponent getView() {
		if (this.mView == null) {
			this.initView();
		}
		return this.mView;
	}

	protected abstract void initView();
}
