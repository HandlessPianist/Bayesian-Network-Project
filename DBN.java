package Projecto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DBN implements Serializable{

	public grafoo graph;
    public amostra data;
    double S;
     
    public DBN(grafoo g, amostra a, double k){
         
        graph=g;
        data=a;
        S=k;
             
    }
 
    //calcula a probabilidade da variavel X ser igual ao valor x dado o vector de valores "antes" 
    public static double prob(DBN net, int[] antes, int X, int x){
    	
        int[] domains=amostra.dom(net.data);
        int n= amostra.varnum(net.data);
                     
        double DFO;
          
        int[] pais = net.graph.parents(X+n);
                 
        if(pais.length==0){ 
        	
        	int[] valor =new int[1];
            valor[0]=x;
            int[] variavel =new int[1];
            variavel[0]=x;
            
        	DFO=(amostra.count_time(net.data, variavel, valor)+net.S)/(amostra.length(net.data)-net.data.vector.length +net.S*domains[X]);
        	
        }
        
        
        else{
        	
        	DFO=0;
        	
        	int[] valoresPais=new int[pais.length];
        	
        	//numero de combinacoes possiveis para popular o vector valoresPais:
        	int comb=1;
        	for(int j=0;j<pais.length;j++){
        		if(pais[j]>=n){comb=comb*domains[pais[j]-n];}
        	}
        	
        	//popular o vector valoresPais:
        	for(int j=0;j<comb;j++){
				int k=j;
				for(int w=0;w<valoresPais.length;w++){
					if(pais[w]<n){valoresPais[w]=antes[w];}
					else{
					valoresPais[w]=k%domains[pais[w]-n];
					k=k/domains[pais[w]-n];
					}
				}
				
				int[] variaveis = new int[pais.length +1];
                variaveis[0]=X+n;
                for(int w=0;w<pais.length;w++){
                	variaveis[w+1]=pais[w];
                }
                 
                int[] valores =new int[variaveis.length];
                valores[0]=x;
                for(int w=0;w<valoresPais.length;w++){
                	valores[w+1]=valoresPais[w];
                }
                
                //para aplicacao da lei da probabilidade total
                double totalProbLaw=1;
                for(int w=0;w<pais.length;w++){
                	if(pais[w]>=n){
                		
                		
                		totalProbLaw=totalProbLaw*prob(net,antes, pais[w]-n, valoresPais[w]);
                		}
                }
                
               
                DFO=DFO+totalProbLaw*(amostra.count_time(net.data, variaveis, valores)+net.S)/(amostra.count_time(net.data, pais, valoresPais)+net.S*domains[X]);
                
        	}
        	
        }
                
            
         
        return DFO;
    }

    //devolve o valor mais provavel da variavel X dado o vector de valores "antes":
    public static int prev(DBN net, int[] antes, int X){
    	
    	//dominio da variavel X:
    	int domain=amostra.dom(net.data)[X];
    	double prob=0;
    	int x=0;
    	
    	for(int i=0;i<domain;i++){
    		double prob2=prob(net,antes,X,i);
    		if(prob2>prob){
    			prob=prob2;
    			x=i;
    		}
    	}
    	
    	return x;
    }

}
