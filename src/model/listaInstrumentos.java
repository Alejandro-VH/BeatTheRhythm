package model;

public class listaInstrumentos {
    private Instrumento[] instrumentos;
    private int cantActual;
    private int cantMax;

    public listaInstrumentos() {
        this.instrumentos = new Instrumento[200];
        cantActual = 0;
        cantMax = 200;
    }

    public boolean agregarInstrumento(Instrumento nuevo){
        if (nuevo == null){
            throw new IllegalArgumentException("el instrumento es nulo!");
        }

        if(this.cantActual < this.cantMax){
            this.instrumentos[this.cantActual] = nuevo;
            this.cantActual++;
            return true;
        }
    return false;
    }

    public Instrumento obtenerInstrumento(int pos){
        return this.instrumentos[pos];
    }

    public boolean eliminarInstrumento(int id){
        return false;
    }

    public int getCantActual() {
        return cantActual;
    }
}
