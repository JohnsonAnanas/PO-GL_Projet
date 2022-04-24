import java.awt.*;

class Joueur{
    String[][] inventaire;
    private Cellule position;
    private final int number;
    private final Color couleur;

    public Joueur(Cellule c, int number, Color couleur){
        this.position =  c;
        this.number = number;
        this.couleur = couleur;
        this.inventaire = new String[4][3];
    }

    public Cellule getPosition(){
        return position;
    }
    public void setPosition(Cellule c){
        this.position = c;
    }

    public int getNumber() {
        return number;
    }

    public Color getCouleur() {
        return couleur;
    }
    public void setInventaire(){
        this.inventaire = new String[][]{
                {"fire" , "0", "0"},
                {"water", "0", "0"},
                {"air"  , "0", "0"},
                {"dirt" , "0", "0"},
                {"sandbag", "0", ""},
                {"helicopter", "0", ""}
        };
    }
    public String[][] getInventaire(){
        return this.inventaire;
    }
    public void addInventaire(int x, int y){
        this.inventaire[x][y] = "1";
    }

}