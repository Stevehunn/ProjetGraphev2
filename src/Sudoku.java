import java.util.ArrayList;
import java.util.HashMap;

/**
 * classe Sudoku
 * permettant de creer un graphe
 * representant une grille de sudoku 9*9
 */

public class Sudoku extends Graphe {

    final static int N = 9;
    // les blocs et la liste des noeuds par blocs
    HashMap<Integer, ArrayList<Integer>> blocs = new HashMap<>();

    public Sudoku() {
        super(N * N);

        /**
         * on genere tous les voisins suivant
         * la ligne
         */
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        this.addArc(N * k + i, N * k + j);
                    }
                }
            }
        }
        /**
         * on genere tous les voisins suivant
         * la colonne
         */
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        this.addArc(N * i + k, N * j + k);
                    }
                }
            }
        }

        /**
         * on met tous les noeuds d'un meme bloc
         * dans une liste
         */
        int debut = 0;
        for (int b = 0; b < N; b++) {
            ArrayList<Integer> listNoeud = new ArrayList<>();
            if (b == 3) {
                debut = 27;
            }
            if (b == 6) {
                debut = 54;
            }
            for (int k = 0; k < 3; k++) {
                for (int i = debut; i < debut + 3; i++) {

                    if (!listNoeud.contains(N * k + i))
                        listNoeud.add(N * k + i);

                }

            }
            debut = debut + 3;
            blocs.put(b, listNoeud);
        }

        /**
         * on genere tous les voisins
         * en cosiderant le bloc
         */
        for (ArrayList<Integer> list : blocs.values()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (i != j) {
                        if (!getNoeud(list.get(i)).hasSuccesseur(list.get(j))) {
                            addArc(list.get(i), list.get(j));
                        }
                    }
                }
            }
        }
    }

}

