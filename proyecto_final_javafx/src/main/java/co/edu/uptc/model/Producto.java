package co.edu.uptc.model;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private String description;
    private double price;
    private String categoria;
    private static int nextId = 1;

    public Producto() {
    }

    public Producto(int id, String nombre, int stock,String description, double price, String categoria) {
        this.id = nextId++;
        this.nombre = nombre;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getstock() {
        return stock;
    }
    public void setstock(int stock) {
        this.stock = stock;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public static void setNextId(int nextId) {
        Producto.nextId = nextId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    @Override
    public String toString() {
        return " \n Producto\n"+"Id:" + id + "\nNombre:" + nombre + "\nStock:" + stock + "\nDescripción:" + description
                + "\nPrecio:" + price + "\nCategoria:" + categoria ;
    }
    
    
    
    
}
