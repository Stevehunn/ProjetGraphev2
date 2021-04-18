

public class RandomGraphe extends Graphe{
	
	/*-----------cr�ation d'un graphe selon la m�thode de Gilbert-----------*/
    public RandomGraphe(int noeuds, float probabilit�){
    	super("class Graphe.csv");
    	if(noeuds > 0 && probabilit� >= 0 && probabilit� <= 1){
            float a = -1;
            int b = 1;
            for(int i = 0;i<noeuds;i++)
                addNoeud(i);
            while(b < noeuds){
                float c = (float) Math.random();
                a = a + 1 + (float)(Math.log(1-c)/Math.log(1-probabilit�));
                
                while(a >= b && b < noeuds)
                {
                    a -= b;
                    b += 1;
                }

                if(b < noeuds)
                    addArc(b, (int) Math.floor(a));
            }
        }
    }
}