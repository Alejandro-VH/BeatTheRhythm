package services;
import model.*;
import ucn.ArchivoEntrada;
import ucn.ArchivoSalida;
import ucn.Registro;
import ucn.StdOut;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public final class Sistema {

    private listaInstrumentos instrumentos;

    public Sistema() throws IOException {
        this.instrumentos = new listaInstrumentos();
        this.cargarInstrumentos();
    }

    /**
     * Subprograma encargado de cargar el archivo que almacena el Stock
     * @throws IOException
     */
    public  void cargarInstrumentos() throws IOException {
        ArchivoEntrada archivoEntrada = new ArchivoEntrada("inventario.csv");
        int pos = 0;
        // reviso la cantidad de instrumentos que hay en la lista
        while (!archivoEntrada.isEndFile()){
            archivoEntrada.getRegistro();
            pos++;
        }
        archivoEntrada.close();

        //Matriz y lista para leer el archivo
        String[][] LecturaArchivo = new String[pos][5];

        archivoEntrada = new ArchivoEntrada("inventario.csv");
        int aux = 0;
        for (int i = 0; i < pos; i++){
            Registro regEntrada = archivoEntrada.getRegistro();

            String codigo = regEntrada.getString();

            //leo los valores que debieran ser enteros y quito los espacios y otros valores no deseados
            String precio = regEntrada.getString();
            precio = precio.replaceAll("\\D+","");

            String stock = regEntrada.getString();
            stock = stock.replaceAll("\\D+","");

            LecturaArchivo[i][0] = regEntrada.getString();
            // reviso que tipo de instrumento es
            aux = Utils.pedirTipoInstrumento(LecturaArchivo[i][0]);

            Instrumento instrumento = null;
            if (aux == 1){
                LecturaArchivo[i][1] = regEntrada.getString();

                LecturaArchivo[i][2] = regEntrada.getString();
                LecturaArchivo[i][2] = LecturaArchivo[i][2].replaceAll("\\D+","");

                LecturaArchivo[i][3] = regEntrada.getString();
                LecturaArchivo[i][4] = regEntrada.getString();
                instrumento = new Cuerda(codigo,Integer.parseInt(precio),Integer.parseInt(stock),LecturaArchivo[i][0],LecturaArchivo[i][1],Integer.parseInt(LecturaArchivo[i][2]),LecturaArchivo[i][3],LecturaArchivo[i][4]);
            } else if (aux == 2) {
                LecturaArchivo[i][1] = regEntrada.getString();
                LecturaArchivo[i][2] = regEntrada.getString();
                LecturaArchivo[i][3] = regEntrada.getString();
                instrumento = new Percusion(codigo,Integer.parseInt(precio),Integer.parseInt(stock),LecturaArchivo[i][0],LecturaArchivo[i][1],LecturaArchivo[i][2],LecturaArchivo[i][3]);
            }else if (aux == 3){
                LecturaArchivo[i][1] = regEntrada.getString();
                instrumento = new Viento(codigo,Integer.parseInt(precio),Integer.parseInt(stock),LecturaArchivo[i][0],LecturaArchivo[i][1]);
            }
            this.instrumentos.agregarInstrumento(instrumento);
        }
        archivoEntrada.close();
    }

    /**
     * Subprograma encargada de actualizar los datos del archivo que almacena el stock
     * @throws IOException
     */
    private void guardarInformacion() throws IOException {
        ArchivoSalida archivoSalida = new ArchivoSalida("inventario.csv");
        Instrumento instrumento = null;
        //recorro la lista de instrumentos
        for (int i = 0; i < instrumentos.getCantActual(); i++) {
            Registro registro = new Registro(8);
            instrumento = this.instrumentos.obtenerInstrumento(i);
            //reviso que tipo de instrumento es y lo sobreescribo
            if (instrumento instanceof Cuerda){
                registro.agregarCampo(instrumento.getCodigo());
                registro.agregarCampo(instrumento.getPrecio());
                registro.agregarCampo(instrumento.getStock());
                registro.agregarCampo(instrumento.getNombre());
                registro.agregarCampo(((Cuerda) instrumento).getMaterialCuerda());
                registro.agregarCampo(((Cuerda) instrumento).getNumeroCuerdas());
                registro.agregarCampo(instrumento.getMaterial());
                registro.agregarCampo(((Cuerda) instrumento).getTipo());
            }else if (instrumento instanceof Percusion){
                registro.agregarCampo(instrumento.getCodigo());
                registro.agregarCampo(instrumento.getPrecio());
                registro.agregarCampo(instrumento.getStock());
                registro.agregarCampo(instrumento.getNombre());
                registro.agregarCampo(instrumento.getMaterial());
                registro.agregarCampo(((Percusion) instrumento).getTipoPercusion());
                registro.agregarCampo(((Percusion) instrumento).getAltura());
            } else if (instrumento instanceof Viento) {
                registro.agregarCampo(instrumento.getCodigo());
                registro.agregarCampo(instrumento.getPrecio());
                registro.agregarCampo(instrumento.getStock());
                registro.agregarCampo(instrumento.getNombre());
                registro.agregarCampo(instrumento.getMaterial());
            }
            archivoSalida.writeRegistro(registro);
        }
        archivoSalida.close();
    }

    /**
     * Subprograma dedicado a mostrar el inventario de manera detallada
     */
    public void mostrarInventario() {
        Instrumento instrumento = null;
        for (int i = 0; i < this.instrumentos.getCantActual(); i++) {
            instrumento = this.instrumentos.obtenerInstrumento(i);
            StdOut.println("Codigo: " + instrumento.getCodigo());
            StdOut.println("Nombre: " + instrumento.getNombre());
            StdOut.println("Stock: " + instrumento.getStock());
            //detalles de cada instrumento:
            if (instrumento instanceof Cuerda){
                StdOut.println("Cuerdas: " + ((Cuerda) instrumento).getNumeroCuerdas() + " de " + ((Cuerda) instrumento).getMaterialCuerda());
            } else if (instrumento instanceof Percusion) {
                StdOut.println("Tipo: " + ((Percusion) instrumento).getTipoPercusion());
                StdOut.println("Altura: " + ((Percusion) instrumento).getAltura());
            } else if (instrumento instanceof Viento) {
                StdOut.println("Material: " + instrumento.getMaterial());
            }
            StdOut.println("");
        }
    }

    /**
     * Subprograma dedicado a buscar un instrumento con el codigo de este
     * @param codigo del instrumento a buscar
     * @return instrumento
     */
    public Instrumento buscarInstrumento(final String codigo) {
        Instrumento instrumento = null;
        // se recorre el contenedor
        for (int i = 0; i < instrumentos.getCantActual(); i++) {
            instrumento = this.instrumentos.obtenerInstrumento(i);
            // si se encuentra, se devuelve el instrumento
        if (codigo.equals(instrumento.getCodigo())) {
            return instrumento;
            }
        }
        // no lo encontre, retorno null.
        return null;
    }

    /**
     *  Subprograma dedicado a agregar un nuevo instrumento al inventario de la tienda
     * @param opcion elegida
     * @param precio
     * @param stock
     * @param codigo
     * @param nombre
     * @param material
     * @param aux
     * @param aux2
     * @param numCuerdas
     */
    public void agregarInstrumento(int opcion, int precio, int stock,String codigo,String nombre,String material,String aux,String aux2,int numCuerdas){
        Instrumento instrumentoAux = null;
        if (opcion == 1){
            instrumentoAux = new Cuerda(codigo,precio,stock,nombre,material,numCuerdas,aux,aux2);
        } else if (opcion == 2) {
            instrumentoAux = new Percusion(codigo,precio,stock,nombre,material,aux,aux2);
        } else if (opcion == 3) {
            instrumentoAux = new Viento(codigo,precio,stock,nombre,material);
        }
        this.instrumentos.agregarInstrumento(instrumentoAux);
        StdOut.println("Se agrego correcamente el instrumento: " + nombre + " de codigo " + codigo);
        StdOut.println("");
    }

    /** Subprograma dedicado a sumarle una cantidad indicada de stock
     * @param instrumento a sumarle stock
     * @param cantidad a agregar
     */
    public void agregarStockInstrumento(Instrumento instrumento, int cantidad) {
        instrumento.agregarInstrumento(cantidad);
    }

    /**
     * Subprograma encargado a realizar la venta
     * @param codigo del instrumento
     * @return false si no se pudo hacer la venta, true si se vendio correctamente
     */
    public boolean realizarVenta(String codigo){
        Instrumento instrumento = buscarInstrumento(codigo);
        //revisa si el instrumento no es nulo
        if (instrumento == null){
            StdOut.println("El codigo " + codigo + " no es valido!");
            return false;
        }
        //revisa si hay stock
        if(instrumento.getStock() < 1){
            StdOut.println("El instrumento " + instrumento.getNombre() + " de codigo " + instrumento.getCodigo() + " no cuenta con stock!");
            return false;
        }

        instrumento.venderInstrumento();
        return true;
    }

    /**
     * Subprograma para apagar el sistema realizando un guardado del archivo
     */
    public void apagarSistema(){

        try{
            this.guardarInformacion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StdOut.println("Cerrando sistema...");

    }

}
