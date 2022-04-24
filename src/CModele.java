import java.awt.*;
import java.util.Objects;
import java.util.Random;

/**
 * Le modèle : le cœur de l'application.
 *
 * Le modèle étend la classe [Observable] : il va posséder un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les prévenir avec [notifyObservers] lors des modifications.
 * Voir la méthode [avance()] pour cela.
 */
class CModele extends Observable {

    public static final int HAUTEUR=6, LARGEUR=6;

    static Cellule[][] cellules = new Cellule[0][0];
    private Joueur[] ensembleJoueur;
    public static Joueur joueurActu;
    public static int nombreDeJoueur;

    public CModele() {

        cellules = new Cellule[LARGEUR+2][HAUTEUR+2];

        for(int i=0; i<LARGEUR+2; i++) {
            for(int j=0; j<HAUTEUR+2; j++) {
                System.out.println("création cellule x y : " + i + " " + j);
                cellules[i][j] = new Cellule();
            }
        }

        init();


    }
    public void init() {

        int nombre_joueur = 2;

        nombreDeJoueur = nombre_joueur;
        ensembleJoueur = new Joueur[nombreDeJoueur];
        Random rand = new Random();
        for (int i = 0; i < nombre_joueur ; i++){
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            ensembleJoueur[i] = new Joueur(cellules[(int) (Math.random()*(5-1)) + 1][(int) (Math.random()*(5-1)) + 1], i, randomColor);
            ensembleJoueur[i].setInventaire();
        }


        joueurActu = ensembleJoueur[0];
        String[] enumTypeZone = Cellule.arrayTypeZone();
        for (int i = 0; i <=4; i++ ) {
            int x = (int) ((Math.random()*(6-1)) + 1);
            int y = (int) ((Math.random()*(6-1)) + 1);
            if (cellules[x][y].typeZone == "") {
                cellules[x][y].typeZone = enumTypeZone[i];
                System.out.println(cellules[x][y].typeZone + x + y);
            } else {
                System.out.println("PAS " + cellules[x][y].typeZone + x + y);

                i--;
            }
        }
    }

    public void avance() {
        int res = 0;
        for (int i = 1; i <=6; i++){
            for (int j = 1; j <=6; j++){
                if(cellules[i][j].etat>=2) {
                    cellules[i][j].etat++;
                    if (cellules[i][j].etat >= 2+nombreDeJoueur && cellules[i][j].typeZone == "heliport"){
                        System.out.println("L'HELIPORT C'EST NOYE");
                        finDePartie(false);
                    }
                }
            }
        }
        while(res < 3) {
            int i =  (int) ((Math.random() * (6 )) + 1);
            int j =  (int) ((Math.random() * (6 )) + 1);
            if(cellules[i][j].etat<2){
                cellules[i][j].etat++;

                res++;
            }
        }
        if (Math.random() < 0.8) {
            int y = (int) ( Math.random() * 6 + 0);
            while (joueurActu.inventaire[y][1] == "1")
                y = (int) (Math.random() * 6 + 0);

            joueurActu.addInventaire(y, 1);
            if (y == 0)
                System.out.println("joueur n"+joueurActu.getNumber()+", fire +1 cle");
            else if (y == 1)
               System.out.println("joueur n"+joueurActu.getNumber()+", water +1 cle");
            else if (y == 2)
                System.out.println("joueur n"+joueurActu.getNumber()+", air +1 cle");
            else if (y == 3)
                System.out.println("joueur n"+joueurActu.getNumber()+", dirt +1 cle");
            else if (y == 4)
                System.out.println("joueur n"+joueurActu.getNumber()+", sandbag +1 cle");
            else if (y == 5)
                System.out.println("joueur n"+joueurActu.getNumber()+", helicopter +1 cle");
        } else
            System.out.println("T'AS RIEN EU VAS GRATTER AILLEURS, CLOCHARD");

        if(joueurActu.getNumber()+1 > nombreDeJoueur - 1)
            joueurActu = ensembleJoueur[0];
        else
            joueurActu = ensembleJoueur[joueurActu.getNumber()+ 1];
        for (int i = 0; i <nombreDeJoueur; i++){
            Cellule ikd = ensembleJoueur[i].getPosition();
            if (ikd.etat >= 2+nombreDeJoueur){

                System.out.println("LE JOUEUR NUMERO " + joueurActu.getNumber() + " C'EST NOYE!!");
                finDePartie(false);            }
        }

        VueCommandes.inventaireJAF1.setText(CModele.joueurActu.inventaire[0][0]);
        VueCommandes.inventaireJAF2.setText(CModele.joueurActu.inventaire[0][1]);
        VueCommandes.inventaireJAF3.setText(CModele.joueurActu.inventaire[0][2]);
        VueCommandes.inventaireJAW1.setText(CModele.joueurActu.inventaire[1][0]);
        VueCommandes.inventaireJAW2.setText(CModele.joueurActu.inventaire[1][1]);
        VueCommandes.inventaireJAW3.setText(CModele.joueurActu.inventaire[1][2]);
        VueCommandes.inventaireJAA1.setText(CModele.joueurActu.inventaire[2][0]);
        VueCommandes.inventaireJAA2.setText(CModele.joueurActu.inventaire[2][1]);
        VueCommandes.inventaireJAA3.setText(CModele.joueurActu.inventaire[2][2]);
        VueCommandes.inventaireJAD1.setText(CModele.joueurActu.inventaire[3][0]);
        VueCommandes.inventaireJAD2.setText(CModele.joueurActu.inventaire[3][1]);
        VueCommandes.inventaireJAD3.setText(CModele.joueurActu.inventaire[3][2]);
        VueCommandes.inventaireJAS1.setText(CModele.joueurActu.inventaire[4][0]);
        VueCommandes.inventaireJAS2.setText(CModele.joueurActu.inventaire[4][1]);
        VueCommandes.inventaireJAH1.setText(CModele.joueurActu.inventaire[5][0]);
        VueCommandes.inventaireJAH2.setText(CModele.joueurActu.inventaire[5][1]);

        notifyObservers();
    }

    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }
    public static int[] getPosTableau(Cellule c){
        int[]tab = new int[2];
        for(int i=0; i<LARGEUR+2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                if (c ==cellules[i][j]){
                    tab[0] = i;
                    tab[1] = j;
                }

            }
        }
        return tab;
    }
    private boolean valideDonnee(int xTab, int yTab) {
        if(xTab<=0 || xTab>=HAUTEUR+1) return false;
        return !(yTab<=0 || yTab>=LARGEUR+1);
    }
    public void mouvement(String axe, int mv) {
        int[] cord = getPosTableau(joueurActu.getPosition());
        if (Objects.equals(axe, "x")){
            if (valideDonnee(cord[0]+ mv,cord[1])){
                joueurActu.setPosition(cellules[cord[0]+ mv][cord[1]]);
            }
        }
        if (Objects.equals(axe, "y")){
            if (valideDonnee(cord[0],cord[1]+ mv)){
                joueurActu.setPosition(cellules[cord[0]][cord[1]+ mv]);
            }
        }
    }

    public void assèche(String axe, int mv) {
        int[] cord = getPosTableau(joueurActu.getPosition());

        if (Objects.equals(axe, "x")){
            if (valideDonnee(cord[0]+ mv,cord[1])){
                if (cellules[cord[0]+ mv][cord[1]].etat > 0)
                    cellules[cord[0]+ mv][cord[1]].etat--;
            }
        }
        if (Objects.equals(axe, "y")){
            if (valideDonnee(cord[0],cord[1] + mv)){
                if (cellules[cord[0]][cord[1] + mv].etat > 0)
                    cellules[cord[0]][cord[1] + mv].etat--;
            }
        }
        notifyObservers();
    }

    public void mouse(int x, int y, int button) {
        int[] cord = CModele.getPosTableau(joueurActu.getPosition());
        if (button == 1){
            if (cellules[x][y].etat <2+nombreDeJoueur) {
                if (cord[0] == x && cord[1] == y && cellules[x][y].typeZone != "") {
                    if (Objects.equals(joueurActu.inventaire[0][0], cellules[x][y].typeZone) && joueurActu.inventaire[0][1] == "1") {
                        joueurActu.inventaire[0][1] = "0";
                        for (int i = 0; i < nombreDeJoueur; i++)
                            ensembleJoueur[i].inventaire[0][2] = "1";
                        System.out.println("CLE AU BON ENDROIT FEU");
                    } else if (Objects.equals(joueurActu.inventaire[1][0], cellules[x][y].typeZone) && joueurActu.inventaire[1][1] == "1") {
                        joueurActu.inventaire[1][1] = "0";
                        for (int i = 0; i < nombreDeJoueur; i++)
                            ensembleJoueur[i].inventaire[1][2] = "1";
                        System.out.println("CLE AU BON ENDROIT EAU");
                    } else if (Objects.equals(joueurActu.inventaire[2][0], cellules[x][y].typeZone) && joueurActu.inventaire[2][1] == "1") {
                        joueurActu.inventaire[2][1] = "0";
                        for (int i = 0; i < nombreDeJoueur; i++)
                            ensembleJoueur[i].inventaire[2][2] = "1";
                        System.out.println("CLE AU BON ENDROIT AIR");
                    } else if (Objects.equals(joueurActu.inventaire[3][0], cellules[x][y].typeZone) && joueurActu.inventaire[3][1] == "1") {
                        joueurActu.inventaire[3][1] = "0";
                        for (int i = 0; i < nombreDeJoueur; i++)
                            ensembleJoueur[i].inventaire[3][2] = "1";
                        System.out.println("CLE AU BON ENDROIT TERRE");
                    } else if (Objects.equals("heliport", cellules[x][y].typeZone) ) {
                        int nombreArtefctTotal = 0;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < nombreDeJoueur; j++) {
                                if (ensembleJoueur[j].inventaire[i][2] == "1") {
                                    nombreArtefctTotal++;
                                    System.out.println("on a l'artefacte de: " + ensembleJoueur[j].inventaire[i][0]);
                                }
                                else
                                    System.out.println("on pas a l'artefacte de: " + ensembleJoueur[j].inventaire[i][0]);

                            }
                        }
                        if (nombreArtefctTotal == 4*nombreDeJoueur) {
                            finDePartie(true);
                            System.out.println("WAAAAAh UN HOULOUCOUPOUTERE");
                        }
                    } else {
                        System.out.println("TAS PAS LA CLE SALLE FRAUDE");
                        System.out.println(joueurActu.inventaire[0][1]);
                        System.out.println(cellules[x][y].typeZone);
                    }


                } else if (cord[0] == x && cord[1] == y)
                    System.out.println("sur moi");
                else
                    joueurActu.setPosition(cellules[x][y]);
            }
            else {
                Controller.PA_Restant++;
                System.out.println("CASE SUBMERGE IMPOSSIBLE DE S'Y DEPLACER");
            }
        }
        else if (button == 3) {
            if (cellules[x][y].etat == 1)
                cellules[x][y].etat = 0;
            else if (cellules[x][y].etat > 0 && cellules[x][y].etat <2+nombreDeJoueur)
                cellules[x][y].etat = 1;
            else
                System.out.println("IMPOSSIBLE D'ASSECHER");
        }
    }

    private void finDePartie (boolean result) {
        if (result == true){
            Controller.partieEnCours = false;
            VueCommandes.ecranFin.setText("GAGNER");
            System.out.println("TAS GAGNE");
        }
        else{
            Controller.partieEnCours = false;
            VueCommandes.ecranFin.setText("PERDU");
            System.out.println("TAS PERDU");
        }
    }

    public Joueur[] getEnsembleJoueur() {
        return ensembleJoueur;
    }


}