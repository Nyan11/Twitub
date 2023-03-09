package main.java.com.ubo.tp.twitub.ihm;

import java.io.File;

import javax.swing.ImageIcon;

public class TwithubRessources {

	public static final String APP_NAME = "Twitub";
	public static final String APP_SHORT = "Avec Twitub partagez vos opinions avec des inconnus";

	public static final String ABOUT_NAME = "?";
	public static final String FILE_NAME = "Fichier";
	public static final String FILE_QUIT_NAME = "Quitter";
	public static final ImageIcon FILE_QUIT_ICON = new ImageIcon("../main/resources/images/exitIcon_20.png");
	public static final String ABOUT_AUTHOR_NAME = "A propos";
	public static final ImageIcon FILE_OPEN_ICON = new ImageIcon("../main/resources/images/editIcon_20.png");
	public static final String FILE_OPEN_NAME = "Ouvrir un nouveau fichier";
	public static final File FILE_OPEN_DIRECTORY = new File("../main/resources");
	
	public static final String ABOUT_TITLE = "A propos";
	public static final String ABOUT_MESSAGE = "M2 TIIL A\nDépartement informatique";
	public static final ImageIcon ABOUT_ICON = new ImageIcon("../main/resources/images/logo_50.jpg");
	
	public static final String WINDOW_NAME = "Twitub";
	public static final ImageIcon WINDOW_ICON = new ImageIcon("../main/resources/images/logo_20.jpg");
	
	public static final String LOGIN_CONNECT = "Se connecter";
	public static final String LOGIN_LOGIN = "Nom utilisateur";
	public static final String LOGIN_PASSWORD = "Mot de Passe";
	public static final String LOGIN_ERROR_WRONG = "Nom utilisateur ou mot de passe incorrecte";
	public static final String LOGIN_ERROR_MISSING = "Nom utilisateur ou mot de passe manquant";
	public static final String LOGIN_SIGNUP = "Créer un compte";
	
	public static final String SIGNUP_CREATE = "Créer un compte";
	public static final String SIGNUP_CANCEL = "Anuller";
	public static final String SIGNUP_LABEL_NAME = "Nom utilisateur";
	public static final String SIGNUP_LABEL_TAG = "Tag";
	public static final String SIGNUP_LABEL_PASSWORD = "Mot de passe";
	public static final String SIGNUP_ERROR_COULD_NOT_CREATE = "Impossible de créer un compte avec les informations fournies.";
	public static final String SIGNUP_ERROR_PASSWORD_DIFFERENT = "Les mots de passe sont différents.";
	public static final String SIGNUP_ERROR_MISSING_CREDENTIALS = "Un des champs est vide.";
	
	public static final String USER_INFO_DECO = "Se déconnecter";
	public static final String USER_INFO_FEED = "Voir les derniers Twits";
	public static final String USER_INFO_FOLLOW = "Voir les gens que vous suivez";
	public static final String USER_INFO_NOTIFICATION = "Notification ";
	
	public static final String TWIT_WRITTER_LABEL = "Votre twit";
	public static final String TWIT_WRITTER_POST = "Poster";
	public static final String TWIT_WRITTER_SIZE_LABEL_START = "Taille du twit : ";
	public static final String TWIT_WRITTER_SIZE_LABEL_END = ".";
	
	public static final String SEARCH_LABEL = "Recherche :";
	
	public static final String FOLLOW_FOLLOW = "S'abonner";
	public static final String FOLLOW_UNFOLLOW = "Se désabonner";
	
	private TwithubRessources() {
	    throw new IllegalStateException("Ressources class");
	  }

}
