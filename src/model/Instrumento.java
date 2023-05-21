package model;

public abstract class Instrumento {
    private String nombre;
    private String material;
    private String codigo;
    private int stock;
    private int precio;
    //id,precio,stock,nombreInstrumento,material,<características según el tipo de instrumento>
    public Instrumento(String codigo, int precio, int stock, String nombre, String material) {
        this.nombre = nombre;
        this.material = material;
        this.codigo = codigo;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMaterial() {
        return material;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getStock() {
        return stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void venderInstrumento() {
        //si no hay stock tira error
        if (this.stock == 0) {
            throw new IllegalArgumentException("No queda stock de este instrumento!");
        }
        //Se resta una unidad del stock disponible
        this.stock -= 1;
    }
    public void agregarInstrumento(int cantidad) {
        this.stock += cantidad;
    }
}
