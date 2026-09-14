package Hilos37;

import java.util.ArrayList;
import java.util.Iterator;



public class SeccionCritica {
	private int celsius;
	private int farenheit;
	public SeccionCritica() {
		celsius=0;
		farenheit=0;
	}
	public synchronized void putCelsius(int t) {
		celsius=t;
	}
	public synchronized int getCelsius() {
		return celsius;
	}
	public synchronized void putFarenheit(int t) {
		farenheit=t;
	}
	public synchronized void showTemperatura()
	{
		System.out.println("T.Celsius "+ celsius+ " T.Farenheit " + farenheit);
	}

	
}
