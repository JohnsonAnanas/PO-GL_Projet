import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class VueGrille extends JPanel implements Observer {
    static JLabel okok;
    final CModele modele;
    private final static int TAILLE = 50;
    File path = null;
    BufferedImage imgHeli = ImageIO.read(new File(path, "img/heli.png"));
    BufferedImage imgFire = ImageIO.read(new File(path, "img/fire.png"));
    BufferedImage imgWater = ImageIO.read(new File(path, "img/water.png"));
    BufferedImage imgAir = ImageIO.read(new File(path, "img/air.png"));
    BufferedImage imgDirt = ImageIO.read(new File(path, "img/dirt.png"));
    public VueGrille(CModele modele) throws IOException {
        this.modele = modele;
        modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE*CModele.LARGEUR, TAILLE*CModele.HAUTEUR);
        this.setPreferredSize(dim);

        Controller crt = new Controller(modele);
        this.addMouseListener(crt);

    }


    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        for(int i=1; i<=CModele.LARGEUR; i++) {
            for(int j=1; j<=CModele.HAUTEUR; j++) {
                try {
                    paint(g, modele.getCellule(i, j), (i-1)*TAILLE, (j-1)*TAILLE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        repaint();
        int [] sdf = CModele.getPosTableau(CModele.joueurActu.getPosition());
        g.setColor( new Color(0, 0, 0, 100));
        g.fillRect((sdf[0]*TAILLE)-TAILLE*2, (sdf[1]*TAILLE)-TAILLE, TAILLE*3, TAILLE);
        g.fillRect((sdf[0]*TAILLE)-TAILLE, (sdf[1]*TAILLE)-TAILLE*2, TAILLE, TAILLE*3);
    }
    private void paint(Graphics g, Cellule c, int x, int y) throws IOException {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        if (c.etat == 0) {
            g.setColor(new Color(0xffffff));
        } else if (c.etat == 1) {
            g.setColor(new Color(0xb9d5fd));
        } else if (c.etat <= 1 + CModele.nombreDeJoueur) {
            g.setColor(new Color(0x274684));
        } else
            g.setColor(new Color(0x071739));

        g.fillRect(x, y, TAILLE, TAILLE);
        if (c.typeZone == "heliport") {
            g.drawImage(imgHeli, x, y, null);
        }
        if (c.typeZone == "fire") {
            g.drawImage(imgFire, x, y, null);
        }
        if (c.typeZone == "water") {
            g.drawImage(imgWater, x, y, null);
        }
        if (c.typeZone == "air") {
            g.drawImage(imgAir, x, y, null);
        }
        if (c.typeZone == "dirt") {
            g.drawImage(imgDirt, x, y, null);
        }
        int res = 0;
        for (Joueur i : modele.getEnsembleJoueur()) {

            if (i.getPosition() == c) {

                if (i.getNumber() == 0) {
                    g.setColor(i.getCouleur());
                    g.fillRect(x, y, (TAILLE / 2) - 1, (TAILLE / 2) - 1);
                    res += 1;
                } else if (i.getNumber() == 1) {
                    g.setColor(i.getCouleur());
                    g.fillRect(x + TAILLE / 2, y + TAILLE / 2, (TAILLE / 2) - 3, (TAILLE / 2) - 3);
                    res += 1;
                } else if (i.getNumber() == 2) {
                    g.setColor(i.getCouleur());
                    g.fillRect(x, y + TAILLE / 2, (TAILLE / 2) - 3, (TAILLE / 2) - 3);
                    res += 1;
                } else if (i.getNumber() == 3) {
                    g.setColor(i.getCouleur());
                    g.fillRect(x + TAILLE / 2, y, (TAILLE / 2) - 3, (TAILLE / 2) - 3);
                }

                if (i.getNumber() == CModele.joueurActu.getNumber()) {
                    g.setColor(new Color(0, 0, 0, 100));


                }

            }
        }

    }
}