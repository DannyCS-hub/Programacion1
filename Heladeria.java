import java.util.ArrayList;
import java.util.Scanner;

public class Heladeria {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<String> compras = new ArrayList<>(); // 🔹 Lista de pedidos

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = menuPrincipal();

            switch (opcion) {
                case 1:
                    menuHelados();
                    break;
                case 2:
                    menuCompras();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 3);
    }

    // 🔹 Menú Principal
    public static int menuPrincipal() {
        System.out.println("****************************");
        System.out.println("*       HELADERÍA          *");
        System.out.println("*--------------------------*");
        System.out.println("* 1. Menú de Helados       *");
        System.out.println("* 2. Ver Compras           *");
        System.out.println("* 3. Terminar              *");
        System.out.println("****************************");
        System.out.print("Seleccione su opción: ");
        return entrada.nextInt();
    }

    // 🔹 Menú de selección de helados
    public static void menuHelados() {
        int opcion;
        do {
            System.out.println("*********************************");
            System.out.println("*       Menú de Helados         *");
            System.out.println("*-------------------------------*");
            System.out.println("* 1. Helado en Cono             *");
            System.out.println("* 2. Helado en Vaso             *");
            System.out.println("* 3. Helado Sundae              *");
            System.out.println("* 4. Helado Banana Split        *");
            System.out.println("* 5. Volver al Menú Principal   *");
            System.out.println("*********************************");
            System.out.print("Seleccione su opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1: case 2: case 3: case 4:
                    menuCantidad(opcion);
                    return;
                case 5:
                    System.out.println("Regresando al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // 🔹 Menú de Cantidad de Bolas
    public static void menuCantidad(int helado) {
        int opcion;
        do {
            System.out.println("****************************");
            System.out.println("*   ¿Cuántas bolas desea?  *");
            System.out.println("*--------------------------*");
            System.out.println("* 1. Una bola              *");
            System.out.println("* 2. Dos bolas             *");
            System.out.println("* 3. Tres bolas            *");
            System.out.println("* 4. Cuatro bolas          *");
            System.out.println("* 5. Volver                *");
            System.out.println("****************************");
            System.out.print("Seleccione su opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1: case 2: case 3: case 4:
                    menuSabores(helado, opcion);
                    return;
                case 5:
                    System.out.println("Regresando...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // 🔹 Menú de Sabores
    public static void menuSabores(int helado, int cantidad) {
        int opcion;
        do {
            System.out.println("****************************");
            System.out.println("*   Seleccione un sabor    *");
            System.out.println("*--------------------------*");
            System.out.println("* 1. Vainilla              *");
            System.out.println("* 2. Chocolate             *");
            System.out.println("* 3. Fresa                 *");
            System.out.println("* 4. Mango                 *");
            System.out.println("* 5. Volver                *");
            System.out.println("****************************");
            System.out.print("Seleccione su opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1: case 2: case 3: case 4:
                    menuComplementos(helado, cantidad, opcion);
                    return;
                case 5:
                    System.out.println("Regresando...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    // 🔹 Menú de Complementos
    public static void menuComplementos(int helado, int cantidad, int sabor) {
        int opcion;
        do {
            System.out.println("****************************");
            System.out.println("*   ¿Desea complementos?   *");
            System.out.println("*--------------------------*");
            System.out.println("* 1. Chispas de Chocolate  *");
            System.out.println("* 2. Salsa de Caramelo     *");
            System.out.println("* 3. Galleta               *");
            System.out.println("* 4. Frutas                *");
            System.out.println("* 5. Sin Complementos      *");
            System.out.println("****************************");
            System.out.print("Seleccione su opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1: case 2: case 3: case 4: case 5:
                    String pedido = cantidad + " bola(s) de helado " +
                            nombreHelado(helado) + " de sabor " + nombreSabor(sabor) +
                            (opcion == 5 ? " sin complementos." : " con " + nombreComplemento(opcion));

                    compras.add(pedido); // Se guarda el pedido
                    System.out.println(" Pedido agregado: " + pedido);
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion < 1 || opcion > 5);
    }

    // 🔹 Menú Compras
    public static void menuCompras() {
        System.out.println("****************************");
        System.out.println("*       COMPRAS            *");
        System.out.println("****************************");

        if (compras.isEmpty()) {
            System.out.println("No ha realizado ningún pedido todavía.");
        } else {
            for (int i = 0; i < compras.size(); i++) {
                System.out.println((i + 1) + ". " + compras.get(i));
            }
        }
        System.out.println("****************************");
    }

    // Métodos de nombres
    public static String nombreHelado(int opcion) {
        switch (opcion) {
            case 1: return "en Cono";
            case 2: return "en Vaso";
            case 3: return "Sundae";
            case 4: return "Banana Split";
            default: return "desconocido";
        }
    }

    public static String nombreSabor(int opcion) {
        switch (opcion) {
            case 1: return "Vainilla";
            case 2: return "Chocolate";
            case 3: return "Fresa";
            case 4: return "Mango";
            default: return "Desconocido";
        }
    }

    public static String nombreComplemento(int opcion) {
        switch (opcion) {
            case 1: return "Chispas de Chocolate";
            case 2: return "Salsa de Caramelo";
            case 3: return "Galleta";
            case 4: return "Frutas";
            default: return "Sin Complementos";
        }
    }
}


