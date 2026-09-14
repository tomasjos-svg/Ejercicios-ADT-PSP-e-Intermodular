package Ejercicio_21_sockets_TCP;

public class SeccionCritica {
	private ListaCalificaciones listac;
	private ListaUsuarios listau;
	public synchronized void cargarListaCalificaciones() {
		listac=new ListaCalificaciones();
		listac.cargarDesdeBD();
	}
	public synchronized ListaCalificaciones devolverListaCalificaciones() {
		return listac;
	}
	public synchronized void cargarListaUsuarios() {
		listau=new ListaUsuarios();	
		listau.cargarDesdeBD();
	}
	public synchronized ListaUsuarios devolverListaUsuarios() {
		return listau;
	}
	
}
