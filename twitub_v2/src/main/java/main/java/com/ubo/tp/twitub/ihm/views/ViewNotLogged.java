package main.java.com.ubo.tp.twitub.ihm.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.components.ComponentLogin;
import main.java.com.ubo.tp.twitub.ihm.controllers.complogin.ControllerCompLogin;

public class ViewNotLogged extends ATwitubView {

	private ComponentLogin login;

	public ViewNotLogged(ControllerCompLogin controller) {
		this.login = new ComponentLogin(controller);
		this.initView();
	}

	@Override
	protected void initView() {
		this.mView = new JPanel(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		JLabel h1 = new JLabel(TwithubRessources.APP_NAME);
		JLabel h2 = new JLabel(TwithubRessources.APP_SHORT);
		
		contraints.fill = GridBagConstraints.BOTH;
		contraints.weightx = 0.5;
		contraints.weighty = 0.5;
		contraints.gridwidth = 1;

		contraints.insets = new Insets(32,32,32,32);
		contraints.gridx = 0;
		contraints.gridy = 0;
		this.mView.add(h1, contraints);
		
		contraints.gridx = 0;
		contraints.gridy = 1;
		this.mView.add(h2, contraints);
		
		contraints.insets = new Insets(0,32,0,32);
		contraints.gridx = 1;
		contraints.gridy = 0;
		contraints.gridheight = 2;
		this.mView.add(this.login.getComponent(), contraints);
		
	}
}
