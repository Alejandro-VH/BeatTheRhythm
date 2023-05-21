package model;

public final class Percusion extends Instrumento{
    private String tipoPercusion;
    private String altura;
    //id,precio,stock,nombreInstrumento,material,<características según el tipo de instrumento>
    public Percusion(String codigo, int precio, int stock, String nombre, String material,String tipoPercusion, String altura) {
        super(codigo,precio,stock,nombre,material);

        this.tipoPercusion = tipoPercusion;
        this.altura = altura;
    }

    public String getTipoPercusion() {
        return tipoPercusion;
    }

    public String getAltura() {
        return altura;
    }

}
