import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * La vue : l'interface avec l'utilisateur.
 *
 * On définit une classe chapeau [CVue] qui crée la fenêtre principale de
 * l'application et contient les deux parties principales de notre vue :
 *  - Une zone d'affichage où on voit l'ensemble des cellules.
 *  - Une zone de commande avec un bouton pour passer à la génération suivante.
 */
class CVue {


    /** Construction d'une vue attachée à un modèle. */
    public CVue(CModele modele) throws IOException {
        JFrame frame = new JFrame("Jeu de la vie de Conway");
        frame.setLayout(new FlowLayout());

        VueGrille grille = new VueGrille(modele);
        frame.add(grille);

        VueCommandes commandes = new VueCommandes(modele);
        frame.add(commandes);



        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}