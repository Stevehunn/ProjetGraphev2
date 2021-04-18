public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

       /* RandomGraphe g1=new RandomGraphe(10, 0.5F);
        System.out.println(g1.toString());
         g1.export();*/
		/*Graphe g=new Graphe(8);
		g.addArc(0,1);
		g.addArc(0,2);
		g.addArc(0,3);
		g.addArc(1,4);
		g.addArc(1,5);
		g.addArc(2,6);
		g.addArc(2,7);
		/*g.addArc(1,3);
		g.addArc(1,5);
		g.addArc(1,6);
		g.addArc(1,7);
		g.addArc(2,1);
		g.addArc(3,2);
		g.addArc(3,6);
		g.addArc(4,2);
		g.addArc(4,9);
		g.addArc(7,3);
		g.addArc(7,6);
		g.addArc(8,2);
		g.addArc(8,4);
		g.addArc(8,5);
		g.addArc(0,4);*/
		//g.export();*/

      		/*----------Graphe avec 6 noeuds non oriente----------*/
		/*Graphe g = new Graphe(6);
		g.addArc(0, 1);
		g.addArc(1, 0);
		g.addArc(1, 2);
		g.addArc(2, 1);
		g.addArc(2, 3);
		g.addArc(2, 5);
		g.addArc(3, 2);
		g.addArc(4, 2);
		g.addArc(4, 5);
		g.addArc(5, 4);
		g.addArc(5, 2);
		g.addArc(5, 1);
		g.backtrack();
		g.reinitNoeuds();
		g.backtrack2();*/
      
        //Graphe g1=new Graphe("class Graphe.csv");
        RandomGraphe g1=new RandomGraphe(10, 0.5);
        System.out.println(g1.toString());
		System.out.println(" ******************  Coloration Degre croissant     *****************          ");
		long begin=System.nanoTime();
		System.out.println("\nnombre chromatique: "+g1.degreDecroissant());
		long end =System.nanoTime();
		float time=end-begin;
		System.out.println(time);
		System.out.println(" ******************  Coloration indice croissant    *****************        ");
			long begin1=System.nanoTime();
		System.out.println("\nnombre chromatique: "+g1.indiceCroissant());
		long end1 =System.nanoTime();

		float time1=end1-begin1;
		System.out.println(time1);

        System.out.println(" ******************  Coloration Recuit Simulé    *****************        ");
		long begin2=System.nanoTime();
		 System.out.println(g1.recuitSimule(1.5,0.5));
		long end2 =System.nanoTime();

		float time2=end2-begin2;
		System.out.println(time2);

		System.out.println(" ******************  Coloration Backtracking    *****************        ");
		long begin3=System.nanoTime();

		System.out.println(g1.backTrack(g1.hmap.size()));
		long end3 =System.nanoTime();

		float time3=end3-begin3;
		System.out.println(time3);



         //Sudoku s=new Sudoku();
         //System.out.println(s.toString());
		//System.out.println(" ******************  Coloration Backtracking sudoku    *****************        ");
		 //System.out.println(s.backTrack(s.hmap.size()));
		//System.out.println("\nnombre chromatique: "+s.recuitSimule(1.5,0.2));



    }

}
