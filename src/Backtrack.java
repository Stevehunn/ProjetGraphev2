import java.util.Arrays;
import java.util.Stack;

public class Backtrack extends Graphe {

    public Graphe graphe;

    public Backtrack(int k) {
        super(k);
        reinitNoeuds();
    }

    public int solve() {
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

    public int solve2() {
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


    private void rollback(Noeud n, int[] couleurs, Stack<Noeud> stack) {
        Noeud popped = stack.pop();
        while (popped.equals(n)) {
            popped.setMark(false);
            couleurs[popped.getId()] = 0;
        }
    }

}
