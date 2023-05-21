import model.Instrumento;
import services.Sistema;
import services.Utils;
import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;
import java.util.Objects;

public final class Main {
    public static void main(String[] args) throws IOException {

        Sistema sistema = new Sistema();
        String opcion = null;

        while (!Objects.equals(opcion, "4")){

            StdOut.println("¡Bienvenid@ a Beat the Rhythm!");
            StdOut.println("[1] Agregar instrumento");
            StdOut.println("[2] Vender instrumento");
            StdOut.println("[3] Consultar inventario");
            StdOut.println("[4] Cerrar Sistema");
            StdOut.println("");
            StdOut.println("¿Que desea hacer?");


            opcion = StdIn.readLine();

            switch (opcion){
                case "1" -> agregarInstrumento(sistema);
                case "2" -> venderInstrumento(sistema);
                case "3" -> sistema.mostrarInventario();
                case "4" -> sistema.apagarSistema();
                default -> StdOut.println("Ha ingresado una opción invalida, pruebe nuevamente");
            }
        }
    }

    /**
     * Subprograma encargado de agregar stock o un nuevo instrumento
     * @param sistema
     */
    public static void agregarInstrumento(Sistema sistema){
        StdOut.println("----[Agregar Instrumento]----");
        StdOut.println("¿Que tipo de instrumento desea agregar?");
        StdOut.println("[1] Cuerda");
        StdOut.println("[2] Percusión");
        StdOut.println("[3] Viento");
        String opcion = StdIn.readLine();

        // Variables a usar si se agregara un producto existente
        int stock = 0, precio = 0;
        // Variables a usar si se agregara un producto nuevo
        String nombre = "",codigo = "" , material = "" , aux = "", aux2 = "";
        int auxNum = 0;
        Instrumento instrumento;

        switch (opcion){
            case "1" :
                StdOut.println("Ingrese el codigo del producto (En caso de que no exista se solicitaran detalles)");
                codigo = StdIn.readString();

                // Se pide un numero y se valida que no se ingresen letras
                stock = Utils.pedirNumero("Ingrese el stock a agregar");

                instrumento = sistema.buscarInstrumento(codigo);
                //Si el instrumento no es nulo, se agregara stock
                if (instrumento != null){
                    sistema.agregarStockInstrumento(instrumento, stock);
                    break;
                }

                // Se pide un numero y se valida que no se ingresen letras
                precio = Utils.pedirNumero("Ingrese el precio del producto");

                StdOut.println("¿Que instrumento es?: (Guitarra, Bajo, Violín, Arpa)");
                nombre = StdIn.readString();
                StdOut.println("Tipo de cuerda: (Nylon, Acero, Tripa)");
                aux = StdIn.readString();
                auxNum = Utils.pedirNumero("Numero de cuerdas:");
                StdOut.println("Material de construcción: (Madera, Metal)");
                material = StdIn.readString();
                StdOut.println("Tipo: (Acústico, Eléctrico)");
                aux2 = StdIn.readString();

                //si uno de los elementos no es valdio no se agregara el instrumento
                if (!Utils.validarInstrumentoCuerda(nombre, material, aux, aux2)){
                    break;
                }

                sistema.agregarInstrumento(Integer.parseInt(opcion),precio,stock,codigo,nombre,material,aux,aux2,auxNum);
                break;

                case "2" :

                StdOut.println("Ingrese el codigo del producto (En caso de que no exista se solicitaran detalles)");
                codigo = StdIn.readString();

                // Se pide un numero y se valida que no se ingresen letras
                stock = Utils.pedirNumero("Ingrese el stock a agregar");

                instrumento = sistema.buscarInstrumento(codigo);

                if (instrumento != null){
                    sistema.agregarStockInstrumento(instrumento, stock);
                    break;
                }

                // Se pide un numero y se valida que no se ingresen letras
                precio = Utils.pedirNumero("Ingrese el precio del producto");

                StdOut.println("¿Que instrumento es?: (Bongó, Cajón, Campanas Tubulares, Bombo)");
                nombre = StdIn.readString();
                StdOut.println("Tipo de percusión: (Membranófono, Idiófono).");
                aux = StdIn.readString();
                StdOut.println("Material de construcción: (Madera, Metal, Piel)");
                material = StdIn.readString();
                StdOut.println("Altura: (definida, indefinida)");
                aux2 = StdIn.readString();

                //si uno de los elementos no es valdio no se agregara el instrumento
                if (!Utils.validarInstrumentoPercusion(nombre, material, aux, aux2)){
                    break;
                }

                sistema.agregarInstrumento(Integer.parseInt(opcion),precio,stock,codigo,nombre,material,aux,aux2,auxNum);
                break;

            case "3" :
                StdOut.println("Ingrese el codigo del producto (En caso de que no exista se solicitaran detalles)");
                codigo = StdIn.readString();

                // Se pide un numero y se valida que no se ingresen letras
                stock = Utils.pedirNumero("Ingrese el stock a agregar");

                instrumento = sistema.buscarInstrumento(codigo);

                if (instrumento != null){
                    sistema.agregarStockInstrumento(instrumento, stock);
                    break;
                }

                precio = Utils.pedirNumero("Ingrese el precio del producto");

                StdOut.println("¿Que instrumento es?:  (Trompeta, Saxofón, Clarinete, Flauta traversa)");
                nombre = StdIn.readString();
                StdOut.println("Material (Madera, Metal)");
                material = StdIn.readString();

                //si uno de los elementos no es valdio no se agregara el instrumento
                if (!Utils.validarInstrumentoViento(nombre, material)){
                    break;
                }

                sistema.agregarInstrumento(Integer.parseInt(opcion),precio,stock,codigo,nombre,material,aux,aux2,auxNum);
                break;

            default : StdOut.println("[Error] Ha ingresado una opción invalida, pruebe nuevamente");
        }
    }

    /**
     * Subprograma encargado de realizar a venta de uno o más instrumentos
     * @param sistema
     */
    public static void venderInstrumento(Sistema sistema){
        //Se muestra el stock
        sistema.mostrarInventario();
        //Lista de compras y contador de objetos en la lista
        int auxContador = 0;
        Instrumento[] listaCompras = new Instrumento[100];
        String opcion = null;
        String codigo = "";
       while (!Objects.equals(opcion, "4")){
            StdOut.println("------[Vender Instrumento]------");
            StdOut.println("[1] Agregar producto");
            StdOut.println("[2] Realizar compra");

           StdOut.println("Ingrese la opcion a realizar");
           opcion = StdIn.readString();

           switch (opcion){
               case "1":
                   StdOut.println("Ingrese el codigo del producto a comprar:");
                   codigo = StdIn.readString();
                   Instrumento auxInstrumento =  sistema.buscarInstrumento(codigo);

                   if (auxInstrumento != null){
                       listaCompras[auxContador] = auxInstrumento;
                       StdOut.println("El instrumento se ha agregado a la lista de compras exitosamente!");
                       auxContador++;

                   }else {
                      StdOut.println("El código ingresado no es válido.");
                   }
               break;

               case "2":
                   //si la lista de compras esta vacia no se pueden realizar compras
                   if (listaCompras[0] == null){
                       StdOut.println("Debe ingresar productos a la lista de compra!");
                       break;
                   }
                   //Se empiezan a vender los instrumentos de la lista uno por uno
                   for (int i = 0; i < auxContador; i++) {
                       boolean tieneStock = sistema.realizarVenta(listaCompras[i].getCodigo());

                       if (!tieneStock){
                            listaCompras[i] = null;
                       }
                   }
                   //si el primer elemento no es nulo se despliega la boleta
                   if (listaCompras[0] != null){
                       generarBoleta(listaCompras, auxContador);
                   }
               return;

               default:
                   StdOut.println("Ingreso una opción incorrecta!");
               break;
           }
        }

    }

    /**
     * Subprograma encargado de generar una boleta
     * @param lista - lista que contiene los instrumentos a vender
     * @param max - tamaño de la lista
     */
    public static void generarBoleta(Instrumento[] lista, int max){
        StdOut.println("----------------------------------");
        StdOut.println("|     Boleta Beat The Rhythm     |");
        StdOut.println("|--------------------------------|");
        StdOut.println("|                                |");
        StdOut.println("| Productos:                     |");
        StdOut.println("|                                |");
        for (int i = 0; i < max; i++) {
            if (lista[i] != null){
                StdOut.println("| " +  String.format("%-" + (29 - 4) + "s", lista[i].getNombre()) + String.format("%-" + 5 + "s", "$" + lista[i].getPrecio())+ "|");
            }
        }
        StdOut.println("|                                |");
        StdOut.println("----------------------------------");
    }

}
