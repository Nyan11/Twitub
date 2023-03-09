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
import main.java.com.ubo.tp.twitub.ihm.controllers.compsignup.ControllerSignUp;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsignup.IControllerSignUp;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsignup.IControllerSignUpObserver;

public class ComponentSignUp extends ATwithubComponent implements IControllerSignUpObserver {

	private JTextField nameField;
	private JTextField tagField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private IControllerSignUp controller;
	private JLabel errorLabel;

	public ComponentSignUp(ControllerSignUp controller) {
		this.controller = controller;
		this.controller.addObserver(this);
	}

	@Override
	protected void initComponent() {
		JLabel labelName;
		JLabel labelTag;
		JLabel labelPassword;

		JButton buttonCreate = new JButton(TwithubRessources.SIGNUP_CREATE);
		JButton buttonCancel = new JButton(TwithubRessources.SIGNUP_CANCEL);

		this.mComponent = new JPanel(new GridBagLayout());

		nameField = new JTextField();
		tagField = new JTextField();

		passwordField1 = new JPasswordField();
		passwordField2 = new JPasswordField();

		errorLabel = new JLabel(" ");
		labelName = new JLabel(TwithubRessources.SIGNUP_LABEL_NAME);
		labelTag = new JLabel(TwithubRessources.SIGNUP_LABEL_TAG);
		labelPassword = new JLabel(TwithubRessources.SIGNUP_LABEL_PASSWORD);

		buttonCreate.addActionListener(e -> this.actionCreate());

		buttonCancel.addActionListener(e -> this.actionCancel());

		this.mComponent.add(labelName, new GridBagConstraints(0, 0, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));
		this.mComponent.add(nameField, new GridBagConstraints(0, 1, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));

		this.mComponent.add(labelTag, new GridBagConstraints(0, 2, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));
		this.mComponent.add(tagField, new GridBagConstraints(0, 3, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));

		this.mComponent.add(labelPassword, new GridBagConstraints(0, 4, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));
		this.mComponent.add(passwordField1, new GridBagConstraints(0, 5, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));
		this.mComponent.add(passwordField2, new GridBagConstraints(0, 6, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));

		this.mComponent.add(errorLabel, new GridBagConstraints(0, 7, 2, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 0, 16), 0, 0));

		this.mComponent.add(buttonCreate, new GridBagConstraints(0, 8, 1, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 16, 16), 0, 0));
		this.mComponent.add(buttonCancel, new GridBagConstraints(1, 8, 1, 1, 0.5, 0.5, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(16, 16, 16, 16), 0, 0));
	}

	private void actionCancel() {
		this.controller.actionCancel();
	}

	private void actionCreate() {
		String name = this.nameField.getText();
		String tag = this.tagField.getText();
		String password1 = String.valueOf(this.passwordField1.getPassword());
		String password2 = String.valueOf(this.passwordField2.getPassword());

		this.controller.actionSignUp(name, tag, null, password1, password2);
	}

	private void errorMessage(String error) {
		this.errorLabel.setText(error);
	}

	@Override
	public void notifySignUpErrorCouldNotCreateNewUser() {
		this.errorMessage(TwithubRessources.SIGNUP_ERROR_COULD_NOT_CREATE);
	}

	@Override
	public void notifySignUpErrorPasswordsNotIdentical() {
		this.errorMessage(TwithubRessources.SIGNUP_ERROR_PASSWORD_DIFFERENT);
		this.passwordField1.setText("");
		this.passwordField2.setText("");
	}

	@Override
	public void notifySignUpErrorMissingCredential() {
		this.errorMessage(TwithubRessources.SIGNUP_ERROR_MISSING_CREDENTIALS);
	}

	@Override
	public void notifySignUpSuccess() {
		this.nameField.setText("");
		this.tagField.setText("");
		this.passwordField1.setText("");
		this.passwordField2.setText("");
		this.errorLabel.setText(" ");
	}
}
