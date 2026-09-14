package iniciadorProcesos;

public class IniciadorProcesos{ 
	public void ejecutar(String ruta){ 
		ProcessBuilder pb; 
		try { 
			pb = new  ProcessBuilder(ruta); 
		    pb.inheritIO();  
			Process p=	pb.start(); 
			p.waitFor(); 
			//    Process p=   
			//      
			//Runtime.getRuntime().exec(ruta); 
		} 
        catch ( Exception e) {        
        	e.printStackTrace(); 
      } 
 
} 
public static void main(String[] args){                                      
	String ruta= "C:\\users\\tomas\\downloads\\sqldeveloper-26.2.0.186.2220-x64\\sqldeveloper\\sqldeveloper.exe"; 
	IniciadorProcesos lp=new IniciadorProcesos(); 
	lp.ejecutar(ruta);            
	System.out.println("Finalizado"); 
	} 
}  
