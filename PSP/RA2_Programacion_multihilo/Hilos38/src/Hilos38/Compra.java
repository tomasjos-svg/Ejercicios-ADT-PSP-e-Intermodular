package Hilos38;

class Compra {
    Producto producto;
    int cantidad;

    public Compra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return producto.precio * cantidad;
    }
}