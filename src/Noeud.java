import java.util.LinkedList;

/**
 * classe Noeud
 * representant un noeud du graphe
 */
public class Noeud {
    //liste des successeurs du graphe
    LinkedList<Arc> succ = new LinkedList<Arc>();
    boolean mark;
    private int id;

    /**
     * constructeur pour creer le noeud
     *
     * @param id l'identifiant du noeud
     */
    public Noeud(int id) {
        this.id = id;

    }

    /**
     * methode permettant de determiner
     * si un noeud est sucesseur d'un autre
     *
     * @param j l'identifiant du second noeud
     * @return true si le noeud d'identifiant j
     * est successeur de this
     */

    public boolean hasSuccesseur(int j) {
        LinkedList<Integer> cibles = new LinkedList<Integer>();
        boolean trouve = false;

        for (Arc c : succ) {
            cibles.add(c.getCible().getId());
        }
        for (int i : cibles) {
            if (i == j) {
                trouve = true;
            }

        }
        return trouve;


    }

    /**
     * methode permettant d'afficher le noeud
     *
     * @return la chaine de caractere
     * representant le noeud
     */

    public String toString() {
        String successeurs = "";
        for (Arc n : this.succ) {

            successeurs = successeurs + n.getCible().getId() + " ";
        }
        if (!this.succ.isEmpty()) {
            return id + " ---> " + successeurs;
        } else {
            return id + "";
        }
    }

    /**
     * getters et setters
     **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Arc> getSuccesseurs() {
        return this.succ;
    }

    public void setSuccesseurs(LinkedList<Arc> succ) {
        this.succ = succ;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean hasUnmarkedSuccessor() {
        for (Arc a : getSuccesseurs()) {
            if (!a.getCible().isMark()) {
                return true;
            }
        }
        return false;
    }
}
