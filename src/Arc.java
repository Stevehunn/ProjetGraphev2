/**
 * classe Arc
 * representant un arc du graphe
 */
public class Arc {

    private Noeud source;
    private Noeud cible;

    /**
     * constructeur
     * @param x la source
     * @param y la cible
     */

    public Arc(Noeud x,Noeud y) {
        this.source=x;
        this.cible=y;
    }

    /**
     * methode permettant d'afficher l'arc
     * @return
     */
    public String toString() {
        return "Source : "+source.getId()+"\nCible : "+cible.getId();
    }

    /** getters et setters **/
    public Noeud getSource() {
        return this.source;
    }

    public Noeud getCible() {
        return this.cible;
    }



}
