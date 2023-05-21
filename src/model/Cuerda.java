package model;

public final class Cuerda extends Instrumento{
    private String materialCuerda;
    private int numeroCuerdas;
    private String tipo;
    //id,precio,stock,nombreInstrumento,material,<características según el tipo de instrumento>
    public Cuerda(String codigo, int precio, int stock, String nombre, String materialCuerda, int numeroCuerdas, String material, String tipo) {
        super(codigo,precio,stock,nombre,material);

        this.materialCuerda = materialCuerda;
        this.numeroCuerdas = numeroCuerdas;
        this.tipo = tipo;
    }

    public String getMaterialCuerda() {
        return materialCuerda;
    }

    public int getNumeroCuerdas() {
        return numeroCuerdas;
    }

    public String getTipo() {
        return tipo;
    }
}
