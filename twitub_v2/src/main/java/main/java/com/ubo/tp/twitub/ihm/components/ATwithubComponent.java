package main.java.com.ubo.tp.twitub.ihm.components;

import javax.swing.JComponent;

public abstract class ATwithubComponent {

	protected JComponent mComponent;
	
	public JComponent getComponent() {
		if (mComponent == null) {
			this.initComponent();
		}
		return mComponent;
	}

	protected abstract void initComponent();
}
