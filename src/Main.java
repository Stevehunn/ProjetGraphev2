public class Main {
    public static void main(String[] args) 
    {
		//Graphe g;
		/*----------Graphe avec 40 noeuds---------*/
    	/*g=new Graphe(40);
        g.addArc(0,1);g.addArc(1,3);g.addArc(1,5);g.addArc(1,6);g.addArc(1,7);g.addArc(2,1);
		g.addArc(3,2);g.addArc(3,6);g.addArc(4,2);g.addArc(4,9);g.addArc(7,3);g.addArc(7,6);
		g.addArc(8,2);g.addArc(8,4);g.addArc(8,5);g.addArc(9,1);g.addArc(8,1);g.addArc(8,3);
    	g.addArc(8,6);g.addArc(9,0);g.addArc(10,1);g.addArc(11,13);g.addArc(15,25);g.addArc(21,6);
    	g.addArc(11,7);g.addArc(12,1);g.addArc(13,12);g.addArc(23,16);g.addArc(24,22);g.addArc(14,29);
		g.addArc(17,23);g.addArc(27,26);g.addArc(18,2);g.addArc(18,4);g.addArc(18,15);g.addArc(29,1);
		g.addArc(28,1);g.addArc(18,13);g.addArc(7,16);g.addArc(19,0);g.addArc(20,11);g.addArc(5,13);
		g.addArc(6,25);g.addArc(2,19);g.addArc(11,17);g.addArc(10,14);g.addArc(13,19);g.addArc(22,11);
		g.addArc(20,8);g.addArc(11,27);g.addArc(16,21);g.addArc(24,22);g.addArc(14,21);g.addArc(11,25);
		g.addArc(15,14);g.addArc(28,2);g.addArc(17,3);g.addArc(12,4);g.addArc(4,15);g.addArc(26,0);
		g.addArc(25,5);g.addArc(39,13);g.addArc(37,25);g.addArc(38,19);g.addArc(35,17);g.addArc(36,14);
		g.addArc(34,19);g.addArc(33,11);g.addArc(31,8);g.addArc(30,27);g.addArc(29,21);g.addArc(28,22);
		g.addArc(26,21);g.addArc(25,25);g.addArc(32,14);g.addArc(38,2);g.addArc(37,3);g.addArc(12,4);
		g.addArc(34,15);g.addArc(36,0);g.addArc(35,5);*/


		/*----------Graphe avec 6 noeuds----------*/
		/*g=new Graphe(6);
		g.addArc(1,2);
		g.addArc(6,2);
		g.addArc(5,4);
		g.addArc(2,3);
		g.addArc(4,2);
		g.addArc(5,2);
		g.addArc(3,2);
		g.addArc(2,5);
		g.addArc(1,5);
		g.addArc(4,2);
		g.addArc(5,2);
		g.addArc(3,2);
		g.addArc(1,5);
		g.addArc(0,5);*/

		/*----------Graphe avec 6 noeuds non oriente----------*/
		Graphe g = new Graphe(6);
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
		g.backtrack2();

		/*-------Graphe avec 100 noeuds-------*/
//    	g=new Graphe(100);
//    	g.addArc(0,1);g.addArc(1,3);g.addArc(1,5);g.addArc(1,6);g.addArc(1,7);g.addArc(2,1);g.addArc(3,2);g.addArc(3,6);
//		g.addArc(4,2);g.addArc(4,9);g.addArc(7,3);g.addArc(7,6);g.addArc(8,2);g.addArc(8,4);g.addArc(8,5);
//		g.addArc(9,1);g.addArc(8,1);g.addArc(8,3);g.addArc(8,6);g.addArc(9,0);g.addArc(10,1);g.addArc(11,13);
//		g.addArc(15,25);g.addArc(21,6);g.addArc(11,7);g.addArc(12,1);g.addArc(13,12);g.addArc(23,16);
//		g.addArc(24,22);g.addArc(14,29);g.addArc(17,23);g.addArc(27,26);g.addArc(18,2);g.addArc(18,4);g.addArc(18,15);
//		g.addArc(29,1);g.addArc(28,1);g.addArc(18,13);g.addArc(7,16);g.addArc(19,0);g.addArc(20,11);
//
//		g.addArc(5,13);g.addArc(6,25);g.addArc(2,19);g.addArc(11,17);g.addArc(10,14);g.addArc(13,19);g.addArc(22,11);
//		g.addArc(20,8);g.addArc(11,27);g.addArc(16,21);g.addArc(24,22);g.addArc(14,21);g.addArc(11,25);
//		g.addArc(15,14);g.addArc(28,2);g.addArc(17,3);g.addArc(12,4);g.addArc(4,15);g.addArc(26,0);
//		g.addArc(25,5);g.addArc(39,13);g.addArc(37,25);g.addArc(38,19);g.addArc(35,17);g.addArc(36,14);
//		g.addArc(34,19);g.addArc(33,11);g.addArc(31,8);g.addArc(30,27);g.addArc(29,21);g.addArc(28,22);
//		g.addArc(26,21);g.addArc(25,25);g.addArc(32,14);g.addArc(38,2);g.addArc(37,3);g.addArc(12,4);g.addArc(34,15);
//		g.addArc(36,0);g.addArc(35,5);g.addArc(0,1);g.addArc(1,3);g.addArc(1,5);g.addArc(1,6);g.addArc(1,7);
//		g.addArc(2,1);g.addArc(3,2);g.addArc(3,6);g.addArc(4,2);g.addArc(4,9);g.addArc(7,3);g.addArc(7,6);g.addArc(8,2);
//		g.addArc(8,4);g.addArc(8,5);g.addArc(9,1);g.addArc(8,1);g.addArc(8,3);g.addArc(8,6);g.addArc(9,0);
//
//		g.addArc(10,1);g.addArc(11,13);g.addArc(15,25);g.addArc(21,6);g.addArc(11,7);g.addArc(12,1);
//		g.addArc(23,16);g.addArc(24,22);g.addArc(14,29);g.addArc(17,23);g.addArc(27,26);g.addArc(18,2);
//		g.addArc(18,4);g.addArc(18,15);g.addArc(29,1);g.addArc(28,1);g.addArc(18,13);g.addArc(7,16);
//		g.addArc(19,0);g.addArc(20,11);g.addArc(5,13);g.addArc(6,25);g.addArc(2,19);g.addArc(11,17);
//		g.addArc(10,14);g.addArc(13,19);g.addArc(22,11);g.addArc(20,8);g.addArc(11,27);g.addArc(16,21);
//		g.addArc(24,22);g.addArc(14,21);g.addArc(11,25);g.addArc(15,14);g.addArc(28,2);g.addArc(17,3);
//		g.addArc(12,4);g.addArc(52,15);g.addArc(53,0);g.addArc(52,5);g.addArc(51,13);g.addArc(51,25);
//		g.addArc(50,19);g.addArc(51,17);g.addArc(50,14);g.addArc(44,19);g.addArc(43,11);g.addArc(45,8);
//		g.addArc(48,27);g.addArc(47,21);g.addArc(48,22);g.addArc(46,21);g.addArc(49,25);g.addArc(45,14);
//		g.addArc(43,2);g.addArc(41,3);g.addArc(42,4);g.addArc(42,15);g.addArc(41,0);g.addArc(40,5);
//		g.addArc(0,1);g.addArc(1,3);g.addArc(1,5);g.addArc(1,61);g.addArc(1,74);g.addArc(2,17);g.addArc(3,28);
//		g.addArc(3,65);g.addArc(4,23);g.addArc(4,90);g.addArc(7,87);g.addArc(7,86);g.addArc(8,22);g.addArc(8,20);
//		g.addArc(8,30);g.addArc(9,40);g.addArc(8,60);g.addArc(8,70);g.addArc(8,80);g.addArc(9,90);
//
//		g.addArc(10,77);g.addArc(11,76);g.addArc(15,75);g.addArc(21,63);g.addArc(11,74);g.addArc(12,76);
//		g.addArc(13,75);g.addArc(23,84);g.addArc(24,83);g.addArc(14,82);g.addArc(17,81);g.addArc(27,28);
//		g.addArc(18,73);g.addArc(18,71);g.addArc(18,79);g.addArc(29,73);g.addArc(28,70);g.addArc(18,81);
//		g.addArc(7,82);g.addArc(19,83);g.addArc(20,84);g.addArc(5,91);g.addArc(6,93);g.addArc(2,92);
//		g.addArc(11,94);g.addArc(10,97);g.addArc(13,54);g.addArc(22,68);g.addArc(20,80);g.addArc(11,83);
//		g.addArc(16,85);g.addArc(24,95);g.addArc(14,96);g.addArc(11,93);g.addArc(15,94);g.addArc(28,90);
//		g.addArc(17,81);g.addArc(12,80);g.addArc(4,75);g.addArc(26,70);
//		g.addArc(25,78);g.addArc(39,68);g.addArc(37,67);g.addArc(38,50);g.addArc(35,54);g.addArc(36,34);
//		g.addArc(34,24);g.addArc(33,67);g.addArc(31,52);g.addArc(30,64);g.addArc(29,35);g.addArc(28,67);
//		g.addArc(26,85);g.addArc(25,97);g.addArc(32,76);g.addArc(38,68);g.addArc(37,58);g.addArc(12,36);
//		g.addArc(34,98);g.addArc(36,99);g.addArc(35,55);

		//g = new RandomGraphe(5, 0.5, false);
		g.export();

		//System.out.println(g.toString());

//	   System.out.println("\n *********************  Parcourt en profondeur Recursif *********************");
//	   //g.parcours();
//
//	   System.out.println("\n *********************  Parcourt en profondeur Iteratif  *********************");
//	   //g.profI();
//
//	   System.out.println("\n *********************  Parcourt en largeur *********************");
//	   //g.largeur();

		System.out.println(" ******************  Coloration Degre croissant     *****************          ");
		System.out.println("\nnombre chromatique: " + g.degreDecroissant());

		System.out.println(" ******************  Coloration indice croissant    *****************        ");
		System.out.println("\nnombre chromatique: " + g.indiceCroissant());

		//Graphe g1=new Graphe("class Graphe.csv");
		System.out.println(g.toString());
		System.out.println(" ******************  Coloration Degre croissant     *****************          ");
		long begin=System.nanoTime();
		System.out.println("\nnombre chromatique: "+g.degreDecroissant());
		long end =System.nanoTime();
		float time=end-begin;
		System.out.println(time);
		System.out.println(" ******************  Coloration indice croissant    *****************        ");
			long begin1=System.nanoTime();
		System.out.println("\nnombre chromatique: "+g.indiceCroissant());
		long end1 =System.nanoTime();

		float time1=end1-begin1;
		System.out.println(time1);
		long begin2=System.nanoTime();
		
		System.out.println(g.recuitSimule(1.5,0.5));
		long end2 =System.nanoTime();
		float time2=end2-begin2;
		System.out.println(time2);
//		IndiceCroissant i=new IndiceCroissant(g);
//		DegreDecroissant d=new DegreDecroissant(g);
//		System.out.println(g.listNoeudDegreDeCroissant().toString());
//		System.out.println(g.matriceAdj()[1][0]);



    }

}
