import java.util.Scanner;

public class HeladeriaVersion1 {

    static Scanner input = new Scanner(System.in);

    static String[] helados = new String[20];
    static int totalHelados = 0;

    public static void main(String[] args) {
        String[] opciones = {
                " Agregar un helado al menú",
                " Ver todos los helados disponibles",
                " Modificar un helado del menú",
                " Quitar un helado del menú",
                " Buscar un helado especial",
                " Salir de la heladería"
        };

        mostrarMenu("HELADERÍA LA DULZURA", opciones);
    }
    public static void mostrarMenu(String titulo, String[] opciones) {
        System.out.println("\n=== " + titulo + " ===");

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }

        System.out.print("Seleccione una opción: ");
        int opcion = input.nextInt();
        input.nextLine();

        switch (opcion) {
            case 1:
                agregarHelado();
                break;
            case 2:
                listarHelados();
                break;
            case 3:
                System.out.println(" Función de editar aún en construcción...");
                break;
            case 4:
                System.out.println(" Función de borrar aún en construcción...");
                break;
            case 5:
                System.out.println(" Función de buscar aún en construcción...");
                break;
            case 6:
                System.out.println(" Gracias por visitar la heladería. ¡Vuelve pronto!");
                return;
            default:
                System.out.println(" Opción no válida.");
        }

        // Recursividad: volver al menú principal
        mostrarMenu(titulo, opciones);
    }

    // Submenú para agregar un helado
    public static void agregarHelado() {
        System.out.println("\n ¡Vamos a crear tu helado especial!");

        // Sección 1: Tipo de helado
        String[] tipos = {"Cono", "Copa", "Vaso", "Banana Split", "Helado Gourmet"};
        int tipo = seleccionarOpcion("Selecciona el tipo de helado:", tipos);

        // Sección 2: Sabor
        String[] sabores = {"Vainilla", "Chocolate", "Fresa", "Mango", "Arequipe"};
        int sabor = seleccionarOpcion("Elige un sabor:", sabores);

        // Sección 3: Acompañamiento
        String[] acomp = {"Oblea", "Galleta", "Sirope de chocolate", "Frutas", "Chispas de colores"};
        int acompSel = seleccionarOpcion("Escoge un acompañamiento:", acomp);

        // Guardamos el helado en el inventario
        if (totalHelados < helados.length) {
            helados[totalHelados++] = tipos[tipo] + " de " + sabores[sabor] + " con " + acomp[acompSel];
            System.out.println(" Helado agregado al menú: " + helados[totalHelados-1]);
        } else {
            System.out.println(" No se pueden agregar más helados, inventario lleno.");
        }
    }

    // Función auxiliar recursiva para submenús
    public static int seleccionarOpcion(String titulo, String[] opciones) {
        System.out.println("\n=== " + titulo + " ===");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        System.out.print("Seleccione una opción: ");
        int opcion = input.nextInt() - 1;
        input.nextLine();

        if (opcion < 0 || opcion >= opciones.length) {
            System.out.println(" Opción inválida, inténtalo de nuevo.");
            return seleccionarOpcion(titulo, opciones); // recursividad si se equivoca
        }

        return opcion;
    }

    // Listar todos los helados
    public static void listarHelados() {
        if (totalHelados == 0) {
            System.out.println(" No hay helados en el menú todavía.");
            return;
        }
        System.out.println("\n Carta de Helados:");
        for (int i = 0; i < totalHelados; i++) {
            System.out.println((i + 1) + ". " + helados[i]);
        }
    }
}
