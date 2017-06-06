package Projecto;

import java.util.ArrayList;
import java.util.LinkedList;


public class bfs {
	
	/**
	 * Se usares cenas do pacote arrays
	 * e aquilo na descrição disser na parte da implementacao
	 * IMPLEMENTS INTERFACE, ou seja, a operacao implementa uma interface
	 * usa este comando
	 * @Override
	 * public boolean method(){....
	 * e isso resolve-te o problema
	 */

	public static ArrayList<Integer> sempais(grafoo g){
		
		ArrayList<Integer> v = new ArrayList<Integer>();
		int[][] a=g.adjMatrix;
		for(int i=0;i< a.length;i++){
		if(g.parentsnum(i)==0){v.add(i);}
		}; 
		return v;
	}
	
	
	  public static boolean cycleQ(grafoo g){
		  int[][] matrix = g.adjMatrix; 
		  boolean bol=true;
		  int comp=g.adjMatrix.length;
		  for(int i=0;i<comp;i++){
			  if(matrix[i][i]==1){bol=false;};}// se a diagonal é !=0 então é ciclico
		  
		  ArrayList<Integer> sp=sempais(g);
		  if(sp.isEmpty()){bol=false;}// se não há nos sem pais então é ciclico
		  
		  // Create a queue
		    LinkedList<Integer> queue = new LinkedList<Integer>();
		    // Create a visited list.
		    
		    int k=0;
			while(k<sp.size()){
			ArrayList<Integer> visited = new ArrayList<Integer>();
		    queue.offer(sp.get(k));
		    k++;
			System.out.println(queue);
		    // While the queue is not empty
		    while(!queue.isEmpty()&& bol){
		      int node = queue.poll();
		      visited.add(node); 
		      System.out.printf("%d ", node);
		      
		      for(int i = 0; i < comp; i++){
		        // If node not in queue or in visited add to queue.
		        if(matrix[node][i] == 1){ // Make sure we are an edge
		          if(!queue.contains(i)){
		        	  if(!visited.contains(i)){
		        		  queue.offer(i);}
		        	  else{bol=false;}
		        	  }
		        }
		      }
		    }
			}
		    return !bol;
		  }
	
	  public static boolean path(grafoo g,int in){
		  int[][] matrix = g.adjMatrix; 
		  boolean bol=true;
		  int comp=g.adjMatrix.length;
		  
		  LinkedList<Integer> queue = new LinkedList<Integer>();
		  ArrayList<Integer> visited = new ArrayList<Integer>();
		  queue.offer(in);
		  
		  while(!queue.isEmpty()&& bol){
		      int node = queue.poll();
		      visited.add(node); 
		      System.out.printf("%d ", node);
		      
		      for(int i = 0; i < comp; i++){
		        // If node not in queue or in visited add to queue.
		        if(matrix[node][i] == 1){ // Make sure we are an edge
		          if(!queue.contains(i)){
		        	  if(!visited.contains(i)){
		        		  queue.offer(i);}
		        	  else{bol=false;}
		        	  }
		        }
		      }
		    }
		    return !bol;
		  }



}
	

