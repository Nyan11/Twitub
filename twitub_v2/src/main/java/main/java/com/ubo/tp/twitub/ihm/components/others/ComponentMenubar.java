package main.java.com.ubo.tp.twitub.ihm.components.others;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.ihm.TwithubRessources;
import main.java.com.ubo.tp.twitub.ihm.components.ATwithubComponent;

public class ComponentMenubar extends ATwithubComponent {

	private IDatabase mDatabase;
	private EntityManager mEntityManager;

	public ComponentMenubar(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
	}

	@Override
	protected void initComponent() {
		this.mComponent = new JMenuBar();
		this.mComponent.add(this.initMenuFile());
		this.mComponent.add(this.initMenuAbout());
	}

	private JMenu initMenuAbout() {
		JMenu menu = new JMenu(TwithubRessources.ABOUT_NAME);
		JMenuItem item1 = new JMenuItem(TwithubRessources.ABOUT_AUTHOR_NAME);
		menu.add(item1);

		item1.addActionListener(e -> this.showAboutWindow());
		return menu;
	}

	protected void showAboutWindow() {
		JOptionPane.showMessageDialog(null, TwithubRessources.ABOUT_MESSAGE, TwithubRessources.ABOUT_TITLE,
				JOptionPane.PLAIN_MESSAGE, TwithubRessources.ABOUT_ICON);
	}

	private JMenu initMenuFile() {

		JMenu menu = new JMenu(TwithubRessources.FILE_NAME);

		JMenuItem item1 = new JMenuItem(TwithubRessources.FILE_OPEN_NAME, TwithubRessources.FILE_OPEN_ICON);
		JMenuItem item2 = new JMenuItem(TwithubRessources.FILE_QUIT_NAME, TwithubRessources.FILE_QUIT_ICON);

		menu.add(item1);
		menu.add(item2);

		item1.addActionListener(e -> this.executeFileOpen());
		item2.addActionListener(e -> this.executeFileQuit());

		return menu;
	}

	private void executeFileOpen() {
		JFileChooser fileChooser = new JFileChooser(TwithubRessources.FILE_OPEN_DIRECTORY);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(mComponent) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (this.mDatabase.isValideExchangeDirectory(file)) {
				this.mEntityManager.setExchangeDirectory(file.getAbsolutePath());
			}
		}
	}

	private void executeFileQuit() {
		System.exit(0);
	}

}
