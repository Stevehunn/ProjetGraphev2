import java.io.*;
import java.util.*;

/**
 * classe Graphe
 * elle contient toutes les methodes de traitement
 * du graphe
 */
public class Graphe {

    //variable contenant les noeuds
    LinkedList<Noeud> noeuds;
    HashMap<Integer, Noeud> hmap;
    //variable contenant les couleurs des noeuds
    ArrayList<Integer> couleursDispo;
    LinkedList<String> ordreParcours = new LinkedList<>();

    /**
     * Constructeur pour creer un graphe
     * connaissant sa taille
     *
     * @param k la taille du graphe à creer
     */
    public Graphe(int k) {
        noeuds = new LinkedList<Noeud>();
        hmap = new HashMap<Integer, Noeud>();
        couleursDispo = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Noeud noeud = new Noeud(i);
            if (!noeuds.contains(noeud) && !hmap.containsKey(i))
                this.noeuds.add(noeud);
            this.hmap.put(i, noeud);
            couleursDispo.add(i);
        }

    }

    /**
     * Constructeur pour generer un graphe
     * à partir d'un fichier
     *
     * @param file le fichier csv contenant les
     *             arcs du graphe
     */
    public Graphe(String file) {
        noeuds = new LinkedList<Noeud>();
        hmap = new HashMap<Integer, Noeud>();
        couleursDispo = new ArrayList<>();
        try {
            File inputfile = new File(file);
            FileReader in = new FileReader(inputfile);
            BufferedReader br = new BufferedReader(in);
            String line = br.readLine();

            while (line != null) {
                if (!line.equalsIgnoreCase("Source,Target")) {
                    String[] data = line.split(",");
                    int x = Integer.parseInt(data[0]);
                    int y = Integer.parseInt(data[1]);
                    this.addNoeud(x);
                    this.addNoeud(y);
                    this.addArc(x, y);
                }

                line = br.readLine();

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < hmap.size(); i++) {
            couleursDispo.add(i + 1);
        }


    }

    /**
     * methode permettant d'ajouter un
     * noeud au graphe
     *
     * @param n l'identifiant du noeud
     */
    public void addNoeud(int n) {
        boolean trouve = false;

        for (Noeud i : noeuds) {
            if (i.getId() == n) {
                trouve = true;
            }
        }

        if (!trouve) {
            this.noeuds.add(new Noeud(n));
            this.hmap.put(hmap.size(), new Noeud(n));
        }

    }

    /**
     * methode permettant de recuperer
     * un noeud du graphe
     *
     * @param n l'identifiant du noeud à
     *          recuperer
     * @return le noeud correspondant
     */
    public Noeud getNoeud(int n) {

        Noeud noeud = null;
        for (int i : hmap.keySet()) {
            if (hmap.get(i).getId() == n) {
                noeud = hmap.get(i);
            }
        }

        return noeud;
    }

    /**
     * methode permettant d'ajouter un arc
     * entre deux noeuds
     *
     * @param x l'indentifiant du premier noeud
     * @param y l'indentifiant du premier noeud
     */
    public void addArc(int x, int y) {
        Noeud noeudx = this.getNoeud(x);
        Noeud noeudy = this.getNoeud(y);
        if (noeudx != null && noeudy != null) {
            if (!noeudx.hasSuccesseur(y)) {
                Arc a = new Arc(noeudx, noeudy);
                noeudx.getSuccesseurs().add(a);

            }
        }
    }

    /**
     * Methode permettant d'afficher le
     * graphe en console
     *
     * @return une chaine de caractere
     * contanant les noeuds du graphe
     * et leurs successeurs
     */
    public String toString() {
        String graphe = " ";
        for (Noeud n : hmap.values()) {

            graphe += "\n" + n.toString();
        }

        return graphe;
    }

    /**
     * Methode permettant d'exporter un graphe
     * sous forme de fichier csv
     */
    public void export() {
        String buff = "Source,Target\n";
        String sep = ",";
        for (Noeud n : this.noeuds) {
            for (Arc a : n.getSuccesseurs()) {
                buff += a.getSource().getId() + sep +
                        a.getCible().getId() + "\n";
            }
        }
        File outputFile = new File(this.getClass() + ".csv");
        FileWriter out;
        try {
            out = new FileWriter(outputFile);
            out.write(buff);
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * methode permettant de generer
     * la matrice d'adjacence du graphe
     *
     * @return un tableau à deux dimensions
     * representant la matrice
     */
    public boolean[][] matriceAdj() {
        boolean[][] adj = new boolean[hmap.size()][hmap.size()];
        for (Noeud noeud : hmap.values()) {
            for (Arc a : noeud.getSuccesseurs()) {
                adj[a.getSource().getId()][a.getCible().getId()] = true;
            }
            for (Arc a : noeud.getSuccesseurs()) {
                adj[a.getCible().getId()][a.getSource().getId()] = true;
            }
        }
        return adj;
    }


    /**
     * Methode permettant de trier les noeuds
     * d'un graphe par ordre decroissant
     *
     * @return
     */
    public LinkedList<Noeud> listNoeudDegreDeCroissant() {
        LinkedList<Noeud> valeurs = new LinkedList<>();
        LinkedList<Noeud> mesNoeuds = new LinkedList<>();
        for (Noeud n : hmap.values()) {
            mesNoeuds.add(n);

        }
        while (valeurs.size() < hmap.size()) {
            Noeud max = mesNoeuds.getFirst();
            for (int i = 0; i < mesNoeuds.size(); i++) {

                if (mesNoeuds.get(i).getSuccesseurs().size() > max.getSuccesseurs().size()) {
                    max = mesNoeuds.get(i);

                }
            }
            mesNoeuds.remove(max);
            valeurs.add(max);
        }

        return valeurs;


    }

    /**
     * methode  permettant de recenser
     * la couleur des voisins d'un noeud
     *
     * @param n        le noeud initial
     * @param couleurs la liste des couleurs du graphe
     * @return la liste des couleurs des voisins de n
     */
    public LinkedList<Integer> couleursVoisins(Noeud n, int[] couleurs) {
        boolean[][] adj = matriceAdj();
        LinkedList<Integer> liste = new LinkedList<>();
        for (Noeud i : hmap.values()) {
            if (adj[i.getId()][n.getId()]) {
                if (!liste.contains(couleurs[i.getId()])) {
                    liste.add(couleurs[i.getId()]);
                }

            }
            if (adj[n.getId()][i.getId()]) {
                if (!liste.contains(couleurs[i.getId()])) {
                    liste.add(couleurs[i.getId()]);
                }

            }
        }
        return liste;
    }

    /**
     * Methode de coloration du graphe
     * avec l'algorithme du degre decroissant
     *
     * @return le nombre chromatique du graphe
     */
    public int degreDecroissant() {
        int[] couleurs = new int[hmap.size()];

        LinkedList<Noeud> valeurs = listNoeudDegreDeCroissant();
        boolean[][] adj = matriceAdj();
        for (Noeud x : valeurs) {
            int couleur = 1;
            for (Noeud y : valeurs) {
                if (adj[x.getId()][y.getId()] && couleurs[y.getId()] == couleur) {
                    couleur = couleur + 1;
                }
            }
            while (couleursVoisins(x, couleurs).contains(couleur)) {
                couleur = couleur + 1;
            }
            couleurs[x.getId()] = couleur;
        }

        for (int i = 0; i < couleurs.length; i++) {
            System.out.println(valeurs.get(i).getId() + " : " + couleurs[valeurs.get(i).getId()] + " ");
        }
        return max(couleurs);
    }


    /**
     * Methode de coloration du graphe
     * avec l'algorithme de l'indice croissant
     *
     * @return le nombre chromatique du graphe
     */
    public int indiceCroissant() {
        int[] couleurs = new int[hmap.size()];
        boolean[][] adj = matriceAdj();
        for (Noeud x : hmap.values()) {
            int couleur = 1;
            for (Noeud y : hmap.values()) {
                if (adj[x.getId()][y.getId()] && couleurs[y.getId()] == couleur) {
                    couleur = couleur + 1;
                }
            }
            while (couleursVoisins(x, couleurs).contains(couleur)) {
                couleur = couleur + 1;
            }
            couleurs[x.getId()] = couleur;
        }

        for (int i = 0; i < couleurs.length; i++) {
            System.out.println(hmap.get(i).getId() + " : " + couleurs[hmap.get(i).getId()] + " ");
            //System.out.println(couleursVoisins(hmap.get(i),couleurs).toString());
        }

        return max(couleurs);
    }

    /**
     * methode permettant de permuter
     * du noeuds aleatoires du graphe
     *
     * @return la nouvelle liste de noeuds
     */
    public LinkedList<Noeud> permuterNoeud() {
        int n1 = (int) (Math.random() * (hmap.size() - 1));
        int n2 = (int) (Math.random() * (hmap.size() - 1));
        LinkedList<Noeud> mesNoeuds = new LinkedList<>();
        for (Noeud n : hmap.values()) {
            mesNoeuds.add(n);

        }
        // System.out.println(mesNoeuds.toString());
        Noeud x = mesNoeuds.get(n1);
        Noeud y = mesNoeuds.get(n2);
        mesNoeuds.remove(x);
        mesNoeuds.add(n1, y);
        mesNoeuds.remove(y);
        mesNoeuds.add(n2, x);

        //  System.out.println(mesNoeuds.toString());

        return mesNoeuds;
    }

    /**
     * methode permettant de colorier
     * un graphe avec une liste de noeuds
     * dans un ordre quelconque
     *
     * @param valeurs la liste des noeuds
     * @return le nombre chromatique
     */
    public int solutionColoriage(LinkedList<Noeud> valeurs) {
        int[] couleurs = new int[hmap.size()];
        LinkedList<String> ordre = new LinkedList<>();
        boolean[][] adj = matriceAdj();
        for (Noeud x : valeurs) {
            int couleur = 1;
            for (Noeud y : valeurs) {
                if (adj[x.getId()][y.getId()] && couleurs[y.getId()] == couleur) {
                    couleur = couleur + 1;
                }
            }
            while (couleursVoisins(x, couleurs).contains(couleur)) {
                couleur = couleur + 1;
            }
            couleurs[x.getId()] = couleur;
        }
        for (int i = 0; i < couleurs.length; i++) {
            ordre.add(valeurs.get(i).getId() + " : " + couleurs[valeurs.get(i).getId()]);
            // System.out.println(hmap.get(i).getId()+" : " +couleurs[hmap.get(i).getId()]+" ");
            //System.out.println(couleursVoisins(hmap.get(i),couleurs).toString());
        }
        ordreParcours = ordre;
        return max(couleurs);

    }

    /**
     * methode de coloration du graphe
     * par l'algorithme du recuit simulé
     *
     * @param temp  la temperature
     * @param alpha le facteur de refroidissement
     * @return le nombre chromatique
     */

    public int recuitSimule(double temp, double alpha) {
        int nci = solutionColoriage(listNoeudDegreDeCroissant());
        int ncv = 0;
        LinkedList<String> ordre = ordreParcours;
        ;
        //ArrayList<Integer> solutions=new ArrayList<>();
        //solutions.add(nci);
        int nbmax = 5;
        while (temp > 0) {
            int i = 0;
            while (i < nbmax) {
                ncv = solutionColoriage(permuterNoeud());
                if (nci > ncv) {
                    nci = ncv;
                    ordre = ordreParcours;
                }
                i++;

            }
            temp = alpha * temp;
        }
        System.out.println(ordre.toString());
        return nci;
    }

    /**
     * methode recursive permettant de
     * colorier le graphe  avec l'algorithme
     * du backtrack
     *
     * @param g            le graphe à colorier
     * @param noeudCourant le noeud courant
     * @param couleurs     le tableau de couleurs du graphe
     * @param nbNoeud      le nombre de noeuds du graphe
     * @return true si le graphe est colorié
     */
    private boolean backTrackRec(Graphe g, int noeudCourant, int couleurs[], int nbNoeud) {
        int nombreSommets = g.hmap.size();
        if (noeudCourant == nombreSommets) {
            return true;
        }

        for (int c = 1; c <= nbNoeud; c++) {
            if (!g.couleursVoisins(getNoeud(noeudCourant), couleurs).contains(c)) {
                couleurs[hmap.get(noeudCourant).getId()] = c;
                if (backTrackRec(g, noeudCourant + 1, couleurs, nbNoeud)) {
                    return true;
                }
                couleurs[hmap.get(noeudCourant).getId()] = 0;

            }
        }

        return false;
    }

    /**
     * methode exacte de backtrack
     * de coloration du graphe
     *
     * @param nbNoeud le nombre de noeud
     * @return le nombre chromatique
     */
    public int backTrack(int nbNoeud) {
        int[] couleurs = new int[hmap.size()];
        for (int i = 0; i < hmap.size(); i++) {
            couleurs[hmap.get(i).getId()] = 0;
        }
        if (!backTrackRec(this, 0, couleurs, nbNoeud)) {
            System.out.println("Pas de solution");
            return 0;
        }
        for (int i = 0; i < couleurs.length; i++) {
            System.out.println(hmap.get(i).getId() + " : " + couleurs[hmap.get(i).getId()] + " ");
        }
        return max(couleurs);
    }

    public int smallestLast() {
        int[] couleurs = new int[hmap.size()];

        LinkedList<Noeud> valeurs = listNoeudDegreCroissant();
        boolean[][] adj = matriceAdj();
        for (Noeud x : valeurs) {
            int couleur = 1;
            for (Noeud y : valeurs) {
                if (adj[x.getId()][y.getId()] && couleurs[y.getId()] == couleur) {
                    couleur = couleur + 1;
                }

            }
            while (couleursVoisins(x, couleurs).contains(couleur)) {
                couleur = couleur + 1;
            }
            couleurs[x.getId()] = couleur;
        }

        for (int i = 0; i < couleurs.length; i++) {
            System.out.println(valeurs.get(i).getId() + " : " + couleurs[valeurs.get(i).getId()] + " ");

        }


        return max(couleurs);

    }


    public LinkedList<Noeud> listNoeudDegreCroissant() {
        LinkedList<Noeud> valeurs = new LinkedList<>();

        LinkedList<Noeud> mesNoeuds = new LinkedList<>();
        for (Noeud n : hmap.values()) {
            mesNoeuds.add(n);

        }
        while (valeurs.size() < hmap.size()) {
            Noeud max = mesNoeuds.getFirst();
            for (int i = 0; i < mesNoeuds.size(); i++) {

                // la meme idee comme la methode degre decroissant, sauf que ici on veut la degre plus petit
                if (mesNoeuds.get(i).getSuccesseurs().size() < max.getSuccesseurs().size()) {
                    max = mesNoeuds.get(i);

                }
            }
            mesNoeuds.remove(max);
            valeurs.add(max);
        }

        return valeurs;

    }

    /**
     * methode permettant le calculer
     * le maximum d'un tableau d'entiers
     *
     * @param a le tableau d'entier
     * @return le nombre maximum
     */
    public int max(int[] a) {
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > m)
                m = a[i];
        }
        return m;
    }


    // reinitialise les noeuds
    public void reinitNoeuds() {
        for (Noeud n : this.getNoeuds()) {
            n.setMark(false);
        }
    }

    //getter pour recuper la liste des noeuds
    public LinkedList<Noeud> getNoeuds() {
        LinkedList<Noeud> noeudsT = new LinkedList<>();
        for (Noeud n : hmap.values()) {
            noeudsT.add(n);
        }
        return noeuds;
    }

    /**
     * methode permettant d'utiliser
     * la méthode backtrack v1
     *
     * @return la solution trouvé ou annonce qu'il n'y a pas de solution
     */
    public int backtrack() {
        int[] couleurs = new int[noeuds.size()];
        Stack<Noeud> stack = new Stack<>();
        if (backtrackColor(getNoeud(0), couleurs, stack)) {
            System.out.println(Arrays.toString(couleurs));
            System.out.println("Une solution existe.");
            return 1;
        } else {
            System.out.println("Pas de solution.");
            return 0;
        }
    }

    /**
     * methode permettant d'utiliser
     * la méthode backtrack v2
     *
     * @return la solution trouvé ou annonce qu'il n'y a pas de solution
     */
    public int backtrack2() {
        int[] couleurs = new int[noeuds.size()];
        if (backtrackColorv2(getNoeud(0), couleurs)) {
            System.out.println(Arrays.toString(couleurs));
            System.out.println("Une solution existe.");
            return 1;
        } else {
            System.out.println("Pas de solution.");
            return 0;
        }
    }

    /**
     * methode permettant de parcourir le graphe
     *
     * @param n
     * @param couleurs
     * @param stack
     * @return
     */
    private boolean backtrackColor(Noeud n, int[] couleurs, Stack<Noeud> stack) {
        for (int c = 1; c < noeuds.size(); c++) {
            if (!couleursVoisins(n, couleurs).contains(c)) {
                boolean needRollback = false;
                couleurs[n.getId()] = c;
                n.setMark(true);
                stack.add(n);
                if (!n.hasUnmarkedSuccessor()) {
                    return true;
                }
                for (Arc a : n.getSuccesseurs()) {
                    if (!a.getCible().isMark()) {
                        if (!backtrackColor(a.getCible(), couleurs, stack)) {
                            needRollback = true;
                            break;
                        }
                    }
                }
                if (needRollback) {
                    rollback(n, couleurs, stack);
                } else {
                    return true;
                }
            }
        }
        couleurs[n.getId()] = -1;
        n.setMark(false);
        return false;
    }

    /**
     * methode permettant de parcourir le graphe
     *
     * @param n
     * @param couleurs
     * @return
     */
    private boolean backtrackColorv2(Noeud n, int[] couleurs) {
        if (!n.hasUnmarkedSuccessor()) {
            return auxBacktrackColorv2(n, couleurs);
        } else {
            n.setMark(true);
            for (Arc a : n.getSuccesseurs()) {
                if (!a.getCible().isMark()) {
                    if (!backtrackColorv2(a.getCible(), couleurs)) {
                        return false;
                    }
                }
            }
            return auxBacktrackColorv2(n, couleurs);
        }
    }

    private boolean auxBacktrackColorv2(Noeud n, int[] couleurs) {
        for (int c = 1; c < noeuds.size(); c++) {
            if (!couleursVoisins(n, couleurs).contains(c)) {
                couleurs[n.getId()] = c;
                n.setMark(true);
                return true;
            }
        }
        return false;
    }

    /**
     * methode permettant de revenir en arrière
     * pour la methode backtrack v1
     *
     * @param n
     * @param couleurs
     * @param stack
     */
    private void rollback(Noeud n, int[] couleurs, Stack<Noeud> stack) {
        Noeud popped = stack.pop();
        while (popped.equals(n)) {
            popped.setMark(false);
            couleurs[popped.getId()] = 0;
        }
    }
}
