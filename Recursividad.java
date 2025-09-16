import java.text.Collator;
import java.util.Locale;
import java.util.Scanner;

public class Recursividad {
    public static int NRO_USUARIOS = 10;
    public static String[] nombre = new String[NRO_USUARIOS];
    public static float[] sueldo = new float[NRO_USUARIOS];
    static int proximoUsuario = 0; // cantidad de usuarios registrados

    // Collator para comparar cadenas en español
    static Collator collator = Collator.getInstance(new Locale("es", "ES"));

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Borrar usuario por índice");
            System.out.println("3. Ver lista de usuarios");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    if (proximoUsuario < NRO_USUARIOS) {
                        System.out.print("Ingrese nombre: ");
                        String n = sc.nextLine();
                        System.out.print("Ingrese sueldo: ");
                        float s = sc.nextFloat();
                        sc.nextLine();
                        agregarUsuarioOrdenado(n, s);
                    } else {
                        System.out.println(" No hay espacio para más usuarios.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el índice a borrar: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    int res = borrarRegistro(idx);
                    if (res == 1) {
                        System.out.println(" Usuario borrado.");
                    } else {
                        System.out.println(" Índice inválido.");
                    }
                    break;

                case 3:
                    imprimirTodos();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println(" Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }

    // Agregar usuario manteniendo el orden alfabético (usando Collator)
    public static int agregarUsuarioOrdenado(String nombreUsuario, float sueldoC) {
        if (proximoUsuario >= NRO_USUARIOS) {
            return 0; // No hay espacio
        }

        int pos = proximoUsuario;

        // Encontrar posición donde insertar según orden alfabético español
        for (int i = 0; i < proximoUsuario; i++) {
            if (collator.compare(nombreUsuario, nombre[i]) < 0) {
                pos = i;
                break;
            }
        }

        // Desplazar a la derecha desde "pos" usando recursividad
        desplazarDerechaRecursivo(proximoUsuario, pos);

        // Insertar en la posición encontrada
        nombre[pos] = nombreUsuario;
        sueldo[pos] = sueldoC;
        proximoUsuario++;

        return 1;
    }

    // Función recursiva para desplazar elementos a la derecha
    private static void desplazarDerechaRecursivo(int actual, int limite) {
        if (actual <= limite) return;
        nombre[actual] = nombre[actual - 1];
        sueldo[actual] = sueldo[actual - 1];
        desplazarDerechaRecursivo(actual - 1, limite);
    }

    // Borrar registro con recursividad
    public static int borrarRegistro(int indice) {
        if ((indice >= 0) && (indice < proximoUsuario)) {
            desplazarIzquierdaRecursivo(indice);
            nombre[proximoUsuario - 1] = null;
            sueldo[proximoUsuario - 1] = 0;
            proximoUsuario--;
            return 1;
        } else {
            return -1; // índice inválido
        }
    }

    // Función recursiva para desplazar hacia la izquierda
    private static void desplazarIzquierdaRecursivo(int indice) {
        if (indice >= proximoUsuario - 1) return;
        nombre[indice] = nombre[indice + 1];
        sueldo[indice] = sueldo[indice + 1];
        desplazarIzquierdaRecursivo(indice + 1);
    }

    // Imprimir usuarios con recursividad
    public static void imprimirTodos() {
        if (proximoUsuario == 0) {
            System.out.println(" No hay usuarios registrados.");
        } else {
            System.out.println("\n--- Lista de usuarios ---");
            imprimirRecursivo(0);
        }
    }

    private static void imprimirRecursivo(int indice) {
        if (indice >= proximoUsuario) return;
        System.out.println(indice + " -> " + nombre[indice] + " | $" + sueldo[indice]);
        imprimirRecursivo(indice + 1);
    }
}
