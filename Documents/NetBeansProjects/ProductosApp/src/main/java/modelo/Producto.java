package modelo;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto(String codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " | ₡" + precio + " | " + categoria;
    }
}