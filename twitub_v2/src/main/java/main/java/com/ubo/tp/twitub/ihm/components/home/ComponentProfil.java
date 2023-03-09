package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.controllers.compprofil.IControllerProfil;
import main.java.com.ubo.tp.twitub.ihm.controllers.routers.IControllerRouterHome;
import main.java.com.ubo.tp.twitub.ihm.controllers.routers.IControllerRouterHomeObserver;
import main.java.com.ubo.tp.twitub.ihm.controllers.session.IControllerSession;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.IModelNotifications;
import main.java.com.ubo.tp.twitub.ihm.models.notifications.IModelNotificationsObserver;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSession;
import main.java.com.ubo.tp.twitub.ihm.models.session.IModelSessionObserver;

public class ComponentProfil extends ATwithubComponent
		implements IModelSessionObserver, IControllerRouterHomeObserver, IModelNotificationsObserver {

	private JButton feedButton;
	private JButton followButton;
	private JButton notificationsButton;
	private IControllerSession mControllerSession;
	private IControllerProfil mControllerProfil;
	private IModelSession mModelSession;
	private JLabel nameLabel;
	private IModelNotifications mModelNotifications;

	public ComponentProfil(IControllerRouterHome router, IControllerProfil controllerProfil,
			IControllerSession controller, IModelSession model, IModelNotifications modelNotifications) {
		this.mControllerSession = controller;
		this.mModelSession = model;
		this.mControllerProfil = controllerProfil;
		this.mModelNotifications = modelNotifications;

		modelNotifications.addObserver(this);
		router.addObserver(this);
		model.addObserver(this);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());

		nameLabel = new JLabel(" ");

		this.mComponent.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.initActionsComponents();
	}

	private void initActionsComponents() {
		this.feedButton = new JButton(TwithubRessources.USER_INFO_FEED);
		this.followButton = new JButton(TwithubRessources.USER_INFO_FOLLOW);
		this.notificationsButton = new JButton("");
		JButton decoButton = new JButton(TwithubRessources.USER_INFO_DECO);

		decoButton.addActionListener(e -> this.mControllerSession.disconnect());
		feedButton.addActionListener(e -> this.mControllerProfil.actionFeed());
		followButton.addActionListener(e -> this.mControllerProfil.actionFollow());
		notificationsButton.addActionListener(e -> this.mControllerProfil.actionNotifications());

		this.mComponent.add(this.feedButton, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.mComponent.add(this.followButton, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		this.mComponent.add(this.notificationsButton, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		this.mComponent.add(decoButton, new GridBagConstraints(0, 5, 1, 1, 1, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(8, 4, 4, 4), 0, 0));

		this.feedButton.setEnabled(false);
		this.followButton.setEnabled(true);
		this.notificationsButton.setEnabled(this.hasNotifications());
		this.updateNotificationsLabel();
	}

	@Override
	public void notifyConnect() {
		if (this.mComponent == null) {
			this.initComponent();
		}
		this.nameLabel
				.setText(this.mModelSession.getUser().getName() + " -- @" + this.mModelSession.getUser().getUserTag());
	}

	@Override
	public void notifyDisconnect() {
		this.nameLabel.setText("");
	}

	@Override
	public void notifyRouterFeed() {
		this.feedButton.setEnabled(false);
		this.followButton.setEnabled(true);
		this.notificationsButton.setEnabled(this.hasNotifications());
		this.updateNotificationsLabel();
	}

	@Override
	public void notifyRouterFollow() {
		this.feedButton.setEnabled(true);
		this.followButton.setEnabled(false);
		this.notificationsButton.setEnabled(this.hasNotifications());
		this.updateNotificationsLabel();
	}

	@Override
	public void notifyRouterNotification() {
		this.feedButton.setEnabled(true);
		this.followButton.setEnabled(true);
		this.notificationsButton.setEnabled(false);
		this.updateNotificationsLabel();
	}

	@Override
	public void notifyNewNotifications() {
		this.notificationsButton.setEnabled(this.hasNotifications());
		this.updateNotificationsLabel();
	}

	private void updateNotificationsLabel() {
		String label = TwithubRessources.USER_INFO_NOTIFICATION;
		label += " - " + this.mModelNotifications.numberNotifications();
		this.notificationsButton.setText(label);
	}

	private boolean hasNotifications() {
		return this.mModelNotifications.hasNotifications();
	}
}
