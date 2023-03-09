package main.java.com.ubo.tp.twitub.ihm.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.ihm.components.ComponentSignUp;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsignup.ControllerSignUp;

public class ViewSignUp extends ATwitubView {

	private ComponentSignUp signUp;

	public ViewSignUp(ControllerSignUp controller) {
		this.signUp = new ComponentSignUp(controller);
		this.initView();
	}

	@Override
	protected void initView() {
		this.mView = new JPanel(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		
		contraints.fill = GridBagConstraints.BOTH;
		contraints.weightx = 0.5;
		contraints.weighty = 0.5;
		contraints.gridwidth = 1;

		contraints.insets = new Insets(32,32,32,32);
		contraints.gridx = 0;
		contraints.gridy = 0;
		this.mView.add(this.signUp.getComponent(), contraints);
	}
}
