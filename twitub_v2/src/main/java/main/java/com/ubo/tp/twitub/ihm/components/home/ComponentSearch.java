package main.java.com.ubo.tp.twitub.ihm.components.home;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;
import main.java.com.ubo.tp.twitub.ihm.controllers.compsearch.IControllerSearch;

public class ComponentSearch extends ATwithubComponent {

	private IControllerSearch mController;

	public ComponentSearch(IControllerSearch controller) {
		this.mController = controller;
	}

	@Override
	protected void initComponent() {
		JTextField searchField = new JTextField();
		JLabel searchLabel = new JLabel(TwithubRessources.SEARCH_LABEL);

		this.mComponent = new JPanel(new GridBagLayout());

		searchField.addCaretListener(e -> this.mController.actionCaretUpdated(searchField.getText()));

		this.mComponent.add(searchLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(8, 8, 8, 8), 0, 0));
		this.mComponent.add(searchField, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(8, 8, 8, 0), 0, 0));

	}

}
