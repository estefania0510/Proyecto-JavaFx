package co.edu.uptc.model;

import java.util.List;

public class Orden {
    private String user;
    private  List<Producto> productosCarrito;
    private String direccion;
    private double priceTotal;
    public Orden() {
    }
    public Orden(String user, List<Producto> productosCarrito, String direccion, double priceTotal) {
        this.user = user;
        this.productosCarrito = productosCarrito;
        this.direccion = direccion;
        this.priceTotal = priceTotal;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public List<Producto> getProductosCarrito() {
        return productosCarrito;
    }
    public void setProductosCarrito(List<Producto> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public double getPriceTotal() {
        return priceTotal;
    }
    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
    @Override
    public String toString() {
        return "Orden:\n" + //
                        "Usuario: " + user + "\n Productos del carrito:" + productosCarrito + "\n Dirección:" + direccion
                + "\nPrecio Total de la compra : $" + priceTotal + ".";
    }
}
