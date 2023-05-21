package services;

import ucn.*;

import java.io.IOException;
import java.text.Normalizer;
import java.util.*;

public class Utils {

    private Utils() {
        //vacio
    }

    /**
     * Subprograma encargado de validar los datos de un instrumento tipo cuerda
     * @param nombre del instrumento
     * @param material del instrumento
     * @param materialCuerdas material de las cuerdas del instrumento
     * @param tipo de intrumento (acustico / electrico)
     * @return
     */
    public static boolean validarInstrumentoCuerda(String nombre,String material, String materialCuerdas , String tipo){
        nombre = quitarTildes(nombre);
        material = quitarTildes(material);
        materialCuerdas = quitarTildes(materialCuerdas);
        tipo = quitarTildes(tipo);
        if (nombre.equalsIgnoreCase("guitarra") || nombre.equalsIgnoreCase("bajo") || nombre.equalsIgnoreCase("violin") || nombre.equalsIgnoreCase("arpa")){
            if (materialCuerdas.equalsIgnoreCase("nylon") || materialCuerdas.equalsIgnoreCase("acero") || materialCuerdas.equalsIgnoreCase("tripa")){
                if (material.equalsIgnoreCase("madera") || material.equalsIgnoreCase("metal")){
                    if (tipo.equalsIgnoreCase("acustico") || tipo.equalsIgnoreCase("electrico")){
                        return true;
                    }else {
                        StdOut.println("El tipo de instrumento ingresado no es valido!");
                    }
                }else {
                    StdOut.println("El material ingresado no es valido!");
                }
            }else {
                StdOut.println("El tipo de cuerdas ingresado no es valido!");
            }
        }else {
            StdOut.println("El instrumento ingresado no es valido!");
        }
        return false;
    }

    /**
     * Subprograma encargado de validar los datos de un instrumento tipo percusion
     * @param nombre del instrumento
     * @param material del instrumento (madrea, metal, piel)
     * @param tipo de percusion del instrumento (membrafono / idiofono)
     * @param altura del instrumento (definido / no definido)
     * @return
     */
    public static boolean validarInstrumentoPercusion(String nombre,String material, String tipo,String altura){
        nombre = quitarTildes(nombre);
        material = quitarTildes(material);
        altura = quitarTildes(altura);
        tipo = quitarTildes(tipo);
        if (nombre.equalsIgnoreCase("bongo") || nombre.equalsIgnoreCase("cajon") || nombre.equalsIgnoreCase("campanas tubulares") || nombre.equalsIgnoreCase("bombo")){
            if (tipo.equalsIgnoreCase("membranofono") || tipo.equalsIgnoreCase("idiofono")){
                if (material.equalsIgnoreCase("madera") || material.equalsIgnoreCase("metal") || material.equalsIgnoreCase("piel")){
                    if (altura.equalsIgnoreCase("definida") || altura.equalsIgnoreCase("indefinida")){
                        return true;
                    }else{
                        StdOut.println("La altura ingresada no es valida!");
                    }
                }else {
                    StdOut.println("El material ingresado no es valido!");
                }
            } else{
                StdOut.println("El tipo de percusion ingresado no es valido!");
            }
        }else {
            StdOut.println("El nombre del instrumento ingresado no es valido!");
        }
        return false;
    }

    /**
     * Subprograma encargado de validar los datos de un instrumento tipo viento
     * @param nombre del instrumento
     * @param material del instrumento
     * @return true si es valido o false si un dato no es valido
     */
    public static boolean validarInstrumentoViento(String nombre,String material){
        //se quitan tildes del texto
        nombre = quitarTildes(nombre);
        material = quitarTildes(material);
        // se revisa que los datos sean validos
        if (nombre.equalsIgnoreCase("trompeta") || nombre.equalsIgnoreCase("saxofon") || nombre.equalsIgnoreCase("clarinete") || nombre.equalsIgnoreCase("flauta traversa")){
            if (material.equalsIgnoreCase("madera") || material.equalsIgnoreCase("metal")){
                return true;
            }else {
                StdOut.println("El material ingresado no es valido!");
            }
        }else {
            StdOut.println("El nombre del instrumento ingresado no es valido!");
        }
        return false;
    }

    /**
     * Subprograma encargado de solicitar un numero, en caso de que este contenga letras se pedira el numero hasta que se entregue mostrando nuevamente el enunciado
     * @param enunciado - enunciado a mostrar
     * @return numero sin ningun caracter alfabetico
     */
    public static int pedirNumero(String enunciado) {
        int numero = 0;
        String opcion = "";
        boolean esNumero = false;

        while (!esNumero) {
            StdOut.println(enunciado);
            opcion = StdIn.readString();
            try {
                numero = Integer.parseInt(opcion);
                esNumero = true;
            } catch (NumberFormatException e) {
                StdOut.println("[Error] Debe ingresar un número entero válido en este apartado");
            }
        }

        return numero;
    }

    /**
     * Subprograma encargado de verificar que tipo de instrumento es revisando su nombre devolviendo un numero segun el tipo de instrumento
     * @param nombre del instrumento a revisar
     * @return numero entero - 1 = cuerda, 2 = percusion, 3 = viento
     */
    public static int pedirTipoInstrumento(String nombre){
        String instrumentoFinal = quitarTildes(nombre);

        int tipo = 0;
        // Se checkea si es un instrumento de cuerda
        if (instrumentoFinal.equalsIgnoreCase("guitarra") || instrumentoFinal.equalsIgnoreCase("bajo") || instrumentoFinal.equalsIgnoreCase("violin") || instrumentoFinal.equalsIgnoreCase("arpa")){
            tipo = 1;
        }
        // Se checkea si es un instrumento de percusion
        if (instrumentoFinal.equalsIgnoreCase("bongo") || instrumentoFinal.equalsIgnoreCase("cajon") || instrumentoFinal.equalsIgnoreCase("campanas tubulares") || instrumentoFinal.equalsIgnoreCase("bombo")){
            tipo = 2;
        }
        // Se checkea si es un instrumento de viento
        if (instrumentoFinal.equalsIgnoreCase("trompeta") || instrumentoFinal.equalsIgnoreCase("saxofon") || instrumentoFinal.equalsIgnoreCase("clarinete") || instrumentoFinal.equalsIgnoreCase("flauta traversa")){
            tipo = 3;
        }

        return tipo;
    }

    /**
     * Subprograma encargado de quitar tildes y acentos de una cadena de texto
     * Autor: Jorgesys - Link: https://es.stackoverflow.com/questions/31178/c%C3%B3mo-limpiar-string-de-tildes-en-java
     * @param texto a limpiar de tildes / acentos
     * @return texto sin tildes / acentos
     */
    public static String quitarTildes(String texto){
        String stringNormalize = Normalizer.normalize(texto, Normalizer.Form.NFD);
        String stringFinal = stringNormalize.replaceAll("[^\\p{ASCII}]", "");
        return stringFinal;
    }
}
