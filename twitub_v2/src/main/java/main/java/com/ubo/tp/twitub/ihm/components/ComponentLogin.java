package main.java.com.ubo.tp.twitub.ihm.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.controllers.complogin.ControllerCompLogin;
import main.java.com.ubo.tp.twitub.ihm.controllers.complogin.IControllerCompLogin;
import main.java.com.ubo.tp.twitub.ihm.controllers.complogin.IControllerCompLoginObserver;

public class ComponentLogin extends ATwithubComponent implements IControllerCompLoginObserver {

	private JTextField loginField;
	private JPasswordField passwordField;
	private IControllerCompLogin controllerLogin;
	private JLabel errorLabel;

	public ComponentLogin(ControllerCompLogin controller) {
		this.controllerLogin = controller;
		this.controllerLogin.addObserver(this);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		JLabel label;
		
		JButton buttonLogin = new JButton(TwithubRessources.LOGIN_CONNECT);
		JButton buttonSignUp = new JButton(TwithubRessources.LOGIN_SIGNUP);
		loginField = new JTextField();
		passwordField = new JPasswordField();
		errorLabel = new JLabel(" ");
		
		buttonLogin.addActionListener(e -> {
			this.controllerLogin.actionLogin(loginField.getText(), String.valueOf(passwordField.getPassword()));
			this.passwordField.setText("");
		});
		
		buttonSignUp.addActionListener(e -> {
			this.controllerLogin.actionSignUp();
			this.passwordField.setText("");
		});

		contraints.fill = GridBagConstraints.HORIZONTAL;
		contraints.weightx = 0.5;
		contraints.gridwidth = 5;
		contraints.gridx = 0;
		contraints.gridy = 0;
		contraints.insets = new Insets(16,16,0,16);
		
		label = new JLabel(TwithubRessources.LOGIN_LOGIN);
		this.mComponent.add(label, contraints);
		contraints.gridy += 1;
		this.mComponent.add(loginField, contraints);
		contraints.gridy += 1;
		
		label = new JLabel(TwithubRessources.LOGIN_PASSWORD);
		this.mComponent.add(label, contraints);
		contraints.gridy += 1;
		this.mComponent.add(passwordField, contraints);
		contraints.gridy += 1;
		
		this.mComponent.add(errorLabel, contraints);
		contraints.gridy += 1;
		
		this.mComponent.add(buttonLogin, contraints);
		contraints.gridy += 1;

		contraints.gridx = 1;
		contraints.gridwidth = 3;
		contraints.insets = new Insets(16,16,16,16);
		this.mComponent.add(buttonSignUp, contraints);
	}

	@Override
	public void notifyLoginErrorWrongCredential() {
		errorLabel.setText(TwithubRessources.LOGIN_ERROR_WRONG);
		passwordField.setText("");
	}

	@Override
	public void notifyLoginErrorMissingCredential() {
		errorLabel.setText(TwithubRessources.LOGIN_ERROR_MISSING);
		passwordField.setText("");
	}

	@Override
	public void notifyLoginSuccess() {
		this.loginField.setText("");
		this.passwordField.setText("");
		this.errorLabel.setText(" ");
	}
}
