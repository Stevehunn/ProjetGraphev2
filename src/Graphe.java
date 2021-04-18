import java.io.*;
import java.util.*;

public class Graphe {
    LinkedList<Noeud>  noeuds;
    HashMap<Integer,Noeud> hmap;
    ArrayList<Integer> couleursDispo;



    public Graphe(int k) {
        noeuds=new LinkedList<Noeud>();
        hmap=new HashMap<Integer,Noeud>();
        couleursDispo=new ArrayList<>();
        for(int i=0; i<k;i++) {
            Noeud noeud=new Noeud(i);
            if(!noeuds.contains(noeud)&& !hmap.containsKey(i))
                this.noeuds.add(noeud);
                this.hmap.put(i, noeud);
                couleursDispo.add(i);
        }

    }


    public void addNoeud(int n) {
            boolean trouve=false;

            for(Noeud i:noeuds) {
                if(i.getId()==n) {
                    trouve=true;
                }

            }

            if(trouve==false) {
                this.noeuds.add(new Noeud(n));
                this.hmap.put(hmap.size(),new Noeud(n));
            }

    }

    public Noeud getNoeud(int n) {

        Noeud noeud=null;
      for(int i:hmap.keySet()){
          if(hmap.get(i).getId()==n){
              noeud= hmap.get(i);
          }
      }

        return noeud;


    }

    public void addArc(int x,int y) {
        Noeud noeudx=this.getNoeud(x);
        Noeud noeudy=this.getNoeud(y);
        if( noeudx!=null && noeudy!=null) {
            if(!noeudx.hasSuccesseur(y)) {
                Arc a=new Arc(noeudx,noeudy);
                noeudx.getSuccesseurs().add(a);
            }
        }
    }

    public String toString() {
        String graphe=" ";
        for(Noeud n:hmap.values()) {

            graphe=graphe+"\n"+n.toString();
        }

        return graphe ;
    }

    public LinkedList<Noeud> getNoeuds() {
        LinkedList<Noeud> noeudsT=new LinkedList<>();
        for(Noeud n: hmap.values()){
            noeudsT.add(n);
        }


        return noeuds;
    }

    public Graphe(String file) {
        noeuds=new LinkedList<Noeud>();
        hmap=new HashMap<Integer,Noeud>();
        couleursDispo=new ArrayList<>();
        try {
            File inputfile = new File(file);
            FileReader in=new FileReader(inputfile);
            BufferedReader br=new BufferedReader(in);
            String line= br.readLine();

            while(line!=null){
                if(!line.equalsIgnoreCase("Source,Target")) {
                    String[] data=line.split(",");
                    int x=Integer.parseInt(data[0]);
                    int y=Integer.parseInt(data[1]);
                    this.addNoeud(x);
                    this.addNoeud(y);
                    this.addArc(x,y);
                }

                line= br.readLine();

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i< hmap.size();i++){
            couleursDispo.add(i+1);
        }
    }
    
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

    public   boolean[][]  matriceAdj(){
        boolean[][] adj= new boolean[hmap.size()][hmap.size()];
        for (Noeud noeud : hmap.values()){
            for (Arc a: noeud.getSuccesseurs()){
                adj[a.getSource().getId()][a.getCible().getId()] = true;
            }
            for (Arc a: noeud.getSuccesseurs()){
                adj[a.getCible().getId()][a.getSource().getId()] = true;
            }
        }
        return adj;
    }

    public LinkedList<Noeud> listNoeudDegreDeCroissant(){
        LinkedList<Noeud> valeurs=new LinkedList<>();

       LinkedList<Noeud> mesNoeuds=new LinkedList<>();
        for(Noeud n: hmap.values()){
            mesNoeuds.add(n);

           }
        while (valeurs.size()<hmap.size()) {
            Noeud max=mesNoeuds.getFirst();
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
      public LinkedList<Integer> couleursVoisins(Noeud n, int[] couleurs){
        boolean[][] adj=matriceAdj();
        LinkedList<Integer> liste=new LinkedList<>();
       for(Noeud i: hmap.values()){
           if(adj[i.getId()][n.getId()]){
                   if (!liste.contains(couleurs[i.getId()])) {
                       liste.add(couleurs[i.getId()]);
                   }

           }
           if(adj[n.getId()][i.getId()]){
               if (!liste.contains(couleurs[i.getId()])) {
                   liste.add(couleurs[i.getId()]);
               }

           }
       }
 return liste;
    }
    public int degreDecroissant(){
        int[] couleurs=new int[hmap.size()];

        LinkedList<Noeud> valeurs=listNoeudDegreDeCroissant();
        boolean[][] adj=matriceAdj();
        for(Noeud x: valeurs){
            int couleur=1;
            for(Noeud y: valeurs){
                if(adj[x.getId()][y.getId()] && couleurs[y.getId()]==couleur){
                    couleur=couleur+1;
                }

            }
            while (couleursVoisins(x,couleurs).contains(couleur)){
                couleur=couleur+1;
            }
            couleurs[x.getId()]=couleur;
        }

        for(int i=0;i<couleurs.length;i++){
            System.out.println(valeurs.get(i).getId()+" : " +couleurs[valeurs.get(i).getId()]+" ");

        }


      return max(couleurs);

    }

    public int indiceCroissant(){
     int[] couleurs=new int[hmap.size()];

        boolean[][] adj=matriceAdj();
            for(Noeud x: hmap.values()){
                int couleur=1;
                for(Noeud y: hmap.values()){
                    if(adj[x.getId()][y.getId()] && couleurs[y.getId()]==couleur){
                        couleur=couleur+1;
                    }
                }
                while (couleursVoisins(x,couleurs).contains(couleur)){
                    couleur=couleur+1;
                }
                couleurs[x.getId()]=couleur;
            }

        for(int i=0;i<couleurs.length;i++){
            System.out.println(hmap.get(i).getId()+" : " +couleurs[hmap.get(i).getId()]+" ");
            //System.out.println(couleursVoisins(hmap.get(i),couleurs).toString());
        }

        return max(couleurs);


    }


     public LinkedList<Noeud> permuterNoeud(){
        int n1= (int) (Math.random()*(hmap.size()-1));
        int n2=(int) (Math.random()*(hmap.size()-1));
         LinkedList<Noeud> mesNoeuds=new LinkedList<>();
         for(Noeud n: hmap.values()){
             mesNoeuds.add(n);

         }
        // System.out.println(mesNoeuds.toString());
         Noeud x= mesNoeuds.get(n1);
         Noeud y=mesNoeuds.get(n2);
         mesNoeuds.remove(x);
         mesNoeuds.add(n1,y);
         mesNoeuds.remove(y);
         mesNoeuds.add(n2,x);

       //  System.out.println(mesNoeuds.toString());



     return mesNoeuds;
      }
     LinkedList<String> ordreParcours=new LinkedList<>();
      public int solutionColoriage(  LinkedList<Noeud> valeurs){
          int[] couleurs=new int[hmap.size()];
          LinkedList<String> ordre=new LinkedList<>();
          boolean[][] adj=matriceAdj();
          for(Noeud x: valeurs){
              int couleur=1;
              for(Noeud y: valeurs){
                  if(adj[x.getId()][y.getId()] && couleurs[y.getId()]==couleur){
                      couleur=couleur+1;
                  }
              }
              while (couleursVoisins(x,couleurs).contains(couleur)){
                  couleur=couleur+1;
              }
              couleurs[x.getId()]=couleur;
          }
          for(int i=0;i<couleurs.length;i++){
              ordre.add(valeurs.get(i).getId()+" : " +couleurs[valeurs.get(i).getId()]);
             // System.out.println(hmap.get(i).getId()+" : " +couleurs[hmap.get(i).getId()]+" ");
              //System.out.println(couleursVoisins(hmap.get(i),couleurs).toString());
          }
          ordreParcours=ordre;
          return max(couleurs);

      }

    public int recuitSimule(double temp,double alpha){
        int nci=solutionColoriage(listNoeudDegreDeCroissant());
        int ncv=0;
        LinkedList<String> ordre=ordreParcours;;
        //ArrayList<Integer> solutions=new ArrayList<>();
        //solutions.add(nci);
        int nbmax=3;
        while (temp>0){
            int i=0;
            while(i<nbmax){
                ncv=solutionColoriage(permuterNoeud());
                if(nci>ncv){
                    nci=ncv;
                    ordre=ordreParcours;

                }
                i++;

            }
            temp= alpha*temp;
        }
        System.out.println(ordre.toString());
        return nci;


    }

    public int algoBrown(Noeud n){
        int[] couleurs=new int[noeuds.size()];

        for(int c=0; c<couleursDispo.size();c++){
            couleurs[n.getId()]=c;
            if(couleursVoisins(n,couleurs).contains(c)){

            }
        }

        return 1;
    }

	public int smallestLast() {
		 int[] couleurs=new int[hmap.size()];

			LinkedList<Noeud> valeurs=listNoeudDegreCroissant();
			boolean[][] adj=matriceAdj();
			for(Noeud x: valeurs){
				int couleur=1;
				for(Noeud y: valeurs){
					if(adj[x.getId()][y.getId()] && couleurs[y.getId()]==couleur){
						couleur=couleur+1;
					}

				}
				while (couleursVoisins(x,couleurs).contains(couleur)){
					couleur=couleur+1;
				}
				couleurs[x.getId()]=couleur;
			}

			for(int i=0;i<couleurs.length;i++){
				System.out.println(valeurs.get(i).getId()+" : " +couleurs[valeurs.get(i).getId()]+" ");

			}


			return max(couleurs);

	 }
	
	 
	 public LinkedList<Noeud> listNoeudDegreCroissant(){
			LinkedList<Noeud> valeurs=new LinkedList<>();

			LinkedList<Noeud> mesNoeuds=new LinkedList<>();
			for(Noeud n: hmap.values()){
				mesNoeuds.add(n);

			}
			while (valeurs.size()<hmap.size()) {
				Noeud max=mesNoeuds.getFirst();
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

    public int max(int[] a){
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > m)
                m = a[i];
        }
        return m;
    }
    // reinitialise les noeuds
    public void reinitNoeuds() {
        for(Noeud n : this.getNoeuds()) {
            n.setCouleur(-1);
            n.setMark(false);
        }
    }

}
