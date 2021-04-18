import java.util.Random;

/**
 * methode de generation de
 * graphe aleatoire
 */

public class RandomGraphe extends Graphe {

    public RandomGraphe(int n, double p, boolean oriente) {
        super(n);
        Random random = new Random();
        if (n > 0 && p >= 0 && p <= 1) {
            double w = -1;
            int v = 1;
            while (v < n) {
                double r = random.nextDouble();
                w += 1 + (Math.log(1 - r) / Math.log(1 - p));
                for (int i = 0; i < n; i++) {
                    addNoeud(i);
                }
                while (w >= v && v < n) {
                    w -= v;
                    v += 1;
                }

                if (v < n) {
                    addArc(v, (int) Math.floor(w));
                    if (!oriente) {
                        addArc((int) Math.floor(w), v);
                    }
                }
            }
        }
    }
}