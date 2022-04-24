import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class VueCommandes extends JPanel {
    public static JLabel ecranFin;
    static JLabel Label_PA;
    static JLabel inventaireJAF1;
    static JLabel inventaireJAF2;
    static JLabel inventaireJAF3;
    static JLabel inventaireJAW1;
    static JLabel inventaireJAW2;
    static JLabel inventaireJAW3;
    static JLabel inventaireJAA1;
    static JLabel inventaireJAA2;
    static JLabel inventaireJAA3;
    static JLabel inventaireJAD1;
    static JLabel inventaireJAD2;
    static JLabel inventaireJAD3;
    static JLabel inventaireJAS1;
    static JLabel inventaireJAS2;
    static JLabel inventaireJAS3;
    static JLabel inventaireJAH1;
    static JLabel inventaireJAH2;
    static JLabel inventaireJAH3;
    public VueCommandes(CModele modele) {

        Label_PA = new JLabel();

        Label_PA.setOpaque(true);
        Label_PA.setBackground(CModele.joueurActu.getCouleur());
        Label_PA.setForeground(Color.WHITE);
        Label_PA.setText(String.valueOf(Controller.PA_Restant));
        Label_PA.setFont(new Font("Arial", Font.BOLD, 25));
        this.add(Label_PA);


        inventaireJAF1 = new JLabel();
        inventaireJAF1.setText(CModele.joueurActu.inventaire[0][0]);
        inventaireJAF2 = new JLabel();
        inventaireJAF2.setText(CModele.joueurActu.inventaire[0][1]);
        inventaireJAF3 = new JLabel();
        inventaireJAF3.setText(CModele.joueurActu.inventaire[0][2]);

        inventaireJAW1 = new JLabel();
        inventaireJAW1.setText(CModele.joueurActu.inventaire[1][0]);
        inventaireJAW2 = new JLabel();
        inventaireJAW2.setText(CModele.joueurActu.inventaire[1][1]);
        inventaireJAW3 = new JLabel();
        inventaireJAW3.setText(CModele.joueurActu.inventaire[1][2]);

        inventaireJAA1 = new JLabel();
        inventaireJAA1.setText(CModele.joueurActu.inventaire[2][0]);
        inventaireJAA2 = new JLabel();
        inventaireJAA2.setText(CModele.joueurActu.inventaire[2][1]);
        inventaireJAA3 = new JLabel();
        inventaireJAA3.setText(CModele.joueurActu.inventaire[2][2]);

        inventaireJAD1 = new JLabel();
        inventaireJAD1.setText(CModele.joueurActu.inventaire[3][0]);
        inventaireJAD2 = new JLabel();
        inventaireJAD2.setText(CModele.joueurActu.inventaire[3][1]);
        inventaireJAD3 = new JLabel();
        inventaireJAD3.setText(CModele.joueurActu.inventaire[3][2]);

        inventaireJAS1 = new JLabel();
        inventaireJAS1.setText(CModele.joueurActu.inventaire[4][0]);
        inventaireJAS2 = new JLabel();
        inventaireJAS2.setText(CModele.joueurActu.inventaire[4][1]);
        inventaireJAS3 = new JLabel();
        inventaireJAS3.setText(CModele.joueurActu.inventaire[4][2]);
        inventaireJAH1 = new JLabel();
        inventaireJAH1.setText(CModele.joueurActu.inventaire[5][0]);
        inventaireJAH2 = new JLabel();
        inventaireJAH2.setText(CModele.joueurActu.inventaire[5][1]);
        inventaireJAH3 = new JLabel();
        inventaireJAH3.setText(CModele.joueurActu.inventaire[5][2]);

        JPanel okok = new JPanel();
        okok.add(inventaireJAF1);
        okok.add(inventaireJAW1);
        okok.add(inventaireJAA1);
        okok.add(inventaireJAD1);
        okok.add(inventaireJAS1);
        okok.add(inventaireJAH1);
        okok.add(inventaireJAF2);
        okok.add(inventaireJAW2);
        okok.add(inventaireJAA2);
        okok.add(inventaireJAD2);
        okok.add(inventaireJAS2);
        okok.add(inventaireJAH2);
        okok.add(inventaireJAF3);
        okok.add(inventaireJAW3);
        okok.add(inventaireJAA3);
        okok.add(inventaireJAD3);
        okok.add(inventaireJAS3);
        okok.add(inventaireJAH3);
        System.out.println("okok");

        this.add(okok);
        okok.setLayout(new GridLayout(3,6));

        JButton fin = new JButton("fin de tour");
        this.add(fin);

        ecranFin = new JLabel();
        ecranFin.setText(" ");
        ecranFin.setFont(new Font("Arial", Font.BOLD, 25));
        this.add(ecranFin);

        Controller crt = new Controller(modele);
        fin.addActionListener(crt);


    }
}