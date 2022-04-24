import java.lang.reflect.Array;

class Cellule {
    /** L'état d'une cellule est donné par un booléen. */
    protected int etat;
    protected String typeZone;
    protected static String[] arrayZone = {"heliport", "fire", "water", "air", "dirt"};

    public Cellule() {
        /* On conserve un pointeur vers la classe principale du modèle. */
        this.etat = 0;
        this.typeZone = "";
    }

    /** Un test à l'usage des autres classes (sera utilisé par la vue). */
    public int estVivante() {
        return etat;
    }

    public static String[] arrayTypeZone() {
        return arrayZone;
    }

}