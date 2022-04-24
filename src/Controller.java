import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

class Controller extends MouseAdapter implements ActionListener {
    static int PA_Restant = 3;
    boolean marche = true;
    static boolean partieEnCours = true;
    CModele modele;

    public Controller(CModele modele) {
        this.modele = modele;
    }


    public void mousePressed(MouseEvent e) {
        if (partieEnCours) {
            int x = (e.getX() / 50) + 1;
            int y = (e.getY() / 50) + 1;
            int[] cord = CModele.getPosTableau(CModele.joueurActu.getPosition());
            if (PA_Restant > 0) {
                if ((x - 1 == cord[0] && y == cord[1]) || (x + 1 == cord[0] && y == cord[1]) ||
                    (x == cord[0] && y - 1 == cord[1]) || (x == cord[0] && y + 1 == cord[1]) ||
                    (x == cord[0] && y == cord[1])) {
                    modele.mouse(x, y, e.getButton());
                    PA_Restant -= 1;
                } else if (CModele.joueurActu.inventaire[4][1] == "1" ){
                    System.out.println("UTIISATION DE LA CARTE SPECIALE");
                    modele.mouse(x, y, e.getButton());
                    CModele.joueurActu.inventaire[4][1] = "0";
                    PA_Restant -= 1;

                }else if (CModele.joueurActu.inventaire[5][1] == "1"){
                    System.out.println("UTIISATION DE LA CARTE SPECIALE");
                    modele.mouse(x, y, e.getButton());
                    CModele.joueurActu.inventaire[5][1] = "0";
                    PA_Restant -= 1;

                } else
                    System.out.println("Coordinates out of pos");
            } else
                System.out.println("no more action points remaining");

            VueCommandes.Label_PA.setBackground(CModele.joueurActu.getCouleur());
            VueCommandes.Label_PA.setText(String.valueOf(PA_Restant));
        }
    }

        public void actionPerformed (ActionEvent e) {
            if (partieEnCours) {
                if (Objects.equals(e.getActionCommand(), "fin de tour")) {
                    PA_Restant = 3;
                    modele.avance();
                }
                VueCommandes.Label_PA.setBackground(CModele.joueurActu.getCouleur());
                VueCommandes.Label_PA.setText(String.valueOf(PA_Restant));
            }
        }
}