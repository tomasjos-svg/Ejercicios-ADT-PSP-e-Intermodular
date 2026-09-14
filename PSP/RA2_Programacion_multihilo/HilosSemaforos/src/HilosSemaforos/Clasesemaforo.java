package HilosSemaforos;

public class Clasesemaforo{
private String color;
private int temperatura;
private boolean hayCambio=false;
Clasesemaforo(String c) {
  this.color=c;
  this.temperatura=0;
 }
public synchronized String getColor() {
        return color;
 }
public synchronized int getTemperatura() {
        return this.temperatura;
 }
public synchronized void cambiadatos(String c, int t) {
  this.color=c;
  this.temperatura=t;
  hayCambio=true;
  if(color.equals("verde")){
     notify();
   }
}
public synchronized int muestraDatos(){
while(!color.equals("verde")){
 try{
       wait();
  } catch(Exception e){e.printStackTrace();};
 }
  hayCambio=false;
  return temperatura; 
}
}
