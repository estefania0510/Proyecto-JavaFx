package co.edu.uptc.model;

public class Sala {

     private String nombre1;





    private int id;
    private String nombre;
    private int capMaxima;
    private boolean disponible;
    private static int nextId = 1;
    public Sala() {
    }
    public Sala(int id, String nombre, int capMaxima, boolean disponible) {
        this.id = nextId++;
        this.nombre = nombre;
        this.capMaxima = capMaxima;
        this.disponible = disponible;
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
    public int getCapMaxima() {
        return capMaxima;
    }
    public void setCapMaxima(int capMaxima) {
        this.capMaxima = capMaxima;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public static void setNextId(int nextId) {
        Sala.nextId = nextId;
    }
    @Override
    public String toString() {
        return "Sala [id=" + id + ", nombre=" + nombre + ", capMaxima=" + capMaxima + ", disponible=" + disponible
                + "]";
    }
    
}
