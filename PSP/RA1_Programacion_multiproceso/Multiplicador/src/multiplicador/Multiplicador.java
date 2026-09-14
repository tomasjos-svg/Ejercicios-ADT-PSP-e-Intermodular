package multiplicador;

public class Multiplicador {
	  public static int multiplicar( int n1, int n2)  {
	       int result=1;
	       for (int i=n1;i<=n2;i++){
	           result=result*i;
	       }
	       return result;
	  } 
	public static void main(String[] args){
	      int n1 = Integer.parseInt(args[0]);
	      int n2 = Integer.parseInt(args[1]);
	      int resultado = 0;
	      resultado = multiplicar(n1, n2);
	      System.out.println(resultado);
	  }
	 }

