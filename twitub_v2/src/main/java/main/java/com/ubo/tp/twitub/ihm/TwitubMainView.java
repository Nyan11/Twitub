package main.java.com.ubo.tp.twitub.ihm;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.events.file.IWatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.components.others.ComponentMenubar;
import main.java.com.ubo.tp.twitub.ihm.controllers.main.ControllerMain;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {

	/**
	 * Fenetre du bouchon
	 */
	protected JFrame mFrame;

	/**
	 * Base de donénes de l'application.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Gestionnaire de directory
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Controller pour changer de vue.
	 */
	private ControllerMain router;

	/**
	 * Constructeur.
	 * 
	 * @param database            , Base de données de l'application.
	 * @param mWatchableDirectory
	 */
	public TwitubMainView(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.router = new ControllerMain(this, database, entityManager);
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		// Init auto de l'IHM au cas ou ;)
		if (mFrame == null) {
			this.initGUI();
		}

		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitubMainView.this.mFrame;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				// Affichage
				TwitubMainView.this.mFrame.setVisible(true);

				TwitubMainView.this.mFrame.pack();
			}
		});
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		// Création de la fenetre principale
		mFrame = new JFrame(TwithubRessources.WINDOW_NAME);
		mFrame.setIconImage(TwithubRessources.WINDOW_ICON.getImage());

		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.setSize(800, 600);

		// Ajout de la Menubar
		JMenuBar menubar = (JMenuBar) new ComponentMenubar(mDatabase, mEntityManager).getComponent();
		mFrame.setJMenuBar(menubar);

		// set up de la view
		this.router.gotoLogin();
	}

	public void setCurrentView(JComponent view) {
		this.mFrame.getContentPane().removeAll();
		this.mFrame.getContentPane().add(view);
		this.mFrame.setVisible(false);
		this.mFrame.setVisible(true);
	}

	public File askDirectory() {
		JFileChooser fileChooser = new JFileChooser(TwithubRessources.FILE_OPEN_DIRECTORY);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this.mFrame) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
}
