import java.io.IOException;
import java.util.*;
import java.awt.*;

interface Observer{
	void update();
}

/**
 * Classe des objets pouvant être observés.
 */
abstract class Observable{
	/**
	 * On a une liste [observers] d'observateurs, initialement vide, à laquelle
	 * viennent s'inscrire les observateurs via la méthode [addObserver].
	 */

	private final ArrayList<Observer> observers;
	public Observable() {
		this.observers = new ArrayList<>();
	}
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Lorsque l'état de l'objet observé change, il est convenu d'appeler la
	 * méthode [notifyObservers] pour prévenir l'ensemble des observateurs
	 * enregistrés.
	 * On le fait ici concrètement en appelant la méthode [update] de chaque
	 * observateur.
	 */
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update();
		}
	}
}
/** Fin du schéma observateur/observé. */

public class Conway {
	/**
	 * L'amorçage est fait en créant le modèle et la vue, par un simple appel
	 * à chaque constructeur.
	 * Ici, le modèle est créé indépendamment (il s'agit d'une partie autonome
	 * de l'application), et la vue prend le modèle comme paramètre (son
	 * objectif est de faire le lien entre modèle et utilisateur).
	 */
	public static void main(String[] args) {
		/*
		  Pour les besoins du jour, on considère la ligne EvenQueue... comme une
		  incantation qu'on pourra expliquer plus tard.
		 */
		EventQueue.invokeLater(() -> {
			/* Voici le contenu qui nous intéresse. */
			CModele modele = new CModele();
			try {
				new CVue(modele);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}















