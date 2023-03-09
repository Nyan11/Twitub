package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.controllers.comptwitwritter.IControllerTwitWritter;
import main.java.com.ubo.tp.twitub.ihm.controllers.comptwitwritter.IControllerTwitWritterObserver;

public class ComponentTwitWritter extends ATwithubComponent implements IControllerTwitWritterObserver {

	private JButton buttonPost;
	private JLabel sizeLabel;
	private IControllerTwitWritter mController;
	protected JTextArea textArea;
	protected JLabel userLabel;

	public ComponentTwitWritter(IControllerTwitWritter controller) {
		this.mController = controller;
		this.mController.addObserver(this);
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JPanel(new GridBagLayout());
		JScrollPane scrollPane;

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(450, 110));

		userLabel = new JLabel("");

		this.mComponent.add(userLabel, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 0, 4), 0, 0));
		this.mComponent.add(scrollPane, new GridBagConstraints(0, 1, 3, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.BOTH, new Insets(4, 4, 4, 4), 0, 0));

		this.initActionsComponents();
	}

	private void initActionsComponents() {
		this.buttonPost = new JButton(TwithubRessources.TWIT_WRITTER_POST);
		this.buttonPost.setEnabled(false);

		this.sizeLabel = new JLabel(TwithubRessources.TWIT_WRITTER_SIZE_LABEL_START + "0"
				+ TwithubRessources.TWIT_WRITTER_SIZE_LABEL_END);

		buttonPost.addActionListener(e -> this.mController.actionPost(this.textArea.getText()));

		textArea.addCaretListener(e -> this.mController.actionCaretUpdated(textArea.getText()));

		this.mComponent.add(sizeLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
		this.mComponent.add(buttonPost, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
	}

	@Override
	public void notifyTwitPostSuccess() {
		this.textArea.setText("");
		this.sizeLabel.setText(TwithubRessources.TWIT_WRITTER_SIZE_LABEL_START + "0"
				+ TwithubRessources.TWIT_WRITTER_SIZE_LABEL_END);
	}

	@Override
	public void notifyTwitPostErrorNotLogged() {
		this.textArea.setText("H3ll0 h4ck3r !");
	}

	@Override
	public void notifyTwitPostErrorTwitTooLong() {
		this.disableButtonPost();
	}

	@Override
	public void notifyTwitPostErrorTwitTooShort() {
		this.disableButtonPost();
	}
	
	private void disableButtonPost() {
		this.buttonPost.setEnabled(false);
		this.mComponent.revalidate();
	}

	@Override
	public void notifyTwitPostErrorTwitGoodLenght(int contentSize) {
		this.buttonPost.setEnabled(true);
		this.sizeLabel.setText(TwithubRessources.TWIT_WRITTER_SIZE_LABEL_START + String.valueOf(contentSize)
				+ TwithubRessources.TWIT_WRITTER_SIZE_LABEL_END);
	}

}
