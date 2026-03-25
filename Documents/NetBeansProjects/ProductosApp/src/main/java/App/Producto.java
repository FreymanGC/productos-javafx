package App;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private String descripcion;

    public Producto(String codigo, String nombre, double precio, Categoria categoria, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - ₡" + precio + " - " + categoria + " - " + descripcion;
    }
}